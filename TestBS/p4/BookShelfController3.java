package p4;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BookShelfController3 implements Initializable {
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
	@FXML
	private AnchorPane apane;

	private Rectangle[] rect = new Rectangle[9];
	private ArrayList<Color> bd = new ArrayList<Color>();
	private int now=0;
	private boolean buttonCliked = false;
	private Timeline  timer = null;

	public BookShelfController3() {
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
		this.leftButton.setOpacity(0.2);
		this.rightButton.setOpacity(0.2);
		this.animation();
	}

	public void animation() {
		timer = new Timeline(
				new KeyFrame(Duration.millis(3000),
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						apane.setOnMouseClicked(new EventHandler<MouseEvent>() {
						    @Override
							public void handle(MouseEvent event) {
						        boolean doubleClicked
						            = event.getClickCount() == 2;
						        if (doubleClicked) {
						        	anchorPaneDClicked();
						        }
						    }
						});

						boolean flag = buttonCliked;
						if(flag) {
							timer.stop();
						}
						updateAnimation();
						updateButton();

						if(flag) {
							timer.stop();
						}
					}
				}));


		timer.setCycleCount(Timeline.INDEFINITE);
	    timer.play();
	}

	private void updateAnimation() {
		if((now+1)>3) {
			now=0;
		}else {
			now++;
		}
		for(Rectangle r : rect) {
			r.setFill(bd.get(now));
		}
	}

	private void updateButton() {
		if(timer.getStatus().equals(Animation.Status.RUNNING)) {
			this.leftButton.setOpacity(0.2);
			this.rightButton.setOpacity(0.2);
		}
		if(timer.getStatus().equals(Animation.Status.STOPPED)) {
			this.leftButton.setOpacity(1);
			this.rightButton.setOpacity(1);
		}
	}

	private void anchorPaneDClicked() {
		if(this.buttonCliked) {
			this.buttonCliked = false;
			timer.play();
		}else {
			this.buttonCliked = true;
		}
	}

	//左ボタンが押された
	@FXML
	private void leftButtonCliked(MouseEvent event) {
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
