/**
 * 
 */
package com.shopping.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;
import com.shopping.domains.users.LoginForm;
import com.shopping.domains.users.RegistrationForm;
import com.shopping.dto.LayoutData;
import com.shopping.dto.User;

/**
 * @author Amit
 *
 */
public class UserTable {

	Connection conn = null;
	Statement statment = null;
	
	String TABLE_NAME = "mydb.Users";
	
	public UserTable(){
		conn = new RdsConnection().getRdsConnection();
	}

	public void insertUserRecord(User userDetails) {
		// STEP 4: Execute a query
		System.out.println("Inserting records into the table...");
		try {
			statment = conn.createStatement();
			String sqlStatement =	"insert into " + TABLE_NAME + " " + 
									"(FirstName, LastName, EmailId, Password) " +
									"values(?, ?, ?, ?);";
			System.out.println("Created statement as: " + sqlStatement );
			java.sql.PreparedStatement insertStatement = conn.prepareStatement(sqlStatement);
			insertStatement.setString(1, userDetails.getInputFirstName());
			insertStatement.setString(2, userDetails.getInputLastName());
			insertStatement.setString(3, userDetails.getInputEmail());
			insertStatement.setString(4, userDetails.getInputPassword());
			System.out.println("Statement Created..");
			insertStatement.executeUpdate();
			System.out.println("Insert Statement Executed..");
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(statment!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
	
	
	/**
	 * Authenticate the user based on email id and password. 
	 * If authenticated successfully, return the userId which is auto stored in the RDS table.
	 * @param loginDetails
	 * @return
	 */
	public LayoutData isAuthentic(LoginForm loginDetails){
		
		System.out.println("Creating Select statement");
		LayoutData data = new LayoutData();
		try {
			statment = conn.createStatement();
			
			String sql = "SELECT * from "+ TABLE_NAME + " where " + "EmailId=? and Password=?;";
			java.sql.PreparedStatement selectStatement = conn.prepareStatement(sql);
			
			selectStatement.setString(1, loginDetails.getInputEmail());
			selectStatement.setString(2, loginDetails.getInputPassword());
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				System.out.println("Data Retrieve: " + rs.getString(1));
				System.out.println("Data Retrieve: " + rs.getString(2));
				//at column 1 in the 'Users' table is 'UserId'. That's why will return that value.
				data.setUserId(rs.getInt(1));
				data.setUserName(rs.getString(2));
				return data;
			}
			//if result set is empty.
			if (rs.isBeforeFirst()) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
