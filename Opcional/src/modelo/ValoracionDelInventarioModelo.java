/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * @authors:
 * Juan Cifuentes
 * Alejandra Carvajal
 */

public class ValoracionDelInventarioModelo {
    
    private ProductoModelo producto;
    private Integer cantidad;
    private double valorUnidad;
    private double valorTotal;
    
   public ValoracionDelInventarioModelo(ProductoModelo producto, Integer cantidad, double valorUnidad, double valorTotal) {
       this.producto = producto;
       this.cantidad = cantidad;
       this.valorUnidad = valorUnidad;
       this.valorTotal = valorTotal;
    }
    
    public ProductoModelo getProducto() {
        return producto;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getValorUnidad() {
        return valorUnidad;
    }
    
    public void setValorUnidad(double valorUnidad) {
        this.valorUnidad = valorUnidad;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    @Override
    public String toString() {
        return "{" + "Id: " + this.getProducto().getId() + ", Nombre: " + this.getProducto().getNombre() + ", Cantidad: " + this.cantidad + ", Valor unidad: " + this.getValorUnidad() + ", Valor total: " + this.getValorTotal() + "}";
    }
}

