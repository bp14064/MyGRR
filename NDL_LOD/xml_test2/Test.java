package xml_test2;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


/*
 * XPathのAnalayzeXMLの結果を基にして、もう一度やってみたがだめだった
 *
 * やはり、AnalayzeXMLでやったように地道に調べて階層を下げていったほうがよさそう
 * しかもあれならなんかメソッドにして、まわしていけば面倒だが単純にできそうな気がする
 */
public class Test {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\xml_test\\samp.xml");


		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			List nodes = document.selectNodes("//record");
			System.out.println(nodes.size());
			for(Iterator i = nodes.iterator(); i.hasNext();) {
				System.out.println("1");
				Node node = (Node) i.next();
				System.out.println("title:" + node.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
