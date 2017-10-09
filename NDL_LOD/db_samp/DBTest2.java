package db_samp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest2 {

	public static void main(String[] args) {
		String dbDir = "C:\\DB\\hsqldb-2.4.0\\hsqldb\\lib";
		Connection con = null;
		Statement stat = null;

		try {//DBへの接続
        Class.forName("org.hsqldb.jdbcDriver");
        //String url = "jdbc:hsqldb:file:" + dbDir;
        String url = "jdbc:hsqldb:hsql://localhost/";
        String user = "sa";
        String password = "";

        con = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
		}

		try {//クエリの作成と実行
          stat = con.createStatement();

          //ここにINSERT INTOでいけるか？
          stat.execute("INSERT INTO testdb VALUES(1, '978-4-7966-8513-9', '読んでおきたいベスト集!夏目漱石', '夏目漱石', '出版社', '913.6', '606p ; 16cm', 'KH426-J34')");//これで追加できる

		  stat.execute("SELECT * FROM testdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}


        ResultSet rs;
		try {//結果の取得と解析（実際にはここは違うクラスでやった方がいい）
			rs = stat.getResultSet();

			 while (rs.next()) {
		            int id = rs.getInt("ID");
		            String ndc = rs.getString("NDC");
		            System.out.println("ID:"+id + " NDC:"+ ndc);
		        }

		        rs.close();
		        stat.close();
		        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

}
