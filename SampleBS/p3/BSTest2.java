package p3;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * すこし、自分のイメージに近いものができた？
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

		Rectangle rect = new Rectangle(100, 100, 50, 50);
		Rectangle rect2 = new Rectangle(200, 100, 50, 50);
		Rectangle rect3 = new Rectangle(300, 100, 50, 50);
		ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();
		rectArray.add(rect);
		rectArray.add(rect2);
		rectArray.add(rect3);

		for(Rectangle r : rectArray) {
			third.getChildren().add(r);
		}
		//third.getChildren().add(rect);
		//third.getChildren().add(rect2);
		//third.getChildren().add(rect3);

		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(500),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						Iterator<Rectangle> r = rectArray.iterator();
						while(r.hasNext()) {
					    Rectangle rg = r.next();
						rg.setX(rg.getX()+10);
						//r.setY(r.getY()+10);
						if(rg.getX() > 400) {
							System.out.println("外に出た");
							r.remove();
						    third.getChildren().remove(rg);
						    Rectangle nr = new Rectangle(100, 100, 50, 50);
						    rectArray.add(nr); //ここでダメな理由は、上で
						    third.getChildren().add(nr);
						    r = rectArray.iterator();

					     }
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

		stage.setScene(new Scene(root, 500,500));
		stage.show();
		timer.setCycleCount(Timeline.INDEFINITE);
	    timer.play();
	}

	public Rectangle createRect() {
		return new Rectangle(10, 10, 100, 100);
	}

}
