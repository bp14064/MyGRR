package webViewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller implements Initializable {
	@FXML
	private WebView webView;


	public Controller() {
		// 実際にはここで他のクラスからリクエストをもらう
		//this.engine = new WebEngine("http://iss.ndl.go.jp/books?ar=4e1f&mediatypes[]=1&display=&search_mode=advanced&repository_nos[]=R100000002&rft.au=%E5%A4%8F%E7%9B%AE%E6%BC%B1%E7%9F%B3");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 WebEngine webEngine = this.webView.getEngine();
		    webEngine.getLoadWorker().stateProperty()
		        .addListener((obs, oldValue, newValue) -> {
		          if (newValue == State.SUCCEEDED) {
		            System.out.println("finished loading"); //ここでプログレスバーを使えないか？
		          }
		        }); // addListener()

		    // begin loading...
		    webEngine.load("http://iss.ndl.go.jp/books?ar=4e1f&mediatypes[]=1&display=&search_mode=advanced&repository_nos[]=R100000002&rft.au=%E5%A4%8F%E7%9B%AE%E6%BC%B1%E7%9F%B3");
		}

}
