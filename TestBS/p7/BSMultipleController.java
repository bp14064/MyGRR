package p7;

import java.net.URL;
import java.util.ArrayList;
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
		ArrayList<String> p1 = new ArrayList<String>();
		p1.add("C:\\Users\\AILab08\\Desktop\\dir\\a.jpg");
		p1.add("C:\\Users\\AILab08\\Desktop\\dir\\b.jpg");
		p1.add("C:\\Users\\AILab08\\Desktop\\dir\\c.jpg");
		p1.add("C:\\Users\\AILab08\\Desktop\\dir\\d.jpg");

		BookShelfCustumController bscc = new BookShelfCustumController(1,p1);
		//このFXMLもこのコントローラーを参照する
		//なので、ここでBookShelfContoroller3が行っていたことを行う
		tab.setContent(bscc);


		tabPane.getTabs().add(tab);
		Tab tab2 = new Tab();
		tab2.setText("2");
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("C:\\Users\\AILab08\\Desktop\\dir\\e.jpg");
		p2.add("C:\\Users\\AILab08\\Desktop\\dir\\f.jpg");
		p2.add("C:\\Users\\AILab08\\Desktop\\dir\\g.jpg");
		p2.add("C:\\Users\\AILab08\\Desktop\\dir\\h.jpg");
		BookShelfCustumController bscc2 = new BookShelfCustumController(2,p2);
		tab2.setContent(bscc2);
		tabPane.getTabs().add(tab2);

		bscc.init();
		bscc2.init();
	}

}
