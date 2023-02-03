/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ProductoModelo;

/**
 * @authors:
 * Juan Cifuentes
 * Alejandra Carvajal
 */

public class ProductoControlador {
    private static Map <Integer, ProductoModelo> listaProductos;
    
    public ProductoControlador(){
        listaProductos = new HashMap<>();
    }
    
    public void agregar(Integer id, String nombre) {
        ProductoModelo producto = new ProductoModelo(id, nombre);
        listaProductos.put(id, producto);
    }
    
    public static String listar() {
        String lista = "---------- Productos ----------\n";
        for(int id : listaProductos.keySet()){
            lista += listaProductos.get(id) + "\n";
        }
        return lista;
    }
    
    public static void generarCSV() {
        String archivoPersistencia = "";
        for(int codigo : listaProductos.keySet()){
            archivoPersistencia += listaProductos.get(codigo) + "\n";
        }
        try {
            FileOutputStream os = new FileOutputStream(new File("src\\main\\java\\persistencia\\productosPersistencia.txt"));
            os.write(archivoPersistencia.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restaurarDatos() {
        File archivo = new File("src\\main\\java\\persistencia\\productosPersistencia.txt");
        StringTokenizer stringTokenizer;
  
        String cadena1;
        String cadena2;
        
        Integer id;
        String nombre;
        
        try {
            FileReader fileReader = new FileReader(archivo);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while ((cadena1 = bufferedReader.readLine()) != null){
                    cadena2 = cadena1.replace("{Id: ", "");
                    cadena1 = cadena2.replace(" Nombre: ", "");
                    cadena2 = cadena1.replace("}", "");
                    
                    stringTokenizer = new StringTokenizer(cadena2,",");
                    
                    if (stringTokenizer.countTokens() == 2) {
                        id = Integer.valueOf(stringTokenizer.nextToken());
                        nombre = stringTokenizer.nextToken();
                        
                        ProductoModelo producto = new ProductoModelo(id, nombre);
                        listaProductos.put(id, producto);
                    } 
                }
            }
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
