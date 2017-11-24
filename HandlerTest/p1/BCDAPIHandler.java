package p1;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BCDAPIHandler extends APIHandler {
	private String targetISBN;
	private final String filePathHead = "C:\\Users\\AILab08\\git\\mygr\\HandlerTest\\BCData\\"; // 自宅で実行するときは変更
	private final String filePathTail = ".jpg";
	private final String requestHead = "http://www.hanmoto.com/bd/img/";
	private final String requestTail = ".jpg";
	//private int id; // 画像を保存する際にファイル名を作るためのID

	public BCDAPIHandler() {
		// TODO 自動生成されたコンストラクター・スタブ

	}

	/**
	 *
	 * @param isbn
	 *            取りたい書影画像を持つ本のISBN
	 * @return 保存した画像へのファイルパス
	 */
	public String getBCData(String isbn) {
		this.targetISBN = isbn;
		String resultFilePath = this.filePathHead + this.targetISBN + this.filePathTail;
		int readByte=0;
		boolean cantGet = false;

		/*
		 * 版元ドットコムのAPI 普通に取得 例： http://www.hanmoto.com/bd/img/978-4-7808-0172-9.jpg →
		 * http://www.hanmoto.com/bd/img/ ＋ ISBN ＋.jpg
		 *
		 * 動的リサイズ取得
		 * http://www.hanmoto.com/bd/img/image.php/ISBN(13ケタ).jpg?width=横幅&image=/bd/
		 * img/ ISBNの出版社記号部分/ISBN(13ケタ).jpg
		 */

		// isbnを使ってリクエストの作成
		String request = this.requestHead + this.targetISBN + this.requestTail;
		// データの取得
		URL url;
		try {
			url = new URL(request);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.connect();

			int httpStatusCode = conn.getResponseCode();
			if (httpStatusCode != HttpURLConnection.HTTP_OK) {
				throw new Exception();
			}

			// Inputの処理
			DataInputStream dataInStream = new DataInputStream(conn.getInputStream());

			// Outputの処理
			DataOutputStream dataOutStream = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(resultFilePath)));

			// データの読み込み
			byte[] b = new byte[4096];
			readByte = 0;
			int sum=0;
			while (-1 != (readByte = dataInStream.read(b))) {
				sum+=readByte;
				// ローカルへの保存
				dataOutStream.write(b, 0, readByte);
			}
			//System.out.println(sum);
			if(sum==3185) {
				cantGet = true;
			}

			// ストリームのクローズ
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
		//this.id++; // 取得が出来たらid番号を更新
		if(cantGet) {
			File file = new File(resultFilePath);
			if(file.exists()) {
				file.delete();
			}
			resultFilePath = "non";
		}

		return resultFilePath;
	}

}
