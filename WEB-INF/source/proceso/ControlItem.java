package proceso;

import dinamica.*;

import javax.sql.DataSource;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Calendar;
import java.util.Date;
//import java.util.List;

public class ControlItem extends GenericTransaction
{
	/* (non-Javadoc)
	 * @see dinamica.GenericTransaction#pedido(dinamica.Recordset)
	 */
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public int service(Recordset inputParams) throws Throwable{
			
		int rc = 0;
		Double precio;
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
		Integer cantidad_recibida;
		Double valor_unitario_ml;
		
		String mensaje = "";
		String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		File newLogFile = new File("D:/data/Simone/smn_inventario/log/log_CostoItem"+fechaActual+".txt");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        FileWriter fw = new FileWriter(newLogFile);
		super.service(inputParams);
		
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");
		
		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		DataSource ds = Jndi.getDataSource(jndiName); 
		Connection conn = ds.getConnection();
		this.setConnection(conn);
				
		try {
		
			Db db = getDb();
			String str="";
			str = str + "PROCESAR CABECERA INVENTARIO"+ " \n";	
			fw.write(str);
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
					
					//CalculoCostosInventario calculoCostosInventario = new CalculoCostosInventario();
					//calculoCostosInventario.tipoCostoSalidas(TIPO_COSTO.ULTIMO_COSTO);
					while (rsMovimientoDetalle.next())
					{
						str = "PROCESAR CABECERA DETALLE INVENTARIO"+ " \n";	
						fw.write(str);
						tipo_movimiento=rsMovimientoDetalle.getString("mde_tipo_movimiento");
						cantidad_recibida=rsMovimientoDetalle.getInteger("mde_cantidad_recibida");
						valor_unitario_ml=rsMovimientoDetalle.getDouble("mde_valor_unitario_ml");
						inputParams.setValue("coi_fecha_movimiento", fecha_mov_cab);
						tipo_costo=rsMovimientoDetalle.getString("cit_tipo_costo");
						item_id=rsMovimientoDetalle.getInteger("item_rf");
						inputParams.setValue("smn_item_id", item_id);
						Integer movimiento_detalle_id=rsMovimientoDetalle.getInteger("smn_movimiento_detalle_id");
						inputParams.setValue("smn_movimiento_detalle_id", movimiento_detalle_id);
						
						String sqlCheckControl = getSQL(getResource("sql-consultaControlItem.sql"), inputParams);
						Recordset rssqlCheckControl = db.get(sqlCheckControl);
					
						if(rssqlCheckControl.getRecordCount() > 0) {
							while (rssqlCheckControl.next())
							{
								str = "PROCESAR CONTROL ITEM"+ " \n";	
								fw.write(str);
								inputParams.setValue("smn_control_item_id", rssqlCheckControl.getInteger("smn_control_item_id"));
								existe=true;
								precio=rssqlCheckControl.getDouble("coi_precio");
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
							str = "PROCESAR CONTROL ITEM NO EXISTE"+ " \n";	
							fw.write(str);
							existe=false;
							precio=0.00;
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
						fw.write(timestamp+": tipo_movimiento "+tipo_movimiento+" \n");
						if(tipo_movimiento.equals("EN")){
							str = "PROCESAR ENTRADA"+ " \n";	
							fw.write(str);
							inputParams.setValue("coi_precio", valor_unitario_ml);
							cantidad_entradas=cantidad_entradas+cantidad_recibida;
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

							fw.write(timestamp+": cantidad_entradas "+cantidad_entradas+" \n");
							fw.write(timestamp+": valor_unitario_ml "+valor_unitario_ml+" \n");
							fw.write(timestamp+": saldo_inicial_existencia "+saldo_inicial_existencia+" \n");
							fw.write(timestamp+": costo_promedio_ponderado "+costo_promedio_ponderado+" \n");
							
							costo_mas_alto=Math.max(costo_mas_alto, valor_unitario_ml);
							inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
							costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+
									(saldo_inicial_existencia*costo_promedio_ponderado)) /
									(cantidad_entradas + saldo_inicial_existencia);
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
							
							
						}else{
							str = "PROCESAR SALIDA"+ " \n";	
							fw.write(str);
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
							
							fw.write(timestamp+": cantidad_entradas "+cantidad_entradas+" \n");
							fw.write(timestamp+": valor_unitario_ml "+valor_unitario_ml+" \n");
							fw.write(timestamp+": saldo_inicial_existencia "+saldo_inicial_existencia+" \n");
							fw.write(timestamp+": costo_promedio_ponderado "+costo_promedio_ponderado+" \n");
							
							costo_promedio_ponderado=((cantidad_entradas*valor_unitario_ml)+
									(saldo_inicial_existencia*costo_promedio_ponderado)) /
									(cantidad_entradas + saldo_inicial_existencia);
							inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
						}
						if(!existe){
							str = "INSERTANDO CONTROL ITEM"+ " \n";	
							fw.write(str);
							String insertControlItem = getSQL(getResource("insert_ControlItem.sql"), inputParams);
							Recordset insControlItem = db.get(insertControlItem);
							if(insControlItem.getRecordCount() > 0) {
								while(insControlItem.next()){
									Integer control_item_id=insControlItem.getInteger("id_control_item"); 
									inputParams.setValue("smn_control_item_id", control_item_id);
									str = "INSERTANDO RELACION CONTROL ITEM DETALLE"+ " \n";	
									fw.write(str);
									String insRelControlItem = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
									db.exec(insRelControlItem);
								}
								
							}else{
								str = "NOOO INSERTO CONTROL ITEM"+ " \n";	
								fw.write(str);
							}
						}else{
							fw.write(timestamp+": fecha_mov_cab "+fecha_mov_cab+" \n");
							fw.write(timestamp+": fecha_movimiento "+fecha_movimiento+" \n");
							fw.write(str);
							if(fecha_mov_cab.compareTo(fecha_movimiento)==0){
								str = "MODIFICANDO CONTROL ITEM"+ " \n";	
								fw.write(str);
								String updateControlItem = getSQL(getResource("update_ControlItem.sql"), inputParams);
								db.exec(updateControlItem);
								str = "INSERTANDO RELACION CONTROL ITEM DETALLE"+ " \n";	
								fw.write(str);
								String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
								db.exec(insRelControlItem2);
							}else{
								str = "INSERTANDO CONTROL ITEM"+ " \n";	
								fw.write(str);
								String insertControlItem2 = getSQL(getResource("insert_ControlItem.sql"), inputParams);
								Recordset insControlItem2 = db.get(insertControlItem2);
								if(insControlItem2.getRecordCount() > 0) {
									while(insControlItem2.next()){
										Integer control_item_id=insControlItem2.getInteger("id_control_item"); 
										inputParams.setValue("smn_control_item_id", control_item_id);
										str = "INSERTANDO RELACION CONTROL ITEM DETALLE"+ " \n";	
										fw.write(str);
										String insRelControlItem2 = getSQL(getResource("insert_relacionControlItem.sql"), inputParams);
										db.exec(insRelControlItem2);
									}
									
								}else{
									str = "NOOO INSERTO CONTROL ITEM"+ " \n";	
									fw.write(str);
								}
							}
							
						}
						str = "MODIFICANDO STATUS CABECERA DE MOVIMIENTO"+ " \n";	
						fw.write(str);
						String updmovcab = getSQL(getResource("update_Movimiento_cabecera.sql"), inputParams);
						db.exec(updmovcab);
						
						
						/*Item item = new Item();
						item.setId(rsMovimientoDetalle.getInt("smn_inventario_detalle_id"));
						
						Date fecha_movimiento=rsMovimientoDetalle.getDate("mde_fecha_registro");
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(fecha_movimiento);
						LocalDate date = LocalDate.of(cal.get(Calendar.YEAR),
						        cal.get(Calendar.MONTH) + 1,
						        cal.get(Calendar.DAY_OF_MONTH));

						item.setFecha(date);
						item.setClaveItem(rsMovimientoDetalle.getInt("smn_item_rf"));
						//item.setClaveItemDetalle(21);
						item.setPrecio(rsMovimientoDetalle.getInt("mde_valor_unitario_ml"));
						if (rsMovimientoDetalle.getString("mde_tipo_movimiento").compareTo("EN")==0){
							item.setCantEntradas(rsMovimientoDetalle.getInt("mde_cantidad_recibida"));
						}else{
							item.setCantSalidas(rsMovimientoDetalle.getInt("mde_cantidad_recibida"));
						}
						
						calculoCostosInventario.agregarItemLista(item);*/
					}
					//calculoCostosInventario.ordenarItemsPorFecha();

					//calculoCostosInventario.generarCostos();
					//List<Item> listaItems=calculoCostosInventario.obtenerListaItem();
					
				}else{
				
				str = "NO HAY MOVIMIENTO DETALLE"+ " \n";	
				mensaje=str;
				inputParams.setValue("mensaje", mensaje);
		        fw.write(str);
		        fw.close();
				return rc;
				}
			}
			
			str = "Proceso de Control Item Exitoso"+ " \n";	
			mensaje=str;
			inputParams.setValue("mensaje", mensaje);
			str = "FIN DEL PROCESO"+ " \n";	
	        fw.write(str);
	        fw.close();
		}catch (Throwable e){
			throw e;
			
		}
		finally
		{
			if (conn!=null)
				conn.close();
			   fw.close();
		}
		fw.close();
		return rc;
	}

}
