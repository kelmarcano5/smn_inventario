select
		smn_inventario.smn_despacho.des_descripcion
from
		smn_inventario.smn_despacho
where
		upper(smn_inventario.smn_despacho.des_descripcion) = upper('${fld:des_descripcion}')
