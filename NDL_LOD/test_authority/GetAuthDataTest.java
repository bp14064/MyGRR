package test_authority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * NDLのLODデータ取得API：APIを使って、典拠データを取得するサンプル
 * ここでは、クエリはマニュアルからそのまま使い、Javaからリクエストを送って結果を受け取れるのかをやってみる
 * 動作確認（9/28）
 * @author Shingo
 *
 */
public class GetAuthDataTest {

	public static void main(String[] args) {
		String request = "http://id.ndl.go.jp/auth/ndla?query=PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0ASELECT+*+WHERE+%7B%0D%0A%09%3Fsubj+rdfs%3Alabel+%22%E5%9B%B3%E6%9B%B8%E9%A4%A8%22%0D%0A%7D%0D%0A&output=xml";
		try {
		      URL url = new URL(request);
		      URLConnection con = url.openConnection();
		      try (BufferedReader reader = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), "UTF-8"))) {
		        while (reader.ready()) {
		          System.out.println(reader.readLine());
		        }
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}

}
