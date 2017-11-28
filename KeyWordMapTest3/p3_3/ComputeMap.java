package p3_3;

import java.util.ArrayList;


public class ComputeMap {
	private ArrayList<String> list;
	private ArrayList<String> link;
	private ArrayList<Node> node;
	private Node lockNode;
	private double r;

	public ComputeMap(ArrayList<String> list, double r) {
		this.list = list;
		this.r = r;
		this.node = new ArrayList<Node>();
		this.makeLink();
		this.setNode();
	}

	private void makeLink() {
		this.link = new ArrayList<String>();
		for(int i=1;i<this.list.size();i++) {
			this.link.add("1,"+String.valueOf(i+1));
		}
	}

	/**
	 * ノードの設定
	 */
	private void setNode() {
		for(String l : this.list) {
			this.node.add(new Node(l));
		}

		for(String l : link) {
			String[] subLink = l.split(",");
			this.node.get(Integer.parseInt(subLink[0])-1).getNeighbor().add(this.node.get(Integer.parseInt(subLink[1])-1));
			this.node.get(Integer.parseInt(subLink[1])-1).getNeighbor().add(this.node.get(Integer.parseInt(subLink[0])-1));
		}
		circularize(this.r, this.node);
		this.lockNode = this.node.get(0);
	}

	public static void circularize(double r, ArrayList<Node> node) {
		int count = node.size();
		double dtheta = 2.0 * Math.PI / (double)count;
		double theta = 0.0;
		double newX=0,newY=0;
		for(Node n : node) {
			//座標の初期化　指定した円の円周上に配置
			newX = (int)(r * Math.cos(theta));
			newY = (int)(r * Math.sin(theta));
			//System.out.println(newX+"  "+newY);
			n.setCoordinates(new Vector(newX, newY));
			//速度の初期化
			newX = 0.0;
			newY = 0.0;
			n.setVelocity(new Vector(newX, newY));

			//θの変更
			theta += dtheta;
		}
	}

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

	public void compute(int TRIALS) {
		for(int i=0;i<TRIALS;i++)
			this.MoveAll();
	}

	public ArrayList<Node> getNode() {
		return node;
	}

	public void showMapInfo() {
		if(this.node.size()!=0) {
			for(Node n : this.node) {
				n.showNodeInfo();
			}
		}
	}

}
