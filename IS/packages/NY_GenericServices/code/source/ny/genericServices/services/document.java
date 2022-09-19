package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void splitDocList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(splitDocList)>> ---
		// @sigtype java 3.5
		// [i] record:1:required list
		// [i] field:0:required size
		// [o] record:1:required splitList
		// [o] - record:1:required subList
		// [o] field:0:required splitListSize
		IDataCursor cursor = pipeline.getCursor();  
		IData[] list = IDataUtil.getIDataArray(cursor, "list");   // Original list  
		int size = IDataUtil.getInt(cursor, "size", list.length);   // Desired size of sub-lists (i.e. batch size)  
		  
		int iterations = list.length / size;   // Number of times to loop to create sub-lists  
		int remainder = list.length % size;   // In case the original list size is not a multiple of 'size'  
		IData[] splitList = new IData[remainder == 0 ? iterations : iterations + 1];    // Output list  
		  
		int i;  
		for(i = 0; i < iterations; i++) {  
		    IData subList[] = new IData[size];  
		    System.arraycopy(list, size * i, subList, 0, size);  
		    splitList[i] = IDataFactory.create(new Object[][]{{"subList", subList}});  
		}  
		if(remainder != 0) {  
		    IData subList[] = new IData[remainder];  
		    System.arraycopy(list, size * i, subList, 0, remainder);  
		    splitList[i] = IDataFactory.create(new Object[][]{{"subList", subList}});  
		}  
		  
		String splitListSize = Integer.toString(splitList.length);
		IDataUtil.put(cursor, "splitList", splitList);  
		IDataUtil.put(cursor, "splitListSize", splitListSize); 
		cursor.destroy();
		
			
		// --- <<IS-END>> ---

                
	}
}

