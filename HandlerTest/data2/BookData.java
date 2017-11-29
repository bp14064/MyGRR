package data2;

import java.util.ArrayList;

public class BookData extends Data{
	private String ID;
	private boolean isSelected;
	private ArrayList<ArrayList<String>> allData;//これは今後の様子を見て要らないようなら消す
	private ArrayList<String>  mainTitle;
	private ArrayList<String> author;
	private ArrayList<String> ndc;
	private ArrayList<String> series;
	private ArrayList<String> subject;
	private ArrayList<String> publisher;
	private ArrayList<String> subtitle;
	private ArrayList<String> page;
	private ArrayList<String> isbn;
	private ArrayList<String> callNum;
	private ArrayList<String> materialIdentifer; //対象利用者、資料種別のためのメンバ　一般、図書　以外は結果として表示しない
	private ArrayList<String> publishYear;
	private ArrayList<String> imagePath; //画像の保存先のパス

	public BookData(String ID, boolean isSelected) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.ID = ID;
		this.isSelected = isSelected;
	}

	/*
	 * 不完全
	 */
	public BookData(String ID, boolean isSelected, ArrayList<String> mt, ArrayList<String> series, ArrayList<String> author, ArrayList<String> st,
			ArrayList<String> pub, ArrayList<String> pages, ArrayList<String> ndc, ArrayList<String> sub,
			ArrayList<String> isbn, ArrayList<String> cn, ArrayList<String> mi, ArrayList<String> py, ArrayList<String> ip) {
		this.ID = ID;
		this.isSelected = isSelected;
		this.mainTitle = mt;
		this.series = series;
		this.author = author;
		this.subtitle = st;
		this.publisher = pub;
		this.page = pages;
		this.ndc = ndc;
		this.subject = sub;
		this.isbn = isbn;
		this.callNum = cn;
		this.materialIdentifer = mi;
		this.publishYear = py;
		this.imagePath = ip;
	}

	/*
	 * 不完全
	 */
	public void checkBookData() {
		System.out.println("-------------本情報------------");
		for(String s6 : this.mainTitle)
			System.out.println("メインタイトル:" + s6);
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
		for(String s7 : this.isbn)
		    System.out.println("ISBN："+s7);
		for(String s8 : this.callNum)
			System.out.println("請求記号:"+s8);
		for(String s9 : this.page)
			System.out.println("ページ数:"+s9);
		for(String s10 : this.publishYear)
			System.out.println("出版年:"+s10);
		for(String s11 : this.imagePath)
			System.out.println("画像の保存先:"+s11);
		for(String s12 : this.materialIdentifer)
			System.out.println("資料種別情報"+s12);

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

	public boolean isNormalBookData() {
		boolean isbn=false;
		boolean audience=true;
		boolean materialType=false;

		if(!this.isbn.isEmpty()) {
			isbn = true;
		}
		for(String s : this.materialIdentifer) {
			//System.out.println(s);
			if(s.contains("対象利用者")) {
				if(s.contains("児童")) {
					audience = false;
				}
			}
			if(s.contains("資料種別")) {
				if(s.contains("図書")) {
					materialType = true;
				}
			}
		}

		if(isbn&&audience&&materialType)
			return true;
		else
			return false;
	}
	/*-----------------問い合わせ-------------*/


	/*------------------getter----------------*/
	public ArrayList<ArrayList<String>> getAllData(){
		return this.allData;
	}

	public ArrayList<String> getMainTitle() {
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

	public ArrayList<String> getPage() {
		return page;
	}

	public ArrayList<String> getIsbn() {
		return isbn;
	}

	public ArrayList<String> getCallnum() {
		return callNum;
	}
	public ArrayList<String> getSubtitle() {
		return subtitle;
	}

	public ArrayList<String> getMaterialIdentifer() {
		return materialIdentifer;
	}

	public ArrayList<String> getPublishYear() {
		return this.publishYear;
	}

	@Override
	public String getID() {
		// TODO 自動生成されたメソッド・スタブ
		return this.ID;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	/*------------------getter----------------*/


}
