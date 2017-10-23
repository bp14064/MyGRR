package explorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.dom4j.DocumentException;

import data.BookData;
import exception.ArgsTypeException;
import get_data.CreateRequest;
import get_data.RequestTR;
import get_data.ResultAnalyzer3;

public class BookExplorer {

	public BookExplorer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer3 ra = new ResultAnalyzer3(); // 結果解析
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
		 ArrayList<BookData> resultData = new ArrayList<BookData>();
		 int getDataNum = 10;
		 int chunkNum = getDataNum+1;
		 int test = 0;
		 try {
			String query = cr.createRequest(keyword, "keyword", "non", getDataNum);
			String result = rtr.requestProcess(query);
			test = ra.createBookDataFile(result);
			//System.out.println("TEST:"+test);
			for(int i=1;i<chunkNum;i++) {
			resultData.add(ra.createBookData(i));
			}
		} catch (ArgsTypeException | IOException | DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		 ArrayList<BookData> normalBookData = new ArrayList<BookData>();
		 for(BookData bd : resultData) {
			 if(bd.isNormalBookData()) {
				 bd.checkBookData();
				 normalBookData.add(bd);
			 }
		 }
		 if(normalBookData.size() == 0) {
			 System.out.println("対象書籍がない");
			 System.exit(0);
		 }

		/*
		 * スタート本の件名、分類から、関連キーワードを取得(SubjectDataの作成)
		 */
		 scan.close();
		 Scanner scan2 = new Scanner(System.in);
		 System.out.print("選択：");
		 String select = scan2.nextLine();

		/*
		 * 得られたデータの表示(とりあえず、CUIでやる)
		 */
	}
}
