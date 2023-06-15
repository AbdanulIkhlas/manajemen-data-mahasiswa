/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.LoginView;
import view.ProfileView;


/**
 *
 * @author LENOVO
 */
public class DataModel {

    public DataModel() {
    }
    
    public void getData(String sql, int head, DefaultTableModel model){

        try{
            //membuat statemen untuk memanggil data table
            Connector connection = new Connector();   
            Statement stat = connection.koneksi.createStatement();
            String query = sql;
            ResultSet res = stat.executeQuery(query);

            while(res.next()){
                String[] kolom = new String[head];
                for (int i = 1; i < head; i++) {
                    kolom[i- 1] = res.getString(i);
                }
                model.addRow(kolom);
            }
        }catch(SQLException err){
              JOptionPane.showMessageDialog(null, err.getMessage() );
        }
    }
    
    public void getprofile(String nama, String username, String password){
        ProfileView profileView = new ProfileView(); 
        profileView.lnama.setText("");
        profileView.lnama.setVisible(true);     
    }
}
