package p1;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import data2.BookData;
import exception.ArgsTypeException;
import get_data2.CreateNDLSRequest;
import get_data2.RequestTR;
import get_data2.ResultAnalyzer3;

public class NDLSAPIHandler extends APIHandler {
	private ArrayList<BookData> bdList;
	private int getDataNum;

	public NDLSAPIHandler() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.bdList = new ArrayList<BookData>();
	}

	/**
	 * 検索から得られた結果（本データ）を取得するためのメソッド
	 * @return
	 */
	public  ArrayList<BookData> getBookData() {
		return this.bdList;
	}

	/**
	 * 本の検索を行うためのメソッド →　このメソッド
	 *
	 * @throws ArgsTypeException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void doBooKSearch(String seed, String searchType) throws ArgsTypeException, IOException, DocumentException {
		/*
		 * リクエスト（何を探したいのか）により変わる
		 * また、結果は本データのリストとしてメンバに保存される
		 */

		/*if(searchType.matches("keyword")) {
		//	this.keywordSearch();
		}else if(searchType.matches("subject")) {
			this.subjectSearch();
		}else if(searchType.matches("category")) {
			this.categorySearch();
		}*/


	}

	/**
	 * キーワード検索を行うためのメソッド
	 *
	 * @param keyword（配列）
	 * @throws ArgsTypeException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void keywordSearch(String[] keyword) throws ArgsTypeException, IOException, DocumentException {
		CreateNDLSRequest req = new CreateNDLSRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer3 ra = new ResultAnalyzer3();

		String query = req.createRequest(keyword, "keyword", 50);
		String result = rtr.requestProcess(query);
		this.getDataNum = ra.createBookDataFile(result);
		for(int i=1;i<this.getDataNum+1;i++) {
			this.bdList.add(ra.createBookData(i));
		}

	}

	/**
	 * AND検索（キーワード検索で）を行うためのメソッド
	 * @throws ArgsTypeException
	 * @throws DocumentException
	 * @throws IOException
	 *
	 */
	public void ANDSearch(String[] keywords) throws ArgsTypeException, IOException, DocumentException {
		CreateNDLSRequest req = new CreateNDLSRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer3 ra = new ResultAnalyzer3();

		String query = req.createRequest(keywords, "AND", 50);
		String result = rtr.requestProcess(query);
		this.getDataNum = ra.createBookDataFile(result);
		for(int i=1;i<this.getDataNum+1;i++) {
			this.bdList.add(ra.createBookData(i));
		}

	}

	/**
	 * 件名検索を行うためのメソッド
	 * @throws ArgsTypeException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void subjectSearch(String[] keywords) throws ArgsTypeException, IOException, DocumentException {
		CreateNDLSRequest req = new CreateNDLSRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer3 ra = new ResultAnalyzer3();

		String query = req.createRequest(keywords, "AND", 50);
		String result = rtr.requestProcess(query);
		this.getDataNum = ra.createBookDataFile(result);
		for(int i=1;i<this.getDataNum+1;i++) {
			this.bdList.add(ra.createBookData(i));
		}

	}

	/**
	 * 分類検索を行うためのメソッド<br>
	 * ・分類検索のタイプ
	 * 　・同分類
	 * 　・
	 * 　・
	 *
	 * @param keyword これはタイトル
	 * @param ndc
	 */
	public void categorySearch(String keyword, String ndc) {
		CreateNDLSRequest req = new CreateNDLSRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer3 ra = new ResultAnalyzer3();



		/*String query = req.createRequest(keywords, "AND", 50);
		String result = rtr.requestProcess(query);
		this.getDataNum = ra.createBookDataFile(result);
		for(int i=1;i<this.getDataNum+1;i++) {
			this.bdList.add(ra.createBookData(i));
		}*/

	}


	/**
	 * DBへデータを格納するためのメソッド
	 */
	private void storeBookData4DB() {

	}

	/**
	 * 追加検索を行うためのメソッド
	 */
	private void additionalSearch() {

	}

	/**
	 * 本データのIDを作るためのメソッド
	 * だが、管理はどうやる？
	 */
	public void makeID() {

	}

}
