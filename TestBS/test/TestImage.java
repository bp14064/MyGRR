package test;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TestImage extends Application {

	public TestImage() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		Group root = new Group();

        // 枠線
        //Rectangle rectangle = new Rectangle(50, 50, 300, 300);
        //rectangle.setStrokeWidth(5.0);
        //rectangle.setStroke(Color.BLACK);
		 ImageView imageView = new ImageView();
	        Image image = new Image(new File( "C:\\Users\\AILab08\\Desktop\\a.jpg" ).toURI().toString());
	        imageView.setFitWidth(150);
	        imageView.setFitHeight(150);
	        imageView.setImage(image);


       root.getChildren().add(imageView);

        Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Application.launch(args);
	}

}
