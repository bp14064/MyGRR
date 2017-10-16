package kep;

import java.util.ArrayList;
import java.util.Scanner;

import exception.ArgsTypeException;

public class KeywordExplorerProto {

	public KeywordExplorerProto() {

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		KeywordExplorerProto kep = new KeywordExplorerProto();

		// キーワードの入力
		Scanner scan = new Scanner(System.in);
		System.out.print("キーワード：");
		String keyword = scan.nextLine();
		System.out.println("入力キーワード > " + keyword);
		System.out.println("");

		// 一度ASKで取得できるかを見てみる

		// 典拠データを取得
		ArrayList<ArrayList<String>> res = kep.getSubjectData(keyword);
		kep.checkSubjectDataResult(res);

		/*
		 * for (String s : b) System.out.println("上位語：" + s); for (String s : n)
		 * System.out.println("下位語：" + s); for (String s : r) System.out.println("関連語："
		 * + s); for (String s : c) System.out.println("NDC：" + s);
		 */
		// NDCは、この後にNDC部分を取り出す処理を行う リストのリスト？

		// 取った上位語、下位語、関連語でさらに検索をかけ、その分類を取得する

	}

	private ArrayList<ArrayList<String>> getSubjectData(String keyword) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer ra = new ResultAnalyzer(); // 結果解析

		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>(); // 結果リスト（返り値）
		ArrayList<String> bro = new ArrayList<String>(); // 上位語を格納するためのリスト
		ArrayList<String> nar = new ArrayList<String>(); // 下位語を格納するためのリスト
		ArrayList<String> rel = new ArrayList<String>(); // 関連語を格納するためのリスト
		ArrayList<String> ndc = new ArrayList<String>(); // 代表分類を格納するためのリスト

		// データの取得
		try {
			bro = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "broad")));
			nar = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "narrow")));
			rel = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "relate")));
			ndc = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(keyword, "non", "ndc")), true);
		} catch (ArgsTypeException e) {
			e.printStackTrace();
		}
		// 結果リストへの格納
		result.add(bro);
		result.add(nar);
		result.add(rel);
		result.add(ndc);

		return result;
	}

	private void checkSubjectDataResult(ArrayList<ArrayList<String>> result) {
		ArrayList<String> tmp = null;
		String[] subType = null;
		if(result.size() == 4) {
		   subType = new String[4];
		   subType[0] = "上位語";
		   subType[1] = "下位語";
		   subType[2] = "関連語";
		   subType[3] = "代表分類";
		}

		for (int i = 0; i < result.size(); i++) {
			tmp = result.get(i);
			for (String res : tmp) {
				if(result.size() == 4)
					System.out.println(subType[i] + ":" + res);
			}
		}
	}

}
