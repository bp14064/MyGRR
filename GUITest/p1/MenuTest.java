package p1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * メニューバーのテスト（普通にコードを書く）
 */
public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();
		root.setTop(menuBar);
		Menu menuFile = new Menu("ファイル(_F)"); //(_F)アクセスキーの設定
		menuBar.getMenus().add(menuFile);
		MenuItem menuItemFileExit = new MenuItem("終了(_X)");
		menuItemFileExit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));//終了メニューにAlt+F4を割り当てる
		menuItemFileExit.setOnAction(
				new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					Platform.exit();
				}
				});
		menuFile.getItems().add(menuItemFileExit);
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setTitle("メニューのテスト");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
