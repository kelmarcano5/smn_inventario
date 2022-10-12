SELECT 
	smn_inventario.smn_principio_activo.smn_principio_activo_id AS id,
	smn_inventario.smn_principio_activo.pac_codigo ||' - '|| smn_inventario.smn_principio_activo.pac_descripcion AS item
FROM
	smn_inventario.smn_principio_activo