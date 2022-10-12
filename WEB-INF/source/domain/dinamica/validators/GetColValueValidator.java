package domain.dinamica.validators;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import dinamica.AbstractValidator;
import dinamica.Db;
import dinamica.Recordset;
import dinamica.TemplateEngine;

/**
 * Permite ejecutar un SQL usando como filtro los campos del request para obtener un valor el cual sera<br>
 * almacenado en un campo del recordset de request, un campo calculado.<br>
 * El campo que retorna el SQL debe tener el mismo nombre que tiene el campo calculado en el recordset del request.<br>
 * En caso que el SQL no retorne registro esta clase arrojara un mensaje de error.<br>
 * El mensaje de error permite usar markers con los campos del recordset del request.
 * <br><br>
 * Ejemplo de como utilizarlo:<br>
 * <xmp>
 * <custom-validator classname='dinamica.validators.GetColValueValidator' on-error-label='El código no está registrado.' parameter='codigo' sql="getcolvalue.sql"/>
 * <br>
 * NOTA: en el ejemplo de arriba, el query getcolvalue.sql DEBE contener un campo llamado codigo, use un alias de ser necesario.
 * </xmp>
 *  <br><br>
 * Last update: 2015-02-15<br>
 * (c) 2004-2013 Martin Cordova<br>
 * This code is released under the LGPL license<br>
 * @author Martin Cordova y Asociados, C.A. (martin.cordova@gmail.com)
 * */
public class GetColValueValidator extends AbstractValidator {

	String _errorMsg = "";
	Recordset _rs = null;
	
	@Override
	public boolean isValid(HttpServletRequest req, Recordset inputParams,
			HashMap<String, String> attribs) throws Throwable {
		
		boolean flag = true;
		
		//get db channel
		Db db = getDb();

		//detect if sql parameter was passed to the validator
		boolean bSql = attribs.containsKey("sql");
		if (!bSql)
			throw new Throwable("Bad configuration: 'sql' attribute not found.");
	
		String setValue = attribs.get("parameter");
		
		_errorMsg = attribs.get("on-error-label");
		
		String dataSourceName = null;
		boolean bDSN = attribs.containsKey("datasource");
		if (bDSN)
			dataSourceName = attribs.get("datasource");

		String sql = "";
		String query = (String)attribs.get("sql");
		sql = getResource(query);
		sql = getSQL(sql, inputParams);
		
		_rs = inputParams;
		
		Recordset rs = null;
		
		//execute sql
		if (dataSourceName!=null)
			rs = dbGet(dataSourceName, sql);
		else
			rs = db.get(sql);
		
		if(rs.getRecordCount()==0)
			flag = false;
		else {
			rs.first();
			Object value = rs.getValue(setValue);
			inputParams.setValue(setValue, value);
		}
		
		return flag;
	}
	
	@Override
	public String getErrorMessage() {
		String msg =  _errorMsg;
		if (msg.indexOf("${fld:")>=0) {
			TemplateEngine t = new TemplateEngine(getContext(),getRequest(), msg);
			try {
				_rs.first();
				t.replace(_rs, "");
				msg = t.toString();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

}
