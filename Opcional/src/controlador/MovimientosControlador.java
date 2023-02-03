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
import modelo.MovimientosModelo;
import modelo.ProductoModelo;

/**
 * @authors:
 * Juan Cifuentes
 * Alejandra Carvajal
 */

public class MovimientosControlador {
    private static Map <Integer, MovimientosModelo> listaMovimientos;
    
    public MovimientosControlador(){
        listaMovimientos = new HashMap<>();
    }
    
    public void agregar(ProductoModelo producto, String fecha, String movimiento, Integer cantidad, double valorUnidad, double valorTotal) {
        MovimientosModelo movimientos = new MovimientosModelo(producto, fecha, movimiento, cantidad, valorUnidad, valorTotal);
        listaMovimientos.put((movimientos.getProducto()).getId(), movimientos);
    }
    
    public String listar(Integer id) {
        String lista = "---------- Movimientos del producto " + ((listaMovimientos.get(id)).getProducto()).getNombre() + " ----------\n";
        for(int idAux : listaMovimientos.keySet()){
            if (idAux == id) {
                lista += listaMovimientos.get(idAux) + "\n";
            }
        }
        return lista;
    }
    
    public void generarCSV() {
        String archivoPersistencia = "";
        for(int codigo : listaMovimientos.keySet()){
            archivoPersistencia += listaMovimientos.get(codigo) + "\n";
        }
        try {
            FileOutputStream os = new FileOutputStream(new File("src\\main\\java\\persistencia\\movimientosPersistencia.txt"));
            os.write(archivoPersistencia.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restaurarDatos() {
        File archivo = new File("src\\main\\java\\persistencia\\movimientosPersistencia.txt");
        StringTokenizer stringTokenizer;
  
        String cadena1;
        String cadena2;
        
        Integer id;
        String nombre;
        String fecha;
        String movimiento;
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
                    cadena1 = cadena2.replace(" Fecha: ", "");
                    cadena2 = cadena1.replace(" Movimiento: ", "");
                    cadena1 = cadena2.replace(" Cantidad: ", "");
                    cadena2 = cadena1.replace(" Valor unidad: ", "");
                    cadena1 = cadena2.replace(" Valor total: ", "");
                    cadena2 = cadena1.replace("}", "");
                    
                    stringTokenizer = new StringTokenizer(cadena2,",");
                    
                    if (stringTokenizer.countTokens() == 7) {
                        id = Integer.valueOf(stringTokenizer.nextToken());
                        nombre = stringTokenizer.nextToken();
                        ProductoModelo producto = new ProductoModelo(id, nombre);
                        
                        fecha = stringTokenizer.nextToken();
                        movimiento = stringTokenizer.nextToken();
                        cantidad = Integer.valueOf(stringTokenizer.nextToken());
                        valorUnidad = Double.valueOf(stringTokenizer.nextToken());
                        valorTotal = Double.valueOf(stringTokenizer.nextToken());
                        
                        MovimientosModelo movimientos = new MovimientosModelo(producto, fecha, movimiento, cantidad, valorUnidad, valorTotal);
                        listaMovimientos.put(id, movimientos);
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
}
