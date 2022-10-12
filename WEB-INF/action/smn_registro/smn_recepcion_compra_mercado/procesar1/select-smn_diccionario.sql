SELECT
	smn_diccionario_id
FROM
	smn_base.smn_diccionario
WHERE
	dic_esquema = 'smn_inventario'
AND
	dic_tabla = ${fld:tabla}
AND
	dic_campo = ${fld:campo}