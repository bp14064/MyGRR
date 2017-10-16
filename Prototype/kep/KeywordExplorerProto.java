package kep;

import java.util.ArrayList;
import java.util.Scanner;

import exception.ArgsTypeException;

public class KeywordExplorerProto {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		CreateRequest cr = new CreateRequest();
		RequestTR rtr = new RequestTR();
		ResultAnalyzer ra = new ResultAnalyzer();
		// キーワードの入力
		Scanner scan = new Scanner(System.in);
		System.out.print("キーワード：");
		String keyword = scan.nextLine();
		System.out.println(keyword);
		// 一度ASKで取得できるかを見てみる

		// 上位語、下位語、関連語を取得
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> n = new ArrayList<String>();
		ArrayList<String> r = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		try {
			b = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "broad")));
			n = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "narrow")));
			r = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "relate")));
			c = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "ndc")), true);
		} catch (ArgsTypeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		for (String s : b)
			System.out.println("上位語：" + s);
		for (String s : n)
			System.out.println("下位語：" + s);
		for (String s : r)
			System.out.println("関連語：" + s);
		for (String s : c)
			System.out.println("NDC：" + s);
		// NDCは、この後にNDC部分を取り出す処理を行う リストのリスト？

				//取った上位語、下位語、関連語でさらに検索をかけ、その分類を取得する

	}

}
