package avionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AvionJDBC {
	
	static void mostrarMenu() {
        System.out.println("Seleccione opción:");
        System.out.println("1. Mostrar todos los vuelos");
        System.out.println("2. Mostrar los datos de un vuelo");
        System.out.println("3. Insertar un nuevo vuelo");
        System.out.println("4. Borrar un vuelo");
        System.out.println("5. Actualizar horario de un vuelo");
        System.out.println("0. Salir");
    }

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		String url = "jdbc:mysql://127.0.0.1:3307/avion";
		String user = "alumno";
		String password = "1234-Abcd";
		
		int opcion;
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			do {
				
				mostrarMenu();
	            opcion = s.nextInt();
	            s.nextLine();
	            
	            switch(opcion) {
	            
	            	case 1:
	            	
	            		//MOSTRAR TODOS LOS VUELOS:
	            		
	            		Statement stmt = con.createStatement();
            			ResultSet rs = stmt.executeQuery("SELECT * FROM salida");
            			while (rs.next()) {
            				int id=rs.getInt("id");
            				String hora=rs.getString("hora");
            				String vuelo=rs.getString("vuelo");
            				String destino=rs.getString("destino");
            				String mostrador=rs.getString("mostrador");
            				int puerta=rs.getInt("puerta");
            				System.out.println("ID: " + id + ", Hora: " + hora + ", Vuelo: " + vuelo + ", Destino: " + destino + ", Mostrador: " + mostrador + ", Embarque: " + puerta);
            			}
            			rs.close();
            			stmt.close();
	            		
	            	break;
	            	
	            	case 2:
	            		
	            		//MOSTRAR LOS DATOS DE UN SOLO VUELO:
	            		
	            		System.out.println("Elige un id");
		                int id = s.nextInt();
		                
		                PreparedStatement sel_pstmt = con.prepareStatement("SELECT * FROM salida WHERE id=?");
            			sel_pstmt.setInt(1, id);
            			ResultSet rs_sel = sel_pstmt.executeQuery();
            			while (rs_sel.next()) {
            				id=rs_sel.getInt("id");
            				String hora=rs_sel.getString("hora");
            				String vuelo=rs_sel.getString("vuelo");
            				String destino=rs_sel.getString("destino");
            				String mostrador=rs_sel.getString("mostrador");
            				int puerta=rs_sel.getInt("puerta");
            				System.out.println("Hora: " + hora + ", Vuelo: " + vuelo + ", Destino: " + destino + ", Mostrador: " + mostrador + ", Embarque: " + puerta);
            			}
            			rs_sel.close();
            			sel_pstmt.close();
	            		
	            	break;
	            	
	            	case 3:
	            		
	            		//INSERTAR UN VUELO:
	            		
	            		System.out.println("Pon un id");
		                id = s.nextInt();
		                
		                System.out.println("Pon una hora");
		                String hora = s.next();
		                
		                System.out.println("Pon un vuelo");
		                String vuelo = s.next();
		                
		                System.out.println("Pon un destino");
		                String destino = s.next();
		                
		                System.out.println("Pon un mostrador");
		                String mostrador = s.next();
		                
		                System.out.println("Pon una puerta de embarque");
		                int puerta = s.nextInt();
		                
		                PreparedStatement ins_pstmt = con.prepareStatement("INSERT INTO salida (id, hora, vuelo, destino, mostrador, puerta) VALUES (?, ?, ?, ?, ?, ?)");
                	  	ins_pstmt.setInt(1, id);
                	  	ins_pstmt.setString(2, hora);
                	  	ins_pstmt.setString(3, vuelo);
                	  	ins_pstmt.setString(4, destino);
                	  	ins_pstmt.setString(5, mostrador);
                	  	ins_pstmt.setInt(6, puerta);
                	  	int rowsInserted = ins_pstmt.executeUpdate();
            			ins_pstmt.close();
	            		
	            	break;
	            	
	            	case 4:
	            		
	            		//BORRAR UN VUELO:
	            		
	            		System.out.println("Elige un id");
		                id = s.nextInt();
		                
		                PreparedStatement dele_pstmt = con.prepareStatement("DELETE FROM salida WHERE id = ?");
                	  	dele_pstmt.setInt(1, id);
                	  	int rowsDeleted =dele_pstmt.executeUpdate();
                	  	dele_pstmt.close();
	            		
	            	break;
	            	
	            	case 5:
	            		
	            		//ACTUALIZAR HORA DE UN VUELO:
	            		
	            		System.out.println("Pon un id");
		                id = s.nextInt();
		                
		                System.out.println("Pon una hora");
		                hora = s.next();
		                
		                PreparedStatement upd_pstmt = con.prepareStatement("UPDATE salida SET hora = ? WHERE id = ?");
                	  	upd_pstmt.setString(1, hora);
                	  	upd_pstmt.setInt(2, id);
                	  	int rowsUpdated = upd_pstmt.executeUpdate();
                	  	upd_pstmt.close();
	            		
	            	break;
	            	
	            	case 0:
		                    System.out.println("Fin del programa");
		            break;
		            
		            default:
		                    System.out.println("Opción no válida");
	            		
	            }
				
			}while (opcion != 0);
			con.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}
}