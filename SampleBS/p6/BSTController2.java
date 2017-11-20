package p6;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BSTController2 implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;

	private double delX=0;
	Rectangle[] rectArray = new Rectangle[3];
	public BSTController2() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		//初期配置
				rectArray[2] = new Rectangle(12.5, 25, 150, 150);
				rectArray[1] = new Rectangle(175, 25, 150, 150);
				rectArray[0] = new Rectangle(337.5, 25, 150, 150);
				for(Rectangle r : rectArray) {
					r.setStroke(Color.BLACK);
			        r.setFill(Color.WHITE);
					pane.getChildren().add(r);
				}

				Timeline timer = new Timeline(
						new KeyFrame(Duration.millis(800),
						new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) { //上で宣言したタイマー内のイベント処理　→　だからここでノードに更新もやらないといけない?

								//ノードを動かす処理
								for(int i=0;i<3;i++) {
							     rectArray[i].setX(rectArray[i].getX() + 10);
								//r.setY(r.getY()+10);
								if(rectArray[i].getX() > 500) {
									System.out.println("外に出た");

								    pane.getChildren().remove(rectArray[i]);
								    rectArray[i] = null;
								    Rectangle nr = new Rectangle(12.5, 25, 150, 150);
								    nr.setStroke(Color.BLACK);
							        nr.setFill(Color.WHITE);
								    rectArray[i] = nr;
								    pane.getChildren().add(nr);

							     }
								}

								//クリックされたときに動かす処理
								for(Rectangle r2: rectArray) {
									r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
										 public void handle(MouseEvent e)
							                {

											 System.out.println("clicked!");
											 r2.setX(r2.getX()-10);
											 for(Rectangle r3 : rectArray)
											  if(!r2.equals(r3))
											   r3.setX(r3.getX()-10);

							                }
									});
								}
							}


						}));


				timer.setCycleCount(Timeline.INDEFINITE);
			    timer.play();
	}
	/*@FXML
	private void userMoved(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			delX = event.getX();
			 for(Rectangle r3 : rectArray)
			   r3.setX(r3.getX()- delX);
		}
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			for(Rectangle r3 : rectArray)
				   r3.setX(r3.getX()- delX);
		}
	}*/

}
