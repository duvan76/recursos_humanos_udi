package com.udi.rrhh.vista;

import com.udi.rrhh.excepciones.DAOException;
import com.udi.rrhh.modelo.Funcionario;
import com.udi.rrhh.modelo.NivelEstudio;
import com.udi.rrhh.modelo.Universidad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class FuncionarioForm extends JFrame {

    private FuncionarioService funcionarioService = new FuncionarioService(); // Usa la clase externa
    private JTextField txtTipoIdentificacion;
    private JTextField txtNumeroIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEstadoCivil;
    private JComboBox<Character> cmbSexo;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtFechaNacimiento;
    private JComboBox<NivelEstudio> cmbNivelEstudio;
    private JComboBox<Universidad> cmbUniversidad;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private Funcionario funcionarioEditar;
    private ListaFuncionarioForm listaFuncionariosForm;

    public FuncionarioForm(ListaFuncionarioForm listaFuncionariosForm) {
        this.listaFuncionariosForm = listaFuncionariosForm;
        setTitle("Formulario de Funcionario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 5, 5));
        setPreferredSize(new Dimension(400, 450));

        // Inicializar componentes
        txtTipoIdentificacion = new JTextField(10);
        txtNumeroIdentificacion = new JTextField(20);
        txtNombres = new JTextField(30);
        txtApellidos = new JTextField(30);
        txtEstadoCivil = new JTextField(20);
        cmbSexo = new JComboBox<>(new Character[]{'M', 'F'});
        txtDireccion = new JTextField(50);
        txtTelefono = new JTextField(20);
        txtFechaNacimiento = new JTextField("AAAA-MM-DD");
        cmbNivelEstudio = new JComboBox<>();
        cmbUniversidad = new JComboBox<>();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        cargarNivelesEstudio();
        cargarUniversidades();

        // Agregar etiquetas y campos al formulario
        add(new JLabel("Tipo de Identificación:"));
        add(txtTipoIdentificacion);
        add(new JLabel("Número de Identificación:"));
        add(txtNumeroIdentificacion);
        add(new JLabel("Nombres:"));
        add(txtNombres);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Estado Civil:"));
        add(txtEstadoCivil);
        add(new JLabel("Sexo:"));
        add(cmbSexo);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Fecha de Nacimiento (AAAA-MM-DD):"));
        add(txtFechaNacimiento);
        add(new JLabel("Nivel de Estudio:"));
        add(cmbNivelEstudio);
        add(new JLabel("Universidad:"));
        add(cmbUniversidad);
        add(btnGuardar);
        add(btnCancelar);

        // Manejadores de eventos
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarFuncionario();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void cargarFuncionario(Funcionario funcionario) {
        this.funcionarioEditar = funcionario;
        txtTipoIdentificacion.setText(funcionario.getTipoIdentificacion());
        txtNumeroIdentificacion.setText(funcionario.getNumeroIdentificacion());
        txtNombres.setText(funcionario.getNombres());
        txtApellidos.setText(funcionario.getApellidos());
        txtEstadoCivil.setText(funcionario.getEstadoCivil());
        cmbSexo.setSelectedItem(funcionario.getSexo());
        txtDireccion.setText(funcionario.getDireccion());
        txtTelefono.setText(funcionario.getTelefono());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtFechaNacimiento.setText(sdf.format(funcionario.getFechaNacimiento()));
        cmbNivelEstudio.setSelectedItem(funcionario.getNivelEstudio());
        cmbUniversidad.setSelectedItem(funcionario.getUniversidad());
        txtNumeroIdentificacion.setEditable(false); // No permitir editar la clave primaria
    }

    private void cargarNivelesEstudio() {
        // Aquí deberías tener un DAO para NivelEstudio
        cmbNivelEstudio.addItem(new NivelEstudio(1, "Bachillerato"));
        cmbNivelEstudio.addItem(new NivelEstudio(2, "Técnico Profesional"));
        cmbNivelEstudio.addItem(new NivelEstudio(3, "Tecnólogo"));
        cmbNivelEstudio.addItem(new NivelEstudio(4, "Profesional Universitario"));
        cmbNivelEstudio.addItem(new NivelEstudio(5, "Especialización"));
        cmbNivelEstudio.addItem(new NivelEstudio(6, "Maestría"));
        cmbNivelEstudio.addItem(new NivelEstudio(7, "Doctorado"));
    }

    private void cargarUniversidades() {
        // Aquí deberías tener un DAO para Universidad
        cmbUniversidad.addItem(new Universidad(1, "Universidad de Antioquia"));
        cmbUniversidad.addItem(new Universidad(2, "Universidad Nacional de Colombia"));
        cmbUniversidad.addItem(new Universidad(3, "Politécnico Colombiano Jaime Isaza Cadavid"));
        cmbUniversidad.addItem(new Universidad(4, "Instituto Tecnológico Metropolitano"));
        cmbUniversidad.addItem(new Universidad(5, "Universidad Pontificia Bolivariana"));
    }

    public void actualizar(Funcionario funcionario) throws DAOException {
        funcionarioService.actualizarFuncionario(funcionario);
        JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        listaFuncionariosForm.cargarFuncionarios();
        dispose();
    }

    private void guardarFuncionario() {
        String tipoIdentificacion = txtTipoIdentificacion.getText();
        String numeroIdentificacion = txtNumeroIdentificacion.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String estadoCivil = txtEstadoCivil.getText();
        char sexo = (char) cmbSexo.getSelectedItem();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String fechaNacimientoStr = txtFechaNacimiento.getText();
        NivelEstudio nivelEstudio = (NivelEstudio) cmbNivelEstudio.getSelectedItem();
        Universidad universidad = (Universidad) cmbUniversidad.getSelectedItem();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNac = null;
        try {
            fechaNac = sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto (AAAA-MM-DD)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Funcionario funcionario = new Funcionario(tipoIdentificacion, numeroIdentificacion, nombres, apellidos, estadoCivil, sexo, direccion, telefono, fechaNac, nivelEstudio, universidad);

        if (funcionarioEditar == null) {
            funcionarioService.crearFuncionario(funcionario);
            JOptionPane.showMessageDialog(this, "Funcionario guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            funcionarioService.actualizarFuncionario(funcionario);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        listaFuncionariosForm.cargarFuncionarios(); // Recargar la lista
        dispose();
    }
}