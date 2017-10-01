package main;

import java.io.File;

public class XMLAnalyzer {
	private String result = "";
	public XMLAnalyzer(String result) {
		this.result = result;
	}

	public void xmlAnalyze(String targetData) {
		//一時保存用ファイルの作成
		File filetmp = new File("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\main\\tmp.xml");

		if(targetData.matches("book")) {//書誌データの解析

		}else if(targetData.matches("auth")) {//典拠データの解析

		}

		//一時保存用ファイルの削除
		if(filetmp.delete())
	    	System.out.println("xml file deleted");
	}

}
