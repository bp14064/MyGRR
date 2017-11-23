package breadcrumb2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BreadCrumbTest2 extends Application {

	public BreadCrumbTest2() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		primaryStage.setTitle("BreadCrumbTest");
		primaryStage.setWidth(600);
		primaryStage.setHeight(210);


		Parent root = FXMLLoader.load(getClass().getResource("BreadCrumbTest2.fxml"));
		Scene scene = new Scene(root, 600, 210);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
