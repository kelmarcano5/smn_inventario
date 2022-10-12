select
	smn_base.smn_respuestas.*
from 
	smn_base.smn_respuestas,
	smn_base.smn_mensajes
where
	smn_base.smn_respuestas.smn_mensajes_id=smn_base.smn_mensajes.smn_mensajes_id 
and 
	smn_base.smn_mensajes.smn_mensajes_id=${fld:id}
and
	smn_base.smn_mensajes.msj_usuario = '${def:user}'
and
	smn_base.smn_respuestas.rsp_usuario = '${def:user}'
