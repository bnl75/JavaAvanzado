/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego
 */
public class Consultas {
    
    public static String todoCliente() {

        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = ""; // se movio para poder ser retornada
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT * FROM cliente";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            
            while (rs.next()) {
                //String msj = ""; se mueve afuera del while y del try para que pueda ser retornada
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Id cliente: " + rs.getInt("id_cliente") + "  ";
                msj += "Nombre: " + rs.getString("nombre") + "  ";
                msj += "Dirección: " + rs.getString("direccion") + "  ";
                msj += "\n";
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;
    }
    
    public static String todoFactura() {
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT * FROM factura ORDER BY id_factura";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Id factura: " + rs.getInt("id_factura") + "  ";
                msj += "Monto: " + rs.getDouble("monto") + "  ";
                msj += "\n";
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;
    }
    
    public static String todoClienteVehiculo() {
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT cliente.nombre, vehiculo.placa, vehiculo.modelo, factura.monto FROM cliente, vehiculo, factura WHERE vehiculo.id_factura = factura.id_factura AND vehiculo.id_cliente = cliente.id_cliente; ";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Cliente: " + rs.getString("cliente.nombre") + "  ";
                msj += "Placa: " + rs.getString("vehiculo.placa") + "  ";
                msj += "Modelo: " + rs.getString("vehiculo.modelo") + "  ";
                msj += "Costo: $" + rs.getDouble("factura.monto") + "  ";
                msj += "\n";
                
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;
    }
    
    
    public static String mostrarFechas() {
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT poliza.fapertura, TIMESTAMPADD(YEAR, 1, poliza.fapertura) FROM poliza ORDER BY poliza.fapertura";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Apertura: " + rs.getDate("poliza.fapertura") + "   ";
                msj += "Vencimiento: " + rs.getDate("TIMESTAMPADD(YEAR, 1, poliza.fapertura)") + "   ";
                msj += "\n";
                
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;
    }
    
    public static String mayorCostoPoliza() {
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT cliente.nombre, vehiculo.placa, poliza.costo FROM cliente, vehiculo, factura, poliza WHERE factura.id_factura = vehiculo.id_factura AND factura.id_factura = poliza.id_factura AND cliente.id_cliente = poliza.id_cliente AND poliza.costo = ( SELECT MAX( poliza.costo )  FROM poliza)";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Cliente: " + rs.getString("cliente.nombre") + "  ";
                msj += "Placa: " + rs.getString("vehiculo.placa") + "  ";
                msj += "Costo poliza: $" + rs.getDouble("poliza.costo") + "  ";
                msj += "\n";
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;
    }
    
   public static String busqueda1(String busqueda){
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT cliente.nombre, vehiculo.placa, cliente.direccion, factura.monto FROM cliente, vehiculo, factura WHERE cliente.nombre = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura OR cliente.direccion = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura OR vehiculo.placa = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Cliente: " + rs.getString("cliente.nombre") + " ";
                msj += "Dirección: " + rs.getString("cliente.direccion") + " ";
                msj += "Placa: " + rs.getString("vehiculo.placa") + " ";
                //msj += "Modelo: " + rs.getString("vehiculo.modelo") + " ";
                //msj += "Costo: " + rs.getDouble("factura.monto") + " ";
                msj += "\n";
                
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj;     
    }
   
   public static String busqueda2(String busqueda){
        
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String msj = "";
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "SELECT cliente.nombre, vehiculo.placa, poliza.costo, poliza.prima FROM cliente, vehiculo, factura, poliza WHERE cliente.nombre = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura AND vehiculo.id_vehiculo = poliza.id_vehiculo OR vehiculo.placa = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura AND vehiculo.id_vehiculo = poliza.id_vehiculo OR factura.monto = '" + busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura AND vehiculo.id_vehiculo = poliza.id_vehiculo OR poliza.prima = '" +busqueda + "' AND vehiculo.id_cliente = cliente.id_cliente AND factura.id_factura = vehiculo.id_factura AND vehiculo.id_vehiculo = poliza.id_vehiculo";
        
        try {
            stmt = conn.createStatement();     //Creamos el statement
            rs = stmt.executeQuery(query);     //Guardamos el resultado de nuestra query
            rs = stmt.getResultSet();
            System.out.println("Consulta exitosa: ");
            while (rs.next()) {
                //String msj = "";
                
                 //Imprime de las columnas de la base de datos con el getInt
                msj += "Cliente: " + rs.getString("cliente.nombre") + " ";
                //msj += "Dirección: " + rs.getString("cliente.direccion") + " ";
                msj += "Placa: " + rs.getString("vehiculo.placa") + " ";
                //msj += "Modelo: " + rs.getString("vehiculo.modelo") + " ";
                msj += "Costo de póliza: $" + rs.getDouble("poliza.costo") + " ";
                msj += "Prima: $" + rs.getDouble("poliza.prima") + " ";
                msj += "\n";
                
                System.out.println(msj);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return msj; 
    }
   
}

