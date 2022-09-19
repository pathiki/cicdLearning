package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class truncate

{
	// ---( internal utility methods )---

	final static truncate _instance = new truncate();

	static truncate _newInstance() { return new truncate(); }

	static truncate _cast(Object o) { return (truncate)o; }

	// ---( server methods )---




	public static final void truncateInput (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(truncateInput)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inputValue
		// [i] field:0:required maxLength
		// [o] field:0:required outputValue
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	inputValue = IDataUtil.getString( pipelineCursor, "inputValue" );
			String	maxLength = IDataUtil.getString( pipelineCursor, "maxLength" );
			String outputValue="";
			int max =  Integer.parseInt(maxLength);
			 
		pipelineCursor.destroy();
		
		 if (inputValue != null && inputValue.length() > max )
			 outputValue = inputValue.substring(0, max);
		 else
			 outputValue = inputValue;
			  
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "outputValue", outputValue );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}
}

