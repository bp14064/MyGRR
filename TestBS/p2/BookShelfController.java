package p2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class BookShelfController implements Initializable {
	@FXML
	private Rectangle book1;
	@FXML
	private Rectangle book2;
	@FXML
	private Rectangle book3;
	@FXML
	private Rectangle book4;
	@FXML
	private Rectangle book5;
	@FXML
	private Rectangle book6;
	@FXML
	private Rectangle book7;
	@FXML
	private Rectangle book8;
	@FXML
	private Rectangle book9;
	@FXML
	private Button rightButton;
	@FXML
	private Button leftButton;

	//private Rectangle[] rect = {book1,book2,book3,book4,book5,book6,book7,book8,book9};

	private Control cnt = new Control(0,3);
	Rectangle[] rect = {book1,book2,book3,book4,book5,book6,book7,book8,book9};
	private AnimationTimer timer;

	public BookShelfController() {
		// TODO 自動生成されたコンストラクター・スタブ
		//stem.out.println("BookShelfController");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		//ここは固定
		timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	if(cnt.getIndex() != cnt.getMax()) {
					  for(int i=0;i<9;i++) {
						  rect[i].setFill(cnt.color[cnt.getIndex()]);
					  }
					  cnt.setIndex(cnt.getIndex() + 1);
					}else {
						for(int i=0;i<9;i++) {
							  rect[i].setFill(cnt.color[cnt.getIndex()]);
						  }
						  cnt.setIndex(0);
					}
            }



        };
        timer.start();

	}

	//左ボタンが押された
	@FXML
	private void leftButtonCliked(MouseEvent event) {
		System.out.println("clicked");
		timer.stop();
		int index = cnt.getIndex() -1;
		if(index>=0)
		cnt.setIndex(index);
		else
		cnt.setIndex(cnt.getMax());

		for(int i=0;i<9;i++) {
			  rect[i].setFill(cnt.color[cnt.getIndex()]);
		  }

		timer.start();
	}
	//右ボタンが押された
	@FXML
	private void rightButtonCliked(MouseEvent event) {

	}

	//アニメーションの再スタート？


}
