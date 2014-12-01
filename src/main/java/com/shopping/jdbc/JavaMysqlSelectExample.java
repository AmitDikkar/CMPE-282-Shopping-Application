package com.shopping.jdbc;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
public class JavaMysqlSelectExample {

	public List<item> total( int aNumber ) {

		Connection con = null;
		List<item> datalist = new ArrayList<item>();
		try {
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds 
			= new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName("cmpe275.ciupcmtzesph.us-west-1.rds.amazonaws.com");
			ds.setPortNumber(3306);
			ds.setDatabaseName("test262");
			ds.setUser("root");
			ds.setPassword("password");
			con = ds.getConnection();

			// PreparedStatement for SELECT statement with one parameter
			PreparedStatement sta = con.prepareStatement(
					"SELECT * FROM temp WHERE item = ?");

			// Provide a value to the parameter
			int id = 1176;
			sta.setInt(1,aNumber);

			// Execute the PreparedStatement as a query
			ResultSet res = sta.executeQuery();

			// Get values out of the ResultSet

			while (res.next()){
				//   res.next();
				String item = res.getString("item");
				String track = res.getString("track");
				String rating = res.getString("rating");
				String algo = res.getString("algo");

				item newitem = new item(item,track, rating, algo);
				datalist.add( newitem );

				System.out.println(item +"\t"+ track+"\t"+rating+"\t"+algo);
			}

			// Close ResultSet and PreparedStatement
			res.close();
			sta.close();
			con.close();   
			for (item item : datalist) {
				System.out.println("item.item : " + item.item);
			}
			
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			e.printStackTrace();
		}
		return datalist;
	}



}