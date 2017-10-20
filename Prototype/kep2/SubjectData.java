package kep2;

import java.util.ArrayList;

public class SubjectData {
	private String subject;
	private ArrayList<String> ndc;
	private ArrayList<String> ndc9;
	private ArrayList<String> ndc10;

	public SubjectData(String subject, ArrayList<String> ndc) {
		this.subject = subject;
		this.ndc = ndc;
		this.inputNDC(ndc);
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
		System.out.print("件名 : " + this.subject);
		System.out.print(" : NDC9 ");
		for(String n9 : this.ndc9) {
			System.out.print(n9 + " / ");
		}
		System.out.print(" : NDC10 ");
		for(String n10 : this.ndc10) {
			System.out.print(n10 + " / ");
		}
		System.out.println("");
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

}
