package proceso;

import dinamica.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

public class RecepcionComprasMercado extends GenericTransaction
{
	public int service(Recordset inputParams) throws Throwable 
	{
		int rc=0; //variable a retornar 0=proceso sin errores / 1=error en el proceso.
		String sql; //variable para almacenar los sql.
		
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");
		
		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		DataSource ds = Jndi.getDataSource(jndiName); 
		Connection conn = ds.getConnection();
		this.setConnection(conn);
		conn.setAutoCommit(false);
		
		String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		String sistemaOperativo = System.getProperty("os.name");
		String file;
		  
		if(sistemaOperativo.equals("Windows 7") || sistemaOperativo.equals("Windows 8") || sistemaOperativo.equals("Windows 10")) 
			file =  "C:/log/logRecepcionComprasMercado_"+fechaActual+".txt";
		else
			file = "./logRecepcionComprasMercado_"+fechaActual+".txt";
		
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
			
			str = "----------"+timestamp+"----------";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			bw.newLine();
			
			Db db = getDb();
			
			sql = getSQL(getResource("sql-consultaMovimientoCabecera.sql"),inputParams);
			Recordset rsMovimientoCabecera = db.get(sql);
			
			if(rsMovimientoCabecera.getRecordCount() > 0)
			{
				rsMovimientoCabecera.first();
				
				str = "*Procesando movimiento*";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(rsMovimientoCabecera.getString("smn_entidad_rf") != null)
					inputParams.setValue("smn_entidad_rf", rsMovimientoCabecera.getInt("smn_entidad_rf"));
				else
				{
					inputParams.setValue("mensaje","*El movimiento no tiene empresa registrada*");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc=1;
					return 1;
				}
				
				if(rsMovimientoCabecera.getString("smn_almacen_rf") != null)
					inputParams.setValue("smn_almacen_rf", rsMovimientoCabecera.getInt("smn_almacen_rf"));
				else
				{
					inputParams.setValue("mensaje","*El movimiento no tiene almacen registrado*");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc=1;
					return 1;
				}
				
				if(rsMovimientoCabecera.getString("mca_fecha_recibida") != null)
					inputParams.setValue("mca_fecha_recibida", rsMovimientoCabecera.getDate("mca_fecha_recibida"));
				else
				{
					inputParams.setValue("mensaje","*El movimiento no tiene fecha recibida registrada*");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc=1;
					return 1;
				}
				
				if(rsMovimientoCabecera.getString("smn_sucursal_rf") != null)
					inputParams.setValue("smn_sucursal_rf", rsMovimientoCabecera.getInt("smn_sucursal_rf"));
				
				sql = getSQL(getResource("sql-consultaMovimientoDetalle.sql"),inputParams);
				Recordset rsMovimientoDetalle = db.get(sql);
				
				str = "Consultando movimiento detalle...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(rsMovimientoDetalle.getRecordCount() > 0)
				{
					str = "Total detalles encontrados = "+rsMovimientoDetalle.getRecordCount();
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					int unidad_medida;
					
					while(rsMovimientoDetalle.next())
					{
						inputParams.setValue("smn_movimiento_detalle_id", rsMovimientoDetalle.getInt("smn_movimiento_detalle_id"));
						
						if(rsMovimientoDetalle.getString("smn_unidad_medida_rf") != null)
							unidad_medida=rsMovimientoDetalle.getInt("smn_unidad_medida_rf");
						else
						{
							inputParams.setValue("mensaje","*El item no tiene unidad de medida registrada*");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc=1;
							return 1;
						}
						
						if(rsMovimientoDetalle.getString("smn_item_rf") != null)
							inputParams.setValue("smn_item_rf", rsMovimientoDetalle.getInt("smn_item_rf"));
						else
						{
							inputParams.setValue("mensaje","*No existe item registrado en el movimiento*");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc=1;
							return 1;
						}
						
						if(rsMovimientoDetalle.getString("mde_cantidad_recibida") != null)
							inputParams.setValue("mde_cantidad_recibida", rsMovimientoDetalle.getDouble("mde_cantidad_recibida"));
						else
							inputParams.setValue("mde_cantidad_recibida", 0);
						
						if(rsMovimientoDetalle.getString("mde_valor_unitario_ml") != null)
							inputParams.setValue("mde_valor_unitario_ml", rsMovimientoDetalle.getDouble("mde_valor_unitario_ml"));
						else
							inputParams.setValue("mde_valor_unitario_ml", 0);
						
						sql = getSQL(getResource("select-smn_caracteristica_item.sql"),inputParams);
						Recordset rsCaracteristicaItem = db.get(sql);
						
						str = "Consultando caracteristica de item...";
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						int unidad_medida_base=0;
						
						if(rsCaracteristicaItem.getRecordCount()>0)
						{
							rsCaracteristicaItem.first();
							str = "Caracteristica de item encontrado";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							if(rsCaracteristicaItem.getString("smn_unidad_medida_base_rf")!=null)
								unidad_medida_base = rsCaracteristicaItem.getInt("smn_unidad_medida_base_rf");
						}
						else
						{
							inputParams.setValue("mensaje","*No existe item registrado en caracteristica de item*");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc=1;
							return 1;
						}
						
						if(unidad_medida!=unidad_medida_base)
						{
							//Calcula reconversion de unidad de medida.
							
							inputParams.setValue("smn_unidad_medida_rf", unidad_medida);
							
							sql = getSQL(getResource("select-smn_conversion_unidad_medida.sql"),inputParams);
							Recordset rsConversionUnidadMedida = db.get(sql);
							
							str = "Consultando conversion de unidad de medida...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							double factor_conversion=0;
							String signo_conversion;
							double cantidad_recibida=inputParams.getDouble("mde_cantidad_recibida");
							double cantidad_total_conversion=0;
							double valor_unitario_conversion=0;
							double valor_unitario=inputParams.getDouble("mde_valor_unitario_ml");
							
							if(rsConversionUnidadMedida.getRecordCount()>0)
							{
								rsConversionUnidadMedida.first();
								str = "Conversion de unidad de medida encontrada";
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								if(rsConversionUnidadMedida.getString("cou_factor_conversion")!=null)
									factor_conversion=rsConversionUnidadMedida.getDouble("cou_factor_conversion");
								
								if(rsConversionUnidadMedida.getString("cou_signo_conversion")!=null)
									signo_conversion=rsConversionUnidadMedida.getString("cou_signo_conversion");
								else
								{
									inputParams.setValue("mensaje","*El signo de conversion no esta registrado*");
									str = inputParams.getString("mensaje");
									bw.write(str);
									bw.flush();
									bw.newLine();
									rc=1;
									return 1;
								}
							}
							else
							{
								inputParams.setValue("mensaje","*La unidad de conversion no esta registrada*");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc=1;
								return 1;
							}
							
							if(signo_conversion.equals("*"))
							{
								cantidad_total_conversion=cantidad_recibida*factor_conversion;
								valor_unitario_conversion=valor_unitario/factor_conversion;
							}
							else
							{
								cantidad_total_conversion=cantidad_recibida/factor_conversion;
								valor_unitario_conversion=valor_unitario*factor_conversion;
							}
								
							inputParams.setValue("mde_cantidad_recibida", cantidad_total_conversion);
							inputParams.setValue("mde_cantidad_solicitada", cantidad_recibida);
							inputParams.setValue("mde_valor_unitario_ml", valor_unitario_conversion);
							inputParams.setValue("mde_valor_unitario_ml_origen", valor_unitario);
							
							str = "Actualizando cantidades y valor unitario ya que se aplico conversion de unidad de medida";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							sql = getSQL(getResource("update-mde_cantidades.sql"),inputParams);
							db.exec(sql);
							
							str = "*Cantidades y valor unitario actualizados*";
							bw.write(str);
							bw.flush();
							bw.newLine();
						}
					} //END-WHILE
				}
				
				rc = this.controlUbicaciones(conn, inputParams, str, bw); 
			
				if(rc == 0)
				{
					rc = this.controlItem(conn, inputParams, str, bw);
					
					if(rc == 0)
					{
						rc = this.controlLote(conn, inputParams, str, bw);
						
						if(rc == 0)
						{
							rc = this.contabilizar(conn, inputParams, str, bw);
							
							if(rc == 0)
							{
								rc = this.nota_entrada(conn, inputParams, str, bw);
							}
						}
					}
				}
				
				if(rc == 0)
				{
					sql = getSQL(getResource("update-estatus_movimiento_cabecera.sql"),inputParams);
					db.exec(sql);
					
					rc = 0;
					inputParams.setValue("mensaje","*El movimiento se genero exitosamente*");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
				}	
			}
			else
			{
				inputParams.setValue("mensaje","*El movimiento no tiene estatus REGISTRADO*");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc = 1;
			}
		}catch(Throwable e){
			conn.rollback();
			throw e;
		}
		
