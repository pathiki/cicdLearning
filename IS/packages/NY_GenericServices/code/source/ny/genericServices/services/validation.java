package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class validation

{
	// ---( internal utility methods )---

	final static validation _instance = new validation();

	static validation _newInstance() { return new validation(); }

	static validation _cast(Object o) { return (validation)o; }

	// ---( server methods )---




	public static final void determineValidData (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(determineValidData)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional senderName
		// [i] field:0:optional receiverName
		// [i] field:0:optional documentType
		// [o] field:0:required senderName
		// [o] field:0:required receiverName
		// [o] field:0:required documentType
			
		
		// pipeline
		String outputSender="";
		String outputReceiver="";
		String outputMsgType="";
		String outputDocType="";
		
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	senderName = IDataUtil.getString( pipelineCursor, "senderName" );
			String	receiverName = IDataUtil.getString( pipelineCursor, "receiverName" );
		
			String	documentType = IDataUtil.getString( pipelineCursor, "documentType" );
		pipelineCursor.destroy();
		
		if(senderName.indexOf("%")>=0)
		{
			outputSender="Unknown";
		}else
		{
			outputSender=senderName;
		}
		
		if(receiverName.indexOf("%")>=0)
		{
			outputReceiver="Unknown";
		}else
		{
			outputReceiver=receiverName;
		}
		
				
		if(documentType.indexOf("%")>=0)
		{
			outputDocType="Unknown";
		}else
		{
			outputDocType=documentType;
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "senderName", outputSender);
		IDataUtil.put( pipelineCursor_1, "receiverName", outputReceiver );
		
		IDataUtil.put( pipelineCursor_1, "documentType", outputDocType );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}
}

