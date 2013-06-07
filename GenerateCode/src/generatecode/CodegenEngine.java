package generatecode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class CodegenEngine {
	
	
	
	static String base = System.getProperty("user.dir");
	private static String source = base + "\\xsl\\xslt-server\\cdcatalog.xml";
	private static String target = base + "\\xsl\\xslt-server\\cdcatalog.html";
	private static String rule = base + "\\xsl\\xslt-server\\cdcatalog.xsl";
	
	public static void main(String[] args) throws TransformerException, IOException {
		generatecode();
		//regeneratecode();
	}
	
	public static void generatecode () throws TransformerException, IOException {
		Source xsltSource = new StreamSource(rule);
		Source xmlSource = new StreamSource(source);
		TransformerFactory transfact = TransformerFactory.newInstance();
		StringWriter sw = new StringWriter();
		Result result = new StreamResult(sw);
		Transformer trans = transfact.newTransformer(xsltSource);
		trans.transform(xmlSource, result);
		String str = sw.toString();
		str = str.replace("&lt;", "<");
		str = str.replace("&gt;", ">");
		str = str.replace("&apos;", "\'");
		str = str.replace("&quot;", "\"");
		str = str.replace("&amp;", "&");
		str = "<!-- @generated -->\r\n" + str;
		
		File file = new File(target);
		
		if(!file.exists()) {
			 FileOutputStream fos = new FileOutputStream(target);
			    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			    BufferedWriter bw = new BufferedWriter(osw);
			    bw.write(str);
			    bw.close();
		}else {
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			System.out.println(line);
			System.out.println(line.length());
			if(line.equals("<!-- @generated -->")) {
				FileOutputStream fos = new FileOutputStream(target);
			    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			    BufferedWriter bw = new BufferedWriter(osw);
			    bw.write(str);
			    bw.close();
			}
		}
	    
	   
	}
	
}
