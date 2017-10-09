package dcndl_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DcndlTest2 {

	private static final int[] targetNodeNum = {11, 15, 21, 45, 47, 51, 53, 55};
	private final static String xmlFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp3.xml";
	private final static String txtFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp3.txt";

	public static void main(String[] args) {
		//String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=title%3d%22%e3%81%93%e3%81%93%e3%82%8d%22%20AND%20creator%3d%22%e5%a4%8f%e7%9b%ae%e6%bc%b1%e7%9f%b3%22%20AND%20from%3d%222011%22%20AND%20until%3d%222013%22&recordSchema=dcndl_simple";
				//String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=dpid%3D%22iss-ndl-opac%22+AND+title%3D%22%E3%81%93%E3%81%93%E3%82%8D%22+AND+creator%3D%22%E5%A4%8F%E7%9B%AE%E6%BC%B1%E7%9F%B3%22+AND+from%3D%222011%22+AND+until%3D%222013%22&recordSchema=dcndl&onlyBib";
				String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=dpid%3D%22iss-ndl-opac%22+AND+title%3D%22%E4%BA%BA%E5%B7%A5%E7%9F%A5%E8%83%BD%22+AND+mediatype%3D1&maximumRecords=10&recordSchema=dcndl&onlyBib=\"true\"";
				String tmp="";
				try {
				      URL url = new URL(request);
				      URLConnection con = url.openConnection();
				      try (BufferedReader reader = new BufferedReader(
				        new InputStreamReader(con.getInputStream(), "UTF-8"))) {
				    	  while (reader.ready()) {
				    	    //System.out.println(reader.readLine());
				    		  tmp+=reader.readLine()+"\n";
				    	  }
				      }
				}catch (Exception e) {
				      e.printStackTrace();
				    }
				 try {
				 File filetmp = new File("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp3.xml");


					BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
					bw.write(tmp);
					bw.close();
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}

				//File file = new File("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\dcdnl_test\\tmp.xml");
				 File file = new File(xmlFilePath);

				System.out.println("一時作成されたXMLファイルから以下の取得対象のものを抽出する");
				System.out.println("取得対象：タイトル、著者名、出版社、ページ数、ISBN、NDLC（請求記号）、NDC9、資料種別");

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

				String dbDir = "C:\\DB\\hsqldb-2.4.0\\hsqldb\\lib";
				Connection con = null;
				Statement stat = null;

				try {//DBへの接続
		        Class.forName("org.hsqldb.jdbcDriver");
		        //String url = "jdbc:hsqldb:file:" + dbDir;
		        String url = "jdbc:hsqldb:hsql://localhost/";
		        String user = "sa";
		        String password = "";

		        con = DriverManager.getConnection(url, user, password);
				}catch(Exception e) {
					e.printStackTrace();
				}

				     //クエリの作成と実行
			          stat = con.createStatement();

			          //ここにINSERT INTOでいけるか？
			          //stat.execute("INSERT INTO testdb VALUES(1, '978-4-7966-8513-9', '読んでおきたいベスト集!夏目漱石', '夏目漱石', '出版社', '913.6', '606p ; 16cm', 'KH426-J34')");//これで追加できる



			      BufferedReader br = new BufferedReader(fr);
			      boolean ndlch = false; //国立国会図書館の請求記号だけ抜き出すためのフラグ
			      boolean chunkch = false; //チャンク（複数のデータがあったときのそれぞれの塊）のためのフラグ
			      boolean mainTitleCh = false; //メインタイトルとサブタイトルを識別するためのフラグ
			      int chunknum = 1; //タイトル識別用
			      boolean creatorch = false;
			      int creatornum = 1;
			      boolean publisherch = false;
			      String isbn = null, title = null, author = null, publisher = null, ndc = null, page = null, callnum = null;
			      int id = 1;
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

							//stat.execute("INSERT INTO testdb VALUES(1, '978-4-7966-8513-9', '読んでおきたいベスト集!夏目漱石', '夏目漱石', '出版社', '913.6', '606p ; 16cm', 'KH426-J34')");
							  stat.execute("INSERT INTO testdb VALUES("+ id + ", '" +  isbn +"' , '" + title +"' , '"+ author +"' , '"+ publisher +"' , '"+ ndc +"' , '" + page + "' , '" + callnum + "')");
							  id++;
						  }

						  if(cmp.contains("dcterms:title")) {//タイトル
							  if(mainTitleCh == false) {
								  title = formatData(cmp);
								  System.out.println("メインタイトル:" + title);
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
							  callnum = formatData(cmp);
							  System.out.println("請求記号：" + callnum);
							  ndlch = false;
							 }
						  }

						  if(cmp.contains("dc:creator")) {
							  if(creatorch == false) {
								  System.out.println("著作者番号 " + creatornum + ":" + formatData(cmp));
								  creatornum++;
							  }else {
								  creatornum = 1;
								  author = formatData(cmp);
								  System.out.println("著作者番号 " + creatornum + ":" + author);
								  creatornum++;
								  creatorch = false;
							  }
						  }

						  if(cmp.contains("dcterms:publisher") && !cmp.contains("/dcterms:publisher")) {
							  publisherch = true;
						  }

						  if(publisherch == true && cmp.contains("foaf:name")) {
							  publisher = formatData(cmp);
							  System.out.println("出版社:" + publisher);
							  publisherch = false;
						  }

						  if(cmp.contains("dcterms:extent")) {
							  page = formatData(cmp);
							  System.out.println("ページ数:" + page);
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

							  ndc = cmp;
							  System.out.println("NDC" + ndcnum + ":" + ndc);
						  }

						  if(cmp.contains("dcterms:identifier rdf:datatype=")&&cmp.contains("ISBN")) {//ISBNの抜き出し
							  isbn = formatData(cmp);
							  System.out.println("ISBN:" + isbn);
						  }


				        }
					br.close();
					fr.close();
					recordsFile.delete();


					ResultSet rs;
					try {//結果の取得と解析（実際にはここは違うクラスでやった方がいい）
						stat.execute("SELECT * FROM testdb");
						rs = stat.getResultSet();

						 while (rs.next()) {
					            int id1 = rs.getInt("ID");
					            String title2 = rs.getString("TITLE");
					            System.out.println("ID:"+id1 + " NDC:"+ title2);
					        }

					        rs.close();
					        stat.close();
					        con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			    }catch (DocumentException e) {
			        e.printStackTrace();
			    } catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}

			    if(file.delete())
			    	System.out.println("xml file deleted");

	}


	static boolean eqaulTarget(int comp) {
		for(int i=0;i<targetNodeNum.length;i++) {
			if(comp==targetNodeNum[i])
				return true;
		}
		return false;
	}

	private static boolean checkBeforeWritefile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canWrite()){
	        return true;
	      }
	    }
	    return false;
	  }

	private static String formatData(String target) {
		//まずは、不要な空白の除去
		String result = target.trim();

		int start = result.indexOf(">");
		int end = result.indexOf("</");
		result = result.substring(start+1, end);
		return result;
	}
}
