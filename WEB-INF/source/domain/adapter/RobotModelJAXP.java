package domain.adapter;

import java.io.File;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.xml.xpath.*;

/**
 * Carga el archivo action.xml generado por el plugin de dinámica para almacenar el modelo amateras ERD
 * y provee un método para alamacenar el valor de los atributos en una tabla.<br><br>
 * Creado: 2015-12-03<br>
 * Actualizado: 2015-12-03<br>
 * Framework Dinámica - (c) 2015 SIMONE C.A.<br>
 * @author juanc.eduardoa@omzyasociados.com
 */
public class RobotModelJAXP 
{

	Document _doc = null;

	
	/**
	 * Constructor, se le pasa la ruta donde se encuentra el archivo XML generado
	 * por el framework, este archivo debe renombrarse al estandard action.xml.
	 * @param path Ruta donde se encuentra en archivo action.xml
	 * @throws Throwable
	 */
	public RobotModelJAXP(String path) throws Throwable
	{
	
        //preparar parser XML estandar (JAXP)
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        
        //leer respuesta xml
        DocumentBuilder db = dbf.newDocumentBuilder();
        _doc = db.parse(new File(path + "/smn_xml/action.xml"));		
		
	}
		
	public void setFilterValueModel(RobotFilter filter) throws Throwable
	{
		/* find filter */
		XPath xpath = XPathFactory.newInstance().newXPath();

		RobotUtil.logger.info("Loading attributes of Filter"); 
		String expression = "/model/filter/row/@*";
		NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(_doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
		    Attr attr = (Attr) nodeList.item(i);
		    String key = attr.getName();
		    String value = attr.getValue();
		    filter.put(key, value);
		    RobotUtil.logger.info("Attribute = " + key + " | Value = " + value); 
		}
	}
	
	public void setDataGridValueModel(RobotDataGrid grid) throws Throwable
	{
		/* find filter */
		XPath xpath = XPathFactory.newInstance().newXPath();

		RobotUtil.logger.info("Loading attributes of DataGrid"); 
		String expression = "/model/grid/row/@*";
		NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(_doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
		    Attr attr = (Attr) nodeList.item(i);
		    String key = attr.getName();
		    String value = attr.getValue();
		    grid.put(key, value);
		    RobotUtil.logger.info("Attribute = " + key + " | Value = " + value); 
		}
	}
	
}
