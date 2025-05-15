package com.udi.rrhh.vista;

import com.udi.rrhh.dao.FuncionarioDAO;
import com.udi.rrhh.dao.impl.FuncionarioDAOImpl;
import com.udi.rrhh.excepciones.DAOException;
import com.udi.rrhh.modelo.Funcionario;
import javax.swing.JOptionPane;

class FuncionarioService {

        private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl(); // Crear una instancia del DAO

        public FuncionarioService() {
        }

        void crearFuncionario(Funcionario funcionario) {
            try {
                funcionarioDAO.crear(funcionario);
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el funcionario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Considera usar un logger
            }
        }

        void actualizarFuncionario(Funcionario funcionario) {
            try {
                funcionarioDAO.actualizar(funcionario);
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el funcionario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Considera usar un logger
            }
        }
    }
