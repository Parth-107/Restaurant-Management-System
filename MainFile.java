
/*
	Project: Restaurant Management System

	Memebers name: 
		- Parth Shah 
		- Mayank Patel

	Oracle Account:
		- ORA_pss114
		- ORA_mtp114

	Entire Databse created in: ORA_pss114 

*/

import java.sql.*;
import java.io.*;
import java.util.*;

public class MainFile {

	static BufferedReader keyboard;
	static Connection conn;
	static Statement stmt;
	static Statement stmt2;
	static Statement stmt3;

	// Customer function
	private static void Customer() {

		try {

			System.out.println("What you would like to order?");
			System.out.println("-----Here is our Menu-----");
			// Building statement
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();

			// Show the Menu items to the Customer
			String query = "SELECT DishName, Calorie, Price, Description FROM Menu_Items";
			String leftAlignFormat = "| %-20s | %-20s | %-20s | %-27s |%n";

			System.out.format(
					"+----------------------+----------------------+----------------------------------------------------+%n");
			System.out.format(
					"| Dish Name            | Calories             | Price($)                | Description              |%n");
			System.out.format(
					"+----------------------+----------------------+----------------------------------------------------+%n");
			// Executing query
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				System.out.format(leftAlignFormat, rset.getString(1), rset.getString(2), rset.getString(3),
						rset.getString(4));
			}

			System.out.print("\nEnter the Dish Name :");
			String dish = keyboard.readLine();

			// First select the dish from the menu and check that in Menu items table
			ResultSet rst2 = stmt2
					.executeQuery("SELECT ItemID FROM Menu_Items WHERE UPPER(DishName) = UPPER('" + dish + "')");
			int ItmID = 0;

			// Fteching the records
			while (rst2.next()) {
				ItmID = rst2.getInt(1);
			}
			// If above statement is true
			if (ItmID != 0) {

				// Random rd = new Random();
				// int oid = rd.nextInt(1,100);
				int max = 100;
				int min = 1;
				int oid = (int)Math.floor(Math.random()*(max-min+1)+min);

				try {
					// Insert new Dish name into the Order table
					int i = stmt.executeUpdate(
							"INSERT INTO Orders values('" + oid + "','" + dish + "','" + ItmID + "','" + 1 + "')");
					if (i == 1) {// if User inputed valid option
						System.out.println("\nItem is Successfully Added..! " + "Here is your order ID :" + oid);
					}
				} catch (Exception e)// If wrong Dish selected
				{
					System.out.println("invalid choice...");
					return;
				}
			} else {
				System.out.println("\nItem is not exist in the Menu. Please try again");
			}
			// Commit the changes
			conn.commit();
			// Close the connections
			rset.close();
			rst2.close();
		}

