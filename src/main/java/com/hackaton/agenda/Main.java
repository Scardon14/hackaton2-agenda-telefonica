package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

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