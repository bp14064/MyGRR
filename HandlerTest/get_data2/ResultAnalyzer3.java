package get_data2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import data2.BookData;
import exception.ArgsTypeException;

public class ResultAnalyzer3 {
	/*
	 * ここでファイルパスを管理する
	 */
	private String tmpFilePath;
	private String txtFilePath;
	private String xmlFilePath;
	private String bookdataFilePath;



	public ResultAnalyzer3() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 典拠データの解析用（上位語、下位語、関連語）
	 * @param data
	 * @return 上位語、下位語、関連語
	 */
	public ArrayList<String> AnalyzeResult(String data) {
		String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		//String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		ArrayList<String> result = new ArrayList<String>();
		try {
			// 一時保存用ファイルの作成
			File filetmp = new File(tmpFilePath);
			// 一時保存ファイルに書き出し
			BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File recordsFile = new File(tmpFilePath);
		FileReader fr = null;
		try {
			fr = new FileReader(recordsFile);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		String cmp = "";
		try {
			while ((cmp = br.readLine()) != null) {
				if (cmp.contains("literal")) {
					result.add(this.formatData(cmp));
				}
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//for(String s:result) {
		//	System.out.println(s);
		//}

		return result;

	}

	/**
	 * ある件名の代表分類を取得
	 * @param data
	 * @param NDC
	 * @return 件名の代表分類
	 * @throws ArgsTypeException
	 */
	public ArrayList<String> AnalyzeResult(String data, boolean NDC) throws ArgsTypeException {
		if (NDC) {
			String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
			//String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
			ArrayList<String> result = new ArrayList<String>();
			try {
				File filetmp = new File(tmpFilePath);
				BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
				bw.write(data);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			File recordsFile = new File(tmpFilePath);
			FileReader fr = null;
			try {
				fr = new FileReader(recordsFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr);
			String cmp = "";
			try {
				while ((cmp = br.readLine()) != null) {
					if (cmp.contains("uri") && cmp.contains("http")) {
						result.add(this.formatData(cmp, NDC));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			//for(String s:result) {
			//	System.out.println(s);
			//}
			return result;
		} else {
			throw new ArgsTypeException("引数エラー：NDCフラグがFALSE");
		}
	}

	/**
	 * 書誌データから必要な項目を抜き出し、それらの結果をまとめ、テキストファイルに書き出す
	 * ここで、返り値として何個データがあるのか（chunkNum）を返したい
	 * @param data
	 * @return
	 * @return 何個データがあったか
	 * @throws IOException
	 * @throws DocumentException
	 */
	public int createBookDataFile(String data) throws IOException, DocumentException {
		String txtFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.txt";
		String xmlFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml";
		//String txtFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype2\\get_data\\tmp.txt";
		//SString xmlFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype2\\get_data\\tmp.xml";

		//一度結果のXMLから最低限必要なところを抜き出したいため、一度XMLファイルとして保管 //もしかしたら今後はこの段階でテキスト処理を行ってもよいかもしれない
		File filetmp = new File(xmlFilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
		bw.write(data);
		bw.close();
		File file = new File(xmlFilePath);
		SAXReader reader = new SAXReader();
		String records = "";

			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			Element element = null;
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				element = (Element) i.next();
				if (element.getName().matches("records")) {
					records = element.getStringValue();
					break;
				}
			}
			//recordsをテキストファイルに一回保存
			filetmp = new File(txtFilePath);
			FileWriter fw = new FileWriter(filetmp);
			bw = new BufferedWriter(fw);
			bw.write(records);
			bw.close();
			fw.close();



			//テキストファイルから一行ずつ読み込んでいき、ゴリ押しのテキスト処理を行う
			File recordsFile = new File(txtFilePath);
			FileReader fr = new FileReader(recordsFile);
			BufferedReader br = new BufferedReader(fr);

			boolean ndlch = false; //国立国会図書館の請求記号だけ抜き出すためのフラグ
			boolean chunkch = false; //チャンク（複数のデータがあったときのそれぞれの塊）のためのフラグ
			//boolean mainTitleCh = false; //メインタイトルとサブタイトルを識別するためのフラグ
			int chunknum = 1; //タイトル識別用
			boolean creatorch = false;
			int creatornum = 1;
			boolean publisherch = false;
			boolean maintitle = false;
			boolean subjectch = false;
			boolean series = false;
			boolean subtitle = false;
			File bookDataFile = new File("C:\\Users\\AILab08\\git\\MyGRR\\Prototype2\\get_data\\bookdata.txt");
			FileWriter fw2 = new FileWriter(bookDataFile);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			int subtitlenum = 1;
			int publishernum = 1;
			int subtitlecreatornum = 1;
		/*
		 * テキストファイルに保存
		 */
		String cmp = "";
		while ((cmp = br.readLine()) != null) {
			//System.out.println(cmp);

			if (chunkch == false && cmp.contains("rdf:RDF xmlns")) {//データチャンクの開始処理
				chunkch = true;
				//System.out.println("データ番号：" + chunknum + "開始");
				bw2.write("データ番号：" + chunknum + "開始\n");
			}

			if (chunkch == true && cmp.contains("/rdf:RDF")) {//データチャンクの終了処理
				chunkch = false;
				//System.out.println("データ番号:" + chunknum + "終了");
			    bw2.write("データ番号:" + chunknum + "終了\n");
				chunknum++;
				//新しくフラグを作ったら、ここでリセットしないと
				subtitlenum=1;
				subtitlecreatornum=1;
				creatornum=1;
				series = false;
				subtitle = false;
				creatorch = false;
				maintitle = false;
				subjectch = false;
			}

			if (cmp.contains("dcterms:title") && !maintitle) {//タイトル
					//System.out.println("メインタイトル:" + formatData(cmp));
					bw2.write("メインタイトル:" +formatData(cmp) + "\n");
					maintitle = true;
			}

			if(cmp.contains("dcndl:partInformation") && subtitle==false) {//部分タイトル
				subtitle = true;
			}

			if(cmp.contains("dcterms:title")&&subtitle) {
				//System.out.println("部分タイトル:" +subtitlenum+":"+ formatData(cmp));
				bw2.write("部分タイトル:" +subtitlenum+":"+ formatData(cmp)+"\n");
				subtitlenum++;
			}
			if(cmp.contains("dc:creator")&&subtitle) {
				//System.out.println("部分タイトル:" +subtitlecreatornum +":著者名:"+formatData(cmp));
				bw2.write("部分タイトル:" +subtitlecreatornum +":著者名:"+formatData(cmp) + "\n");
				subtitlecreatornum++;
			}
			if(cmp.contains("/dcndl:partInformation")) {
				subtitle = false;
			}
			if(cmp.contains("seriesTitle")) { //シリーズ名
				series = true;
			}
			if(cmp.contains("rdf:value") && series) {
				//System.out.println("シリーズ名：" + formatData(cmp));
				bw2.write("シリーズ名:" + formatData(cmp) + "\n");
				//series = false;
			}

			if (cmp.contains("<foaf:name>国立国会図書館")) { //請求記号のチェック用
				ndlch = true;
			}

			if (cmp.contains("dcndl:callNumber")) {//請求記号
				//いまのフラグだけだと、もし国立国会図書館の請求記号がない場合（そんなことあるのか？　→　可能性はる）は対応できない
				if (ndlch == true) {
					//System.out.println("請求記号：" + formatData(cmp));
					bw2.write("請求記号：" + formatData(cmp) + "\n");
					ndlch = false;
				}
			}

			if(cmp.contains("/dcterms:creator")) {
				creatorch = true;
			}
			if (cmp.contains("dc:creator") &&  creatorch) {
					//System.out.println("著作者番号 " + creatornum + ":" + formatData(cmp));
					bw2.write("著作者番号 " + creatornum + ":" + formatData(cmp) + "\n");
					creatornum++;
			}

			if (cmp.contains("dcterms:publisher") && !cmp.contains("/dcterms:publisher")) {
				publisherch = true;
			}

			if (publisherch == true && cmp.contains("foaf:name")) {
				//System.out.println("出版社:" +publishernum+":"+ formatData(cmp));
				bw2.write("出版社:" + formatData(cmp) + "\n");
				publisherch = false;
			}

			if (cmp.contains("dcterms:extent")) {
				//System.out.println("ページ数:" + formatData(cmp));
				bw2.write("ページ数:" + formatData(cmp) + "\n");
			}

			if (cmp.contains("dcterms:subject rdf:resource=") && cmp.contains("ndc")) {//NDCの抜き出し
				//ndc8,9,10に一応対応できるようにしておく
				int ndcnum = 0;
				if (cmp.contains("ndc9")) {
					ndcnum = 9;
				} else if (cmp.contains("ndc8")) {
					ndcnum = 8;
				} else if (cmp.contains("ndc10")) {
					ndcnum = 10;
				}
				cmp = cmp.trim();
				int end = cmp.lastIndexOf("/");
				cmp = cmp.substring(0, end);
				int start = cmp.lastIndexOf("/");
				end = cmp.lastIndexOf("\"");
				cmp = cmp.substring(start + 1, end);
				//System.out.println("NDC" + ndcnum + ":" + cmp);
				bw2.write("NDC" + ndcnum + ":" + cmp + "\n");
			}

			if (cmp.contains("dcterms:identifier rdf:datatype=") && cmp.contains("ISBN")) {//ISBNの抜き出し
				//System.out.println("ISBN:" + formatData(cmp));
				bw2.write("ISBN:" + formatData(cmp) + "\n");
			}

			//件名の取得
			if(cmp.contains("dcterms:subject")) {
				subjectch = true;
			}
			if(cmp.contains("rdf:value")&&subjectch) {
				//System.out.println("件名:" + formatData(cmp));
				bw2.write("件名:" + formatData(cmp) + "\n");
				subjectch = false;
			}

			//マテリアルタイプの区別のための情報の取得
			if(cmp.contains("dcterms:audience")) {
				bw2.write("対象利用者:" + formatData(cmp) + "\n");
			}
			if(cmp.contains("dcndl:materialType") && cmp.contains("ndltype/Book")) {
				//これは別メソッドが必要
				bw2.write("資料種別:"+"図書"+"\n");
			}
			//<dcterms:date>2006.3</dcterms:date>
			if(cmp.contains("dcterms:date")) {
				bw2.write("出版年：" + formatData(cmp));
			}


		}//end of while
	    br.close();
		fr.close();
		bw2.close();
		fw2.close();
		//filetmp.delete();
		//recordsFile.delete();
		file.delete();
		return chunknum-1;//最後の終了時に一度プラスされているため
	}

	/**
	 * chunkNumで指定された番号のデータからだけ、各データの抽出を行い、BookDataの作成を行う
	 * @param chunkNum
	 * @return chunkNumで指定された番号のデータから作成したBookData
	 * @throws IOException
	 */
	public BookData createBookData(int chunkNum) throws IOException {
		File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\Prototype2\\get_data\\bookdata.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> mainTitle = new ArrayList<String>();
		ArrayList<String> isbn = new ArrayList<String>();
		ArrayList<String> pages = new ArrayList<String>();
		ArrayList<String> callNum = new ArrayList<String>();
		ArrayList<String> ndc = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		ArrayList<String> series = new ArrayList<String>();
		ArrayList<String> subtitle = new ArrayList<String>();
		ArrayList<String> publisher = new ArrayList<String>();
		ArrayList<String> subject = new ArrayList<String>();
		ArrayList<String> materialIdentifer = new ArrayList<String>();
		ArrayList<String> publishYear = new ArrayList<String>();
		//ArrayList<String> imagePath = new ArrayList<String>();
		//ArrayList<String> tmpL = new ArrayList<String>();

		String tmp;
		boolean get = false;
		while((tmp = br.readLine()) != null) {
            if(tmp.contains("データ番号")&&tmp.contains("開始")&&(get==false)) {
            	if(tmp.contains(Integer.toString(chunkNum))) {
            		get=true;
            	}
			}
			if(tmp.startsWith("メインタイトル")&&get) {
				mainTitle.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("ISBN")&&get) {
				isbn.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("部分タイトル")&&get) {
				subtitle.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("ページ数")&&get) {
				pages.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("請求記号")&&get) {
				callNum.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("出版社")&&get) {
				publisher.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("件名")&&get) {
				subject.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("シリーズ名")&&get) {
				series.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("著作者番号")&&get) {
				author.add(formatData4BD(tmp));
			}
			if(tmp.startsWith("NDC")&&get) {
				ndc.add(tmp);
			}
			if(tmp.startsWith("対象利用者")&&get) {
				materialIdentifer.add(tmp);
			}
			if(tmp.startsWith("資料種別")&&get) {
				materialIdentifer.add(tmp);
			}
			if(tmp.startsWith("出版年")&&get) {
				publishYear.add(tmp);
			}

			if(tmp.startsWith("データ番号")&&tmp.contains("終了")&&get) {
				if(tmp.contains(Integer.toString(chunkNum))) {
					get=false;
	        		break;
            	}
			}
		}
		br.close();
		fr.close();
		return new BookData(null,false,mainTitle, series, author, subtitle, publisher, pages, ndc, subject, isbn, callNum, materialIdentifer, publishYear, null);
	}


	public boolean checkTorF(String data) {
		String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		//String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep3\\tmpc.txt";
		try {
			File filetmp = new File(tmpFilePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File recordsFile = new File(tmpFilePath);
		FileReader fr = null;
		try {
			fr = new FileReader(recordsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String cmp = "";

		try {
			while ((cmp = br.readLine()) != null) {
				//System.out.println(cmp);
				if (cmp.contains("true")) {
					return true;
				}
				if (cmp.contains("false")) {
					return false;
				}
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		recordsFile.delete();
		return false;
	}

	private String formatData(String target) {
		//まずは、不要な空白の除去
		String result = target.trim();
		int start = result.indexOf(">");
		int end = result.indexOf("</");
		result = result.substring(start + 1, end);
		return result;
	}

	private String formatData4BD(String target) {
		String result = null;
		result = target.substring(target.indexOf(":")+1);
		//System.out.println(result);
		return result;
	}
	private String formatData4MaterialType(String target) {
		String result = null;
		int start = target.indexOf("rdf:label") + 10;
		int end = target.indexOf("\"");
		result = target.substring(start, end);
		return result;
	}

	private String formatData(String target, boolean NDC) {
		target = target.trim();
		int start = target.indexOf(">");
		int end = target.indexOf("</");
		target = target.substring(start + 1, end);
		int s = target.indexOf("class");
		int s2 = target.indexOf("/", s);
		//System.out.println(s + ":" + s2);
		target = target.substring(s2 + 1);
		//System.out.println(target);

		/*String[] result = target.split("/");
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}*/
		return target;
	}

}
