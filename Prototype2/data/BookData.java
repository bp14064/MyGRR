package data;

import java.util.ArrayList;

public class BookData {
	private ArrayList<ArrayList<String>> allData;//これは今後の様子を見て要らないようなら消す
	private String  mainTitle;
	private ArrayList<String> author;
	private ArrayList<String> ndc;
	private ArrayList<String> series;
	private ArrayList<String> subject;
	private ArrayList<String> publisher;
	private ArrayList<String> subtitle;
	private String page;
	private String isbn;
	private String callnum;


	public BookData(String mainTitle, ArrayList<String> series, ArrayList<String> creator, ArrayList<String> subtitle,
		ArrayList<String> publisher, String pages, String isbn, ArrayList<String> ndc, String callNum, ArrayList<String> subject) {
		this.mainTitle = mainTitle;
		this.author = creator;
		this.series = series;
		this.subject = subject;
		this.publisher = publisher;
		this.ndc = ndc;
		this.page = pages;
		this.isbn = isbn;
		this.callnum = callNum;
		this.subtitle = subtitle;
	}

	public void checkBookData() {
		System.out.println("-------------本情報------------");
		System.out.println("メインタイトル："+this.mainTitle);
		for(String s : this.series)
			System.out.println("シリーズ名:"+s);
		for(String s1 : this.author)
			System.out.println("著作者名"+s1);
		for(String s2 : this.subtitle)
			System.out.println("サブタイトル:"+s2);
		for(String s3 : this.publisher)
			System.out.println("出版社:"+s3);
		for(String s4 : this.ndc)
			System.out.println("分類:"+s4);
		for(String s5 : this.subject)
			System.out.println("件名:"+s5);
		System.out.println("ISBN："+this.isbn);
		System.out.println("請求記号:"+this.callnum);
		System.out.println("ページ数:"+this.page);

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
	public ArrayList<ArrayList<String>> getAllData(){
		return this.allData;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public ArrayList<String> getNdc() {
		return ndc;
	}

	public ArrayList<String> getSeries() {
		return series;
	}

	public ArrayList<String> getSubject() {
		return subject;
	}

	public ArrayList<String> getPublisher() {
		return publisher;
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
	public ArrayList<String> getSubtitle() {
		return subtitle;
	}
	/*------------------getter----------------*/
}
