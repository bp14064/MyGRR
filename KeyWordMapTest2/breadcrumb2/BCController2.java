package breadcrumb2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BCController2 implements Initializable {
	@FXML
	private HBox hbox;

	private ArrayList<String> buttonName;
	private ArrayList<Button> buttonList;

	public BCController2() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.buttonName = new ArrayList<String>();
		this.buttonName.add("1");
		this.buttonName.add("2");
		this.buttonName.add("3");
		this.buttonName.add("4");
		this.buttonName.add("5");
		this.buttonName.add("6");
		this.buttonName.add("7");
		this.buttonName.add("8");
		this.makeButtonList();
	}

	private void makeButtonList() {
		this.buttonList = new ArrayList<Button>();
		Button btn;
		for(String s : this.buttonName) {
			btn = new Button(s);
			btn.setPrefWidth(100);
			btn.setMaxWidth(100);
			btn.setMinWidth(100);
			btn.setPrefHeight(150);
			this.buttonList.add(btn);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		//this.flowPane.setAlignment(Pos.BASELINE_LEFT);
		this.hbox.setFillHeight(false);
		this.hbox.setMaxSize(500, 500);
		this.hbox.getChildren().addAll(buttonList);
	}

}
