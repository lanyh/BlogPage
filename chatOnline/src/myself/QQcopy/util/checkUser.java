package myself.QQcopy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class checkUser 
{
	//±‹√‚÷ÿ∏¥◊¢≤·
	public static boolean isExistInMySQL(String name)
	{
		/*boolean isExist = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqcopy?useSSL=true", "root", "*******");
			String sqlDDL = "SELECT username,userpassword FROM userData";
			PreparedStatement ps = conn.prepareStatement(sqlDDL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(1).equals(name))
						isExist = true;
			}
			
			return isExist;
		} catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}	
		return false;*/
		return true;
		//∫„’Ê∑Ω±„≤‚ ‘
	}
	
	public static boolean check(String name, String password)
	{
		/*boolean isExist = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqcopy?useSSL=true", "root", "yklyn1996");
			String sqlDDL = "SELECT username,userpassword FROM userData";
			PreparedStatement ps = conn.prepareStatement(sqlDDL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(1).equals(name))
				{
					if(rs.getString(2).equals(password))
						isExist = true;
				}
			}
			
			return isExist;
		} catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}	
		return false;*/
		return true;
		//∫„’Ê∑Ω±„≤‚ ‘
	}
}
