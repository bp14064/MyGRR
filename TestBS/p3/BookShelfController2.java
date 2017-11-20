package p3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BookShelfController2 implements Initializable {
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

	private Rectangle[] rect = new Rectangle[9];
	private ArrayList<Color> bd = new ArrayList<Color>();
	private int now=0;
	private Timeline timer = null;

	public BookShelfController2() {
		// TODO 自動生成されたコンストラクター・スタブ
		//stem.out.println("BookShelfController");


		bd.add(Color.RED);
		bd.add(Color.BLUE);
		bd.add(Color.YELLOW);
		bd.add(Color.GREEN);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//最初のもので初期化
		this.rect[0] = this.book1;
		this.rect[1] = this.book2;
		this.rect[2] = this.book3;
		this.rect[3] = this.book4;
		this.rect[4] = this.book5;
		this.rect[5] = this.book6;
		this.rect[6] = this.book7;
		this.rect[7] = this.book8;
		this.rect[8] = this.book9;
		for(Rectangle r : rect) {
			r.setFill(bd.get(now));
		}
		timer = new Timeline(
				new KeyFrame(Duration.millis(3000),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) { //上で宣言したタイマー内のイベント処理　→　だからここでノードに更新もやらないといけない?
						doAction();
					}
				}));


		timer.setCycleCount(Timeline.INDEFINITE);
	    timer.play();

	}

	private void doAction() {
		for(Rectangle r : rect) {
			r.setFill(bd.get(now));
		}
		if((now+1)>3) {
			now=0;
		}else {
			now++;
		}
	}

	//左ボタンが押された
	@FXML
	private void leftButtonCliked(MouseEvent event) {
		timer.pause();
		if((now-1)<0) {
			now=3;
		}else{
			now--;
		}
		for(Rectangle r : rect) {
			r.setFill(bd.get(now));
		}
	}
	//右ボタンが押された
	@FXML
	private void rightButtonCliked(MouseEvent event) {
		timer.pause();
		if((now+1)>3) {
			now=0;
		}else {
			now++;
		}
		for(Rectangle r : rect) {
			r.setFill(bd.get(now));
		}
	}

	//アニメーションの再スタート？


}
