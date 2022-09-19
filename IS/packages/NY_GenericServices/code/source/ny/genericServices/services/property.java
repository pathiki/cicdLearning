package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.InvokeState;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;
import java.util.Stack;
import com.softwareag.util.IDataMap;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
import com.wm.lang.ns.NSService;
// --- <<IS-END-IMPORTS>> ---

public final class property

{
	// ---( internal utility methods )---

	final static property _instance = new property();

	static property _newInstance() { return new property(); }

	static property _cast(Object o) { return (property)o; }

	// ---( server methods )---




	public static final void fetchConfigParam (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fetchConfigParam)>> ---
		// @sigtype java 3.5
		// [i] field:1:required key
		// [o] field:1:required value
		IDataCursor pipelineCursor = pipeline.getCursor();
		String[] key = IDataUtil.getStringArray( pipelineCursor, "key" );
		pipelineCursor.destroy();
		String[] value = new String[key.length];
		try
		{
		for(int i=0; i<key.length;i++)
		{
			value[i] = (String) Memory.get(key[i]);
		}
		IDataUtil.put( pipelineCursor, "value", value );
		pipelineCursor.destroy();
		}catch(Exception e)
		{
			throw new ServiceException("Internal Server Error - property file retrieval failed");
		}
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void loadMemory (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(loadMemory)>> ---
		// @sigtype java 3.5
		// [i] object:0:required memoryObj
			
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			Object	memoryObj = IDataUtil.get( pipelineCursor, "memoryObj" );
		pipelineCursor.destroy();
		try
		{
		Memory = (Values)memoryObj;
		}catch(Exception e)
		{
			throw new ServiceException("Internal Server Error - property file retrieval failed");
		}
		// pipeline
			
		// --- <<IS-END>> ---

                
	}



	public static final void putToMemory (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(putToMemory)>> ---
		// @sigtype java 3.5
		// [i] record:1:required Codes
		// [o] object:0:required memoryObj
		Values tempMemory = new Values();
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// Codes
			IData[]	Codes = IDataUtil.getIDataArray( pipelineCursor, "Codes" );
			if ( Codes != null)
			{
				for ( int i = 0; i < Codes.length; i++ )
				{
					IDataCursor CodesCursor = Codes[i].getCursor();
					String	key = IDataUtil.getString( CodesCursor, "key" );
					String	value = IDataUtil.getString( CodesCursor, "value" );
					tempMemory.put(key,value);
					CodesCursor.destroy();
				}
			}
		
		
		// pipeline
		
		IDataUtil.put( pipelineCursor, "memoryObj", tempMemory );
		
		pipelineCursor.destroy();
		pipelineCursor.destroy();
			
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	protected static Values Memory = new Values();
	
	public static long copy(InputStream input, OutputStream output) throws IOException
	{
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
	
	public static Object getRequiredObjParam(IDataCursor dataCursor, String paramKey, Class clazz) throws ServiceException
	{
		Object obj = IDataUtil.get(dataCursor, paramKey);
	
		if (obj == null) {
			throw new ServiceException("PARAM_IS_MISSING");
		}
	
		if (!clazz.isInstance(obj)) {
			throw new ServiceException("INVALID_PARAM_TYPE");
		}
	
		return obj;
	}
	
	public static String getRequiredStrParam(IDataCursor dataCursor, String paramKey) throws ServiceException
	{
		String temp = (String) getRequiredObjParam(dataCursor, paramKey, String.class);
		if (temp.length() == 0) {
			throw new ServiceException("EMPTY_PARAM");
		}
	
		return temp;
	}
	
	public static String getRequiredStrParam(IDataMap data, String paramKey) throws ServiceException
	{
		String str = data.getAsNonEmptyString(paramKey);
		if (str == null) {
			throw new ServiceException("EMPTY_PARAM");
		}
		return str;
	}
	
	private static void streamToFile(IDataCursor dataCursor, InputStream inStream, String filePath, String appendStr, boolean closeInputStream) throws Exception
	{
		boolean append = Boolean.valueOf(appendStr).booleanValue();
	
		File destFile = new File(filePath);
	
		//CommonUtils.checkDirCreated(destFile);
		//CommonUtils.checkFileWritable(destFile);
		//CommonUtils.isDirectory(destFile);
	
		OutputStream outStream = new BufferedOutputStream(new FileOutputStream(destFile, append));
	
		long length = -10L;
		try {
			length = copy(inStream, outStream);
		} finally {
			if (closeInputStream) {
				closeQuietly(inStream);
			}
			closeQuietly(outStream);
		}
	
		mergeOutput(dataCursor, "length", "" + length);
	}
	
	public static void closeQuietly(InputStream input) {
		if (input != null)
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void closeQuietly(OutputStream output)
	{
		if (output != null)
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void closeQuietly(Writer writer)
	{
		if (writer != null)
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void closeQuietly(Reader reader)
	{
		if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void mergeOutput(IDataCursor id, String key, Object value) {
		if (id.first(key)) {
			id.setValue(value);
		} else {
			id.last();
			id.insertAfter(key, value);
		}
	}
	
	public static void throwAsServiceException(Throwable th) throws ServiceException {
		if ((th instanceof ServiceException)) {
			throw ((ServiceException) th);
		}
		throw new ServiceException(th);
	}
		
		
		
	// --- <<IS-END-SHARED>> ---
}

