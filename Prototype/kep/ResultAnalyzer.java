package kep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultAnalyzer {

	public ResultAnalyzer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ArrayList<String> AnalyzeResult(String data) {
		String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
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

	private  String formatData(String target) {
		//まずは、不要な空白の除去
		String result = target.trim();

		int start = result.indexOf(">");
		int end = result.indexOf("</");
		result = result.substring(start+1, end);
		return result;
	}

}
