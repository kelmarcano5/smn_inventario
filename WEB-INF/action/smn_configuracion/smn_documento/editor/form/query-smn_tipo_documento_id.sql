select 
	smn_inventario.smn_tipo_documento.smn_tipo_documento_id as id, 
	smn_inventario.smn_tipo_documento.tdc_codigo|| ' - ' || smn_inventario.smn_tipo_documento.tdc_nombre as item 
from 
	smn_inventario.smn_tipo_documento 
order by tdc_nombre