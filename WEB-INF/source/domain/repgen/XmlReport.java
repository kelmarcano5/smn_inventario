package domain.repgen;

import javax.servlet.ServletContext;

import dinamica.*;

/**
 * Genera un archivo excel con el contenido de un recordset,
 * se basa en una clase generica del framework. En general solo debe
 * implementar el metodo "print".
 * <br><br>
 */

public class XmlReport extends GenericOutput
{
    ServletContext _ctx = null;	
	Labels _labels = null;
	String _language = null;
		
	@Override
	public void print(TemplateEngine te, GenericTransaction data) throws Throwable
	{
		//SIMONE
    	_ctx =  super.getSession().getServletContext();		
		if (_ctx==null)
			throw new Throwable("Servlet Context is null - this method can't work without a ServletContext.");
		
		_labels = new Labels(_ctx);
		_language =  _ctx.getInitParameter("def-language");
		//SIMONE

		String nameCustom  = "b_communities";
		String labelCustom = "${lbl:b_communities}";

		String str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));		
		//System.out.println("--> TextReport");
		
		getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + str_filter + ".xml\";");
		
		/* for each print command */
		if (data!=null)
		{			
			Recordset rs = getConfig().getPrintCommands();
			
			//System.out.println("--> recordset: " + rs.getRecordCount());

			while (rs.next())
			{

				String nullExpr = null;
				if (getConfig().contentType.equals("plain/txt"))
					nullExpr = "&nbsp;";
				else
					nullExpr = "";

				String mode = (String)rs.getValue("mode");
				String tag = (String)rs.getValue("tag");
				String rsname = (String)rs.getValue("recordset");
				String nullValue = (String)rs.getValue("null-value");
				//System.out.println("--> Mode: " + mode + "  Tag: " + tag + "  RSName: " + rsname + "   NullValue: " + nullValue);

				if (nullValue!=null)
					nullExpr = nullValue;

				if (mode.equals("plain"))
				{
					Recordset x = data.getRecordset(rsname);
					te.replace(x,nullExpr,tag);
				}
				else if (mode.equals("clear"))
				{
					te.clearFieldMarkers();
				}
				else
				{
					throw new Throwable("Invalid print mode [" + mode + "] attribute in config.xml: " + getConfig().path);
				}

			}

		}

	}
	
	/**
	 * Read a XML-related config parameter. Will search first
	 * in the Action config.xml, then in web.xml file.
	 * @param param
	 * @return The corresponding value or NULL if not found.
	 */
	protected String getXmlConfigValue(String param)
	{
		String value = null;
		//System.out.println("value --> " + param);
		try
		{
			value = getConfig().getConfigValue(param);
			if (value==null || value.trim().equals(""))
			{
				value = getContext().getInitParameter(param);
			}
		}
		catch (Throwable e)
		{
			value = getContext().getInitParameter(param);
			if (value==null)
				value = "";
		}
		//System.out.println("value --> " + value);
		return value;
	}

	/**
	 * Sets the value of the Content-Disposition response header, by default
	 * it will be [inline; filename="report.xml";], but can be overriden or
	 * configured using the config element pdf-file-name in config.xml
	 * @return Content-Disposition response header value
	 */
	protected String getFileName()
	{
		String value = getXmlConfigValue("txt-file-name");
		//System.out.println("value --> " + value);
		if(value.equals(""))
			value = "inline; filename=\"xml-report.txt\";";
		else
			value = "inline; filename=\""+ value +"\";";
		return value;
	}

}
