package p7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class BSMultiple extends Application {

	public BSMultiple() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TabPane root = FXMLLoader.load(getClass().getResource("BSMultiple.fxml"));
		Scene scene = new Scene(root, 700, 650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
