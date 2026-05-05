import java.sql.*;
import java.util.Scanner;

public  class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean sql=true;
        boolean sqlA=true;
        boolean sqlC=true;
        boolean sqlE=true;
        int menu;
        int menuA;
        int menuC;
        int menuE;

        //menu general
        while (sql){
            System.out.println("----Menu----");
            System.out.println("1. artista");
            System.out.println("2. concierto");
            System.out.println("3. entrada");
            System.out.println("4. salir");
            System.out.print("que desea hacer: ");
            menu = sc.nextInt();
            switch (menu){
                case 1:
                    while(sqlA){
                        //menu artista
                        System.out.println("----Menu_Artista----");
                        System.out.println("1. crear artista");
                        System.out.println("2. eliminar artista");
                        System.out.println("3. listar artistas");
                        System.out.println("4. volver");
                        System.out.print("que desea hacer: ");
                        menuA = sc.nextInt();
                        switch(menuA){
                            case 1:
                                artista.insertarDatosA();
                            break;
                            case 2:
                                artista.eliminarDatosA();
                            break;
                            case 3:
                                artista.listarA();
                            break;
                            case 4:
                                System.out.println("volviendo");
                                sqlA = false;
                            break;
                            default: System.out.println("Opcion no permitida");
                        }
                    }
                break;
                case 2:
                    while(sqlC){
                        //menu concierto
                        System.out.println("----Menu_Concierto----");
                        System.out.println("1. crear concierto");
                        System.out.println("2. eliminar concierto");
                        System.out.println("3. listar conciertos");
                        System.out.println("4. volver");
                        System.out.print("que desea hacer: ");
                        menuC = sc.nextInt();
                        switch(menuC){
                            case 1:
                                concierto.insertarDatosC();
                            break;
                            case 2:
                                concierto.eliminarDatosC();
                            break;
                            case 3:
                                concierto.listarC();
                            break;
                            case 4:
                                System.out.println("volviendo");
                                sqlC = false;
                            break;
                            default: System.out.println("Opcion no permitida");
                        }
                    }
                break;
                case 3:
                    while(sqlE){
                        //menu entradas
                        System.out.println("----Menu_Entrada----");
                        System.out.println("1. crear entrada");
                        System.out.println("2. eliminar entrada");
                        System.out.println("3. listar entradas");
                        System.out.println("4. volver");
                        System.out.print("que desea hacer: ");
                        menuE = sc.nextInt();
                        switch(menuE){
                            case 1:
                                entrada.insertarDatosE();
                            break;
                            case 2:
                                entrada.eliminarDatosE();
                            break;
                            case 3:
                                entrada.listarE();
                            break;
                            case 4:
                                System.out.println("volviendo");
                                sqlE = false;
                            break;
                            default: System.out.println("Opcion no permitida");
                        }
                    }
                break;
                case 4:
                    System.out.println("saliendo");
                    sql = false;
                break;
                default: System.out.println("Opcion no permitida");
            }
        }
    }
}