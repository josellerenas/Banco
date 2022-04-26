package com.company;

public class CuentaDeCheques extends Cuentas {

    // Declaración de variables (propiedades)
    private int totalDeRegistrosDisponibles = 20;
    private int[] comisionUsoChequera = new int[totalDeRegistrosDisponibles];
    private int[] comisionSaldoInsuficiente = new int[totalDeRegistrosDisponibles];

    // Constructor de la clase
    public CuentaDeCheques() {

    }

    // Getters y Setters

    public int getComisionUsoChequera(int indiceDeCuenta) {
        return comisionUsoChequera[indiceDeCuenta];
    }

    public void setComisionUsoChequera(int comisionUsoChequera, int indiceDeCuenta) {
        this.comisionUsoChequera[indiceDeCuenta] = comisionUsoChequera;
    }

    public int getComisionSaldoInsuficiente(int indiceDeCuenta) {
        return comisionSaldoInsuficiente[indiceDeCuenta];
    }

    public void setComisionSaldoInsuficiente(int comisionSaldoInsuficiente, int indiceDeCuenta) {
        this.comisionSaldoInsuficiente[indiceDeCuenta] = comisionSaldoInsuficiente;
    }

    @Override
    public void inicializar() {
        super.inicializar();
        for (int i = 0; i < this.totalDeRegistrosDisponibles; i++) {
            this.comisionUsoChequera[i] = 0;
            this.comisionSaldoInsuficiente[i] = 0;
        }
    }
}
