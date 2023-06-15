/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.InsertDataView;
import model.Connector;
import javax.swing.*;
import java.sql.*;
import view.DataView;
import view.MenuView;

/**
 *
 * @author G a r a
 */
public class ControllerInput {

    InsertDataView insertDataView;

    public ControllerInput(InsertDataView insertDataView) {
        this.insertDataView = insertDataView;

    }

    public void input() {
        String nama = insertDataView.getInputNama().getText();
        String nim = insertDataView.getInputNIM().getText();
        String AA = insertDataView.getAA();
        String AOK = insertDataView.getAOK();
        String OPK = insertDataView.getOPK();
        String PBO = insertDataView.getPBO();
        String PKN = insertDataView.getPKN();
        String PPBO = insertDataView.getPPBO();
        String PSCPK = insertDataView.getPSCPK();
        String RPL = insertDataView.getRPL();
        String SCPK = insertDataView.getSCPK();
        String TECHNO = insertDataView.getTECHNO();
        
        String matkul[] = {AA, AOK, OPK, PBO, PKN, PPBO, PSCPK, RPL, SCPK, TECHNO};
        double nilai[] = {0,0,0,0,0,0,0,0,0,0};

        try {
            Connector connector = new Connector();

            for (int i = 0; i < 10; i++) {
                switch (matkul[i]) {
                    case "A" ->   {
                        nilai[i] = 4.0;
                    }
                    case "B+" ->   {
                        nilai[i] = 3.5;
                    }
                    case "B" ->   {
                        nilai[i] = 3.0;
                    }
                    case "C+" ->   {
                        nilai[i] = 2.5;
                    }
                    case "C" ->   {
                        nilai[i] = 2.0;
                    }
                    case "D" ->   {
                        nilai[i] = 1.0;
                    }
                    case "E" ->   {
                        nilai[i] = 0.0;
                    }
                    default ->  {
                    }
                }
            }


            // MENGHITUNG IPS
            double IPS = (((nilai[0] * 2) + (nilai[1] * 3) + (nilai[2] * 3) + (nilai[3] * 3) + (nilai[4] * 2) + (nilai[5] * 1) + (nilai[6] * 1) + (nilai[7] * 3) + (nilai[8] * 3) + (nilai[9] * 3)) / 24);
            
            // END OF COUNTING
            if (nama.isEmpty() || nim.isEmpty() || AA.equals("Pilih Nilai") || AOK.equals("Pilih Nilai") || OPK.equals("Pilih Nilai") || PBO.equals("Pilih Nilai") || PKN.equals("Pilih Nilai") || PPBO.equals("Pilih Nilai") || PSCPK.equals("Pilih Nilai") || RPL.equals("Pilih Nilai") || SCPK.equals("Pilih Nilai") || TECHNO.equals("Pilih Nilai") ) {
                String message = "Input Ada Yang Salah Atau Kosong !";
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if("Tambah".equals(insertDataView.TambahData.getText())){
                    String query = "INSERT INTO mhs(nama, nim, aa, aok, opk, pbo, pkn, ppbo, pscpk, rpl, scpk, techno, ips) VALUES "
                            + "('" + nama + "', '" + nim + "', '" + AA + "', '" + AOK + "', '" + OPK + "', '" + PBO + "',"
                            + " '" + PKN + "', '" + PPBO + "', '" + PSCPK + "', '" + RPL + "', '" + SCPK + "', '" + TECHNO + "', '" + IPS + "' )";

                    Statement statement = connector.koneksi.createStatement();

                    int rowsAffected = statement.executeUpdate(query);
                    System.out.println(" baris berhasil ditambahkan");

                    statement.close();
                    connector.koneksi.close();

                    String message = "Input Berhasil !";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);

                    insertDataView.dispose();
                    new MenuView().setVisible(true);
                }else{
                    String query = "UPDATE mhs SET nama = '" + nama + "', aa = '" + AA 
                            + "', aok = '" + AOK + "', opk = '" + OPK + "',pbo= '" + PBO 
                            + "', pkn = '" + PKN + "', ppbo = '" + PPBO + "', pscpk = '" + PSCPK 
                            + "', rpl = '" + RPL + "', scpk = '" + SCPK + "', techno = '" + TECHNO 
                            + "', ips = '" + IPS +"' WHERE nim = '" + nim + "'";

                    Statement statement = connector.koneksi.createStatement();

                    int rowsAffected = statement.executeUpdate(query);
                    System.out.println(" baris berhasil ditambahkan");

                    statement.close();
                    connector.koneksi.close();

                    String message = "Update Berhasil !";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);

                    new DataView().setVisible(true);
                    insertDataView.dispose();
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());

        }
    }

}
