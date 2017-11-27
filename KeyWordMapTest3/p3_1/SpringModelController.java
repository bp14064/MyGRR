package p3_1;

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
	private Color[] colorArray = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PURPLE, Color.BROWN, Color.BLACK, Color.BLACK};
	/**
	 * マップに設置するノード
	 */
	private String[] nodeName = {"1", "2", "3", "4", "5", "6"};
	/**
	 * ノードタイプ格納用のリスト
	 */
	//private String[] nodeType = {"selected", "subjectParent", "subject", "subject", "subject", "subject", "related", "related"};
	/**
	 * ノード間のリンクを表す
	 */
	private String[] link = {"1,2", "1,3", "1,4", "1,5", "1,6"};


	private String[] nodeName2 = {"2", "7", "8"};
	private String[] link2 = {"1,2", "1,3"};

	private ComputeMap cm;
	private ComputeMap cm2;

	private final int TRIALS = 10000;

	private Rectangle[] rectArray;
	private Rectangle[] rectArray2;

	private double rectWidth = 20;
	private double rectHeight = 20;


	public SpringModelController() {
		//探索マップのためのメンバの作成
		this.cm = new ComputeMap(this.nodeName, this.link, false);
		this.cm2 = new ComputeMap(this.nodeName2,this.link2,true);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ

		this.drawMap();
		this.cm.showMapInfo();
		this.drawMap2();
	}

	private void drawMap() {
		for(int i=0;i<TRIALS;i++)
			this.cm.MoveAll();

		this.makeRectArray();
		this.group.getChildren().addAll(this.rectArray);
		this.group.autosize();
	}

	private void drawMap2() {
		for(int i=0;i<TRIALS;i++)
			this.cm2.MoveAll();

		this.makeRectArray2();
		this.group.getChildren().addAll(this.rectArray2);
		this.group.autosize();
	}

	private void makeRectArray() {
		this.rectArray = this.cm.makeRectangle(this.nodeNum, this.rectWidth, this.rectHeight, 600,600);
		for(int i=0;i<this.rectArray.length;i++) {
			this.rectArray[i].setFill(this.colorArray[i]);
		}
	}

	private void makeRectArray2() {
		this.rectArray2 = this.cm2.makeRectangle(3, this.rectWidth, this.rectHeight, 600,600);
		for(int i=0;i<this.rectArray2.length;i++) {
			this.rectArray2[i].setFill(this.colorArray[i]);
		}
	}

	private void update() {

	}

}
