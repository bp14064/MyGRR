package p5;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	private String nodeName;
	private ArrayList<Node> neighbor;
	private String nodeType;
	/**
	 * ノードの座標
	 */
	private Vector coordinates;
	/**
	 * ノードの速度
	 */
	private Vector velocity;
	/**
	 * ばね定数（任意定数）
	 */
	private final double k=0.5;
	/**
	 * ばねの自然長（任意定数）
	 */
	private double l=100;
	/**
	 * 反発力の強さ（任意定数）
	 */
	private double g=500;
	/**
	 * 比例定数μ
	 */
	private double mu=0.4;

	public Node(String name, String nodeType) {
		this.nodeName = name;
		this.neighbor = new ArrayList<Node>();
		this.nodeType = nodeType;
	}

	/**
	 * 単純なオイラー法によるノードの位置計算<br>
	 * ノードの質量はすべて１として計算<br>
	 * @see <a href="http://matarillo.com/layout/part1.php">計算法の参考サイト</a>
	 * @param dt 定数。この値が小さいとオイラー法の誤差が小さくなる
	 * @param f このノードにかかる力
	 */
	public void moveEular(double dt, Vector f) {
		this.coordinates = new Vector((this.coordinates.getX() + dt * this.velocity.getX()), (this.coordinates.getY() + dt * this.velocity.getY()));
		this.velocity = new Vector((this.velocity.getX() + dt * f.getX()), (this.velocity.getY() + dt * f.getY()));
	}

	/**
	 * ばねの弾性力（あるノードがばねから受ける力）を計算<br>
	 *  F(x) = -k*(d-l)*cosθ<br>
	 *  F(y) = -k*(d-l)*sinθ<br>
	 * @param n 相手のノード
	 * @return ばねから受ける力 F
	 */
	public Vector getSpringForce(Node n) {
		double Fx = 0, Fy = 0;

		/*
		 * ノード間の距離を求める
		 */
		double dx = this.coordinates.getX() - n.coordinates.getX();
		double dy = this.coordinates.getY() - n.coordinates.getY();
		// 距離dの二乗
		double d2 = dx * dx + dy * dy;

		/*
		 * ノード間の距離が0のときの対策 このとき、角度θが計算できないので、乱数を使ってランダムな方向に力を向ける
		 */
		if (d2 <= 0) {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			Fx = rand.nextDouble() - 0.5;
			Fy = rand.nextDouble() - 0.5;
			return new Vector(Fx, Fy);
		}
		// 距離d
		double d = Math.sqrt(d2);
		double cos = dx / d;
		double sin = dy / d;
		// ノード間の距離とばねの自然長の差
		double dl = d - l;
		// 力Fの計算D
		Fx = -k * dl * cos;
		Fy = -k * dl * sin;
		return new Vector(Fx, Fy);
	}

	/**
	 * ノード同士の反発力の計算（これはばねで繋がっているかどうかに関係なく、すべてノード間で働く）<br>
	 * 反発力は逆2乗の法則に従う（力Fの大きさが距離dの2乗に反比例）ことにしている<br>
	 * RF(x)=g/d~2 * cosθ<br>
	 * RF(y)=g/d~2 * sinθ<br>
	 * @param n 相手ノード
	 * @return 反発力 RF
	 */
	public Vector getReplusiveForce(Node n) {
		double RFx=0,RFy=0;

		double dx = this.coordinates.getX() - n.coordinates.getX();
		double dy = this.coordinates.getY() - n.coordinates.getY();
		double d2 = dx * dx + dy * dy;
		/*
		 * 距離0のときは力の向きが決まらないだけなく、力の大きさが無限大になってしまう
		 */
		if(d2<=0) {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			RFx = rand.nextDouble() - 0.5;
			RFy = rand.nextDouble() - 0.5;
			return new Vector(RFx, RFy);
		}
		double d = Math.sqrt(d2);
		double cos = dx / d;
		double sin = dy / d;
		//this.setReplusiveForceCoefficient(n.nodeType);
		RFx = g / d2 * cos;
		RFy = g / d2 * sin;

		return new Vector(RFx,RFy);
	}

	/**
	 * 空気抵抗を参考にした摩擦力を計算<br>
	 * FF(x) = -μ*V(x)<br>
	 * FF(y) = -μ*V(y)<br>
	 * @return 摩擦力 FF
	 */
	public Vector getFrictionalForce() {
		double FFx=0, FFy=0;
		FFx = -mu * this.velocity.getX();
		FFy = -mu * this.velocity.getY();
		return new Vector(FFx, FFy);
	}

	/**
	 * ノード同士の組み合わせによって反発力を変えるためのメソッド
	 */
	private void setReplusiveForceCoefficient(String targetNodeType) {
		if(this.nodeType.matches("subject") && targetNodeType.matches("subject")) {
			this.g = 1000;
		}
		if((this.nodeType.matches("subject") && targetNodeType.matches("selected"))&&(this.nodeType.matches("selected") && targetNodeType.matches("subject"))){
			//変わらない
		}
		if(this.nodeType.matches("related") && targetNodeType.matches("related")) {
			this.g = 10;
		}
		if((this.nodeType.matches("subject") && targetNodeType.matches("related"))&&(this.nodeType.matches("related") && targetNodeType.matches("subject"))) {
			this.g = 100;
		}
		if((this.nodeType.matches("subjectParent") && targetNodeType.matches("related"))&&(this.nodeType.matches("related") && targetNodeType.matches("subjectParent"))) {
			this.g = 1;
		}
	}

	public String getNodeName() {
		return nodeName;
	}

	public ArrayList<Node> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(ArrayList<Node> neighbor) {
		this.neighbor = neighbor;
	}

	public Vector getCoordinates() {
		return coordinates;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setCoordinates(Vector coordinates) {
		this.coordinates = coordinates;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public void showNodeInfo() {
		System.out.println("ノード名:"+this.nodeName);
		System.out.println("X座標:"+this.coordinates.getX()+" Y座標:"+this.coordinates.getY());
	}

}
