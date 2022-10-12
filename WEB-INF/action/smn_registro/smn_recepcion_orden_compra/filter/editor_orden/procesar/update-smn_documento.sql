UPDATE smn_cont_financiera.smn_documento SET
	doc_monto_ml = (SELECT 
						SUM(dod_monto_ml) 
					FROM 
						smn_cont_financiera.smn_documento_detalle 
					WHERE 
						smn_documento_id = ${fld:smn_documento_cabecera_id}
					),
	doc_monto_me = (SELECT 
						SUM(dod_monto_ma) 
					FROM 
						smn_cont_financiera.smn_documento_detalle 
					WHERE 
						smn_documento_id = ${fld:smn_documento_cabecera_id}
					),
	doc_idioma = '${def:locale}',
  	doc_usuario = '${def:user}',
  	doc_fecha_registro = {d '${def:date}'},
  	doc_hora = '${def:time}'
WHERE
	smn_documento_id = ${fld:smn_documento_cabecera_id} 