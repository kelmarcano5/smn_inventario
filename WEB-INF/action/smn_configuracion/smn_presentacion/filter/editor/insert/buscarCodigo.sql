select
		smn_inventario.smn_presentacion.pre_codigo
from
		smn_inventario.smn_presentacion
where
		upper(smn_inventario.smn_presentacion.pre_codigo) = upper(${fld:pre_codigo})
