package p1;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Test {

	public static void main(String[] args) {
		   try {
	            URL url = new URL("http://cover.openbd.jp/9784152087195.jpg");
	            //コネクション開いて接続
	            URLConnection urlcon = url.openConnection();

	            //接続先からストリーム読み込み
	            DataInputStream datainput= new DataInputStream(urlcon.getInputStream());

	            int bytes=datainput.available();//ダウンロードするファイルは何バイトか？
	            byte buf[] = new byte[bytes];
	            datainput.read(buf);
	            datainput.close();
	            FileOutputStream fout =new FileOutputStream("a.png");//map.pngという名前で保存
	            fout.write(buf,0,bytes);
	            fout.close();
	            System.out.println("ダウンロード完了!");

	        } catch (Exception e) {//URLが見つからないとかネットに繋がっていない場合
	            System.out.println("インターネット接続できませんでした");
	            e.printStackTrace();
	        }


	}

}
