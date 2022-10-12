select
		smn_inventario.smn_tipo_documento.tdc_codigo
from
		smn_inventario.smn_tipo_documento
where
		upper(smn_inventario.smn_tipo_documento.tdc_codigo) = upper(${fld:tdc_codigo})
