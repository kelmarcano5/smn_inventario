package services;

import dinamica.*;

public class UserImpl extends GenericTransaction
{
public String findUser(String id) throws Exception
	 {
		 //evitar ataques de inyeccion de sql
		 String newID = StringUtil.replace(id, "'", "''");
		
	 try {
		 //generar sql
		 String sql = StringUtil.replace(
		 getLocalResource("get-user.sql"), "${id}",newID );
		
		 //ejecutar I/O a BD
		 Recordset rs = dbGet("jdbc/dinamica", sql);
		
		 //si no existe retorna un error
		 if (rs.next())
		 	 rs.getString("prueba");
		 else
			 throw new Exception("User not found:" + id);
		 } catch (Throwable e) {
		 throw new Exception(e);
		 }
	return newID;
	
	 }
} 

