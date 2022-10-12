select
		smn_inventario.smn_presentacion.pre_descripcion
from
		smn_inventario.smn_presentacion
where
		upper(smn_inventario.smn_presentacion.pre_descripcion) = upper(${fld:pre_descripcion})
