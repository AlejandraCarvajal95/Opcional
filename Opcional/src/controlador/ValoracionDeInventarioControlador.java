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

}
