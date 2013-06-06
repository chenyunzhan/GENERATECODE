package test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class TransformerDemo {
	public static void main(String[] args) throws TransformerException, IOException {
		
		//System.out.println(Class.class.getClass().getResource("/").getPath() );
		//System.out.println(System.getProperty("user.dir"));
		
		String base = System.getProperty("user.dir");
		
		Source xsltSource = new StreamSource(base + "\\xsl\\xslt-server\\cdcatalog.xsl");
		Source xmlSource = new StreamSource(base + "\\xsl\\xslt-server\\cdcatalog.xml");
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
	    //System.out.println(str);
	    
	    FileOutputStream fos = new FileOutputStream(base + "\\xsl\\xslt-server\\cdcatalog.html");
	    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
	    BufferedWriter bw = new BufferedWriter(osw);
        bw.write(str);
        bw.close();
	}
}
