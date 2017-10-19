package kep2;

import java.util.Scanner;

import exception.ArgsTypeException;
import kep.CreateRequest;
import kep.RequestTR;
import kep.ResultAnalyzer;

public class BookDataTest {

	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest();
		RequestTR rt = new RequestTR();
		ResultAnalyzer ra = new ResultAnalyzer();
		String result = null;
		BookData bd1;

		//なんでも良いのでとりあえず検索
		Scanner scan = new Scanner(System.in);
		System.out.print("キーワード：");
		String keyword = scan.nextLine();
		System.out.println("入力キーワード > " + keyword);
		System.out.println("");

		try {
			result = cr.createRequest(keyword, "keyword", "non");
			bd1 = new BookData(ra.createBookData(rt.requestProcess(result)));
			bd1.checkBookData();
		} catch (ArgsTypeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
