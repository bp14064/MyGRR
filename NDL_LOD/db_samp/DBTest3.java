package db_samp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest3 {

	public DBTest3() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		try {
		 // JDBC ドライバロード
        Class.forName("org.hsqldb.jdbcDriver");

        // データベースに接続
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\Shingo\\Desktop\\testdb;shutdown=true", "SA", "");

        // テーブル作成
        Statement st = connection.createStatement();
        st.executeUpdate("CREATE TABLE TEST_TABLE (ID INTEGER, VALUE VARCHAR(10))");

        // データ挿入
        st.executeUpdate("INSERT INTO TEST_TABLE VALUES (1, 'HOGE')");
        st.executeUpdate("INSERT INTO TEST_TABLE VALUES (2, 'FUGA')");

        // データ取得
        ResultSet rs = st.executeQuery("SELECT * FROM TEST_TABLE");

        while (rs.next()) {
            System.out.println("ID=" + rs.getInt("ID") + ", VALUE=" + rs.getString("VALUE"));
        }

        st.close();
        connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}


	}

}
