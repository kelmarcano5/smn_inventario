select 
	smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id as mdi_sustraendo_rf_ref 
from   
	smn_base.smn_codigos_impuestos 
where  
	upper(imp_monto_sustraendo) = upper(${fld:mdi_sustraendo_rf_desc})
