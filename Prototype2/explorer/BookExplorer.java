package explorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.dom4j.DocumentException;

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
		 ArrayList<ArrayList<ArrayList<String>>> res = null;
		 try {
			String query = cr.createRequest(keyword, "keyword", "non", 10);
			String result = rtr.requestProcess(query);
			ra.createBookDataFile(result);
		} catch (ArgsTypeException | IOException | DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		/* ArrayList<BookData> bdList = new ArrayList<BookData>();
		 for(ArrayList<ArrayList<String>> r : res) {
			 bdList.add(new BookData(r));
		 }
		 System.out.println(bdList.size());
		 System.out.println(bdList.get(0).getTitle());
		 /*for(BookData bd : bdList) {
			 bd.checkBookData();
		 }*/
		/*
		 * スタート本の件名、分類から、関連キーワードを取得(SubjectDataの作成)
		 */

		/*
		 * 得られたデータの表示(とりあえず、CUIでやる)
		 */
	}

}
