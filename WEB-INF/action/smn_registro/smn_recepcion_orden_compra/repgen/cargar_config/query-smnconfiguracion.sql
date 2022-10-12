select(
	select
			smn_base.smn_configuracion.cfg_descripcion
	from
			smn_base.smn_configuracion
	where
			smn_base.smn_configuracion.cfg_actionroot = '${def:actionroot}'
	order by
			to_timestamp(smn_base.smn_configuracion.cfg_fecha_registro || ' ' ||smn_base.smn_configuracion.cfg_hora, 'yyyy-MM-dd HH24:MI:SS') desc
	limit 
			1
) as cfg_descripcion