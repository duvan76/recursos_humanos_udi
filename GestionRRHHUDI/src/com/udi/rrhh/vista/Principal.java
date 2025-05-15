
package com.udi.rrhh.vista;


public class Principal {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new ListaFuncionarioForm().setVisible(true);
        });
    }
}
