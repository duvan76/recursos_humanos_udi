package com.udi.rrhh.dao;


import com.udi.rrhh.excepciones.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/recursos_humanos_udi";
    private static final String USUARIO = "root"; // Reemplaza con tu usuario
    private static final String CLAVE = "1234";   // Reemplaza con tu clave
    private static volatile Connection conexion;

    private ConexionBD() {}

    public static Connection obtenerConexion() throws DAOException {
        Connection conexion = ConexionBD.conexion;
        if (conexion == null) {
            synchronized (ConexionBD.class) {
                conexion = ConexionBD.conexion;
                if (conexion == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        ConexionBD.conexion = conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                    } catch (ClassNotFoundException e) {
                        throw new DAOException("Error al cargar el driver de MySQL", e);
                    } catch (SQLException e) {
                        throw new DAOException("Error al conectar a la base de datos", e);
                    }
                }
            }
        }
        return conexion;
    }

    public static void cerrarConexion() throws DAOException {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar la conexión a la base de datos", e);
            }
        }
    }
}
