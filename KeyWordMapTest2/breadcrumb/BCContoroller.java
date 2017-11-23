package breadcrumb;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class BCContoroller implements Initializable {
	@FXML
	private StackPane stackPane;

	private ArrayList<String> buttonName;
	//private Button button;
	private ArrayList<Button> buttonList;

	public BCContoroller() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.buttonName = new ArrayList<String>();
		this.buttonName.add("1");
		this.buttonName.add("2");
		this.buttonName.add("3");
		this.buttonName.add("4");
		this.buttonName.add("5");
		this.buttonName.add("6");
		this.makeButtonList();
	}

	private void makeButtonList() {
		this.buttonList = new ArrayList<Button>();
		Button btn;
		int width = 600;
		int height = 200;
		int n = 0;
		for(String s : this.buttonName) {
			btn = new Button(s);
			btn.setPrefWidth(600 - (n * 100));
			btn.setPrefHeight(150);
			this.buttonList.add(btn);
			n = n + 1;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.stackPane.setAlignment(Pos.BASELINE_LEFT);
		this.stackPane.getChildren().addAll(buttonList);
	}

}
