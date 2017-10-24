package data;

import java.util.ArrayList;

public class SubjectData {
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

	public SubjectData(String subject, ArrayList<String> ndc) {
		this.subject = subject;
		this.ndc = ndc;
		this.inputNDC(ndc);
	}

	public SubjectData(String subject, ArrayList<String> ndc, ArrayList<String> broader, ArrayList<String> narrower, ArrayList<String> related, ArrayList<String> sameCategorySubject) {
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

	public void showSubjectData() {
		System.out.println("件名 : " + this.subject);
		System.out.print("NDC9: ");
		for(String n9 : this.ndc9) {
			System.out.print(n9 + " / ");
		}
		System.out.print(" NDC10: ");
		for(String n10 : this.ndc10) {
			System.out.print(n10 + " / ");
		}
		System.out.println("");
		if(!this.broader.isEmpty()) {
			System.out.print("上位語:");
			for(String b : this.broader) {
				System.out.print(b + " / ");
			}
			System.out.println("");
		}
		if(!this.narrower.isEmpty()) {
			System.out.print("下位語:");
			for(String n : this.narrower) {
				System.out.print(n + " / ");
			}
			System.out.println("");
		}
		if(!this.related.isEmpty()) {
			System.out.print("関連語:");
			for(String r : this.related) {
				System.out.print(r + " / ");
			}
			System.out.println("");
		}
		if(!this.sameCategorySubject.isEmpty()) {
			System.out.print("同じ代表分類に属する件名:");
			for(String s : this.sameCategorySubject) {
				System.out.print(s + " / ");
			}
			System.out.println("");
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
