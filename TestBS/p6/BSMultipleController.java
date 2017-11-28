package p6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class BSMultipleController implements Initializable {

	@FXML
	private TabPane tabPane;

	public BSMultipleController() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		Tab tab = new Tab();
		tab.setText("1");
		try {
			AnchorPane aPane = FXMLLoader.load(getClass().getResource("BookShelf3.fxml"));
			//このFXMLもこのコントローラーを参照する
			//なので、ここでBookShelfContoroller3が行っていたことを行う
			tab.setContent(aPane);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		tabPane.getTabs().add(tab);
		Tab tab2 = new Tab();
		tab2.setText("2");
		try {
			AnchorPane aPane2 = FXMLLoader.load(getClass().getResource("BookShelf3.fxml"));
			tab2.setContent(aPane2);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		tabPane.getTabs().add(tab2);
	}

}
