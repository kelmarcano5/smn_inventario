package domain.repgen;

import java.io.ByteArrayOutputStream;
import javax.servlet.ServletContext;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import dinamica.*;

/**
 * Reporte PDF (IText) basico con logo, titulo, filtro de busqueda, tabla, 
 * total, grafico y pie de pagina hecho con codigo, con estilo "pagina X de Y". 
 * Para el manejo de los eventos que imprimen el pie de pagina, esta clase usa
 * una clase generica del framework llamada "PDFPageEvents". Puede proveer
 * su propio manejador de eventos si tiene necesidades mas elaboradas.
 * <br><br>
 * Actualizado: 2007-05-29<br>
 * Framework Dinamica - Distribuido bajo licencia LGPL<br>
 * @author mcordova (martin.cordova@gmail.com)
 */
public class PDFReport extends AbstractPDFOutput
{

	//parametros requeridos para el footer
	PdfTemplate tpl = null;
	BaseFont bf = null;
	PdfContentByte cb = null;
	Image img = null;
	Font tblHeaderFont = null;
	Font tblBodyFont = null;

	//cambiar: parametros generales del reporte	
	String reportTitle = ""; //lo lee de config.xml por defecto
	String footerText  = ""; //lo lee de web.xml o config.xml por defecto
	String logoPath    = "/images/logo-simone-pdf.png"; //ubicacion del logotipo
	String pageXofY    = " de ";  //texto por defecto para Pagina X de Y

	//SIMONE
	ServletContext _ctx = null;	
	Labels _labels = null;
	String _language = null;
	//SIMONE

	protected void createPDF(GenericTransaction data, ByteArrayOutputStream buf)
			throws Throwable
	{
		//SIMONE
		_ctx =  super.getSession().getServletContext();		
		if (_ctx==null)
			throw new Throwable("Servlet Context is null - this method can't work without a ServletContext.");

		_labels = new Labels(_ctx);
		_language =  _ctx.getInitParameter("def-language");
		//SIMONE

		//inicializar documento: tamano de pagina, orientacion, margenes
		Document doc = new Document();
		PdfWriter docWriter = PdfWriter.getInstance(doc, buf);
		doc.setPageSize(PageSize.LETTER);
		doc.setMargins(30,30,30,40);

		doc.open();

		//crear fonts por defecto
		tblHeaderFont = new Font(Font.HELVETICA, 10f, Font.BOLD);
		tblBodyFont = new Font(Font.HELVETICA, 10f, Font.NORMAL);

		//definir pie de pagina del lado izquierdo
		String footerText = this.getFooter(); //read it from config.xml or web.xml
		String reportDate = StringUtil.formatDate(new java.util.Date(), "dd-MM-yyyy HH:mm");

		//crear template (objeto interno de IText) y manejador de evento 
		//para imprimir el pie de pagina
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		cb = docWriter.getDirectContent();
		tpl = cb.createTemplate(20, 14);
		docWriter.setPageEvent(new PDFPageEvents(footerText, pageXofY, tpl, bf, cb, reportDate));

		//titulo - lo lee de config.xml por defecto
		reportTitle = getReportTitle();
		Paragraph t = new Paragraph(reportTitle, new Font(Font.HELVETICA, 14f, Font.BOLD));
		t.setAlignment(Rectangle.ALIGN_RIGHT);
		doc.add(t);

		//logo
		img = Image.getInstance(getImage(this.getServerBaseURL() + logoPath, false));
		img.scalePercent(100);
		float imgY = doc.top() - img.getHeight();
		float imgX = doc.left();
		img.setAbsolutePosition(imgX, imgY);
		doc.add(img);	

		//imprimir filtro de busqueda
		PdfPTable filter = getReportFilter(data);
		filter.setSpacingBefore(40);
		doc.add(filter);

		//imprimir tabla principal de data con totales
		PdfPTable datatbl1 = getDataTable(data);
		datatbl1.setSpacingBefore(10);
		doc.add(datatbl1);

		doc.close();
		docWriter.close();

	}

	/**
	 * Retorna una tabla conteniendo el filtro de busqueda
	 * @param data Objeto de negocios que suple los recordsets
	 * @return
	 */
	PdfPTable getReportFilter(GenericTransaction data) throws Throwable
	{

		//obtener recordset de filtro
		Recordset rs = data.getRecordset("repgen.filter");
		rs.first();

		//definir estructura de la tabla
		PdfPTable datatable = new PdfPTable(2);
		datatable.getDefaultCell().setPadding(3);
		int headerwidths[] = {50,50};
		datatable.setWidths(headerwidths);
		datatable.setWidthPercentage(45);

		PdfPCell c = null;
		String d = null;

		String name  = "b_filter_find";
		String label = "${lbl:b_filter_find}";

		String str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
		//encabezado para toda la tabla
		c = new PdfPCell(new Phrase(str_filter, tblHeaderFont ));
		c.setGrayFill(0.95f);
		c.setColspan(2);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		datatable.addCell(c);

		name  = "b_code";
		label = "${lbl:b_code}";

		str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
		//fila 2 - campo de filtro
		c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
		c.setGrayFill(0.95f);
		c.setHorizontalAlignment(Element.ALIGN_LEFT);
		datatable.addCell(c);

		//fila 1 - valor de busqueda
		d = rs.getString("efi_codigo");
		if (d != null) 
		{
			d = d.replaceAll("%", "");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);	
		}

