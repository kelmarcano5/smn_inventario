insert into smn_inventario.smn_rel_ctrl_item_mov_det
(
smn_rel_ctrl_item_mov_det_id,
smn_control_item_id,
smn_movimiento_detalle_id
)
Select
nextval('smn_inventario.seq_smn_rel_ctrl_item_mov_det'),
${fld:smn_control_item_id},
smn_movimiento_detalle_id
FROM smn_inventario.smn_movimiento_detalle
WHERE smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id}