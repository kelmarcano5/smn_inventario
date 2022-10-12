package domain.repgen;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import dinamica.*;

/**
 * Genera un archivo excel con el contenido de un recordset,
 * se basa en una clase generica del framework. En general solo debe
 * implementar el metodo "createWorkbook".
 * <br><br>
 * Actualizado: 2007-05-29<br>
 * Framework Dinamica - Distribuido bajo licencia LGPL<br>
 * @author mcordova (martin.cordova@gmail.com)
 */

public class ExcelReport extends AbstractExcelOutput
{
	//SIMONE
    ServletContext _ctx = null;	
	Labels _labels = null;
	String _language = null;
	//SIMONE

	/**
	 * Export tabular data to excel document
	 * @param data Data object passed by the Transaction object to this Output object 
	 * @return Workbook
	 * @throws Throwable
	 */
	public WritableWorkbook createWorkbook(GenericTransaction data, ByteArrayOutputStream buf) throws Throwable
	{
		//SIMONE
    	_ctx =  super.getSession().getServletContext();		
		if (_ctx==null)
			throw new Throwable("Servlet Context is null - this method can't work without a ServletContext.");
		
		_labels = new Labels(_ctx);
		_language =  _ctx.getInitParameter("def-language");
		//SIMONE

		String nameCustom  = "b_financial_entities";
		String labelCustom = "${lbl:b_financial_entities}";

		String str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));
		//crear el workbook
		WritableWorkbook wb = Workbook.createWorkbook(buf);
		WritableSheet sheet = wb.createSheet(labelCustom, 0);
		WritableCellFormat dateFormat = new WritableCellFormat (new DateFormat("dd-MM-yyyy"));
		
	    //obtener recordset de data
	    Recordset rs = data.getRecordset("query.sql");
	    
		//obtener recordset de columnas dinamicas del crosstab
		Recordset rsCols = data.getRecordset("field.sql");
				
		//SIMONE
		
		 String logoPath = "/images/logo-simone-pdf.png";

		int cellExcelX = rsCols.getRecordCount() / 2;
		int cellExcelY = 1;
		int cellStep   = 0;

		BufferedImage input = ImageIO.read(getContext().getResource(logoPath));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(input, "PNG", baos);

		float imgX = 0;
		float imgY = 0;

		sheet.addImage(
				new WritableImage(
						imgX + cellStep,
						imgY,
						2,
						2,
						baos.toByteArray()
						)
				);

		Label label = new Label( 0, 0, "" ); 
		sheet.addCell(label);

		label = new Label( 0, 1, "" ); 
		sheet.addCell(label);
				
		label = new Label( cellExcelX, cellExcelY+1, str_filter ); 
		sheet.addCell(label);
		
		label = new Label( 0, 2, "" ); 
		sheet.addCell(label);
		
		//SIMONE
		
		//añadir encabezados de las columnas dinamicas en el row 0
	    int c = 0;
		rsCols.top();
		while (rsCols.next()) {
			labelCustom = rsCols.getString("alias");
			nameCustom  = labelCustom.substring(labelCustom.indexOf(":") + 1, labelCustom.indexOf("}"));

			str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));
			label = new Label(c, 3, str_filter); 
        	sheet.addCell(label);
			c++;
		}	    
	  
	    //añadir la data a partir del row 1
	    rs.top();
	    while (rs.next())
	    {
		    
		    //usar las columnas dinamicas para pedir al data
	    	//de cada columna en cada row
	    	int i = 0;
		    rsCols.top();
			while (rsCols.next()) {

				//asignar a la celda el valor segun el
				//tipo de dato que contenga
				String colName = rsCols.getString("col");
				RecordsetField rf = rs.getField(colName);
				switch (rf.getType()) {
	
					case java.sql.Types.DATE:
						DateTime date = new DateTime( i, 4, rs.getDate(colName), dateFormat );
	        			sheet.addCell(date);	
						break;
	
					case java.sql.Types.INTEGER:						
					case java.sql.Types.DOUBLE:
						Number number = new Number( i, 4, rs.getDouble(colName) ); 
	        			sheet.addCell(number);						
						break;
						
					default:
						label = new Label( i, 4, rs.getString(colName) ); 
	        			sheet.addCell(label);						
						break;
				}
				i++;
				
			}
	    }

	    //retornar el documento para su impresion hacia browser
        wb.write();
        wb.close(); 
        return wb;
		
	}
	
}
