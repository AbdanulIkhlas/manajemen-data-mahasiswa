/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.LoginView;
import model.Connector;
import view.MenuView;
import javax.swing.*;
import java.sql.*;
import model.DataModel;
import view.DataView;
import view.EditAdminView;

/**
 *
 * @author LENOVO
 */
public class ControllerLogin {
    LoginView loginView;
    DataModel dataModel = new DataModel();
    
    public ControllerLogin(LoginView loginView) {
        this.loginView = loginView;
    } 
    
    public void cek(){
        String username = loginView.getLoginUsername().getText();
        String password = loginView.getLoginPassword();
        String cek_user = null;
        String cek_pass = null;
        String cek_nama = null;
        
        try {
            Connector connector = new Connector();
            Statement statement = connector.koneksi.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password +"'";
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                cek_user = result.getString("username");
                cek_pass = result.getString("password");
                cek_nama = result.getString("nama");
            }
            
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan");
        }
        
        if (cek_user == null && cek_pass == null) {
            String message = "Username Atau Password Salah";
            JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.INFORMATION_MESSAGE);
        }else {
            String message1 = "Login Berhasil";
            JOptionPane.showMessageDialog(null,message1,"Message",JOptionPane.INFORMATION_MESSAGE);
                       
            new MenuView().setVisible(true);
            loginView.dispose();
        }
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
            }else{
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
