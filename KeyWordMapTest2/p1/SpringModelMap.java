package p1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringModelMap extends Application{

	public SpringModelMap() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");
		stage.setWidth(550);
		stage.setHeight(550);

		Group root = new Group();

		final Canvas canvas = new Canvas(550,550);
		canvas.setLayoutX(100);
		canvas.setLayoutY(100);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Color[] color = new Color[4];
		color[0] = Color.RED;
		color[1] = Color.BLUE;
		color[2] = Color.YELLOW;
		color[3] = Color.GREEN;
		//color[4] = Color.PURPLE;
		//color[5] = Color.BURLYWOOD;

		final int TRIALS = 3000;

		ComputeMap cm = new ComputeMap();
		cm.showMapInfo();
		//cm.drawMap(gc, color, canvas);
		System.out.println("=======================================================");

		for(int i=0;i<TRIALS;i++)
		cm.MoveAll();

		Color[] color2 = new Color[4];
		color2[0] = Color.PURPLE;
		color2[1] = Color.AQUA;
		color2[2] = Color.LIGHTYELLOW;
		color2[3] = Color.DARKGREEN;

		cm.showMapInfo();
		cm.drawMap(gc, color, canvas);
		root.getChildren().add(canvas);

		stage.setScene(new Scene(root));
		stage.show();
	}

}
