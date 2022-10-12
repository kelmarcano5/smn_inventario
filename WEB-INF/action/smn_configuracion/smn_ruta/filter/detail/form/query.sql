SELECT
	smn_inventario.smn_ruta.smn_ruta_id,
	smn_inventario.smn_ruta.rut_codigo,
	smn_inventario.smn_ruta.rut_nombre,
	smn_base.smn_zona.zon_codigo ||' - '|| smn_base.smn_zona.zon_descripcion AS smn_zona_rf,
	smn_inventario.smn_ruta.rut_posicion_ruta,
	case
	when smn_inventario.smn_ruta.rut_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_ruta.rut_estatus='IN' then '${lbl:b_inactive}'
	end as rut_estatus,
	smn_inventario.smn_ruta.rut_vigencia,
	smn_inventario.smn_ruta.rut_idioma,
	smn_inventario.smn_ruta.rut_usuario,
	smn_inventario.smn_ruta.rut_fecha_registro,
	smn_inventario.smn_ruta.rut_hora
FROM
	smn_inventario.smn_ruta
	INNER JOIN smn_base.smn_zona ON smn_base.smn_zona.smn_zona_id = smn_inventario.smn_ruta.smn_zona_rf
WHERE
	smn_ruta_id = ${fld:id}