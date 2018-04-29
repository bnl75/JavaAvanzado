/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aseguradora;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Diego
 */
public class Lecturas {
    static double[] montos =new double[4];
    static String[] nombres = new String[4];
    static String[] direcciones = new String[4];
    
    public static void leerFacturas() throws SQLException {
        double costo;
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        try {
            File archivo = new File("C:\\Users\\USER\\Desktop\\Facturas.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);     //Todo esto (renglones anteriores tambien) es para hacer un casteo y saber de qué tipo de documento se trata
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaEmpleados = document.getElementsByTagName("factura");    //Un arreglo de nodos (un nodo es una etiqueta del xml)
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Node nodo = listaEmpleados.item(i);     //Para obtener los nodos en una lista de nodos
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {  //Si es un elemento de tipo nodo
                    Element element = (Element) nodo;
                    
                    costo = Double.parseDouble(element.getElementsByTagName("costo_total").item(0).getTextContent());
                    
                    montos[i] = costo;
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        for (int i = 0; i < montos.length; i++) {
            
            String query = "INSERT INTO factura (monto) VALUES(?)";
            try {
            ps = conn.prepareStatement(query);

            ps.setDouble(1, montos[i]);
            
            renglones_afectados = ps.executeUpdate();
            ps.close();
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
            
        }
        
        conn.close();
        System.out.println("Carga exitosa...");
       
        
    }
    
    
    
    public static void leerClientes() throws SQLException {
        String nombre;
        String direccion;
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        try {
            File archivo = new File("C:\\Users\\USER\\Desktop\\Clientes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);     //Todo esto (renglones anteriores tambien) es para hacer un casteo y saber de qué tipo de documento se trata
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaEmpleados = document.getElementsByTagName("cliente");    //Un arreglo de nodos (un nodo es una etiqueta del xml)
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Node nodo = listaEmpleados.item(i);     //Para obtener los nodos en una lista de nodos
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {  //Si es un elemento de tipo nodo
                    Element element = (Element) nodo;
                    
                    nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    direccion = element.getElementsByTagName("direccion").item(0).getTextContent();
                    
                    nombres[i] = nombre;
                    direcciones[i] = direccion;
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        for (int i = 0; i < nombres.length; i++) {
            
            String query = "INSERT INTO cliente (nombre, direccion) VALUES(?, ?)";
            try {
            ps = conn.prepareStatement(query);
            
            
            ps.setString(1, nombres[i]);
            ps.setString(2, direcciones[i]);
            
            
            renglones_afectados = ps.executeUpdate();
            ps.close();
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
            
        }
        
        conn.close();
        System.out.println("Carga exitosa...");
       
        
    }
    
    
    public static void leerVehiculos() throws SQLException {
        String nombre;
        String direccion;
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        try {
            File archivo = new File("C:\\Users\\USER\\Desktop\\Clientes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);     //Todo esto (renglones anteriores tambien) es para hacer un casteo y saber de qué tipo de documento se trata
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaEmpleados = document.getElementsByTagName("cliente");    //Un arreglo de nodos (un nodo es una etiqueta del xml)
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Node nodo = listaEmpleados.item(i);     //Para obtener los nodos en una lista de nodos
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {  //Si es un elemento de tipo nodo
                    Element element = (Element) nodo;
                    
                    nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    direccion = element.getElementsByTagName("direccion").item(0).getTextContent();
                    
                    nombres[i] = nombre;
                    direcciones[i] = direccion;
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        for (int i = 0; i < nombres.length; i++) {
            
            String query = "INSERT INTO cliente (nombre, direccion) VALUES(?, ?)";
            try {
            ps = conn.prepareStatement(query);
            
            
            ps.setString(1, nombres[i]);
            ps.setString(2, direcciones[i]);
            
            
            renglones_afectados = ps.executeUpdate();
            ps.close();
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
            
        }
        
        conn.close();
        System.out.println("Carga exitosa...");
       
        
    }
    
    
}
