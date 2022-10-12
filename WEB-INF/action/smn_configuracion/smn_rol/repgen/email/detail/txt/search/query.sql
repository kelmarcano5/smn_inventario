select	
	smn_base.smn_mensajes.msj_asunto,
	smn_base.smn_mensajes.msj_texto,
	smn_base.smn_mensajes.msj_fecha_registro,
	smn_base.smn_mensajes.msj_hora,
	smn_base.smn_mensajes.smn_mensajes_id 
from
	smn_base.smn_mensajes
where
	smn_mensajes_id is not null	
and
	smn_base.smn_mensajes.msj_usuario = '${def:user}'
order by 
	smn_mensajes_id