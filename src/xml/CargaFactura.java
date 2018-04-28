/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import bd.Conexion;
import bd.Poblar;
import java.io.File;
import java.sql.Connection;
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
public class CargaFactura {
    public static double costoTotal;

    
    public static void main(String[] args) {
        Connection conn;
        
        
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost:3306/prueba", "diegobnl", "123");
        
         try {
            File archivo = new File("C:\\Users\\USER\\Desktop\\Facturas.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);     //Todo esto (renglones anteriores tambien) es para hacer un casteo y saber de qué tipo de documento se trata
            //System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaEmpleados = document.getElementsByTagName("factura");    //Un arreglo de nodos (un nodo es una etiqueta del xml)
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Node nodo = listaEmpleados.item(i);     //Para obtener los nodos en una lista de nodos
                //System.out.println("Elemento: " + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {  //Si es un elemento de tipo nodo
                    
                    Element element = (Element) nodo;
                    costoTotal = Double.parseDouble(element.getElementsByTagName("costo_total").item(0).getTextContent());
                    Poblar.poblarFactura(conn, costoTotal);
                    
                    //Element element = (Element) nodo;
                    //System.out.println("Costo total: " + element.getElementsByTagName("costo_total").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
