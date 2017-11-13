package p1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Seleniumを試してみた
 * サイトのコードを貼り付けだけ（https://qiita.com/tsukakei/items/41bc7f3827407f8f37e8）
 *
 * これから、著者名検索の誘導ぐらいはできるようにしたい
 */
public class SeleniumTest {

	public static void main(String[] args) {
		      // Optional, if not specified, WebDriver will search your path for chromedriver.
		      System.setProperty("webdriver.chrome.driver", "C:\\Users\\AILab08\\git\\MyGRR\\exe\\chromedriver.exe");

		      WebDriver driver = new ChromeDriver();
		      driver.get("http://www.google.com/xhtml");
		      try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}  // Let the user actually see something!
		      WebElement searchBox = driver.findElement(By.name("q"));
		      searchBox.sendKeys("ChromeDriver");
		      searchBox.submit();
		      try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}  // Let the user actually see something!
		      driver.quit();

	}

}
