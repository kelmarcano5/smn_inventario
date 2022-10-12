with rows as (
	select smn_inventario.smn_valoracion_conteo_fisico.* from smn_inventario.smn_valoracion_conteo_fisico
	where smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id=${fld:conteo} and smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='AP'
)
update smn_inventario.smn_valoracion_conteo_fisico set vcf_estatus='CE' from rows
WHERE smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id=${fld:conteo} and smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_diferencia>0;
