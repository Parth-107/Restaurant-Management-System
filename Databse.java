

import java.sql.*;
import java.io.*;

public class Databse {

	static BufferedReader keyboard;
	static Connection conn;
	static Statement stmt;

	// Method to create all the tables
	private static void AllTabels() {
		try {
			// Building statement
			Statement stmt = conn.createStatement();

           //1. Employee (EmpID, EmployeeName, EmployeePhone#, EmployeeEmail, EmployeeSalary)
            String emp = "CREATE TABLE Employee (EmpID NUMBER(9,0) PRIMARY KEY, EmployeeName VARCHAR2(30), EmployeePhone# NUMBER(9,0), EmployeeEmail VARCHAR2(50), EmployeeSalary NUMBER(9,0))";
            //String sql = "DROP TABLE Employee";
            stmt.executeUpdate(emp);
            conn.commit();

            //2. Staff (EmpID, Assigned_Duties)
            String staff = "CREATE TABLE Staff (EmpID NUMBER(9,0) PRIMARY KEY, Assigned_Duties VARCHAR2(50))";
            stmt.executeUpdate(staff);
            conn.commit();

			//3. Manager (EmpID, Type, Manage)
            String Manager = "CREATE TABLE Manager (EmpID NUMBER(9,0) PRIMARY KEY, Type VARCHAR2(50), Manage VARCHAR2(50))";
            stmt.executeUpdate(Manager);
            conn.commit();
            
            //4. Customer (CustomerID, CustomerName, CustomerPhone#, CustomerEmail)
            String Customer = "CREATE TABLE Customer (CustomerID NUMBER(9,0) PRIMARY KEY, CustomerName VARCHAR2(30), CustomerPhone# NUMBER(9,0), CustomerEmail VARCHAR2(50) )";
            stmt.executeUpdate(Customer);
            conn.commit();

            //5. Menu Items (ItemID, DishName, Calorie, Price, Description)
            String menu = "CREATE TABLE Menu_Items (ItemID NUMBER(9,0) PRIMARY KEY, DishName VARCHAR2(30), Calorie NUMBER(9,0), Price NUMBER(6,0), Description VARCHAR2(100) )";
            stmt.executeUpdate(menu);
            conn.commit();

            //6.  Order (OrderID, ItemID, CustomerID, Order Type)
            String Orders = "CREATE TABLE Orders(OrderID NUMBER(9,0) PRIMARY KEY, OrderType VARCHAR2(10), ItemID NUMBER(9,0), CustomerID NUMBER(9,0), FOREIGN KEY (ItemID) REFERENCES Menu_Items,  FOREIGN KEY (CustomerID) REFERENCES Customer )";
            stmt.executeUpdate(Orders);
            conn.commit();

			//7. Inventory(FoodID, ItemID, FoodName, FoodQty)            
			String Inventory = "CREATE TABLE Inventory(FoodID NUMBER(9,0) PRIMARY KEY, FoodName VARCHAR2(30), FoodQty NUMBER(6,0), ItemID NUMBER(9,0), FOREIGN KEY (ItemID) REFERENCES Menu_Items)";
            stmt.executeUpdate(Inventory);
            conn.commit();

			//8. Payroll (PayID, EmpID, Hours of work, date_paid, status)             
			String Payroll = "CREATE TABLE Payroll(PayID NUMBER(9,0) PRIMARY KEY, Hours_worked NUMBER, Date_paid DATE, Status VARCHAR2(30), EmpID NUMBER(9,0), FOREIGN KEY (EmpID) REFERENCES Employee)";
            stmt.executeUpdate(Payroll);
            conn.commit();

            System.out.println("All tables are created successfully in Database....");   
		}

		catch (Exception e) {
			System.out.println("Caught Exception: \n     " + e);
			return;
		}
	}

