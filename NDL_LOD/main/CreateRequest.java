package main;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CreateRequest {

	public CreateRequest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String createRequest(String querySeed, String queryType, String getDataType) {
		//queryTypeがNullなら典拠データ、getDataTypeがNullなら書誌データ
		//book
		if(getDataType.equals("non")) {

		}
		//auth
		if(queryType.equals("non")) {
			String query = this.createQuery4Auth(querySeed, getDataType);
			query = this.URIEncode(query);
			String request = "http://id.ndl.go.jp/auth/ndla?query=";
			request+=query;
			request+="&output=xml";
			return request;
		}

		if(getDataType.equals("non")&&queryType.equals("non")) {
			return null;
		}
		return null;
	}

	public String createQuery4Book(String querySeed, String queryType) {
		return null;
	}

	public String createQuery4Auth(String querySeed, String getDataType) {
		String head ="PREFIX skos: <http://www.w3.org/2004/02/skos/core#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT * ?label { ";
		String tail =" }";
		String query ="";
		query+=head;
		String tmp = " ?subj rdfs:label ";
		//上位語broad
		if(getDataType.matches("broad")) {
		  String tmpB1 = tmp + "\"" + querySeed + "\" ;";
		  String tmpB2 =  " skos:broader ?broader . ?broader rdfs:label ?label .";
		  query += tmpB1 + tmpB2;
		}
		//下位語narrow
		else if(getDataType.matches("narrow")) {
		  String tmpN1 = tmp +"\"" +querySeed + "\" ;";
		  String tmpN2 =  " skos:narrower ?narrower . ?narrower rdfs:label ?label .";
	      query += tmpN1 + tmpN2;
		}
		//関連語relate
		else if(getDataType.matches("relate")) {
		  String tmpR1 = tmp + "\"" + querySeed + "\" ;";
		  String tmpR2 =  " skos:related ?related . ?related rdfs:label ?label .";
		  query += tmpR1 + tmpR2;
		}else {
			return null;
		}
		query+=tail;
		System.out.println(query);
		return query;
	}

	public String URIEncode(String target) {
		String encodedResult=null;
		try {
			encodedResult = URLEncoder.encode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return encodedResult;
	}

}