		//fila 1 - valor de busqueda
		d = rs.getString("efi_nombre");
		if (d != null)
		{
			name  = "b_name";
			label = "${lbl:b_name}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));

			//fila 2 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			d = d.replaceAll("%", "");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);	
		}

		//fila 1 - valor de busqueda
		d = rs.getString("pai_nombre_largo");
		if (d != null)
		{
			name  = "b_country";
			label = "${lbl:b_country}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			//fila 2 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			d = d.replaceAll("%", "");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);
		}

		//fila 1 - valor de busqueda
		d = rs.getString("efe_nombre_largo");
		if (d != null)
		{
			name  = "b_federal_entity";
			label = "${lbl:b_federal_entity}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			//fila 2 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			d = d.replaceAll("%", "");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);
		}

		//fila 1 - valor de busqueda
		d = rs.getString("ciu_nombre_largo");
		if (d != null)
		{
			name  = "b_city";
			label = "${lbl:b_city}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			//fila 2 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			d = d.replaceAll("%", "");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);
		}

		//fila 1 - valor de busqueda
		d = StringUtil.formatDate(rs.getDate("fdesde"), "dd-MM-yyyy");
		if (d != null)
		{
			name  = "b_store_date_grater_than";
			label = "${lbl:b_store_date_grater_than}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			//fila 1 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);	
		}

		//fila 2 - valor de busqueda
		if (d != null)
		{
			name  = "b_store_date_less_than";
			label = "${lbl:b_store_date_less_than}";

			str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			//fila 2 - campo de filtro
			c = new PdfPCell(new Phrase(str_filter, tblHeaderFont));
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);

			d = StringUtil.formatDate(rs.getDate("fhasta"), "dd-MM-yyyy");
			c = new PdfPCell( new Phrase( d , tblBodyFont ) );
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);
		}

		return datatable;

	}


	/**
	 * Retorna una tabla conteniendo la data y los totales, con 
	 * la variante de que imprime de manera dinamica las columnas
	 * variables del crosstab.
	 * @param data Objeto de negocios que suple los recordsets
	 * @return
	 */
	PdfPTable getDataTable(GenericTransaction data) throws Throwable
	{

		//obtener recordset de columnas dinamicas del crosstab
		Recordset rsCols = data.getRecordset("field.sql");
		rsCols.top();

		//obtener recordset de data
		Recordset rs = data.getRecordset("query.sql");
		rs.top();

		//definir estructura de la tabla
		//dejar que IText calcule los anchos de columnas
		PdfPTable datatable = new PdfPTable(rsCols.getRecordCount());
		datatable.getDefaultCell().setPadding(3);
		datatable.setWidthPercentage(100);
		datatable.setHeaderRows(1);

		PdfPCell c = null;
		String v = "";

		//añadir columnas dinámicas
		while (rsCols.next()) {

			String label = rsCols.getString("alias");
			String name  = label.substring(label.indexOf(":") + 1, label.indexOf("}"));

			String str_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));
			c = new PdfPCell( new Phrase(str_filter, tblHeaderFont) );
			c.setGrayFill(0.95f);
			c.setHorizontalAlignment(Element.ALIGN_LEFT);
			datatable.addCell(c);
		}

		//imprimir cuerpo de la tabla
		while (rs.next())
		{
			int align = 0;
			rsCols.top();
			while (rsCols.next()) {
				//saber la alineación de cada registro
				if (rsCols.getString("align").equals("center"))
					align=Element.ALIGN_CENTER;
				else {
					if (rsCols.getString("align").equals("left"))
						align=Element.ALIGN_LEFT;
					else
						align=Element.ALIGN_RIGHT;
				}  

				if (rsCols.getString("type").equals("VARCHAR") || rsCols.getString("type").equals("INTEGER")){
					v = rs.getString(rsCols.getString("col"));
				}
				else {
					//remplazar el @ para poder aplicar formato
					String format = rsCols.getString("format").replace("@", "").trim();
					if (rsCols.getString("type").equals("DATE")){
						v = StringUtil.formatDate(rs.getDate(rsCols.getString("col")), format);	
					}
					else {
						v = StringUtil.formatNumber(rs.getValue(rsCols.getString("col")),format);
					}
				}

				c = new PdfPCell( new Phrase( v, tblBodyFont ) );
				c.setHorizontalAlignment(align);
				datatable.addCell(c);	

			}
		}

		return datatable;

	}    

}
