package test2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestController implements Initializable {
	@FXML
	ImageView imageView;

	public TestController() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		Image image = new Image(getClass().getResourceAsStream("a.jpg"));
        imageView.setImage(image);
        //imageView.autosize();
	}

}
