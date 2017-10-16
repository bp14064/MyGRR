package kep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.ArgsTypeException;

public class ResultAnalyzer {

	public ResultAnalyzer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ArrayList<String> AnalyzeResult(String data) {
		//String tmpFilePath = "C:\\Users\\Shingo\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
		String tmpFilePath = "C:\\Users\\AILab08\\git\\MyGRR\\Prototype\\kep\\tmp.txt";
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
