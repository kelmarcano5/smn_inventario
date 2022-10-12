package proceso;

import dinamica.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;

public class RegistrarImpuestosDescuentos extends GenericTransaction
{
	
	public int service(Recordset inputParams) throws Throwable
	{
		int rc = 1;	//variable a retornar.
		//String mensaje = ""; //mensaje a retornar
		
		String jndiName = (String)getContext().getAttribute("dinamica.security.datasource");
		
		if (jndiName==null)
			throw new Throwable("Context attribute [dinamica.security.datasource] is null, check your security filter configuration.");
		
		//establecer la conexion con la base de datos.
		
			DataSource ds = Jndi.getDataSource(jndiName); 
			Connection conn = ds.getConnection();
			this.setConnection(conn);
		
		//**
		
		String fechaActual= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sistemaOperativo = System.getProperty("os.name");
		String file;
		  
		if(sistemaOperativo.equals("Windows 7") || sistemaOperativo.equals("Windows 8") || sistemaOperativo.equals("Windows 10")) 
			file =  "C:/log/logRegistrarImpuestosDescuentos_"+fechaActual+".txt";
		else
			file = "./logRegistrarImpuestosDescuentos_"+fechaActual+".txt";
		
		File newLogFile = new File(file);
		FileWriter fw;
		String str="";
			
		if(!newLogFile.exists())
			fw = new FileWriter(newLogFile);
		else
			fw = new FileWriter(newLogFile,true);
			
		BufferedWriter bw=new BufferedWriter(fw);
			
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
			
		conn.setAutoCommit(false);
	
		try
		{
			str = "----------"+timestamp+"----------";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			bw.newLine();
			
			Db db = getDb(); //objeto de conexion.
			
			String impuestos[] = StringUtil.split(inputParams.getString("impuestos_id"), ";");
			String descuentos[] = StringUtil.split(inputParams.getString("descuentos_id"), ";");
			String sql; 
			double mde_monto_base_ml=0;
			double mde_monto_base_ma=0;
			double dyr_porcentaje_base=0;
			double dyr_porcentaje_descuento=0;
			double total_descuento_ml=0;
			double total_descuento_ma=0;
			double sum_descuento_ml=0;
			double sum_descuento_ma=0;
			double total_impuesto_ml=0;
			double total_impuesto_ma=0;
			double sum_impuesto_ml=0;
			double sum_impuesto_ma=0;
			double monto_neto_ml=0;
			double monto_neto_ma=0;
			double imp_monto_sustraendo=0;
			double imp_porcentaje_calculo=0;
			double imp_porcentaje_base=0;
			String imp_tipo_codigo;
			String ent_calcula_redondeo;
			
			str = "Consultando movimiento detalle";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			sql = getSQL(getResource("select-smn_movimiento_detalle.sql"),inputParams);
			Recordset rsDetalle = db.get(sql);
			
			rsDetalle.first();
				
			inputParams.setValue("smn_movimiento_cabecera_id", rsDetalle.getInt("smn_movimiento_cabecera_id"));
			
			if(rsDetalle.getString("mde_monto_bruto_ml")!=null)
			{
				inputParams.setValue("mde_monto_bruto_ml",rsDetalle.getDouble("mde_monto_bruto_ml"));
				mde_monto_base_ml = rsDetalle.getDouble("mde_monto_bruto_ml");
			}
			else
			{
				inputParams.setValue("mde_monto_bruto_ml",0.0);
				mde_monto_base_ml = 0;
			}
			
			if(rsDetalle.getString("mde_monto_bruto_ma")!=null)
			{
				inputParams.setValue("mde_monto_bruto_ma",rsDetalle.getDouble("mde_monto_bruto_ma"));
				mde_monto_base_ma = rsDetalle.getDouble("mde_monto_bruto_ma");
			}
			else
			{
				inputParams.setValue("mde_monto_bruto_ma",0.0);
				mde_monto_base_ma = 0;
			}
			
			if(rsDetalle.getString("mde_monto_neto_ml")!=null)
			{
				inputParams.setValue("mde_monto_neto_ml",rsDetalle.getDouble("mde_monto_neto_ml"));
				monto_neto_ml = rsDetalle.getDouble("mde_monto_neto_ml");
			}
			else
			{
				inputParams.setValue("mde_monto_neto_ml",0.0);
				monto_neto_ml = 0;
			}
			
			if(rsDetalle.getString("mde_monto_neto_ma")!=null)
			{
				inputParams.setValue("mde_monto_neto_ma",rsDetalle.getDouble("mde_monto_neto_ma"));
				monto_neto_ma = rsDetalle.getDouble("mde_monto_neto_ma");
			}
			else
			{
				inputParams.setValue("mde_monto_neto_ma",0.0);
				monto_neto_ma = 0;
			}
			
			if(rsDetalle.getString("smn_moneda_rf")!=null)
			{
				inputParams.setValue("smn_moneda_rf_detalle",rsDetalle.getInt("smn_moneda_rf"));
			}
			else
			{
				inputParams.setValue("smn_moneda_rf_detalle",0);
			}
			
			if(rsDetalle.getString("smn_tasa_rf")!=null)
			{
				inputParams.setValue("smn_tasa_rf_detalle",rsDetalle.getInt("smn_tasa_rf"));
			}
			else
			{
				inputParams.setValue("smn_tasa_rf_detalle",0);
			}
			
			sql = getSQL(getResource("select-smn_entidades.sql"),inputParams);
			Recordset rsEntidad = db.get(sql);
			
			rsEntidad.first();
			
			if(rsEntidad.getString("ent_calcula_redondeo")!=null)
				ent_calcula_redondeo=rsEntidad.getString("ent_calcula_redondeo");
			else
				ent_calcula_redondeo="NO";
			
			sql = getSQL(getResource("select-smn_caracteristica_almacen.sql"),inputParams);
			Recordset rsCaracteristicaAlmacen = db.get(sql);
			
			rsCaracteristicaAlmacen.first();
			
			if(rsCaracteristicaAlmacen.getString("cal_tipo_calculo_costo")!=null)
				inputParams.setValue("cal_tipo_calculo_costo", rsCaracteristicaAlmacen.getString("cal_tipo_calculo_costo"));
			else
			{
				str = "El tipo de calculo del costo esta vacio en caracteristicas de almacen";	
				bw.write(str);
				bw.flush();
				bw.newLine();
				return 1;
			}
			
			if(inputParams.getString("subtotal_monto_neto_ml")==null)
				inputParams.setValue("subtotal_monto_neto_ml", mde_monto_base_ml);
				
			str = "<Procesando descuentos>";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			for(int p=0; p<descuentos.length; p++)
			{
				inputParams.setValue("smn_descuentos_retenciones_id", Integer.parseInt(descuentos[p]));
				
				str = "Consultando descuento con ID "+Integer.parseInt(descuentos[p]);	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				sql = getSQL(getResource("select-smn_descuentos_retenciones.sql"),inputParams);
				Recordset rsDescuento = db.get(sql);
				
				rsDescuento.first();
				
				if(rsDescuento.getString("dyr_porcentaje_base")!=null)
				{
					inputParams.setValue("dyr_porcentaje_base", rsDescuento.getDouble("dyr_porcentaje_base"));
					dyr_porcentaje_base = rsDescuento.getDouble("dyr_porcentaje_base");
				}
				else
				{
					inputParams.setValue("dyr_porcentaje_base",0);
					dyr_porcentaje_base = 0;
				}
				
				if(rsDescuento.getString("dyr_apli_cant_precio").equals("TL"))
				{
					inputParams.setValue("dyr_porcentaje_descuento", inputParams.getDouble("porc_descuento_libre"));
					dyr_porcentaje_descuento = inputParams.getDouble("porc_descuento_libre");
				}
				else
				{
					if(rsDescuento.getString("dyr_porcentaje_descuento")!=null)
					{
						inputParams.setValue("dyr_porcentaje_descuento", rsDescuento.getDouble("dyr_porcentaje_descuento"));
						dyr_porcentaje_descuento = rsDescuento.getDouble("dyr_porcentaje_descuento");
					}
					else
					{
						inputParams.setValue("dyr_porcentaje_descuento",0);
						dyr_porcentaje_descuento = 0;
					}
				}	
				
				total_descuento_ml = (mde_monto_base_ml*dyr_porcentaje_base)/100;
				total_descuento_ml = (total_descuento_ml*dyr_porcentaje_descuento)/100;
				
				total_descuento_ma = (mde_monto_base_ma*dyr_porcentaje_base)/100;
				total_descuento_ma = (total_descuento_ma*dyr_porcentaje_descuento)/100;
				
				if(ent_calcula_redondeo.equals("SI"))
				{
					sum_descuento_ml += Math.round(total_descuento_ml);
					sum_descuento_ma += Math.round(total_descuento_ma);
					
					inputParams.setValue("total_descuento_ml_redondeo",Math.round(total_descuento_ml));
					inputParams.setValue("total_descuento_ma_redondeo",Math.round(total_descuento_ma));
				}
				else
				{
					sum_descuento_ml += total_descuento_ml;
					sum_descuento_ma += total_descuento_ma;
					
					inputParams.setValue("total_descuento_ml_redondeo",total_descuento_ml);
					inputParams.setValue("total_descuento_ma_redondeo",total_descuento_ma);
				}
				
				sql = getSQL(getResource("select-smn_movimiento_detalle_desc_ret.sql"),inputParams);
				Recordset rsDetalle_desc_ret = db.get(sql);
				
				if(rsDetalle_desc_ret.getRecordCount() > 0)
				{
					rsDetalle_desc_ret.first();
					inputParams.setValue("smn_descuento_retencion_id", rsDetalle_desc_ret.getInt("smn_descuento_retencion_id"));
					
					str = "Actualizando detalle descuento con ID "+rsDetalle_desc_ret.getInt("smn_descuento_retencion_id");	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					sql = getSQL(getResource("update-smn_movimiento_detalle_desc_ret.sql"),inputParams);
					db.exec(sql);
					
					str = "*Detalle descuento actualizado correctamente*";	
					bw.write(str);
					bw.flush();
					bw.newLine();
				}
				else
				{
					str = "Registrando detalle descuento con ID "+Integer.parseInt(descuentos[p]);	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					sql = getSQL(getResource("insert-smn_movimiento_detalle_desc_ret.sql"),inputParams);
					db.exec(sql);
					
					str = "*Detalle descuento registrado correctamente*";	
					bw.write(str);
					bw.flush();
					bw.newLine();
				}
			}
			
			monto_neto_ml = mde_monto_base_ml+sum_impuesto_ml-sum_descuento_ml;
			monto_neto_ma = mde_monto_base_ma+sum_impuesto_ma-sum_descuento_ma;
			
			inputParams.setValue("sum_impuesto_ml",sum_impuesto_ml);
			inputParams.setValue("sum_impuesto_ma",sum_impuesto_ma);
			inputParams.setValue("sum_descuento_ml",sum_descuento_ml);
			inputParams.setValue("sum_descuento_ma",sum_descuento_ma);
			
			if(ent_calcula_redondeo.equals("SI"))
			{
				inputParams.setValue("monto_neto_ml_redondeo",Math.round(monto_neto_ml));
				inputParams.setValue("monto_neto_ma_redondeo",Math.round(monto_neto_ma));
			}
			else
			{
				inputParams.setValue("monto_neto_ml_redondeo",monto_neto_ml);
				inputParams.setValue("monto_neto_ma_redondeo",monto_neto_ma);
			}
			
			str = "Acutalizando monto neto y monto descuento en el detalle";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			sql = getSQL(getResource("update-smn_movimiento_detalle.sql"),inputParams);
			db.exec(sql);
			
			str = "*Montos actualizados correctamente*";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			str = "<Procesando impuestos>";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			for(int p=0; p<impuestos.length; p++)
			{
				inputParams.setValue("smn_codigos_impuestos_id", Integer.parseInt(impuestos[p]));
				
				str = "Se registro el impuesto ID "+Integer.parseInt(impuestos[p]);	
				bw.write(str);
				bw.flush();
				bw.newLine();
				
				sql = getSQL(getResource("select-smn_codigos_impuestos.sql"),inputParams);
				Recordset rsCodigoImpuesto = db.get(sql);
				
				rsCodigoImpuesto.first();
				
				if(rsCodigoImpuesto.getString("imp_monto_sustraendo")!=null)
				{
					inputParams.setValue("imp_monto_sustraendo", rsCodigoImpuesto.getDouble("imp_monto_sustraendo"));
					imp_monto_sustraendo = rsCodigoImpuesto.getDouble("imp_monto_sustraendo");
				}
				else
				{
					inputParams.setValue("imp_monto_sustraendo",0);
					imp_monto_sustraendo = 0;
				}
				
				if(rsCodigoImpuesto.getString("imp_porcentaje_calculo")!=null)
				{
					inputParams.setValue("imp_porcentaje_calculo", rsCodigoImpuesto.getDouble("imp_porcentaje_calculo"));
					imp_porcentaje_calculo = rsCodigoImpuesto.getDouble("imp_porcentaje_calculo");
				}
				else
				{
					inputParams.setValue("imp_porcentaje_calculo",0);
					imp_porcentaje_calculo = 0;
				}
				
				if(rsCodigoImpuesto.getString("imp_porcentaje_base")!=null)
				{
					inputParams.setValue("imp_porcentaje_base", rsCodigoImpuesto.getDouble("imp_porcentaje_base"));
					imp_porcentaje_base = rsCodigoImpuesto.getDouble("imp_porcentaje_base");
				}
				else
				{
					inputParams.setValue("imp_porcentaje_base",0);
					imp_porcentaje_base = 0;
				}
				
				inputParams.setValue("imp_tipo_codigo", rsCodigoImpuesto.getString("imp_tipo_codigo"));
				imp_tipo_codigo = rsCodigoImpuesto.getString("imp_tipo_codigo");
				
				if(inputParams.getString("cal_tipo_calculo_costo").equals("DI"))
				{
					total_impuesto_ml = (monto_neto_ml*imp_porcentaje_base)/100;
					total_impuesto_ml = (total_impuesto_ml*imp_porcentaje_calculo)/100;
					total_impuesto_ml = total_impuesto_ml-imp_monto_sustraendo;
					
					total_impuesto_ma = (monto_neto_ma*imp_porcentaje_base)/100;
					total_impuesto_ma = (total_impuesto_ma*imp_porcentaje_calculo)/100;
					total_impuesto_ma = total_impuesto_ma-imp_monto_sustraendo;
				}
				else
				{
					total_impuesto_ml = (mde_monto_base_ml*imp_porcentaje_base)/100;
					total_impuesto_ml = (total_impuesto_ml*imp_porcentaje_calculo)/100;
					total_impuesto_ml = total_impuesto_ml-imp_monto_sustraendo;
					
					total_impuesto_ma = (mde_monto_base_ma*imp_porcentaje_base)/100;
					total_impuesto_ma = (total_impuesto_ma*imp_porcentaje_calculo)/100;
					total_impuesto_ma = total_impuesto_ma-imp_monto_sustraendo;
					
					inputParams.setValue("subtotal_monto_neto_ml",mde_monto_base_ml);
				}
				
				if(imp_tipo_codigo.equals("RE"))
				{
					total_impuesto_ml=total_impuesto_ml*-1;
					total_impuesto_ma=total_impuesto_ma*-1;
				}
						
				if(ent_calcula_redondeo.equals("SI"))
				{
					sum_impuesto_ml += Math.round(total_impuesto_ml);
					sum_impuesto_ma += Math.round(total_impuesto_ma);
					
					inputParams.setValue("total_impuesto_ml_redondeo",Math.round(total_impuesto_ml));
					inputParams.setValue("total_impuesto_ma_redondeo",Math.round(total_impuesto_ma));
				}
				else
				{
					sum_impuesto_ml += total_impuesto_ml;
					sum_impuesto_ma += total_impuesto_ma;
					
					inputParams.setValue("total_impuesto_ml_redondeo",total_impuesto_ml);
					inputParams.setValue("total_impuesto_ma_redondeo",total_impuesto_ma);
				}
				
				sql = getSQL(getResource("select-smn_movimiento_detalle_impuesto.sql"),inputParams);
				Recordset rsDetalle_impuesto = db.get(sql);
				
				if(rsDetalle_impuesto.getRecordCount() > 0)
				{
					rsDetalle_impuesto.first();
					inputParams.setValue("smn_mov_det_impuesto_id", rsDetalle_impuesto.getInt("smn_mov_det_impuesto_id"));
					
					str = "Actualizando detalle impuesto con ID "+rsDetalle_impuesto.getInt("smn_mov_det_impuesto_id");	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					sql = getSQL(getResource("update-smn_movimiento_detalle_impuesto.sql"),inputParams);
					db.exec(sql);
					
					str = "*Detalle impuesto actualizado correctamente*";	
					bw.write(str);
					bw.flush();
					bw.newLine();
				}
				else
				{
					str = "Registrando detalle impuesto con ID "+Integer.parseInt(impuestos[p]);	
					bw.write(str);
					bw.flush();
					bw.newLine();
					
					sql = getSQL(getResource("insert-smn_movimiento_detalle_impuesto.sql"),inputParams);
					db.exec(sql);
					
					str = "*Detalle impuesto registrado correctamente*";	
					bw.write(str);
					bw.flush();
					bw.newLine();
				}
			}
			
			monto_neto_ml = mde_monto_base_ml+sum_impuesto_ml-sum_descuento_ml;
			monto_neto_ma = mde_monto_base_ma+sum_impuesto_ma-sum_descuento_ma;
			
			inputParams.setValue("sum_impuesto_ml",sum_impuesto_ml);
			inputParams.setValue("sum_impuesto_ma",sum_impuesto_ma);
			inputParams.setValue("sum_descuento_ml",sum_descuento_ml);
			inputParams.setValue("sum_descuento_ma",sum_descuento_ma);
			
			if(ent_calcula_redondeo.equals("SI"))
			{
				inputParams.setValue("monto_neto_ml_redondeo",Math.round(monto_neto_ml));
				inputParams.setValue("monto_neto_ma_redondeo",Math.round(monto_neto_ma));
			}
			else
			{
				inputParams.setValue("monto_neto_ml_redondeo",monto_neto_ml);
				inputParams.setValue("monto_neto_ma_redondeo",monto_neto_ma);
			}
			
			str = "Acutalizando monto neto y monto impuesto en el detalle";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			sql = getSQL(getResource("update-smn_movimiento_detalle.sql"),inputParams);
			db.exec(sql);
			
			str = "*Montos actualizados correctamente*";	
			bw.write(str);
			bw.flush();
			bw.newLine();
			
			rc=0; //Proceso ejecutado exitosamente.
		}
		catch(Throwable e)
		{
			bw.close();
			conn.rollback();
			throw e;
		}
		
		finally
		{		
			bw.close();
			if(rc==0)
				conn.commit();
			else
				conn.rollback();
			
			if(conn!=null)
				conn.close();
		}
		
		return rc;
	}
}
