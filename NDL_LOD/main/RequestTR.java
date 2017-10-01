package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * リクエスト作成部（CreateRequest）からリクエストを受け取り、
 * NDLのAPIに渡し、結果を受け取る
 * 結果（String）は、XML解析部（XMLAnalyzer）に渡す
 * @author Shingo
 *
 */
public class RequestTR {
	private String request = null;
	private String result = "";

	public RequestTR(String request) {
		this.request = request;
	}

	public String requestProcess() {
		// リクエストの送信と結果の受信
		try {
			URL url = new URL(this.request);
			URLConnection con = url.openConnection();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
				while (reader.ready()) {
					// System.out.println(reader.readLine());
					this.result += reader.readLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.result;
	}

}
