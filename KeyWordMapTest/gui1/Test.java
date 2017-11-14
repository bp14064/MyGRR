package gui1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Test extends Application { //classをpublic属性にしておくことに注意

	public Test() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");
		stage.setWidth(1000);
		stage.setHeight(1000);

		Group root = new Group();

		final Canvas canvas = new Canvas(900,900);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.strokeOval(450, 450, 20, 20);
		//各ノードの取り出し

		//ノードからx,y座標を取得してsetCircleに渡す
		this.setCircle(gc,150,150);
		root.getChildren().add(canvas);

		//canvasクリア用　これを次のグラフを新たに作成する際に使えるか？
		//gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

		//または、アニメーションでどんどん描画していく

		stage.setScene(new Scene(root, 1000,1000));
		stage.show();
	}

	public void setCircle(GraphicsContext gc, double x, double y) {
		gc.strokeOval(x,y,10,10);
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
