package proceso;

import dinamica.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class DespacharTransferencia extends GenericTransaction{

	public int service(Recordset inputParams) throws Throwable {
		// TODO Auto-generated constructor stub
		int rc=0;
		
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
			file =  "C:/log/logDespachoTransferir_"+fechaActual+".txt";
		else
			file = "./logDespachoTransferir_"+fechaActual+".txt";
		
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
			
			String sqlDespacho = getSQL(getResource("consultar_despacho.sql"),inputParams);
			Recordset rsDespacho = db.get(sqlDespacho);
			
			str = "> CONSULTANDO DESPACHO <";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			if(rsDespacho.getRecordCount()>0)
			{
				rsDespacho.first();
				
				str = "-Despacho encontrado, validando data...";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				//**extraccion de datos de la tabla smn_despacho**
	 
				if(rsDespacho.getString("smn_entidad_rf")!=null)
					inputParams.setValue("smn_entidad_rf", rsDespacho.getInt("smn_entidad_rf"));
				else
					inputParams.setValue("smn_entidad_rf", 0);
				
				if(rsDespacho.getString("smn_sucursal_rf")!=null)
					inputParams.setValue("smn_sucursal_rf", rsDespacho.getInt("smn_sucursal_rf"));
				else
					inputParams.setValue("smn_sucursal_rf", 0);
				
				if(rsDespacho.getString("smn_almacen_rf")!=null)
					inputParams.setValue("smn_almacen_rf", rsDespacho.getInt("smn_almacen_rf"));
				else
					inputParams.setValue("smn_almacen_rf", 0);
				
				if(rsDespacho.getString("smn_modulo_rf")!=null)
					inputParams.setValue("smn_modulo_rf", rsDespacho.getInt("smn_modulo_rf"));
				else
					inputParams.setValue("smn_modulo_rf", 0);
				
				if(rsDespacho.getString("smn_documento_origen_id")!=null)
					inputParams.setValue("smn_documento_origen_id", rsDespacho.getInt("smn_documento_origen_id"));
				else
					inputParams.setValue("smn_documento_origen_id", 0);
				
				if(rsDespacho.getString("des_numero_documento_origen")!=null)
					inputParams.setValue("des_numero_documento_origen", rsDespacho.getInt("des_numero_documento_origen"));
				else
					inputParams.setValue("des_numero_documento_origen", 0);
	
				if(rsDespacho.getString("smn_documento_id")!=null)
					inputParams.setValue("smn_documento_id", rsDespacho.getInt("smn_documento_id"));
				else
					inputParams.setValue("smn_documento_id", 0);
				
				if(rsDespacho.getString("des_numero_documento")!=null)
					inputParams.setValue("des_numero_documento", rsDespacho.getInt("des_numero_documento"));
				else
					inputParams.setValue("des_numero_documento", 0);
				
				if(rsDespacho.getString("des_monto_pedido_ml")!=null)
					inputParams.setValue("des_monto_pedido_ml", rsDespacho.getDouble("des_monto_pedido_ml"));
				else
					inputParams.setValue("des_monto_pedido_ml", 0.0);
				
				if(rsDespacho.getString("des_monto_pedido_ma")!=null)
					inputParams.setValue("des_monto_pedido_ma", rsDespacho.getDouble("des_monto_pedido_ma"));
				else
					inputParams.setValue("des_monto_pedido_ma",0.0);
				
				if(rsDespacho.getString("des_monto_descuento_ml")!=null)
					inputParams.setValue("des_monto_descuento_ml", rsDespacho.getDouble("des_monto_descuento_ml"));
				else
					inputParams.setValue("des_monto_descuento_ml", 0.0);
				
				if(rsDespacho.getString("des_monto_descuento_ma")!=null)
					inputParams.setValue("des_monto_descuento_ma", rsDespacho.getDouble("des_monto_descuento_ma"));
				else
					inputParams.setValue("des_monto_descuento_ma", 0.0);
				
				Date fecha_despacho=new Date(); 
				
				if(rsDespacho.getString("des_fecha_despacho")!=null)
					fecha_despacho=rsDespacho.getDate("des_fecha_despacho");
				/*else
					fecha_despacho=null;*/
				
				double impuesto_ml;
				
				if(rsDespacho.getString("des_monto_impuesto_ml")!=null)
					impuesto_ml = rsDespacho.getDouble("des_monto_impuesto_ml");
				else
					impuesto_ml = 0.0;
				
				inputParams.setValue("des_monto_impuesto_ml", impuesto_ml);
				
				double bonificacion_ml;
				
				if(rsDespacho.getString("des_monto_bonificacion_ml")!=null)
					bonificacion_ml =  rsDespacho.getDouble("des_monto_bonificacion_ml");
				else
					bonificacion_ml = 0.0;
				
				inputParams.setValue("des_monto_bonificacion_ml", bonificacion_ml);
				
				double monto_ml = impuesto_ml + bonificacion_ml;
				inputParams.setValue("mca_monto_valor_agregado_ml", monto_ml);
				
				double impuesto_ma;
				
				if(rsDespacho.getString("des_monto_impuesto_ma")!=null)
					impuesto_ma = rsDespacho.getDouble("des_monto_impuesto_ma");
				else
					impuesto_ma = 0.0;
	
				inputParams.setValue("des_monto_impuesto_ma", impuesto_ma);	
				
				double bonificacion_ma;
				
				if(rsDespacho.getString("des_monto_bonificacion_ma")!=null)
					bonificacion_ma = rsDespacho.getDouble("des_monto_bonificacion_ma");
				else
					bonificacion_ma = 0.0;
				
				inputParams.setValue("des_monto_bonificacion_ma", bonificacion_ma);
				
				double monto_ma = impuesto_ma + bonificacion_ma;
				inputParams.setValue("mca_monto_valor_agregado_ma", monto_ma);
				
				if(rsDespacho.getString("des_monto_neto_ml")!=null)
					inputParams.setValue("des_monto_neto_ml", rsDespacho.getDouble("des_monto_neto_ml"));
				else
					inputParams.setValue("des_monto_neto_ml", 0.0);
		
				if(rsDespacho.getString("des_monto_neto_ma")!=null)
					inputParams.setValue("des_monto_neto_ma", rsDespacho.getDouble("des_monto_neto_ma"));
				else
					inputParams.setValue("des_monto_neto_ma", 0.0);
		
				if(rsDespacho.getString("smn_moneda_rf")!=null)
					inputParams.setValue("smn_moneda_rf", rsDespacho.getInt("smn_moneda_rf"));
				else
					inputParams.setValue("smn_moneda_rf", 0);
				
				if(rsDespacho.getString("smn_tasa_rf")!=null)
					inputParams.setValue("smn_tasa_rf", rsDespacho.getInt("smn_tasa_rf"));
				else
					inputParams.setValue("smn_tasa_rf", 0);
				
				//Busca el almacen solicitante
				if(rsDespacho.getString("smn_almacen_solicitante")!=null)
					inputParams.setValue("smn_almacen_solicitante", rsDespacho.getInt("smn_almacen_solicitante"));
				else
					inputParams.setValue("smn_almacen_solicitante", 0);
				
								
				//insertar registros de smn_despacho -> smn_movimiento_cabecera
				//Almacen Proveedor
				String sqlMovimiento    = getSQL(getResource("insertar-movimiento_cabecera.sql"),inputParams);
				Recordset rsMovimiento  = db.get(sqlMovimiento);
				
				str = "< Registrando movimiento cabecera... >";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				if(rsMovimiento.getRecordCount()>0)
				{
					str = "-Movimiento registrado exitosamente";	
					bw.write(str);
					bw.flush();
					bw.newLine();
					rsMovimiento.first();
					String movimientoCabeceraId = rsMovimiento.getString("movimiento_cabecera_id");
					
					//insertar registros de smn_despacho -> smn_movimiento_cabecera
					//Almacen Solicitante
					String sqlMovimientoSolicitante    = getSQL(getResource("insertar-movimiento_cabecera_sol.sql"),inputParams);
					Recordset rsMovimientoSolicitante  = db.get(sqlMovimientoSolicitante);
					
					str = "< Registrando movimiento cabecera del almacen solicitante... >";	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					if(rsMovimientoSolicitante.getRecordCount()>0)
					{
						str = "-Movimiento del almacen solicitante registrado exitosamente";	
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						rsMovimientoSolicitante.first();
						String movimientoCabeceraIdSol = rsMovimientoSolicitante.getString("movimiento_cabecera_id");
						
						if(movimientoCabeceraIdSol!="0" && movimientoCabeceraIdSol!=null)
						{	
							int idMovSolicitante = Integer.parseInt(movimientoCabeceraIdSol);
							
							inputParams.setValue("smn_movimiento_cabecera_id_sol", idMovSolicitante );
						}
					}
					
					
					if(movimientoCabeceraId!="0" && movimientoCabeceraId!=null)
					{	
						int id = Integer.parseInt(movimientoCabeceraId);
						
						inputParams.setValue("smn_movimiento_cabecera_id", id);
						String sqlDespacho_detalle   = getSQL(getResource("consultar-despacho_detalle.sql"),inputParams);
						Recordset rsDespacho_detalle = db.get(sqlDespacho_detalle);
						
						str = "> CONSULTANDO DETALLES DEL DESPACHO <";	
						bw.write(str);
						bw.flush();
						bw.newLine();
						
						if(rsDespacho_detalle.getRecordCount()>0)
						{
							str = "-Detalles encontrados, validando data...";	
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							String cit_tipo_costo;
							
							while(rsDespacho_detalle.next()!=false)
							{
								rsDespacho_detalle.next();
								
								if(rsDespacho_detalle.getString("smn_despacho_detalle_id")!=null)
									inputParams.setValue("smn_despacho_detalle_id", rsDespacho_detalle.getInt("smn_despacho_detalle_id"));
								else
									inputParams.setValue("smn_despacho_detalle_id", 0);
								
								if(rsDespacho_detalle.getString("smn_item_rf")!=null)
									inputParams.setValue("smn_item_rf", rsDespacho_detalle.getInt("smn_item_rf"));
								else
									inputParams.setValue("smn_item_rf", 0);
									
								String sql = getSQL(getResource("consultar-centro_costo.sql"),inputParams);
								Recordset rsCentroCosto = db.get(sql);
								
								if(rsCentroCosto.getRecordCount()>0)
								{
									rsCentroCosto.first();
									
									if(rsCentroCosto.getString("smn_centro_costo_rf")!=null)
										inputParams.setValue("smn_centro_costo_rf",rsCentroCosto.getInt("smn_centro_costo_rf"));
									else
										inputParams.setValue("smn_centro_costo_rf",0);
								}
								else
									inputParams.setValue("smn_centro_costo_rf",0);
								
								sql = getSQL(getResource("consultar-unidad_medida_venta.sql"),inputParams);
								Recordset rsUnidadMedida = db.get(sql);
								
								if(rsUnidadMedida.getRecordCount()>0)
								{
									rsUnidadMedida.first();
									
									if(rsUnidadMedida.getString("smn_unidad_medida_venta_rf")!=null)
										inputParams.setValue("smn_unidad_medida_venta_rf",rsUnidadMedida.getInt("smn_unidad_medida_venta_rf"));
									else
										inputParams.setValue("smn_unidad_medida_venta_rf",0);
								}
								else
									inputParams.setValue("smn_unidad_medida_venta_rf",0);
								
								double cantidadDespachada;
								
								if(rsDespacho_detalle.getString("dde_cantidad_despachada")!=null)
									cantidadDespachada =  rsDespacho_detalle.getDouble("dde_cantidad_despachada");
								else
									cantidadDespachada = 0.0;
								
								inputParams.setValue("dde_cantidad_despachada", cantidadDespachada);
							
								double costo_ml;
								
								if(rsDespacho_detalle.getString("dde_costo_ml")!=null)
									costo_ml = rsDespacho_detalle.getDouble("dde_costo_ml");
								else
									costo_ml = 0.0;
								
								inputParams.setValue("dde_costo_ml", costo_ml);
								
								double costo_ma;
								
								if(rsDespacho_detalle.getString("dde_costo_ma")!=null)
									costo_ma = rsDespacho_detalle.getDouble("dde_costo_ma");
								else
									costo_ma = 0.0;
								
								inputParams.setValue("dde_costo_ma", costo_ma);
								
								double montoBruto_ml = cantidadDespachada * costo_ml;
								double montoBruto_ma = cantidadDespachada * costo_ma;
										
								inputParams.setValue("mde_monto_bruto_ml", montoBruto_ml);
								inputParams.setValue("mde_monto_bruto_ma", montoBruto_ma);
								
								inputParams.setValue("mde_lote", 0);
								inputParams.setValue("mde_fecha_vencimiento_lote",null);
								
								String sqlConsultarLote = getSQL(getResource("consultarLote.sql"),inputParams);
								Recordset lote          = db.get(sqlConsultarLote);
								
								if(lote.getRecordCount()>0)
								{
									lote.first();
									
									inputParams.setValue("mde_lote", lote.getInt("id_lote"));
									inputParams.setValue("mde_fecha_vencimiento_lote", lote.getString("fecha_venc"));
									
								}
								
								String sqlTipoCosto = getSQL(getResource("sql-consultarTipoCosto.sql"),inputParams);
								Recordset rsTipoCosto = db.get(sqlTipoCosto);
								
								str = "> Consultando tipo de costo... <";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								if(rsTipoCosto.getRecordCount()>0)
								{
									str = "< Tipo de costo encontrado >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									
									rsTipoCosto.first();
									
									if(rsTipoCosto.getString("cit_tipo_costo")!=null)
										cit_tipo_costo = rsTipoCosto.getString("cit_tipo_costo");
									else
									{
										str = "*El tipo de costo esta vacio*";	
										bw.write(str);
										bw.flush();
										bw.newLine();
										rc=1;
										return rc;
									}
								}
								else
								{
									str = "*El item no esta registrado en caracteristicas de item*";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									rc=1;
									return rc;
								}
								
								String sqlControlItem = getSQL(getResource("sql-consultaControlItem.sql"),inputParams);
								Recordset rssqlCheckControl = db.get(sqlControlItem);
								
								str = "> Consultando control de Item... <";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								boolean existe=false;
								double saldo_inicial_existencia=0.0;
								double cantidad_entradas=0.0;
								double cantidad_salidas=0.0;
								double saldo_final_existencia=0.0;
								double valor_inicial=0.0;
								double valor_entrada=0.0;
								double valor_salida=0.0;
								double valor_final=0.0;
								double costo_promedio=0.0;
								double ultimo_costo=0.0;
								double costo_mas_alto=0.0;
								double costo_promedio_ponderado=0.0;
								double valor_inicial_ma=0.0;
								double valor_entrada_ma=0.0;
								double valor_salida_ma=0.0;
								double valor_final_ma=0.0;
								double costo_promedio_ma=0.0;
								double ultimo_costo_ma=0.0;
								double costo_mas_alto_ma=0.0;
								double costo_promedio_ponderado_ma=0.0;
								Date fecha_movimiento= new Date();
								if(rssqlCheckControl.getRecordCount() > 0) {
									str = "< Control de item encontrado >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
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
										fecha_movimiento = rssqlCheckControl.getDate("coi_fecha_movimiento");
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
									str = "< Control de item no encontrado >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
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
								
								inputParams.setValue("coi_precio", costo_ml);
								inputParams.setValue("coi_precio_ma", costo_ma);
								cantidad_salidas=cantidad_salidas+cantidadDespachada;
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
								valor_entrada=costo_ml*cantidad_entradas;
								//
								valor_entrada_ma=costo_ma*cantidad_entradas;
								//
								inputParams.setValue("coi_valor_entrada", valor_entrada);
								inputParams.setValue("coi_valor_entrada_ma", valor_entrada_ma);
								//
								if (cit_tipo_costo.equals("PR")) {
									valor_salida=costo_promedio*cantidad_salidas;
									//
									valor_salida_ma=costo_promedio_ma*cantidad_salidas;
									//
								} else if (cit_tipo_costo.equals("UC")) {
									valor_salida=ultimo_costo*cantidad_salidas;
									//
									valor_salida_ma=ultimo_costo_ma*cantidad_salidas;
									//
								} else if (cit_tipo_costo.equals("CM")) {
									valor_salida=costo_mas_alto*cantidad_salidas;
									//
									valor_salida_ma=costo_mas_alto_ma*cantidad_salidas;
									//
								} else if (cit_tipo_costo.equals("PP")) {
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
								ultimo_costo=costo_ml;
								//
								ultimo_costo_ma=costo_ma;
								//
								inputParams.setValue("coi_ultimo_costo", ultimo_costo);
								inputParams.setValue("coi_ultimo_costo_ma", ultimo_costo_ma);
								costo_mas_alto=Math.max(costo_mas_alto, costo_ml);
								//
								costo_mas_alto_ma=Math.max(costo_mas_alto_ma, costo_ma);
								//
								inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
								inputParams.setValue("coi_costo_mas_alto_ma", costo_mas_alto_ma);
								if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
									costo_promedio_ponderado=((cantidad_entradas*costo_ml)+(saldo_inicial_existencia*costo_promedio_ponderado))/(cantidad_entradas + saldo_inicial_existencia);
								else
									costo_promedio_ponderado = 0.0;
								//
								if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
									costo_promedio_ponderado_ma=((cantidad_entradas*costo_ma)+(saldo_inicial_existencia*costo_promedio_ponderado_ma))/(cantidad_entradas + saldo_inicial_existencia);
								else
									costo_promedio_ponderado_ma = 0.0;
								//
								inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
								inputParams.setValue("coi_costo_promedio_ponderado_ma", costo_promedio_ponderado_ma);
								inputParams.setValue("coi_cantidad_reservada", 0.0);
								inputParams.setValue("coi_cantidad_espera_recepcion", 0.0);
								inputParams.setValue("coi_fecha_movimiento", fecha_movimiento);
								
								str = "Costo promedio: " + inputParams.getString("coi_costo_promedio");
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								if(!existe){
									str = "Registrando control de item...";
									bw.write(str);
									bw.flush();
									bw.newLine();
									String insertControlItem = getSQL(getResource("insert_ControlItem.sql"), inputParams);
									Recordset insControlItem = db.get(insertControlItem);
									if(insControlItem.getRecordCount() > 0) {
										str = "**Control de item registrado exitosamente**";
										bw.write(str);
										bw.flush();
										bw.newLine();
									}else{
										str = "**No registro control de item**";
										bw.write(str);
										bw.flush();
										bw.newLine();
										rc=1;
										return rc;
									}
								}else{
									if(fecha_despacho.compareTo(fecha_movimiento)==0){
										str = "**Modificando control de item**";
										bw.write(str);
										bw.flush();
										bw.newLine();
										String updateControlItem = getSQL(getResource("update_ControlItem.sql"), inputParams);
										db.exec(updateControlItem);
										str = "**Control de item modificado exitosamente**";
										bw.write(str);
										bw.flush();
										bw.newLine();
									}else{
										inputParams.setValue("coi_fecha_movimiento",fecha_despacho);
										str = "Insertando control de item...";
										bw.write(str);
										bw.flush();
										bw.newLine();
										String insertControlItem2 = getSQL(getResource("insert_ControlItem.sql"), inputParams);
										Recordset insControlItem2 = db.get(insertControlItem2);
										if(insControlItem2.getRecordCount() > 0) {
											str = "**Control de item registrado exitosamente**";
											bw.write(str);
											bw.flush();
											bw.newLine();
										}else{
											str = "**No inserto control de item**";
											bw.write(str);
											bw.flush();
											bw.newLine();
											rc=1;
											return rc;
										}
									}
								}
								
								str = "< Registrando detalle... >";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								String sqlMovimiento_detalle   = getSQL(getResource("insertar-movimiento_detalle.sql"), inputParams);
							    db.get(sqlMovimiento_detalle);
							    
							    str = "-Detalle registrado exitosamente";	
								bw.write(str);
								bw.flush();
								bw.newLine();
							
							
							
							
								///////Proceso para registrar movimiento detalle
								///Almacen Solicitante
							
								String sqlControlItemAlmSol = getSQL(getResource("sql-consultaControlItemAlmSol.sql"),inputParams);
								Recordset rssqlCheckControlAlmSol = db.get(sqlControlItemAlmSol);
								
								str = "> Consultando control de Item... <";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								boolean existeAlmSol=false;
								double saldo_inicial_existenciaAlmSol=0.0;
								double cantidad_entradasAlmSol=0.0;
								double cantidad_salidasAlmSol=0.0;
								double saldo_final_existenciaAlmSol=0.0;
								double valor_inicialAlmSol=0.0;
								double valor_entradaAlmSol=0.0;
								double valor_salidaAlmSol=0.0;
								double valor_finalAlmSol=0.0;
								double costo_promedioAlmSol=0.0;
								double ultimo_costoAlmSol=0.0;
								double costo_mas_altoAlmSol=0.0;
								double costo_promedio_ponderadoAlmSol=0.0;
								double valor_inicial_maAlmSol=0.0;
								double valor_entrada_maAlmSol=0.0;
								double valor_salida_maAlmSol=0.0;
								double valor_final_maAlmSol=0.0;
								double costo_promedio_maAlmSol=0.0;
								double ultimo_costo_maAlmSol=0.0;
								double costo_mas_alto_maAlmSol=0.0;
								double costo_promedio_ponderado_maAlmSol=0.0;
								Date fecha_movimientoAlmSol=null;
								if(rssqlCheckControlAlmSol.getRecordCount() > 0) {
									str = "< Control de item encontrado >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									while (rssqlCheckControlAlmSol.next())
									{
										inputParams.setValue("smn_control_item_id", rssqlCheckControlAlmSol.getInteger("smn_control_item_id"));
										existeAlmSol=true;
										saldo_inicial_existenciaAlmSol=rssqlCheckControlAlmSol.getDouble("coi_saldo_inicial_existencia"); 
										cantidad_entradasAlmSol=rssqlCheckControlAlmSol.getDouble("coi_cantidad_entradas");  
										cantidad_salidasAlmSol=rssqlCheckControlAlmSol.getDouble("coi_cantidad_salidas"); 
										saldo_final_existenciaAlmSol=rssqlCheckControlAlmSol.getDouble("coi_saldo_final_existencia"); 
										valor_inicialAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_inicial"); 
										valor_entradaAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_entrada"); 
										valor_salidaAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_salida"); 
										valor_finalAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_final"); 
										costo_promedioAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_promedio"); 
										ultimo_costoAlmSol=rssqlCheckControlAlmSol.getDouble("coi_ultimo_costo"); 
										costo_mas_altoAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_mas_alto"); 
										costo_promedio_ponderadoAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_promedio_ponderado");
										fecha_movimientoAlmSol = rssqlCheckControlAlmSol.getDate("coi_fecha_movimiento");
										//
										if(rssqlCheckControlAlmSol.getString("coi_valor_inicial_ma") != null)
											valor_inicial_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_inicial_ma");
										else
											valor_inicial_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_valor_entradas_ma") != null)
											valor_entrada_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_entradas_ma"); 
										else
											valor_entrada_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_valor_salidas_ma") != null)
											valor_salida_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_salidas_ma"); 
										else
											valor_salida_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_valor_final_ma") != null)
											valor_final_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_valor_final_ma");
										else
											valor_final_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_costo_promedio_ma") != null)
											costo_promedio_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_promedio_ma"); 
										else
											costo_promedio_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_ultimo_costo_ma") != null)
											ultimo_costo_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_ultimo_costo_ma"); 
										else
											ultimo_costo_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_costo_mas_alto_ma") != null)
											costo_mas_alto_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_mas_alto_ma"); 
										else
											costo_mas_alto_maAlmSol=0.0;
										
										if(rssqlCheckControlAlmSol.getString("coi_costo_promedio_ponderado_ma") != null)
											costo_promedio_ponderado_maAlmSol=rssqlCheckControlAlmSol.getDouble("coi_costo_promedio_ponderado_ma");
										else
											costo_promedio_ponderado_maAlmSol=0.0;
									}
								}else{
									str = "< Control de item no encontrado >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									existeAlmSol=false;
									saldo_inicial_existenciaAlmSol=0.00; 
									cantidad_entradasAlmSol=0.00; 
									cantidad_salidasAlmSol=0.00;
									saldo_final_existenciaAlmSol=0.00;
									valor_inicialAlmSol=0.00;
									valor_entradaAlmSol=0.00;
									valor_salidaAlmSol=0.00;
									valor_finalAlmSol=0.00;
									costo_promedioAlmSol=0.00;
									ultimo_costoAlmSol=0.00;
									costo_mas_altoAlmSol=0.00;
									costo_promedio_ponderadoAlmSol=0.00;
									//
									valor_inicial_maAlmSol=0.00;
									valor_entrada_maAlmSol=0.00;
									valor_salida_maAlmSol=0.00;
									valor_final_maAlmSol=0.00;
									costo_promedio_maAlmSol=0.00;
									ultimo_costo_maAlmSol=0.00;
									costo_mas_alto_maAlmSol=0.00;
									costo_promedio_ponderado_maAlmSol=0.00;
								}
								
								inputParams.setValue("coi_precio", costo_ml);
								inputParams.setValue("coi_precio_ma", costo_ma);
								//cantidad_salidas=cantidad_salidas+cantidadDespachada;
								cantidad_entradasAlmSol=cantidad_entradasAlmSol+cantidadDespachada;
								inputParams.setValue("coi_cantidad_entradas", cantidad_entradasAlmSol);
								saldo_inicial_existenciaAlmSol=saldo_final_existenciaAlmSol;
								saldo_final_existenciaAlmSol=saldo_final_existenciaAlmSol+cantidadDespachada;
								inputParams.setValue("coi_saldo_inicial_existencia", saldo_inicial_existenciaAlmSol);
								inputParams.setValue("coi_saldo_final_existencia", saldo_final_existenciaAlmSol);
								inputParams.setValue("coi_cantidad_salidas", cantidad_salidasAlmSol);
								valor_inicialAlmSol=valor_finalAlmSol;
								//
								valor_inicial_maAlmSol=valor_final_maAlmSol;
								//
								inputParams.setValue("coi_valor_inicial", valor_inicialAlmSol);
								inputParams.setValue("coi_valor_inicial_ma", valor_inicial_maAlmSol);
								valor_salidaAlmSol=costo_ml*cantidad_salidasAlmSol;
								//
								valor_salida_maAlmSol=costo_ma*cantidad_salidasAlmSol;
								//
								inputParams.setValue("coi_valor_salida", valor_salidaAlmSol);
								inputParams.setValue("coi_valor_salida_ma", valor_salida_maAlmSol);
								//
								if (cit_tipo_costo.equals("PR")) {
									valor_entradaAlmSol=costo_promedioAlmSol*cantidad_entradasAlmSol;
									//
									valor_entrada_maAlmSol=costo_promedio_maAlmSol*cantidad_entradasAlmSol;
									//
								} else if (cit_tipo_costo.equals("UC")) {
									valor_entradaAlmSol=ultimo_costoAlmSol*cantidad_entradasAlmSol;
									//
									valor_entrada_maAlmSol=ultimo_costo_maAlmSol*cantidad_entradasAlmSol;
									//
								} else if (cit_tipo_costo.equals("CM")) {
									valor_entradaAlmSol=costo_mas_altoAlmSol*cantidad_entradasAlmSol;
									//
									valor_entrada_maAlmSol=costo_mas_alto_maAlmSol*cantidad_entradasAlmSol;
									//
								} else if (cit_tipo_costo.equals("PP")) {
									valor_entradaAlmSol=costo_promedio_ponderadoAlmSol*cantidad_entradasAlmSol;
									valor_entrada_maAlmSol=costo_promedio_ponderado_maAlmSol*cantidad_entradasAlmSol;
								}
								//
								inputParams.setValue("coi_valor_entrada", valor_entradaAlmSol);
								inputParams.setValue("coi_valor_entrada_ma", valor_entrada_maAlmSol);
								valor_finalAlmSol=valor_inicialAlmSol+valor_entradaAlmSol;
								//
								valor_final_maAlmSol=valor_inicial_maAlmSol+valor_entrada_maAlmSol;
								//
								inputParams.setValue("coi_valor_final", valor_finalAlmSol);
								inputParams.setValue("coi_valor_final_ma", valor_final_maAlmSol);
								costo_promedioAlmSol=valor_finalAlmSol / saldo_final_existenciaAlmSol;
								//
								costo_promedio_maAlmSol=valor_final_maAlmSol / saldo_final_existenciaAlmSol;
								//
								inputParams.setValue("coi_costo_promedio", costo_promedioAlmSol);
								inputParams.setValue("coi_costo_promedio_ma", costo_promedio_maAlmSol);
								ultimo_costoAlmSol=costo_ml;
								//
								ultimo_costo_maAlmSol=costo_ma;
								//
								inputParams.setValue("coi_ultimo_costo", ultimo_costoAlmSol);
								inputParams.setValue("coi_ultimo_costo_ma", ultimo_costo_maAlmSol);
								costo_mas_alto=Math.max(costo_mas_altoAlmSol, costo_ml);
								//
								costo_mas_alto_ma=Math.max(costo_mas_alto_maAlmSol, costo_ma);
								//
								inputParams.setValue("coi_costo_mas_alto", costo_mas_altoAlmSol);
								inputParams.setValue("coi_costo_mas_alto_ma", costo_mas_alto_maAlmSol);
								if((cantidad_entradasAlmSol + saldo_inicial_existenciaAlmSol) > 0.0)
									costo_promedio_ponderadoAlmSol=((cantidad_entradasAlmSol*costo_ml)+(saldo_inicial_existenciaAlmSol*costo_promedio_ponderadoAlmSol))/(cantidad_entradasAlmSol + saldo_inicial_existenciaAlmSol);
								else
									costo_promedio_ponderadoAlmSol = 0.0;
								//
								if((cantidad_entradasAlmSol + saldo_inicial_existenciaAlmSol) > 0.0)
									costo_promedio_ponderado_maAlmSol=((cantidad_entradasAlmSol*costo_ma)+(saldo_inicial_existenciaAlmSol*costo_promedio_ponderado_maAlmSol))/(cantidad_entradasAlmSol + saldo_inicial_existenciaAlmSol);
								else
									costo_promedio_ponderado_maAlmSol = 0.0;
								//
								inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderadoAlmSol);
								inputParams.setValue("coi_costo_promedio_ponderado_ma", costo_promedio_ponderado_maAlmSol);
								inputParams.setValue("coi_cantidad_reservada", 0.0);
								inputParams.setValue("coi_cantidad_espera_recepcion", 0.0);
								
								str = "Costo promedio: " + inputParams.getString("coi_costo_promedio");
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								if(!existeAlmSol){
									str = "Registrando control de item...";
									bw.write(str);
									bw.flush();
									bw.newLine();
									String insertControlItemAlmSol = getSQL(getResource("insert_ControlItemAlmSol.sql"), inputParams);
									Recordset insControlItemAlmSol = db.get(insertControlItemAlmSol);
									if(insControlItemAlmSol.getRecordCount() > 0) {
										str = "**Control de item registrado exitosamente**";
										bw.write(str);
										bw.flush();
										bw.newLine();
									}else{
										str = "**No registro control de item**";
										bw.write(str);
										bw.flush();
										bw.newLine();
										rc=1;
										return rc;
									}
								}else{
									if(fecha_despacho.compareTo(fecha_movimientoAlmSol)==0){
										str = "**Modificando control de item**";
										bw.write(str);
										bw.flush();
										bw.newLine();
										String updateControlItemAlmSol = getSQL(getResource("update_ControlItem.sql"), inputParams);
										db.exec(updateControlItemAlmSol);
										str = "**Control de item modificado exitosamente**";
										bw.write(str);
										bw.flush();
										bw.newLine();
									}else{
										inputParams.setValue("coi_fecha_movimiento",fecha_despacho);
										str = "Insertando control de item...";
										bw.write(str);
										bw.flush();
										bw.newLine();
										String insertControlItem2AlmSol = getSQL(getResource("insert_ControlItemAlmSol.sql"), inputParams);
										Recordset insControlItem2AlmSol = db.get(insertControlItem2AlmSol);
										if(insControlItem2AlmSol.getRecordCount() > 0) {
											str = "**Control de item registrado exitosamente**";
											bw.write(str);
											bw.flush();
											bw.newLine();
										}else{
											str = "**No inserto control de item**";
											bw.write(str);
											bw.flush();
											bw.newLine();
											rc=1;
											return rc;
										}
									}
								}
								
								str = "< Registrando detalle Movimiento Almacen Solicitante... >";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								
								String sqlMovimiento_detalleAlmSol   = getSQL(getResource("insertar-movimiento_detalle.sql"), inputParams);
							    db.get(sqlMovimiento_detalleAlmSol);
							    
							    str = "-Detalle Almancen Solicitante registrado exitosamente";	
								bw.write(str);
								bw.flush();
								bw.newLine();
							}
						
							
							String sqlCantidades  = getSQL(getResource("consultar-cantidades.sql"),inputParams);
							Recordset rsCantidades = db.get(sqlCantidades);
							
							str = "-Consultando cantidades...";	
							bw.write(str);
							bw.flush();
							bw.newLine();
							
							if(rsCantidades.getRecordCount()>0)
							{
								rsCantidades.first();
								double solicitada = rsCantidades.getDouble("cantidad_solicitada");
								double despachada = rsCantidades.getDouble("cantidad_despachada");
								
								if(solicitada == despachada)
								{
									str = "< Actualizando despacho EN >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									
									String sqlActualizar_estatus   = getSQL(getResource("actualizar-despacho_EN.sql"),inputParams);
									Recordset result = db.get(sqlActualizar_estatus);
									
									if(result.getRecordCount()>0)
									{
										str = "-Despacho actualizado correctamente";	
										bw.write(str);
										bw.flush();
										bw.newLine();
										rc = 0;
									}
										
								}
								else
								{
									str = "< Actualizando despacho PD >";	
									bw.write(str);
									bw.flush();
									bw.newLine();
									
									String sqlActualizar_estatus = getSQL(getResource("actualizar-despacho_PD.sql"),inputParams);
									Recordset result = db.get(sqlActualizar_estatus);
									
									if(result.getRecordCount()>0)
									{
										str = "-Despacho actualizado correctamente";	
										bw.write(str);
										bw.flush();
										bw.newLine();
										rc = 0;
									}
								}
							}
							else
							{
								str = "*No se encontraron las cantidades del despacho*";	
								bw.write(str);
								bw.flush();
								bw.newLine();
								rc=1; //registro no puede ser procesado ocurrio un error en el query
							}						
						}
						else
						{
							str = "*No se encontraron los detalles del despacho*";	
							bw.write(str);
							bw.flush();
							bw.newLine();
							rc=1; //registro no puede ser procesado ocurrio un error en el query
						}
					}
					else
					{
						str = "*El id del movimiento esta vacio*";	
						bw.write(str);
						bw.flush();
						bw.newLine();
						rc=1; //registro no puede ser procesado movimientoCabeceraId *vacio*
					}
				}
				else
				{
					str = "*El movimiento cabecera no se registro*";	
					bw.write(str);
					bw.flush();
					bw.newLine();
					rc=1; //registro no puede ser procesado ocurrio un error en el query
				}
			}
			else
			{
				str = "*No se encontro el despacho o no tiene estatus GENERADO*";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				rc=1; //registro no puede ser procesado estatus diferente a GE
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
			
			str = "-- FIN DEL PROCESO --";	
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
}
