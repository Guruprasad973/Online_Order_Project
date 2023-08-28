import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Order_System 
{
	public static void Home()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your option \n1.Admin\n2.User");
		String  s = Scan.nextLine();	
		switch(s)
		{
		case "1":
			Admin.admin();
			break;
		case "2":
			User.user();
			break;
		case "3":
			default:
			System.out.println("Your have enter a wrong input please try again!");
			Order_System.Home();
			break;
		}
	}
	public static void main(String[] args)
	{
		Admin adm = new Admin();
		User usr = new User();
		Home();
	}
}
class Admin
{
	public static void admin()
	{
		Admin adm = new Admin();
		System.out.println("Enter your option\n1.addmenu\n2.deleteMenu\n3.viewMenu\n4.addLocation\n5.deleteLocation\n6.viewLocation\n7.viewUsers\n8.back to main menu");
		Scanner Scan = new Scanner(System.in);
		String s = Scan.nextLine();
		switch(s)
		{
		case"1":
			adm.addMenu();
			break;
		case "2":
			adm.deleteMenu();
			break;
		case "3":
			adm.viewMenu();
			break;
		case "4":
			adm.addLocation();
			break;
		case "5":
			adm.deleteLocation();
			break;
		case "6":
			adm.viewLocation();
			break;
		case "7":
			adm.viewUsers();
			break;
		case "8":
			default:
			System.out.println("Your have enter a wrong input please try again!");
			admin();
			break;
		}
	}
	public static void addMenu()
	{
		System.out.println("Enter your menu Name");
		Scanner Scan = new Scanner(System.in);
		String menuName = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/Online_food_Ordering_System";
		String user ="root";
		String password = "Guru@1997";
		
		String query = "insert into menu values(?)";
		
		boolean bool = searchMenuName(menuName);
		if(bool == true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1,menuName);
				pst.executeUpdate();
				System.out.println("You have successfully added new menu");
				admin();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Menu name is already Exist ! try with another menu name");
			admin();
		}
	}
	public static boolean searchMenuName(String menuName)
	{
		String url = "jdbc:mysql://localhost:3306/Online_food_Ordering_System";
		String user  = "root";
		String password = "Guru@1997";
		String query = "select menuname from menu where menuname = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,menuName);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static void deleteMenu()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Eneter your MenuName");
		String menuName = Scan.nextLine();
		String url = "jdbc:mysql://localhost:3306/online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "delete from menu where menuname = ?";
		boolean bool = deleteMenuSearchName(menuName);
		if(bool==true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1,menuName);
				pst.executeUpdate();
				System.out.println("Deleted successfully");
				admin();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("You have Entered wrong menuname please enter valid input and try again!");
			admin();
		}
	}
	public static boolean deleteMenuSearchName(String menuName)
	{
		String url = "jdbc:mysql://localhost:3306/online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query  = "select menuname from menu where menuname = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,menuName);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void viewMenu()
	{
		String url = "jdbc:mysql://localhost:3306/online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "select * from menu";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				++i;
				System.out.println(i+"."+rs.getString("menuname"));
			}
			admin();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void addLocation()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your new location");
		String location = Scan.nextLine();
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "insert into location values(?)";
		boolean bool = searchLocation(location);
		if(bool==true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, location);
				pst.executeUpdate();
				System.out.println("you have succefully added a new loaction");
				admin();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("You try to entering a new location is already in exist!  please enter different location");
			admin();
		}
	}
	public static boolean searchLocation(String location)
	{
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "select location from location where location=?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, location);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void deleteLocation()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your location");
		String location = Scan.nextLine();
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "delete location from location where location = ?";
		boolean bool = deletesearchLocation(location);
		if(bool==true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, location);
				pst.executeUpdate();
				System.out.println("you have succefully deleted your loaction");
				admin();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("You have entered a wrong location please enter valid location and try again!");
			admin();
		}
	}
	public static boolean deletesearchLocation(String location)
	{
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "select location from location where location=?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, location);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void viewLocation()
	{
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "select location from location";
		
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int a = 0;
			while(rs.next())
			{
				++a;
				System.out.println(a+"."+rs.getString("location"));
			}
			admin();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void viewUsers()
	{
		String url = "jdbc:mysql://localhost:3306/online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "select * from user";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				++i;
				System.out.println(i+"."+rs.getString("username"));
			}
			admin();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
class User
{
	public static void user()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your option \n1.NewUser?\n2.LogIn");
		String  s = Scan.nextLine();
		
		switch(s)
		{
		case "1":
			signin();
			break;
		case "2":
			login();
			break;
		case "3":
			default:
				System.out.println("You have entered wrong input please try again!");
				user();
			break;
		}
	}
	public static void signin()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your name");
		String username = Scan.nextLine();
		System.out.println("Enter your password");
		String userpassword = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/Online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "insert into user values(?,?)";
		
		boolean bool = searchUserName(username);
		if(bool==true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, username);
				pst.setString(2, userpassword);
				pst.executeUpdate();
				System.out.println("You have succefully created your account");
				
				System.out.println("you want to login\n1.Press-1\n2.Main menu");
				int a = Scan.nextInt();
				switch(a)
				{
				case 1:
					login();
					break;
				case 2:
					user();
					break;
				case 3:
					default:
						System.out.println("username already exist please eneter different username and try again!");
						signin();
					break;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Username already Exist please try again with another username!");
			signin();
		}
	}
	public static boolean searchUserName(String username)
	{
		String url = "jdbc:mysql://localhost:3306/Online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "select * from user where username =?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void login()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Eneter your username");
		String username = Scan.nextLine();
		System.out.println("Eneter your password");
		String userpassword = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "select * from user where (username,password)=(?,?)";
		
		boolean bool = searchUserName(username,userpassword);
		if(bool==true)
		{
			System.out.println("Enter your option \n1.Restaurant location \n2.List of menu names");
			String s = Scan.nextLine();
			switch(s)
			{
			case "1":
				RestaurantLocation();
				break;
			case "2":
				ListOfMenu();
				break;
			case "3":
				default:
					System.out.println("You have enter a wrong input please try agian with valid input!");
					login();
				break;
			}
		}
		else
		{
			System.out.println("You have entered wrong username or password please try again with vaild username and password!");
			signin();
		}
	}
	public static boolean searchUserName(String username,String usernamepassword)
	{
		String url = "jdbc:mysql://localhost:3306/Online_food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		String query = "select * from user where (username,password) =(?,?)";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, usernamepassword);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else
			{
				System.out.println("You have Entered wrong username please enter valid username and try again!");
				login();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void RestaurantLocation()
	{
		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "select * from location";
		
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int a = 0;
			while(rs.next())
			{
				++a;
				System.out.println(a+"."+rs.getString("location"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void ListOfMenu()
	{

		String url  ="jdbc:mysql://localhost:3306/Online_Food_Ordering_System";
		String user = "root";
		String password = "Guru@1997";
		
		String query = "select * from menu";
		
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int a = 0;
			while(rs.next())
			{
				++a;
				System.out.println(a+"."+rs.getString("menuname"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}