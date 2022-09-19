package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class encoding

{
	// ---( internal utility methods )---

	final static encoding _instance = new encoding();

	static encoding _newInstance() { return new encoding(); }

	static encoding _cast(Object o) { return (encoding)o; }

	// ---( server methods )---




	public static final void getClassName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getClassName)>> ---
		// @sigtype java 3.5
		// [i] object:0:required ffdata
		// [i] object:0:required node
		// [o] field:0:required name
		IDataCursor pipelineCursor = pipeline.getCursor();
		Object ffdata = IDataUtil.get(pipelineCursor, "ffdata");
		Object node = IDataUtil.get(pipelineCursor, "node");
		String name = "";
		try{
		if(ffdata == null)
		{
			name="ffdatanull";
		}
		
		if(node == null)
		{
			name="nodenull";
		}
		}catch(Exception e)
		{
			e.getMessage();
		}
		pipelineCursor.insertAfter("name", name);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

