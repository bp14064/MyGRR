package test_book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * NDLのLODデータ取得API：SRUを使って、書誌データを取得するサンプル
 * ここでは、クエリはマニュアルからそのまま使い、Javaからリクエストを送って結果を受け取れるのかをやってみる
 * 動作確認（9/28）
 *
 * XML解析の追加（9/29）
 * ライブラリとして、dom4jとjaxenを追加
 * この２つのライブラリについては、ほかのものでまとめる（出来たら）
 * 参考サイト：http://blog.codebook-10000.com/entry/20130710/1373465211
 *
 * @author Shingo
 *
 */
public class GetBookDataTest {

	public static void main(String[] args) {
		/*
		 * まず、クエリを用意
		 * クエリは、外部提供インタフェース仕様書 附録 3 から
		 */
		String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=title%3d%22%e3%81%93%e3%81%93%e3%82%8d%22%20AND%20creator%3d%22%e5%a4%8f%e7%9b%ae%e6%bc%b1%e7%9f%b3%22%20AND%20from%3d%222011%22%20AND%20until%3d%222013%22&recordSchema=dcndl_simple";

		try {
		      URL url = new URL(request);
		      URLConnection con = url.openConnection();
		      try (BufferedReader reader = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), "UTF-8"))) {
		        /*
		         * これで、readerが読み込んだものをすべて取れる
		         * この後、XMLの解析を行うのだが、参考にしたサイトでは
		         * XMLファイルから読み込んで使っている
		         *
		         * しかし、読み込むためのメソッドがread(java.io.InputStream in)となっているので、うえで
		         * con.getInputSreamをそのまま使えばいいのでは
		         */
		    	/*String xml = "";
		    	while (reader.ready()) {
		          xml += reader.readLine();
		          System.out.println(xml);
		        }*/
		    	  while (reader.ready()) {
		    	    System.out.println(reader.readLine());
		    	  }
		      }


		}catch (Exception e) {
		      e.printStackTrace();
		    }

	}
}
