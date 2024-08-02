package roomBookingApplication.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String CONNECTION = "jdbc:mysql://localhost:3306/hoteldb";
	private static String USER = "root";
	private static String PASSWORD = "Crossworld@123";
	
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
    	boolean flag = true;
    	try(Connection connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);) {
        	
    		while(flag) {
            	System.out.println("1.Add a booking ");
            	System.out.println("2.Remove a booking ");
            	System.out.println("3.Show all bookings");
            	System.out.println("4.Show a booking by id");
            	System.out.println("5.Show a booking by name");
            	System.out.println("6.Update name of booking by Id");
            	System.out.println("7.Exit");
            	int input = scanner.nextInt();
            	switch(input) {
            		case 1:
            			addBooking(connection, scanner);
            			break;
            		case 2:
            			removeBooking(connection, scanner);
            			break;
            		case 3:
            			showAllBookings(connection,scanner);
            			break;
            		case 4:
            			showBookingById(connection,scanner);
            			break;
            		default: 
            			flag = false;
            			break;
            		}
            	
        	}
		} catch (SQLException e) {
			System.out.println("Connection to DB failed "+e.getMessage());
			e.printStackTrace();
		}


    }
    
    private static void addBooking(Connection connection, Scanner scanner) {
		System.out.println("Enter Name");
		String name = scanner.next();
		System.out.println("Enter Price");
		int price = scanner.nextInt();
		System.out.println("Enter days");
		int days = scanner.nextInt();
    	String sqlString = "INSERT INTO bookings(name,price,days) VALUE(?,?,?)";
    	try(PreparedStatement statement = connection.prepareStatement(sqlString)) {
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, days);
			int lines = statement.executeUpdate();
			if(lines> 0) System.out.println(lines+ " got added in DB");
			else System.out.println("Failed to add in DB");
			
		} catch (SQLException e) {
			System.out.println("Failed to create statement "+ e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private static void removeBooking(Connection connection, Scanner scanner) {
    	System.out.println("Enter name for which you want to remove the bookings");
		String name = scanner.next();
		String sqlString = "DELETE FROM bookings WHERE name = ?";
		try(PreparedStatement statement = connection.prepareStatement(sqlString)) {
			statement.setString(1, name);
			int lines = statement.executeUpdate();
			if(lines > 0) System.out.println(lines+ " got deleted from DB");
			else System.out.println("Failed to delete from DB");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
    }
    
    private static void showBookingById(Connection connection, Scanner scanner) {
    	int id = scanner.nextInt();
		String sqlString = "SELECT * FROM bookings WHERE id = ?";
    	try(PreparedStatement statement = connection.prepareStatement(sqlString)) {
    		statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Name " + resultSet.getString("name")+ " Price " + resultSet.getInt("price")+ " days "+ resultSet.getInt("days"));
			}

		} catch (SQLException e) {
			System.out.println("Failed to create statement "+ e.getMessage());
			e.printStackTrace();
		}
		
	}
    
    private static void showAllBookings(Connection connection, Scanner scanner) {
		String sqlString = "SELECT * FROM bookings";
    	try(Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sqlString);
			while(resultSet.next()) {
				System.out.println("Loop Infinite");
				System.out.println("Name " + resultSet.getString("name")+ " Price " + resultSet.getInt("price")+ " days "+ resultSet.getInt("days"));
			}

		} catch (SQLException e) {
			System.out.println("Failed to create statement "+ e.getMessage());
			e.printStackTrace();
		}
		
	}
}
