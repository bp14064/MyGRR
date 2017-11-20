package p6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BSTest4 extends Application {

	public BSTest4() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		Parent root = FXMLLoader.load(getClass().getResource("BST2.fxml"));
		Scene scene = new Scene(root, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
