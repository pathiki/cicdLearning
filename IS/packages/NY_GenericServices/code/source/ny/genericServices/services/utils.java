package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.prefs.BackingStoreException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.File;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void bytesToFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(bytesToFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required directory
		// [i] object:0:required bytes
		// [i] field:0:optional append {"false","true"}
		// [o] field:0:required length
		IDataCursor dataCursor = pipeline.getCursor();
		try
		{
		 byte[] bytes = (byte[])getRequiredObjParam(dataCursor, "bytes", byte[].class);
		  		 
		 String fileName = getRequiredStrParam(dataCursor, "filename");
		 String dirName = getRequiredStrParam(dataCursor, "directory");
		 		 
		 String appendStr = IDataUtil.getString(dataCursor, "append");
		
		 new File(dirName).mkdirs();
		 
		 streamToFile(dataCursor, new ByteArrayInputStream(bytes), dirName + fileName, appendStr, true);
		}
		catch (Exception e)
		{
		 throwAsServiceException(e);
		} finally {
		 dataCursor.destroy();
		}	
		// --- <<IS-END>> ---

                
	}



	public static final void deleteFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deleteFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fileName
		// [o] field:0:required status
		// [o] field:0:required errorMessage
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String fileName = IDataUtil.getString(idcPipeline, "fileName");
		String status;
		
		try{
			 
			File file = new File(fileName);
		 
			if(file.delete()){
				status = file.getName() + " is deleted!";
			}else{
				status = "Delete operation is failed.";
			}
			
			idcPipeline.insertAfter("status", status);
		 
		}catch(Exception e){
		 			
			idcPipeline.insertAfter("errorMessage", e.getMessage());		 
		}
		finally
		{
		   idcPipeline.destroy();
		}		
			
		// --- <<IS-END>> ---

                
	}



	public static final void moveFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(moveFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fileName
		// [i] field:0:required targetDirectory
		// [o] field:0:required status
		// [o] field:0:required errorMessage
		IDataCursor idcPipeline = pipeline.getCursor();
		String fileName = IDataUtil.getString(idcPipeline, "fileName");
		String targetDirectory = IDataUtil.getString(idcPipeline, "targetDirectory");
		String status;
		
		try
		{	   	 
			   File afile = new File(fileName);
		 
			   if(afile.renameTo(new File(targetDirectory + afile.getName())))
			   {
				   status = "File is moved successful!";
			   }
			   else
			   {
				   status = "File is failed to move!";
			   }	 
			   
			   idcPipeline.insertAfter("status", status);
		}
		catch(Exception e)
		{
			idcPipeline.insertAfter("errorMessage", e.getMessage());
		}
		finally
		{
		   idcPipeline.destroy();
		}			   	
			
		// --- <<IS-END>> ---

                
	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inString
		// [i] field:0:optional outputRecord {"true","false"}
		// [i] field:0:optional maxLength
		// [i] field:0:optional delimitor
		// [i] record:1:optional fields
		// [i] - field:0:required name
		// [i] - field:0:optional startIndex
		// [i] - field:0:optional length
		// [i] field:0:optional ignoreWhitespace {"true","false"}
		// [o] record:0:optional record
		// [o] field:1:optional list
		IDataCursor cursor = pipeline.getCursor();
		
		String line = "";
		boolean outputRecord = true;
		boolean ignoreWhitespace = true;
		String delimitor = null;
		String[] tokens = null;
		
		if (cursor.first("inString"))
		{
		  line = (String) cursor.getValue();
		  if (cursor.first("ignoreWhitespace"))
		  {
		    ignoreWhitespace = Boolean.valueOf((String) cursor.getValue()).booleanValue();
		  }
		
		  if (cursor.first("outputRecord"))
		  {
		    outputRecord = Boolean.valueOf((String) cursor.getValue()).booleanValue();
		  }
		
		  // Create an array of tokens from the String using the delimitor
		  if (cursor.first("delimitor"))
		  {
		    delimitor = (String) cursor.getValue();
		    if (delimitor != null && delimitor.length() > 0)
		    {
		      tokens = tokenize(line, delimitor);
		    }
		  }
		
		  // If the output is to be in a record format
		  if (outputRecord)
		  {
		    // Create the output record to hold the fields
		    IData outRecord = IDataFactory.create();
		    IDataCursor outRecordCursor = outRecord.getCursor();
		
		    if (cursor.first("fields"))
		    {
		      // Get the fields list
		      IData[] fields = (IData[]) cursor.getValue();
		      for (int i = 0; i < fields.length; i++)
		      {
		        // Extract the name of the current field
		        IDataCursor fieldCursor = fields[i].getCursor();
		        if (delimitor != null && delimitor.length() > 0)
		          {
		            String name = null;
		            if (fieldCursor.first("name"))
		            {
		              name = (String) fieldCursor.getValue();
		            }
		            else
		            {
		              try {
						throw new Exception("Field does not have a 'name' attribute");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		              
		            }
		
		            String token = tokens[i];
		            outRecordCursor.insertAfter(name, ignoreWhitespace ? token.trim().length() > 0 ? token.trim() : null : token);
		          }
		         else
		         {
		            
		          }
		        fieldCursor.destroy();
		      }
		    }
		    else
		    {
		      throw new ServiceException("Input 'fields' must exist when outputRecord is set to 'true'");
		    }
		
		    outRecordCursor.destroy();
		    IDataUtil.put(cursor, "record", outRecord);
		  }
		  else
		  {
		    // Simple String List needed
		    String[] array;
		
		    if (delimitor != null && delimitor.length() > 0)
		    {
		      array = new String[tokens.length];
		      for (int i = 0; i < array.length; i++)
		      {
		        array[i] = ignoreWhitespace ? tokens[i].trim().length() > 0 ? tokens[i].trim() : null : tokens[i];
		      }
		    }
		    else
		    {
		      int maxLength = 0;
		
		      if (cursor.first("maxLength"))
		      {
		        maxLength = Integer.parseInt((String) cursor.getValue());
		      }
		
		      if (maxLength <= 0)
		      {
		        throw new ServiceException("Input 'maxLength' or 'delimitor' must exist when outputRecord is set to 'false'");
		      }
		
		      array = new String[line.length() / maxLength + 1];
		      for (int i = 0; i < array.length; i++)
		      {
		        if ((i + 1) * maxLength > line.length())
		        {
		          String value = line.substring(i * maxLength);
		          array[i] = ignoreWhitespace ? value.trim().length() > 0 ? value.trim() : null : value;
		        }
		        else
		        {
		          String value = line.substring(i * maxLength, (i + 1) * maxLength);
		          array[i] = ignoreWhitespace ? value.trim().length() > 0 ? value.trim() : null : value;
		        }
		      }
		    }
		    IDataUtil.put(cursor, "list", array);
		  }
		}
		else
		{
		  throw new ServiceException("Missing input 'inString'");
		}
		
		cursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void removeNullFields (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(removeNullFields)>> ---
		// @sigtype java 3.5
		// [i] record:0:required inputDocument
		// [i] field:0:required removeEmpty
		// [i] field:0:required trimFields
		// [o] record:0:required outputDocument
		IDataCursor cursor = pipeline.getCursor();
		IData input = IDataUtil.getIData(cursor, "inputDocument");
		boolean trimFields = IDataUtil.getBoolean(cursor, "trimFields", false);
		boolean removeEmpty = IDataUtil.getBoolean(cursor, "removeEmpty", false);
		IDataUtil.put(cursor, "outputDocument", removeNullFields(input, removeEmpty, trimFields));
		cursor.destroy();
		}
		private static IData removeNullFields(IData inIData, boolean removeEmpty, boolean trimFields)
		{
		  IData outIData = IDataFactory.create();
		  IDataCursor outCursor = outIData.getCursor();
		  IDataCursor inCursor = inIData.getCursor();
		
		  while (inCursor.next())
		  {
		    Object obj = inCursor.getValue();
		    if (obj == null)
		    {
		      continue;
		    }
		
		    if (obj instanceof String)
		    {
		      String temp = (String) obj;
		      if (trimFields)
		      {
		        temp = temp.trim();
		      }
		
		      if (temp.length() > 0 || !removeEmpty)
		      {
		        IDataUtil.put(outCursor, inCursor.getKey(), temp);
		      }
		    }
		    else if (obj instanceof IData)
		    {
		      IData out = removeNullFields((IData) obj, removeEmpty, trimFields);
		      if (out != null && out.getCursor().hasMoreData())
		      {
		        IDataUtil.put(outCursor, inCursor.getKey(), out);
		      }
		    }
		    else if (obj instanceof IData[])
		    {
		      IData[] objArray = (IData[]) obj;
		      ArrayList outArrayList = new ArrayList();
		
		      for (int i = 0; i < objArray.length; i++)
		      {
		        IData out = removeNullFields(objArray[i], removeEmpty, trimFields);
		        if (out != null && out.getCursor().hasMoreData())
		        {
		          outArrayList.add(out);
		        }
		      }
		
		      IData[] outArray = null;
		      if (outArrayList.size() > 0)
		      {
		        outArray = new IData[outArrayList.size()];
		        outArrayList.toArray(outArray);
		        IDataUtil.put(outCursor, inCursor.getKey(), outArray);
		      }
		    }
		    else
		    {
		      IDataUtil.put(outCursor, inCursor.getKey(), obj);
		    }
		  }
		  outCursor.destroy();
		  inCursor.destroy();
		  return outIData;
		}
		private static IData trimValues(IData inIData, boolean removeEmptyFields)
		{
		  IData outIData = IDataFactory.create();
		  IDataCursor outCursor = outIData.getCursor();
		  IDataCursor inCursor = inIData.getCursor();
		
		  while (inCursor.next())
		  {
		    Object obj = inCursor.getValue();
		    if (obj instanceof String)
		    {
		      if (obj != null)
		      {
		        String temp = ((String) obj).trim();
		        if (temp.length() > 0 || removeEmptyFields == false)
		        {
		          IDataUtil.put(outCursor, inCursor.getKey(), temp);
		        }
		        else
		        {
		          IDataUtil.put(outCursor, inCursor.getKey(), null);
		        }
		      }
		    }
		    else if (obj instanceof IData)
		    {
		      IDataUtil.put(outCursor, inCursor.getKey(), trimValues((IData) obj, removeEmptyFields));
		    }
		    else if (obj instanceof IData[])
		    {
		      IData[] objArray = (IData[]) obj;
		      IData[] outArray = new IData[objArray.length];
		      for (int i = 0; i < objArray.length; i++)
		      {
		        outArray[i] = trimValues(objArray[i], removeEmptyFields);
		      }
		      IDataUtil.put(outCursor, inCursor.getKey(), outArray);
		    }
		    else
		    {
		      IDataUtil.put(outCursor, inCursor.getKey(), obj);
		    }
		  }
		  outCursor.destroy();
		  inCursor.destroy();
		  return outIData;
		  
		  /**
		   * The service implementations given below are read-only and show only the
		   * method definitions and not the complete implementation.
		   */
		  
			
		// --- <<IS-END>> ---

                
	}



	public static final void renameFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(renameFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required sourceFileName
		// [i] field:0:required targetFileName
		// [o] field:0:required status
		IDataCursor idcPipeline = pipeline.getCursor();
		String sourceFileName = IDataUtil.getString(idcPipeline, "sourceFileName");
		String targetFileName = IDataUtil.getString(idcPipeline, "targetFileName");
		String status;		
		
		File oldfile =new File(sourceFileName);
		File newfile =new File(targetFileName);
		 
		if(oldfile.renameTo(newfile)){
			status = "Rename succesful";
		}else{
			status = "Rename failed";
		}
		
		idcPipeline.insertAfter("status", status);
		// --- <<IS-END>> ---

                
	}



	public static final void stripLeadingZeros (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stripLeadingZeros)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inValue
		// [o] field:0:required outValue
		IDataCursor pipelineCursor = pipeline.getCursor();
		String inValue = IDataUtil.getString(pipelineCursor, "inValue");
		
		long strippedNumber = Long.parseLong(inValue);
		String outValue = strippedNumber + "";
		
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put(pipelineCursor_1, "outValue", outValue);
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void unzipFolder (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unzipFolder)>> ---
		// @sigtype java 3.5
		// [i] field:0:required zipFileName
		// [i] field:0:required targetDirectory
		// [o] field:0:required numFilesProcessed
		// [o] field:1:required unzippedFileNames
		IDataCursor idcPipeline = pipeline.getCursor();
		 
		String strZipFileName = "";
		String strTargetDirectory = "";
		
		if (idcPipeline.first("zipFileName"))
		  strZipFileName = (String) idcPipeline.getValue();
		if (idcPipeline.first("targetDirectory"))
		  strTargetDirectory = (String) idcPipeline.getValue();
		
		
		FileInputStream fileis = null;
		try
		{
		  fileis = new FileInputStream(strZipFileName);
		}
		catch (FileNotFoundException e)
		{
		  throw new ServiceException("Could not find zip file: " + strZipFileName);
		}
		
		BufferedInputStream buffileis = new BufferedInputStream(fileis);
		ZipInputStream zipis = new ZipInputStream(buffileis);
		
		Vector vecUnzippedFileNames = new Vector();
		BufferedOutputStream buffileos = null;
		FileOutputStream fileos = null;
		int intNumFilesProcessed = 0;
		ZipEntry ze = null;
		
		try
		{
		  while ((ze = zipis.getNextEntry()) != null)
		  {
		    if (ze.isDirectory())
		    {
		      continue;
		    }
		
		    // System.out.println("ZIP entry: " + ze.getName()); //debug
		
		    // Create output file
		    String strUnzippedFileName = strTargetDirectory + "/" + ze.getName();
		    fileos = new FileOutputStream(strUnzippedFileName);
		    buffileos = new BufferedOutputStream(fileos);
		
		    int intNumBytesRead;
		    byte data[] = new byte[4096];
		
		    while ((intNumBytesRead = zipis.read(data)) != -1)
		    {
		      buffileos.write(data, 0, intNumBytesRead);
		    }
		    if (buffileos != null)
			  {
			    try
			    {
			      buffileos.flush();
			      buffileos.close();
			    }
			    catch (IOException e)
			    {
			    }
			  }
			  if (fileos != null)
			  {
			    try
			    {
			      fileos.close();
			    }
			    catch (IOException e)
			    {
			    }
			  }
		    vecUnzippedFileNames.addElement(strUnzippedFileName);
		    intNumFilesProcessed++;
		  }// end while
		}
		catch (IOException e)
		{
		  throw new ServiceException("Exception occurred while handling ZIP file: " + e);
		}
		finally
		{
		  if (buffileos != null)
		  {
		    try
		    {
		      buffileos.flush();
		      buffileos.close();
		    }
		    catch (IOException e)
		    {
		    }
		  }
		  if (fileos != null)
		  {
		    try
		    {
		      fileos.close();
		    }
		    catch (IOException e)
		    {
		    }
		  }
		  if (zipis != null)
		  {
		    try
		    {
		      zipis.close();
		    }
		    catch (IOException e)
		    {
		    }
		  }
		  if (fileis != null)
		  {
		    try
		    {
		      fileis.close();
		    }
		    catch (IOException e)
		    {
		    }
		  }
		  if (buffileis != null)
		  {
		    try
		    {
		      buffileis.close();
		    }
		    catch (IOException e)
		    {
		    }
		  }
		}
		
		String arrStrUnzippedFileNames[] = new String[vecUnzippedFileNames.size()];
		Enumeration enumeration = vecUnzippedFileNames.elements();
		int i = 0;
		while (enumeration.hasMoreElements())
		{
		  arrStrUnzippedFileNames[i] = (String) enumeration.nextElement();
		  i++;
		}
		
		System.gc();
		idcPipeline.insertAfter("numFilesProcessed", Integer.toString(intNumFilesProcessed));
		idcPipeline.insertAfter("unzippedFileNames", arrStrUnzippedFileNames);
		idcPipeline.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void zipFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(zipFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required srcFileDir
		// [i] field:0:required srcFileName
		// [i] field:0:required zipFileName
		// [o] field:0:required errorMessage
		IDataCursor idcPipeline = pipeline.getCursor();
				
		String srcFileDir = IDataUtil.getString(idcPipeline, "srcFileDir");
		String srcFileName = IDataUtil.getString(idcPipeline, "srcFileName");
		String zipFileName = IDataUtil.getString(idcPipeline, "zipFileName");
		
			
		try
		{
		   	byte[] buffer = new byte[1024];
		    
			try{
		 
				FileOutputStream fos = new FileOutputStream(srcFileDir+zipFileName);
				ZipOutputStream zos = new ZipOutputStream(fos);
				ZipEntry ze= new ZipEntry(srcFileName);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(srcFileDir+srcFileName);
		 
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
		 
				in.close();
				zos.closeEntry();
		 
				//remember close it
				zos.close();
		 
				System.out.println("Done");
		 
			}catch(IOException e){
				idcPipeline.insertAfter("errorMessage", e.getMessage());
			}			
		}
		finally
		{
		   idcPipeline.destroy();
		}
		
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static String checkNull(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
	
		long length = -1L;
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
		
	private static String[] tokenize(String string, String delimitor)
	{
	  Vector vector = new Vector();
	  int index = 0;
	  int prevIndex = 0;
	
	  while ((index = string.indexOf(delimitor, prevIndex)) >= 0)
	  {
	    vector.addElement(string.substring(prevIndex, index));
	    prevIndex = index + 1;
	  }
	  vector.addElement(string.substring(prevIndex));
	  String[] array = new String[vector.size()];
	  for (int i = 0; i < array.length; i++)
	  {
	    array[i] = (String) vector.elementAt(i);
	  }
	
	  return array;
	}
		
	// --- <<IS-END-SHARED>> ---
}

