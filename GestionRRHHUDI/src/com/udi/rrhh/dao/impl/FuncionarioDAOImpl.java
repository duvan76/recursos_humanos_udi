package com.udi.rrhh.dao.impl;

import com.udi.rrhh.dao.ConexionBD;
import com.udi.rrhh.dao.FuncionarioDAO;
import com.udi.rrhh.excepciones.DAOException; // Importación correcta
import com.udi.rrhh.modelo.Funcionario;
import com.udi.rrhh.modelo.NivelEstudio;
import com.udi.rrhh.modelo.Universidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    private static final String INSERT = "INSERT INTO Funcionario (tipoIdentificacion, numeroIdentificacion, nombres, apellidos, estadoCivil, sexo, direccion, telefono, fechaNacimiento, nivelEstudioId, universidadId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ONE = "SELECT f.*, ne.nombre AS nombreNivelEstudio, u.nombre AS nombreUniversidad FROM Funcionario f JOIN NivelEstudio ne ON f.nivelEstudioId = ne.nivelEstudioId JOIN Universidad u ON f.universidadId = u.universidadId WHERE numeroIdentificacion = ?";
    private static final String SELECT_ALL = "SELECT f.*, ne.nombre AS nombreNivelEstudio, u.nombre AS nombreUniversidad FROM Funcionario f JOIN NivelEstudio ne ON f.nivelEstudioId = ne.nivelEstudioId JOIN Universidad u ON f.universidadId = u.universidadId";
    private static final String UPDATE = "UPDATE Funcionario SET tipoIdentificacion = ?, nombres = ?, apellidos = ?, estadoCivil = ?, sexo = ?, direccion = ?, telefono = ?, fechaNacimiento = ?, nivelEstudioId = ?, universidadId = ? WHERE numeroIdentificacion = ?";
    private static final String DELETE = "DELETE FROM Funcionario WHERE numeroIdentificacion = ?";

    @Override
    public Funcionario obtener(String numeroIdentificacion) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(SELECT_ONE);
            stmt.setString(1, numeroIdentificacion);
            rs = stmt.executeQuery();
            if (rs.next()) {
                funcionario = construirFuncionario(rs);
            } else {
                throw new DAOException("Funcionario no encontrado con ID: " + numeroIdentificacion);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el funcionario con ID: " + numeroIdentificacion, e);
        } finally {
            cerrarRecursos(rs, stmt, conn); // Incluimos ResultSet
        }
        return funcionario;
    }

    @Override
    public void actualizar(Funcionario funcionario) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, funcionario.getTipoIdentificacion());
            stmt.setString(2, funcionario.getNombres());
            stmt.setString(3, funcionario.getApellidos());
            stmt.setString(4, funcionario.getEstadoCivil());
            stmt.setString(5, String.valueOf(funcionario.getSexo()));
            stmt.setString(6, funcionario.getDireccion());
            stmt.setString(7, funcionario.getTelefono());
            stmt.setDate(8, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            stmt.setInt(9, funcionario.getNivelEstudio().getNivelEstudioId());
            stmt.setInt(10, funcionario.getUniversidad().getUniversidadId());
            stmt.setString(11, funcionario.getNumeroIdentificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el funcionario", e);
        } finally {
            cerrarRecursos(stmt, conn);
        }
    }

    @Override
    public void eliminar(String numeroIdentificacion) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, numeroIdentificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el funcionario con ID: " + numeroIdentificacion, e);
        } finally {
            cerrarRecursos(stmt, conn);
        }
    }

    @Override
    public List<Funcionario> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                funcionarios.add(construirFuncionario(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los funcionarios", e);
        } finally {
            cerrarRecursos(rs, stmt, conn); // Incluimos ResultSet
        }
        return funcionarios;
    }

    @Override
    public void crear(Funcionario funcionario) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, funcionario.getTipoIdentificacion());
            stmt.setString(2, funcionario.getNumeroIdentificacion());
            stmt.setString(3, funcionario.getNombres());
            stmt.setString(4, funcionario.getApellidos());
            stmt.setString(5, funcionario.getEstadoCivil());
            stmt.setString(6, String.valueOf(funcionario.getSexo()));
            stmt.setString(7, funcionario.getDireccion());
            stmt.setString(8, funcionario.getTelefono());
            stmt.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            stmt.setInt(10, funcionario.getNivelEstudio().getNivelEstudioId());
            stmt.setInt(11, funcionario.getUniversidad().getUniversidadId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al crear el funcionario", e);
        } finally {
            cerrarRecursos(stmt, conn);
        }
    }

    private Funcionario construirFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setTipoIdentificacion(rs.getString("tipoIdentificacion"));
        funcionario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
        funcionario.setNombres(rs.getString("nombres"));
        funcionario.setApellidos(rs.getString("apellidos"));
        funcionario.setEstadoCivil(rs.getString("estadoCivil"));
        funcionario.setSexo(rs.getString("sexo").charAt(0));
        funcionario.setDireccion(rs.getString("direccion"));
        funcionario.setTelefono(rs.getString("telefono"));
        funcionario.setFechaNacimiento(rs.getDate("fechaNacimiento"));

        NivelEstudio nivelEstudio = new NivelEstudio();
        nivelEstudio.setNivelEstudioId(rs.getInt("nivelEstudioId"));
        nivelEstudio.setNombre(rs.getString("nombreNivelEstudio"));
        funcionario.setNivelEstudio(nivelEstudio);

        Universidad universidad = new Universidad();
        universidad.setUniversidadId(rs.getInt("universidadId"));
        universidad.setNombre(rs.getString("nombreUniversidad"));
        funcionario.setUniversidad(universidad);

        return funcionario;
    }

    private void cerrarRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (SQLException e) {
                    // Considerar usar un logger
                    
                } catch (Exception e) {
                    // Considerar usar un logger
                    
                }
            }
        }
    }
}