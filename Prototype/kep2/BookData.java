package kep2;

import java.util.ArrayList;

public class BookData {
	private ArrayList<ArrayList<String>> allData;//これは今後の様子を見て要らないようなら消す
	private ArrayList<String> title;
	private ArrayList<String> author;
	private ArrayList<String> ndc;
	private ArrayList<String> subject;
	private String publisher;
	private String page;
	private String isbn;
	private String callnum;

	/**
	 * BookDataを作成するときに入れるデータ構造は以下の通り
	 * ArrayList <データを表す名前*1> <データ> ・・・
	 * ArrayList<ArrayList<String>> <タイトル> <著者名> ・・・
	 *
	 * *1 データを表す名前
	 * タイトル（サブタイトル含む）: title
	 * 著者名 : author
	 * NDC : ndc
	 * 件名 : subject
	 * 出版社 : publisher
	 * ページ数 : page
	 * ISBN : isbn
	 * 請求記号（国立国会図書館）: callnum
	 * @param data
	 */
	public BookData(ArrayList<ArrayList<String>> data) {
		this.allData = data;
		this.inputData2Member(data); //メンバーへの入力
	}

	public void checkBookData() {
		System.out.println("-------------本情報------------");
		int num = 1;
		for(String s1: this.title) {
			System.out.println(num + ":" + s1);
			num++;
		}
		num=1;
		for(String s2 : this.author) {
			System.out.println(num + ":" + s2);
			num++;
		}
		num=1;
		for(String s3 : this.ndc) {
			System.out.println(num + ":" + s3);
			num++;
		}
		num=1;
		for(String s4 : this.subject) {
			System.out.println(num + ":" + s4);
			num++;
		}
		System.out.println(this.publisher);
		System.out.println(this.page);
		System.out.println(this.isbn);
		System.out.println(this.callnum);
	}

	/*-----------------問い合わせ-------------*/
	public boolean hasSameNdc(String target) {
		for(String ndc : this.ndc) {
			if(ndc.contains(target))
				return true;
		}
		return false;
	}

	public boolean hasSameSubject(String target) {
		for(String subject : this.subject) {
			if(subject.contains(target))
				return true;
		}
		return false;
	}
	/*-----------------問い合わせ-------------*/

	/*------------------getter----------------*/
	public ArrayList<String> getTitle() {
		return title;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public ArrayList<String> getNdc() {
		return ndc;
	}

	public ArrayList<String> getSubject() {
		return subject;
	}

	public String getPage() {
		return page;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getCallnum() {
		return callnum;
	}

	public String getPublisher() {
		return publisher;
	}

	public ArrayList<ArrayList<String>> getAllData(){
		return this.allData;
	}
	/*------------------getter----------------*/

	/*---------------------コンストラクタ用-------------------------*/
	private void inputData2Member(ArrayList<ArrayList<String>> data) {
		ArrayList<String> tmp = null;
		// リストの中から取り出したリストの先頭要素を見る
		for (int i = 0; i < data.size(); i++) {
			tmp = data.get(i);
			if (tmp.get(0).matches("title")) {
				this.title = this.ridHeadDataOfList(tmp);
			} else if (tmp.get(0).matches("author")) {
				this.author = this.ridHeadDataOfList(tmp);
			} else if (tmp.get(0).matches("ndc")) {
				this.ndc = this.ridHeadDataOfList(tmp);
			} else if (tmp.get(0).matches("subject")) {
				this.subject = this.ridHeadDataOfList(tmp);
			}else if(tmp.get(0).matches("publisher")) {
				this.publisher = tmp.get(1);
			} else if (tmp.get(0).matches("page")) {
				this.page = tmp.get(1);
			} else if (tmp.get(0).matches("isbn")) {
				this.isbn = tmp.get(1);
			} else if (tmp.get(0).matches("callnum")) {
				this.callnum = tmp.get(1);
			} else {
				System.out.println("error");
			}
		}
	}

	private ArrayList<String> ridHeadDataOfList(ArrayList<String> target) {
		if (target.size() != 1)
			target.remove(0);
		else
			System.out.println("error");
		return target;
	}
	/*---------------------コンストラクタ用-------------------------*/
}
