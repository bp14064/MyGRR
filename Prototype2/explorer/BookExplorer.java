package explorer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

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
		GetData gd = new GetData();

		//とりあえず、最初に初期クエリを入力
		String keyword = input();
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
		 String select = input();
		 int sn = Integer.parseInt(select);

		 BookData target = normalBookData.get(sn);
		 target.checkBookData();
		 System.out.println("以下は取得できたキーワード");
		 ArrayList<String> sub = target.getSubject();
		 String key = null;
		 for(String tmp : sub) {
			 key = tmp;
		 }
		 //典拠データの取得
		 ArrayList<ArrayList<String>> result =gd.getSubjectData(key);
		 ArrayList<String> wordList = new ArrayList<String>();
		 wordList.addAll(gd.getDataList(result));
		 wordList.addAll(gd.getDataList(gd.getSubjectData(result), true));

		//普通件名でないものを除く
			Iterator<String> it = wordList.iterator();
	        while(it.hasNext()){
	            String s = it.next();
	            if(!ra.checkTorF(rtr.requestProcess(cr.getCheckIsNormalSubject(s)))) {
	            	it.remove();
	            }
	        }
	        wordList = gd.removeSameWord(wordList, keyword);

	        for(String s : wordList) {
	        	System.out.println(s);
	        }

		/*
		 * 得られたデータの表示(とりあえず、CUIでやる)
		 */
	}

	public static String input() {
		 InputStreamReader isr = new InputStreamReader(System.in);
		 BufferedReader br = new BufferedReader(isr);
	     System.out.print("入力してください   ⇒  ");
	     String str = null;;
		try {
			str = br.readLine();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	     return str;
	}
}
