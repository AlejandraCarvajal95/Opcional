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
import modelo.ValoracionDelInventarioModelo;
import modelo.ProductoModelo;

/**
 * @authors:
 * Juan Cifuentes
 * Alejandra Carvajal
 */

public class ValoracionDeInventarioControlador {
    private static Map <Integer, ValoracionDelInventarioModelo> listaValoracionDelInventario;
    
    public ValoracionDeInventarioControlador(){
        listaValoracionDelInventario = new HashMap<>();
    }
    
    public void agregar(ProductoModelo producto, Integer cantidad, double valorUnidad, double valorTotal) {
        ValoracionDelInventarioModelo valoracionDelInventario = new ValoracionDelInventarioModelo(producto, cantidad, valorUnidad, valorTotal);
        listaValoracionDelInventario.put((valoracionDelInventario.getProducto()).getId(), valoracionDelInventario);
    }

    public String verValoracionDelInventario(Integer id) {
        String lista = "---------- Valoracion del inventario del producto " + ((listaValoracionDelInventario.get(id)).getProducto()).getNombre() + " ----------\n";
        for(int idAux : listaValoracionDelInventario.keySet()){
            if (idAux == id) {
                lista += listaValoracionDelInventario.get(idAux) + "\n";
            }
        }
        return lista;
    }
    
    public static void generarCSV() {
        String archivoPersistencia = "";
        for(int codigo : listaValoracionDelInventario.keySet()){
            archivoPersistencia += listaValoracionDelInventario.get(codigo) + "\n";
        }
        try {
            FileOutputStream os = new FileOutputStream(new File("src\\main\\java\\persistencia\\valoracionDeInventarioPersistencia.txt"));
            os.write(archivoPersistencia.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValoracionDeInventarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValoracionDeInventarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restaurarDatos() {
        File archivo = new File("src\\main\\java\\persistencia\\valoracionDeInventarioPersistencia.txt");
        StringTokenizer stringTokenizer;
  
        String cadena1;
        String cadena2;
        
        Integer id;
        String nombre;
        
        Integer cantidad;
        double valorUnidad;
        double valorTotal;
        
        try {
            FileReader fileReader = new FileReader(archivo);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while ((cadena1 = bufferedReader.readLine()) != null){
                    cadena2 = cadena1.replace("{", "");
                    cadena1 = cadena2.replace("Id: ", "");
                    cadena2 = cadena1.replace(" Nombre: ", "");
                    cadena1 = cadena2.replace(" Cantidad: ", "");
                    cadena2 = cadena1.replace(" Valor unidad: ", "");
                    cadena1 = cadena2.replace(" Valor total: ", "");
                    cadena2 = cadena1.replace("}", "");
                    
                    stringTokenizer = new StringTokenizer(cadena2,",");
                    
                    if (stringTokenizer.countTokens() == 5) {
                        id = Integer.valueOf(stringTokenizer.nextToken());
                        nombre = stringTokenizer.nextToken();
                        ProductoModelo producto = new ProductoModelo(id, nombre);
                        
                        cantidad = Integer.valueOf(stringTokenizer.nextToken());
                        valorUnidad = Double.valueOf(stringTokenizer.nextToken());
                        valorTotal = Double.valueOf(stringTokenizer.nextToken());
                        
                        ValoracionDelInventarioModelo valoracionDelInventario = new ValoracionDelInventarioModelo(producto, cantidad, valorUnidad, valorTotal);
                        listaValoracionDelInventario.put(id, valoracionDelInventario);
                    } 
                }
            }
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarLaValoracionDelInventarioDespuesDeEntrada(Integer id){
        (listaValoracionDelInventario.get(id)).setCantidad(0);
        (listaValoracionDelInventario.get(id)).setValorUnidad(0);
        (listaValoracionDelInventario.get(id)).setValorTotal(0);
    }
    
    public void actualizarLaValoracionDelInventarioDespuesDeSalida(Integer id){
        (listaValoracionDelInventario.get(id)).setCantidad(0);
        (listaValoracionDelInventario.get(id)).setValorUnidad(0);
        (listaValoracionDelInventario.get(id)).setValorTotal(0);
    }

    public double getValorUnitario(Integer id) {
        return (listaValoracionDelInventario.get(id)).getValorUnidad();
    }
}
