select
		smn_inventario.smn_sub_division.smn_sub_division_id,
	${field}
from
	smn_inventario.smn_sub_division
where
		smn_inventario.smn_sub_division.smn_sub_division_id is not null
	${filter}
	
	
