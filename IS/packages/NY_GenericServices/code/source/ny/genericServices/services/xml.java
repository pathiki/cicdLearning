package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class xml

{
	// ---( internal utility methods )---

	final static xml _instance = new xml();

	static xml _newInstance() { return new xml(); }

	static xml _cast(Object o) { return (xml)o; }

	// ---( server methods )---




	public static final void extractXMLRootNode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(extractXMLRootNode)>> ---
		// @sigtype java 3.5
		// [i] field:0:required xmldata
		// [o] field:0:required root
		// [o] field:0:required idocContent
		IDataCursor pipelineCursor=pipeline.getCursor();
		String root=null;
		String idocContent =IDataUtil.getString(pipelineCursor, "xmldata");
		//idocContent=idocContent.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
				
		try{	 DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
				  DocumentBuilder builder = fact.newDocumentBuilder();
				 // Document doc = builder.parse(idocContent);
				  
				  Document doc = builder.parse(new InputSource(new StringReader(
						  idocContent)));
				  Node node = doc.getDocumentElement();
				  doc.getDocumentElement().normalize();
				  root=doc.getDocumentElement().getNodeName();
				  // root = node.getNodeName();
				  
		}catch(Exception e){
			
		}
				   IDataCursor pipelineCursor_1 = pipeline.getCursor();
					IDataUtil.put(pipelineCursor_1,"root",String.valueOf(root));
					//IDataUtil.put(pipelineCursor_1,"idocContent",String.valueOf(idocContent));
			
			
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
 
	
	
	// --- <<IS-END-SHARED>> ---
}

