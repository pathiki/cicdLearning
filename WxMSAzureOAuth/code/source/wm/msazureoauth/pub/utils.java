package wm.msazureoauth.pub;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void getConfigParam (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getConfigParam)>> ---
		// @sigtype java 3.5
		// [i] field:0:required type {"email","sharepoint"}
		// [o] field:0:required client_id
		// [o] field:0:required scope
		// [o] field:0:required client_secret
		// [o] field:0:required tenant_id
		// [o] field:0:required ms_graph_url
		// [o] field:0:required site_name
		// pipeline
		IDataCursor pipelineCursorIn = pipeline.getCursor();
		String	type = IDataUtil.getString( pipelineCursorIn, "type" );
		pipelineCursorIn.destroy();
		
		Properties prop = new Properties();
		String client_id = new String();
		String scope = new String();
		String client_secret = new String();
		String tenant_id = new String();
		String ms_graph_url = new String();
		String site_name = new String();
		String userDir = System.getProperty("user.dir", "No property found");
		String fileSep = System.getProperty("file.separator", "No property found");
		try {
			prop.load(new FileInputStream(userDir+fileSep+"packages"+fileSep+"WxMSAzureOAuth"+fileSep+"config"+fileSep+"config.properties"));
			client_id = prop.getProperty(type+"_client_id");
			scope = prop.getProperty(type+"_scope");
			client_secret = prop.getProperty(type+"_client_secret");
			tenant_id = prop.getProperty("tenant_id");
			ms_graph_url = prop.getProperty("ms_graph_url");
			site_name = prop.getProperty(type+"_site_name");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// pipeline
		IDataCursor pipelineCursorOut = pipeline.getCursor();
		IDataUtil.put( pipelineCursorOut, "client_id", client_id );
		IDataUtil.put( pipelineCursorOut, "scope", scope );
		IDataUtil.put( pipelineCursorOut, "client_secret", client_secret );
		IDataUtil.put( pipelineCursorOut, "tenant_id", tenant_id );
		IDataUtil.put( pipelineCursorOut, "ms_graph_url", ms_graph_url );
		IDataUtil.put( pipelineCursorOut, "site_name", site_name );
		pipelineCursorOut.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

