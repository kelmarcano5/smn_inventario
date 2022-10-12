package domain;

import javax.servlet.ServletContext;

import dinamica.*;

/**
 * Genera un archivo excel con el contenido de un recordset,
 * se basa en una clase generica del framework. En general solo debe
 * implementar el metodo "print".
 * <br><br>
 */

public class XmlReportOutput extends GenericOutput
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

		String nameCustom  = "b_communities";
		String labelCustom = "${lbl:b_communities}";

		String str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));		
		
		getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + str_filter + ".xml\";");
		
		//SIMONE
        
		super.print(te, data);
    }

}

