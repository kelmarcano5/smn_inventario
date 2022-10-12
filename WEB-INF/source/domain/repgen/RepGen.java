package domain.repgen;

import dinamica.*;
import dinamica.xml.*;

/**
 * Motor de busqueda, construye un SQL condicionalmente
 * de acuerdo a los parametros recibidos, a su vez los campos del select estan
 * condicionados por parametros en un array de entrada. A su vez ejecuta queries
 * para obtener la anchura total de la tabla HTML, ya que puede variar al tratarse de columnas
 * dinamicas. Ejecuta el query base y retorna 0 o 1 dependiendo de si el recordset tiene o no
 * registros. Esta clase dejara los recordset en sesion para que pueda ser publicados.
 * <br><br>
 * */
public class RepGen extends GenericTransaction {

	//define el ID del recordset base a publicar
	String _rsName = "query.sql";
	//define el ID del recordset que contrendra los campos a publicar
	String _rsField = "field.sql";
	String _rsField2 = "field2.sql";
	//define el ID del recordset de la anchura total de la tabla HTML
	String _rsWidth = "total_width.sql";

	@Override
	public int service(Recordset inputParams) throws Throwable {

		//reutiliza la logica de la clase padre
		int rc = super.service(inputParams);

		/* ensamblar query que tendra la lista de los campos, que seran inyectados en la condición select */

		//carga el template de los campos de la sentencia "select" del query principal
		String qfield = getResource("query-field.sql");
		String qfield2 = getResource("query-field2.sql");
		//System.out.println("qfield --> " + qfield);

		//ahora remplaza las condiciones en el query que retornará los campos
		//que irán en la condición del select.
		String fields = getSqlIN((Recordset)getRequest().getAttribute("fields"), "field_id");
		//System.out.println("fields --> " + fields);
		qfield = StringUtil.replace(qfield,"${fields}", fields);
		qfield2 = StringUtil.replace(qfield2,"${fields}", fields);
		//System.out.println("qfield --> " + qfield);

		//ejecutar query que retornará los campos del select y crea recordset
		Recordset rsField = getDb().get(qfield);
		Recordset rsField2 = getDb().get(qfield2);

		/* listo el query de la lista de los campos - quedo ensamblado */

		/* ensamblar query del anchos total de la tabla HTML */

		//carga el template con en el ancho total
		//el cual servira para dar el tamaño total de la tabla en HTML
		String qwidth = getResource("total_width.sql");
		//System.out.println("qwidth --> " + qwidth);

		//ahora reemplaza la condicion con los ID de los campos del select en el query que tendra
		//los anchos totales
		qwidth = StringUtil.replace(qwidth,"${fields}", fields);
		//System.out.println("qwidth --> " + qwidth);

		//ejecutar query de anchura total y crear recordset
		Recordset rsWidth = getDb().get(qwidth);

		/* listo el query de la anchura total - quedo ensamblado */

		/* ensamblar query-base */

		//carga el template base del query
		String qBase = getResource("query-base.sql");
		//System.out.println("qBase --> " + qBase);

		//llama a un metodo que recibe como el recordset con la lista de los campos del select
		//retorna un string con los nombres de los campos separados por coma
		String fieldSelect = buildArrayRecordset (rsField);		
		//System.out.println("fieldSelect --> " + fieldSelect);

		//ahora reemplaza los campos del select en el query base
		qBase = StringUtil.replace(qBase,"${field}", fieldSelect);
		//System.out.println("qBase --> " + qBase);

		/* listo el query-base - quedo ensamblado */

		//ahora reemplaza los valores de los parametros en el query
		String sql = getSQL(qBase, inputParams);
		//System.out.println("sql --> " + sql);

		//Relizar el Generic Search
		//documento config.xml
		Document docXML = getConfig().getDocument();

		//PATCH 2011-10-06 obtener mas de un elemento searchCols
		Element elem[] = docXML.getElements("searchCols");

		if ( elem == null )
			throw new Throwable("El elemento <searchCols> DEBE estar definido.");

		for (int j = 0; j < elem.length; j++) {

			Element searchCols = elem[j];

			//define el ID del recordset a publicar
			String _rsName = searchCols.getAttribute("id");
			//si no existe un atributo ID entonces buscar en el atributo paging.recordset 
			if ( _rsName == null )
				_rsName = (String)getRequest().getAttribute("paging.recordset");
			if ( _rsName == null )
				throw new Throwable("El atributo [id] del elemento <searchCols> no ha sido definido, este atributo DEBE contener el ID del Recordset.");

			/* ensamblar query */

			//valores del elemento searchCols
			String cols = searchCols.getValue();

			//verificar que el elemento searchCols contiene una lista separada por ";"
			if ( cols == null )
				throw new Throwable("El elemento <searchCols> DEBE contener una lista de valores separados por \";\"");

			//obtener array de los valores
			String params[] = StringUtil.split(cols, ";");

			if (params.length>0 && inputParams==null)
				throw new Throwable("La validación no está activada en config.xml para el Action [search], ponga el elemento <validator> en true.");

			//ya tenemos la lista de condiciones
			String where = getParamsFilter(params, inputParams, _rsName);

			//ahora reemplaza las condiciones en el query base
			sql = StringUtil.replace(sql,"${filter}", where);

			sql = prepareSql(sql);
			//System.out.println(sql);

			/* listo el query - quedo ensamblado */

			//ahora reemplaza los valores de los parametros en el query
			//String sql1 = getSQL(qBase, inputParams);
			sql = getSQL(sql, inputParams);
			//System.out.println(sql);

			//PATCH 2010-09-13 permite varios recordset de parametros separados por punta y coma
			String paramsID = searchCols.getAttribute("params"); 

			if (paramsID!=null) {
				String pams[] = StringUtil.split(paramsID, ";");
				for (int i = 0; i < pams.length; i++) {
					//buscar recordset en la session
					Recordset rsParams = (Recordset)getSession().getAttribute(pams[i]);
					if (rsParams!=null) {
						if (rsParams.getRecordCount()>0)
							rsParams.first();
						else
							throw new Throwable("El recordset [" + pams[i] + "] usado para remplazar el SQL no tiene registros.");
						sql = getSQL(sql, rsParams);
					} else 
						throw new Throwable("El Recordset con ID: " + pams[i] + " no se encuentra en sesión. Revise los config.xml y validator.xml de Actions previos a este.");
				}
			}

			//ejecutar query y crear recordset
			Recordset rs1 = getDb().get(sql);

			//si el recordset no trajo registros
			if(rs1.getRecordCount()==0) {
				rc = 1;
				break;
			} else {
				getSession().setAttribute(_rsName, rs1);
				getSession().setAttribute(_rsField, rsField);
				getSession().setAttribute(_rsWidth, rsWidth);	
				getSession().setAttribute(_rsField2, rsField2);
				rc = 0;
			}

		}


		return rc;
	}


