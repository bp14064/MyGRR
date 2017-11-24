package p1;



public class TestAPI {

	public TestAPI() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BCDAPIHandler api = new BCDAPIHandler();
		String isbn = "9784309226712";
		String s = api.getBCData(isbn);
		System.out.println(s);
	}

}
