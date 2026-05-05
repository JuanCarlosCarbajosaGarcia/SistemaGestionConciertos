import java.sql.*;
import java.util.Scanner;

public class artista {
    public static void insertarDatosA() {
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

            String sqlArtista = "INSERT INTO ARTISTA (NOMBRE, GENEROMUSICAL, PAISORIGEN) VALUES (?, ?, ?)";
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

    public static void eliminarDatosA() {
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

    public static void listarA(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        try(Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conn.createStatement()) {

            String sqlListarA = "SELECT * FROM ARTISTA";
            ResultSet rs = st.executeQuery(sqlListarA);

            while(rs.next()){
                int id = rs.getInt("ID");
                String Nombre = rs.getString("NOMBRE");
                String Genero = rs.getString("GENEROMUSICAL");
                String Pais = rs.getString("PAISORIGEN");
                System.out.println(id + " " + Nombre + " " + Genero + " " + Pais);
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
