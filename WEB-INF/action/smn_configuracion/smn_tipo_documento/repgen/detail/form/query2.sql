select
		smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tid_codigo,
	smn_inventario.smn_tipo_documento.tid_nombre,
	smn_inventario.smn_tipo_documento.tid_empresa,
	smn_inventario.smn_tipo_documento.tid_sucursal,
	smn_inventario.smn_tipo_documento.tid_area_servicio,
	smn_inventario.smn_tipo_documento.tid_unidad_servicio,
	smn_inventario.smn_tipo_documento.tid_req_control_fiscal,
	smn_inventario.smn_tipo_documento.tid_serie,
	smn_inventario.smn_tipo_documento.tid_numero_fiscal,
	smn_inventario.smn_tipo_documento.tid_requiere_control_interno,
	smn_inventario.smn_tipo_documento.tid_numero_control,
	smn_inventario.smn_tipo_documento.tid_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdo_codigo,
	smn_inventario.smn_tipo_documento.tdo_descripcion,
	smn_inventario.smn_tipo_documento.tdo_accion,
	smn_inventario.smn_tipo_documento.tdo_estatus,
	smn_inventario.smn_tipo_documento.tdo_vigencia,
	smn_inventario.smn_tipo_documento.tdo_fecha_registro
from
	smn_inventario.smn_tipo_documento 
where
	smn_inventario.smn_tipo_documento.smn_tipo_documento_id = ${fld:id}
