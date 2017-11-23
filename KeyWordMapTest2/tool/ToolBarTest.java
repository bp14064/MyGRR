package tool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ToolBarTest extends Application {

	public ToolBarTest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		ToolBar toolBar = null;
	    String[] btnText = { "青", "赤", "黒", "緑", "黄色", "茶色", "灰色" };
	    Color[] btnColor = { Color.BLUE, Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW, Color.BROWN, Color.GRAY};
	    Canvas canvas = new Canvas(250, 180);
	        stage.setTitle("ToolBarSmpl");
	        stage.setWidth(300);
	        stage.setHeight(200);

	        /*
	        *   ツールバーを作るときには、あらかじめツールバーに入れるコントロールを作成してから、
	        *   コンストラクタでコントロールを指定する
	        */
	        Button button[] = new Button[7];
	        for(int i=0; i<7; i++){
	            button[i] = new Button(btnText[i]);
	            button[i].setPrefWidth(60);
	            button[i].setPrefHeight(20);
	            Background bg = new Background(new BackgroundFill(btnColor[i], null, null));
	            button[i].setBackground(bg);

	        }
	        toolBar = new ToolBar(button);



	        BorderPane root = new BorderPane();
	        root.setTop(toolBar);
	        root.setCenter(canvas);

	        stage.setScene(new Scene(root));
	        stage.show();
	}


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
