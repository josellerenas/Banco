package com.company;

import javax.swing.*;

public class Cuentas {
    // Declaración de las propiedades de la clase
    private int totalDeRegistrosDisponibles = 20;
    private String[] nombreDelCliente = new String[totalDeRegistrosDisponibles];
    private int[] numeroDeCuenta = new int[totalDeRegistrosDisponibles];
    private double[] saldo = new double[totalDeRegistrosDisponibles];

    public void inicializar() {
        for (int i = 0; i < this.totalDeRegistrosDisponibles; i++) {
            this.nombreDelCliente[i] = "";
            this.numeroDeCuenta[i] = 0;
            this.saldo[i] = 0;
        }
    }



    // Constructor de la clase
    public Cuentas(){
    }

    // Getters and Setters de la clase

    public int getNumeroDeCuenta(int indiceDeCuenta) {
        return numeroDeCuenta[indiceDeCuenta];
    }

    public void setNumeroDeCuenta(int numeroDeCuenta, int indiceDeCuenta) {
        this.numeroDeCuenta[indiceDeCuenta] = numeroDeCuenta;
    }

    public String getNombreDelCliente(int indiceDeCuenta) {
        return nombreDelCliente[indiceDeCuenta];
    }

    public void setNombreDelCliente(String nombreDelCliente, int indiceDeCuenta) {
        this.nombreDelCliente[indiceDeCuenta] = nombreDelCliente;
    }

    public double getSaldo(int indiceDeCuenta) {
        return saldo[indiceDeCuenta];
    }

    public void setSaldo(double saldo, int indiceDeCuenta) {
        this.saldo[indiceDeCuenta] = saldo;
    }

    // Métodos agregados
    public int getLongitudDelArray() {
        return this.nombreDelCliente.length;
    }

    public int getIndiceDeCuenta(String nombreDelCliente) {
        for (int i = 0; i < this.nombreDelCliente.length; i++) {
            if (this.nombreDelCliente[i].equals(nombreDelCliente)) {
                return i;
            }
        }
        return -1;
    }

    public int getCantidadDeRegistros() {
        int cantidadDeRegistros = 0;
        for (int i = 0; i < this.nombreDelCliente.length; i++) {
            if (this.nombreDelCliente[i].equals("")) {

            } else {
                cantidadDeRegistros++;
            }
        }
        return cantidadDeRegistros;
    }

    /*
    [0] Pepe
    [1] Juan
    [2]
    [3]
    ....
    [19]


     */


    public void mostrarTodasLasCuentas() {
        for (int i = 0; i < this.nombreDelCliente.length; i++) {
            if (this.nombreDelCliente[i].equals("") == true) {

            } else {
                System.out.println(this.nombreDelCliente[i]);
            }
        }
        System.out.println();
    }

}
