package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import kep2.SubjectData;

public class CSVAnalyzer {

	public CSVAnalyzer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ArrayList<SubjectData> createSubjectData(String filePath) throws IOException{
		File f = new File(filePath);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String tmp;
		String[] element;
		ArrayList<String> tmpList = new ArrayList<String>();
		String subj = "";
		ArrayList<SubjectData> result = new ArrayList<SubjectData>();

		while((tmp = br.readLine()) != null) {
			//System.out.println(tmp);
			element = tmp.split(",");
			subj = element[0];
			for(int i = 1; i<element.length; i++) {
				if(!element[i].equals("") || !element[i].equals(null)) {
					tmpList.add(element[i]);
				}
			}
			result.add(new SubjectData(subj, tmpList));
			tmpList.clear();
		}
		br.close();
		fr.close();
		return result;
	}


	public void showCSVData(String filePath) throws IOException {
		File f = new File(filePath);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String tmp;
		while((tmp = br.readLine()) != null) {
			System.out.println(tmp);
		}
		br.close();
		fr.close();
	}

}
