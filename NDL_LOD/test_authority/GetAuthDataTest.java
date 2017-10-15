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
		//複数のクエリ混ぜのテスト//PREFIX+skos%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2004%2F02%2Fskos%2Fcore%23%3E+PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+WHERE+%7B+rdfs%3Alabel+%E2%80%9C%E3%82%A4%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%8D%E3%83%83%E3%83%88%E2%80%9D+%3B+skos%3Abroader+%3Fbroader+.++skos%3Anarrower+%3Fnarrower+.++skos%3Arelated+%3Frelated+.++%3Fbroader+rdf%3Alabel+%3Flabel+.+%3Fnarrower+rdf%3Alabel+%3Flabel+.+%3Frelated+rdf%3Alabel+%3Flabel+.+%7D
		//改行なしのテスト//PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+WHERE+%7B+%3Fsubj+rdfs%3Alabel+%22%E5%9B%B3%E6%9B%B8%E9%A4%A8%22+%7D
		//String request = "http://id.ndl.go.jp/auth/ndla?query=PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0ASELECT+*+WHERE+%7B%0D%0A%09%3Fsubj+rdfs%3Alabel+%22%E5%9B%B3%E6%9B%B8%E9%A4%A8%22%0D%0A%7D%0D%0A&output=xml";
		//String request = "http://id.ndl.go.jp/auth/ndla?query=PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+WHERE+%7B+%3Fsubj+rdfs%3Alabel+%22%E5%9B%B3%E6%9B%B8%E9%A4%A8%22+%7D&output=xml";
		//String request = "http://id.ndl.go.jp/auth/ndla?query=PREFIX+skos%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2004%2F02%2Fskos%2Fcore%23%3E+PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+WHERE+%7B+rdfs%3Alabel+%E2%80%9C%E3%82%A4%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%8D%E3%83%83%E3%83%88%E2%80%9D+%3B+skos%3Abroader+%3Fbroader+.++skos%3Anarrower+%3Fnarrower+.++skos%3Arelated+%3Frelated+.++%3Fbroader+rdf%3Alabel+%3Flabel+.+%3Fnarrower+rdf%3Alabel+%3Flabel+.+%3Frelated+rdf%3Alabel+%3Flabel+.+%7D&output=xml";
		//String request ="http://id.ndl.go.jp/auth/ndla?query=PREFIX+skos%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2004%2F02%2Fskos%2Fcore%23%3E+PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+WHERE+%7B++%3Fsubj+rdfs%3Alabel+%22%E3%82%A4%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%8D%E3%83%83%E3%83%88%22+%3B+skos%3Abroader+%3Fbroader+.+%3Fbroader+rdfs%3Alabel+%3Flabel+.+%7D&output=xml";
		String request = "http://id.ndl.go.jp/auth/ndla?query=PREFIX+skos%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2004%2F02%2Fskos%2Fcore%23%3E+PREFIX+rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E+SELECT+*+%3Flabel+%7B++%3Fsubj+rdfs%3Alabel+%22%E4%BA%BA%E5%B7%A5%E7%9F%A5%E8%83%BD%22+%3B+skos%3Abroader+%3Fbroader+.+%3Fbroader+rdfs%3Alabel+%3Flabel+.+%7D&output=xml";
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
