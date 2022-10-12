package proceso;

import dinamica.*;

import javax.sql.DataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Generar_valoracion extends GenericTransaction{
	public int service(Recordset inputParams) throws Throwable {
		// TODO Auto-generated constructor stub
		//default return code
		int rc = 0;
		
		//reuse superclass code
		super.service(inputParams);
		
		
		//get security datasource
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");
		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		//get datasource and DB connection
		DataSource ds = Jndi.getDataSource(jndiName); 
		Connection conn = ds.getConnection();
		this.setConnection(conn);
		conn.setAutoCommit(false);
		
		String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String sistemaOperativo = System.getProperty("os.name");
		String file;
		  
		if(sistemaOperativo.equals("Windows 7") || sistemaOperativo.equals("Windows 8") || sistemaOperativo.equals("Windows 10")) 
			file =  "C:/log/logValoracion_"+fechaActual+".txt";
		else
			file = "./logValoracion_"+fechaActual+".txt";
		
		File newLogFile = new File(file);
		FileWriter fw;
		String str="";
		
		if(!newLogFile.exists())
			fw = new FileWriter(newLogFile);
		else
			fw = new FileWriter(newLogFile,true);
		
		BufferedWriter bw=new BufferedWriter(fw);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		
		try {
			Db db = getDb();
			//String str="";
			
			str = str +"Consultando la tabla conteo fisico y control item";
			String sqlCheckIng = getSQL(getResource("query.sql"), inputParams);
			Recordset rs = db.get(sqlCheckIng);
			
			if(rs.getRecordCount() > 0) {
				while(rs.next()){
					str  = str + "Creando la valoracion del inventario"+ " \n";
					String sqlInsert = getResource("insertvaloracion.sql");
					sqlInsert = getSQL(sqlInsert, rs );
					db.exec(sqlInsert);
					
				}
				str = str + "Fin proceso while creando la valoracion"+ " \n";
				bw.write(str);
				bw.flush();
				bw.newLine();
				getRequest().setAttribute("mensaje", str);
			}else{
				str = str + "Error en el while al crear la valoracion"+ " \n";
				bw.write(str);
				bw.flush();
				bw.newLine();
				getRequest().setAttribute("mensaje", str);
			}	
			
			str = str + "Error al crear la cabecera del Pedido"+ " \n";
			bw.write(str);
			bw.flush();
			bw.newLine();
			getRequest().setAttribute("mensaje", str);
			
			
		}catch (Throwable e){
			conn.rollback();
			throw e;
		}
		finally{
			if(rc == 0){
				conn.commit();
				str = "Cambios en la base de datos guardados correctamente";	
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			else{
				conn.rollback();
				str = "Los cambios en la base de datos no se guardaron";	
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
		}
			
		return rc;
	}
}
