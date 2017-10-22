package get_data;

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

import exception.ArgsTypeException;

public class ResultAnalyzer {

	public ResultAnalyzer() {
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
		ArrayList <String> result = new ArrayList<String>();
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
			while((cmp = br.readLine()) != null) {
				if(cmp.contains("literal")) {
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
			//String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
			String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
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
					if (cmp.contains("uri")&&cmp.contains("http")) {
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
	 * 書誌データから必要な項目を抜き出して表示
	 * これは、あくまでチェック用なので、createの方がうまくいっていれば、消してもよい
	 * @param data
	 * @return
	 */
	public boolean showBookData(String data) {
		boolean res = false;
		String txtFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.txt";
		String xmlFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml";
		//String txtFilePath = "C:\\Users\\AILab08\\Shingo\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.txt";
		//String xmlFilePath = "C:\\Users\\AILab08\\Shingo\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml";
		 try {
			    File filetmp = new File(xmlFilePath);
				BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
				bw.write(data);
				bw.close();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			 File file = new File(xmlFilePath);
			//System.out.println("一時作成されたXMLファイルから以下の取得対象のものを抽出する");
			//System.out.println("取得対象：タイトル、著者名、出版社、ページ数、ISBN、NDLC（請求記号）、NDC9、資料種別");

			SAXReader reader = new SAXReader();
			String records ="";
		    try {
		      Document doc = reader.read(file);
		      Element root = doc.getRootElement();
		      Element element = null;
		      for (Iterator i = root.elementIterator(); i.hasNext();) {
		    	  element = (Element) i.next();
		    	  //System.out.println(element.getName()+" "+element.getNodeTypeName()+" "+element.nodeCount());
				  //System.out.println("テキストを取り出す:" + element.getStringValue());
		    	  if(element.getName().matches("records")) {
		    		 records = element.getStringValue();
		    		 break;
		    	  }
		      }

		      //recordsをテキストファイルに一回保存
		      try {
		 		 File filetmp = new File(txtFilePath);
		 		 FileWriter fw = new FileWriter(filetmp);
		 	     BufferedWriter bw = new BufferedWriter(fw);
		 	     bw.write(records);
		 	     bw.close();
		 	     fw.close();
		 		} catch (IOException e1) {
		 			// TODO 自動生成された catch ブロック
		 			e1.printStackTrace();
		 		}


		      //ゴリ押しのテキスト処理
		      File recordsFile = new File(txtFilePath);
		      FileReader fr = null;
			try {
				fr = new FileReader(recordsFile);
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		      BufferedReader br = new BufferedReader(fr);
		      boolean ndlch = false; //国立国会図書館の請求記号だけ抜き出すためのフラグ
		      boolean chunkch = false; //チャンク（複数のデータがあったときのそれぞれの塊）のためのフラグ
		      boolean mainTitleCh = false; //メインタイトルとサブタイトルを識別するためのフラグ
		      int chunknum = 1; //タイトル識別用
		      boolean creatorch = false;
		      int creatornum = 1;
		      boolean publisherch = false;
		      try {
				String cmp = "";
				  while((cmp = br.readLine()) != null){
					  if(chunkch == false && cmp.contains("rdf:RDF xmlns")) {//データチャンクの開始処理
						  chunkch = true;
						  System.out.println("データ番号：" + chunknum + "開始");
					  }

					  if(chunkch == true && cmp.contains("/rdf:RDF")) {//データチャンクの終了処理ク
						  chunkch = false;
						  System.out.println("データ番号:" + chunknum + "終了");
						  chunknum++;
						  //新しくフラグを作ったら、ここでリセットしないと
						  mainTitleCh = false;
						  creatorch = true;
					  }

					  if(cmp.contains("dcterms:title")) {//タイトル
						  if(mainTitleCh == false) {
							  System.out.println("メインタイトル:" + formatData(cmp));
							  mainTitleCh = true;
						  }else{
							  System.out.println("サブタイトル:" + formatData(cmp));
						  }
					  }

					  if(cmp.contains("<foaf:name>国立国会図書館")) { //請求記号のチェック用
						  ndlch = true;
					  }

					  if(cmp.contains("dcndl:callNumber")) {//請求記号
						  //いまのフラグだけだと、もし国立国会図書館の請求記号がない場合（そんなことあるのか？）は対応できない
						 if(ndlch == true) {
						  System.out.println("請求記号：" + formatData(cmp));
						  ndlch = false;
						 }
					  }

					  if(cmp.contains("dc:creator")) {
						  if(creatorch == false) {
							  System.out.println("著作者番号 " + creatornum + ":" +formatData(cmp));
							  creatornum++;
						  }else {
							  creatornum = 1;
							  System.out.println("著作者番号 " + creatornum + ":" +formatData(cmp));
							  creatornum++;
							  creatorch = false;
						  }
					  }

					  if(cmp.contains("dcterms:publisher") && !cmp.contains("/dcterms:publisher")) {
						  publisherch = true;
					  }

					  if(publisherch == true && cmp.contains("foaf:name")) {
						  System.out.println("出版社:" + formatData(cmp));
						  publisherch = false;
					  }

					  if(cmp.contains("dcterms:extent")) {
						  System.out.println("ページ数:" + formatData(cmp));
					  }

					  if(cmp.contains("dcterms:subject rdf:resource=") && cmp.contains("ndc")) {//NDCの抜き出し
						  //ndc8,9,10に一応対応できるようにしておく
						  int ndcnum = 0;
						  if(cmp.contains("ndc9")) {
							  ndcnum = 9;
						  }else if(cmp.contains("ndc8")) {
							  ndcnum = 8;
						  }else if(cmp.contains("ndc10")) {
							  ndcnum = 10;
						  }

						  cmp = cmp.trim();
						  int end = cmp.lastIndexOf("/");
						  cmp = cmp.substring(0, end);
						  int start = cmp.lastIndexOf("/");
						  end = cmp.lastIndexOf("\"");
						  cmp = cmp.substring(start+1, end);

						  System.out.println("NDC" + ndcnum + ":" + cmp);
					  }

					  if(cmp.contains("dcterms:identifier rdf:datatype=")&&cmp.contains("ISBN")) {//ISBNの抜き出し
						  System.out.println("ISBN:"+formatData(cmp));
					  }

			        }
				br.close();
				fr.close();

			} catch (IOException e) {
				e.printStackTrace();
			}


		      recordsFile.delete();

		    }catch (DocumentException e) {
		        e.printStackTrace();
		    }

		    if(file.delete())
		    	System.out.println("xml file deleted");
		    return res;
	}

	/**
	 * 書誌データから必要な項目を抜き出し、それらの結果をまとめたリストを返す
	 * しかし、いまの状態だと、複数の本のデータをつくることができない
	 * このため、次のコミットで書き換えを行う
	 * @param data
	 * @return
	 */
	public ArrayList<ArrayList<String>> createBookData(String data) {
		//String txtFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.txt";
		//String xmlFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml";
		String txtFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		String xmlFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.xml";
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		 try {
			    File filetmp = new File(xmlFilePath);
				BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
				bw.write(data);
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 File file = new File(xmlFilePath);
			SAXReader reader = new SAXReader();
			String records ="";
		    try {
		      Document doc = reader.read(file);
		      Element root = doc.getRootElement();
		      Element element = null;
		      for (Iterator i = root.elementIterator(); i.hasNext();) {
		    	  element = (Element) i.next();
		    	  //System.out.println(element.getName()+" "+element.getNodeTypeName()+" "+element.nodeCount());
				  //System.out.println("テキストを取り出す:" + element.getStringValue());
		    	  if(element.getName().matches("records")) {
		    		 records = element.getStringValue();
		    		 break;
		    	  }
		      }
		      //recordsをテキストファイルに一回保存
		      try {
		 		 File filetmp = new File(txtFilePath);
		 		 FileWriter fw = new FileWriter(filetmp);
		 	     BufferedWriter bw = new BufferedWriter(fw);
		 	     bw.write(records);
		 	     bw.close();
		 	     fw.close();
		 		} catch (IOException e1) {
		 			// TODO 自動生成された catch ブロック
		 			e1.printStackTrace();
		 		}
		      //ゴリ押しのテキスト処理
		      File recordsFile = new File(txtFilePath);
		      FileReader fr = null;
			try {
				fr = new FileReader(recordsFile);
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		      BufferedReader br = new BufferedReader(fr);
		      boolean ndlch = false; //国立国会図書館の請求記号だけ抜き出すためのフラグ
		      boolean chunkch = false; //チャンク（複数のデータがあったときのそれぞれの塊）のためのフラグ
		      boolean mainTitleCh = false; //メインタイトルとサブタイトルを識別するためのフラグ
		      int chunknum = 1; //タイトル識別用
		      boolean creatorch = false;
		      int creatornum = 1;
		      boolean publisherch = false;
		      ArrayList<String> title = new ArrayList<String>(); title.add("title");
		      ArrayList<String> author = new ArrayList<String>(); author.add("author");
		      ArrayList<String> ndc = new ArrayList<String>(); ndc.add("ndc");
		      ArrayList<String> subject = new ArrayList<String>(); subject.add("subject");
		      ArrayList<String> publisher = new ArrayList<String>(); publisher.add("publisher");
		      ArrayList<String> page = new ArrayList<String>(); page.add("page");
		      ArrayList<String> isbn = new ArrayList<String>(); isbn.add("isbn");
		      ArrayList<String> callnum = new ArrayList<String>(); callnum.add("callnum");
		      try {
				String cmp = "";
				  while((cmp = br.readLine()) != null){

					  if(chunkch == false && cmp.contains("rdf:RDF xmlns")) {//データチャンクの開始処理
						  chunkch = true;
						  System.out.println("データ番号：" + chunknum + "開始");
					  }

					  if(chunkch == true && cmp.contains("/rdf:RDF")) {//データチャンクの終了処理ク
						  chunkch = false;
						  System.out.println("データ番号:" + chunknum + "終了");
						  chunknum++;
						  //新しくフラグを作ったら、ここでリセットしないと
						  mainTitleCh = false;
						  creatorch = true;
					  }

					  if(cmp.contains("dcterms:title")) {//タイトル
						  if(mainTitleCh == false) {
							  System.out.println("メインタイトル:" + formatData(cmp));
							  title.add(formatData(cmp));
							  mainTitleCh = true;
						  }else{
							  System.out.println("サブタイトル:" + formatData(cmp));
							  title.add(formatData(cmp));
						  }
					  }

					  if(cmp.contains("<foaf:name>国立国会図書館")) { //請求記号のチェック用
						  ndlch = true;
					  }

					  if(cmp.contains("dcndl:callNumber")) {//請求記号
						  //いまのフラグだけだと、もし国立国会図書館の請求記号がない場合（そんなことあるのか？　→　可能性はる）は対応できない
						 if(ndlch == true) {
						  System.out.println("請求記号：" + formatData(cmp));
						  callnum.add(formatData(cmp));
						  ndlch = false;
						 }
					  }

					  if(cmp.contains("dc:creator")) {
						  if(creatorch == false) {
							  System.out.println("著作者番号 " + creatornum + ":" +formatData(cmp));
							  author.add(formatData(cmp));
							  creatornum++;
						  }else {
							  creatornum = 1;
							  System.out.println("著作者番号 " + creatornum + ":" +formatData(cmp));
							  creatornum++;
							  creatorch = false;
							  author.add(formatData(cmp));
						  }
					  }

					  if(cmp.contains("dcterms:publisher") && !cmp.contains("/dcterms:publisher")) {
						  publisherch = true;
					  }

					  if(publisherch == true && cmp.contains("foaf:name")) {
						  System.out.println("出版社:" + formatData(cmp));
						  publisher.add(formatData(cmp));
						  publisherch = false;
					  }

					  if(cmp.contains("dcterms:extent")) {
						  System.out.println("ページ数:" + formatData(cmp));
						  page.add(formatData(cmp));
					  }

					  if(cmp.contains("dcterms:subject rdf:resource=") && cmp.contains("ndc")) {//NDCの抜き出し
						  //ndc8,9,10に一応対応できるようにしておく
						  int ndcnum = 0;
						  if(cmp.contains("ndc9")) {
							  ndcnum = 9;
						  }else if(cmp.contains("ndc8")) {
							  ndcnum = 8;
						  }else if(cmp.contains("ndc10")) {
							  ndcnum = 10;
						  }
						  cmp = cmp.trim();
						  int end = cmp.lastIndexOf("/");
						  cmp = cmp.substring(0, end);
						  int start = cmp.lastIndexOf("/");
						  end = cmp.lastIndexOf("\"");
						  cmp = cmp.substring(start+1, end);
						  System.out.println("NDC" + ndcnum + ":" + cmp);
						  ndc.add(cmp);//版情報は入れなくて良いのか?
					  }
					  if(cmp.contains("dcterms:identifier rdf:datatype=")&&cmp.contains("ISBN")) {//ISBNの抜き出し
						  System.out.println("ISBN:"+formatData(cmp));
						  isbn.add(formatData(cmp));
					  }
			        }

				result.add(title);
				result.add(author);
				result.add(ndc);
				result.add(subject);
				result.add(publisher);
				result.add(page);
				result.add(isbn);
				result.add(callnum);

				br.close();
				fr.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		      recordsFile.delete();
		    }catch (DocumentException e) {
		        e.printStackTrace();
		    }

		    file.delete();
		    return result;
	}

	public boolean checkTorF(String data) {
		//String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep3\\tmpc.txt";
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
				if(cmp.contains("false")) {
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


	private  String formatData(String target) {
		//まずは、不要な空白の除去
		String result = target.trim();
		int start = result.indexOf(">");
		int end = result.indexOf("</");
		result = result.substring(start+1, end);
		return result;
	}

	private String formatData(String target, boolean NDC) {
		target = target.trim();
		int start = target.indexOf(">");
		int end = target.indexOf("</");
		target = target.substring(start+1, end);
		int s = target.indexOf("class");
		int s2 = target.indexOf("/", s);
		//System.out.println(s + ":" + s2);
		target = target.substring(s2+1);
		//System.out.println(target);

		/*String[] result = target.split("/");
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}*/
		return target;
	}

}
