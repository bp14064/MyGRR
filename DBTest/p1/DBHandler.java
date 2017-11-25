package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHandler {
	private Connection connection;
	private Statement st;
	private ResultSet rs;
	private String dbFilePath = "C:\\Users\\AILab08\\git\\mygr\\DBTest\\DBData\\test";

	public DBHandler() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void test() {
		try {
			 // JDBC ドライバロード
	        Class.forName("org.hsqldb.jdbcDriver");

	        // データベースに接続
	        this.connection = DriverManager.getConnection("jdbc:hsqldb:file:"+this.dbFilePath+";shutdown=true", "SA", "");

	        // テーブル作成
	        this.st = connection.createStatement();
	        this.st.executeUpdate("CREATE TABLE KEYWORDDATA (ID INTEGER, TYPE VARCHAR(100), KEYWORD VARCHAR(255), NDC VARCHAR(255), BROADER VARCHAR(255), NARROWER VARCHAR(255), RELATED VARCHAR(255), SAMECATEGORY VARCHAR(255));");

	        // データ挿入
	        //this.st.executeUpdate("INSERT INTO BOOKDATA VALUES (1, 'SELECTED', '魔法の世紀', '○○シリーズ　１', '落合陽一', '○○○○', '○○○○出版社', '2016年', '007.6', '情報科学,コンピューター', '200p', '28919297947927', 'K-487429', 'C:\\ajfiajri\\fajlkjd\\jfaka\\aj')");

	        // データ取得
	        //this.rs = st.executeQuery("SELECT * FROM TEST_TABLE");

	       // while (this.rs.next()) {
	           // System.out.println("ID=" + this.rs.getInt("ID") + ", VALUE=" + this.rs.getString("VALUE"));
	       // }

	        this.st.close();
	        this.connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

}
