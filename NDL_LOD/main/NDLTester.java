package main;

import exception.ArgsTypeException;

/**
 * リクエスト作成クラス、結果送信受信クラス、XML解析クラスを動かすためのクラス
 * 必要な項目の入力や結果表示程度は、すぐにできそうなのでGUI化してもよいのでは（とりあえず、ほかのところが完成してから）
 * @author Shingo
 *
 */
public class NDLTester {

	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest();
		RequestTR rt;
		XMLAnalyzer xa;
		//クエリの作成に必要な情報を入力
		 String querySeed = "人工知能";
		 String queryType = "title";
		 String getDataType = "non";
		 String targetDataType = "book";
		//リクエスト作成部に
		 String req = null;
		try {
			req = cr.createRequest(querySeed, queryType, getDataType);
		} catch (ArgsTypeException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		System.out.println(req);
		//作成したリクエストを結果送信受信部に
		// rt = new RequestTR(req);
		//XML解析部に
		// xa = new XMLAnalyzer(rt.requestProcess());
		// xa.xmlAnalyze(targetDataType);


		/*
		 * クエリ作成（典拠データのテスト）
		 *
		try {
			System.out.println(cr.createRequest("インターネット", "non", "broad"));
		} catch (ArgsTypeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/


	}

}
