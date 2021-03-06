package p4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * すこし、自分のイメージに近いものができた？
 * 次はこれを自分の考えているようにできるか？
 */
public class BSTest2 extends Application{
	boolean set = false;
	public BSTest2() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		stage.setTitle("Test");
		stage.setWidth(500);
		stage.setHeight(500);

		Pane root = new Pane();
		Group third = new Group();
		root.getChildren().add(third);
		Rectangle[] rectArray = new Rectangle[3];
		//初期配置
		rectArray[0] = new Rectangle(100, 100, 50, 50);
		rectArray[1] = new Rectangle(200, 100, 50, 50);
		rectArray[2] = new Rectangle(300, 100, 50, 50);
		for(Rectangle r : rectArray) {
			r.setStroke(Color.BLACK);
	        r.setFill(Color.WHITE);
			third.getChildren().add(r);
		}
		//third.getChildren().add(rect);
		//third.getChildren().add(rect2);
		//third.getChildren().add(rect3);

		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(500),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) { //上で宣言したタイマー内のイベント処理　→　だからここでノードに更新もやらないといけない?

						//ノードを動かす処理
						for(int i=0;i<3;i++) {
					     rectArray[i].setX(rectArray[i].getX() + 10);
						//r.setY(r.getY()+10);
						if(rectArray[i].getX() > 400) {
							System.out.println("外に出た");

						    third.getChildren().remove(rectArray[i]);
						    rectArray[i] = null;
						    Rectangle nr = new Rectangle(100, 100, 50, 50);
						    nr.setStroke(Color.BLACK);
					        nr.setFill(Color.WHITE);
						    rectArray[i] = nr; //ここでダメな理由は、上で
						    third.getChildren().add(nr);

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


		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
				 public void handle(MouseEvent e)
	                {
					  if(set) {
						  timer.play();
						  set=false;
					  }else {
						  timer.stop();
						  set=true;
					  }
	                }
			});


		stage.setScene(new Scene(root, 500,500));
		stage.show();
		timer.setCycleCount(Timeline.INDEFINITE);
	    timer.play();
	}

	public Rectangle createRect() {
		return new Rectangle(10, 10, 100, 100);
	}

}
