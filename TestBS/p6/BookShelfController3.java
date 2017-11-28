package p6;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BookShelfController3 implements Initializable {
	@FXML
	private ImageView book1;
	@FXML
	private ImageView book2;
	@FXML
	private ImageView book3;
	@FXML
	private ImageView book4;
	@FXML
	private ImageView book5;
	@FXML
	private ImageView book6;
	@FXML
	private ImageView book7;
	@FXML
	private ImageView book8;
	@FXML
	private ImageView book9;
	@FXML
	private Button rightButton;
	@FXML
	private Button leftButton;
	@FXML
	private AnchorPane apane;

	private ImageView[] ivArray = new ImageView[9];
	private ArrayList<Image> imageList;
	private int now=0;
	private boolean buttonCliked = false;
	private Timeline  timer = null;

	public BookShelfController3() {
		// TODO 自動生成されたコンストラクター・スタブ
		//stem.out.println("BookShelfController");
		this.imageList = new ArrayList<Image>();
		imageList.add(new  Image(new File( "C:\\Users\\Shingo\\Pictures\\pycon2017\\pycon2017_baseball\\1.png" ).toURI().toString()));
		imageList.add(new  Image(new File( "C:\\Users\\Shingo\\Pictures\\pycon2017\\pycon2017_baseball\\2.png" ).toURI().toString()));
		imageList.add(new  Image(new File( "C:\\Users\\Shingo\\Pictures\\pycon2017\\pycon2017_baseball\\3.png" ).toURI().toString()));
		imageList.add(new  Image(new File( "C:\\Users\\Shingo\\Pictures\\pycon2017\\pycon2017_baseball\\4.png").toURI().toString()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//最初のもので初期化
		this.ivArray[0] = this.book1;
		this.ivArray[1] = this.book2;
		this.ivArray[2] = this.book3;
		this.ivArray[3] = this.book4;
		this.ivArray[4] = this.book5;
		this.ivArray[5] = this.book6;
		this.ivArray[6] = this.book7;
		this.ivArray[7] = this.book8;
		this.ivArray[8] = this.book9;
		for(ImageView r : ivArray) {
			r.setImage(imageList.get(now));
			r.autosize();
		}
		//this.leftButton.setOpacity(0.2);
		//this.rightButton.setOpacity(0.2);
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
						//updateButton();

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
		for(ImageView r : ivArray) {
			r.setImage(imageList.get(now));
			r.autosize();
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
		for(ImageView r : ivArray) {
			r.setImage(imageList.get(now));
			r.autosize();
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
		for(ImageView r : ivArray) {
			r.setImage(imageList.get(now));
			r.autosize();
		}
	}

	//アニメーションの再スタート？


}
