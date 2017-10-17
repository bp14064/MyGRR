package kep;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

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

		// NDCのものは、その代表分類に対応する件名を取得する
		kep.checkSubjectDataResult(res, true);

		ArrayList<String> wordList = new ArrayList<String>();
		wordList.addAll(kep.getDataList(res));
		wordList.addAll(kep.getDataList(kep.getSubjectData(res),true));

		wordList = kep.removeSameWord(wordList, keyword);

		System.out.println("----------------------------------------");
		//取得した全ての語（キーワードを除く）の典拠データを取得
		ArrayList<ArrayList<String>> res2 = null;
		ArrayList<String> wordList2 = new ArrayList<String>();
		wordList2.addAll(wordList);
		for(String next : wordList) {
			res2 = kep.getSubjectData(next);
			//kep.checkSubjectDataResult(res2);
			//kep.checkSubjectDataResult(res2, true);
			wordList2.addAll(kep.getDataList(res2));
			wordList2.addAll(kep.getDataList(kep.getSubjectData(res2),true));
			wordList2 = kep.removeSameWord(wordList2, keyword);
		}

		System.out.println("\n最終的な結果");
		for(String s : wordList2)
			System.out.println(s);

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
		String[] subType = this.getSubType(result);

		for (int i = 0; i < result.size(); i++) {
			tmp = result.get(i);
			for (String res : tmp) {
				if (result.size() == 4)
					System.out.println(subType[i] + ":" + res);
			}
		}
	}

	private String[] getSubType(ArrayList<ArrayList<String>> check) {
		String[] subType = null;
		if (check.size() == 4) {
			subType = new String[4];
			subType[0] = "上位語";
			subType[1] = "下位語";
			subType[2] = "関連語";
			subType[3] = "代表分類";
		}
		return subType;
	}

	private void checkSubjectDataResult(ArrayList<ArrayList<String>> result, boolean relatedMatch) {
		ArrayList<String> tmp = null;
		ArrayList<ArrayList<String>> resNdc = new ArrayList<ArrayList<String>>();
		ArrayList<String> ndc = result.get(3);
		ArrayList<String> ndcLabel = new ArrayList<String>();

		System.out.println("");
		System.out.println("代表分類に対応する件名");
		for (String l : ndc) {
			if (l.contains("ndc9")) {
				ndcLabel.add(l.split("/")[1]);
				resNdc.add(this.getNDCData(l));
			}
		}

		for (int i = 0; i < resNdc.size(); i++) {
			String n = ndcLabel.get(i);
			tmp = resNdc.get(i);
			for (String r : tmp) {
				System.out.println("代表分類 " + n + " : " + r);
			}
		}

	}

	private ArrayList<ArrayList<String>> getSubjectData(ArrayList<ArrayList<String>> result) {
		ArrayList<ArrayList<String>> resNdc = new ArrayList<ArrayList<String>>();
		ArrayList<String> ndc = result.get(3);

		for (String l : ndc) {
			if (l.contains("ndc9")) {
				resNdc.add(this.getNDCData(l));
			}
		}
		return resNdc;
	}

	private ArrayList<String> getNDCData(String target) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer ra = new ResultAnalyzer(); // 結果解析
		ArrayList<String> result = new ArrayList<String>();

		try {
			result = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(target, "non", "relatedMatch")));
		} catch (ArgsTypeException e) {
			e.printStackTrace();
		}

		return result;
	}

	private ArrayList<String> getDataList(ArrayList<ArrayList<String>> target){
		int getDataNum = 3;//ここで入れたいのは上位語、下位語、関連語
		ArrayList<String> result  = new ArrayList<String>();

		for(int i=0; i<getDataNum; i++) {
			result.addAll(target.get(i));
		}

		return result;
	}

	private ArrayList<String> getDataList(ArrayList<ArrayList<String>> target, boolean NDC){
		ArrayList<String> result  = new ArrayList<String>();
		ArrayList<String> tmp = null;

		for(int i=0; i<target.size(); i++) {
			tmp = target.get(i);
			result.addAll(tmp);
		}

		return result;
	}

	private ArrayList<String> removeSameWord(ArrayList<String> target, String keyword){
		ArrayList<String> result = new ArrayList<String>();
		result = (ArrayList<String>) target.stream().distinct().collect(Collectors.toList());

		//キーワードと同じものを削除
		int removenum = 0;
		for(int i=0; i<result.size(); i++) {
			String tmp = result.get(i);
			if(tmp.matches(keyword)) {
				removenum = i;
				break;
			}
		}
		result.remove(removenum);

		return result;
	}

}
