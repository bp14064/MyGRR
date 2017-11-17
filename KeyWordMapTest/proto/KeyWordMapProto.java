package proto;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class KeyWordMapProto extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");
		stage.setWidth(250);
		stage.setHeight(250);

		Group root = new Group();

		final Canvas canvas = new Canvas(200,200);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		//初期配置の描画
		Keyword[] key = setKeyword();
		/*for(int i=0;i<4;i++) {
				this.setCircle(gc,key[i].getX()+100,key[i].getY()+100);
		}*/
		key = compute(key);
		//更新後の描画
		//gc.strokeOval(101,101,15,15);

		for(int i=0;i<4;i++) {
			this.setCircle(gc,key[i].getX()+100,key[i].getY()+100);
	    }

		/*try {
		Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}*/


		root.getChildren().add(canvas);
		/*gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
		key = compute(key);
		//更新後の描画
		for(int i=0;i<4;i++) {
			this.setCircle(gc,key[i].getX()*10,key[i].getY()*10);
	    }*/
		//canvasクリア用　これを次のグラフを新たに作成する際に使えるか？
		//gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

		//または、アニメーションでどんどん描画していく

		stage.setScene(new Scene(root));
		stage.show();
	}

	public void setCircle(GraphicsContext gc, double x, double y) {
		gc.strokeOval(x,y,10,10);
	}
	public int x() {
		return 10;
	}

	public Keyword[] setKeyword() {
		//キーワードの設定
		Keyword[] keyArray = new Keyword[4];
		keyArray[0] = new Keyword(1.0, 1.0);
		keyArray[1] = new Keyword(2.0, -1.5);
		keyArray[2] = new Keyword(-3.0, 4.0);
		keyArray[3] = new Keyword(-6.0, -5.0);
		return keyArray;
	}

	public Keyword[] compute(Keyword[] keyArray) {
		ComputeMap cm = new ComputeMap(4);
		cm.setEachValue(keyArray);
		return cm.computeMap(keyArray);
	}

}
