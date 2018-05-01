/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class Actualizar {
    
    public static void actualizaDireccion(int id, String direccion) {
        
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        //String direccion = "chalmitas";
        //Cambiar para que el usuario ingrese el id_cliente !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //int id = 1;
        
        String query = "UPDATE cliente SET direccion = ? WHERE cliente.id_cliente = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, direccion);
            ps.setInt(2, id);
            renglones_afectados = ps.executeUpdate();
            System.out.println("Número de renglones actualizados: " + renglones_afectados);
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public static void actualizaMarca(int id, String marca) {
        
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        //String marca = "Audi";
        //Cambiar para que el usuario ingrese el id_cliente !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //int id = 5;
        
        String query = "UPDATE vehiculo SET marca = ? WHERE vehiculo.id_vehiculo = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, marca);
            ps.setInt(2, id);
            renglones_afectados = ps.executeUpdate();
            System.out.println("Número de renglones actualizados: " + renglones_afectados);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            
        }
    }
    
    public static void actualizaModelo(int id, String modelo) {
        
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        //String modelo = "TT";
        //Cambiar para que el usuario ingrese el id_cliente !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //int id = 5;
        
        String query = "UPDATE vehiculo SET modelo = ? WHERE vehiculo.id_vehiculo = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, modelo);
            ps.setInt(2, id);
            renglones_afectados = ps.executeUpdate();
            System.out.println("Número de renglones actualizados: " + renglones_afectados);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            
        }
    }
    
}
