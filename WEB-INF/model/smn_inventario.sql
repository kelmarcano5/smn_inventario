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


CREATE TABLE smn_inventario.smn_rel_control_item_pedido(
  smn_rel_control_item_pedido_id INTEGER NOT NULL,
  smn_control_item_id INTEGER NOT NULL,
  pca_numero_pedido INTEGER NOT NULL,
  rcp_descripcion VARCHAR(200) NOT NULL,
  rcp_cantidad INTEGER NOT NULL,
  rcp_idioma CHARACTER(2) NOT NULL,
  rcp_usuario VARCHAR(10) NOT NULL,
  rcp_fecha_registro DATE NOT NULL,
  rcp_hora CHARACTER(8) NOT NULL
);

CREATE SEQUENCE smn_inventario.seq_smn_rel_control_item_pedido;
ALTER TABLE smn_inventario.smn_rel_control_item_pedido ADD PRIMARY KEY (smn_rel_control_item_pedido_id);


ALTER TABLE smn_inventario.smn_movimiento_cabecera ADD PRIMARY KEY (smn_movimiento_cabecera_id);

ALTER TABLE smn_inventario.smn_movimiento_detalle ADD PRIMARY KEY (smn_movimiento_detalle_id);
ALTER TABLE smn_inventario.smn_movimiento_detalle ADD CONSTRAINT FK_smn_movimiento_detalle_0 FOREIGN KEY (smn_movimiento_cabecera_id) REFERENCES smn_inventario.smn_movimiento_cabecera (smn_movimiento_cabecera_id) ON DELETE NO ACTION;

ALTER TABLE smn_inventario.smn_rel_control_item_pedido ADD PRIMARY KEY (smn_rel_control_item_pedido_id);

