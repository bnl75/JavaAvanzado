/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aseguradora;

import static aseguradora.Lecturas.nombres;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diego
 */
public class Poliza {
    
    public static void crearTablaPoliza() {
        
        Connection conn;
        PreparedStatement ps;
        
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "CREATE TABLE prueba.poliza (id_poliza INT NOT NULL AUTO_INCREMENT, costo DOUBLE NOT NULL, prima DOUBLE NOT NULL, fapertura DATE NOT NULL, id_cliente INT NOT NULL, id_factura INT NOT NULL, PRIMARY KEY (id_poliza)) ENGINE = InnoDB;";
        //La columna de fecha de vencimiento es una columna derivada de la fecha de apertura (apartir de la apertura + 1 año = fecha de vencimiento) por lo tanto no se le crea en la tabla
                
        try {
            ps = conn.prepareStatement(query);
            ps.execute();
            
            
            System.out.println("Tabla creada exitosamente...");
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            
        }
        
    }
    
    public static void cargaPoliza() throws ParseException, SQLException {
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        double[] costos = {277.91, 722.58, 1389.58, 271.80};
        double[] primas = {42500, 110500, 212500, 41565};
       // java.sql.Date[] aperturas; //= new Date({17/5/16, 16/8/25, 17/10/17, 18/7/02});
        java.sql.Date fechaA1 = new Date(17/5/16);
        //String[] vencimientos = {"18/05/16", "17/08/25", "18/10/17", "19/07/02"};
        int[] clientes = {1, 2, 3, 4};
        int[] facturas = {1, 2, 3, 4};
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        //java.sql.Date fecha = new Date(17/8/25);
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        for (int i = 0; i < clientes.length; i++) {
            String query = "INSERT INTO poliza (costo, prima, fapertura, id_cliente, id_factura) VALUES(?, ?, ?, ?, ?)";
            
            try {
            ps = conn.prepareStatement(query);
            
            
            ps.setDouble(1, costos[i]);
            ps.setDouble(2, primas[i]);
            //ps.setDate(3, (Date) sdf.parse(aperturas[i]));
            //ps.setDate(4, (Date) sdf.parse(vencimientos[i]));
            ps.setDate(3, fechaA1);
            ps.setInt(4, clientes[i]);
            ps.setInt(5, facturas[i]);
            
            
            renglones_afectados = ps.executeUpdate();
            ps.close();
            
            } catch (SQLException e) {

            }
            
        }
        
        conn.close();
        System.out.println("Carga en tabla poliza exitosa...");
        
    }
    
    public static void crearIndices() {
        
        Connection conn;
        PreparedStatement ps;
        
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "ALTER TABLE poliza ADD INDEX (id_cliente);";
        String query2 = "ALTER TABLE poliza ADD INDEX (id_factura);";
        
                
        try {
            ps = conn.prepareStatement(query);
            ps.execute();
            ps = conn.prepareStatement(query2);
            ps.execute();
            
            
            System.out.println("Índices creados exitosamente...");
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            
        }
    }
    
    
    public static void crearLlavesForaneas() {
        
        Connection conn;
        PreparedStatement ps;
        
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        String query = "ALTER TABLE poliza ADD CONSTRAINT p_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE;";
        String query2 = "ALTER TABLE poliza ADD CONSTRAINT p_factura_fk FOREIGN KEY (id_factura) REFERENCES factura (id_factura) ON DELETE RESTRICT ON UPDATE CASCADE;";
        String query3 = "ALTER TABLE vehiculo ADD CONSTRAINT v_factura_fk FOREIGN KEY (id_factura) REFERENCES factura (id_factura) ON DELETE RESTRICT ON UPDATE CASCADE;";
        
                
        try {
            ps = conn.prepareStatement(query);
            ps.execute();
            ps = conn.prepareStatement(query2);
            ps.execute();
            ps = conn.prepareStatement(query3);
            ps.execute();
            
            System.out.println("FK's creadas exitosamente...");
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            
        }
    }
    
}
