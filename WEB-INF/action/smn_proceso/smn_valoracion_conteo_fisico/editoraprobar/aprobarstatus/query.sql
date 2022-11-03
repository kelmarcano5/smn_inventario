with RECURSIVE rows2 as (
	select smn_inventario.smn_valoracion_conteo_fisico.* from smn_inventario.smn_valoracion_conteo_fisico
	where smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id=${fld:id} 
)
update smn_inventario.smn_valoracion_conteo_fisico set vcf_estatus='AP' from rows2
WHERE smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id=${fld:id} and smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_diferencia>0 
and smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='VL';

