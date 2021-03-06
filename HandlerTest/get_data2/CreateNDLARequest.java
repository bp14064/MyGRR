package get_data2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import exception.ArgsTypeException;

public class CreateNDLARequest {

	public CreateNDLARequest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 他のクラスからリクエスト作成のために使用するためのメソッド
	 *
	 * @param querySeed
	 *            キーワードまたは検索項目（queryTypeで指定）を入力
	 *            このquerySeedは、書誌データか典拠データのクエリ作成どちらに使うかで入るものが違う。
	 *            書誌データなら、キーワードまたは検索項目。 典拠データなら、キーワードのみ。
	 * @param queryType
	 *            書誌データのクエリ作成時に用いる。検索項目が何なのか（タイトル、著者名、分類など）を指定。
	 * @param getDataType
	 *            典拠データのクエリ作成時に用いる。キーワードの何を取得するか（上位語:broad、下位語:narrow、関連語:relate）
	 * @return リクエスト（入力されたものに問題がなければ、そのまま使える）。指定するものがどちらもnonの場合は、null
	 * @throws ArgsTypeException
	 */
	public String createRequest(String querySeed, String getDataType) throws ArgsTypeException {
			String query = this.createQuery4Auth(querySeed, getDataType);
			query = this.URIEncode(query);
			//System.out.println(query);
			String request = "http://id.ndl.go.jp/auth/ndla?query=";
			request += query;
			request += "&output=xml";
			return request;
	}

	/**
	 * 入力されたキーワードに対して、getDataTypeで指定されたもの（上位語、下位語、関連語）のSPAQLクエリを作成する。
	 *
	 * @param keyword
	 * @param getDataType
	 * @return クエリ（SPAQL形式）
	 * @throws ArgsTypeException
	 */
	private String createQuery4Auth(String keyword, String getDataType) throws ArgsTypeException {
		String head = "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> SELECT * WHERE { ";
		String tail = " }";
		String query = head;
		String tmp = " ?subj rdfs:label ";
		// 上位語broad
		if (getDataType.matches("broad")) {
			String tmpB1 = tmp + "\"" + keyword + "\" ;";
			String tmpB2 = " skos:broader ?broader . ?broader rdfs:label ?label .";
			query += tmpB1 + tmpB2;
		}
		// 下位語narrow
		else if (getDataType.matches("narrow")) {
			String tmpN1 = tmp + "\"" + keyword + "\" ;";
			String tmpN2 = " skos:narrower ?narrower . ?narrower rdfs:label ?label .";
			query += tmpN1 + tmpN2;
		}
		// 関連語relate
		else if (getDataType.matches("relate")) {
			String tmpR1 = tmp + "\"" + keyword + "\" ;";
			String tmpR2 = " skos:related ?related . ?related rdfs:label ?label .";
			query += tmpR1 + tmpR2;
		}
		// NDC分類 ndc　この場合は、headの部分が異なる
		else if(getDataType.matches("ndc")) {
			query = null;
			query = "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> SELECT ?uri2 WHERE { ";
			tmp = null;
			tmp = " ?uri1 rdfs:label ";
			String tmpC1 = tmp + "\"" + keyword + "\" ;";
			String tmpC2 = " skos:relatedMatch ?uri2 . FILTER regex(?uri2, \"ndc\")";
			query += tmpC1 + tmpC2;
		}
		// 代表分類からそれに属する件名を取得
		else if(getDataType.matches("relatedMatch")) {
			tmp = null;
			tmp = " ?subj ";
			String tmpRM1 = tmp + "skos:relatedMatch <http://id.ndl.go.jp/class/";
			String tmpRM2 = keyword + "> ; rdfs:label ?label .";
			query += tmpRM1 + tmpRM2;
		} else {
			throw new ArgsTypeException("getDataTypeに指定されたものが適切ではありません。");
		}
		query += tail;
		//System.out.println(query);
		return query;
	}

	public String getCheckSubjectExist(String target) {
		String query = "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ASK WHERE { ";
		String tmp = null;
		tmp = " ?uri1 rdfs:label ";
		String tmpC1 = tmp + "\"" + target + "\" ;";
		String tmpC2 = " skos:relatedMatch ?uri2 . }";
		query += tmpC1 + tmpC2;
		String request = "http://id.ndl.go.jp/auth/ndla?query=";
		request += this.URIEncode(query);
		request += "&output=xml";
		return request;
	}

	public String getCheckIsNormalSubject(String target) {
		String query = "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ASK WHERE { ";
		query += "?uri1 rdfs:label" + " \"" + target + "\"" + "; skos:inScheme ?uri2. FILTER regex(?uri2, \"auth#topicalTerms\") }";
		String request = "http://id.ndl.go.jp/auth/ndla?query=";
		request += this.URIEncode(query);
		request += "&output=xml";
		return request;
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
