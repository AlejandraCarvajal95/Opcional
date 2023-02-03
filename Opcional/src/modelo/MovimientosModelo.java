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

public class MovimientosModelo {
    
    private ProductoModelo producto;
    private String fecha;
    private String movimiento;
    private Integer cantidad;
    private double valorUnidad;
    private double valorTotal;

    public MovimientosModelo(ProductoModelo producto, String fecha, String movimiento, Integer cantidad, double valorUnidad, double valorTotal) {
        this.producto = producto;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
        this.valorUnidad = valorUnidad;
        this.valorTotal = valorTotal;
    }
    
    public ProductoModelo getProducto() {
        return producto;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getMovimiento() {
        return movimiento;
    }
    
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
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
        return "{" + "Id: " + this.getProducto().getId() + ", Nombre: " + this.getProducto().getNombre() + ", Fecha: " + this.getFecha() + ", Movimiento: " + this.getMovimiento() + ", Cantidad: " + this.cantidad + ", Valor unidad: " + this.getValorUnidad() + ", Valor total: " + this.getValorTotal() + "}";
    }
}
