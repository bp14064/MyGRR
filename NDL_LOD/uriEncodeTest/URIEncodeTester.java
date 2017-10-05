package uriEncodeTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URIEncodeTester {

	public static void main(String[] args) {
		/*
		 * クエリは改行なしでもいける
		 * PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
		 * SELECT * WHERE {
		 *     ?subj rdfs:label "図書館"
		 * }
		 */
		//String target="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT * WHERE { ?subj rdfs:label \"図書館\" }";
		//String target="PREFIX skos: <http://www.w3.org/2004/02/skos/core#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT * WHERE { rdfs:label “インターネット” ; skos:broader ?broader .  skos:narrower ?narrower .  skos:related ?related .  ?broader rdf:label ?label . ?narrower rdf:label ?label . ?related rdf:label ?label . }";
		String target = "dpid=\"iss-ndl-opac\" AND title=\"こころ\" AND creator=\"夏目漱石\" AND from=\"2011\" AND until=\"2013\"";
		// エンコードの例
		String encodedResult = null;
		try {
			encodedResult = URLEncoder.encode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println("エンコード結果:" + encodedResult);
	}

}
