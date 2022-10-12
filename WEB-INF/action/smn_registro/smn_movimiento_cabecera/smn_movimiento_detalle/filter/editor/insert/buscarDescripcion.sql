select
		smn_inventario.smn_movimiento_detalle.mde_descripcion
from
		smn_inventario.smn_movimiento_detalle
where
		upper(smn_inventario.smn_movimiento_detalle.mde_descripcion) = upper(${fld:mde_descripcion})
