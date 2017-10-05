package xml_analyze_proto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLAnalayze {

	private static final int[] targetNodeNum = {11, 15, 21, 45, 47, 51, 53, 55};

	public static void main(String[] args) {
		String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=title%3d%22%e3%81%93%e3%81%93%e3%82%8d%22%20AND%20creator%3d%22%e5%a4%8f%e7%9b%ae%e6%bc%b1%e7%9f%b3%22%20AND%20from%3d%222011%22%20AND%20until%3d%222013%22&recordSchema=dcndl_simple";
		String tmp="";
		try {
		      URL url = new URL(request);
		      URLConnection con = url.openConnection();
		      try (BufferedReader reader = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), "UTF-8"))) {
		    	  while (reader.ready()) {
		    	    //System.out.println(reader.readLine());
		    		  tmp+=reader.readLine();
		    	  }
		      }
		}catch (Exception e) {
		      e.printStackTrace();
		    }
		 try {
		 File filetmp = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\xml_analyze_proto\\tmp.xml");


			BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
			bw.write(tmp);
			bw.close();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		 /* FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\xml_analyze_proto\\tmp.xml");

			 //BufferedReaderオブジェクトの生成
		    BufferedReader br = new BufferedReader(fr);
		    String str="";
		    while((str = br.readLine()) != null)
		        System.out.println(str);

		} catch (FileNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/



		//File file = new File("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\xml_test\\samp.xml");
		 System.out.println("ok");
		 File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\xml_analyze_proto\\tmp.xml");

		System.out.println("一時作成されたXMLファイルから以下の取得対象のものを抽出する");
		System.out.println("取得対象：タイトル、著者名、出版社、ページ数、ISBN、NDLC（請求記号）、NDC9、資料種別");

		SAXReader reader = new SAXReader();
	    try {
	      Document doc = reader.read(file);
	      Element root = doc.getRootElement();


	      /*
	       * ノードdc以下の処理はいいとして
	       * その前の、階層的に下がっていくところもfor文で指定の名前のノードを探すより
	       * あらかじめNode番号を控えておけばいいのでは？
	       * それで、いろいろなクエリの結果で試してみる
	       *
	       * でもそもそもこれで動くなら綺麗さとかはべつにいいのでは？
	       * 後で保守のときとかに困らなければ、このままでもよい
	       */
	      for (Iterator i = root.elementIterator(); i.hasNext();) {
	    	  Element element = (Element) i.next();
	    	  if(element.getName().matches("records")) {
	    		  for(int i1=0; i1<element.nodeCount();i1++) {
	    			  if(element.node(i1).getName()!=null&&element.node(i1).getName().matches("record")) {
	    				  Element e2 = (Element) element.node(i1);
	    				  for(int i2=0; i2<element.nodeCount();i2++) {
	    					  if(e2.node(i2).getName()!=null&&e2.node(i2).getName().matches("recordData")) {
	    						  Element e3 = (Element)e2.node(i2);
	    						  for(int i3=0;i3<e3.nodeCount();i3++) {
	    							  if(e3.node(i3).getName()!=null&&e3.node(i3).getName().matches("dc")) {
	    								  Element e4 = (Element)e3.node(i3);
	    								  for(int i4=0;i4<e4.nodeCount();i4++) {
	    									  System.out.println(e4.node(i4).getName());

	    									  if(eqaulTarget(i4)) {
	    										  System.out.println("NodeNum="+i4+",Value="+e4.node(i4).getStringValue());
	    									  }
	    								  }
	    								  break;
	    							  }
	    						  }


	    						  break;
	    					  }
	    				  }

	    				  break;
	    			  }
	    		  }


	    		  break;
	    	  }
	      }

	    }catch (DocumentException e) {
	        e.printStackTrace();
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

}
