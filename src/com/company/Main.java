package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Scanner leer = new Scanner(System.in);
        String nombreCliente = "";
        int totalDeRegistrosDisponibles = 20;
        int indiceDeCuenta = 0;
        int numeroCuenta = 0;
        int porcentajeIM;
        int comisionUsoChequera;
        int comisionEmisiónSaldoInsuficiente;
        double cantidadADepositar = 0;
        double cantidadARetirar = 0;
        double multa = 0;
        int tipoDeCuenta = 0;
        int[][] fechaDeVencimiento = new int[totalDeRegistrosDisponibles][3];
        int[] fechaActual = new int[3];
        int dia;

        CuentaDeAhorro miCuentaDeAhorros = new CuentaDeAhorro();
        CuentaDeCheques miCuentaDeCheques = new CuentaDeCheques();

        double saldo, saldoFinal;
        int Opc = 0;
        int Exit = 0;
        boolean sesionIniciada = false, fechaCorrecta = false, porcentajeCorrecto = false;
        boolean comisionUsoChequeraCorrecto = false;
        boolean comisionEmisionSaldoInsuficienteCorrecto = false;

        miCuentaDeAhorros.inicializar();
        miCuentaDeCheques.inicializar();

        do {
            if (sesionIniciada == false) {
                System.out.println("-----#BANCO NACIONAL DE MEXICO#-----" //MENU
                        + "\n1.-Iniciar Sesión"
                        + "\n2.-Registrarse"
                        + "\n3.-Salir del programa"
                );
                Opc = leer.nextInt();
                switch (Opc) {
                    case 1: // Iniciar Sesión
                        if (hayCuentasDeAhorroRegistradas(miCuentaDeAhorros) || hayCuentasDeChequesRegistradas(miCuentaDeCheques)) {
                            System.out.println("De qué tipo es su cuenta? 1)Ahorros. 2)Cheques.");
                            tipoDeCuenta = leer.nextInt();
                        } else {
                            System.out.println("No hay ninguna cuenta registrada");
                            break;
                        }


                        if (tipoDeCuenta == 1) { // Si es una cuenta de Ahorros
                            if (hayCuentasDeAhorroRegistradas(miCuentaDeAhorros)) {

                                System.out.println("Lista de cuentas registradas:");
                                miCuentaDeAhorros.mostrarTodasLasCuentas();

                                System.out.println("Favor de ingresar su nombre:");
                                nombreCliente = leer.next();

                                System.out.println("Favor de ingresar su número de cuenta:");
                                numeroCuenta = leer.nextInt();

                                if (iniciarSesionAhorros(miCuentaDeAhorros, nombreCliente, numeroCuenta)) {
                                    System.out.println("Bienvenido " + nombreCliente+ "!");
                                    indiceDeCuenta = miCuentaDeAhorros.getIndiceDeCuenta(nombreCliente);
                                    sesionIniciada = true;
                                } else {
                                    System.out.println("Datos incorrectos, favor de intentarlo de nuevo");
                                }
                            } else {
                                System.out.println("No hay cuentas registradas");
                            }
                        } else if (tipoDeCuenta == 2) { // Cheques
                            if (hayCuentasDeChequesRegistradas(miCuentaDeCheques)) {

                                System.out.println("Lista de cuentas registradas:");
                                miCuentaDeCheques.mostrarTodasLasCuentas();

                                System.out.println("Favor de ingresar su nombre:");
                                nombreCliente = leer.next();

                                System.out.println("Favor de ingresar su número de cuenta:");
                                numeroCuenta = leer.nextInt();

                                if (iniciarSesionCheques(miCuentaDeCheques, nombreCliente, numeroCuenta)) {
                                    System.out.println("Bienvenido " + nombreCliente+ "!");
                                    indiceDeCuenta = miCuentaDeCheques.getIndiceDeCuenta(nombreCliente);
                                    sesionIniciada = true;
                                } else {
                                    System.out.println("Datos incorrectos, favor de intentarlo de nuevo");
                                }
                            } else {
                                System.out.println("No hay cuentas registradas");
                            }
                        }
                        break;
                    case 2: // Registrarse
                        System.out.println("Favor de ingresar su nombre:");
                        nombreCliente = leer.next();

                        System.out.println("Favor de ingresar su número de cuenta:");
                        numeroCuenta = leer.nextInt();

                        System.out.println("Favor de ingresar su saldo inicial:");
                        saldo = leer.nextDouble();

                        do {
                            System.out.println("Favor de indicar de qué tipo es su cuenta: 1)Ahorros. 2)Cheques.");
                            tipoDeCuenta = leer.nextInt();
                            if (tipoDeCuenta < 1 || tipoDeCuenta > 2) {
                                System.out.println("Solo es válido ingresar una de las opciones.");
                            }
                        } while (tipoDeCuenta < 1 || tipoDeCuenta > 2);

                        // Valorar si la cuenta es de ahorros o cheques, y solicitar los datos correspondientes
                        if (tipoDeCuenta == 1) { // Tipo de cuenta: Ahorros

                            indiceDeCuenta = miCuentaDeAhorros.getCantidadDeRegistros();

                            // Solicitar la fecha de vencimiento
                            do {
                                System.out.println("Favor de indicar la fecha de vencimiento:");
                                System.out.println("Día:");
                                fechaDeVencimiento[indiceDeCuenta][0] = leer.nextInt();
                                System.out.println("Mes:");
                                fechaDeVencimiento[indiceDeCuenta][1] = leer.nextInt();
                                System.out.println("Año:");
                                fechaDeVencimiento[indiceDeCuenta][2] = leer.nextInt();
                                fechaCorrecta = verificarFecha(fechaDeVencimiento, indiceDeCuenta);
                            } while (fechaCorrecta == false);

                            // Solicitar el porcentaje de interés mensual
                            do {
                                System.out.println("Favor de indicar el porcentaje de interés mensual:");
                                porcentajeIM = leer.nextInt();
                                porcentajeCorrecto = verificarPorcentaje(porcentajeIM);
                            } while (porcentajeCorrecto == false);


                            agregarCuentaDeAhorros(miCuentaDeAhorros, nombreCliente, numeroCuenta, saldo,
                                    fechaDeVencimiento, porcentajeIM, indiceDeCuenta);

                            sesionIniciada = true;

                        } else if (tipoDeCuenta == 2) {

                            // Solicita la comisión por uso de chequera
                            do {
                                System.out.println("Favor de indicar la comisión por uso de chequera (1%-99%):");
                                comisionUsoChequera = leer.nextInt();
                                comisionUsoChequeraCorrecto = verificarPorcentaje(comisionUsoChequera);
                            } while (comisionUsoChequeraCorrecto == false);

                            // Solicita la comisión por emisión de cheques con saldo insuficiente
                            do {
                                System.out.println("Favor de indicar la comisión por emisión de cheques con saldo insuficiente (1%-99%):");
                                comisionEmisiónSaldoInsuficiente = leer.nextInt();
                                comisionEmisionSaldoInsuficienteCorrecto = verificarPorcentaje(comisionEmisiónSaldoInsuficiente);
                            } while (comisionEmisionSaldoInsuficienteCorrecto == false);

                            // Agregando datos al objeto/clase
                            indiceDeCuenta = miCuentaDeCheques.getCantidadDeRegistros();

                            agregarCuentaDeCheques(miCuentaDeCheques, nombreCliente, numeroCuenta, saldo,
                                    comisionUsoChequera, comisionEmisiónSaldoInsuficiente, indiceDeCuenta);

                            sesionIniciada = true;
                        }
                        break;
                    case 3: // Salir
                        Opc = 5;
                        break;
                    default: // Opción no válida
                        System.out.println("Favor de elegir una opción válida");
                        break;
                }
            }

            if (sesionIniciada == true && indiceDeCuenta >= 0) {
                menuPrincipal();
                Opc = leer.nextInt();
                switch (Opc) {
                    case 1: // Consulta de datos: nombre, cuenta, saldo, tipo de cuenta...
                        if (tipoDeCuenta == 1) { // Cuenta de ahorros
                            miCuentaDeAhorros.consultarDatosCuentaAhorros(miCuentaDeAhorros, indiceDeCuenta);
                        } else if (tipoDeCuenta == 2) { // Cuenta de cheques
                            consultarDatosCuentaCheques(miCuentaDeCheques, indiceDeCuenta);
                        }
                        break;
                    case 2:
                        if (tipoDeCuenta == 1) { // Cuenta de ahorros
                            System.out.println("Ingrese el día del mes en el que estamos el día de hoy (1-31)");
                            dia = leer.nextInt();
                            if (dia == 1) {
                                System.out.println("Su saldo actual es: $" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));
                                System.out.println("Indique la cantidad de intereses a depositar:");
                                cantidadADepositar = leer.nextDouble();
                                saldoFinal = miCuentaDeAhorros.getSaldo(indiceDeCuenta) + cantidadADepositar;
                                miCuentaDeAhorros.setSaldo(saldoFinal, indiceDeCuenta);
                                System.out.println("Su nuevo saldo es: $" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));
                            } else {
                                System.out.println("Solo puede depositar intereses el primer dia del mes");
                            }
                        } else if (tipoDeCuenta == 2) { // Cuenta de cheques
                            //TODO indice fuera de 20
                            System.out.println("Su saldo actual es: $" + miCuentaDeCheques.getSaldo(indiceDeCuenta));
                            System.out.println("Indique la cantidad a depositar:");
                            cantidadADepositar = leer.nextDouble();
                            saldoFinal = miCuentaDeCheques.getSaldo(indiceDeCuenta) + cantidadADepositar;
                            miCuentaDeCheques.setSaldo(saldoFinal, indiceDeCuenta);
                            System.out.println("Su nuevo saldo es: $" + miCuentaDeCheques.getSaldo(indiceDeCuenta));
                        }
                        break;
                    case 3:
                        if (tipoDeCuenta == 1) { // Cuenta de ahorros
                            System.out.println("Favor de indicar la fecha de hoy:");
                            System.out.println("Día:");
                            fechaActual[0] = leer.nextInt();
                            System.out.println("Mes:");
                            fechaActual[1] = leer.nextInt();
                            System.out.println("Año:");
                            fechaActual[2] = leer.nextInt();
                            fechaDeVencimiento = miCuentaDeAhorros.getFechaDeVencimiento();
                            // Verificar si la fecha actual es igual a la fecha de vencimiento
                            if (fechaDeVencimiento[indiceDeCuenta][0] == fechaActual[0] &&
                                    fechaDeVencimiento[indiceDeCuenta][1] == fechaActual[1] &&
                                    fechaDeVencimiento[indiceDeCuenta][2] == fechaActual[2]) {
                                System.out.println("Su saldo actual es: $" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));
                                System.out.println("Indique la cantidad que desea retirar:");
                                cantidadARetirar = leer.nextDouble();
                                if (cantidadARetirar <= miCuentaDeAhorros.getSaldo(indiceDeCuenta)) {
                                    saldoFinal = miCuentaDeAhorros.getSaldo(indiceDeCuenta) - cantidadARetirar;
                                    miCuentaDeAhorros.setSaldo(saldoFinal, indiceDeCuenta);
                                    System.out.println("Su nuevo saldo es: $" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));
                                } else {
                                    System.out.println("No puede retirar una cantidad mayor a la que existe en su saldo");
                                }
                            } else {
                                System.out.println("Solamente puedes retirar en la fecha de vencimiento");
                            }
                        } else if (tipoDeCuenta == 2) { // Cuenta de cheques
                            System.out.println("Su saldo actual es: $" + miCuentaDeCheques.getSaldo(indiceDeCuenta));
                            System.out.println("Indique la cantidad a retirar:");
                            cantidadARetirar = leer.nextDouble();
                            if (cantidadARetirar <= miCuentaDeCheques.getSaldo(indiceDeCuenta)) {
                                saldoFinal = miCuentaDeCheques.getSaldo(indiceDeCuenta) - cantidadARetirar;
                                miCuentaDeCheques.setSaldo(saldoFinal, indiceDeCuenta);
                                System.out.println("Su nuevo saldo es: $" + miCuentaDeCheques.getSaldo(indiceDeCuenta));
                            } else {
                                System.out.println("Saldo insuficiente. Se le realizó un cobro por emisión de cheques " +
                                        "con saldo insuficiente");
                                System.out.println("Comisión por emisión de cheques con saldo insuficiente: " +
                                        miCuentaDeCheques.getComisionSaldoInsuficiente(indiceDeCuenta) + "%");
                                multa = miCuentaDeCheques.getSaldo(indiceDeCuenta) * (miCuentaDeCheques.getComisionSaldoInsuficiente(indiceDeCuenta) * 0.01);
                                System.out.println("Multa: $" + multa);
                                saldoFinal = miCuentaDeCheques.getSaldo(indiceDeCuenta) - multa;
                                miCuentaDeCheques.setSaldo(saldoFinal, indiceDeCuenta);
                                System.out.println("Su nuevo saldo es: $" + miCuentaDeCheques.getSaldo(indiceDeCuenta));

                                // Cálculo de cuánto se va a descontar
                                //saldo actual: $100
                                //comisión por emisión de cheques con saldo insuficiente: 20%
                                //-$20 (multa)
                                //$80 (nuevo total)

                                //Fórmula:
                                //$100 * .2 = $20
                                //saldo * (comision * 0.01) = $20
                                //saldo - $20
                            }
                        }
                        break;
                    case 4:
                        sesionIniciada = false;
                        break;
                    case 5:
                        System.out.println("¿Esta seguro que desea salir del programa? 1.-SI     2.-NO");
                        Exit= leer.nextInt();
                        if(Exit == 1){
                            Opc = 5;
                            System.out.println("Salida de la aplicación exitosa.");
                        } else {
                            Opc = 0;
                        }
                        break;
                }
            }
        } while (Opc != 5);
    }

    public static void menuPrincipal() {
        System.out.println("-----#BANCO NACIONAL DE MEXICO#-----" //MENU
                + "\n1.-Consultar mis datos"
                + "\n2.-Hacer depósito"
                + "\n3.-Retiro de dinero"
                + "\n4.-Cerrar Sesión"
                + "\n5.-Salir del programa"
        );
    }

