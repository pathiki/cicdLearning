package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class invoke

{
	// ---( internal utility methods )---

	final static invoke _instance = new invoke();

	static invoke _newInstance() { return new invoke(); }

	static invoke _cast(Object o) { return (invoke)o; }

	// ---( server methods )---




	public static final void customInvoke (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(customInvoke)>> ---
		// @sigtype java 3.5
		// [i] field:0:required serviceName
		// [i] record:0:required flowInput
		// [o] record:0:required flowOutput
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	serviceName = IDataUtil.getString( pipelineCursor, "serviceName" );
			
			// flowInput
			IData	flowInput = IDataUtil.getIData( pipelineCursor, "flowInput" );
			if ( flowInput != null)
			{
				//TODO: check if null is ok or not
			}
		
		 
		// flowOutput
		IData	flowOutput;
		
		try{
			String[] result = serviceName.split(":");
			flowOutput = IDataUtil.clone(Service.doInvoke( result[0], result[1], IDataUtil.clone(flowInput)));
		}catch( Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		IDataUtil.put(pipelineCursor_1, "flowOutput", flowOutput );
		pipelineCursor_1.destroy();
		
			
		// --- <<IS-END>> ---

                
	}
}