		finally{
			getRequest().setAttribute("mensaje", inputParams.getValue("mensaje"));
			
			if(rc == 0)
			{
				conn.commit();
				str = "Cambios en la base de datos guardados correctamente";	
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			else
			{
				conn.rollback();
				str = "Los cambios en la base de datos no se guardaron";	
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			
			str = "FIN DEL PROCESO";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			bw.newLine();
	        bw.close();
			
			if(conn!=null)
				conn.close();
		}
		
		return rc;
	}
	
	private int controlUbicaciones(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable
	{
		int rc=0;                      		 //variable a retornar.
		int movimiento_cabecera_id = inputParams.getInt("smn_movimiento_cabecera_id"); 
		String sql;                    		 //variable que almacena instrucciones sql.
		String control_ubicacion = "NO";     //almacena si el detalle maneja control de ubicacion (si/no)
		
		this.setConnection(conn); 
				
		try 
		{
			str = "*Procesando control de ubicacion*";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			Db db = getDb();
			
			sql = getSQL(getResource("select-cal_control_ubicacion.sql"),inputParams);
			Recordset rsControl_ubicacion = db.get(sql); //busca si requiere control de ubicacion
			
			str = "Consultando si requiere control de ubicacion";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			if(rsControl_ubicacion.getRecordCount() > 0)
			{
				rsControl_ubicacion.first();
				
				control_ubicacion = rsControl_ubicacion.getString("cal_control_ubicacion");
				
				str = control_ubicacion+" requiere control de ubicacion";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(control_ubicacion.equals("SI"))
				{
					sql = getSQL(getResource("sql-consultaMovimientoDetalle.sql"),inputParams);
					Recordset rsMovimiento_detalle = db.get(sql); //busca el movimiento detalle
					
					str = "Consultando movimiento detalle...";	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					if(rsMovimiento_detalle.getRecordCount()>0)
					{
						str = "Total detalles encontrados = "+rsMovimiento_detalle.getRecordCount();	
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						while(rsMovimiento_detalle.next())
						{
							inputParams.setValue("smn_movimiento_detalle_id", rsMovimiento_detalle.getInt("smn_movimiento_detalle_id"));
							
							if(rsMovimiento_detalle.getString("smn_item_rf") != null)
								inputParams.setValue("smn_item_rf", rsMovimiento_detalle.getInt("smn_item_rf"));
							else
							{
								inputParams.setValue("mensaje", "El detalle del movimiento con id "+inputParams.getValue("smn_movimiento_detalle_id")+" no tiene item registrado");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							if(rsMovimiento_detalle.getString("mde_lote") != null)
								inputParams.setValue("mde_lote", rsMovimiento_detalle.getInt("mde_lote"));
							else
								inputParams.setValue("mde_lote", 0);
							
							if(rsMovimiento_detalle.getString("mde_cantidad_recibida") != null)
								inputParams.setValue("mde_cantidad_recibida", rsMovimiento_detalle.getDouble("mde_cantidad_recibida"));
							else
							{
								inputParams.setValue("mensaje", "El detalle del movimiento con id "+inputParams.getValue("smn_movimiento_detalle_id")+" no tiene registrada la cantidad recibida");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							sql = getSQL(getResource("select-smn_caracteristica_almacen.sql"),inputParams);
							Recordset rsCaracteristica_almacen = db.get(sql); //busca el id de caracteristica_almacen
							
							if(rsCaracteristica_almacen.getRecordCount() > 0)
							{
								rsCaracteristica_almacen.first();
								
								inputParams.setValue("smn_caracteristica_almacen_id", rsCaracteristica_almacen.getInt("smn_caracteristica_almacen_id"));
							}
							else
							{
								inputParams.setValue("mensaje", "No se encontro la caracteristica del almacen con id "+inputParams.getValue("smn_almacen_rf"));
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							sql = getSQL(getResource("select-smn_caracteristica_item.sql"),inputParams);
							Recordset rsCaracteristica_item = db.get(sql); //busca el id de caracteristica_item
							
							if(rsCaracteristica_item.getRecordCount() > 0)
							{
								rsCaracteristica_item.first();
								
								inputParams.setValue("smn_caracteristica_item_id", rsCaracteristica_item.getInt("smn_caracteristica_item_id"));
							}
							else
							{
								inputParams.setValue("mensaje", "No se encontro la caracteristica del item con id "+inputParams.getValue("smn_item_rf"));
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							sql = getSQL(getResource("insert-smn_ubicacion_cabecera.sql"),inputParams);
							Recordset rsUbicacion_cabecera = db.get(sql); //registra ubicacion_cabecera
							
							str = "Registrando ubicacion cabecera...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							if(rsUbicacion_cabecera.getRecordCount()>0)
							{
								rsUbicacion_cabecera.first();
								
								inputParams.setValue("smn_ubicacion_cabecera_id", rsUbicacion_cabecera.getInt("smn_ubicacion_cabecera_id"));
								
								sql = getSQL(getResource("select-col_cantidad_final.sql"),inputParams);
								Recordset rsCantidad_final= db.get(sql);  //busca la cantidad_final 
								
								if(rsCantidad_final.getRecordCount()>0)
								{
									rsCantidad_final.first();
									
									if(rsCantidad_final.getString("col_cantidad_final")!=null)
										inputParams.setValue("col_cantidad_final", rsCantidad_final.getDouble("col_cantidad_final"));
									else
										inputParams.setValue("col_cantidad_final", 0.0);
								}
								else
								{
									inputParams.setValue("col_cantidad_final", 0.0);
								}
								
								inputParams.setValue("col_cantidad_final", inputParams.getDouble("col_cantidad_final")+inputParams.getDouble("mde_cantidad_recibida"));
								
								sql = getSQL(getResource("insert-smn_ubicacion_detalle.sql"),inputParams);
								db.exec(sql); //registra ubicacion_detalle
								
								str = "Registrando ubicacion detalle...";
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								rc = 0;		
							}
							else
							{
								inputParams.setValue("mensaje", "*Ocurrio un error al registrar el control de ubicacion*");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}	
						}//END WHILE
					}
					else
					{
						inputParams.setValue("mensaje", "El movimiento con el id "+movimiento_cabecera_id+" no tiene estatus REGISTRADO");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
					}
				}
			}
			
			if(rc == 0)
			{
				inputParams.setValue("mensaje", "*El control de Ubicacion se realizo exitosamente*");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			else
			{
				inputParams.setValue("mensaje", "*Ocurrio un error al registrar el control de ubicacion*");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			
		}catch(Throwable e){
			throw e;
		}
		
		return rc;
	}
	
	private int controlItem(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable
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
		Double valor_inicial_ma=0.00; 
		Double valor_entrada_ma=0.00;
		Double valor_salida_ma=0.00;
		Double valor_final_ma=0.00;
		Double costo_promedio_ma=0.00;
		Double ultimo_costo_ma=0.00;
		Double costo_mas_alto_ma=0.00;
		Double costo_promedio_ponderado_ma=0.00;
		Boolean existe=false;
		Integer almacen;
		Integer item_id;
		String tipo_movimiento;
		String tipo_costo;
		Date fecha_mov_cab;
		Date fecha_movimiento = null;
		double cantidad_recibida;
		Double valor_unitario_ml=0.00;;
		Double valor_unitario_ma=0.00;
		String mensaje = "";
		//
		String tipo_costo_alm = "";
		Double valor_disminucion_ml=0.00;;
		Double valor_disminucion_ma=0.00;
		
		this.setConnection(conn);
				
		try 
		{
			str = "*Procesando control de item*";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			Db db = getDb();

			String sqlCheckCab = getSQL(getResource("sql-consultaMovimientoCabecera.sql"), inputParams);
			Recordset rsMovimientoCabecera = db.get(sqlCheckCab);
			
			str = "Consultando movimiento cabecera...";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			if(rsMovimientoCabecera.getRecordCount() > 0) {
			
				rsMovimientoCabecera.first();
				almacen=rsMovimientoCabecera.getInteger("smn_almacen_rf");
				tipo_costo_alm=rsMovimientoCabecera.getString("cal_tipo_calculo_costo");
				inputParams.setValue("smn_almacen_rf", almacen);
				fecha_mov_cab=rsMovimientoCabecera.getDate("mca_fecha_recibida");
				inputParams.setValue("coi_fecha_movimiento", fecha_mov_cab);
				
				String sqlCheckDet = getSQL(getResource("sql-consultaMovimientoDetalle.sql"), inputParams);
				Recordset rsMovimientoDetalle = db.get(sqlCheckDet);
				
				str = "Consultando movimiento detalle...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(rsMovimientoDetalle.getRecordCount() > 0) {
					str = "Total detalles encontrados = "+rsMovimientoDetalle.getRecordCount();
					bw.write(str);
					bw.flush();
					bw.newLine();
					while (rsMovimientoDetalle.next())
					{
						tipo_movimiento=rsMovimientoDetalle.getString("mde_tipo_movimiento");
						cantidad_recibida=rsMovimientoDetalle.getDouble("mde_cantidad_recibida");
						valor_unitario_ml=rsMovimientoDetalle.getDouble("mde_valor_unitario_ml");
						//
						if(rsMovimientoDetalle.getString("mde_valor_unitario_ma") != null)
							valor_unitario_ma=rsMovimientoDetalle.getDouble("mde_valor_unitario_ma");
						else
							valor_unitario_ma=0.0;
						//
						if(rsMovimientoDetalle.getString("mde_monto_disminucion_ml") != null)
							valor_disminucion_ml=rsMovimientoDetalle.getDouble("mde_monto_disminucion_ml");
						else
							valor_disminucion_ml=0.0;				
						//
						if(rsMovimientoDetalle.getString("mde_monto_disminucion_ma") != null)
							valor_disminucion_ma=rsMovimientoDetalle.getDouble("mde_monto_disminucion_ma");
						else
							valor_disminucion_ma=0.0;
						//
						if(tipo_costo_alm.equals("DI")){
							valor_unitario_ml=valor_unitario_ml-valor_disminucion_ml;
							valor_unitario_ma=valor_unitario_ma-valor_disminucion_ma;
						}
						//
						inputParams.setValue("coi_fecha_movimiento", fecha_mov_cab);
						tipo_costo=rsMovimientoDetalle.getString("cit_tipo_costo");
						item_id=rsMovimientoDetalle.getInteger("item_rf");
						inputParams.setValue("smn_item_rf", item_id);
						Integer movimiento_detalle_id=rsMovimientoDetalle.getInteger("smn_movimiento_detalle_id");
						inputParams.setValue("smn_movimiento_detalle_id", movimiento_detalle_id);
						
						String sqlCheckControl = getSQL(getResource("sql-consultaControlItem.sql"), inputParams);
						Recordset rssqlCheckControl = db.get(sqlCheckControl);
						
						str = "Consultando control de item...";
						bw.write(str);
						bw.flush();
						bw.newLine();
						
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
								//
								if(rssqlCheckControl.getString("coi_valor_inicial_ma") != null)
									valor_inicial_ma=rssqlCheckControl.getDouble("coi_valor_inicial_ma");
								else
									valor_inicial_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_valor_entradas_ma") != null)
									valor_entrada_ma=rssqlCheckControl.getDouble("coi_valor_entradas_ma"); 
								else
									valor_entrada_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_valor_salidas_ma") != null)
									valor_salida_ma=rssqlCheckControl.getDouble("coi_valor_salidas_ma"); 
								else
									valor_salida_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_valor_final_ma") != null)
									valor_final_ma=rssqlCheckControl.getDouble("coi_valor_final_ma");
								else
									valor_final_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_costo_promedio_ma") != null)
									costo_promedio_ma=rssqlCheckControl.getDouble("coi_costo_promedio_ma"); 
								else
									costo_promedio_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_ultimo_costo_ma") != null)
									ultimo_costo_ma=rssqlCheckControl.getDouble("coi_ultimo_costo_ma"); 
								else
									ultimo_costo_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_costo_mas_alto_ma") != null)
									costo_mas_alto_ma=rssqlCheckControl.getDouble("coi_costo_mas_alto_ma"); 
								else
									costo_mas_alto_ma=0.0;
								
								if(rssqlCheckControl.getString("coi_costo_promedio_ponderado_ma") != null)
									costo_promedio_ponderado_ma=rssqlCheckControl.getDouble("coi_costo_promedio_ponderado_ma");
								else
									costo_promedio_ponderado_ma=0.0;
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
							//
							valor_inicial_ma=0.00;
							valor_entrada_ma=0.00;
							valor_salida_ma=0.00;
							valor_final_ma=0.00;
							costo_promedio_ma=0.00;
							ultimo_costo_ma=0.00;
							costo_mas_alto_ma=0.00;
							costo_promedio_ponderado_ma=0.00;
							
						}
						if(tipo_movimiento.equals("EN")){
							inputParams.setValue("coi_precio", valor_unitario_ml);
							inputParams.setValue("coi_precio_ma", valor_unitario_ma);
							cantidad_entradas=cantidad_recibida;
							inputParams.setValue("coi_cantidad_entradas", cantidad_entradas);
							saldo_inicial_existencia=saldo_final_existencia;
							saldo_final_existencia=saldo_final_existencia+cantidad_entradas;
							inputParams.setValue("coi_saldo_inicial_existencia", saldo_inicial_existencia);
							inputParams.setValue("coi_saldo_final_existencia", saldo_final_existencia);
							inputParams.setValue("coi_cantidad_salidas", cantidad_salidas);
							valor_inicial=valor_final;
							//
							valor_inicial_ma=valor_final_ma;
							//
							inputParams.setValue("coi_valor_inicial", valor_inicial);
							inputParams.setValue("coi_valor_inicial_ma", valor_inicial_ma);
							valor_entrada=valor_unitario_ml*cantidad_entradas;
							//
							valor_entrada_ma=valor_unitario_ma*cantidad_entradas;
							//
							inputParams.setValue("coi_valor_entrada", valor_entrada);
							inputParams.setValue("coi_valor_entrada_ma", valor_entrada_ma);
							inputParams.setValue("coi_valor_salida", valor_salida);
							inputParams.setValue("coi_valor_salida_ma", valor_salida_ma);
							valor_final=valor_inicial+valor_entrada;
							//
							valor_final_ma=valor_inicial_ma+valor_entrada_ma;
							//
							inputParams.setValue("coi_valor_final", valor_final);
							inputParams.setValue("coi_valor_final_ma", valor_final_ma);
							costo_promedio=valor_final / saldo_final_existencia;
							//
							costo_promedio_ma=valor_final_ma / saldo_final_existencia;
							//
							inputParams.setValue("coi_costo_promedio", costo_promedio);
							inputParams.setValue("coi_costo_promedio_ma", costo_promedio_ma);
							ultimo_costo=valor_unitario_ml;
							//
							ultimo_costo_ma=valor_unitario_ma;
							//
							inputParams.setValue("coi_ultimo_costo", ultimo_costo);
							inputParams.setValue("coi_ultimo_costo_ma", ultimo_costo_ma);
							costo_mas_alto=Math.max(costo_mas_alto, valor_unitario_ml);
							//
							costo_mas_alto_ma=Math.max(costo_mas_alto_ma, valor_unitario_ma);
							//
							inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
							inputParams.setValue("coi_costo_mas_alto_ma", costo_mas_alto_ma);
							costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+
									(saldo_inicial_existencia*costo_promedio_ponderado)) /
									(cantidad_entradas + saldo_inicial_existencia);
							//
							costo_promedio_ponderado_ma=((cantidad_entradas*valor_unitario_ma)+
									(saldo_inicial_existencia*costo_promedio_ponderado_ma)) /
									(cantidad_entradas + saldo_inicial_existencia);
							//
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
							inputParams.setValue("coi_costo_promedio_ponderado_ma", costo_promedio_ponderado_ma);
							
						}else{
							inputParams.setValue("coi_precio", valor_unitario_ml);
							inputParams.setValue("coi_precio_ma", valor_unitario_ma);
							cantidad_salidas=cantidad_salidas+cantidad_recibida;
							inputParams.setValue("coi_cantidad_entradas", cantidad_entradas);
							saldo_inicial_existencia=saldo_final_existencia;
							saldo_final_existencia=saldo_final_existencia-cantidad_salidas;
							inputParams.setValue("coi_saldo_inicial_existencia", saldo_inicial_existencia);
							inputParams.setValue("coi_saldo_final_existencia", saldo_final_existencia);
							inputParams.setValue("coi_cantidad_salidas", cantidad_salidas);
							valor_inicial=valor_final;
							//
							valor_inicial_ma=valor_final_ma;
							//
							inputParams.setValue("coi_valor_inicial", valor_inicial);
							inputParams.setValue("coi_valor_inicial_ma", valor_inicial_ma);
							valor_entrada=valor_unitario_ml*cantidad_entradas;
							//
							valor_entrada_ma=valor_unitario_ma*cantidad_entradas;
							//
							inputParams.setValue("coi_valor_entrada", valor_entrada);
							inputParams.setValue("coi_valor_entrada_ma", valor_entrada_ma);
							//
							if (tipo_costo.equals("PR")) {
								valor_salida=costo_promedio*cantidad_salidas;
								//
								valor_salida_ma=costo_promedio_ma*cantidad_salidas;
								//
							} else if (tipo_costo.equals("UC")) {
								valor_salida=ultimo_costo*cantidad_salidas;
								//
								valor_salida_ma=ultimo_costo_ma*cantidad_salidas;
								//
							} else if (tipo_costo.equals("CM")) {
								valor_salida=costo_mas_alto*cantidad_salidas;
								//
								valor_salida_ma=costo_mas_alto_ma*cantidad_salidas;
								//
							} else if (tipo_costo.equals("PP")) {
								valor_salida=costo_promedio_ponderado*cantidad_salidas;
								valor_salida_ma=costo_promedio_ponderado_ma*cantidad_salidas;
							}
							//
							inputParams.setValue("coi_valor_salida", valor_salida);
							inputParams.setValue("coi_valor_salida_ma", valor_salida_ma);
							valor_final=valor_inicial-valor_salida;
							//
							valor_final_ma=valor_inicial_ma-valor_salida_ma;
							//
							inputParams.setValue("coi_valor_final", valor_final);
							inputParams.setValue("coi_valor_final_ma", valor_final_ma);
							costo_promedio=valor_final / saldo_final_existencia;
							//
							costo_promedio_ma=valor_final_ma / saldo_final_existencia;
							//
							inputParams.setValue("coi_costo_promedio", costo_promedio);
							inputParams.setValue("coi_costo_promedio_ma", costo_promedio_ma);
							ultimo_costo=valor_unitario_ml;
							//
							ultimo_costo_ma=valor_unitario_ma;
							//
							inputParams.setValue("coi_ultimo_costo", ultimo_costo);
							inputParams.setValue("coi_ultimo_costo_ma", ultimo_costo_ma);
							costo_mas_alto=Math.max(costo_mas_alto, valor_unitario_ml);
							//
							costo_mas_alto_ma=Math.max(costo_mas_alto_ma, valor_unitario_ma);
							//
							inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
							inputParams.setValue("coi_costo_mas_alto_ma", costo_mas_alto_ma);
							costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+
									(saldo_inicial_existencia*costo_promedio_ponderado)) /
									(cantidad_entradas + saldo_inicial_existencia);
							//
							costo_promedio_ponderado_ma=((cantidad_entradas*valor_unitario_ma)+
									(saldo_inicial_existencia*costo_promedio_ponderado_ma)) /
									(cantidad_entradas + saldo_inicial_existencia);
							//
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
							inputParams.setValue("coi_costo_promedio_ponderado_ma", costo_promedio_ponderado_ma);
						}
						if(!existe){
							str = "Insertando control de item...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							String insertControlItem = getSQL(getResource("insert_ControlItem.sql"), inputParams);
							Recordset insControlItem = db.get(insertControlItem);
							if(insControlItem.getRecordCount() > 0) {
								while(insControlItem.next()){
									Integer control_item_id=insControlItem.getInteger("id_control_item"); 
									inputParams.setValue("smn_control_item_id", control_item_id);
									str = "Insertando relacion control de item detalle...";
									bw.write(str);
									bw.flush();
									bw.newLine();
									String insRelControlItem = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
									db.exec(insRelControlItem);
								}
								
							}else{
								mensaje = "NO INSERTO CONTROL ITEM";
								str = mensaje;
								bw.write(str);
								bw.flush();
								bw.newLine();
								inputParams.setValue("mensaje", mensaje);
								return 1;
							}
						}else{
							if(fecha_mov_cab.compareTo(fecha_movimiento)==0){
								mensaje = "MODIFICANDO CONTROL ITEM";
								str = mensaje;
								bw.write(str);
								bw.flush();
								bw.newLine();
								String updateControlItem = getSQL(getResource("update_ControlItem.sql"), inputParams);
								db.exec(updateControlItem);
								mensaje = "INSERTANDO RELACION CONTROL ITEM DETALLE";
								str = mensaje;
								bw.write(str);
								bw.flush();
								bw.newLine();
								String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
								db.exec(insRelControlItem2);
							}else{
								mensaje = "INSERTANDO CONTROL ITEM";
								str = mensaje;
								bw.write(str);
								bw.flush();
								bw.newLine();
								String insertControlItem2 = getSQL(getResource("insert_ControlItem.sql"), inputParams);
								Recordset insControlItem2 = db.get(insertControlItem2);
								if(insControlItem2.getRecordCount() > 0) {
									while(insControlItem2.next()){
										Integer control_item_id=insControlItem2.getInteger("id_control_item"); 
										inputParams.setValue("smn_control_item_id", control_item_id);
										mensaje = "INSERTANDO RELACION CONTROL ITEM DETALLE";
										str = mensaje;
										bw.write(str);
										bw.flush();
										bw.newLine();
										String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
										db.exec(insRelControlItem2);
									}
									
								}else{
									mensaje = "NO INSERTO CONTROL ITEM";
									str = mensaje;
									bw.write(str);
									bw.flush();
									bw.newLine();
									inputParams.setValue("mensaje", mensaje);
									return 1;
								}
							}
							
						}
						/*mensaje = "MODIFICANDO STATUS CABECERA DE MOVIMIENTO";
						str = mensaje;
						bw.write(str);
						bw.flush();
						bw.newLine();
						String updmovcab = getSQL(getResource("update_Movimiento_cabecera.sql"), inputParams);
						db.exec(updmovcab);*/
					}
				}else{
				mensaje = "NO HAY MOVIMIENTO DETALLE";
				str = mensaje;
				bw.write(str);
				bw.flush();
				bw.newLine();
				inputParams.setValue("mensaje", mensaje);
				return 1;
				}
			}
			mensaje = "Proceso de Control Item Exitoso";
			str = mensaje;
			bw.write(str);
			bw.flush();
			bw.newLine();
			inputParams.setValue("mensaje", mensaje);
			mensaje = "FIN DEL PROCESO";
			str = mensaje;
			bw.write(str);
			bw.flush();
			bw.newLine();
		}catch(Throwable e){
			throw e;
		}
		
		return rc;
	}
	
	private int controlLote(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable
	{
		int rc=0;                     	 //variable a retornar.
		String sql;                    	//variable que almacena instrucciones sql.
		String control_lote = "NO";     //almacena si el detalle maneja control de lote (si/no)
		
		this.setConnection(conn); 
				
		try 
		{
			str = "*Procesando control de lote*";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			Db db = getDb();
			
			sql = getSQL(getResource("select-smn_caracteristica_almacen.sql"),inputParams);
			Recordset rsCaracteristica_almacen = db.get(sql);
			
			str = "Consultando caracteristica de almacen...";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			if(rsCaracteristica_almacen.getRecordCount() > 0)
			{
				rsCaracteristica_almacen.first();
				inputParams.setValue("smn_caracteristica_almacen_id", rsCaracteristica_almacen.getInt("smn_caracteristica_almacen_id"));
			}
			
			sql = getSQL(getResource("sql-consultaMovimientoDetalle.sql"),inputParams);
			Recordset rsMovimientoDetalle = db.get(sql);
			
			str = "Consultando movimiento detalle...";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			if(rsMovimientoDetalle.getRecordCount() > 0)
			{
				str = "Total detalles encontrados = "+rsMovimientoDetalle.getRecordCount();
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				while(rsMovimientoDetalle.next())
				{
					if(rsMovimientoDetalle.getString("smn_movimiento_detalle_id") != null)
						inputParams.setValue("smn_movimiento_detalle_id", rsMovimientoDetalle.getInt("smn_movimiento_detalle_id"));
					
					if(rsMovimientoDetalle.getString("smn_item_rf") != null)
						inputParams.setValue("smn_item_rf", rsMovimientoDetalle.getInt("smn_item_rf"));
					
					sql = getSQL(getResource("select-smn_caracteristica_item.sql"),inputParams);
					Recordset rsCaracteristica_item = db.get(sql);
					
					str = "Consultando caracteristica de item...";
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					if(rsCaracteristica_item.getRecordCount() > 0)
					{
						rsCaracteristica_item.first();
						
						inputParams.setValue("smn_caracteristica_item_id", rsCaracteristica_item.getInt("smn_caracteristica_item_id"));
						
						control_lote = rsCaracteristica_item.getString("cit_req_control_lote");
						
						str = control_lote+" requiere control de lote";
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						if(control_lote.equals("SI"))
						{
							if(rsMovimientoDetalle.getString("mde_lote") != null)
								inputParams.setValue("mde_lote", rsMovimientoDetalle.getInt("mde_lote"));
							else
							{
								inputParams.setValue("mensaje", "El movimiento_detalle con id="+inputParams.getValue("smn_movimiento_detalle_id")+" no tiene lote");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							if(rsMovimientoDetalle.getString("mde_fecha_vencimiento_lote") != null)
								inputParams.setValue("mde_fecha_vencimiento_lote", rsMovimientoDetalle.getDate("mde_fecha_vencimiento_lote"));
							else
							{
								inputParams.setValue("mensaje", "El movimiento_detalle con id="+inputParams.getValue("smn_movimiento_detalle_id")+" no tiene fecha de vencimiento del lote");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
							
							sql = getSQL(getResource("sql-consultaControlItem.sql"),inputParams);
							Recordset rsControlItem = db.get(sql);
							
							str = "Consultando control de item...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							if(rsControlItem.getRecordCount() > 0)
							{
								rsControlItem.first();
								
								if(rsControlItem.getString("coi_saldo_inicial_existencia") != null)
									inputParams.setValue("coi_saldo_inicial_existencia", rsControlItem.getDouble("coi_saldo_inicial_existencia"));
								else
								{
									inputParams.setValue("mensaje", "El saldo_inicial del control item esta vacio");
									str = inputParams.getString("mensaje");
									bw.write(str);
									bw.flush();
									bw.newLine();
									return 1;
								}
								
								if(rsControlItem.getString("coi_cantidad_entradas") != null)
									inputParams.setValue("coi_cantidad_entradas", rsControlItem.getDouble("coi_cantidad_entradas"));
								else
								{
									inputParams.setValue("mensaje", "El valor_entrada del control item esta vacio");
									str = inputParams.getString("mensaje");
									bw.write(str);
									bw.flush();
									bw.newLine();
									return 1;
								}
								
								if(rsControlItem.getString("coi_cantidad_salidas") != null)
									inputParams.setValue("coi_cantidad_salidas", rsControlItem.getDouble("coi_cantidad_salidas"));
								else
								{
									inputParams.setValue("mensaje", "El valor_salida del control item esta vacio");
									str = inputParams.getString("mensaje");
									bw.write(str);
									bw.flush();
									bw.newLine();
									return 1;
								}
								
								if(rsControlItem.getString("coi_saldo_final_existencia") != null)
									inputParams.setValue("coi_saldo_final_existencia", rsControlItem.getDouble("coi_saldo_final_existencia"));
								else
								{
									inputParams.setValue("mensaje", "El valor_final del control item esta vacio");
									str = inputParams.getString("mensaje");
									bw.write(str);
									bw.flush();
									bw.newLine();
									return 1;
								}
								
								sql = getSQL(getResource("insert-smn_control_lote.sql"),inputParams);
								db.exec(sql); 
								
								str = "Registrando control de lote...";
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								rc = 0;
							}
							else
							{
								inputParams.setValue("mensaje", "El item con id="+inputParams.getValue("smn_item_rf")+" no se encontro en control item");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								return 1;
							}
						}
					}
					else
					{
						inputParams.setValue("mensaje", "El item con id="+inputParams.getValue("smn_item_rf")+" no se encontro en caracteristica item");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						return 1;
					}
				} //END-WHILE
			}
			else
			{
				inputParams.setValue("mensaje", "El movimiento no tiene detalles");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc = 1;
			}
			
			if(rc == 0)
			{
				inputParams.setValue("mensaje", "*El control de lote se realizo exitosamente*");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			else
			{
				inputParams.setValue("mensaje", "*Ocurrio un error en control de lote*");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
			}
			
		}catch(Throwable e){
			throw e;
		}
		
		return rc;
	}
	
	private int contabilizar(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable
	{
		int rc=0;                      		 //variable a retornar.
		int cantDias = 0;                    //variable que alamacen la cantidad de dias de la cond. financiera del proveedor.
		String sql;                    		 //variable que almacena instrucciones sql.
		Date mca_fecha_recibida = null;      //variable que almacena la fecha recibida del movimiento cabecera.
		double mde_monto_bruto_ml = 0.0;     //variable que almacena el monto de la recepcion en moneda local.
		double mde_monto_bruto_ma = 0.0;     //variable que almacena el monto de la recepcion en moneda local.		
		double mdi_monto_base = 0.0;         //variable que almacena el monto base de los impuestos.
		double monto_sustraendo = 0.0;       //variable que almacena el monto del sustraendo del impuesto.
		
		this.setConnection(conn); 
				
		try 
		{
			str = "Contabilizando recepcion...";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			Db db = getDb();
			
			sql = getSQL(getResource("sql-consultaMovimientoCabecera.sql"),inputParams);
			Recordset rsMovimientoCabecera = db.get(sql);
			
			if(rsMovimientoCabecera.getRecordCount() > 0)
			{
				rsMovimientoCabecera.first();
				
				sql = getSQL(getResource("select-modulo.sql"),inputParams);
				Recordset rsModulo = db.get(sql);
				
				if(rsModulo.getRecordCount() > 0)
				{
					rsModulo.first();
					
					inputParams.setValue("smn_modulos_id",rsModulo.getInt("smn_modulos_id"));
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el modulo INVENTARIO**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(rsMovimientoCabecera.getString("smn_entidad_rf") != null)
					inputParams.setValue("smn_entidad_rf", rsMovimientoCabecera.getInt("smn_entidad_rf"));
				else
				{
					inputParams.setValue("mensaje","**La recepcion generada no tiene entidad asignada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(rsMovimientoCabecera.getString("smn_sucursal_rf") != null)
					inputParams.setValue("smn_sucursal_rf", rsMovimientoCabecera.getInt("smn_sucursal_rf"));
				else
					inputParams.setValue("smn_sucursal_rf", 0);
				
				if(rsMovimientoCabecera.getString("smn_documento_id") != null)
					inputParams.setValue("smn_documento_id", rsMovimientoCabecera.getInt("smn_documento_id"));
				else
					inputParams.setValue("smn_documento_id", 0);
				
				sql = getSQL(getResource("select-documento_general.sql"),inputParams);
				Recordset rsDocGeneral = db.get(sql);
				
				if(rsDocGeneral.getRecordCount() > 0)
				{
					rsDocGeneral.first();
					
					if(rsDocGeneral.getString("smn_documento_general_rf") != null)
						inputParams.setValue("smn_documento_general_rf",rsDocGeneral.getInt("smn_documento_general_rf"));
					else
					{
						inputParams.setValue("mensaje","**El documento general esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el documento general asociado a la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				sql = getSQL(getResource("select-tipo_documento.sql"),inputParams);
				Recordset rsTipoDoc = db.get(sql);
				
				if(rsTipoDoc.getRecordCount() > 0)
				{
					rsTipoDoc.first();
					
					if(rsTipoDoc.getString("smn_tipo_documento_id") != null)
						inputParams.setValue("smn_tipo_documento_id",rsTipoDoc.getInt("smn_tipo_documento_id"));
					else
					{
						inputParams.setValue("mensaje","**El tipo de documento esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el tipo de documento asociado a la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(rsMovimientoCabecera.getString("mca_recibo") != null && rsMovimientoCabecera.getInt("mca_recibo") != 0)
					inputParams.setValue("mca_numero", rsMovimientoCabecera.getInt("mca_recibo"));
				else
				if(rsMovimientoCabecera.getString("mca_numero") != null && rsMovimientoCabecera.getInt("mca_numero") != 0)
					inputParams.setValue("mca_numero", rsMovimientoCabecera.getInt("mca_numero"));
				else
				{
					inputParams.setValue("mensaje","**La recepcion no tiene factura ni recibo**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
					
				if(rsMovimientoCabecera.getString("smn_auxiliar_rf") != null)
					inputParams.setValue("smn_auxiliar_rf", rsMovimientoCabecera.getInt("smn_auxiliar_rf"));
				else
				{
					inputParams.setValue("mensaje","**El pagador de la recepcion esta vacio**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				sql = getSQL(getResource("select-clase_auxiliar_pagador.sql"),inputParams);
				Recordset rsClaseAuxiliar = db.get(sql);
				
				if(rsClaseAuxiliar.getRecordCount() > 0)
				{
					rsClaseAuxiliar.first();
					
					if(rsClaseAuxiliar.getString("smn_clase_auxiliar_rf")!=null)
						inputParams.setValue("smn_clase_auxiliar_rf",rsClaseAuxiliar.getInt("smn_clase_auxiliar_rf"));
					else
					{
						inputParams.setValue("mensaje","**La clase auxiliar del pagador esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el auxiliar asociado al pagador de la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(rsMovimientoCabecera.getString("smn_proveedor_rf") != null)
					inputParams.setValue("smn_proveedor_rf", rsMovimientoCabecera.getInt("smn_proveedor_rf"));
				else
				{
					inputParams.setValue("mensaje","**El proveedor de la recepcion esta vacio**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				sql = getSQL(getResource("select-clase_auxiliar_proveedor.sql"),inputParams);
				Recordset rsClaseAuxiliarProv = db.get(sql);
				
				if(rsClaseAuxiliarProv.getRecordCount() > 0)
				{
					rsClaseAuxiliarProv.first();
					
					if(rsClaseAuxiliarProv.getString("smn_clase_auxiliar_rf")!=null)
						inputParams.setValue("smn_clase_auxiliar_rf_proveedor",rsClaseAuxiliarProv.getInt("smn_clase_auxiliar_rf"));
					else
					{
						inputParams.setValue("mensaje","**La clase auxiliar del proveedor esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
					
					if(rsClaseAuxiliarProv.getString("smn_auxiliar_rf")!=null)
						inputParams.setValue("smn_auxiliar_rf_proveedor",rsClaseAuxiliarProv.getInt("smn_auxiliar_rf"));
					else
					{
						inputParams.setValue("mensaje","**El auxiliar del proveedor esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
					
					if(rsClaseAuxiliarProv.getString("aux_rif")!=null)
						inputParams.setValue("aux_rif",rsClaseAuxiliarProv.getString("aux_rif"));
					else
					{
						inputParams.setValue("mensaje","**El rif del proveedor esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el auxiliar asociado al proveedor de la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(rsMovimientoCabecera.getString("smn_almacen_rf") != null)
					inputParams.setValue("smn_almacen_rf", rsMovimientoCabecera.getInt("smn_almacen_rf"));
				else
					inputParams.setValue("smn_almacen_rf", 0);
				
				sql = getSQL(getResource("select-centro_costo.sql"),inputParams);
				Recordset rsCentroCosto = db.get(sql);
				
				if(rsCentroCosto.getRecordCount() > 0)
				{
					rsCentroCosto.first();
					inputParams.setValue("smn_centro_costo_rf",rsCentroCosto.getInt("smn_centro_costo_rf"));
				}
				
				if(rsMovimientoCabecera.getString("mca_fecha_recibida") != null)
				{
					inputParams.setValue("mca_fecha_recibida", rsMovimientoCabecera.getDate("mca_fecha_recibida"));
					mca_fecha_recibida = rsMovimientoCabecera.getDate("mca_fecha_recibida");
				}
				else
				{
					inputParams.setValue("mensaje","**La fecha recibida de la recepcion generada esta vacia**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				sql = getSQL(getResource("select-smn_condicion_financiera.sql"),inputParams);
				Recordset rsCondFinanciera = db.get(sql);
				
				if(rsCondFinanciera.getRecordCount() > 0)
				{
					rsCondFinanciera.first();
					
					if(rsCondFinanciera.getString("cfi_cant_dias") != null)
						cantDias = rsCondFinanciera.getInt("cfi_cant_dias");
					else
						cantDias = 0;
				}
				
				inputParams.setValue("doc_fecha_vcto", sumarDias(mca_fecha_recibida,cantDias));
				
				sql = getSQL(getResource("select-tipo_comprobante.sql"),inputParams);
				Recordset rsTipoComprobante = db.get(sql);
				
				if(rsTipoComprobante.getRecordCount() > 0)
				{
					rsTipoComprobante.first();
					
					if(rsTipoComprobante.getString("mcc_tipo_comp") != null)
						inputParams.setValue("mcc_tipo_comp",rsTipoComprobante.getInt("mcc_tipo_comp"));
					else
					{
						inputParams.setValue("mensaje","**El tipo de comprobante esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el tipo de comprobante asociado a la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				inputParams.setValue("mca_fecha_registro", rsMovimientoCabecera.getDate("mca_fecha_registro"));
				inputParams.setValue("doc_descripcion","Movimiento Almacen Diario");
				
				str = "Registrando el documento contable...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				sql = getSQL(getResource("insert-smn_documento.sql"),inputParams);
				Recordset rsDoc = db.get(sql);
				
				str = "Se registro el documento contable...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc = 0;
				
				rsDoc.first();
				
				inputParams.setValue("smn_documento_cabecera_id", rsDoc.getInt("smn_documento_id"));
				
				sql = getSQL(getResource("select-smn_movimiento_detalle_group_by.sql"),inputParams);
				Recordset rsMovimientoDetalle = db.get(sql);
				
				str = "Consultando el movimiento detalle...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(rsMovimientoDetalle.getRecordCount() > 0)
				{
					while(rsMovimientoDetalle.next()) 
					{
						inputParams.setValue("campo", "smn_item_rf");
						inputParams.setValue("tabla", "smn_movimiento_detalle");
						
						sql = getSQL(getResource("select-smn_diccionario.sql"),inputParams);
						Recordset rsDiccionario = db.get(sql);
						
						if(rsDiccionario.getRecordCount() > 0)
						{
							rsDiccionario.first();
							inputParams.setValue("smn_diccionario_id", rsDiccionario.getInt("smn_diccionario_id"));
						}
						else
						{
							inputParams.setValue("mensaje","**El diccionario no contiene el elemento smn_item_rf**");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc = 1;
							return rc;
						}
						
						sql = getSQL(getResource("select-smn_tipo_elemento.sql"),inputParams);
						Recordset rsTipoElemento = db.get(sql);
						
						if(rsTipoElemento.getRecordCount() > 0)
						{
							rsTipoElemento.first();
							inputParams.setValue("smn_tipo_elemento_id", rsTipoElemento.getInt("smn_tipo_elemento_id"));
						}
						else
						{
							inputParams.setValue("mensaje","**No se encontro el tipo de elemento**");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc = 1;
							return rc;
						}
						
						if(rsMovimientoDetalle.getString("codigo") != null)
							inputParams.setValue("elemento", rsMovimientoDetalle.getString("codigo"));
						else
						{
							inputParams.setValue("mensaje","**No se encontro el elemento itm_codigo**");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc = 1;
							return rc;
						}
						
						if(rsMovimientoDetalle.getString("smn_item_rf") != null)
							inputParams.setValue("smn_item_rf", rsMovimientoDetalle.getInt("smn_item_rf"));
						else
						{
							inputParams.setValue("mensaje","**El item de la recepcion esta vacio**");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc = 1;
							return rc;
						}
						
						inputParams.setValue("descripcion", rsMovimientoDetalle.getString("descripcion"));
						inputParams.setValue("smn_clase_auxiliar_rf", 0);
						inputParams.setValue("smn_auxiliar_rf", 0);
						
						sql = getSQL(getResource("select-centro_costo_item.sql"),inputParams);
						Recordset rsCentroCostoItem = db.get(sql);
						
						if(rsCentroCostoItem.getRecordCount() > 0)
						{
							rsCentroCostoItem.first();
							inputParams.setValue("smn_centro_costo_rf_elemento", rsCentroCostoItem.getInt("smn_centro_costo_rf"));
						}
						else
						{
							inputParams.setValue("mensaje","**No se encontro el centro de costo de smn_item_rf**");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc = 1;
							return rc;
						}
						
						if(rsMovimientoDetalle.getString("mde_monto_bruto_ml") != null)
							inputParams.setValue("mde_monto_bruto_ml", rsMovimientoDetalle.getDouble("mde_monto_bruto_ml"));
						else
							inputParams.setValue("mde_monto_bruto_ml", 0.0);
						
						if(rsMovimientoDetalle.getString("mde_monto_bruto_ma") != null)
							inputParams.setValue("mde_monto_bruto_ma", rsMovimientoDetalle.getDouble("mde_monto_bruto_ma"));
						else
							inputParams.setValue("mde_monto_bruto_ma", 0.0);
						
						if(rsMovimientoDetalle.getString("mde_monto_neto_ml") != null)
							inputParams.setValue("mde_monto_neto_ml", rsMovimientoDetalle.getDouble("mde_monto_neto_ml"));
						else
							inputParams.setValue("mde_monto_neto_ml", 0.0);
						
						if(rsMovimientoDetalle.getString("mde_monto_neto_ma") != null)
							inputParams.setValue("mde_monto_neto_ma", rsMovimientoDetalle.getDouble("mde_monto_neto_ma"));
						else
							inputParams.setValue("mde_monto_neto_ma", 0.0);
						
						mde_monto_bruto_ml = inputParams.getDouble("mde_monto_bruto_ml");
						mde_monto_bruto_ma = inputParams.getDouble("mde_monto_bruto_ma");
						
						if(rsMovimientoDetalle.getString("smn_moneda_rf") != null)
							inputParams.setValue("smn_moneda_rf", rsMovimientoDetalle.getInt("smn_moneda_rf"));
						else
							inputParams.setValue("smn_moneda_rf", 0);
						
						if(rsMovimientoDetalle.getString("smn_tasa_rf") != null)
							inputParams.setValue("smn_tasa_rf", rsMovimientoDetalle.getInt("smn_tasa_rf"));
						else
							inputParams.setValue("smn_tasa_rf", 0);
						
						if(mde_monto_bruto_ma>0)
							inputParams.setValue("smn_tasa", mde_monto_bruto_ml/mde_monto_bruto_ma);
						else
							inputParams.setValue("smn_tasa", 0.0);
						
						str = "Registrando documento detalle...";
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						sql = getSQL(getResource("insert-smn_documento_detalle.sql"),inputParams);
						db.exec(sql);
						
						str = "documento detalle registrado correctamente";
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 0;
						
					} //END WHILE	
			
					//REGISTRAR IMPUESTOS, DESCUENTOS Y DEDUCCIONES.
					
					sql = getSQL(getResource("select-smn_movimiento_detalle_impuesto.sql"),inputParams);
					Recordset rsImpuesto = db.get(sql);
					
					if(rsImpuesto.getRecordCount() > 0)
					{
						while(rsImpuesto.next())
						{
							inputParams.setValue("campo", "smn_cod_impuesto_deduc_rf");
							inputParams.setValue("tabla", "smn_movimiento_detalle_impuesto");
							
							sql = getSQL(getResource("select-smn_diccionario.sql"),inputParams);
							Recordset rsDiccionario = db.get(sql);
							
							if(rsDiccionario.getRecordCount() > 0)
							{
								rsDiccionario.first();
								inputParams.setValue("smn_diccionario_id", rsDiccionario.getInt("smn_diccionario_id"));
							}
							else
							{
								inputParams.setValue("mensaje","**El diccionario no contiene el elemento smn_movimiento_detalle_impuesto**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							sql = getSQL(getResource("select-smn_tipo_elemento.sql"),inputParams);
							Recordset rsTipoElemento = db.get(sql);
							
							if(rsTipoElemento.getRecordCount() > 0)
							{
								rsTipoElemento.first();
								inputParams.setValue("smn_tipo_elemento_id", rsTipoElemento.getInt("smn_tipo_elemento_id"));
							}
							else
							{
								inputParams.setValue("mensaje","**No se encontro el tipo de elemento**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							if(rsImpuesto.getString("codigo") != null)
								inputParams.setValue("elemento", rsImpuesto.getString("codigo"));
							else
							{
								inputParams.setValue("mensaje","**No se encontro el elemento imp_codigo**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							inputParams.setValue("descripcion", rsMovimientoDetalle.getString("descripcion"));
							inputParams.setValue("smn_clase_auxiliar_rf", 0);
							inputParams.setValue("smn_auxiliar_rf", 0);
							
							if(rsImpuesto.getString("smn_centro_costo_rf") != null)
								inputParams.setValue("smn_centro_costo_rf_elemento", rsImpuesto.getInt("smn_centro_costo_rf"));
							else
								inputParams.setValue("smn_centro_costo_rf_elemento", 0);
							
							if(rsImpuesto.getString("mdi_monto_impuesto_ml") != null)
								inputParams.setValue("mde_monto_bruto_ml", rsImpuesto.getDouble("mdi_monto_impuesto_ml"));
							else
								inputParams.setValue("mde_monto_bruto_ml", 0.0);
							
							if(rsImpuesto.getString("mdi_monto_impuesto_ma") != null)
								inputParams.setValue("mde_monto_bruto_ma", rsImpuesto.getDouble("mdi_monto_impuesto_ma"));
							else
								inputParams.setValue("mde_monto_bruto_ma", 0.0);
							
							inputParams.setValue("mde_monto_neto_ml", 0.0);
							inputParams.setValue("mde_monto_neto_ma", 0.0);
							
							mde_monto_bruto_ml = inputParams.getDouble("mde_monto_bruto_ml");
							mde_monto_bruto_ma = inputParams.getDouble("mde_monto_bruto_ma");
							
							if(rsImpuesto.getString("smn_moneda") != null)
								inputParams.setValue("smn_moneda_rf", rsImpuesto.getInt("smn_moneda"));
							else
								inputParams.setValue("smn_moneda_rf", 0);
							
							if(rsImpuesto.getString("smn_tasa") != null)
								inputParams.setValue("smn_tasa_rf", rsImpuesto.getInt("smn_tasa"));
							else
								inputParams.setValue("smn_tasa_rf", 0);
							
							if(mde_monto_bruto_ma>0)
								inputParams.setValue("smn_tasa", mde_monto_bruto_ml/mde_monto_bruto_ma);
							else
								inputParams.setValue("smn_tasa", 0.0);
							
							str = "Registrando documento detalle...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							sql = getSQL(getResource("insert-smn_documento_detalle.sql"),inputParams);
							db.exec(sql);
							
							str = "documento detalle registrado correctamente";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							//Registrar impuesto en el modulo de impuestos.
							
							if(rsImpuesto.getString("porcentaje_base") != null)
								inputParams.setValue("porcentaje_base", rsImpuesto.getDouble("porcentaje_base"));
							else
							{
								inputParams.setValue("mensaje","**El porcentaje base relacionado al impuesto esta vacio**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							if(rsImpuesto.getString("mdi_monto_base") != null)
							{
								inputParams.setValue("mdi_monto_base", rsImpuesto.getDouble("mdi_monto_base"));
								mdi_monto_base = rsImpuesto.getDouble("mdi_monto_base");
							}
							else
							{
								inputParams.setValue("mensaje","**El monto base relacionado al impuesto esta vacio**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							inputParams.setValue("monto_base_excenta_ml", rsImpuesto.getDouble("mde_monto_bruto_ml")-mdi_monto_base);
							
							if(rsImpuesto.getString("smn_cod_impuesto_deduc_rf") != null)
								inputParams.setValue("smn_cod_impuesto_deduc_rf", rsImpuesto.getInt("smn_cod_impuesto_deduc_rf"));
							else
							{
								inputParams.setValue("mensaje","**El codigo del impuesto esta vacio**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							if(rsImpuesto.getString("porcentaje_calculo") != null)
								inputParams.setValue("porcentaje_calculo", rsImpuesto.getDouble("porcentaje_calculo"));
							else
							{
								inputParams.setValue("mensaje","**El porcentaje relacionado al impuesto esta vacio**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							if(rsImpuesto.getString("monto_sustraendo") != null)
							{
								inputParams.setValue("monto_sustraendo", rsImpuesto.getDouble("monto_sustraendo"));
								monto_sustraendo = rsImpuesto.getDouble("monto_sustraendo");
							}
							else
							{
								inputParams.setValue("monto_sustraendo", 0.0);
								monto_sustraendo = 0;
							}
							
							inputParams.setValue("mde_monto_neto_ml",mde_monto_bruto_ml-monto_sustraendo);
							
							if(rsImpuesto.getString("mde_descripcion") != null)
								inputParams.setValue("mde_descripcion", rsImpuesto.getString("mde_descripcion"));
							else
							{
								inputParams.setValue("mensaje","**La descripcion relacionada al detalle del impuesto esta vacia**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							str = "Registrando documento de impuestos...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							sql = getSQL(getResource("insert-smn_impuestos.sql"),inputParams);
							db.exec(sql);
							
							str = "documento de impuestos registrado correctamente";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							rc = 0;
						} //END WHILE
					}
					
					sql = getSQL(getResource("select-smn_movimiento_detalle_desc_ret.sql"),inputParams);
					Recordset rsDescRet = db.get(sql);
					
					if(rsDescRet.getRecordCount() > 0)
					{
						while(rsDescRet.next())
						{
							inputParams.setValue("campo", "smn_codigo_descuento_rf");
							inputParams.setValue("tabla", "smn_movimiento_detalle_desc_ret");
							
							sql = getSQL(getResource("select-smn_diccionario.sql"),inputParams);
							Recordset rsDiccionario = db.get(sql);
							
							if(rsDiccionario.getRecordCount() > 0)
							{
								rsDiccionario.first();
								inputParams.setValue("smn_diccionario_id", rsDiccionario.getInt("smn_diccionario_id"));
							}
							else
							{
								inputParams.setValue("mensaje","**El diccionario no contiene el elemento smn_codigo_descuento_rf**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							sql = getSQL(getResource("select-smn_tipo_elemento.sql"),inputParams);
							Recordset rsTipoElemento = db.get(sql);
							
							if(rsTipoElemento.getRecordCount() > 0)
							{
								rsTipoElemento.first();
								inputParams.setValue("smn_tipo_elemento_id", rsTipoElemento.getInt("smn_tipo_elemento_id"));
							}
							else
							{
								inputParams.setValue("mensaje","**No se encontro el tipo de elemento**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							if(rsImpuesto.getString("codigo") != null)
								inputParams.setValue("elemento", rsImpuesto.getString("codigo"));
							else
							{
								inputParams.setValue("mensaje","**No se encontro el elemento dyr_codigo**");
								str = inputParams.getString("mensaje");
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc = 1;
								return rc;
							}
							
							inputParams.setValue("descripcion", rsMovimientoDetalle.getString("descripcion"));
							inputParams.setValue("smn_clase_auxiliar_rf", 0);
							inputParams.setValue("smn_auxiliar_rf", 0);
							
							if(rsDescRet.getString("smn_centro_costo_rf") != null)
								inputParams.setValue("smn_centro_costo_rf_elemento", rsDescRet.getInt("smn_centro_costo_rf"));
							else
								inputParams.setValue("smn_centro_costo_rf_elemento", 0);
							
							if(rsDescRet.getString("mdd_monto_descuento_ml") != null)
								inputParams.setValue("mde_monto_bruto_ml", rsDescRet.getDouble("mdd_monto_descuento_ml"));
							else
								inputParams.setValue("mde_monto_bruto_ml", 0.0);
							
							if(rsDescRet.getString("mdd_monto_descuento_ma") != null)
								inputParams.setValue("mde_monto_bruto_ma", rsDescRet.getDouble("mdd_monto_descuento_ma"));
							else
								inputParams.setValue("mde_monto_bruto_ma", 0.0);
							
							inputParams.setValue("mde_monto_neto_ml", 0.0);
							inputParams.setValue("mde_monto_neto_ma", 0.0);
							
							mde_monto_bruto_ml = inputParams.getDouble("mde_monto_bruto_ml");
							mde_monto_bruto_ma = inputParams.getDouble("mde_monto_bruto_ma");
							
							if(rsDescRet.getString("smn_moneda_rf") != null)
								inputParams.setValue("smn_moneda_rf", rsDescRet.getInt("smn_moneda_rf"));
							else
								inputParams.setValue("smn_moneda_rf", 0);
							
							if(rsDescRet.getString("smn_tasa_rf") != null)
								inputParams.setValue("smn_tasa_rf", rsDescRet.getInt("smn_tasa_rf"));
							else
								inputParams.setValue("smn_tasa_rf", 0);
							
							if(mde_monto_bruto_ma>0)
								inputParams.setValue("smn_tasa", mde_monto_bruto_ml/mde_monto_bruto_ma);
							else
								inputParams.setValue("smn_tasa", 0.0);
							
							str = "Registrando documento detalle...";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							sql = getSQL(getResource("insert-smn_documento_detalle.sql"),inputParams);
							db.exec(sql);
							
							str = "documento detalle registrado correctamente";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							
							rc = 0;
							
						} //END WHILE
					}
					
					if(rc==0)
					{
						str = "Actualizando montos en el documento...";
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						sql = getSQL(getResource("update-smn_documento.sql"),inputParams);
						db.exec(sql);
						
						str = "Montos del documento actualizados correctamente";
						bw.write(str);
						bw.flush();
						bw.newLine();
					}
					else
					{
						inputParams.setValue("mensaje","**No se actualizaron los montos del documento**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontraron detalles de la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
				}
			}
			else
			{
				inputParams.setValue("mensaje","**La recepcion no tiene estatus REGISTRADO**");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc = 1;
				return rc;
			}
			
		}catch(Throwable e){
			throw e;
		}
		
		return rc;
	}
	
	private String sumarDias(Date fecha, int dias)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_formateada = format1.format(calendar.getTime());
		
		return fecha_formateada;
	}
	
	private int nota_entrada(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable
	{
		int rc=0;                      		 //variable a retornar.
		String sql;                    		 //variable que almacena instrucciones sql.	
		
		this.setConnection(conn); 
				
		try 
		{
			str = "Registrar Nota de entrada...";
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			Db db = getDb();
			
			sql = getSQL(getResource("sql-consultaMovimientoCabecera.sql"),inputParams);
			Recordset movimiento_cabecera = db.get(sql);
			
			if(movimiento_cabecera.getRecordCount() > 0)
			{
				movimiento_cabecera.first();
				
				if(movimiento_cabecera.getString("smn_documento_id") != null)
					inputParams.setValue("smn_documento_id", movimiento_cabecera.getInt("smn_documento_id"));
				else
					inputParams.setValue("smn_documento_id", 0);
				
				sql = getSQL(getResource("select-documento_general.sql"),inputParams);
				Recordset rsDocGeneral = db.get(sql);
				
				if(rsDocGeneral.getRecordCount() > 0)
				{
					rsDocGeneral.first();
					
					if(rsDocGeneral.getString("smn_documento_general_rf") != null)
						inputParams.setValue("smn_documento_general_rf",rsDocGeneral.getInt("smn_documento_general_rf"));
					else
					{
						inputParams.setValue("mensaje","**El documento general esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el documento general asociado a la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(movimiento_cabecera.getString("mca_recibo") != null)
					inputParams.setValue("mca_numero", movimiento_cabecera.getInt("mca_recibo"));
				else
				if(movimiento_cabecera.getString("mca_numero") != null)
					inputParams.setValue("mca_numero", movimiento_cabecera.getInt("mca_numero"));
				
				if(movimiento_cabecera.getString("smn_proveedor_rf") != null)
					inputParams.setValue("smn_proveedor_rf", movimiento_cabecera.getInt("smn_proveedor_rf"));
				else
				{
					inputParams.setValue("mensaje","**El proveedor de la recepcion esta vacio**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				sql = getSQL(getResource("select-clase_auxiliar_proveedor.sql"),inputParams);
				Recordset rsClaseAuxiliar = db.get(sql);
				
				if(rsClaseAuxiliar.getRecordCount() > 0)
				{
					rsClaseAuxiliar.first();
					
					if(rsClaseAuxiliar.getString("smn_clase_auxiliar_rf")!=null)
						inputParams.setValue("smn_clase_auxiliar_rf",rsClaseAuxiliar.getInt("smn_clase_auxiliar_rf"));
					else
					{
						inputParams.setValue("mensaje","**La clase auxiliar del proveedor esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro la clase auxiliar asociada al proveedor de la recepcion generada**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(movimiento_cabecera.getString("smn_almacen_rf") != null)
					inputParams.setValue("smn_almacen_rf", movimiento_cabecera.getInt("smn_almacen_rf"));
				else
					inputParams.setValue("smn_almacen_rf", 0);
				
				sql = getSQL(getResource("select-usuario_auxiliar.sql"),inputParams);
				Recordset rsUsuarioAux = db.get(sql);
				
				if(rsUsuarioAux.getRecordCount() > 0)
				{
					rsUsuarioAux.first();
					
					if(rsUsuarioAux.getString("smn_auxiliar_rf") != null)
						inputParams.setValue("smn_usuario_rf",rsUsuarioAux.getInt("smn_auxiliar_rf"));
					else
					{
						inputParams.setValue("mensaje","**El usuario relacionado al movimiento esta vacio**");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
						return rc;
					}
				}
				else
				{
					inputParams.setValue("mensaje","**No se encontro el auxiliar del usuario relacionado movimiento**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(movimiento_cabecera.getString("mca_monto_documento_ml") != null)
					inputParams.setValue("mca_monto_documento_ml", movimiento_cabecera.getDouble("mca_monto_documento_ml"));
				else
				{
					inputParams.setValue("mensaje","**el Monto del documento de la recepcion generada esta vacio**");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
					return rc;
				}
				
				if(movimiento_cabecera.getString("mca_monto_documento_ma") != null)
					inputParams.setValue("mca_monto_documento_ma", movimiento_cabecera.getDouble("mca_monto_documento_ma"));
				else
					inputParams.setValue("mca_monto_documento_ma", 0.0);
				
				if(movimiento_cabecera.getString("smn_moneda_rf") != null)
					inputParams.setValue("smn_moneda_rf", movimiento_cabecera.getInt("smn_moneda_rf"));
				else
					inputParams.setValue("smn_moneda_rf", 0);
				
				str = "Registrando nota de entrada...";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				sql = getSQL(getResource("insert-smn_nota_entrada.sql"),inputParams);
				db.exec(sql);
				
				str = "Nota de entrada registrada correctamente";
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				rc = 0;
			}
			else
			{
				inputParams.setValue("mensaje","**La recepcion no tiene estatus REGISTRADO**");
				str = inputParams.getString("mensaje");
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc = 1;
				return rc;
			}
			
		}catch(Throwable e){
			throw e;
		}
		
		return rc;
	}
}