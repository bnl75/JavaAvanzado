/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aseguradora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class Poblar {
    
    public static void poblarFactura(double x) {
        Connection conn;
        PreparedStatement ps;
        //int renglones_afectados;
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "INSERT INTO factura (monto) VALUES(?)";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setDouble(1, x);
            //renglones_afectados = ps.executeUpdate();
            //System.out.println("Carga exitosa...");
            //System.out.println("Renglones afectados: " + renglones_afectados);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
   /* public static void main(String[] args) {
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        
        String query = "INSERT INTO factura (monto) VALUES(?)";
        double monto = 200000;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setDouble(1, monto);
            renglones_afectados = ps.executeUpdate();
            System.out.println("Carga exitosa...");
            System.out.println("Renglones afectados: " + renglones_afectados);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            
        }
    }*/
}