	//Method to insert all the records into the tables
	private static void Insert() {
		try {
			// Building statement
			Statement stmt = conn.createStatement();
			System.out.println("Inserting records into the table...");

			//1. Employee (EmpID, EmployeeName, EmployeePhone#, EmployeeEmail, EmployeeSalary)
			//First row
			String insert_emp = "INSERT INTO Employee VALUES (101, 'David', 129111222, 'david@gmail.com', 20000)";
			stmt.executeUpdate(insert_emp);
            conn.commit();

			//Second row
			String insert_emp2 = "INSERT INTO Employee VALUES (102, 'Mike', 908362626, 'mike@gmail.com', 15000)";
			stmt.executeUpdate(insert_emp2);
            conn.commit();

			//Third row
			String insert_emp3 = "INSERT INTO Employee VALUES (103, 'Sam', 436251525, 'Sam@gmail.com', 5000)";
			stmt.executeUpdate(insert_emp3);
            conn.commit();

			//Fourth row
			String insert_emp4 = "INSERT INTO Employee VALUES (104, 'Thomas', 384737262, 'thomas@gmail.com', 6000)";
			stmt.executeUpdate(insert_emp4);
            conn.commit();

			//Fifth row
			String insert_emp5 = "INSERT INTO Employee VALUES (105, 'Bob', 454322111, 'bob@gmail.com', 9000)";
			stmt.executeUpdate(insert_emp5);
            conn.commit();

			//2. Staff (EmpID, Assigned_Duties)			
			//First row
			String Staff1 = "INSERT INTO Staff VALUES (103, 'Serving')";
			stmt.executeUpdate(Staff1);
            conn.commit();

			//Second row
			String Staff2 = "INSERT INTO Staff VALUES (104, 'Cleaning')";
			stmt.executeUpdate(Staff2);
            conn.commit();

			//Third row
			String Staff3 = "INSERT INTO Staff VALUES (105, 'Cooking')";
			stmt.executeUpdate(Staff3);
            conn.commit();

			//3. Manager (EmpID, Type, Manage)
			//First row
			String Manager1 = "INSERT INTO Manager VALUES(101, 'Finanace Manager', 'Payroll')";
			stmt.executeUpdate(Manager1);
            conn.commit();

			//Second row
			String Manager2 = "INSERT INTO Manager VALUES (102, 'Product Manager', 'Inventory and Menu Items')";
			stmt.executeUpdate(Manager2);
            conn.commit();

            //4. Customer (CustomerID, CustomerName, CustomerPhone#, CustomerEmail)
			//First row
			String Customer1 = "INSERT INTO Customer VALUES (1, 'ABCD', 1234567, 'abcd@yahooo.com')";
			stmt.executeUpdate(Customer1);
            conn.commit();

			//Second row
			String Customer2 = "INSERT INTO Customer VALUES (2, 'XYZ', 98776362, 'xyz@gmail.com')";
			stmt.executeUpdate(Customer2);
            conn.commit();

			//Third row
			String Customer3 = "INSERT INTO Customer VALUES (3, 'Macy', 08973615, 'abcd@gmail.com')";
			stmt.executeUpdate(Customer3);
            conn.commit();

            //5. Menu Items (ItemID, DishName, Calorie, Price, Description)
			//First row
			String Items1 = "INSERT INTO Menu_Items VALUES (111, 'Pizza', 285, 10, 'Made with cheese and sauce')";
			stmt.executeUpdate(Items1);
            conn.commit();

			//Second row
			String Items2 = "INSERT INTO Menu_Items VALUES (122, 'Pasta', 200, 6, 'Cremmy and saucy')";
			stmt.executeUpdate(Items2);
            conn.commit();

			//Third row
			String Items3 = "INSERT INTO Menu_Items VALUES (133, 'Burger', 100, 4, 'Made with bread')";
			stmt.executeUpdate(Items3);
            conn.commit();

			String Items4 = "INSERT INTO Menu_Items VALUES (144, 'Hot-Dog', 90, 6, 'Made with bread')";
			stmt.executeUpdate(Items4);
            conn.commit();

			String Items5 = "INSERT INTO Menu_Items VALUES (155, 'MilkShake', 40, 3, 'Made with milk')";
			stmt.executeUpdate(Items5);
            conn.commit();

            //6.  Order (OrderID, 'DishName', ItemID, CustomerID)     
			//First row
			String Order1 = "INSERT INTO Orders VALUES (123, 'ABC', 111, 1)";
			stmt.executeUpdate(Order1);
            conn.commit();

			//Second row
			String Order2 = "INSERT INTO Orders VALUES (456, 'XYZ', 122, 2)";
			stmt.executeUpdate(Order2);
            conn.commit();

			//Third row
			String Order3 = "INSERT INTO Orders VALUES (789, 'Quos', 144, 3)";
			stmt.executeUpdate(Order3);
            conn.commit();

			//7. Inventory(FoodID, ItemID, FoodName, FoodQty, )            
			//First row
			String Inventory1 = "INSERT INTO Inventory VALUES (11, 'Bread', 50, 111)";
			stmt.executeUpdate(Inventory1);
            conn.commit();

			//Second row
			String Inventory2 = "INSERT INTO Inventory VALUES (22, 'Cheese', 10, 122)";
			stmt.executeUpdate(Inventory2);
            conn.commit();

			//Third row
			String Inventory3 = "INSERT INTO Inventory VALUES (33, 'Milk', 40, 133)";
			stmt.executeUpdate(Inventory3);
            conn.commit();

			//8. Payroll (PayID, Hours of work, date_paid, status, EmpID)             
			//First row
			String Payroll1 = "INSERT INTO Payroll VALUES (1, 40, TO_DATE('2020-12-09','YYYY-MM-DD'), 'Paid', 103)";
			stmt.executeUpdate(Payroll1);
            conn.commit();

			//Second row
			String Payroll2 = "INSERT INTO Payroll VALUES (2, 60, TO_DATE('2020-12-09','YYYY-MM-DD'), 'Paid', 104)";
			stmt.executeUpdate(Payroll2);
            conn.commit();

			//Third row
			String Payroll3 = "INSERT INTO Payroll VALUES (3, 80, TO_DATE('2020-12-01','YYYY-MM-DD'), 'Paaid', 105)";
			stmt.executeUpdate(Payroll3);
            conn.commit();


			System.out.println("--------------------------------");
			System.out.println("Records inserted successfully....");

		}
		catch (Exception e) {
			System.out.println("Caught Exception: \n     " + e);
			return;
		}
	}

	//Main
	public static void main(String args[]) throws IOException {
		String username = "ORA_pss114", password = "CS470_2178";
		String ename;
		int original_empno = 0;
		int empno;

		keyboard = new BufferedReader(new InputStreamReader(System.in));

		try {

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Registered the driver...");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle2.wiu.edu:1521/orclpdb1", username, password);
			System.out.println("logged into oracle as " + username);
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT TName " + "From Tab");

			while (rset.next()) {
				System.out.println(rset.getString(1));
			}

            AllTabels(); //call table function
			Insert(); //call insert function

			rset.close();
		}

		catch (SQLException e) {
			System.out.println("Caught SQL Exception: \n     " + e);
		}
	}
}

