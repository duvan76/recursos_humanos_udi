package com.udi.rrhh.dao;

import com.udi.rrhh.modelo.Funcionario;
import com.udi.rrhh.excepciones.DAOException;
import java.util.List;

public interface FuncionarioDAO {
    void crear(Funcionario funcionario) throws DAOException;
    Funcionario obtener(String numeroIdentificacion) throws DAOException;
    List<Funcionario> obtenerTodos() throws DAOException;
    void actualizar(Funcionario funcionario) throws DAOException;
    void eliminar(String numeroIdentificacion) throws DAOException;
}
