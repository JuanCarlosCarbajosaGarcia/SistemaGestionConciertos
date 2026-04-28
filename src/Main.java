import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        System.out.println("4. salir");
                        System.out.print("que desea hacer: ");
                        menuA = sc.nextInt();
                        switch(menuA){
                            case 1:
                                insertarDatosA();
                            break;
                            case 2:
                                eliminarDatosA();
                            break;
                            case 3:
                                listarA();
                            break;
                            case 4:
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
                        System.out.println("4. salir");
                        System.out.print("que desea hacer: ");
                        menuC = sc.nextInt();
                        switch(menuC){
                            case 1:
                                insertarDatosC();
                            break;
                            case 2:
                                eliminarDatosC();
                            break;
                            case 3:
                                listarC();
                            break;
                            case 4:
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
                        System.out.println("4. salir");
                        System.out.print("que desea hacer: ");
                        menuE = sc.nextInt();
                        switch(menuE){
                            case 1:
                                insertarDatosE();
                            break;
                            case 2:
                                eliminarDatosE();
                            break;
                            case 3:
                                listarE();
                            break;
                            case 4:
                                System.out.println("saliendo");
                                sqlE = false;
                            break;
                            default: System.out.println("Opcion no permitida");
                        }
                    }
                break;
                case 4:
                    sql = false;
                break;
                default: System.out.println("Opcion no permitida");
            }
        }
    }

    private static void insertarDatosA() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conn.createStatement()) {
        Scanner scA = new Scanner(System.in);
        System.out.println("Ingresa los datos del artista");
        System.out.print("Ingresa el nombre del artista: ");
        String Nombre = scA.nextLine();
        System.out.print("Ingresa el genero musical: ");
        String Genero = scA.nextLine();
        System.out.print("Ingresa el pais de origen: ");
        String Pais = scA.nextLine();

        String sqlArtista = "INSERT INTO ARTISTA (NOMBRE, GENERO, PAIS) VALUES (?, ?, ?)";
        PreparedStatement psArtista = conn.prepareStatement(sqlArtista);
        psArtista.setString(1, Nombre);
        psArtista.setString(2, Genero);
        psArtista.setString(3, Pais);
        psArtista.executeUpdate();

            System.out.println("artista insertado");
        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void insertarDatosC() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conn.createStatement()) {
            Scanner scC = new Scanner(System.in);

            System.out.println("Ingresa los datos del concierto: ");
            System.out.print("Ingresa el id del artista: ");
            int Artista = scC.nextInt();
            System.out.print("Ingresa la fecha del concierto(DD/MM/YYYY): ");
            String Fecha = scC.next();
            System.out.print("Ingresa el lugar del concierto: ");
            String Lugar = scC.nextLine();
            System.out.print("Ingresa el precio de la entrada: ");
            double Precio = scC.nextDouble();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(Fecha,formatter);
            Date conciertofecha = java.sql.Date.valueOf(fecha);

            String sqlConcierto = "INSERT INTO CONCIERTO (ARTISTA_ID, FECHA, LUGAR, PRECIOENTRADA) VALUES (?, ?, ?,?)";
            PreparedStatement psConcierto = conn.prepareStatement(sqlConcierto);
            psConcierto.setInt(1, Artista);
            psConcierto.setDate(2, conciertofecha);
            psConcierto.setString(3, Lugar);
            psConcierto.setDouble(4, Precio);
            psConcierto.executeUpdate();

            System.out.println("concierto insertado");

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void insertarDatosE() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conn.createStatement()) {
            Scanner scE = new Scanner(System.in);

            System.out.println("Ingresa los datos del entrada: ");
            System.out.print("id del concierto: ");
            int Concierto = scE.nextInt();
            System.out.print("nombre del comprador: ");
            String Comprador = scE.nextLine();
            System.out.print("cantidad de entradas: ");
            int Cantidad = scE.nextInt();

            LocalDate fecha = LocalDate.now();
            Date compraEntrada = java.sql.Date.valueOf(fecha);

            String sqlEntrada = "INSERT INTO ENTRADA (CONCIERTO_ID, COMPRADOR, CANTIDAD, FECHACOMPRA) VALUES (?, ?, ?, ?)";
            PreparedStatement psEntrada = conn.prepareStatement(sqlEntrada);
            psEntrada.setInt(1, Concierto);
            psEntrada.setString(2, Comprador);
            psEntrada.setInt(3, Cantidad);
            psEntrada.setDate(4, compraEntrada);
            psEntrada.executeUpdate();

            System.out.println("entrada insertado");

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarDatosA() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
        Statement st = conn.createStatement()) {
            Scanner scDelA = new Scanner(System.in);
            System.out.print("ingrese el artista del artista a eliminar: ");
            int artista = scDelA.nextInt();

            String SQLEliminar = "DELETE FROM ARTISTA WHERE ID = ?";
            PreparedStatement psDelA = conn.prepareStatement(SQLEliminar);
            psDelA.setInt(1, artista);
            psDelA.executeUpdate();

            System.out.println("eliminado");

        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarDatosC() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {
            Scanner scDelC = new Scanner(System.in);
            System.out.print("ingrese el id del concierto a eliminar: ");
            int concierto = scDelC.nextInt();

            String SQLEliminar = "DELETE FROM CONCIERTO WHERE ID = ?";
            PreparedStatement psDelC = conn.prepareStatement(SQLEliminar);
            psDelC.setInt(1, concierto);
            psDelC.executeUpdate();

            System.out.println("eliminado");

        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarDatosE() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {
            Scanner scDelE = new Scanner(System.in);
            System.out.print("ingrese el id del concierto a eliminar: ");
            int concierto = scDelE.nextInt();

            String SQLEliminar = "DELETE FROM ENTRADA WHERE ID = ?";
            PreparedStatement psDelE = conn.prepareStatement(SQLEliminar);
            psDelE.setInt(1, concierto);
            psDelE.executeUpdate();

            System.out.println("eliminado");

        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void listarA(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListar = "SELECT * FROM ARTISTA";
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void listarC(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListar = "SELECT * FROM CONCIERTO";
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void listarE(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListar = "SELECT * FROM ENTRADA";
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}