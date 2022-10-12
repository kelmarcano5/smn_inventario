select
	smn_base.smn_estructura_organizacional.eor_nombre,
	${field}
from
	smn_inventario.smn_rol
	left outer join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id = smn_inventario.smn_rol.rol_posicion_estructura_rf
where
		smn_inventario.smn_rol.smn_rol_id = ${fld:id}
	
