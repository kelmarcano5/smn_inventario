select 
	smn_base.smn_unidad_medida.smn_unidad_medida_id as id, smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as item 
from 
	smn_base.smn_unidad_medida
	INNER JOIN
	smn_base.smn_conversion_unidad_medida ON smn_base.smn_unidad_medida.smn_unidad_medida_id=smn_base.smn_conversion_unidad_medida.smn_unidad_medida_origen_rf