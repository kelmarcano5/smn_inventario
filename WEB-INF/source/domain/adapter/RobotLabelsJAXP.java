package domain.adapter;

import java.io.File;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import dinamica.StringUtil;

import javax.xml.xpath.*;

/**
 * Carga el archivo labels.xml almacenado en /WEB-INF
 * y provee un método para retornar el valor de una etiqueta
 * dado su ID y un código de idioma, como "es" o "en".<br><br>
 * Creado: 2008-07-12<br>
 * Actualizado: 2008-07-12<br>
 * Framework Dinámica - (c) 2008 Martín Córdova y Asociados C.A.<br>
 * Este código se distribuye bajo licencia LGPL<br>
 * @author martin.cordova@gmail.com
 */
public class RobotLabelsJAXP 
{

	Document _doc = null;

	
	/**
	 * Constructor, se le pasa el contexto de servlets, desde una clase
	 * Output o Transaction el contexto se obtiene con el método getContext(),
	 * el servlet controlador del framework se lo pasa a estas clases.
	 * @param ctx Contexto del servlet
	 * @throws Throwable
	 */
	public RobotLabelsJAXP(String path) throws Throwable
	{
	
        //preparar parser XML estandar (JAXP)
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        
        //leer respuesta xml
        DocumentBuilder db = dbf.newDocumentBuilder();
        path = path.replaceAll("web_inf", "web-inf");
        _doc = db.parse(new File(path+"/labels.xml"));		
		
	}
	
	/**
	 * Retorna el valor de una etiqueta almacenado en labels.xml
	 * dado su ID y código de idioma.
	 * @param labelName ID del label
	 * @param language Código del idioma, como "es" o "en", por ejemplo.
	 * @return El valor de la etiqueta
	 * @throws Throwable Si no consigue la etiqueta, sea por un ID errado o por un idioma
	 * errado o no configurado en el archivo.
	 */
	public String getLabel(String labelName, String language) throws Throwable
	{

		if (labelName.contains("${lbl:"))
		{
			labelName = labelName.replace("${lbl:", "").replace("}", "").trim();
		}
		/* find label */
		XPath xpath = XPathFactory.newInstance().newXPath();
		Node node = (Node)xpath.evaluate("label[@id='" + labelName + "']", _doc.getDocumentElement(), XPathConstants.NODE);
		if (node==null)
			throw new Throwable ("Label not found: " + labelName);
		
		/* find translation for language code */
		Node node2 = (Node)xpath.evaluate("value[@language='" + language + "']", node, XPathConstants.NODE);
		if (node2==null)
			throw new Throwable ("Label translation not found for this language code: " + language);
		
		String value =  node2.getFirstChild().getNodeValue();
		value = StringUtil.replace(value, "\t", "");
		value = StringUtil.replace(value, "\n", "");
		return value.trim();
		
	}
	
}
