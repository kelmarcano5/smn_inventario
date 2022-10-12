CREATE TABLE smn_inventario.smn_movimiento_cabecera(
  smn_movimiento_cabecera_id INTEGER NOT NULL,
  smn_modulo_rf INTEGER NOT NULL,
  smn_documento_rf INTEGER NOT NULL,
  mca_numero INTEGER,
  smn_proveedor_rf INTEGER,
  smn_orden_compra_rf INTEGER NOT NULL,
  smn_almacen_rf INTEGER NOT NULL,
  smn_sucursal_rf INTEGER NOT NULL,
  smn_entidad_rf INTEGER NOT NULL,
  mca_recibo INTEGER NOT NULL,
  mca_monto_documento_ml DOUBLE PRECISION NOT NULL,
  mca_monto_documento_ma DOUBLE PRECISION,
  mca_monto_diminucion_ml DOUBLE PRECISION NOT NULL,
  mca_monto_diminucion_ma DOUBLE PRECISION,
  mca_monto_valor_agregado_ml DOUBLE PRECISION NOT NULL,
  mca_monto_valor_agregado_ma DOUBLE PRECISION,
  mca_monto_neto_ml DOUBLE PRECISION NOT NULL,
  mcc_monto_neto_ma DOUBLE PRECISION,
  smn_moneda_rf INTEGER,
  smn_tasa_rf INTEGER,
  mca_fecha_operacion DATE NOT NULL,
  mca_estatus_proceso CHARACTER(2) NOT NULL,
  mca_estatus_financiero CHARACTER(2) NOT NULL,
  mca_idioma CHARACTER(2) NOT NULL,
  mca_usuario VARCHAR(10) NOT NULL,
  mca_fecha_registro DATE NOT NULL,
  mca_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_movimiento_cabecera;


CREATE TABLE smn_inventario.smn_movimiento_detalle(
  smn_movimiento_detalle_id INTEGER NOT NULL,
  smn_movimiento_cabecera_id INTEGER NOT NULL,
  smn_item_rf INTEGER,
  smn_activo_fijo_rf INTEGER,
  smn_centro_costo_rf INTEGER NOT NULL,
  mde_es_bonificacion CHARACTER(2) NOT NULL,
  mde_cantidad INTEGER,
  mde_lote INTEGER,
  mde_fecha_elaboracion_lote DATE,
  mde_fecha_vencimiento_lote DATE,
  mde_descripcion VARCHAR(48) NOT NULL,
  smn_unidad_medida_rf INTEGER NOT NULL,
  smn_precio_ml DOUBLE PRECISION NOT NULL,
  smn_tasa_rf INTEGER NOT NULL,
  smn_moneda_rf INTEGER,
  smn_precio_ma DOUBLE PRECISION,
  mde_monto_documento_ml DOUBLE PRECISION NOT NULL,
  mde_monto_documento_ma DOUBLE PRECISION,
  mde_monto_disminucion_ml DOUBLE PRECISION,
  mde_monto_disminucion_ma DOUBLE PRECISION,
  mde_monto_valor_agregado_ml DOUBLE PRECISION,
  mde_monto_valor_agregado_ma DOUBLE PRECISION,
  mde_monto_neto_ml DOUBLE PRECISION,
  mde_monto_neto_ma DOUBLE PRECISION,
  mde_estatus CHARACTER(2) NOT NULL,
  mde_idioma CHARACTER(2) NOT NULL,
  mde_usuario VARCHAR(10) NOT NULL,
  mde_fecha_registro DATE NOT NULL,
  mde_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_movimiento_detalle;


CREATE TABLE smn_inventario.smn_tipo_documento(
  smn_tipo_documento_id INTEGER NOT NULL,
  tdc_codigo VARCHAR(10) NOT NULL,
  tdc_nombre VARCHAR(100) NOT NULL,
  tdc_naturaleza CHARACTER(2) NOT NULL,
  tdc_vigencia DATE NOT NULL,
  tdc_estatus CHARACTER(2) NOT NULL,
  tdc_idioma CHARACTER(2) NOT NULL,
  tdc_usuario VARCHAR(10) NOT NULL,
  tdc_fecha_registro DATE NOT NULL,
  tdc_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_tipo_documento;


CREATE TABLE smn_inventario.smn_documento(
  smn_documento_id INTEGER NOT NULL,
  smn_tipo_documento_id INTEGER NOT NULL,
  smn_transaccion_general_rf INTEGER NOT NULL,
  doc_codigo VARCHAR(10) NOT NULL,
  doc_nombre VARCHAR(100) NOT NULL,
  doc_secuencia INTEGER,
  doc_vigencia DATE NOT NULL,
  doc_estatus CHARACTER(2) NOT NULL,
  doc_idioma CHARACTER(2) NOT NULL,
  doc_usuario VARCHAR(10) NOT NULL,
  doc_fecha_registro DATE NOT NULL,
  doc_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_documento;


CREATE TABLE smn_inventario.smn_rol(
  smn_rol_id INTEGER NOT NULL,
  smn_usuarios_rf INTEGER NOT NULL,
  rol_tipo CHARACTER(2) NOT NULL,
  smn_corporaciones_rf INTEGER,
  smn_entidades_rf INTEGER,
  smn_sucursales_rf INTEGER,
  smn_areas_servicios_rf INTEGER,
  smn_unidades_servicios_rf INTEGER,
  rol_posicion_estructura_rf INTEGER,
  rol_estatus CHARACTER(2) NOT NULL,
  rol_vigencia DATE NOT NULL,
  rol_idioma CHARACTER(2) NOT NULL,
  rol_usuario VARCHAR(10) NOT NULL,
  rol_fecha_registro DATE NOT NULL,
  rol_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_rol;


CREATE TABLE smn_inventario.smn_caracteristica_almacen(
  smn_caracteristica_almacen_id INTEGER NOT NULL,
  smn_almacen_rf INTEGER NOT NULL,
  cal_tipo_almacen CHARACTER(2) NOT NULL,
  cal_capacidad_almacenaje DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_rf INTEGER NOT NULL,
  cal_espacio_fisico DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_espacio_fisico_rf INTEGER NOT NULL,
  cal_politica_recepcion CHARACTER(2) NOT NULL,
  cal_estatus CHARACTER(2) NOT NULL,
  cal_vigencia_desde DATE NOT NULL,
  cal_vigencia_hasta DATE NOT NULL,
  cal_idioma CHARACTER(2) NOT NULL,
  cal_usuario VARCHAR(10) NOT NULL,
  cal_fecha_registro DATE NOT NULL,
  cal_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_caracteristica_almacen;


CREATE TABLE smn_inventario.smn_division(
  smn_division_id INTEGER NOT NULL,
  div_codigo VARCHAR(10) NOT NULL,
  div_descripcion VARCHAR(100) NOT NULL,
  div_estatus CHARACTER(2) NOT NULL,
  div_vigencia DATE NOT NULL,
  div_idioma CHARACTER(2) NOT NULL,
  div_usuario VARCHAR(10) NOT NULL,
  div_fecha_registro DATE NOT NULL,
  div_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_division;


CREATE TABLE smn_inventario.smn_sub_division(
  smn_sub_division_id INTEGER NOT NULL,
  smn_almacen_rf INTEGER NOT NULL,
  smn_division_id INTEGER NOT NULL,
  sdi_codigo VARCHAR(10) NOT NULL,
  sdi_descripcion VARCHAR(100) NOT NULL,
  sdi_es_ubicacion CHARACTER(2) NOT NULL,
  sdi_alto DOUBLE PRECISION,
  smn_unidad_medida_alto_rf INTEGER,
  sdi_ancho DOUBLE PRECISION,
  smn_unidad_medida_ancho_rf INTEGER,
  sdi_profundidad DOUBLE PRECISION,
  smn_unidad_medida_prof_rf INTEGER,
  sdi_estatus CHARACTER(2) NOT NULL,
  sdi_vigencia DATE NOT NULL,
  sdi_idioma CHARACTER(2) NOT NULL,
  sdi_usuario VARCHAR(10) NOT NULL,
  sdi_fecha_registro DATE NOT NULL,
  sdi_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_sub_division;


CREATE TABLE smn_inventario.smn_item_almacen(
  smn_item_almacen_id INTEGER NOT NULL,
  smn_item_rf INTEGER NOT NULL,
  smn_almacen_rf INTEGER NOT NULL,
  ria_estatus CHARACTER(2) NOT NULL,
  ria_vigencia DATE NOT NULL,
  ria_idioma CHARACTER(2) NOT NULL,
  ria_usuario VARCHAR(10) NOT NULL,
  ria_fecha_registro DATE NOT NULL,
  ria_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_item_almacen;


CREATE TABLE smn_inventario.smn_caracteristica_item(
  smn_caracteristica_item_id INTEGER NOT NULL,
  smn_item_rf INTEGER NOT NULL,
  cit_codigo_barra VARCHAR(100),
  cit_codigo_qr VARCHAR(100),
  cit_codigo_alterno VARCHAR(100),
  cit_descripcion_tecnica VARCHAR(400),
  smn_unidad_medida_base_rf INTEGER NOT NULL,
  smn_unidad_medida_compra_rf INTEGER NOT NULL,
  smn_unidad_medida_venta_rf INTEGER NOT NULL,
  smn_unidad_medida_almacenamiento_rf INTEGER NOT NULL,
  smn_unidad_medida_despacho_rf INTEGER NOT NULL,
  cit_peso DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_peso_rf INTEGER NOT NULL,
  cit_alto DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_alto_rf INTEGER NOT NULL,
  cit_ancho DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_ancho_rf INTEGER NOT NULL,
  cit_profundidad DOUBLE PRECISION NOT NULL,
  smn_unidad_medida_profundidad_rf INTEGER NOT NULL,
  cit_es_medicamento CHARACTER(2) NOT NULL,
  cit_principio_activo VARCHAR(100),
  cit_req_control_lote CHARACTER(2) NOT NULL,
  cit_req_control_vencimiento CHARACTER(2) NOT NULL,
  cit_dias_ant_vencimiento INTEGER,
  cit_tipo_costo CHARACTER(2) NOT NULL,
  cit_valoracion_inventario CHARACTER(2) NOT NULL,
  cit_metodo_despacho CHARACTER(2) NOT NULL,
  cit_cant_minima DOUBLE PRECISION NOT NULL,
  cit_cant_maxima DOUBLE PRECISION NOT NULL,
  cit_punto_reorden DOUBLE PRECISION NOT NULL,
  cit_cant_seguridad DOUBLE PRECISION NOT NULL,
  cit_es_reusable CHARACTER(2) NOT NULL,
  cit_reuso_apertura CHARACTER(2),
  cit_cant_reuso DOUBLE PRECISION,
  cit_origen_producto CHARACTER(2) NOT NULL,
  cit_descripcion_compra VARCHAR(400),
  cit_codigo_arancel VARCHAR(40),
  cit_dias_entrega DOUBLE PRECISION,
  smn_centro_costo_rf INTEGER NOT NULL,
  cit_proveedor_exclusivo CHARACTER(2) NOT NULL,
  cit_almacenado CHARACTER(2) NOT NULL,
  cit_estatus CHARACTER(2) NOT NULL,
  cit_vigencia DATE NOT NULL,
  cit_idioma CHARACTER(2) NOT NULL,
  cit_usuario VARCHAR(10) NOT NULL,
  cit_fecha_registro DATE NOT NULL,
  cit_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_caracteristica_item;



ALTER TABLE smn_inventario.smn_movimiento_cabecera ADD PRIMARY KEY (smn_movimiento_cabecera_id);

ALTER TABLE smn_inventario.smn_movimiento_detalle ADD PRIMARY KEY (smn_movimiento_detalle_id);
ALTER TABLE smn_inventario.smn_movimiento_detalle ADD CONSTRAINT FK_smn_movimiento_detalle_0 FOREIGN KEY (smn_movimiento_cabecera_id) REFERENCES smn_inventario.smn_movimiento_cabecera (smn_movimiento_cabecera_id) ON DELETE NO ACTION;

ALTER TABLE smn_inventario.smn_tipo_documento ADD PRIMARY KEY (smn_tipo_documento_id);

ALTER TABLE smn_inventario.smn_documento ADD PRIMARY KEY (smn_documento_id);
ALTER TABLE smn_inventario.smn_documento ADD CONSTRAINT FK_smn_documento_0 FOREIGN KEY (smn_tipo_documento_id) REFERENCES smn_inventario.smn_tipo_documento (smn_tipo_documento_id) ON DELETE NO ACTION;

ALTER TABLE smn_inventario.smn_rol ADD PRIMARY KEY (smn_rol_id);

ALTER TABLE smn_inventario.smn_caracteristica_almacen ADD PRIMARY KEY (smn_caracteristica_almacen_id);

ALTER TABLE smn_inventario.smn_division ADD PRIMARY KEY (smn_division_id);

ALTER TABLE smn_inventario.smn_sub_division ADD PRIMARY KEY (smn_sub_division_id);
ALTER TABLE smn_inventario.smn_sub_division ADD CONSTRAINT FK_smn_sub_division_0 FOREIGN KEY (smn_division_id) REFERENCES smn_inventario.smn_division (smn_division_id) ON DELETE NO ACTION;

ALTER TABLE smn_inventario.smn_item_almacen ADD PRIMARY KEY (smn_item_almacen_id);

ALTER TABLE smn_inventario.smn_caracteristica_item ADD PRIMARY KEY (smn_caracteristica_item_id);

