select 
	smn_sucursales_id as id, 
	suc_codigo || ' - ' || suc_nombre as item 
from smn_base.smn_sucursales