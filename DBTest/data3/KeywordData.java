package data3;

import java.util.ArrayList;

public class KeywordData extends Data{
	private final int ID;
	private final String type;
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
	//件名が取得できないときのキーワード 　※　ただし、これが本当に必要かは分からない
	private String keyword;

	//まだ、キーワード（件名の変わり）の場合のコンストラクタは作っていない

	public KeywordData(int ID, String type, String subject, ArrayList<String> ndc) {
		this.ID = ID;
		this.type = type;
		this.subject = subject;
		this.ndc = ndc;
		this.inputNDC(ndc);
	}

	public KeywordData(int ID, String type, String subject, ArrayList<String> ndc, ArrayList<String> broader, ArrayList<String> narrower, ArrayList<String> related, ArrayList<String> sameCategorySubject) {
		this.ID = ID;
		this.type = type;
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

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return this.ID;
	}

	@Override
	public String getType() {
		// TODO 自動生成されたメソッド・スタブ
		return this.type;
	}

}
