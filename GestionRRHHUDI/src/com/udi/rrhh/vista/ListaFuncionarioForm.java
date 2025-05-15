package com.udi.rrhh.vista;

import com.udi.rrhh.dao.FuncionarioDAO;
import com.udi.rrhh.dao.impl.FuncionarioDAOImpl;
import com.udi.rrhh.excepciones.DAOException;
import com.udi.rrhh.modelo.Funcionario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaFuncionarioForm extends JFrame {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
    private JTable tablaFuncionarios;
    private DefaultTableModel modeloTabla;
    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;

    public ListaFuncionarioForm() {
        setTitle("Lista de Funcionarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Inicializar la tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Tipo ID", "Número ID", "Nombres", "Apellidos", "Estado Civil", "Sexo", "Dirección", "Teléfono", "Fecha Nacimiento", "Nivel Estudio", "Universidad"}, 0);
        tablaFuncionarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaFuncionarios);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCrear = new JButton("Crear Funcionario");
        btnEditar = new JButton("Editar Funcionario");
        btnEliminar = new JButton("Eliminar Funcionario");

        panelBotones.add(btnCrear);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // Cargar funcionarios al iniciar
        cargarFuncionarios();

        // Manejadores de eventos de los botones
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FuncionarioForm formulario = new FuncionarioForm(ListaFuncionarioForm.this);
                
                formulario.setVisible(true);
                
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaFuncionarios.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    try {
                        Funcionario funcionario = funcionarioDAO.obtener(numeroIdentificacion);
                        if (funcionario != null) {
                            FuncionarioForm formulario = new FuncionarioForm(ListaFuncionarioForm.this);
                            formulario.cargarFuncionario(funcionario);
                            formulario.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "No se encontró el funcionario con el ID: " + numeroIdentificacion, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (DAOException ex) {
                        JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "Error al cargar el funcionario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "Por favor, seleccione un funcionario para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaFuncionarios.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    int opcion = JOptionPane.showConfirmDialog(ListaFuncionarioForm.this, "¿Está seguro de eliminar el funcionario con ID: " + numeroIdentificacion + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        try {
                            funcionarioDAO.eliminar(numeroIdentificacion);
                            cargarFuncionarios(); // Recargar la tabla después de eliminar
                            JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "Funcionario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (DAOException ex) {
                            JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "Error al eliminar el funcionario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(ListaFuncionarioForm.this, "Por favor, seleccione un funcionario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void cargarFuncionarios() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de recargar
        try {
            List<Funcionario> funcionarios = funcionarioDAO.obtenerTodos();
            for (Funcionario funcionario : funcionarios) {
                Object[] fila = {
                        funcionario.getTipoIdentificacion(),
                        funcionario.getNumeroIdentificacion(),
                        funcionario.getNombres(),
                        funcionario.getApellidos(),
                        funcionario.getEstadoCivil(),
                        funcionario.getSexo(),
                        funcionario.getDireccion(),
                        funcionario.getTelefono(),
                        funcionario.getFechaNacimiento(),
                        funcionario.getNivelEstudio(),
                        funcionario.getUniversidad()
                };
                modeloTabla.addRow(fila);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los funcionarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para ejecutar la lista de funcionarios (si no tienes la clase Principal)
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new ListaFuncionarioForm().setVisible(true);
        });
    }
}