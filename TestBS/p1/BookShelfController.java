package p1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

	public BookShelfController() {
		// TODO 自動生成されたコンストラクター・スタブ
		//stem.out.println("BookShelfController");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		Rectangle[] rect = {book1,book2,book3,book4,book5,book6,book7,book8,book9}; //ここは固定

		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(3000),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) { //上で宣言したタイマー内のイベント処理　→　だからここでノードに更新もやらないといけない?

						leftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
							 public void handle(MouseEvent e)
				                {

								 System.out.println("clicked");

									int index = cnt.getIndex() -1;
									if(index>=0)
									cnt.setIndex(index);
									else
									cnt.setIndex(cnt.getMax());
				                }
						});


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

						 leftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
							 public void handle(MouseEvent e)
				                {
								 
								 System.out.println("clicked");

									int index = cnt.getIndex() -1;
									if(index>=0)
									cnt.setIndex(index);
									else
									cnt.setIndex(cnt.getMax());
				                }
						});

					}
				}));


		timer.setCycleCount(Timeline.INDEFINITE);
	    timer.play();
	}

	//左ボタンが押された
	/*@FXML
	private void leftButtonCliked(MouseEvent event) {
		System.out.println("clicked");

		int index = cnt.getIndex() -1;
		if(index>=0)
		cnt.setIndex(index);
		else
		cnt.setIndex(cnt.getMax());
	}*/
	//右ボタンが押された
	@FXML
	private void rightButtonCliked(MouseEvent event) {

	}

	//アニメーションの再スタート？


}
