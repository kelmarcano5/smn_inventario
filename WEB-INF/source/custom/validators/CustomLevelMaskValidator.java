package custom.validators;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import dinamica.*;

/**
 * Generic validator for integer value ranges (value-from - value-to).<br>
 * Will return FALSE if value2 &lt; value1, requires two custom attributes named "value1" and "value2",
 * representing the field names of the two recordset fields to use
 * in the validation logic. Returns TRUE if any of the parameters is null. 
 * 
 * <br>
 * Creation date: 3/march/2004<br>
 * Last Update: 3/march/2004<br>
 * (c) 2004 Martin Cordova<br>
 * This code is released under the LGPL license<br>
 * @author Martin Cordova (dinamica@martincordova.com)
 * */
public class CustomLevelMaskValidator extends AbstractValidator
{

	/* (non-Javadoc)
	 * @see dinamica.AbstractValidator#isValid(javax.servlet.http.HttpServletRequest, dinamica.Recordset, java.util.HashMap)
	 */
	public boolean isValid(
		HttpServletRequest req,
		Recordset inputParams,
		HashMap<String, String> attribs)
		throws Throwable
	{
		String v1 = (String)attribs.get("level");
		String v2 = (String)attribs.get("mask");
		
		if (v1==null || v2==null)
			throw new Throwable("Invalid attributes 'level' or 'mask' - cannot be null.");
		
		if (inputParams.isNull(v1) || inputParams.isNull(v2))
			return true;
			
		int d1 = inputParams.getInt(v1);
		String d2 = inputParams.getString(v2);
		int d3 = d2.length();
		
/**	(Comentario)	System.out.println(d1 + " " + d3); */
				
		if ( d1 != d3 )
			return false;
		else
			return true;
			
	}

}
