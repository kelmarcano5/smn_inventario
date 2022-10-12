select
		smn_inventario.smn_division.smn_division_id,
	${field}
from
	smn_inventario.smn_division
where
		smn_inventario.smn_division.smn_division_id is not null
	${filter}
	
	
