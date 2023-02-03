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

    public String verValoracionDelInventario(Integer id) {
        if (listaValoracionDelInventario.containsKey(id)){
            String lista = "---------- Valoracion del inventario del producto " + ((listaValoracionDelInventario.get(id)).getProducto()).getNombre() + " ----------\n";
            for(int idAux : listaValoracionDelInventario.keySet()){
                if (idAux == id) {
                    lista += listaValoracionDelInventario.get(idAux) + "\n";
                }
            }
            return lista;
        } else {
            return "";
        }
    }
    
    public void generarCSV() {
        String archivoPersistencia = "";
        for(int codigo : listaValoracionDelInventario.keySet()){
            archivoPersistencia += listaValoracionDelInventario.get(codigo) + "\n";
        }
        try {
            FileOutputStream os = new FileOutputStream(new File("src\\Persistencia\\valoracionDelInventarioPersistencia.txt"));
            os.write(archivoPersistencia.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValoracionDeInventarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValoracionDeInventarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restaurarDatos() {
        File archivo = new File("src\\Persistencia\\valoracionDelInventarioPersistencia.txt");
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
    
    public void registrarPrimeraValoracion(ProductoModelo producto, Integer cantidad, double valorUnidad) {
        double valorTotal = valorUnidad*cantidad;
        ValoracionDelInventarioModelo valoracionDelInventario = new ValoracionDelInventarioModelo(producto, cantidad, valorUnidad, valorTotal);
        listaValoracionDelInventario.put((valoracionDelInventario.getProducto()).getId(), valoracionDelInventario);
    }
    
    public void actualizarLaValoracionDelInventarioDespuesDeEntrada(Integer id, double precioDeVentaUnitario, Integer cantidadDeEntradas){
        Integer cantidad = ((listaValoracionDelInventario.get(id)).getCantidad())+cantidadDeEntradas;
        double valorUnidad = (((listaValoracionDelInventario.get(id)).getValorTotal())+(precioDeVentaUnitario*cantidadDeEntradas))/(((listaValoracionDelInventario.get(id)).getCantidad())+cantidadDeEntradas);
        double valorTotal = cantidad*valorUnidad;
        (listaValoracionDelInventario.get(id)).setCantidad(cantidad);
        (listaValoracionDelInventario.get(id)).setValorUnidad(valorUnidad);
        (listaValoracionDelInventario.get(id)).setValorTotal(valorTotal);
    }
    
    public void actualizarLaValoracionDelInventarioDespuesDeSalida(Integer id, Integer cantidadDeSalidas){
        Integer cantidad = ((listaValoracionDelInventario.get(id)).getCantidad())-cantidadDeSalidas;
        (listaValoracionDelInventario.get(id)).setCantidad(cantidad);
//        (listaValoracionDelInventario.get(id)).setValorUnidad(0);
        double valorTotal= cantidad*((listaValoracionDelInventario.get(id)).getValorUnidad());
        (listaValoracionDelInventario.get(id)).setValorTotal(valorTotal);
    }
    
    public ValoracionDelInventarioModelo getValoracionDelInventarioModelo(Integer id) {
        return listaValoracionDelInventario.get(id);
    }

    public double getValorUnitario(Integer id) {
        return (listaValoracionDelInventario.get(id)).getValorUnidad();
    }
}
