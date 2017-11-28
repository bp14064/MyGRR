package p3_4;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MapContoroller implements Initializable {
	@FXML
	private Pane pane;

	//private Group group;
	private ArrayList<Group> groupList;

	private final int TRIALS = 80000;

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
		ComputeMap cm = new ComputeMap(subj1, 300.0);
		cm.compute(this.TRIALS);
		Group group1 = new Group();
		Circle circle = new Circle(cm.getNode().get(0).getCoordinates().getX()+50,cm.getNode().get(0).getCoordinates().getY()+350,300);
		circle.setOpacity(0);
		group1.getChildren().add(circle);
		group1.getChildren().addAll(this.makeRectangle(cm.getNode(), 100, 100, 600, 600));
		this.groupList.add(group1);

		ArrayList<String> subj2 = new ArrayList<String>();
		subj2.add("1");//件名
		subj2.add("2");//以下関連キーワード
		subj2.add("3");
		subj2.add("4");
		subj2.add("5");
		subj2.add("6");
		subj2.add("7");
		subj2.add("8");
		ComputeMap cm2 = new ComputeMap(subj2, 300.0);
		cm2.compute(this.TRIALS);
		Group group2 = new Group();
		Circle circle2 = new Circle(cm2.getNode().get(0).getCoordinates().getX()+50,cm2.getNode().get(0).getCoordinates().getY()+350,300);
		circle2.setOpacity(0);
		group2.getChildren().add(circle2);
		group2.getChildren().addAll(this.makeRectangle(cm2.getNode(), 100, 100, 600, 600));
		this.groupList.add(group2);

		ArrayList<String> subj3 = new ArrayList<String>();
		subj3.add("1");//件名
		subj3.add("2");//以下関連キーワード
		subj3.add("3");
		subj3.add("4");
		subj3.add("5");
		subj3.add("6");
		ComputeMap cm4 = new ComputeMap(subj3, 300.0);
		cm4.compute(this.TRIALS);
		Group group3 = new Group();
		Circle circle3 = new Circle(cm4.getNode().get(0).getCoordinates().getX()+50,cm4.getNode().get(0).getCoordinates().getY()+350,300);
		circle3.setOpacity(0);
		group3.getChildren().add(circle3);
		group3.getChildren().addAll(this.makeRectangle(cm4.getNode(), 100, 100, 600, 600));
		this.groupList.add(group3);

		for(Group g : this.groupList) {
			g.setScaleX(0.5);
			g.setScaleY(0.5);
		}
		//位置計算用
		ArrayList<String> selected = new ArrayList<String>();
		selected.add("selected");
		selected.add("group1");
		selected.add("group2");
		selected.add("group3");
		ComputeMap cm3 = new ComputeMap(selected, 200.0);
		for(int i=0;i<cm3.getNode().size();i++) {
			cm3.getNode().get(i).setL(500);
		}

		cm3.compute(this.TRIALS);
		cm3.showMapInfo();
		//this.groupList.add(new Group(this.makeRectangle(cm3.getNode(), 100, 100, 600, 600)));
		//Group center = new Group(this.makeRectangle(cm3.getNode(), 100, 100, 600, 600));
		//別にcm3の座標が欲しいだけなのでGroupにする必要はなさそう
		for(Group g: this.groupList) {
			System.out.println(g.getLayoutX() + "  " + g.getLayoutY());
		}


		Rectangle rect = new Rectangle(300,300,100,100);
		rect.setFill(Color.YELLOW);

		/*ArrayList<Node> calc = new ArrayList<Node>();
		calc.add(new Node("1"));
		calc.add(new Node("2"));
		calc.add(new Node("3"));

		ComputeMap.circularize(300,calc);
		System.out.println((calc.get(0).getCoordinates().getY()));

		Rectangle rect = new Rectangle(calc.get(0).getCoordinates().getX(),calc.get(0).getCoordinates().getY(),100,100);
		rect.setFill(Color.YELLOW);

		this.groupList.get(0).setLayoutX(calc.get(0).getCoordinates().getX());
		this.groupList.get(0).setLayoutY(calc.get(0).getCoordinates().getY());
		this.groupList.get(1).setLayoutX(calc.get(1).getCoordinates().getX());
		this.groupList.get(1).setLayoutY(calc.get(1).getCoordinates().getY());*/

		//System.out.println( cm3.getNode().get(1).getCoordinates().getX() + "  " + cm3.getNode().get(1).getCoordinates().getY());
		this.groupList.get(0).setLayoutX(cm3.getNode().get(1).getCoordinates().getX());
		this.groupList.get(0).setLayoutY(cm3.getNode().get(1).getCoordinates().getY());
		this.groupList.get(1).setLayoutX(cm3.getNode().get(2).getCoordinates().getX());
		this.groupList.get(1).setLayoutY(cm3.getNode().get(2).getCoordinates().getY());
		this.groupList.get(2).setLayoutX(cm3.getNode().get(3).getCoordinates().getX());
		this.groupList.get(2).setLayoutY(cm3.getNode().get(3).getCoordinates().getY());



		Group map = new Group();
		map.getChildren().add(rect);
		map.getChildren().add(groupList.get(0));
		map.getChildren().add(groupList.get(1));
		map.getChildren().add(groupList.get(2));



		//map.setLayoutX(300*0.6);
		map.setScaleX(0.5);
		map.setScaleY(0.5);
		//map.getChildren().get(1).setScaleX(2); //これで拡大縮小ができそう
		//map.getChildren().get(1).setScaleY(2);
		map.getChildren().get(1).setLayoutX(map.getChildren().get(1).getLayoutX()+300); //これでグループ内の移動はできる

	//	this.pane.getChildren().add(rect);
		this.pane.getChildren().addAll(map);

		this.groupList.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
			  @Override
			  public void handle(MouseEvent event) {
			    System.out.println("group1 clicked");
			    //ドラッグ開始
			    //draggable.startFullDrag();
			    event.consume();
			  }
			});

		this.groupList.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
			  @Override
			  public void handle(MouseEvent event) {
			    System.out.println("group2 clicked");
			    //ドラッグ開始
			    //draggable.startFullDrag();
			    event.consume();
			  }
			});

	}

	public Rectangle[] makeRectangle(ArrayList<Node> nodeList, double w, double h, double mapW, double mapH) {
		Rectangle[] result = new Rectangle[nodeList.size()];
		for(int i=0;i<nodeList.size();i++) {
			result[i] = new Rectangle(nodeList.get(i).getCoordinates().getX(),nodeList.get(i).getCoordinates().getY()+(mapH/2),w,h);
		}
		result[0].setFill(Color.RED);
		return result;
	}

}
