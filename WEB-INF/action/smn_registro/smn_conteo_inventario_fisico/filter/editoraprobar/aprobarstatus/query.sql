with rows as (
	select cfi_estatus from smn_inventario.smn_conteo_inventario_fisico
	where smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id=${fld:id}
)
update smn_inventario.smn_conteo_inventario_fisico set cfi_estatus='AP' from rows
WHERE smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id=${fld:id}