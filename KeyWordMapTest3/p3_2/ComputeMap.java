package p3_2;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class ComputeMap {
	/**
	 * マップに設置するノード
	 */
	private String[] nodeName;
	/**
	 * ノード間のリンクを表す
	 */
	//private String[] link = {"1,2", "1,3", "1,4", "2,3", "2,4", "3,4"};
	private String[] link;
	/**
	 * ノードの格納用リスト
	 */
	private ArrayList<Node> node;
	/**
	 * ロックノード
	 */
	private Node lockNode;

	private boolean second=false;

	public ComputeMap(String[] nodeName, String[] link, boolean second) {
		this.node = new ArrayList<Node>();
		this.nodeName = nodeName;
		this.link = link;
		this.second = second;
		this.setNode();
	}

	/**
	 * ノードの設定
	 */
	private void setNode() {
		for(int i=0; i<this.nodeName.length; i++) {
			this.node.add(new Node(this.nodeName[i]));
		}
		for(String l : link) {
			String[] subLink = l.split(",");
			this.node.get(Integer.parseInt(subLink[0])-1).getNeighbor().add(this.node.get(Integer.parseInt(subLink[1])-1));
			this.node.get(Integer.parseInt(subLink[1])-1).getNeighbor().add(this.node.get(Integer.parseInt(subLink[0])-1));
		}
		circularize(300.0);
		this.lockNode = this.node.get(0);
		if(this.second) {
			for(Node n : this.node) {
				n.setL(50);
				n.setG(50);
			}
		  this.lockNode = this.node.get(0);

		}
	}

	/**
	 * 座標と速度の初期化
	 * @param r　円の半径
	 */
	private void circularize(double r) {
		int count = this.node.size();
		double dtheta = 2.0 * Math.PI / (double)count;
		double theta = 0.0;
		double newX=0,newY=0;
		for(Node n : this.node) {
			//座標の初期化　指定した円の円周上に配置
			newX = (int)(r * Math.cos(theta));
			newY = (int)(r * Math.sin(theta));
			System.out.println(newX+"  "+newY);
			n.setCoordinates(new Vector(newX, newY));
			//速度の初期化
			newX = 0.0;
			newY = 0.0;
			n.setVelocity(new Vector(newX, newY));

			//θの変更
			theta += dtheta;
		}
	}

	/**
	 * オイラー法によるすべてのノードの移動
	 */
	public void MoveAll() {
		final double dt = 0.1;
		for(Node n : this.node) {
		  if(n!=this.lockNode) {
			Vector f = new Vector(0, 0);
			for(Node nn : n.getNeighbor()) {
				f = Vector.operator(f, n.getSpringForce(nn));
			}
			for(Node nn : this.node) {
				if(!n.equals(nn)) {
					f = Vector.operator(f, n.getReplusiveForce(nn));
				}
			}
			f = Vector.operator(f, n.getFrictionalForce());
			n.moveEular(dt, f);
		  }
		}
	}

	public void showMapInfo() {
		if(this.node.size()!=0) {
			for(Node n : this.node) {
				n.showNodeInfo();
			}
		}
	}

	public Rectangle[] makeRectangle(int nodeNum, double w, double h) {
		Rectangle[] result = new Rectangle[nodeNum];
		for(int i=0;i<result.length;i++) {
			result[i] = new Rectangle(this.node.get(i).getCoordinates().getX(),this.node.get(i).getCoordinates().getY(),w,h);
		}
		return result;
	}

	public Rectangle[] makeRectangle(int nodeNum, double w, double h, double mapW, double mapH) {
		Rectangle[] result = new Rectangle[nodeNum];
		for(int i=0;i<result.length;i++) {
			result[i] = new Rectangle(this.node.get(i).getCoordinates().getX(),this.node.get(i).getCoordinates().getY() + 225,w,h);
		}
		return result;
	}


}
