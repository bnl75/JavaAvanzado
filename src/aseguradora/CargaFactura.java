/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aseguradora;

import aseguradora.Poblar;
import java.io.File;
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
    private double costoTotal;

    //Método Get
    public double getCostoTotal() {
        return costoTotal;
    }

    //Método Set
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    public static void main(String[] args) {
        
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
                    //Poblar.poblarFactura(Double.parseDouble(element.getElementsByTagName("costo_total").item(0).getTextContent()));
                    
                    //Element element = (Element) nodo;
                    System.out.println("Costo total: " + element.getElementsByTagName("costo_total").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
