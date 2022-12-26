package proceso;

import dinamica.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class Despachar extends GenericTransaction{

    public int service(Recordset inputParams) throws Throwable {
        int rc = 0;

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
            file =  "C:/log/logDespacho_"+fechaActual+".txt";
        else
            file = "./logDespacho_"+fechaActual+".txt";

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

            str = "INICIO DEL PROCESO";
            bw.write(str);
            bw.flush();
            bw.newLine();

            Db db = getDb();

            String sqlDespacho = getSQL(getResource("consultar_despacho.sql"),inputParams);
            Recordset rsDespacho = db.get(sqlDespacho);

            if(rsDespacho.getRecordCount() <= 0){
                str = "NO SE ENCONTRO EL DESPACHO O NO TIENE  ESTATUS GENERADO";
                bw.write(str);
                bw.flush();
                bw.newLine();
                conn.rollback();
                getRequest().setAttribute("mensaje", str);
                bw.close();
                fw.close();
                conn.close();
                return 1;
            }

            rsDespacho.first();

            inputParams.setValue("smn_entidad_rf", 0);
            inputParams.setValue("smn_sucursal_rf", 0);
            inputParams.setValue("smn_almacen_rf", 0);
            inputParams.setValue("smn_modulo_rf", 0);
            inputParams.setValue("smn_documento_origen_id", 0);
            inputParams.setValue("des_numero_documento_origen", 0);
            inputParams.setValue("smn_documento_id", 0);
            inputParams.setValue("des_numero_documento", 0);
            inputParams.setValue("des_monto_pedido_ml", 0.0);
            inputParams.setValue("des_monto_pedido_ma",0.0);
            inputParams.setValue("des_monto_descuento_ml", 0.0);
            inputParams.setValue("des_monto_descuento_ma", 0.0);

            if(rsDespacho.getString("smn_entidad_rf")!=null){
                inputParams.setValue("smn_entidad_rf", rsDespacho.getInt("smn_entidad_rf"));
            }
            if(rsDespacho.getString("smn_sucursal_rf")!=null){
                inputParams.setValue("smn_sucursal_rf", rsDespacho.getInt("smn_sucursal_rf"));
            }
            if(rsDespacho.getString("smn_almacen_rf")!=null){
                inputParams.setValue("smn_almacen_rf", rsDespacho.getInt("smn_almacen_rf"));
            }
            if(rsDespacho.getString("smn_modulo_rf")!=null){
                inputParams.setValue("smn_modulo_rf", rsDespacho.getInt("smn_modulo_rf"));
            }
            if(rsDespacho.getString("smn_documento_origen_id")!=null){
                inputParams.setValue("smn_documento_origen_id", rsDespacho.getInt("smn_documento_origen_id"));
            }
            if(rsDespacho.getString("des_numero_documento_origen")!=null){
                inputParams.setValue("des_numero_documento_origen", rsDespacho.getInt("des_numero_documento_origen"));
            }
            if(rsDespacho.getString("smn_documento_id")!=null){
                inputParams.setValue("smn_documento_id", rsDespacho.getInt("smn_documento_id"));
            }
            if(rsDespacho.getString("des_numero_documento")!=null){
                inputParams.setValue("des_numero_documento", rsDespacho.getInt("des_numero_documento"));
            }
            if(rsDespacho.getString("des_monto_pedido_ml")!=null){
                inputParams.setValue("des_monto_pedido_ml", rsDespacho.getDouble("des_monto_pedido_ml"));
            }
            if(rsDespacho.getString("des_monto_pedido_ma")!=null){
                inputParams.setValue("des_monto_pedido_ma", rsDespacho.getDouble("des_monto_pedido_ma"));
            }
            if(rsDespacho.getString("des_monto_descuento_ml")!=null){
                inputParams.setValue("des_monto_descuento_ml", rsDespacho.getDouble("des_monto_descuento_ml"));
            }
            if(rsDespacho.getString("des_monto_descuento_ma")!=null){
                inputParams.setValue("des_monto_descuento_ma", rsDespacho.getDouble("des_monto_descuento_ma"));
            }

            Date fecha_despacho = new Date();
            double impuesto_ml = 0.0;
            double bonificacion_ml = 0.0;

            if(rsDespacho.getString("des_fecha_despacho")!=null){
                fecha_despacho = rsDespacho.getDate("des_fecha_despacho");
            }

            if(rsDespacho.getString("des_monto_impuesto_ml")!=null){
                impuesto_ml = rsDespacho.getDouble("des_monto_impuesto_ml");
            }

            inputParams.setValue("des_monto_impuesto_ml", impuesto_ml);

            if(rsDespacho.getString("des_monto_bonificacion_ml")!=null){
                bonificacion_ml =  rsDespacho.getDouble("des_monto_bonificacion_ml");
            }

            inputParams.setValue("des_monto_bonificacion_ml", bonificacion_ml);
            double monto_ml = impuesto_ml + bonificacion_ml;
            inputParams.setValue("mca_monto_valor_agregado_ml", monto_ml);
            double impuesto_ma = 0.0;

            if(rsDespacho.getString("des_monto_impuesto_ma")!=null){
                impuesto_ma = rsDespacho.getDouble("des_monto_impuesto_ma");
            }

            inputParams.setValue("des_monto_impuesto_ma", impuesto_ma);
            double bonificacion_ma = 0.0;

            if(rsDespacho.getString("des_monto_bonificacion_ma")!=null){
                bonificacion_ma = rsDespacho.getDouble("des_monto_bonificacion_ma");
            }
            
            inputParams.setValue("des_monto_bonificacion_ma", bonificacion_ma);
            double monto_ma = impuesto_ma + bonificacion_ma;
            inputParams.setValue("mca_monto_valor_agregado_ma", monto_ma);
            inputParams.setValue("des_monto_neto_ml", 0.0);
            inputParams.setValue("des_monto_neto_ma", 0.0);
            inputParams.setValue("smn_moneda_rf", 0);
            inputParams.setValue("smn_tasa_rf", 0);

            if(rsDespacho.getString("des_monto_neto_ml")!=null){
                inputParams.setValue("des_monto_neto_ml", rsDespacho.getDouble("des_monto_neto_ml"));
            }
            if(rsDespacho.getString("des_monto_neto_ma")!=null){
                inputParams.setValue("des_monto_neto_ma", rsDespacho.getDouble("des_monto_neto_ma"));
            }
            if(rsDespacho.getString("smn_moneda_rf")!=null){
                inputParams.setValue("smn_moneda_rf", rsDespacho.getInt("smn_moneda_rf"));
            }
            if(rsDespacho.getString("smn_tasa_rf")!=null){
                inputParams.setValue("smn_tasa_rf", rsDespacho.getInt("smn_tasa_rf"));
            }

            String sqlMovimiento    = getSQL(getResource("insertar-movimiento_cabecera.sql"),inputParams);
            Recordset rsMovimiento  = db.get(sqlMovimiento);

            if(rsDespacho.getRecordCount() <= 0){
                str = "NO SE REGISTRO EL MOVIMIENTO CABECERA";
                bw.write(str);
                bw.flush();
                bw.newLine();
                conn.rollback();
                getRequest().setAttribute("mensaje", str);
                bw.close();
                fw.close();
                conn.close();
                return 1;
            }

            rsMovimiento.first();
            String movimientoCabeceraId = rsMovimiento.getString("movimiento_cabecera_id");


            if(movimientoCabeceraId == "0" && movimientoCabeceraId == null){
                str = "EL ID DEL MOVIMIENTO ESTA VACIO";
                bw.write(str);
                bw.flush();
                bw.newLine();
                conn.rollback();
                getRequest().setAttribute("mensaje", str);
                bw.close();
                fw.close();
                conn.close();
                return 1;
            }

            int id = Integer.parseInt(movimientoCabeceraId);
            inputParams.setValue("smn_movimiento_cabecera_id", id);

            String sqlDespacho_detalle   = getSQL(getResource("consultar-despacho_detalle.sql"),inputParams);
            Recordset rsDespacho_detalle = db.get(sqlDespacho_detalle);

            if(rsDespacho_detalle.getRecordCount() <= 0){
                str = "NO SE ENCONTRARON LOS DETALLES DEL DESPACHO";
                bw.write(str);
                bw.flush();
                bw.newLine();
                conn.rollback();
                getRequest().setAttribute("mensaje", str);
                bw.close();
                fw.close();
                conn.close();
                return 1;
            }

            String cit_tipo_costo;

            while(rsDespacho_detalle.next() != false){
                rsDespacho_detalle.next();

                inputParams.setValue("smn_despacho_detalle_id", 0);
                inputParams.setValue("smn_item_rf", 0);
                inputParams.setValue("smn_centro_costo_rf",0);
                inputParams.setValue("smn_unidad_medida_venta_rf",0);

                if(rsDespacho_detalle.getString("smn_despacho_detalle_id")!=null){
                    inputParams.setValue("smn_despacho_detalle_id", rsDespacho_detalle.getInt("smn_despacho_detalle_id"));
                }
                if(rsDespacho_detalle.getString("smn_item_rf")!=null){
                    inputParams.setValue("smn_item_rf", rsDespacho_detalle.getInt("smn_item_rf"));
                }

                String sql = getSQL(getResource("consultar-centro_costo.sql"),inputParams);
                Recordset rsCentroCosto = db.get(sql);

                if(rsCentroCosto.getRecordCount() > 0){
                    rsCentroCosto.first();

                    if(rsCentroCosto.getString("smn_centro_costo_rf")!=null){
                        inputParams.setValue("smn_centro_costo_rf",rsCentroCosto.getInt("smn_centro_costo_rf"));
                    }
                }

                sql = getSQL(getResource("consultar-unidad_medida_venta.sql"),inputParams);
                Recordset rsUnidadMedida = db.get(sql);

                if(rsUnidadMedida.getRecordCount() > 0){
                    rsUnidadMedida.first();

                    if(rsUnidadMedida.getString("smn_unidad_medida_venta_rf")!=null){
                        inputParams.setValue("smn_unidad_medida_venta_rf",rsUnidadMedida.getInt("smn_unidad_medida_venta_rf"));
                    }
                }

                double cantidadDespachada = 0.0;

                if(rsDespacho_detalle.getString("dde_cantidad_despachada")!=null){
                    cantidadDespachada =  rsDespacho_detalle.getDouble("dde_cantidad_despachada");
                }

                inputParams.setValue("dde_cantidad_despachada", cantidadDespachada);
                double costo_ml = 0.0;

                if(rsDespacho_detalle.getString("dde_costo_ml")!=null){
                    costo_ml = rsDespacho_detalle.getDouble("dde_costo_ml");
                }

                inputParams.setValue("dde_costo_ml", costo_ml);

                double costo_ma = 0.0;

                if(rsDespacho_detalle.getString("dde_costo_ma")!=null){
                    costo_ma = rsDespacho_detalle.getDouble("dde_costo_ma");
                }

                inputParams.setValue("dde_costo_ma", costo_ma);
                double montoBruto_ml = cantidadDespachada * costo_ml;
                double montoBruto_ma = cantidadDespachada * costo_ma;

                inputParams.setValue("mde_monto_bruto_ml", montoBruto_ml);
                inputParams.setValue("mde_monto_bruto_ma", montoBruto_ma);

                inputParams.setValue("mde_lote", 0);
                inputParams.setValue("mde_fecha_vencimiento_lote",null);

                String sqlConsultarLote = getSQL(getResource("consultarLote.sql"),inputParams);
                Recordset lote = db.get(sqlConsultarLote);

                if(lote.getRecordCount()>0){
                    lote.first();
                    inputParams.setValue("mde_lote", lote.getInt("id_lote"));
                    inputParams.setValue("mde_fecha_vencimiento_lote", lote.getString("fecha_venc"));
                }

                String sqlTipoCosto = getSQL(getResource("sql-consultarTipoCosto.sql"),inputParams);
                Recordset rsTipoCosto = db.get(sqlTipoCosto);

                if(rsTipoCosto.getRecordCount() <= 0){
                    str = "EL ITEM NO ESTA REGISTRADO EN CARACTERISTICAS DEL ITEM";
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    conn.rollback();
                    getRequest().setAttribute("mensaje", str);
                    bw.close();
                    fw.close();
                    conn.close();
                    return 1;
                }

                rsTipoCosto.first();

                if(rsTipoCosto.getString("cit_tipo_costo") == null){
                    str = "EL TIPO DE COSTO DE LA CARACTERISTICA DE ITEM SE ENCUENTRA VACIO";
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    conn.rollback();
                    getRequest().setAttribute("mensaje", str);
                    bw.close();
                    fw.close();
                    conn.close();
                    return 1;
                }

                cit_tipo_costo = rsTipoCosto.getString("cit_tipo_costo");

                String sqlControlItem = getSQL(getResource("sql-consultaControlItem.sql"),inputParams);
                Recordset rssqlCheckControl = db.get(sqlControlItem);

                if(rssqlCheckControl.getRecordCount() <= 0 ){
                    str = "NO HAY EXISTENCIA DISPONIBLE PARA ESTE ITEM" ;
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    conn.rollback();
                    getRequest().setAttribute("mensaje", str);
                    bw.close();
                    fw.close();
                    conn.close();
                    return 1;
                }

                boolean existe = false;
                double saldo_inicial_existencia = 0.0;
                double cantidad_entradas = 0.0;
                double cantidad_salidas = 0.0;
                double saldo_final_existencia = 0.0;
                double valor_inicial = 0.0;
                double valor_entrada = 0.0;
                double valor_salida = 0.0;
                double valor_final = 0.0;
                double costo_promedio = 0.0;
                double ultimo_costo = 0.0;
                double costo_mas_alto=0.0;
                double costo_promedio_ponderado = 0.0;
                double valor_inicial_ma = 0.0;
                double valor_entrada_ma = 0.0;
                double valor_salida_ma = 0.0;
                double valor_final_ma = 0.0;
                double costo_promedio_ma = 0.0;
                double ultimo_costo_ma = 0.0;
                double costo_mas_alto_ma = 0.0;
                double costo_promedio_ponderado_ma = 0.0;
                Date fecha_movimiento = new Date();

                while (rssqlCheckControl.next()){
                    inputParams.setValue("smn_control_item_id", rssqlCheckControl.getInteger("smn_control_item_id"));
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
                valor_inicial_ma=valor_final_ma;

                inputParams.setValue("coi_valor_inicial", valor_inicial);
                inputParams.setValue("coi_valor_inicial_ma", valor_inicial_ma);
                valor_entrada=costo_ml*cantidad_entradas;

                valor_entrada_ma=costo_ma*cantidad_entradas;

                inputParams.setValue("coi_valor_entrada", valor_entrada);
                inputParams.setValue("coi_valor_entrada_ma", valor_entrada_ma);

                if (cit_tipo_costo.equals("PR")) {
                    valor_salida=costo_promedio*cantidad_salidas;
                    valor_salida_ma=costo_promedio_ma*cantidad_salidas;
                } else if (cit_tipo_costo.equals("UC")) {
                    valor_salida=ultimo_costo*cantidad_salidas;
                    valor_salida_ma=ultimo_costo_ma*cantidad_salidas;
                } else if (cit_tipo_costo.equals("CM")) {
                    valor_salida=costo_mas_alto*cantidad_salidas;
                    valor_salida_ma=costo_mas_alto_ma*cantidad_salidas;
                } else if (cit_tipo_costo.equals("PP")) {
                    valor_salida=costo_promedio_ponderado*cantidad_salidas;
                    valor_salida_ma=costo_promedio_ponderado_ma*cantidad_salidas;
                }

                inputParams.setValue("coi_valor_salida", valor_salida);
                inputParams.setValue("coi_valor_salida_ma", valor_salida_ma);
                valor_final=valor_inicial-valor_salida;

                valor_final_ma=valor_inicial_ma-valor_salida_ma;
                inputParams.setValue("coi_valor_final", valor_final);
                inputParams.setValue("coi_valor_final_ma", valor_final_ma);
                costo_promedio=valor_final / saldo_final_existencia;
                costo_promedio_ma=valor_final_ma / saldo_final_existencia;
                inputParams.setValue("coi_costo_promedio", costo_promedio);
                inputParams.setValue("coi_costo_promedio_ma", costo_promedio_ma);
                ultimo_costo=costo_ml;
                ultimo_costo_ma=costo_ma;

                inputParams.setValue("coi_ultimo_costo", ultimo_costo);
                inputParams.setValue("coi_ultimo_costo_ma", ultimo_costo_ma);
                costo_mas_alto=Math.max(costo_mas_alto, costo_ml);

                costo_mas_alto_ma=Math.max(costo_mas_alto_ma, costo_ma);

                inputParams.setValue("coi_costo_mas_alto", costo_mas_alto);
                inputParams.setValue("coi_costo_mas_alto_ma", costo_mas_alto_ma);

                if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
                    costo_promedio_ponderado=((cantidad_entradas*costo_ml)+(saldo_inicial_existencia*costo_promedio_ponderado))/(cantidad_entradas + saldo_inicial_existencia);
                else
                    costo_promedio_ponderado = 0.0;

                if((cantidad_entradas + saldo_inicial_existencia) > 0.0)
                    costo_promedio_ponderado_ma=((cantidad_entradas*costo_ma)+(saldo_inicial_existencia*costo_promedio_ponderado_ma))/(cantidad_entradas + saldo_inicial_existencia);
                else
                    costo_promedio_ponderado = 0.0;

                inputParams.setValue("coi_costo_promedio_ponderado", costo_promedio_ponderado);
                inputParams.setValue("coi_costo_promedio_ponderado_ma", costo_promedio_ponderado_ma);
                inputParams.setValue("coi_cantidad_reservada", 0.0);
                inputParams.setValue("coi_cantidad_espera_recepcion", 0.0);

                if(fecha_despacho.compareTo(fecha_movimiento)==0){
                    String updateControlItem = getSQL(getResource("update_ControlItem.sql"), inputParams);
                    db.exec(updateControlItem);
                }else{
                    inputParams.setValue("coi_fecha_movimiento",fecha_despacho);
                    String insertControlItem2 = getSQL(getResource("insert_ControlItem.sql"), inputParams);
                    Recordset insControlItem2 = db.get(insertControlItem2);

                    if(insControlItem2.getRecordCount() <= 0) {
                        str += "NO SE CREO EL CONTROL ITEM";
                        bw.write(str);
                        bw.flush();
                        bw.newLine();
                        conn.rollback();
                        getRequest().setAttribute("mensaje", str);
                        bw.close();
                        fw.close();
                        conn.close();
                        return 1;
                    }
                }

                String sqlMovimiento_detalle   = getSQL(getResource("insertar-movimiento_detalle.sql"), inputParams);
                db.get(sqlMovimiento_detalle);

            }

            String sqlCantidades  = getSQL(getResource("consultar-cantidades.sql"),inputParams);
            Recordset rsCantidades = db.get(sqlCantidades);

            if(rsCantidades.getRecordCount() <= 0){
                str = "NO SE ENCONTRARON LAS CANTIDADES DEL DESPACHO";
                bw.write(str);
                bw.flush();
                bw.newLine();
                conn.rollback();
                getRequest().setAttribute("mensaje", str);
                bw.close();
                fw.close();
                conn.close();
                return 1;
            }

            rsCantidades.first();
            double solicitada = rsCantidades.getDouble("cantidad_solicitada");
            double despachada = rsCantidades.getDouble("cantidad_despachada");

            if(solicitada == despachada){
                String sqlActualizar_estatus   = getSQL(getResource("actualizar-despacho_EN.sql"),inputParams);
                Recordset result = db.get(sqlActualizar_estatus);

                if(result.getRecordCount() <= 0){
                    str = "NO SE PUDO ACTUALIZAR EL DESPACHO";
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    conn.rollback();
                    getRequest().setAttribute("mensaje", str);
                    bw.close();
                    fw.close();
                    conn.close();
                    return 1;
                }

            }else{
                String sqlActualizar_estatus = getSQL(getResource("actualizar-despacho_PD.sql"),inputParams);
                Recordset result = db.get(sqlActualizar_estatus);

                if(result.getRecordCount() <= 0){
                    str = "NO SE PUDO ACTUALIZAR EL DESPACHO";
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                    conn.rollback();
                    getRequest().setAttribute("mensaje", str);
                    bw.close();
                    fw.close();
                    conn.close();
                    return 1;
                }
            }

            bw.close();
            fw.close();

            conn.commit();
            str = "DESPACHO GENERADO EXITOSAMENTE";
            getRequest().setAttribute("mensaje", str);
            conn.close();
            return 0;

        }catch(Throwable e){
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
