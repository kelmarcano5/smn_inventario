select 
	smn_inventario.smn_documento.smn_documento_id as id, 
	smn_inventario.smn_documento.doc_codigo|| ' - ' || smn_inventario.smn_documento.doc_nombre as item 
from 
	smn_inventario.smn_documento
INNER JOIN 
	smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
WHERE 
	smn_inventario.smn_tipo_documento.tdc_naturaleza = 'EN'
	AND
	smn_inventario.smn_documento.doc_tipo_documento_pago = 'DP'