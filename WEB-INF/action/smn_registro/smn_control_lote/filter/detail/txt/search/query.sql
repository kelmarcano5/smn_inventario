select
	smn_inventario.smn_control_lote.*
from
	smn_inventario.smn_control_lote
where
	smn_control_lote_id = ${fld:id}
