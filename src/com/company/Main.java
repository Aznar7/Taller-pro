package com.company;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);


    static ArrayList<ArrayList<String>> reparacions = new ArrayList<>();
    static ArrayList<ArrayList<String>> vehiculos = new ArrayList<>();
    static ArrayList<String[]> clients = new ArrayList<>();
    static ArrayList<String[]> mecanics = new ArrayList<>();
    public static void main(String[] args) {

        Main main = new Main();
        main.init();
    }



    public void init(){
        int menuItem = 0;
        do {
            System.out.println("TALLER DE REPARACIÓ DE VEHICLES");
            System.out.println("[1] Donar d’alta nou client");
            System.out.println("[2] Donar d’alta nou mecànic");
            System.out.println("[3] Introduir nou vehicle");
            System.out.println("[4] Crear fitxa de nova reparació");
            System.out.println("[5] Sortir");
            System.out.println("Sel·lecciona una opció: ");

            if (input.hasNextInt()){
                menuItem = input.nextInt();
                switch (menuItem){
                    case 1:
                        System.out.println("Has triat donar d’alta nou client....");
                        //insert code here
                        break;
                    case 2:
                        System.out.println("Has triat donar d’alta nou mecànic....");
                        vehiculo();
                        //insert code here
                        break;
                    case 3:
                        System.out.println("Has triat introduir nou vehicle....");
                        //insert code here
                        break;
                    case 4:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        //insert code here
                        break;
                    case 5:
                        System.out.println("Sortint....");
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            }else{
                System.out.println("Opció no vàlida");
            }
            input.nextLine();
            System.out.println("");

        }while(menuItem!=5);

    }


    /**
     * Añade un nuevo Vehiculo a la lista de vehículos.
     */
    public static void vehiculo() {
        ArrayList<ArrayList<String>> vehiculos = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        if (clients.isEmpty()) {
            System.out.println("No hi ha cap client donat d'alta \nAbans de inserir un vehicle has de donar d'alta un client.");
            return;
        } else {
            System.out.println("Escoje el nif del que sera el propietari del vehicle:");
            /*for (ArrayList<Integer> fila : clients) {
                String columna2 = String.valueOf(fila.get(1));
                System.out.println("DNI's: " + columna2);
            }
             */
            for (int i = 0; i < clients.size(); i++) {
                System.out.println("DNI: " + clients.get(i)[0]);
            }
        }
        String nif = input.nextLine();

        // Obtener el índice de la próxima fila disponible en la lista de vehículos
        int indiceFila = 0;
        for (ArrayList<String> fila : vehiculos) {
            if (fila.isEmpty()) {
                break;
            }
            indiceFila++;
        }

        // Asegurarse de que haya suficientes filas en la lista de vehículos
        while (vehiculos.size() <= indiceFila) {
            vehiculos.add(new ArrayList<>());
        }

        String matricula;

        do {
            boolean matriculaExiste = false;
            System.out.println("Introdueix la matrícula del vehicle:");
            matricula = input.nextLine();

            for (ArrayList<String> vehiculo : vehiculos) {
                if (!vehiculo.isEmpty() && Objects.equals(matricula, vehiculo.get(0))) {
                    matriculaExiste = true;
                    break;
                }
            }

            if (!matriculaExiste) {
                System.out.println("Matrícula Afegida.");
                vehiculos.get(indiceFila).add(matricula);
                System.out.println("Introdueix el model vehicle:");
                String model = input.nextLine();
                if (estaVacio(model)) {
                    System.out.println("El model no pot estar buit.");
                    return;
                }
                vehiculos.get(indiceFila).add(model);
                vehiculos.get(indiceFila).add(nif);
            } else {
                System.out.println("Aquesta matrícula no es valida o ja existeix.");
            }
        } while (validarMatricula(matricula));
    }

    /**
     * Verifica que la matrícula tenga el formato correcto.
     * @param matricula La matrícula a verificar.
     * @return true si la matrícula es válida, false si no lo es.
     */
    private static boolean validarMatricula(String matricula) {
        String verifyMatricula = "\\d{4}[a-zA-Z]{3}";
        return matricula.matches(verifyMatricula);
    }
    /**
     * Añade una nueva reparación a la lista de reparaciones.
     */
    public static void reparacio() {

        Scanner input = new Scanner(System.in);

        if (vehiculos.isEmpty()) {
            System.out.println("No hi ha cap vehicle donat d'alta \nAbans has de donar d'alta un vehicle.");
            return;
        } else {
            System.out.println("Escriu la matricula del vehicle:");
            for (ArrayList<String> fila : vehiculos) {
                String columna2 = String.valueOf(fila.get(0));
                System.out.println("Matricula: " + columna2);
            }

        }
        String matricula = input.nextLine();

        // Obtener el índice de la próxima fila disponible en la lista de vehículos
        int indiceFila = 0;
        for (ArrayList<String> fila : reparacions) {
            if (fila.isEmpty()) {
                break;
            }
            indiceFila++;
        }

        // Asegurarse de que haya suficientes filas en la lista de vehículos
        while (vehiculos.size() <= indiceFila) {
            vehiculos.add(new ArrayList<>());
        }

        for (int i = 0; i < mecanics.size(); i++) {
            if (Objects.equals(mecanics.get(i)[2], "lliure")) {
                System.out.println("Hi ha un mecànic lliure:");
                mecanics.get(i)[2] = "ocupat";
                String codi = mecanics.get(i)[0];
                reparacions.get(indiceFila).add(matricula);
                reparacions.get(indiceFila).add(codi);
                reparacions.get(indiceFila).add("en curs");
            }
        }
    }
    private static boolean estaVacio(String input) {
        return input.isEmpty();
    }
}
