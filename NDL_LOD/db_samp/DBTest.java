package db_samp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBTest {

	public static void main(String[] args) {
		String dbDir = "C:\\DB\\hsqldb-2.4.0\\hsqldb\\lib";

		try {
        Class.forName("org.hsqldb.jdbcDriver");
        //String url = "jdbc:hsqldb:file:" + dbDir;
        String url = "jdbc:hsqldb:hsql://localhost/";
        String user = "sa";
        String password = "";

        Connection con = DriverManager.getConnection(url, user, password);
        Statement stat = con.createStatement();

        stat.execute("SELECT * FROM sample");
        ResultSet rs = stat.getResultSet();

        while (rs.next()) {
            int id = rs.getInt("ID");

            System.out.println(String.format("ID=%d", id));
        }

        rs.close();
        stat.close();
        con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}


	}

}
