package p2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringModelMap extends Application{
	private final int num = 6;
	public SpringModelMap() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");
		stage.setWidth(600);
		stage.setHeight(600);

		//Group root = new Group();
		AnchorPane root = new AnchorPane();

		final Canvas canvas = new Canvas(600,600);
		canvas.setLayoutX(100);
		canvas.setLayoutY(100);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Color[] color = new Color[num];
		color[0] = Color.RED;
		color[1] = Color.BLUE;
		color[2] = Color.YELLOW;
		color[3] = Color.GREEN;
		color[4] = Color.PURPLE;
		color[5] = Color.BURLYWOOD;

		final int TRIALS = 3000;

		ComputeMap cm = new ComputeMap();
		cm.showMapInfo();
		//cm.drawMap(gc, color, canvas);
		System.out.println("=======================================================");

		for(int i=0;i<TRIALS;i++)
		cm.MoveAll();

		Color[] color2 = new Color[num];
		color2[0] = Color.PURPLE;
		color2[1] = Color.AQUA;
		color2[2] = Color.LIGHTYELLOW;
		color2[3] = Color.DARKGREEN;

		cm.showMapInfo();
		cm.drawMap(gc, color, canvas);
		AnchorPane.setTopAnchor(canvas, 0.0);
		AnchorPane.setBottomAnchor(canvas, 0.0);
		AnchorPane.setLeftAnchor(canvas, 0.0);
		AnchorPane.setRightAnchor(canvas, 0.0);
		root.getChildren().add(canvas);

		stage.setScene(new Scene(root));
		stage.show();
	}

}
