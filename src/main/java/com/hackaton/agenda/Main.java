package com.hackaton.agenda;

import com.hackaton.agenda.controlador.AgendaControlador;
import com.hackaton.agenda.modelo.Agenda;
import com.hackaton.agenda.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Agenda agendaModelo = new Agenda(10);

            VentanaPrincipal vista = new VentanaPrincipal();

            new AgendaControlador(agendaModelo, vista);

            vista.setVisible(true);
        });
    }
}
