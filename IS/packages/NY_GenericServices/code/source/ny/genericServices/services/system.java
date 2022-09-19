package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.UserManager;
import com.softwareag.util.IDataMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Arrays;
import com.wm.app.b2b.server.Server;
import java.net.InetAddress;
import java.net.UnknownHostException;
// --- <<IS-END-IMPORTS>> ---

public final class system

{
	// ---( internal utility methods )---

	final static system _instance = new system();

	static system _newInstance() { return new system(); }

	static system _cast(Object o) { return (system)o; }

	// ---( server methods )---




	public static final void getParentServiceDetails (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getParentServiceDetails)>> ---
		// @sigtype java 3.5
		// [o] field:0:required folderName
		// [o] field:0:required serviceName
		// [o] field:0:required fullName
		// [o] field:0:required successFlag
		// [o] field:0:required errorMessage
		// [o] field:0:required callStack
			
		// Instantiate a cursor for access to the pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		// Working & Output Variables - Create all output & working variables
		NSService callingService = null;
		StringTokenizer strTok = null;
		String folderPath = new String();
		String serviceName = new String();
		String fullName = new String();
		String successFlag = "true";
		String errorMessage = "None";
		// pipeline.
		
		
		
		
		Stack callStack = InvokeState.getCurrentState().getCallStack(); 
		int size = callStack.size(); 
		
		if (size >= 2) 
		{ 
			try{
		NSService myService = (NSService) callStack.elementAt (size - 3); 
		serviceName = myService.getNSName().getFullName(); 
			}catch(Exception e)
			{
				e.getMessage();
			}
		}else {
			
			serviceName = "Undefined";
		}
		
		
			
		
		// pipeline
		IDataCursor outputCursor = pipeline.getCursor();
		//IDataUtil.put( outputCursor, "folderName", folderPath );
		//IDataUtil.put( outputCursor, "serviceName", serviceName );
		IDataUtil.put( outputCursor, "fullName", serviceName);
		IDataUtil.put( outputCursor, "callStack", callStack);
		//IDataUtil.put( outputCursor, "fullName", parentService );
		//IDataUtil.put( outputCursor, "successFlag", successFlag );
		//IDataUtil.put( outputCursor, "errorMessage", errorMessage );
		
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required sleepTime
		IDataCursor cursor = pipeline.getCursor();
		
		int numSecs = 0;
		if (cursor.first("sleepTime"))
		{
		  numSecs = Integer.parseInt((String) cursor.getValue());
		}
		
		try
		{
		  Thread.currentThread().sleep(numSecs * 1000);
		}
		catch (InterruptedException ie)
		{
		  throw new ServiceException(ie);
		}
		finally
		{
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void throwError (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(throwError)>> ---
		// @sigtype java 3.5
		// [i] field:0:required errorMessage
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String strErrorMessage = null;
		if (idcPipeline.first("errorMessage"))
		{
		  strErrorMessage = (String) idcPipeline.getValue();
		}
		
		idcPipeline.destroy();
		
		throw new ServiceException(strErrorMessage);
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	
	// --- <<IS-END-SHARED>> ---
}

