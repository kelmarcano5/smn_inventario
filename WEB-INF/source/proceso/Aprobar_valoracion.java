package proceso;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import dinamica.Db;
import dinamica.GenericTransaction;
import dinamica.Jndi;
import dinamica.Recordset;

public class Aprobar_valoracion extends GenericTransaction{
	
	public int service(Recordset inputParams) throws Throwable {
		// TODO Auto-generated constructor stub
		int rc=0;
				
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");

		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		DataSource ds = Jndi.getDataSource(jndiName); 
		Connection conn = ds.getConnection();
		this.setConnection(conn);
				
		try {
			Db db = getDb();
			String str="";
						
			//Recordset rsC1 = db.get(sqlCab1);
			
			
			str = str +"Consultando la tabla conteo fisico y conteo y valoracion de conteo fisico";
			String sqlCab = getSQL(getResource("query_cabecera.sql"), inputParams);
			Recordset rsC = db.get(sqlCab);
			
			if(rsC.getRecordCount() > 0) {
				
				rsC.first();
				
					str  = str + "Creando la cabecera del movimiento de inventario"+ " \n";
					String sqlInsertCab = getResource("insert_movimiento_cabecera.sql");
					sqlInsertCab = getSQL(sqlInsertCab, rsC );
					db.exec(sqlInsertCab);
					
				str = str + "Fin proceso creando la cabecera"+ " \n";
			}else{
				str = str + "Error en el proceso al crear la cabecera"+ " \n";
			}	
			
			
			str = str +"Consultando la tabla conteo fisico, conteo y valoracion de conteo fisico";
			String sqlDet = getSQL(getResource("query_detalle.sql"), inputParams);
			Recordset rsD = db.get(sqlDet);
			
			if(rsD.getRecordCount() > 0) {	
				while(rsD.next()){
				
					
					str  = str + "Creando el detalle del movimiento de inventario"+ " \n";
					String sqlInsertDet = getResource("insert_movimiento_detalle.sql");
					sqlInsertDet = getSQL(sqlInsertDet, rsD);
					db.exec(sqlInsertDet);	
				}
				
				String sqlUpdateStatus = getSQL(getResource("updateStatus.sql"), inputParams);
				db.exec(sqlUpdateStatus);	
				
				str = str + "Fin proceso while creando el detalle "+ " \n";
			}else{
				str = str + "Error en el while creando el detalle"+ " \n";
			}
			
			
			str = str +"Consultando la tabla Movimiento Cabecera para obtener el ID";
			String sqlMovId = getSQL(getResource("query_secuencia_cabecera.sql"), inputParams);
			Recordset rsMovId = db.get(sqlMovId);
			
			if(rsMovId.getRecordCount() > 0) {
				
				rsMovId.first();
				
				this.controlItem(conn, inputParams); //registra control de item si lo requiere.
					
				str = str + "Fin proceso creando la cabecera"+ " \n";
			}else{
				str = str + "Error en el proceso al crear la cabecera"+ " \n";
			}	
			
			String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
			File newLogFile = new File("./log_ProcesarValoracionInventario"+fechaActual+".txt");		
			//File newLogFile = new File("D:/data/Simone/smn_inventario/log/log_AprobarValoracion"+fechaActual+".txt");			
		    FileWriter fw = new FileWriter(newLogFile);
		    fw.write(str);
		    fw.close();
			
		}catch(Throwable e){	
			throw e;
		}
		finally{
			if(conn!=null)
				conn.close();
		}
		return rc;
	}
	
	
	private int controlItem(Connection conn, Recordset inputParams) throws Throwable
	{
		int rc = 0;
		Double saldo_inicial_existencia=0.00;
		Double cantidad_entradas=0.00;
		Double cantidad_salidas=0.00; 
		Double saldo_final_existencia=0.00;
		Double valor_inicial=0.00; 
		Double valor_entrada=0.00;
		Double valor_salida=0.00;
		Double valor_final=0.00;
		Double costo_promedio=0.00;
		Double ultimo_costo=0.00;
		Double costo_mas_alto=0.00;
		Double costo_promedio_ponderado=0.00;
		Boolean existe=false;
		Integer almacen;
		Integer item_id;
		String tipo_movimiento;
		String tipo_costo;
		Date fecha_mov_cab;
		Date fecha_movimiento = null;
		double cantidad_recibida;
		Double valor_unitario_ml;
		String mensaje = "";
		String str="";
		
		this.setConnection(conn);
				
		try 
		{
			Db db = getDb();

			String sqlCheckCab = getSQL(getResource("sql-consultaMovimientoCabecera.sql"), inputParams);
			Recordset rsMovimientoCabecera = db.get(sqlCheckCab);
		
			if(rsMovimientoCabecera.getRecordCount() > 0) {
			
				rsMovimientoCabecera.first();
				almacen=rsMovimientoCabecera.getInteger("smn_almacen_rf");
				inputParams.setValue("smn_almacen_rf", almacen);
				fecha_mov_cab=rsMovimientoCabecera.getDate("mca_fecha_recibida");
				inputParams.setValue("coi_fecha_movimiento", fecha_mov_cab);
				
				String sqlCheckDet = getSQL(getResource("sql-consultaMovimientoDetalle.sql"), inputParams);
				Recordset rsMovimientoDetalle = db.get(sqlCheckDet);
			
				if(rsMovimientoDetalle.getRecordCount() > 0) {
					while (rsMovimientoDetalle.next())
					{
						tipo_movimiento=rsMovimientoDetalle.getString("mde_tipo_movimiento");
						cantidad_recibida=rsMovimientoDetalle.getDouble("mde_cantidad_recibida");
						valor_unitario_ml=rsMovimientoDetalle.getDouble("mde_valor_unitario_ml");
						inputParams.setValue("coi_fecha_movimiento", fecha_mov_cab);
						tipo_costo=rsMovimientoDetalle.getString("cit_tipo_costo");
						item_id=rsMovimientoDetalle.getInteger("item_rf");
						inputParams.setValue("smn_item_rf", item_id);
						Integer movimiento_detalle_id=rsMovimientoDetalle.getInteger("smn_movimiento_detalle_id");
						inputParams.setValue("smn_movimiento_detalle_id", movimiento_detalle_id);
						
						String sqlCheckControl = getSQL(getResource("sql-consultaControlItem.sql"), inputParams);
						Recordset rssqlCheckControl = db.get(sqlCheckControl);
					
						if(rssqlCheckControl.getRecordCount() > 0) {
							while (rssqlCheckControl.next())
							{
								inputParams.setValue("smn_control_item_id", rssqlCheckControl.getInteger("smn_control_item_id"));
								existe=true;
								saldo_inicial_existencia=rssqlCheckControl.getDouble("coi_saldo_inicial_existencia"); 
								cantidad_entradas=rssqlCheckControl.getDouble("coi_cantidad_entradas");  
								cantidad_salidas=rssqlCheckControl.getDouble("coi_cantidad_salidas"); 
								saldo_final_existencia=rssqlCheckControl.getDouble("coi_saldo_final_existencia"); 
								valor_inicial=rssqlCheckControl.getDouble("coi_valor_inicial"); 
								valor_entrada=rssqlCheckControl.getDouble("coi_valor_entrada"); 
								valor_salida=rssqlCheckControl.getDouble("coi_valor_salida"); 
								valor_final=rssqlCheckControl.getDouble("coi_valor_final"); 
								costo_promedio=rssqlCheckControl.getDouble("coi_costo_promedio"); 
								ultimo_costo=rssqlCheckControl.getDouble("coi_ultimo_costo"); 
								costo_mas_alto=rssqlCheckControl.getDouble("coi_costo_mas_alto"); 
								costo_promedio_ponderado=rssqlCheckControl.getDouble("coi_costo_promedio_ponderado");
								fecha_movimiento=rssqlCheckControl.getDate("coi_fecha_movimiento");
							}
						}else{
							existe=false;
							saldo_inicial_existencia=0.00; 
							cantidad_entradas=0.00; 
							cantidad_salidas=0.00;
							saldo_final_existencia=0.00;
							valor_inicial=0.00;
							valor_entrada=0.00;
							valor_salida=0.00;
							valor_final=0.00;
							costo_promedio=0.00;
							ultimo_costo=0.00;
							costo_mas_alto=0.00;
							costo_promedio_ponderado=0.00;
							
						}
						if(tipo_movimiento.equals("EN")){
							inputParams.setValue("coi_precio", valor_unitario_ml);
							cantidad_entradas=cantidad_recibida;
							inputParams.setValue("coi_cantidad_entradas", cantidad_entradas);
							saldo_inicial_existencia=saldo_final_existencia;
							saldo_final_existencia=saldo_final_existencia+cantidad_entradas;
							inputParams.setValue("coi_saldo_inicial_existencia", saldo_inicial_existencia);
							inputParams.setValue("coi_saldo_final_existencia", saldo_final_existencia);
							inputParams.setValue("coi_cantidad_salidas", cantidad_salidas);
							valor_inicial=valor_final;
							inputParams.setValue("coi_valor_inicial", valor_inicial);
							valor_entrada=valor_unitario_ml*cantidad_entradas;
							inputParams.setValue("coi_valor_entrada", valor_entrada);
							inputParams.setValue("coi_valor_salida", valor_salida);
							valor_final=valor_inicial+valor_entrada;
							inputParams.setValue("coi_valor_final", valor_final);
							costo_promedio=valor_final / saldo_final_existencia;
							inputParams.setValue("coi_costo_promedio", costo_promedio);
							ultimo_costo=valor_unitario_ml;
							inputParams.setValue("coi_ultimo_costo", ultimo_costo);
							
							costo_mas_alto=Math.max(costo_mas_alto, valor_unitario_ml);
							inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
							
							if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
								costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+(saldo_inicial_existencia*costo_promedio_ponderado))/(cantidad_entradas + saldo_inicial_existencia);
							else
								costo_promedio_ponderado = 0.0;
							
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
							
							
						}else{
							inputParams.setValue("coi_precio", valor_unitario_ml);
							cantidad_salidas=cantidad_salidas+cantidad_recibida;
							inputParams.setValue("coi_cantidad_entradas", cantidad_entradas);
							saldo_inicial_existencia=saldo_final_existencia;
							saldo_final_existencia=saldo_final_existencia-cantidad_salidas;
							inputParams.setValue("coi_saldo_inicial_existencia", saldo_inicial_existencia);
							inputParams.setValue("coi_saldo_final_existencia", saldo_final_existencia);
							inputParams.setValue("coi_cantidad_salidas", cantidad_salidas);
							valor_inicial=valor_final;
							inputParams.setValue("coi_valor_inicial", valor_inicial);
							valor_entrada=valor_unitario_ml*cantidad_entradas;
							inputParams.setValue("coi_valor_entrada", valor_entrada);
							//
							if (tipo_costo.equals("PR")) {
								valor_salida=costo_promedio*cantidad_salidas;
							} else if (tipo_costo.equals("UC")) {
								valor_salida=ultimo_costo*cantidad_salidas;
							} else if (tipo_costo.equals("CM")) {
								valor_salida=costo_mas_alto*cantidad_salidas;
							} else if (tipo_costo.equals("PP")) {
								valor_salida=costo_promedio_ponderado*cantidad_salidas;
							}
							//
							inputParams.setValue("coi_valor_salida", valor_salida);
							valor_final=valor_inicial-valor_salida;
							inputParams.setValue("coi_valor_final", valor_final);
							costo_promedio=valor_final / saldo_final_existencia;
							inputParams.setValue("coi_costo_promedio", costo_promedio);
							ultimo_costo=valor_unitario_ml;
							inputParams.setValue("coi_ultimo_costo", ultimo_costo);
							costo_mas_alto=Math.max(costo_mas_alto, valor_unitario_ml);
							inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
							
							if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
								costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+(saldo_inicial_existencia*costo_promedio_ponderado))/(cantidad_entradas + saldo_inicial_existencia);
							else
								costo_promedio_ponderado = 0.0;
							
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
						}
						if(!existe){
							System.out.println("INSERTANDO CONTROL ITEM");
							String insertControlItem = getSQL(getResource("insert_ControlItem.sql"), inputParams);
							Recordset insControlItem = db.get(insertControlItem);
							if(insControlItem.getRecordCount() > 0) {
								while(insControlItem.next()){
									Integer control_item_id=insControlItem.getInteger("id_control_item"); 
									inputParams.setValue("smn_control_item_id", control_item_id);
									System.out.println("INSERTANDO RELACION CONTROL ITEM DETALLE");
									String insRelControlItem = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
									db.exec(insRelControlItem);
								}
								
							}else{
								/*mensaje = "NO INSERTO CONTROL ITEM";
								inputParams.setValue("mensaje", mensaje);*/
								return 1;
							}
						}else{
							if(fecha_mov_cab.compareTo(fecha_movimiento)==0){
								/*mensaje = "MODIFICANDO CONTROL ITEM";
								System.out.println(mensaje);*/
								String updateControlItem = getSQL(getResource("update_ControlItem.sql"), inputParams);
								db.exec(updateControlItem);
								/*mensaje = "INSERTANDO RELACION CONTROL ITEM DETALLE";
								System.out.println(mensaje);*/
								String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
								db.exec(insRelControlItem2);
							}else{
								/*mensaje = "INSERTANDO CONTROL ITEM";
								System.out.println(mensaje);*/
								String insertControlItem2 = getSQL(getResource("insert_ControlItem.sql"), inputParams);
								Recordset insControlItem2 = db.get(insertControlItem2);
								if(insControlItem2.getRecordCount() > 0) {
									while(insControlItem2.next()){
										Integer control_item_id=insControlItem2.getInteger("id_control_item"); 
										inputParams.setValue("smn_control_item_id", control_item_id);
										/*mensaje = "INSERTANDO RELACION CONTROL ITEM DETALLE";
										System.out.println(mensaje);*/
										String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
										db.exec(insRelControlItem2);
									}
									
								}else{
									/*mensaje = "NO INSERTO CONTROL ITEM";
									inputParams.setValue("mensaje", mensaje);*/
									return 1;
								}
							}
							
						}
						/*mensaje = "MODIFICANDO STATUS CABECERA DE MOVIMIENTO";
						System.out.println(mensaje);*/
						String updmovcab = getSQL(getResource("update_Movimiento_cabecera.sql"), inputParams);
						db.exec(updmovcab);
					}
				}else{
				/*mensaje = "NO HAY MOVIMIENTO DETALLE";
				inputParams.setValue("mensaje", mensaje);*/
				return 1;
				}
			}
			
			String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			File newLogFile = new File("./log_ProcesarValoracionControlItem"+fechaActual+".txt");			
		    FileWriter fw = new FileWriter(newLogFile);
		    fw.write(str);
		    fw.close();
			/*mensaje = "Proceso de Control Item Exitoso";
			inputParams.setValue("mensaje", mensaje);
			mensaje = "FIN DEL PROCESO";
			System.out.println(mensaje);*/
		}catch(Throwable e){
			throw e;
		}
		
		finally{
			System.out.println(inputParams.getValue("mensaje"));
		}
		
		return rc;
	}
}
