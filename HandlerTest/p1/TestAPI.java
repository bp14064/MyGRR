package p1;

import get_data2.CreateBCDRequest;

public class TestAPI {

	public TestAPI() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		CreateBCDRequest api = new CreateBCDRequest();
		String isbn = "9784331518427";
		api.getBCD(isbn);
	}

}
