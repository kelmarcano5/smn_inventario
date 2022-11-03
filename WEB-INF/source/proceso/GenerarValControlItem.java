package proceso;

import dinamica.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class GenerarValControlItem extends GenericTransaction{
	/* (non-Javadoc)
	 * @see dinamica.GenericTransaction#pedido(dinamica.Recordset)
	 */
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public int service(Recordset inputParams) throws Throwable{
		int rc=0;
		int movimiento_cabecera_id = inputParams.getInt("smn_movimiento_cabecera_id");
		String sql;                           //archivos .sql
		String mde_estatus;                   //estatus de movimiento_detalle
		double mde_cantidad_solicitada;       //variable que almacena la cantidad solicitada del movimiento.
		double mde_cantidad_recibida;         //variable que almacena la cantidad recibida del movimiento.
		double mde_cantidad_por_recibir;      //variable que almacena la cantidad por recibir del movimiento.
		int contEstatusInvalidos = 0;         //contador de estatus de los movimiento_detalle invalidos.
		boolean interruptor_PR = false;       //interruptor para determinar si existe algun movimiento_detalle con estatus PR.
		
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");
		
		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		DataSource ds = Jndi.getDataSource(jndiName); 
		Connection conn = ds.getConnection();
		this.setConnection(conn);
		conn.setAutoCommit(false);
		
		String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String file = "../logControlItemValoracion_"+fechaActual+".txt";
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
			 
			sql = getSQL(getResource("sql-consultaMovimientoDetalle.sql"),inputParams);
			Recordset movimientoDetalle = db.get(sql);
			
			if(movimientoDetalle.getRecordCount() > 0)
			{
				while(movimientoDetalle.next())
				{
					mde_estatus = movimientoDetalle.getString("mde_estatus");
					
					mde_cantidad_solicitada = movimientoDetalle.getDouble("mde_cantidad_solicitada");
					
					if(movimientoDetalle.getString("mde_cantidad_recibida") != null)
						mde_cantidad_recibida = movimientoDetalle.getDouble("mde_cantidad_recibida");
					else
						mde_cantidad_recibida = 0.0;
					
					if(movimientoDetalle.getString("mde_cantidad_por_recibir") != null)
						mde_cantidad_por_recibir = movimientoDetalle.getDouble("mde_cantidad_por_recibir");
					else
						mde_cantidad_por_recibir = 0.0;
						
					//VALIDAR QUE TODOS LOS DETALLES TENGAN ESTATUS RC O PR.
					
					if(mde_estatus.equals("PE") || mde_estatus.equals("CE"))
					{
						if(mde_estatus.equals("PE"))
						{
							if((mde_cantidad_recibida+mde_cantidad_por_recibir) < mde_cantidad_solicitada)
								interruptor_PR = true;
						}
					}
					else
					{
						contEstatusInvalidos++;
					}
				} //END-WHILE
				
				if(contEstatusInvalidos != movimientoDetalle.getRecordCount())
				{
					sql = getSQL(getResource("sql-consultaMovimientoCabecera.sql"),inputParams);
					Recordset rsMovimientoCabecera = db.get(sql);
					
					str = "Consultando movimiento con id = "+movimiento_cabecera_id;
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					if(rsMovimientoCabecera.getRecordCount() > 0)
					{
						rsMovimientoCabecera.first();
					
						str = "Procesando movimiento...";
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
							return 1;
						}
						
						if(rsMovimientoCabecera.getString("smn_sucursal_rf") != null)
							inputParams.setValue("smn_sucursal_rf", rsMovimientoCabecera.getInt("smn_sucursal_rf"));
					
						if(interruptor_PR)
							inputParams.setValue("mca_estatus_proceso","PR");
						else
						if(contEstatusInvalidos==0)
							inputParams.setValue("mca_estatus_proceso","RC");
						else
						{
							inputParams.setValue("mensaje","*Aun hay movimientos_detalles sin recibir*");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
							return 1;
						}
						
						//rc = this.controlUbicaciones(conn,inputParams,str,bw);//registra control de ubicacion si lo requiere.
						rc = this.controlItem(conn,inputParams,str,bw); //registra control de item si lo requiere.
						if(rc == 0) 
						{
							//rc = this.controlItem(conn,inputParams,str,bw); //registra control de item si lo requiere.
/*							if(rc == 0) 
							{
								//rc = this.controlLote(conn, inputParams,str,bw); //registra control de lote si lo requiere.								
								if(rc == 0) 
								{
								//	rc = this.actualizar_movimiento_cabecera(conn,inputParams,str,bw);
									if(rc == 0) //actualiza los campos calculados de movimiento_cabecera.
									{
									//	rc = this.control_recepcion_parcial(conn,inputParams,str,bw);
										if(rc == 0)
										{
									//		rc = this.actualizar_movimiento_detalle(conn,inputParams,str,bw);
											if(rc == 0)
											{
												inputParams.setValue("mensaje", "Recepcion procesada exitosamente");
												str = inputParams.getString("mensaje");
												bw.write(str);
												bw.flush();
												bw.newLine();
											
											}
										}
									}
								}
							}*/
						}
						
						if(rc == 0)
						{
							sql = getSQL(getResource("update-mca_estatus.sql"),inputParams);
							db.exec(sql); //actualiza el estatus del movimiento_cabecera
							
							str = "Estatus del movimiento_cabecera actualizado";
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							rc = 0;
							inputParams.setValue("mensaje","El movimiento se genero exitosamente");
							str = inputParams.getString("mensaje");
							bw.write(str);
							bw.flush();
							bw.newLine();
						}
					}
					else
					{
						inputParams.setValue("mensaje","*El movimiento no tiene estatus GENERADO o PARCIALMENTE RECIBIDO*");
						str = inputParams.getString("mensaje");
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc = 1;
					}
				}
				else
				{
					inputParams.setValue("mensaje", "*No hay detalles del movimiento con el id "+movimiento_cabecera_id+" pendientes por procesar*");
					str = inputParams.getString("mensaje");
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc = 1;
				}
			}
			else
			{
				inputParams.setValue("mensaje", "*El movimiento con el id "+movimiento_cabecera_id+" no tiene detalles*");
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
	
	
	private int controlItem(Connection conn, Recordset inputParams, String str, BufferedWriter bw) throws Throwable{
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

}
