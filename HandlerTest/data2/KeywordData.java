package data2;

import java.util.ArrayList;

public class KeywordData {
	private String subject;
	//この件名の代表分類
	private ArrayList<String> ndc;
	private ArrayList<String> ndc9;
	private ArrayList<String> ndc10;
	//上位語
	private ArrayList<String> broader;
	//下位語
	private ArrayList<String> narrower;
	//関連語
	private ArrayList<String> related;
	//この件名が持つ代表分類に属する件名
	private ArrayList<String> sameCategorySubject;
	//件名が取得できないときのキーワード
	private String keyword;


	public KeywordData() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public KeywordData(String subject, ArrayList<String> ndc) {
		this.subject = subject;
		this.ndc = ndc;
		this.inputNDC(ndc);
	}

	public KeywordData(String subject, ArrayList<String> ndc, ArrayList<String> broader, ArrayList<String> narrower, ArrayList<String> related, ArrayList<String> sameCategorySubject) {
		this.subject = subject;
		this.ndc = ndc;
		this.inputNDC(ndc);
		this.broader = broader;
		this.narrower = narrower;
		this.related = related;
		this.sameCategorySubject = sameCategorySubject;
	}

	private void inputNDC(ArrayList<String> target) {
		String[] tmp;
		this.ndc9 = new ArrayList<String>();
		this.ndc10 = new ArrayList<String>();
		for(String s : target) {
			if(s.contains("ndc9")) {
				tmp = s.split("/");
				this.ndc9.add(tmp[1]);
			}else if(s.contains("ndc10")) {
				tmp = s.split("/");
				this.ndc10.add(tmp[1]);
			}else {

			}
		}
	}

	public String getSubject() {
		return subject;
	}

	public ArrayList<String> getNdc() {
		return ndc;
	}

	public ArrayList<String> getNdc9() {
		return ndc9;
	}

	public ArrayList<String> getNdc10() {
		return ndc10;
	}

	public ArrayList<String> getBroader() {
		return broader;
	}

	public ArrayList<String> getNarrower() {
		return narrower;
	}

	public ArrayList<String> getRelated() {
		return related;
	}

	public ArrayList<String> getSameCategorySubject() {
		return sameCategorySubject;
	}

}
