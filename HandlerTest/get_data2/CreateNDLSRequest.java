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
	public String createRequest(String[] querySeed, String queryType, int getDataNum) throws ArgsTypeException {
			String query = this.createQuery4Book(querySeed, queryType);
			query = this.URIEncode(query);
			String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=";
			request += query;
			request += "&maximumRecords=" + getDataNum + "&numberOfRecords&recordSchema=dcndl&onlyBib=\"true\"";
			//request += "&maximumRecords=10&recordSchema=dcndl_simple&onlyBib=\"true\"";
			return request;
	}

	/**
	 * リクエスト作成のためのメソッド（件数指定なし、上限はgetDataNum）
	 *
	 * @param querySeed
	 * @param queryType
	 * @return request
	 * @throws ArgsTypeException
	 */
	public String createRequest(String[] querySeed, String queryType) throws ArgsTypeException {
		String query = this.createQuery4Book(querySeed, queryType);
		query = this.URIEncode(query);
		String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=";
		request += query;
		request += "&maximumRecords=" + this.getDataNum + "&numberOfRecords&recordSchema=dcndl&onlyBib=\"true\"";
		//request += "&maximumRecords=10&recordSchema=dcndl_simple&onlyBib=\"true\"";
		return request;
	}

	/*
	 * これは、必要なリクエストに合わせた変更や細分化が必要
	 * ここではリクエスト内容によってCQLを作成している
	 * API仕様書を見て必要なリクエストに必要なCQLは何かを書き出す
	 */
	/**
	 * 入力されたもの（何かはqueryTypeで判定）に対して、CQLクエリを作成する。
	 *
	 * @param querySeed
	 * @param queryType
	 *            検索項目はなにか（キーワード、タイトル、著者名、分類など）を指定
	 * @return クエリ（CQL形式)
	 * @throws ArgsTypeException
	 */
	private String createQuery4Book(String[] querySeed, String queryType) throws ArgsTypeException {
		String query = "dpid=\"iss-ndl-opac\" AND ";
		if (queryType.matches("keyword")) {
			// クエリ：anywhere="キーワード" キーワード検索
			query += "anywhere=\"" + querySeed[0] + "\"";
		} else if (queryType.matches("title")) {
			// クエリ：title="タイトル" タイトルに”タイトル”を含むものを検索
			query += "title=\"" + querySeed[0] + "\"";
		} else if (queryType.matches("creator")) {
			// クエリ：creator exact="著者名" 著者名からの完全一致検索
			query += "creator exact=\"" + querySeed[0] + "\"";
		/*} else if (queryType.matches("ndc")) {
			// クエリ：ndc="NDC" NDCから検索 ※これは試してみないとうまくいくか分からない
			// クエリはこれでいいのかと、NDCの場合文字列形式でいいのか
			//分類検索を行うためのメソッドは変えた方が良さそう
			query += "ndc=\"" + querySeed[0] + "\"";*/
		} else if (queryType.matches("subject")) {//主題(件名)からの検索
			query += "subject=\"" + querySeed[0] + "\"";
		} else if (queryType.matches("AND")) {
			query += this.createQuery4AND(querySeed);
		} else {
			throw new ArgsTypeException("queryTypeに指定されたものが適切ではありません。");
		}
		query += " AND mediatype=1";
		return query;
	}

	/*
	 * このメソッドはやめる
	 * なぜか？
	 * 書架排列では同分類
	 * 他分類の検索結果では０から８まで
	 * 文学作品では指定
	 * と検索する分類を変えることをここでやろうと思っていたが
	 * ハンドラ側で処理するメソッドを作る（3つ分）
	 */
	public String createQuery4CategorySearch(String ndc, String keyword, int getDataNum) {
		String query="";
		String request="http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=";

		query += "ndc=\"" + ndc + "\" AND title=\""+keyword+"\"";
		query = this.URIEncode(query);
		request += query;
		request += "&maximumRecords=" + getDataNum + "&numberOfRecords&recordSchema=dcndl&onlyBib=\"true\"";
		return request;
	}

	/**
	 * AND検索用のクエリを作成するための一部を返すメソッド<br>
	 * anywhere="○○" AND anywhere="○○" ・・・
	 * @param elements
	 * @return 検索項目を指定したもの
	 */
	private String createQuery4AND(String[] elements) {
		//String query = "dpid=\"iss-ndl-opac\" AND ";
		String tmp = "anywhere=\"";
		for(int i=0;i<elements.length;i++) {
			tmp += elements[i];
			if(i != elements.length-1)
				tmp += "\" AND anywhere=\"";
			else
				tmp += "\"";
		}
		//query += tmp;
		//query += " AND mediatype=1";
		return tmp;
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
