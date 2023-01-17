package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String dbUrl = "jdbc:sqlite:people.db";
		var conn = DriverManager.getConnection(dbUrl);

		var stmt = conn.createStatement();
		var sql = "create table if not exists user (id integer primary key, name text not null)";
		stmt.execute(sql);
		
		sql = "insert into user (id, name) values (0, 'Bob')";
		stmt.execute(sql);
		
		sql = "insert into user (id, name) values (1, 'Mary')";
		stmt.execute(sql);
		
		sql = "select id, name from user";
		var rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			System.out.println(id + ": " + name);
		}
		
		sql = "drop table user";
		stmt.execute(sql);
		stmt.close();
		conn.close();

	}

}
