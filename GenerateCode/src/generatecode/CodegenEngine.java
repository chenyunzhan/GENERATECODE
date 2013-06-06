package generatecode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class CodegenEngine {
	private static String source = "F:\\work\\code\\generatecode\\GenerateCode\\xsl\\cdcatalog.xml";
	private static String target = "F:\\work\\code\\generatecode\\GenerateCode\\xsl\\cdcatalog.html";
	private static String rule = "F:\\work\\code\\generatecode\\GenerateCode\\xsl\\cdcatalog.xsl";
	
	public static void main(String[] args) {
		generatecode();
	}
	
	public static void generatecode () {
		
		try {
			SAXReader saxReader = new SAXReader();
			
			Document sourceDoc = saxReader.read(source);
			Document ruleDoc = saxReader.read(rule);
			Document targetDoc = DocumentHelper.createDocument();
			
			Element html = targetDoc.addElement("html");
			
			Element body = html.addElement("body");
			
			Element h2 = body.addElement("h2");
			
			h2.setText("My CD Collection");
			
			Element table = body.addElement("table");
			
			table.addAttribute("border", "1");
			
			Element tr = table.addElement("tr");
			
			tr.addAttribute("bgcolor", "#9acd32");
			
			Element th1 = tr.addElement("th");
			
			th1.setText("Title");
			
			Element th2 = tr.addElement("th");
			
			th2.setText("Artist");
			
			
			for (Iterator iterator = ruleDoc.selectNodes("//xsl:for-each").iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				
				for (Iterator iterator2 = sourceDoc.selectNodes(element.valueOf("@select")).iterator(); iterator2.hasNext();) {
					Element element2 = (Element) iterator2.next();
					
					
					Element temp = table.addElement("tr");
					
					for (Iterator iterator3 = ruleDoc.selectNodes("//xsl:value-of").iterator(); iterator3.hasNext();) {
						Element element3 = (Element) iterator3.next();
						
						//System.out.println(element3.valueOf("@select"));
						String tt = element3.valueOf("@select");
						
						(temp.addElement("td")).setText(element2.valueOf(tt));
					}
				}
				break;
			}
			
			//获取xsl:template的子孙元素
			Document targetDoc1 = DocumentHelper.createDocument();
			for (Iterator iterator = ruleDoc.selectNodes("//xsl:template/html").iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				targetDoc1.add((Element)element.clone());
			}
			
			
			try {
				XMLWriter output = new XMLWriter(new FileWriter(new File(target)));
				output.write(targetDoc1);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DocumentException e) { 
			e.printStackTrace();
		}
	}
}
