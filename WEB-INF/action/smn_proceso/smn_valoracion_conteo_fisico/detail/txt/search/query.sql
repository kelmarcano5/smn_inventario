select
select
select
select
select
select
select
select
	case
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='VL' then '${lbl:b_valoration}'
	end as vcf_estatus,
	smn_inventario.smn_valoracion_conteo_fisico.*
from
	smn_inventario.smn_valoracion_conteo_fisico
where
	smn_valoracion_conteo_fisico_id = ${fld:id}