//    public static int getIndiceDeCuenta() {
//
//    }


    public static boolean hayCuentasDeAhorroRegistradas(CuentaDeAhorro miCuentaDeAhorros) {
        if (miCuentaDeAhorros.getCantidadDeRegistros() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hayCuentasDeChequesRegistradas(CuentaDeCheques miCuentaDeCheques) {
        if (miCuentaDeCheques.getCantidadDeRegistros() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean iniciarSesionAhorros(CuentaDeAhorro miCuentaDeAhorros, String nombreCliente,
                                               int numeroCuenta) {
        for (int i = 0; i < miCuentaDeAhorros.getLongitudDelArray(); i++) {
            if (miCuentaDeAhorros.getNombreDelCliente(i).equals(nombreCliente)) {
                if (miCuentaDeAhorros.getNumeroDeCuenta(i) == numeroCuenta) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean iniciarSesionCheques(CuentaDeCheques miCuentaDeCheques, String nombreCliente,
                                               int numeroCuenta) {
        for (int i = 0; i < miCuentaDeCheques.getLongitudDelArray(); i++) {
            if (miCuentaDeCheques.getNombreDelCliente(i).equals(nombreCliente)) {
                if (miCuentaDeCheques.getNumeroDeCuenta(i) == numeroCuenta) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean verificarPorcentaje(int porcentaje) {
        if (porcentaje > 0 && porcentaje < 100) {
            return true;
        } else {
            System.out.println("Dato inválido. Favor de ingresar un porcentaje entre 1% y 99%");
            return false;
        }
    }

//    public static void consultarDatosCuentaAhorros(CuentaDeAhorro miCuentaDeAhorros, int indiceDeCuenta) {
//        System.out.println("Nombre de la cuenta:");
//        System.out.println(miCuentaDeAhorros.getNombreDelCliente(indiceDeCuenta));
//        System.out.println("Número de la cuenta:");
//        System.out.println(miCuentaDeAhorros.getNumeroDeCuenta(indiceDeCuenta));
//        System.out.println("Saldo de la cuenta:");
//        System.out.println("$" + miCuentaDeAhorros.getSaldo(indiceDeCuenta));
//
//        // Imprimir fecha de vencimiento
//        System.out.println("Fecha de vencimiento:");
//        int[][] fechaDeVencimiento = miCuentaDeAhorros.getFechaDeVencimiento();
//        for (int i = 0; i < 3; i++) {
//            System.out.print(fechaDeVencimiento[indiceDeCuenta][i]);
//            if (i != 2) {
//                System.out.print("-");
//            }
//        }
//
//        // Imprimir porcentaje de interés mensual
//        System.out.println();
//        System.out.println("Porcentaje de interés mensual:");
//        System.out.println(miCuentaDeAhorros.getPorcentajeIM(indiceDeCuenta) + "%");
//    }

    public static void consultarDatosCuentaCheques(CuentaDeCheques miCuentaDeCheques, int indiceDeCuenta) {
        System.out.println("Nombre de la cuenta:");
        System.out.println(miCuentaDeCheques.getNombreDelCliente(indiceDeCuenta));
        System.out.println("Número de la cuenta:");
        System.out.println(miCuentaDeCheques.getNumeroDeCuenta(indiceDeCuenta));
        System.out.println("Saldo de la cuenta:");
        System.out.println("$" + miCuentaDeCheques.getSaldo(indiceDeCuenta));
        System.out.println("Comisión por uso de chequera:");
        System.out.println(miCuentaDeCheques.getComisionUsoChequera(indiceDeCuenta) + "%");
        System.out.println("Comisión por emisión de cheques con saldo insuficiente:");
        System.out.println(miCuentaDeCheques.getComisionSaldoInsuficiente(indiceDeCuenta) + "%");
    }


    public static void agregarCuentaDeAhorros(CuentaDeAhorro miCuentaDeAhorros, String nombreCliente, int numeroCuenta,
                                              double saldo, int[][] fechaDeVencimiento, int porcentajeIM,
                                              int indiceDeCuenta) {
        miCuentaDeAhorros.setNombreDelCliente(nombreCliente, indiceDeCuenta);
        miCuentaDeAhorros.setNumeroDeCuenta(numeroCuenta, indiceDeCuenta);
        miCuentaDeAhorros.setSaldo(saldo, indiceDeCuenta);
        miCuentaDeAhorros.setFechaDeVencimiento(fechaDeVencimiento, indiceDeCuenta);
        miCuentaDeAhorros.setPorcentajeIM(porcentajeIM, indiceDeCuenta);
    }

    public static void agregarCuentaDeCheques(CuentaDeCheques miCuentaDeCheques, String nombreCliente, int numeroCuenta,
                                              double saldo, int comisionUsoChequera, int comisionEmisionSaldoInsuficiente,
                                              int indiceDeCuenta) {
        miCuentaDeCheques.setNombreDelCliente(nombreCliente, indiceDeCuenta);
        miCuentaDeCheques.setNumeroDeCuenta(numeroCuenta, indiceDeCuenta);
        miCuentaDeCheques.setSaldo(saldo, indiceDeCuenta);
        miCuentaDeCheques.setComisionUsoChequera(comisionUsoChequera, indiceDeCuenta);
        miCuentaDeCheques.setComisionSaldoInsuficiente(comisionEmisionSaldoInsuficiente, indiceDeCuenta);
    }


    public static boolean verificarFecha(int[][] verificarFecha, int indiceDeCuenta) {
        switch (verificarFecha[indiceDeCuenta][1]) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (verificarFecha[indiceDeCuenta][0] < 1 || verificarFecha[indiceDeCuenta][0] > 31) {
                    System.out.println("Fecha incorrecta. Favor de introducir una fecha correcta");
                    return false; //dia incorrecto
                } else {
                    return true; //fecha correcta
                }
            case 4:
            case 6:
            case 9:
            case 11:
                if (verificarFecha[indiceDeCuenta][0] < 1 || verificarFecha[indiceDeCuenta][0] > 30) {
                    System.out.println("Fecha incorrecta. Favor de introducir una fecha correcta");
                    return false; //dia incorrecto
                } else {
                    return true; //fecha correcta
                }
            case 2:
                if (verificarFecha[indiceDeCuenta][2] % 4 == 0) { //año bisiesto
                    if (verificarFecha[indiceDeCuenta][0] < 1 || verificarFecha[indiceDeCuenta][0] > 29) {
                        System.out.println("Fecha incorrecta. Favor de introducir una fecha correcta");
                        return false; //dia incorrecto
                    } else {
                        return true; //fecha correcta
                    }
                } else {
                    if (verificarFecha[indiceDeCuenta][0] < 1 || verificarFecha[indiceDeCuenta][0] > 28) {
                        System.out.println("Fecha incorrecta. Favor de introducir una fecha correcta");
                        return false; //dia incorrecto
                    } else {
                        return true; //fecha correcta
                    }
                }
            default:
                System.out.println("Fecha incorrecta. Favor de introducir una fecha correcta");
                return false; //fecha incorrecta
        }
    }
}
            /*

            -> El programa solicita el registro del primer cliente
            Pide: nombre, cuenta, saldo, y tipo de cuenta (cheques, ahorros):
            Datos que el usuario pone: Pepe, 123, $100, ahorros

            Si la cuenta del usuario es de ahorros:

            El programa te pedirá los siguientes datos:
            -Fecha de vencimiento.
            -Porcentaje de interés mensual

             -> El programa pone el siguiente menú
               1.-Consultar mis datos
               2.-Hacer deposito
               3.-Depósito a intereses
                        -> si el usuario elige esta opción, preguntarle qué día del mes es. Si es 1, procede, si no
                        mencionar que solamente el 1ro se puede hacer depósitos a interes.
               4.-Retiro de dinero
                    -> si el usuario elige esta opción, preguntarle qué día del mes es. Si igual a la fecha de vencimiento,
                     procede, si no, mencionar que solamente se puede retirar el día de la fecha de vencimiento.
               5.-Cerrar Sesión


            Si la cuenta del usuario es de cheques:

            Submenú pidiento lo siguente:
            -Comisión por uso de chequera (% de tu saldo)
            -Comisión por emisión de cheques con saldo insuficiente (% de tu saldo)

             -> El programa pone el siguiente menú
               1.-Consultar mis datos
               2.-Hacer deposito
               3.-Retiro de dinero
                    -> El programa solicita la cantidad de dinero a retirar. Si la cantidad de dinero es mayor al
                    saldo, mencionar que no se pudo realizar el cheque, y cobrarle el porcentaje de comisión por
                    emisión de cheques con saldo insuficiente.
               4.-Cerrar Sesión



            • Comisión por uso de chequera.
            • Comisión por emisión de cheques con saldo insuficiente, la cual se descuenta directamente del saldo.

            Saldo: $5
            Solicito cheque de $20
            El banco me va a cobrar 10% por intentar hacer un cheque con saldo insuficiente.
            Saldo: $4.5


            Las cuentas de ahorros presentan las siguientes características:

            • Fecha de vencimiento.

            • Porcentaje de interés mensual.

            • Método para depositar los intereses el primer día de cada mes.

            • Solamente se puede retirar dinero el día de la fecha de vencimiento.

            */
