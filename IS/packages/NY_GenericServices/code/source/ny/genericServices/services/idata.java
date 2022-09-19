package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import com.wm.lang.ns.*;
// --- <<IS-END-IMPORTS>> ---

public final class idata

{
	// ---( internal utility methods )---

	final static idata _instance = new idata();

	static idata _newInstance() { return new idata(); }

	static idata _cast(Object o) { return (idata)o; }

	// ---( server methods )---




	public static final void removeNullFields (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(removeNullFields)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:required inputDocument
		// [i] field:0:optional removeEmpty {"false","true"}
		// [i] field:0:optional trimFields {"false","true"}
		// [o] record:0:required outputDocument
		IDataCursor cursor = pipeline.getCursor();
		IData input = IDataUtil.getIData(cursor, "inputDocument");
		boolean trimFields = IDataUtil.getBoolean(cursor, "trimFields", false);
		boolean removeEmpty = IDataUtil.getBoolean(cursor, "removeEmpty", false);
		IDataUtil.put(cursor, "outputDocument", removeNullFields(input, removeEmpty, trimFields));
		cursor.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
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
	}
		
	private static void copyFields(IData idataIn, IData idataOut) throws ServiceException, IOException
	{
		IDataCursor idcDocumentIn = idataIn.getCursor();
		IDataCursor idcDocumentOut = idataOut.getCursor();		
		String keys[] = ValuesEmulator.getKeys(idataIn);
	 try{	
		for(int i = 0; i < keys.length; i++){
			Object objIn = IDataUtil.get(idcDocumentIn, keys[i]);
			Object objOut = IDataUtil.get(idcDocumentOut, keys[i]);
			if (objIn != null){
				if (objIn instanceof IData){	
					System.out.println("IDATA Structure");
					System.out.println("Object Out" + objOut);
					if (objOut != null)
						copyFields((IData)objIn, (IData)objOut );
					IDataUtil.put(idcDocumentOut, keys[i], objOut);
				}else if (objIn instanceof IData[]){
					//This array will be of variable size
					System.out.println("IDATA Array Structure");
					IData[] objInArray = (IData[])objIn;
				//	This array will be of size 1
					IData[] innerIData = IDataUtil.getIDataArray(idcDocumentOut, keys[i]);
					IDataCursor idcInnerIData = innerIData[0].getCursor();
					String innerKeys[] = ValuesEmulator.getKeys(innerIData[0]);
					IData[] objOutArray = new IData[objInArray.length];		 		
				
				
						for(int ii = 0; ii < objInArray.length; ii++ ){
							objOutArray[ii] = IDataFactory.create();
							IDataCursor idcObjOutArray = objOutArray[ii].getCursor();
							for (int iii = 0; iii < innerKeys.length; iii++ ){
							
								//System.out.println("Inner Keys -->" + innerKeys[iii] );
							if (innerKeys[iii] != null){
								
								Object obj = IDataUtil.get(idcInnerIData, innerKeys[iii]);
								System.out.println("Inner Keys -->" + innerKeys[iii] );
								System.out.println("Inner Values -->" + obj.toString() );
								if (obj != null){
									if(obj instanceof IData)
										IDataUtil.put(idcObjOutArray, innerKeys[iii], IDataUtil.deepClone((IData)obj));
									else if(obj instanceof IData[])
										IDataUtil.put(idcObjOutArray, innerKeys[iii], (IData[])obj);
									else
										IDataUtil.put(idcObjOutArray, innerKeys[iii], obj);
								}
							
								copyFields(objInArray[ii], objOutArray[ii]);
							}
							}
						}
						IDataUtil.put(idcDocumentOut, keys[i],objOutArray);	
				}else{								
					IDataUtil.put(idcDocumentOut, keys[i],objIn);
					System.out.println("Key is --> " + keys[i]);
					System.out.println("Value is --> " + objIn.toString());
				}
			}
		}
	 }catch(Exception e){
		 e.printStackTrace();
		 throw new ServiceException(e);
	 }
		idcDocumentIn.destroy();
		idcDocumentOut.destroy();
		
	}
	// --- <<IS-END-SHARED>> ---
}

