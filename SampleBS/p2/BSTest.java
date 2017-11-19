package p2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * canvasに描画したものを動かせるようにする
 * アニメーションのお試し
 */
public class BSTest extends Application {
	boolean set = false;
	public BSTest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		stage.setTitle("Test");
		stage.setWidth(1000);
		stage.setHeight(1000);
		Pane pane = new Pane();

		Group root = new Group();
		Canvas canvas = new Canvas(900,900);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.strokeOval(450, 450, 20, 20);


		root.getChildren().add(canvas);


		Rectangle rect = new Rectangle(-110,-110,100,100);
		rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
					 public void handle(MouseEvent e)
		                {
						 System.out.println("clicked!");
						 rect.setX(rect.getX()-10);
		                }
				});
		root.getChildren().add(rect);
		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(100),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						rect.setX(rect.getX()+10);
						rect.setY(rect.getY()+10);
						if(rect.getY() > 700) {
							System.out.println("外に出た");
						root.getChildren().remove(rect);

					 }
					}
				}));
		/*PathTransition pathTransition = new PathTransition();
		pathTransition.setNode(rect);
		pathTransition.setDuration(Duration.millis(5000));
		Line line = new Line(100, 100, 500,100); //（100, 100） →　(500, 100)　に移動

		pathTransition.setPath(line);
		pathTransition.setInterpolator( Interpolator.LINEAR );              // アニメーション補完方法を線形に設定
        pathTransition.setCycleCount( PathTransition.INDEFINITE );          // 無限アニメーションに設定
	*/
		//canvasクリア用　これを次のグラフを新たに作成する際に使えるか？
		//gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
		timer.setCycleCount(Timeline.INDEFINITE);
        canvas.setOnMouseEntered(
				new EventHandler<MouseEvent>() {
					 public void handle(MouseEvent e)
		                {
						 if(set) {
							 timer.pause();
							 set=false;
						 }else {
						 System.out.println("mouse entered!");
						 timer.play();
						 set=true;
						 }
		                }
				});


		stage.setScene(new Scene(root, 1000,1000));
		stage.show();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
