select
		smn_inventario.smn_tipo_documento.tdc_nombre
from
		smn_inventario.smn_tipo_documento
where
		upper(smn_inventario.smn_tipo_documento.tdc_nombre) = upper(${fld:tdc_nombre})
