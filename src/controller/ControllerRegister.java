/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.LoginView;
import model.Connector;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author G a r a
 */
public class ControllerRegister {

    LoginView loginView;

    public ControllerRegister(LoginView loginView) {
        this.loginView = loginView;

    }

    public void input() {
        String nama = loginView.getregisNama().getText();
        String username = loginView.getregisUser().getText();
        String password = loginView.getregisPass();

        try {
            Connector connector = new Connector();

            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                String message = "Input Ada Yang Salah Atau Kosong !";
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
                loginView.dispose();
                new LoginView().setVisible(true);
            } else {
                String query = "INSERT INTO users(nama, username, password) VALUES ('" + nama + "', '" + username + "', '" + password + "')";
                Statement statement = connector.koneksi.createStatement();
                int rowsAffected = statement.executeUpdate(query);
                System.out.println(" baris berhasil ditambahkan");

                String message1 = "Daftar Berhasil";
                JOptionPane.showMessageDialog(null, message1, "Message", JOptionPane.INFORMATION_MESSAGE);

                statement.close();
                connector.koneksi.close();

                loginView.dispose();
                new LoginView().setVisible(true);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
        }

    }

}
