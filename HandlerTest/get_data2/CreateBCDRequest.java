package get_data2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateBCDRequest {

	public CreateBCDRequest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 版元ドットコムのAPIを使って書影画像を取得する
	 * また、それをローカルに保存し、そのファイルパスを返す
	 * @param isbn
	 * @return 画像へのファイルパス
	 */
	public String getBCD(String ISBN) {
		String target="http://www.hanmoto.com/bd/img/"+ISBN+".jpg";//ハイフンありはなしにしないとエラーになる
		String filePath = "C:\\Users\\AILab08\\Desktop\\a.jpg";

		/*
		 * 版元ドットコムのAPI
		 * 普通に取得
		 * 例： http://www.hanmoto.com/bd/img/978-4-7808-0172-9.jpg
		 * →　http://www.hanmoto.com/bd/img/　＋　ISBN　＋.jpg
		 *
		 * 動的リサイズ取得
		 * http://www.hanmoto.com/bd/img/image.php/ISBN(13ケタ).jpg?width=横幅&image=/bd/img/
		 * 		ISBNの出版社記号部分/ISBN(13ケタ).jpg
		 */

		//isbnを使ってリクエストの作成
		URL url;
		try {
			url = new URL(target);

			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.connect();

			int httpStatusCode = conn.getResponseCode();
			if(httpStatusCode!=HttpURLConnection.HTTP_OK) {
				throw new Exception();
			}

			//Inputの処理
			DataInputStream dataInStream = new DataInputStream(conn.getInputStream());

			//Outputの処理
			DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));

			//データの読み込み
			byte[] b = new byte[4096];
			int readByte=0;
			while(-1!=(readByte = dataInStream.read(b))) {
				dataOutStream.write(b,0,readByte);
			}

			//ストリームのクローズ
			dataInStream.close();
			dataOutStream.close();

		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		//リクエストの送信、結果の取得

		//ローカルへの保存、ファイルパスの取得

		return filePath;
	}

}
