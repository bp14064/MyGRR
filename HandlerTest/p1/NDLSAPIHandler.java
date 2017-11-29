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
	 * 本の検索を行うためのメソッド
	 * @throws ArgsTypeException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void doBooKSearch(String seed, String searchType) throws ArgsTypeException, IOException, DocumentException {
		/*
		 * リクエスト（何を探したいのか）により変わる
		 * また、結果は本データのリストとしてメンバに保存される
		 */
		CreateNDLSRequest req = new CreateNDLSRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer3 ra = new ResultAnalyzer3();
		if(searchType.matches("keyword")) {
			String query = req.createRequest(seed, "keyword", 50);
			String result = rtr.requestProcess(query);
			this.getDataNum = ra.createBookDataFile(result);
			for(int i=1;i<this.getDataNum+1;i++) {
				this.bdList.add(ra.createBookData(i));
			}
		}

	}


	/**
	 * DBへデータを格納するためのメソッド
	 */
	public void store4DB() {

	}

	/**
	 * 追加検索を行うためのメソッド
	 */
	public void additionalSearch() {

	}

	/**
	 *
	 */
	public void makeID() {

	}

}
