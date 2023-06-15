/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Connector;
import view.EditAdminView;
import view.MenuView;

/**
 *
 * @author LENOVO
 */
public class ControllerAdmin {
    EditAdminView adminView;

    public ControllerAdmin(EditAdminView adminView) {
        this.adminView = adminView;
    }
    
    public void editAdmin() {
        String nama = adminView.getnama().getText();
        String username = adminView.getusername().getText();
        String password = adminView.getpassword().getText();

        try {
            Connector connector = new Connector();

            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                String message = "Input Ada Yang Salah Atau Kosong !";
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
//                loginView.dispose();
//                new LoginView().setVisible(true);
            }else{
                String query = "UPDATE users SET nama = '" + nama + "', password = '" + password + "' WHERE username = '" + username + "'";
                
                Statement statement = connector.koneksi.createStatement();
                int rowsAffected = statement.executeUpdate(query);
                
                System.out.println(" baris berhasil ditambahkan"); 

                String message1 = "Update Berhasil";
                JOptionPane.showMessageDialog(null, message1, "Message", JOptionPane.INFORMATION_MESSAGE);

                statement.close();
                connector.koneksi.close();

                adminView.dispose();
                new MenuView().setVisible(true);
                
            }

        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
        }
    }
}
