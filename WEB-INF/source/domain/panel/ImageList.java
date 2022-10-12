package domain.panel;

import java.io.File;
import dinamica.*;

/**
 * Obtiene el recordset con la lista de imagenes<br><br>
 * Fecha de creacion: 2011-07-21<br>
 * Fecha de actualizacion: 2011-07-21<br>
 * @author Martin Cordova y Asociados C.A
 */
public class ImageList extends FolderFileList {

	String path="";
	
	@Override
	public Recordset getRecordset(Recordset inputParams) throws Throwable {
		
		Recordset rs = (Recordset)getSession().getAttribute("panel.validator");
		rs.first();
		path = rs.getString("image-path");
		
		return super.getRecordset(inputParams);
	}

	@Override
	public String getFolderName() throws Throwable {
		return path;
	}

	@Override
	public boolean accept(File dir, String name) {
		
		if	(	name.toLowerCase().endsWith(".gif") || 
				name.toLowerCase().endsWith(".jpg") || 
				name.toLowerCase().endsWith(".jpeg") ||
				name.toLowerCase().endsWith(".jpe") ||
				name.toLowerCase().endsWith(".jfif") ||
				name.toLowerCase().endsWith(".png") ||
				name.toLowerCase().endsWith(".bmp") ||
				name.toLowerCase().endsWith(".tiff") ||
				name.toLowerCase().endsWith(".tif")
			)
			return true;
		else
			return false;
	}

}
