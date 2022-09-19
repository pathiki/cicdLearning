package ny.genericServices.services;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class date

{
	// ---( internal utility methods )---

	final static date _instance = new date();

	static date _newInstance() { return new date(); }

	static date _cast(Object o) { return (date)o; }

	// ---( server methods )---




	public static final void HourIncrementDecrement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(HourIncrementDecrement)>> ---
		// @sigtype java 3.5
		// [i] field:0:required hours
		// [i] field:0:required symbol
		// [o] field:0:required results
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	hours = IDataUtil.getString( pipelineCursor, "hours" );
			String	symbol = IDataUtil.getString( pipelineCursor, "symbol" );
		pipelineCursor.destroy();
		
		String conDate = symbol+hours;
		int hoursTo = Integer.parseInt(conDate);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.HOUR, hoursTo);
		date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String strDate = sdf.format(date);
		  
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "results", strDate );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void checkStaleDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkStaleDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateToCheck
		// [o] field:0:required staleStatus
		// [o] field:0:required valueOut
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			String	dateToCheck = IDataUtil.getString( pipelineCursor, "dateToCheck" );
			String staleStatus="";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal  = Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);
			String currentDate = sdf.format(cal.getTime());
			int diff = 0;
				diff = dateToCheck.compareTo(currentDate);
			
			if(diff>0){
				//System.out.println("Date1 is after Date2");
				staleStatus=Integer.toString(diff);
			}else if(diff<0){
				//System.out.println("Date1 is before Date2");
				staleStatus=Integer.toString(diff);
			}else if(diff==0){
				//System.out.println("Date1 is equal to Date2");
				staleStatus=Integer.toString(diff);
			}else{
				//System.out.println("How to get here?");
			}
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "staleStatus", 1 );
		pipelineCursor_1.destroy();
		
			
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getFirstDayOfMonthForGivenDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFirstDayOfMonthForGivenDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inDate
		// [i] field:0:required inputFormat
		// [i] field:0:required outputFormat
		// [o] field:0:required outDate
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	inDate = IDataUtil.getString( pipelineCursor, "inDate" );
		String	inputFormat = IDataUtil.getString( pipelineCursor, "inputFormat" );
		String	outputFormat = IDataUtil.getString( pipelineCursor, "outputFormat" );
		pipelineCursor.destroy();
		
		String output = "";
		
		try
		{
			if(inDate!=null && inDate!="")
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
				Date convertedDate = dateFormat.parse(inDate);
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
							
				c.add(c.MONTH, 1);
				c.set(Calendar.DAY_OF_MONTH, 01);
				
				Date dateInTime = c.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(outputFormat); 
				output = sdf.format(dateInTime);
			
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_1, "outDate", output);
				pipelineCursor_1.destroy();
			}
			else
			{
				throw new ServiceException("Input date is either empty or null");
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void getFirstDayOfWeekForGivenDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFirstDayOfWeekForGivenDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inDate
		// [i] field:0:required inputFormat
		// [i] field:0:required outputFormat
		// [o] field:0:required outDate
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	inDate = IDataUtil.getString( pipelineCursor, "inDate" );
		String	inputFormat = IDataUtil.getString( pipelineCursor, "inputFormat" );
		String	outputFormat = IDataUtil.getString( pipelineCursor, "outputFormat" );
		pipelineCursor.destroy();
		
		String output = "";
		
		try
		{
			if(inDate!=null && inDate!="")
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
				Date convertedDate = dateFormat.parse(inDate);
				
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
				
				int weekOfYear = Integer.parseInt(inDate.substring(inDate.length()-2, inDate.length()));
				c.set(Calendar.WEEK_OF_YEAR, weekOfYear);
				c.set(Calendar.YEAR, Integer.parseInt(inDate.substring(0, 2)));
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				
				
				Date dateInTime = c.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(outputFormat); 
				output = sdf.format(dateInTime);
				
			
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_1, "outDate", output);
				pipelineCursor_1.destroy();
			}
			else
			{
				throw new ServiceException("Input date is either empty or null");
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLastDayOfMonthForGivenDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastDayOfMonthForGivenDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inDate
		// [i] field:0:required inputFormat
		// [i] field:0:required outputFormat
		// [o] field:0:required outDate
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	inDate = IDataUtil.getString( pipelineCursor, "inDate" );
		String	inputFormat = IDataUtil.getString( pipelineCursor, "inputFormat" );
		String	outputFormat = IDataUtil.getString( pipelineCursor, "outputFormat" );
		pipelineCursor.destroy();
		
		String output = "";
		
		try
		{
			if(inDate!=null && inDate!="")
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
				Date convertedDate = dateFormat.parse(inDate);
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
							
				c.add(c.MONTH, 1);
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				
				Date dateInTime = c.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(outputFormat); 
				output = sdf.format(dateInTime);
			
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_1, "outDate", output);
				pipelineCursor_1.destroy();
			}
			else
			{
				throw new ServiceException("Input date is either empty or null");
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLastDayOfWeekForGivenDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastDayOfWeekForGivenDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inDate
		// [i] field:0:required inputFormat
		// [i] field:0:required outputFormat
		// [o] field:0:required outDate
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	inDate = IDataUtil.getString( pipelineCursor, "inDate" );
		String	inputFormat = IDataUtil.getString( pipelineCursor, "inputFormat" );
		String	outputFormat = IDataUtil.getString( pipelineCursor, "outputFormat" );
		pipelineCursor.destroy();
		
		String output = "";
		
		try
		{
			if(inDate!=null && inDate!="")
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
				Date convertedDate = dateFormat.parse(inDate);
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
		
				c.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(inDate.substring(inDate.length()-2, inDate.length()))+2);
				c.set(Calendar.YEAR, Integer.parseInt(inDate.substring(0, 2)));
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				
				Date dateInTime = c.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat(outputFormat); 
				output = sdf.format(dateInTime);
			
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_1, "outDate", output);
				pipelineCursor_1.destroy();
			}
			else
			{
				throw new ServiceException("Input date is either empty or null");
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void incrementDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(incrementDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required startingDate
		// [i] field:0:required startingDateFormat
		// [i] field:0:required endingDateFormat
		// [i] field:0:optional addYears
		// [i] field:0:optional addMonths
		// [i] field:0:optional addDays
		// [i] field:0:optional addHours
		// [i] field:0:optional addMinutes
		// [i] field:0:optional addSeconds
		// [o] field:0:required endingDate
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String strStartingDate = null;
		if (idcPipeline.first("startingDate"))
		{
		  strStartingDate = (String) idcPipeline.getValue();
		}
		else
		{
		  throw new ServiceException("startingDate must be supplied!");
		}
		String strStartingDateFormat = null;
		if (idcPipeline.first("startingDateFormat"))
		{
		  strStartingDateFormat = (String) idcPipeline.getValue();
		}
		else
		{
		  throw new ServiceException("startingDateFormat must be supplied!");
		}
		String strEndingDateFormat = null;
		if (idcPipeline.first("endingDateFormat"))
		{
		  strEndingDateFormat = (String) idcPipeline.getValue();
		}
		else
		{
		  throw new ServiceException("endingDateFormat must be supplied!");
		}
		
		String strAddYears = null;
		String strAddMonths = null;
		String strAddDays = null;
		String strAddHours = null;
		String strAddMinutes = null;
		String strAddSeconds = null;
		
		if (idcPipeline.first("addYears"))
		{
		  strAddYears = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("addMonths"))
		{
		  strAddMonths = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("addDays"))
		{
		  strAddDays = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("addHours"))
		{
		  strAddHours = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("addMinutes"))
		{
		  strAddMinutes = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("addSeconds"))
		{
		  strAddSeconds = (String) idcPipeline.getValue();
		}
		
		SimpleDateFormat ssdf = new SimpleDateFormat(strStartingDateFormat);
		
		Date startingDate = null;
		try
		{
		  startingDate = ssdf.parse(strStartingDate);
		}
		catch (Exception e)
		{
		  throw new ServiceException(e.toString());
		}
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startingDate);
		
		if (strAddYears != null)
		{
		  gc.add(Calendar.YEAR, Integer.parseInt(strAddYears));
		}
		if (strAddMonths != null)
		{
		  gc.add(Calendar.MONTH, Integer.parseInt(strAddMonths));
		}
		if (strAddDays != null)
		{
		  gc.add(Calendar.DAY_OF_MONTH, Integer.parseInt(strAddDays));
		}
		if (strAddHours != null)
		{
		  gc.add(Calendar.HOUR_OF_DAY, Integer.parseInt(strAddHours));
		}
		if (strAddMinutes != null)
		{
		  gc.add(Calendar.MINUTE, Integer.parseInt(strAddMinutes));
		}
		if (strAddSeconds != null)
		{
		  gc.add(Calendar.SECOND, Integer.parseInt(strAddSeconds));
		}
		
		Date endingDate = gc.getTime();
		SimpleDateFormat esdf = new SimpleDateFormat(strEndingDateFormat);
		String strEndingDate = esdf.format(endingDate);
		
		idcPipeline.insertAfter("endingDate", strEndingDate);
		idcPipeline.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	protected static Values Memory = new Values();
		
		
		
	// --- <<IS-END-SHARED>> ---
}

