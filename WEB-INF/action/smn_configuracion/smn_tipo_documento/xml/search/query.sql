select
		smn_inventario.smn_tipo_documento.smn_tipo_documento_id,
	case
	when smn_inventario.smn_tipo_documento.tdc_naturaleza='EN' then '${lbl:b_entrada}'
	when smn_inventario.smn_tipo_documento.tdc_naturaleza='SA' then '${lbl:b_salida}'
	when smn_inventario.smn_tipo_documento.tdc_naturaleza='TR' then '${lblb_transferencia}'
	when smn_inventario.smn_tipo_documento.tdc_naturaleza='AJ' then '${lbl:b_setup}'
	end as tdc_naturaleza_combo,
	case
	when smn_inventario.smn_tipo_documento.tdc_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_tipo_documento.tdc_estatus='IN' then '${lbl:b_inactive}'
	end as tdc_estatus_combo,
	smn_inventario.smn_tipo_documento.tdc_codigo,
	smn_inventario.smn_tipo_documento.tdc_nombre,
	smn_inventario.smn_tipo_documento.tdc_naturaleza,
	smn_inventario.smn_tipo_documento.tdc_vigencia,
	smn_inventario.smn_tipo_documento.tdc_estatus,
	smn_inventario.smn_tipo_documento.tdc_idioma,
	smn_inventario.smn_tipo_documento.tdc_usuario,
	smn_inventario.smn_tipo_documento.tdc_fecha_registro,
	smn_inventario.smn_tipo_documento.tdc_hora,
		smn_inventario.smn_tipo_documento.smn_tipo_documento_id
	
from
	smn_inventario.smn_tipo_documento
order by
		smn_tipo_documento_id
