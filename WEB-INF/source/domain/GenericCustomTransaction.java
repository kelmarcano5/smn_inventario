package domain;

import dinamica.GenericTransaction;
import dinamica.Recordset;

public class GenericCustomTransaction extends GenericTransaction
{
	
	public int service(Recordset inputParams) throws Throwable
	{
		int interval = getSession().getMaxInactiveInterval();
		getRequest().setAttribute("idle_timeout", String.valueOf(interval));

		return super.service(inputParams);
	}
	
}
