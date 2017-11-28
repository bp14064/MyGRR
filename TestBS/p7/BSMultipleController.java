package p7;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
		BookShelfCustumController bscc = new BookShelfCustumController();
		//このFXMLもこのコントローラーを参照する
		//なので、ここでBookShelfContoroller3が行っていたことを行う
		tab.setContent(bscc);


		tabPane.getTabs().add(tab);
		Tab tab2 = new Tab();
		tab2.setText("2");
		BookShelfCustumController bscc2 = new BookShelfCustumController();
		tab2.setContent(bscc2);
		tabPane.getTabs().add(tab2);

		bscc.init();
		bscc2.init();
	}

}