	/**
	 * Crea un string con la lista de valores de un recordset 
	 * separados por coma.
	 * @param rs
	 * @return String
	 * @throws Throwable
	 */
	String buildArrayRecordset (Recordset rs) throws Throwable
	{
		String field = ""; 
		//recorrer recordset
		rs.top();
		while (rs.next()){
			//asignar nombre del campo junto con la coma
			field = field + rs.getString("colname") + ",";
		}
		//ultimo valor quitarle la coma
		if (field.endsWith(","))
			field = field.substring(0,field.length()-1);

		return field;			
	}
	
	/**
	 * Obtiene las condiciones que van en el where del SQL
	 * @param params Array que contiene los campos de condiciones
	 * @param inputs Recordset del request
	 * @param id del recordset al que hace referencia el elemento searchCols
	 * @return String con las clausulas o condiciones listas
	 * @throws Throwable
	 */
	public String getParamsFilter(String params[], Recordset inputs, String id) throws Throwable {
		
		//armar lista de condiciones
		StringBuffer qFilter = new StringBuffer();
		for (int i=0;i<params.length;i++)
		{
			if (inputs.getValue(params[i])!=null)
				qFilter.append(getResource("clause-" + params[i]+ ".sql"));
		}

		//ya tenemos la lista de condiciones
		String where = qFilter.toString();
		
		return where;
	}
	
	/**
	 * Permite realizar modificaciones al contenido del SQL
	 * antes que sea ejecutado en la base de datos, ideal para
	 * los casos de queries tipo crosstab que deben ser preparados antes.
	 * @param sql contenido del SQL que sera modificado
	 * @return sql contenido del SQL modificado
	 * @throws Throwable
	 */
	public String prepareSql(String sql) throws Throwable {
		return sql;
	}

}