		catch (Exception e) {
			System.out.println("Caught Exception: \n     " + e);
			return;
		}
	}

	// Manager function
	private static void Manager() {
		
		try {
			//Creating stataments
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			int ch;
			// Creating manager choices
			System.out.println("\n-----Please select from following options-----");
			System.out.println("\n- Enter 1 if you want to update the Payroll Status");
			System.out.println("- Enter 2 if you want to insert the food in Inventory");
			System.out.println("- Enter 3 if you want to manage the Menu Items");

			System.out.print("\nPlease enter here :");
			ch = Integer.parseInt(keyboard.readLine());
			
			//If update the Payroll
			if (ch == 1) {
				int payID;
				System.out.print("\nPlease Enter the Payroll ID :");
				payID = Integer.parseInt(keyboard.readLine());

				String status;
				System.out.print("\nEnter the new Payroll status (You may type Remaining or Paid) :");
				status = keyboard.readLine();

				//Update Query
				int i = stmt.executeUpdate(
						"UPDATE PAYROLL " + "SET " + "Status" + " = '" + status + "' WHERE PayID = '" + payID + "'");

				//If query is true
				if (i == 1) {
					System.out.println("\nUpdated...!");
					System.out.println("\nHere is the Informtaion: ");

					// Designing
					String Format = "| %-20s | %-20s | %-20s | %-15s |%n";
					System.out.println();
					System.out.format(
							"+----------------------+----------------------+----------------------+-----------------+%n");
					System.out.format(
							"| Employee ID          | Employee Name        | Payroll ID	   | Status  	       |%n");
					System.out.format(
							"+----------------------+----------------------+----------------------+-----------------+%n");

					//Create result set to Join Employee and Payroll tables to display the information
					ResultSet rst = stmt
							.executeQuery("SELECT Employee.EmpID, Employee.EmployeeName, Payroll.PayID, Payroll.Status "
									+ "FROM Employee "
									+ "JOIN Payroll ON Employee.EmpID = Payroll.EmpID ");

					while (rst.next()) {
						System.out.format(Format, rst.getString(1), rst.getString(2), rst.getString(3),
								rst.getString(4));
					}
					rst.close();
				}//End of 1st option
				else {
					System.out.println("\nSorry Somthing went wrong! ");
				}
			} 
			//Second option to manage the inventory
			else if (ch == 2) {
				System.out.print("\nEnter the Item ID that require essential ingredients to prepare the dish :");
				String itemID = keyboard.readLine();

				// First select the dish from the menu and check that in Menu items table
				ResultSet rset = stmt.executeQuery("SELECT ItemID FROM Menu_Items WHERE ItemID = '" + itemID + "'");
				int ItmID = 0;

				// Fteching the records
				while (rset.next()) {
					ItmID = rset.getInt(1);
				}

				//If the user input is correct
				if (ItmID != 0) {
					System.out.print("\nEnter the food Name that you want to add in Inventory : ");
					String foodname = keyboard.readLine();
					System.out.print("\nEnter the Quantity for that food : ");
					int foodqty = Integer.parseInt(keyboard.readLine()); // Reading input from user
					
					//Generating the random Food ID between 0 to 50
					// Random rd = new Random();
					// int foodid = rd.nextInt(50);
					int max = 50;
					int min = 1;
					int foodid = (int)Math.floor(Math.random()*(max-min+1)+min);
					try {
						// Insert new Dish name into the Order table
						int i = stmt.executeUpdate("INSERT INTO Inventory values('" + foodid + "','" + foodname + "','"
								+ foodqty + "','" + ItmID + "')");

						// if User input is correct
						if (i == 1) {
							System.out.println("\nBoth the things are Successfully Added..! "
									+ " Here is your new Food ID :" + foodid);
						}
					} 
					catch (Exception e)// If wrong Dish selected
					{
						System.out.println("invalid choice...");
						return;
					}
				}//End of 1st option 
				else {
					System.out.print("\nItemID is not in the Item Menu.\n");
				}
			} 
			else if (ch == 3) {
				while (true) 
				{
					//Create the options for managers
					System.out.println("\n ----What you want to do? Please select from following options----");
					System.out.println("\n- Press 1 if you wants to add new Item in the Menu ");
					System.out.println("- Press 2 if you wants to Update the item price ");
					System.out.println("- Press 3 if you wants to delete the item from the Menu list ");
					System.out.print("\nEnter your choice here :");
					int choice = Integer.parseInt(keyboard.readLine()); // Reading input from user

					//If add new item into the Item_Menu table
					if (choice == 1) {

						try {
							//Take the necessary inputs from the manager
							System.out.print("\nEnter the Dish name :");
							String dishname = keyboard.readLine();
							System.out.print("\nEnter the calories of that dish :");
							int calories = Integer.parseInt(keyboard.readLine());
							System.out.print("\nEnter the price of that dish :");
							int price = Integer.parseInt(keyboard.readLine());
							System.out.print("\nEnter the description of that dish :");
							String discription = keyboard.readLine();

							// Random rd = new Random();
							// int itemid = rd.nextInt(100, 200);
							int max = 200;
							int min = 100;
							int itemid = (int)Math.floor(Math.random()*(max-min+1)+min);
							
							//Insert new items into the Menu_Items table
							int i = stmt.executeUpdate("INSERT INTO Menu_Items values('" + itemid + "','" + dishname
									+ "','" + calories + "', '" + price + "', '" + discription + "')");
							
							// if above statement is valid
							if (i == 1) {
								System.out.println("\nItem is Successfully Added..! " + " Here is your new Item ID :" + itemid);
								break;
							} else {
								System.out.println("Not added");
							}
						}
						//Handling diffrenet exceptions
						catch (SQLException e) {
							System.out.println("\nSomething is wrong here! Try again ");
						} 
						catch (NumberFormatException e) {
							System.out.println("\nSomething is wrong here! Try again ");
						}
					}//End of 1st option

					//If update iteme price
					if (choice == 2){
						try{
							System.out.print("\nEnter the Dish Name that you would like to change the price : ");
							String dishName = keyboard.readLine();
							System.out.print("\nNow Enter the new Price of that Dish : ");
							int change_price = Integer.parseInt(keyboard.readLine());

							//Exxecute update query
							int update_price = stmt.executeUpdate(
							"UPDATE Menu_Items " + "SET " + "Price" + " = '" + change_price + "' WHERE DishName = '" + dishName + "'");

							//If above query 
							if(update_price == 1){
								System.out.println("\nDish price sucessfully updated!");
								break;
							}
							else{
								System.out.println("\nDish price not updated! Please try again.");
							}
						}
						//Handling necessary exceptions
						catch (SQLException e) {
							System.out.println("Something is wrong here!");
						} 
						catch (NumberFormatException e) {
							System.out.println("Something is wrong here!");
						}
					}//End of 2nd option
					
					//If delete item from Menu_Item table
					if (choice == 3){

						try{
							System.out.print("\nEnter the Dish Name that want to delete : ");
							String delete_dish = keyboard.readLine();
							
							//Firstly find the dish name from the Menu_Item
							ResultSet rst2 = stmt2.executeQuery("SELECT ItemID FROM Menu_Items WHERE UPPER(DishName) = UPPER('" + delete_dish + "')");
							
							int ItmID=0;
							while (rst2.next()) {
								ItmID = rst2.getInt(1);
							}

							//Delete item records from all other tables, as it contains foreign key
							int delete_child2 = stmt3.executeUpdate("DELETE FROM INVENTORY WHERE UPPER(ITEMID) = UPPER('" + ItmID + "')");
							int delete_child = stmt2.executeUpdate("DELETE FROM Orders WHERE UPPER(ITEMID) = UPPER('" + ItmID + "')");
							int update_price = stmt.executeUpdate("DELETE FROM Menu_Items WHERE UPPER(DishName) = UPPER('" + delete_dish + "')");
							
							//If above query true without any exceptions
							if(update_price == 1){
								System.out.println("\nRecord deleted sucessfully !");
								break;
							}
							else{
								System.out.println("\nDish not deleted! Please try again.");
							}
						}
						//Handle different exceptions
						catch (SQLException e) {
							System.out.println("Something is wrong during deletion!" +e);
						} 
						catch (NumberFormatException e) {
							System.out.println("Something is during deletion!");
						}
					}//End of 3rd option
				}
			}
			else {
				System.out.println("\nInvalid choice..! Please select a valid option between 1 to 3");
			}
			// Commit the changes
			conn.commit();
			// Close the connections
		}
		//At the end handle all necessary exceptions 
		catch (SQLSyntaxErrorException e) {
			System.out.println("\n Please enter valid ItemID.");
		} catch (SQLException e) {
			System.out.println("Something is wrong !" + e);

		} catch (NumberFormatException e) {
			System.out.println("Something is wrong in Number formatting!");
		} catch (Exception e) {
			System.out.println("Caught SQL Exception: \n     " + e);
		}
	}

	// Staff
	private static void Staff() {
		try {
			// Create the statement
			stmt = conn.createStatement();

			// Fetching the ORDER table
			String query = "SELECT OrderID, DishName, CustomerID FROM Orders";
			String leftAlignFormat = "| %-20s | %-20s | %-20s |%n";
			System.out.println();
			System.out.format("+----------------------+----------------------+----------------------+%n");
			System.out.format("| Order ID             | Dish Name            | Customer ID          |%n");
			System.out.format("+----------------------+----------------------+----------------------+%n");
			// Executing query
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				System.out.format(leftAlignFormat, rset.getString(1), rset.getString(2), rset.getString(3));
			}

			// Close the connection
			conn.close();
		} catch (Exception e) {
			System.out.println("Caught Exception: \n     " + e);
			return;
		}

	}

	// Main class
	public static void main(String args[])
			throws IOException {
		String username = "ORA_pss114", password = "CS470_2178";
		String ename;
		int original_empno = 0;
		int empno;

		keyboard = new BufferedReader(new InputStreamReader(System.in));

		try {

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Registered the driver...");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle2.wiu.edu:1521/orclpdb1",
					username, password);

			System.out.println("logged into oracle as " + username);

			conn.setAutoCommit(false);

			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery("SELECT TName " + "From Tab");

			while (rset.next()) {
				System.out.println(rset.getString(1));
			}

			rset.close();

			int userChoice;

			// This loop will run until user enters 5 or on exception.
			while (true) {

				// Creating user menu with choices
				System.out.println("\nPlease select from following choices :\n"
						+ "- Enter 1 if you are Customer.\n"
						+ "- Enter 2 if you are Manager.\n"
						+ "- Enter 3 if you are Staff member.\n"
						+ "- Enter 4 to Exit.\n");
				System.out.print("Enter your choice: ");
				userChoice = Integer.parseInt(keyboard.readLine()); // Reading input from user
				switch (userChoice) {
					case 1:

						// Calling method to find number of activities based on camp name.
						Customer();
						break;
					case 2:

						// Calling method to find number of participated customer by activity name.
						Manager();
						break;
					case 3:

						// This method will update the value of customer based on CID.
						Staff();
						break;
					case 4:
						return;
					default:
						System.out.println("Please select from 1-5 choices.");
						break;
				}
			}
		}

		catch (SQLException e) {
			System.out.println("Caught SQL Exception: \n     " + e);
		}
	}
}
