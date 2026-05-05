import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class entrada {
    public static void insertarDatosE() {
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

    public static void eliminarDatosE() {
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

    public static void listarE(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListarE = "SELECT * FROM ENTRADA";
            ResultSet  rs = st.executeQuery(sqlListarE);

            while(rs.next()){
                int id = rs.getInt("ID");
                int idconcierto = rs.getInt("CONCIERTO_ID");
                String comprador = rs.getString("COMPRADOR");
                int cantidad = rs.getInt("CANTIDAD");
                Date fecha = rs.getDate("FECHACOMPRA");

                System.out.println(id + " " + idconcierto + " " + comprador + " " + cantidad + " " + fecha);
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
