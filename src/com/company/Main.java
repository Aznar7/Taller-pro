package com.company;

import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    Scanner input = new Scanner(System.in);
    ArrayList<String[]> clients = new ArrayList<>();
    ArrayList<String[]> mecanics = new ArrayList<>();



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

            System.out.println("");

        }while(menuItem!=5);

    }

    private static boolean estaVacio(String input) {
        return input.isEmpty();
    }

    /**
     * Guarda un DNI de 8 enters i un caracter per consola
     *
     * @String DNI ( 8 int + 1 character )
     */
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

    /**
     * Guarda un Nom per consola
     *
     * @return String nom
     */
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

    /**
     * Pregunta a l'usuari si el mecanic esta ocupat per consola
     *
     * @return boolean estaOcupat
     */
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

    /**
     * Llegeix un codi de 6 enters per consola
     *
     * @return String codi ( 6 int )
     */
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

    /**
     * Crea un arrayList de arrays sobre els clients
     */
    public void registrarClient() {

        clients.add(new String[]{llegirDNI(), llegirNom()});

    }


    /**
     * Crea un arrayList de arrays sobre els mecanics
     */
    public void registrarMecanic() {

        mecanics.add(new String[]{llegirCodi(), llegirNom(), estaOcupat()});
    }


}
