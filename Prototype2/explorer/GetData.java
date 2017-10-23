package explorer;

import java.util.ArrayList;
import java.util.stream.Collectors;

import exception.ArgsTypeException;
import get_data.CreateRequest;
import get_data.RequestTR;
import get_data.ResultAnalyzer3;

public class GetData {

	public GetData() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ArrayList<ArrayList<String>> getSubjectData(String keyword) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer3 ra = new ResultAnalyzer3(); // 結果解析

		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>(); // 結果リスト（返り値）
		ArrayList<String> bro = new ArrayList<String>(); // 上位語を格納するためのリスト
		ArrayList<String> nar = new ArrayList<String>(); // 下位語を格納するためのリスト
		ArrayList<String> rel = new ArrayList<String>(); // 関連語を格納するためのリスト
		ArrayList<String> ndc = new ArrayList<String>(); // 代表分類を格納するためのリスト

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

	public String[] getSubType(ArrayList<ArrayList<String>> check) {
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

	public ArrayList<ArrayList<String>> getSubjectData(ArrayList<ArrayList<String>> result) {
		ArrayList<ArrayList<String>> resNdc = new ArrayList<ArrayList<String>>();
		ArrayList<String> ndc = result.get(3);

		for (String l : ndc) {
			if (l.contains("ndc9")) {
				resNdc.add(this.getNDCData(l));
			}
		}
		return resNdc;
	}

	public ArrayList<String> getNDCData(String target) {
		CreateRequest cr = new CreateRequest(); // リクエスト作成
		RequestTR rtr = new RequestTR(); // リクエスト送受信
		ResultAnalyzer3 ra = new ResultAnalyzer3(); // 結果解析
		ArrayList<String> result = new ArrayList<String>();

		try {
			result = ra.AnalyzeResult(rtr.requestProcess(cr.createRequest(target, "non", "relatedMatch")));
		} catch (ArgsTypeException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<String> getDataList(ArrayList<ArrayList<String>> target) {
		int getDataNum = 3;// ここで入れたいのは上位語、下位語、関連語
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < getDataNum; i++) {
			result.addAll(target.get(i));
		}

		return result;
	}

	public ArrayList<String> getDataList(ArrayList<ArrayList<String>> target, boolean NDC) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> tmp = null;

		for (int i = 0; i < target.size(); i++) {
			tmp = target.get(i);
			result.addAll(tmp);
		}

		return result;
	}

	public ArrayList<String> removeSameWord(ArrayList<String> target, String keyword) {
		ArrayList<String> result = new ArrayList<String>();
		result = (ArrayList<String>) target.stream().distinct().collect(Collectors.toList());

		// キーワードと同じものを削除
		int removenum = 0;
		for (int i = 0; i < result.size(); i++) {
			String tmp = result.get(i);
			if (tmp.matches(keyword)) {
				removenum = i;
				break;
			}
		}
		result.remove(removenum);

		return result;
	}
}
