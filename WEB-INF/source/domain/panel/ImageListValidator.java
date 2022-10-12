package domain.panel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import dinamica.AbstractValidator;
import dinamica.Recordset;
import dinamica.xml.Element;

/**
 * Obtiene la ruta donde se encuentra las imagenes para el panel
 * de la aplicacion.<br><br>
 * Fecha de creacion: 2011-07-21<br>
 * Fecha de actualizacion: 2011-07-21<br>
 * @author Martin Cordova y Asociados C.A
 *
 */
public class ImageListValidator extends AbstractValidator implements EntityResolver {

	StringBuilder s = new StringBuilder();
	
	@Override
    public InputSource resolveEntity(String arg0, String arg1)
                    throws SAXException, IOException {
            InputSource s =  new InputSource(getClass().getResourceAsStream("/javax/servlet/resources/web-app_2_3.dtd"));
            s.setPublicId(arg0);
            s.setSystemId("javax.servlet.resources.web-app_2_3.dtd");
            return s;
    }
	
	@Override
	public boolean isValid(HttpServletRequest req, Recordset inputParams,
			HashMap<String, String> attribs) throws Throwable {
		
		boolean flag = true;
		boolean app = true;
		
		if(getContext().getInitParameter("base-dir") == null || getContext().getInitParameter("base-dir").equals("")) {
			s.append("Parámetro [base-dir] no encontrado en el web.xml" + "<br>");
			flag = false;
		}
		
		File fsource = new File(getContext().getInitParameter("base-dir"));
		
		if (!fsource.exists()) {
			s.append("Directorio invalido: " + getContext().getInitParameter("base-dir") + "<br>");
			flag = false;
		}
		
		String items[] = fsource.list();
		if(items!=null) {
			for (int i=0;i<items.length;i++)
			{
				String pathAppWebXML = fsource.getPath() + File.separator + items[i] + File.separator + "WEB-INF" + File.separator + "web.xml";
				
				File f = new File(pathAppWebXML);
				if (f.isFile()) {
					FileInputStream  webXML = new FileInputStream(f);
					dinamica.xml.Document doc = new dinamica.xml.Document(webXML,this);
					Element initParams[] = doc.getElements("filter[filter-name = 'SecurityFilter']/init-param/param-value");
			
					if(initParams.length > 0){
						
						Element paramValue = initParams[0];
						if(paramValue.getValue().equals(inputParams.getString("alias"))){
							
							app = false;
							
							String imagePath = fsource.getPath() + File.separator + items[i] + File.separator + "images" + File.separator + "panel" + File.separator;
							File fImagePath = new File(imagePath);
							if (!fImagePath.exists()) {
								s.append("Directorio invalido: " + imagePath + "<br>");
								flag = false;
							}
							
							inputParams.setValue("image-path", imagePath);
							inputParams.setValue("app", items[i]);
						}		
					}
				} 
				
			}
			
			
			if(app) {
				s.append("Alias de la aplicación no encontrado: " + inputParams.getString("alias") + "<br>");
				flag = false;
			
			}
		}
		
		return flag;
	}

	@Override
	public String getErrorMessage() {
		
		return s.toString();
	}
	
	

}
