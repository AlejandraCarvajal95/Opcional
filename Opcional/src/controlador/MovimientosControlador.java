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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MovimientosModelo;
import modelo.ProductoModelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @authors:
 * Juan Cifuentes
 * Alejandra Carvajal
 */

public class MovimientosControlador {
//    private static Map <Integer, MovimientosModelo> listaMovimientos;
    private List<String> listaMovimientos;
    private String fecha;
    
    public MovimientosControlador(){
//        listaMovimientos = new HashMap<>();
        listaMovimientos = new ArrayList<>();
    }
    
    public String listar(Integer id, ProductoControlador productoControlador) {
        String strAux = "{Id: " + String.valueOf(id);
        String lista = "---------- Movimientos del producto " + ((productoControlador.getProducto(id))).getNombre() + " ----------\n";
        for(int intAux = 0; intAux < listaMovimientos.size(); intAux++){
            if ((listaMovimientos.get(intAux)).contains(strAux)) {
                lista += listaMovimientos.get(intAux) + "\n";
            }
        }
        return lista;
    }
    
    public void generarCSV() {
        String archivoPersistencia = "";
        for(int intAux = 0; intAux < listaMovimientos.size(); intAux++){
            archivoPersistencia += listaMovimientos.get(intAux) + "\n";
        }
        try {
            FileOutputStream os = new FileOutputStream(new File("src\\Persistencia\\movimientosPersistencia.txt"));
            os.write(archivoPersistencia.getBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restaurarDatos() {
        File archivo = new File("src\\Persistencia\\movimientosPersistencia.txt");
  
        String cadena;
        
        try {
            FileReader fileReader = new FileReader(archivo);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while ((cadena = bufferedReader.readLine()) != null){
                    listaMovimientos.add(cadena);
                }
            }
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovimientosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarRegistro(ProductoModelo producto, String fecha, Integer cantidad, double valorUnidad) {
        this.fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        MovimientosModelo movimientos = new MovimientosModelo(producto, String.valueOf(this.fecha), "Registro", cantidad, valorUnidad, (valorUnidad*cantidad));
        listaMovimientos.add(movimientos.toString());
    }
    
    public void registrarEntrada(ProductoModelo producto, String fecha, Integer cantidad, double valorUnidad) {
        this.fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        MovimientosModelo movimientos = new MovimientosModelo(producto, String.valueOf(this.fecha), "Entrada", cantidad, valorUnidad, (valorUnidad*cantidad));
        listaMovimientos.add(movimientos.toString());
    }
    
    public void registrarSalida(ProductoModelo producto, String fecha, Integer cantidad, ValoracionDeInventarioControlador valoracionDeInventario) {
        this.fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        MovimientosModelo movimientos = new MovimientosModelo(producto, String.valueOf(this.fecha), "Salida", cantidad, valoracionDeInventario.getValorUnitario(producto.getId()), ((valoracionDeInventario.getValorUnitario(producto.getId()))*cantidad));
        listaMovimientos.add(movimientos.toString());
    }
}
