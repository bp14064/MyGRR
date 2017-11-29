package p1;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import data2.BookData;
import exception.ArgsTypeException;

public class TestAPI {

	public TestAPI() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*BCDAPIHandler api = new BCDAPIHandler();
		String isbn = "9784309226712";
		String s = api.getBCData(isbn);
		System.out.println(s);*/
		NDLSAPIHandler ndls = new NDLSAPIHandler();
		final String KEYWORD = "keyword";
		try {
			ndls.doBooKSearch("人工知能", KEYWORD);
			ArrayList<BookData> list =ndls.getBookData();
			for(BookData bd : list) {
				bd.checkBookData();
			}
		} catch (ArgsTypeException | IOException | DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public void makeBookData() {
		//まずは、書誌情報をNDLSAPIから取得
		//このとき、isSelectedはfalse,imagePathはNULLになっている
		//次に、ISBNを用いて書影画像を取得

		//imagePathをBookDataに設定しBookDataの完成
	}

}
