package domain;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import dinamica.*;

public class CustomIntegerRangeValidator extends AbstractValidator
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
		//System.out.println(inputParams);
		
		String value = (String)attribs.get("value");
		
		//System.out.println("Antes de validar --> value = " + value);		
		
		if (value == null)
			throw new Throwable("Invalid attributes 'value' - cannot be null.");
		
		String str = (String)inputParams.getValue(value);
		//System.out.println("str = " + str);
		
		int digit = str.length();
		
		//System.out.println(" digit = " + digit);
		
		if ( digit >= 8 && digit <= 11 )
			return true;
		else
			return false;
			
	}

}
