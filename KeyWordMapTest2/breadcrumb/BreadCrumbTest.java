package breadcrumb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BreadCrumbTest extends Application {

	public BreadCrumbTest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BreadCrumbTest");
		primaryStage.setWidth(500);
		primaryStage.setHeight(200);


		Parent root = FXMLLoader.load(getClass().getResource("BreadCrumbTest.fxml"));
		Scene scene = new Scene(root, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
