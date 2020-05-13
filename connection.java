package com.dr3adl0cks.sqliteapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {

	public static Connection connection(){
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection("jdbc:sqlite:DATA.sqlite");
			System.out.println();

				System.out.println("connected to : " + connection.getMetaData()+ "!");

		} catch (ClassNotFoundException e) {
			System.out.println(e + " ");;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;


	}

}

