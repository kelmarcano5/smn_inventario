package domain.repgen.smn_configuracion.smn_documento;

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
		
		String nameCustom  = "b_document";
		String labelCustom = "${lbl:b_document}";


		String str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));
		//SIMONE
		
		//crear el workbook
		WritableWorkbook wb = Workbook.createWorkbook(buf);
		WritableSheet sheet = wb.createSheet(str_filter, 0);
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
		
		label = new Label( 0, cellExcelY+2, "" ); 
		sheet.addCell(label);
				
		//SIMONE
		WritableCellFormat wcf = new WritableCellFormat();
		wcf = setBackground(jxl.format.Colour.GRAY_25, jxl.format.Alignment.CENTRE, jxl.format.Border.ALL);
		
		//añadir encabezados de las columnas dinamicas en el row 0
	    int c = 0;
		rsCols.top();
		while (rsCols.next()) {
			labelCustom = rsCols.getString("alias");
			nameCustom  = labelCustom.substring(labelCustom.indexOf(":") + 1, labelCustom.indexOf("}"));
			
			str_filter = StringUtil.replace(labelCustom, labelCustom, _labels.getLabel(nameCustom, _language));
			label = new Label(c, cellExcelY+2, str_filter, wcf); 
        	sheet.addCell(label);
			c++;
		}	    
	  
	    //añadir la data a partir del row 1
		int i = 2;
	    rs.top();
	    while (rs.next())
	    {
	    	//System.out.print("Rows...");
		    //usar las columnas dinamicas para pedir al data
	    	//de cada columna en cada row
	    	int j = 0;
		    rsCols.top();
			while (rsCols.next()) {

				//asignar a la celda el valor segun el
				//tipo de dato que contenga
				String colName = rsCols.getString("col");
				//System.out.print("Colname: " + colName);
				RecordsetField rf = rs.getField(colName);
				switch (rf.getType()) {
	
					case java.sql.Types.DATE:
						DateTime date = new DateTime( j, i+2, rs.getDate(colName), dateFormat );
						//System.out.println(" | date: " + date.toString());
	        			sheet.addCell(date);	
						break;
	
					case java.sql.Types.INTEGER:						
					case java.sql.Types.DOUBLE:
						Number numberDbl = new Number( j, i+2, rs.getDouble(colName) ); 
						//System.out.println(" | int: " + numberDbl.getValue());
	        			sheet.addCell(numberDbl);						
						break;
						
					default:
						label = new Label( j, i+2, rs.getString(colName) ); 
						//System.out.println(" | label: " + label.getString());
	        			sheet.addCell(label);						
						break;
				}
				j++;
			}
			i++;
	    }

	    //retornar el documento para su impresion hacia browser
	    //System.out.println("Finish.");
        wb.write();
        wb.close(); 
        return wb;
		
	}
	
	private WritableCellFormat setBackground(jxl.format.Colour colour, jxl.format.Alignment align, jxl.format.Border border) throws Throwable {
		
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBackground(colour);
		wcf.setAlignment(align);
		wcf.setBorder(border, jxl.format.BorderLineStyle.THIN);
		
		return wcf;
		
	}
	
}
