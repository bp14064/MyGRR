package p1;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataStore {
	private Connection connection;
	private Statement st;
	private ResultSet rs;
	private String dbFilePath = "C:\\Users\\AILab08\\git\\mygr\\DBTest\\DBData\\test";

	public DataStore() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public boolean storeData() {
		//引数のデータセットからまずはDB格納に必要な項目ごとにわける

		//DBへの接続
		try {
			this.connectDB();
		//DBへ保存

		//DBの切断
			this.closeDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return false;
	}

	public boolean storeData4BreadCrumb() {
		return false;
	}

	public boolean storeData4History() {
		return false;
	}

	public boolean storeData4BookShelf() {
		return false;
	}

	public boolean storeData4Corner() {
		return false;
	}

	public int getByteLength(String string) {
		Charset charset = Charset.forName("UTF-8");
	    return string.getBytes(charset).length;
	}

	private void connectDB() throws ClassNotFoundException, SQLException {
		 // JDBC ドライバロード
        Class.forName("org.hsqldb.jdbcDriver");

        // データベースに接続
        this.connection = DriverManager.getConnection("jdbc:hsqldb:file:"+this.dbFilePath+";shutdown=true", "SA", "");
        this.st = connection.createStatement();
	}

	private void closeDB() throws SQLException {
		this.st.close();
        this.connection.close();
	}

}
