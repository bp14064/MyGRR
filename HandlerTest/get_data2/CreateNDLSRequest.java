package get_data2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import exception.ArgsTypeException;

public class CreateNDLSRequest {

	private final int getDataNum=50;

	public CreateNDLSRequest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/*
	 * 件名の取得とは分けているので、getDataType, queryTypeでの識別が不要になる
	 */
	/**
	 * 他のクラスからリクエスト作成のために使用するためのメソッド
	 *
	 * @param querySeed
	 *            キーワードまたは検索項目（queryTypeで指定）を入力
	 * @param queryType
	 *            書誌データのクエリ作成時に用いる。検索項目が何なのか（タイトル、著者名、分類など）を指定。
	 * @return リクエスト（入力されたものに問題がなければ、そのまま使える）。指定するものがどちらもnonの場合は、null
	 * @throws ArgsTypeException
	 */
	public String createRequest(String querySeed, String queryType, int getDataNum) throws ArgsTypeException {
			String query = this.createQuery4Book(querySeed, queryType);
			query = this.URIEncode(query);
			String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=";
			request += query;
			request += "&maximumRecords=" + getDataNum + "&numberOfRecords&recordSchema=dcndl&onlyBib=\"true\"";
			//request += "&maximumRecords=10&recordSchema=dcndl_simple&onlyBib=\"true\"";
			return request;
	}

	public String createRequest(String querySeed, String queryType) throws ArgsTypeException {
		String query = this.createQuery4Book(querySeed, queryType);
		query = this.URIEncode(query);
		String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=";
		request += query;
		request += "&maximumRecords=" + this.getDataNum + "&numberOfRecords&recordSchema=dcndl&onlyBib=\"true\"";
		//request += "&maximumRecords=10&recordSchema=dcndl_simple&onlyBib=\"true\"";
		return request;
}

	/**
	 * 入力されたもの（何かはqueryTypeで判定）に対して、CQLクエリを作成する。
	 *
	 * @param querySeed
	 * @param queryType
	 *            検索項目はなにか（キーワード、タイトル、著者名、分類など）を指定
	 * @return クエリ（CQL形式)
	 * @throws ArgsTypeException
	 */
	private String createQuery4Book(String querySeed, String queryType) throws ArgsTypeException {
		String query = "dpid=\"iss-ndl-opac\" AND ";
		if (queryType.matches("keyword")) {
			// クエリ：anywhere="キーワード" キーワード検索
			query += "anywhere=\"" + querySeed + "\"";

		} else if (queryType.matches("title")) {
			// クエリ：title="タイトル" タイトルに”タイトル”を含むものを検索
			query += "title=\"" + querySeed + "\"";

		} else if (queryType.matches("creator")) {
			// クエリ：creator exact="著者名" 著者名の完全一致検索
			query += "creator exact=\"" + querySeed + "\"";

		} else if (queryType.matches("ndc")) {
			// クエリ：ndc="NDC" NDCから検索 ※これは試してみないとうまくいくか分からない
			// クエリはこれでいいのかと、NDCの場合文字列形式でいいのか
			query += "ndc=\"" + querySeed + "\"";

		} else if(queryType.matches("subject")) {//主題(件名)の検索
			query += "subject=\"" + querySeed + "\"";
		} else {
			throw new ArgsTypeException("queryTypeに指定されたものが適切ではありません。");
		}
		query += " AND mediatype=1";
		return query;

	}

	/**
	 * 入力された文字列をURIエンコードする ただし、Java標準のAPIを使っているため、Shift-JISだとうまくエンコードできない可能性あり。
	 *
	 * @param target
	 * @return 入力文字列をURIエンコードしたもの
	 */
	private String URIEncode(String target) {
		String encodedResult = null;

		try {
			encodedResult = URLEncoder.encode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return encodedResult;
	}

}
