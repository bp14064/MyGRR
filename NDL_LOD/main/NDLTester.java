package main;

import exception.ArgsTypeException;

/**
 * リクエスト作成クラス、結果送信受信クラス、XML解析クラスを動かすためのクラス
 * @author Shingo
 *
 */
public class NDLTester {

	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest();
		//クエリのシードを入力

		//リクエスト作成部に

		//作成したリクエストを結果送信受信部に

		//XMLファイルの作成

		//XML解析部に

		/*
		 * クエリ作成（典拠データのテスト）
		 */
		try {
			System.out.println(cr.createRequest("インターネット", "non", "broad"));
		} catch (ArgsTypeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

}
