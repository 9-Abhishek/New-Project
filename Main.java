package Application;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/details";
			String username ="root";
			String password ="111075";
			Connection con = DriverManager.getConnection(url,username,password);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			detail : while(true) {
			System.out.println("Choose Your Option : \n 1.Insert Detail \n 2.Update Detail \n 3.Delete Detail \n 4.Check Detail");
			PreparedStatement ps;
			int id;
			String name;
			String disease;
			String address;
			
			String c = br.readLine();
			int choose = Integer.parseInt(c);
			
			switch (choose) {
			case 1 : ps = con.prepareStatement("insert into info(name,disease,address) values (?,?,?)");
					 System.out.print("Enter Your Name  : ");
					 name = br.readLine() + "\n";
				
					 System.out.print("Enter Your Disease  : ");
					 disease = br.readLine() + "\n";
				
					 System.out.print("Enter Your Address  : ");
					 address = br.readLine() + "\n";
				
					 ps.setString(1, name);
					 ps.setString(2, disease);
					 ps.setString(3, address);
				
				     ps.executeUpdate();
				     System.out.println("Records Insert \n");
				 break;
			case 2 : System.out.println("Update where: \n 1.Name \n 2.Disease \n 3.Address");
					 String u = br.readLine();	
					 int where =Integer.parseInt(u);
					 ps=null;
				     if(where==1) {
				    	ps=con.prepareStatement("update info set name=? where id=?");
				    	System.out.print("Enter Your Name  : ");
				    	name = br.readLine();
				    	ps.setString(1, name);
				    	System.out.print("Enter ID to Edit : ");	
				    	id = Integer.parseInt(br.readLine());
				    	ps.setInt(2, id);
				    	}
				     else if(where==2) {
					    ps=con.prepareStatement("update info set disease=? where id=?");
					    System.out.print("Enter Your Disease  : ");
					    disease = br.readLine();
					    ps.setString(1, disease);
					    System.out.print("Enter ID to Edit : ");	
				    	id = Integer.parseInt(br.readLine());
				    	ps.setInt(2, id);
				        }
				     else if(where==3) {
					    ps=con.prepareStatement("update info set address=? where id=?");
					    System.out.print("Enter Your Address  : ");
					    address = br.readLine();
					    ps.setString(1, address);
					    System.out.print("Enter ID to Edit : ");	
				    	id = Integer.parseInt(br.readLine());
				    	ps.setInt(2, id);
				        } 
				     	ps.executeUpdate();
				    	System.out.println("Records Update \n");
				 break;
			case 3 : ps=con.prepareStatement("delete from info where id=?");
					 System.out.print("Enter Your Id  : ");
					 int delete = Integer.parseInt(br.readLine()); 
					 ps.setInt(1, delete);    
					 ps.executeUpdate();  
					 System.out.println("Records Deleted");  
				 break;
			case 4 : ps=con.prepareStatement("select * from info");
					 ResultSet rs = ps.executeQuery();
					 System.out.println("The Select query has following results : ");
					 System.out.println("\nId  Name\t Disease\tAddress");
					 while(rs.next()) {						
						System.out.println(rs.getInt("id") + "   "  + rs.getString("name") + "  \t"  + rs.getString("disease") + "  \t " + rs.getString("address")   + "\n");   
					 }
				 break;
			default:
					 System.out.println("Error");
				 break;
			}
			System.out.println("yes to continue OR no to exit ");			
			 String check1 = br.readLine();
			if(check1.equals("yes")) {
				System.out.println("jj");
				 continue detail;
			}
			else {
				con.close();
				System.exit(0);
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
