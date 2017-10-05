package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLAnalyzer {
	private String result;
	// private final String tmpFilePath =
	// "C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\main\\tmp.xml";//tmp.xmlのファイルパス
	private final String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\main\\tmp1.xml";
	private File filetmp;
	private int[] targetNodeNum = null;
	private int[] targetNodeNumTest = { 11, 15, 21, 45, 47, 51, 53, 55 };

	public XMLAnalyzer(String result) {
		this.result = result;
		this.filetmp = null;
	}

	public void xmlAnalyze(String targetDataType) {

		try {
			// 一時保存用ファイルの作成
			this.filetmp = new File(this.tmpFilePath);
			// 一時保存ファイルに書き出し
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.filetmp));
			bw.write(this.result);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String msg = null;
		if (targetDataType.matches("book")) {// 書誌データの解析
			msg = this.analyzeBookData();
		} else if (targetDataType.matches("auth")) {// 典拠データの解析
			msg = this.analyzeAuthData();
		}
		System.out.println(msg);

		// 一時保存用ファイルの削除
		if (filetmp.delete())
			System.out.println("xml file deleted");
	}

	/**
	 * 書誌データの解析
	 *
	 * @return
	 */
	private String analyzeBookData() {
		// System.out.println("一時作成されたXMLファイルから以下の取得対象のものを抽出する");
		// System.out.println("取得対象：タイトル、著者名、出版社、ページ数、ISBN、NDLC（請求記号）、NDC9、資料種別");

		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(this.filetmp);
			Element root = doc.getRootElement();

			/*
			 * ノードdc以下の処理はいいとして その前の、階層的に下がっていくところもfor文で指定の名前のノードを探すより
			 * あらかじめNode番号を控えておけばいいのでは？ それで、いろいろなクエリの結果で試してみる
			 *
			 * でもそもそもこれで動くなら綺麗さとかはべつにいいのでは？ 後で保守のときとかに困らなければ、このままでもよい
			 * System.out.println("ok");
			 */
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				if (element.getName().matches("records")) {
					for (int i1 = 0; i1 < element.nodeCount(); i1++) {
						if (element.node(i1).getName() != null && element.node(i1).getName().matches("record")) {
							Element e2 = (Element) element.node(i1);
							for (int i2 = 0; i2 < e2.nodeCount(); i2++) {
								if (e2.node(i2).getName() != null && e2.node(i2).getName().matches("recordData")) {
									Element e3 = (Element) e2.node(i2);
									for (int i3 = 0; i3 < e3.nodeCount(); i3++) {
										if (e3.node(i3).getName() != null && e3.node(i3).getName().matches("dc")) {
											Element e4 = (Element) e3.node(i3);
											for (int i4 = 0; i4 < e4.nodeCount(); i4++) {
												/*System.out.println("NODENAME:" + e4.node(i4).getName());
												if (e4.node(i4).getName() != null
														&& e4.node(i4).getName().matches("title")) {
													System.out.println(i4+e4.node(i4).getStringValue());
												}
												if (e4.node(i4).getName() != null
														&& e4.node(i4).getName().matches("creator")) {
													System.out.println(i4+e4.node(i4).getStringValue());
												}*/
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

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return "書誌データ解析終了"; // 返り値は検討が必要

	}

	/**
	 * 典拠データの解析
	 *
	 * @return
	 */
	private String analyzeAuthData() {
		return "典拠データ解析終了";
	}

	/**
	 * 書誌データ解析の際に使用
	 *
	 * @param comp
	 * @return
	 */
	private boolean eqaulTarget(int comp) {
		for (int i = 0; i < this.targetNodeNumTest.length; i++) {
			if (comp == this.targetNodeNumTest[i])
				return true;
		}
		return false;
	}

}
