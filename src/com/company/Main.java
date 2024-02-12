package com.company;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);


    static ArrayList<String[]> reparacions = new ArrayList<>();
    static ArrayList<String[]> vehicles = new ArrayList<>();
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

                input.nextLine();

                switch (menuItem){
                    case 1:
                        System.out.println("Has triat donar d’alta nou client....");
                        registrarClient();
                        break;
                    case 2:
                        System.out.println("Has triat donar d’alta nou mecànic....");
                        registrarMecanic();
                        break;
                    case 3:
                        System.out.println("Has triat introduir nou vehicle....");
                        registrarVehicle();
                        break;
                    case 4:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        registrarReparacio();
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

            System.out.println("");

        }while(menuItem!=5);

    }


    public String llegirDNI(){

        String DNI;
        int valid = 0;

        do {

            System.out.println("Introdueix el DNI del nou client: ");
            DNI = input.nextLine();

            valid = 0;

            for (int i = 0; i < clients.size(); i++) {
                if ( clients.get(i)[0].equalsIgnoreCase(DNI) ) {
                    valid += 1;

                    System.out.println("Aquest DNI ja esta registrat per l'usuari numero: " + i);

                }
            }

            if (estaVacio(DNI)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

            if (!DNI.matches("\\d{8}[a-zA-Z]{1}")) {
                System.out.println("Introdueix un DNI amb el format correcte");
                valid += 1;
            }

        } while ( valid != 0 );

        return DNI;
    }

    public String llegirNom(){

        String nom;
        int valid = 0;

        do {

            valid = 0;

            System.out.println( "Introdueix el Nom: " );
            nom = input.nextLine();

            if (estaVacio(nom) || nom.equalsIgnoreCase(" ")) {
                valid += 1;
                System.out.println("Has de omplir aquest espai...");
            }

        } while ( valid != 0);

        return nom;

    }

    public String estaOcupat() {
        String sino = "a";

        do {
            System.out.println("Esta el mecànic ocupat?  (si / no) ");
            sino = input.nextLine();

        } while (!sino.equalsIgnoreCase("si") && !sino.equalsIgnoreCase("no"));

        if (sino.equalsIgnoreCase("si")) {
            return "ocupat";
        }
        else {
            return "lliure";
        }

    }

    public String llegirCodi() {
        int valid = 0;
        String codi;

        do {
            valid = 0;

            System.out.println("Escriu el codi del mecànic: ");
            codi = input.nextLine();

            for (int i=0; i < mecanics.size(); i++) {
                if (mecanics.get(i)[0].equalsIgnoreCase(codi) || estaVacio(codi)) {
                    valid += 1;
                }
            }

            if (valid != 0) {
                System.out.println("Escriu un numero valid que no hagi sigut ja introduit abans.");
            }

            if (!codi.matches("\\d{6}")) {
                System.out.println("Format incorrecte, has de posar 6 enters.");
                valid += 1;
            }
        } while ( valid != 0);

        return codi;
    }

    public void registrarClient() {
        clients.add(new String[]{llegirDNI(), llegirNom()});
    }

    public void registrarMecanic() {
        mecanics.add(new String[]{llegirCodi(), llegirNom(), estaOcupat()});
    }

    /**
     * Retorna la matrícula del vehicle.
     * @return La matrícula del vehicle.
     */
    public String llegirMatricula(){

        String matricula;
        int valid = 0;

        do {

            System.out.println("Introdueix la matricula del nou vehicle: ");
            matricula = input.nextLine();

            valid = 0;

            for (int i = 0; i < vehicles.size(); i++) {
                if ( vehicles.get(i)[0].equalsIgnoreCase(matricula) ) {
                    valid += 1;

                    System.out.println("Aquest DNI ja esta registrat per l'usuari numero: " + i);

                }
            }

            if (estaVacio(matricula)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

            if (!matricula.matches("\\d{4}[a-zA-Z]{3}")) {
                System.out.println("Introdueix una matricula amb el format correcte");
                valid += 1;
            }

        } while ( valid != 0 );

        return matricula;
    }
    /**
     * Retorna el model del vehicle.
     * @return El model del vehicle.
     */
    public String llegirModel(){

        String model;
        int valid = 0;

        do {

            valid = 0;

            System.out.println( "Introdueix el Model del vehicle: " );
            model = input.nextLine();

            if (estaVacio(model) || model.equalsIgnoreCase(" ")) {
                valid += 1;
                System.out.println("Has de omplir aquest espai...");
            }

        } while ( valid != 0);

        return model;

    }
    /**
     * Retorna el DNI del propietari del vehicle.
     * @return El DNI del propietari.
     */
    public String escollirDNI(){

        String DNI;
        int valid = 0;

        do {
            valid = 0;

            System.out.println("Escoje el DNI del que sera el propietari del vehicle:");
            for (int i = 0; i < clients.size(); i++) {
                System.out.println("DNI" + ": " + i + " "  + clients.get(i)[0]);
            }

            System.out.println("Introdueix el numero del DNI:");
            int num = input.nextInt();
            DNI = clients.get(num)[0];

            if (estaVacio(DNI)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

        } while ( valid != 0);

        return DNI;

    }
    /**
     * Registra un vehicle.
     */
    public void registrarVehicle() {

        if (clients.isEmpty()) {
            System.out.println("No hi ha cap client donat d'alta \nAbans de inserir un vehicle has de donar d'alta un client.");
            return;
        }

        vehicles.add(new String[]{llegirMatricula(), llegirModel(), escollirDNI()});
    }
    /**
     * Retorna la matrícula del vehicle que es vol reparar.
     * @return La matrícula del vehicle.
     */
    public String escollirMatricula(){

        String matricula;
        int valid = 0;

        do {
            valid = 0;

            System.out.println("Escolleix la matricula del vehicle que vols fer la reparacio:");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println("Matricula" + ": " + i + " "  + vehicles.get(i)[0]);
            }

            System.out.println("Introdueix la posicio de la matricula:");
            int num = input.nextInt();
            input.nextLine();
            matricula = vehicles.get(num)[0];

            if (estaVacio(matricula)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

        } while ( valid != 0);

        return matricula;

    }
    public String codiReparacio(){
        String codi;
        int valid = 0;

        do {
            valid = 0;
            codi = "";

            for (int i = 0; i < mecanics.size(); i++) {
                if (Objects.equals(mecanics.get(i)[2], "lliure")) {
                    codi = mecanics.get(i)[0];
                } else if (Objects.equals(mecanics.get(i)[2], "ocupat")) {
                    codi = mecanics.get(i)[0];
                }
            }

            if (estaVacio(codi)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

        } while ( valid != 0);
        return codi;
    }
    /**
     * Retorna l'estat de la reparació.
     * @return "En curs" si hi ha un mecànic lliure, "Oberta" si no n'hi ha cap.
     */
    public String estatReparacio(){
        String codi;
        String estatReparacio;
        int valid = 0;

        do {
            valid = 0;
            codi = "";
            estatReparacio = "";

            for (int i = 0; i < mecanics.size(); i++) {
                if (Objects.equals(mecanics.get(i)[2], "lliure")) {
                    System.out.println("Hi ha un mecànic lliure, el codi del mecànic es: " + mecanics.get(i)[0]);
                    mecanics.get(i)[2] = "ocupat";
                    codi = mecanics.get(i)[0];
                    estatReparacio = "En curs";
                    System.out.println("Reparacio oberta amb el codi: " + codi + " i l'estat: " + estatReparacio);
                } else if (Objects.equals(mecanics.get(i)[2], "ocupat")) {
                    System.out.println("NO hi ha cap mecànic lliure.");
                    codi = mecanics.get(i)[0];
                    estatReparacio = "Oberta";
                    System.out.println("Reparacio oberta amb el codi: " + codi + " i l'estat: " + estatReparacio);
                }
            }

            if (estaVacio(estatReparacio)) {
                System.out.println("Has de omplir aquest espai");
                valid += 1;
            }

        } while ( valid != 0);
        return estatReparacio;

    }

    /**
     * Registra una reparació.
     */
    public void registrarReparacio() {

        if (vehicles.isEmpty() && mecanics.isEmpty()) {
            System.out.println("No hi ha cap vehicle o mecanic donat d'alta \nAbans de inserir una reparacio has de donar d'alta un vehicle o mecanic.");
            return;
        }

        reparacions.add(new String[]{escollirMatricula(), codiReparacio(), estatReparacio()});
    }

    /**
     * Verifica si un String está vacío.
     * @param input El String a verificar.
     * @return true si el String está vacío, false si no lo está.
     */
    private static boolean estaVacio(String input) {
        return input.isEmpty();
    }
}
