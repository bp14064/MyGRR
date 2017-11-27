package p3_3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MapContoroller implements Initializable {
	@FXML
	private Pane pane;

	private Group group;
	private ArrayList<Group> groupList;

	private final int TRIALS = 70000;

	public MapContoroller() {
		//ノードの作成　実際には注目本をもらい、そこから各要素を自動取得して、それらをノードにしノードリストを作成する
		this.groupList = new ArrayList<Group>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		this.drawMap();
	}

	private void drawMap() {
		ArrayList<String> subj1 = new ArrayList<String>();
		subj1.add("1");//件名
		subj1.add("2");//以下関連キーワード
		subj1.add("3");
		subj1.add("4");
		subj1.add("5");
		subj1.add("6");
		subj1.add("7");
		ComputeMap cm = new ComputeMap(subj1, 100.0);
		cm.compute(this.TRIALS);
		this.groupList.add(new Group(this.makeRectangle(cm.getNode(), 100, 100, 300, 300)));

		ArrayList<String> subj2 = new ArrayList<String>();
		subj2.add("1");//件名
		subj2.add("2");//以下関連キーワード
		subj2.add("3");
		subj2.add("4");
		subj2.add("5");
		subj2.add("6");
		subj2.add("7");
		subj2.add("8");
		ComputeMap cm2 = new ComputeMap(subj1, 300.0);
		cm2.compute(this.TRIALS);
		this.groupList.add(new Group(this.makeRectangle(cm2.getNode(), 100, 100, 300, 300)));

		for(Group g : this.groupList) {
			g.setScaleX(0.5);
			g.setScaleY(0.5);
		}

		this.pane.getChildren().addAll(groupList);
		//注目本

	}

	public Rectangle[] makeRectangle(ArrayList<Node> nodeList, double w, double h, double mapW, double mapH) {
		Rectangle[] result = new Rectangle[nodeList.size()];
		for(int i=0;i<nodeList.size();i++) {
			result[i] = new Rectangle(nodeList.get(i).getCoordinates().getX(),nodeList.get(i).getCoordinates().getY() + 225,w,h);
		}
		return result;
	}

}
