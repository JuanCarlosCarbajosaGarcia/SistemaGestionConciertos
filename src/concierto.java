import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class concierto {
    public static void insertarDatosC() {
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

    public static void eliminarDatosC() {
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

    public static void listarC(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListarC = "SELECT * FROM CONCIERTO";
            st.executeQuery(sqlListarC);
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
