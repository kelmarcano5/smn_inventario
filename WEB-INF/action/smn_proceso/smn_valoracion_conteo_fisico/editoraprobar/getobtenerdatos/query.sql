select smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf as almacen, 
smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id as documento, 
smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_generacion as fecha
 
from smn_inventario.smn_valoracion_conteo_fisico
where
smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id=${fld:id} limit 1;

