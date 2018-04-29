/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Diego
 */
public class ClasePrueba {
    static double[] montos =new double[4];
    
    public static void leerFacturas() {
        //ArrayList<Double> montos = new ArrayList<Double>();
        //double[] montos =new double[4];
        double costo;
        
        try {
            File archivo = new File("C:\\Users\\USER\\Desktop\\Facturas.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);     //Todo esto (renglones anteriores tambien) es para hacer un casteo y saber de qué tipo de documento se trata
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaEmpleados = document.getElementsByTagName("factura");    //Un arreglo de nodos (un nodo es una etiqueta del xml)
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Node nodo = listaEmpleados.item(i);     //Para obtener los nodos en una lista de nodos
                //System.out.println("Elemento: " + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {  //Si es un elemento de tipo nodo
                    Element element = (Element) nodo;
                    
                    costo = Double.parseDouble(element.getElementsByTagName("costo_total").item(0).getTextContent());
                    
                    montos[i] = costo;
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws SQLException {
        Connection conn;
        PreparedStatement ps;
        int renglones_afectados;
        
        leerFacturas();
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
        for (int i = 0; i < montos.length; i++) {
            
             //Conexion.cargar();
        //conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
            
            String query = "INSERT INTO factura (monto) VALUES(?)";
            try {
            ps = conn.prepareStatement(query);
            
            /*for (int i = 0; i < 4; i++) {
                //ps = conn.prepareStatement(query);
                ps.setDouble(1, montos[i]);
            }*/
            
            ps.setDouble(1, montos[i]);
            //ps.setDouble(1, montos[1]);
           // ps.setDouble(1, montos[2]);
            //ps.setDouble(1, montos[3]);
            
            
            renglones_afectados = ps.executeUpdate();
            //System.out.println("Carga exitosa...");
            //System.out.println("Renglones afectados: " + renglones_afectados);
            ps.close();
            //conn.close();
            
            } catch (SQLException e) {
            e.printStackTrace();
        }
            
        }
        
        conn.close();
        System.out.println("Carga exitosa...");
        //System.out.println("Renglones afectados: " + renglones_afectados);
        
        //String query = "INSERT INTO factura (monto) VALUES(?)";
        
        //System.out.println(montos[0]);
        //System.out.println(montos[1]);
        //System.out.println(montos[2]);
        //System.out.println(montos[3]);
        
        
        /* catch (SQLException e) {
            e.printStackTrace();
        }*/
        
    }
    
}
