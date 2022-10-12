package domain;

import com.lowagie.text.Font;
import dinamica.*;

public class PDFReportOutput extends PDFGenericReport {

	/**
	 * Define el font a ser utilizado para los encabezados de la tabla,
	 * puede sobreescribir este metodo si desea cambiar el font en una
	 * subclase de este reporte
	 * @return Font
	 */
	protected Font getTableHeaderFont() {
		return new Font(Font.HELVETICA, 8f, Font.BOLD);
	}

	/**
	 * Define el font a ser utilizado para el cuerpo de la tabla,
	 * puede sobreescribir este metodo si desea cambiar el font en una
	 * subclase de este reporte
	 * @return Font
	 */	
	protected Font getTableBodyFont() {
		return new Font(Font.HELVETICA, 8f, Font.NORMAL);
	}

	/**
	 * Definir el font para el título del reporte
	 * @return
	 */
	protected Font getTitleFont() {
		return new Font(Font.HELVETICA, 10f, Font.BOLD);
	}
}
