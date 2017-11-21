package p1;



public class TestAPI {

	public TestAPI() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BCDAPIHandler api = new BCDAPIHandler();
		String isbn = "9784331518427";
		api.getBCData(isbn);
	}

}
