package com.company;

public class CuentaDeAhorro extends Cuentas {
    private int totalDeRegistrosDisponibles = 20;
    private int[][] fechaDeVencimiento = new int[totalDeRegistrosDisponibles][3]; // Array de 3 celdas, día mes y año
    private int[] porcentajeIM = new int[totalDeRegistrosDisponibles];

    // Constructor de la clase
    public CuentaDeAhorro() {
    }

    // Getters y Setters de la clase

    public int[][] getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(int[][] fechaDeVencimiento, int indiceDeCuenta) {
        for (int i = 0; i < 3; i++) {
            this.fechaDeVencimiento[indiceDeCuenta][i] = fechaDeVencimiento[indiceDeCuenta][i];
        }

    }

    public int getPorcentajeIM(int indiceDeCuenta) {
        return porcentajeIM[indiceDeCuenta];
    }

    public void setPorcentajeIM(int porcentajeIM, int indiceDeCuenta) {
        this.porcentajeIM[indiceDeCuenta] = porcentajeIM;
    }

    public static void consultarDatosCuentaAhorros(CuentaDeAhorro miCuentaDeAhorros, int indiceDeCuenta) {
        System.out.println("Nombre de la cuenta:");
        System.out.println(miCuentaDeAhorros.getNombreDelCliente(indiceDeCuenta));
        System.out.println("Número de la cuenta:");
        System.out.println(miCuentaDeAhorros.getNumeroDeCuenta(indiceDeCuenta));
        System.out.println("Saldo de la cuenta:");
        System.out.println("$" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));

        // Imprimir fecha de vencimiento
        System.out.println("Fecha de vencimiento:");
        int[][] fechaDeVencimiento = miCuentaDeAhorros.getFechaDeVencimiento();
        for (int i = 0; i < 3; i++) {
            System.out.print(fechaDeVencimiento[indiceDeCuenta][i]);
            if (i != 2) {
                System.out.print("-");
            }
        }

        // Imprimir porcentaje de interés mensual
        System.out.println();
        System.out.println("Porcentaje de interés mensual:");
        System.out.println(miCuentaDeAhorros.getPorcentajeIM(indiceDeCuenta) + "%");
    }

    @Override
    public void inicializar() {
        super.inicializar();
        for (int i = 0; i < this.totalDeRegistrosDisponibles; i++) {
            porcentajeIM[i] = 0;
            for (int j = 0; j < 3; j++) {
                fechaDeVencimiento[i][j] = 0;
            }
        }
    }
}
