package p3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpringModelController implements Initializable {

	@FXML
	private HBox hbox;
	@FXML
	private Group group;

	private int nodeNum = 6;
	private Color[] colorArray = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PURPLE, Color.BROWN};
	/**
	 * マップに設置するノード
	 */
	private String[] nodeName = {"1", "2", "3", "4", "5", "6"};
	/**
	 * ノードタイプ格納用のリスト
	 */
	private String[] nodeType = {"selected", "related", "related", "related", "related", "related"};
	/**
	 * ノード間のリンクを表す
	 */
	private String[] link = {"1,2", "1,3", "1,4", "1,5", "1,6"};

	private ComputeMap cm;

	private final int TRIALS = 3000;

	private Rectangle[] rectArray;

	private double rectWidth = 20;
	private double rectHeight = 20;


	public SpringModelController() {
		//探索マップのためのメンバの作成
		this.cm = new ComputeMap(this.nodeName, this.nodeType, this.link);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ

		this.drawMap();
		this.cm.showMapInfo();
	}

	private void drawMap() {
		for(int i=0;i<TRIALS;i++)
			this.cm.MoveAll();

		this.makeRectArray();
		this.group.getChildren().addAll(this.rectArray);
	}

	private void makeRectArray() {
		this.rectArray = this.cm.makeRectangle(this.nodeNum, this.rectWidth, this.rectHeight, 600,600);
		for(int i=0;i<this.rectArray.length;i++) {
			this.rectArray[i].setFill(this.colorArray[i]);
		}
	}

	private void update() {

	}

}
