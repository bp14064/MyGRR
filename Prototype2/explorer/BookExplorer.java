package explorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.dom4j.DocumentException;

import data.BookData;
import exception.ArgsTypeException;
import get_data.CreateRequest;
import get_data.RequestTR;
import get_data.ResultAnalyzer2;

public class BookExplorer {

	public BookExplorer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer2 ra = new ResultAnalyzer2(); // 結果解析
		BookExplorer be = new BookExplorer();

		//とりあえず、最初に初期クエリを入力
		Scanner scan = new Scanner(System.in);
		System.out.print("キーワード：");
		String keyword = scan.nextLine();
		System.out.println("入力キーワード > " + keyword);

		/*
		 * クエリから簡易検索、スタート本を決める（とりあえず、一冊で書籍のものを取得する）
		 * 検索では１０冊ぐらい取得して、その中から図書で一般（雑誌などではなく、また児童書でもない）ものから一冊選ぶ
		 *
		 * 流れとしては
		 * 検索　→　一つ選択？　→BookDataの作成　→スタート本の決定
		 */
		 ArrayList<ArrayList<String>> resultData = null;
		 try {
			String query = cr.createRequest(keyword, "keyword", "non", 10);
			String result = rtr.requestProcess(query);
			ra.createBookDataFile(result);
			resultData = ra.createBookData();
		} catch (ArgsTypeException | IOException | DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		int chunkNum = 1;
		ArrayList<BookData> bd = new ArrayList<BookData>();
		for(;;) {
			if(chunkNum == 11) {
				break;
			}
			bd.add(be.getBookData(resultData, chunkNum));
			chunkNum++;
		}

		for(BookData s : bd) {
			s.checkBookData();
		}

		/*
		 * スタート本の件名、分類から、関連キーワードを取得(SubjectDataの作成)
		 */

		/*
		 * 得られたデータの表示(とりあえず、CUIでやる)
		 */
	}

	private BookData getBookData(ArrayList<ArrayList<String>> target, int chunkNum) {
		String tmp;
		ArrayList<String> mt=null;
		ArrayList<String> isbn=null;
		ArrayList<String> pages=null;
		ArrayList<String> cn=null;
		ArrayList<String> ndc=null;
		ArrayList<String> author=null;
		ArrayList<String> series=null;
		ArrayList<String> st=null;
		ArrayList<String> pub=null;
		ArrayList<String> sub=null;
		for(ArrayList<String> rd : target) {
			tmp = rd.get(0);
			if(tmp.matches("mainTitle")) {
				mt = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("isbn")) {
				isbn = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("pages")) {
				pages = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("callNum")) {
				cn = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("ndc")) {
				ndc = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("author")) {
				author = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("series")) {
				series = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("subtitle")) {
				st = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("publisher")) {
				pub = this.getChunkData(rd, chunkNum);
			}else if(tmp.matches("subject")) {
				sub = this.getChunkData(rd, chunkNum);
			}
		}
		return new BookData(mt, series, author, st, pub, pages, ndc, sub, isbn, cn);
	}

	private ArrayList<String> getChunkData(ArrayList<String> target, int chunkNum){
		ArrayList<String> result = new ArrayList<String>();
		Integer i = new Integer(chunkNum);
		boolean get = false;
		for(String tmp : target) {
			if(get) {
				result.add(tmp);
			}

			if(tmp.contains("chunkstart:" + i.toString())) {
				get = true;
			}

			if(tmp.contains("chunkend:" + i.toString())) {
				get = false;
				break;
			}
		}
		return result;
	}
}
