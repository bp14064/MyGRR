package kep;

public class Test {

	public Test() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String uri = "http://id.ndl.go.jp/class/ndc9/694.5";

		int s = uri.indexOf("class");
		int s2 = uri.indexOf("/", s);
		System.out.println(s);
		System.out.println(s2);

		uri = uri.substring(s2+1);
		System.out.println(uri);

		String[] result = uri.split("/");
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}

	}

}
