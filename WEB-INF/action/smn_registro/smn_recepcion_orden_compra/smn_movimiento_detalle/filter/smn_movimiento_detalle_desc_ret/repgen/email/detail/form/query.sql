select
	*
from 
	smn_base.smn_mensajes
where
	smn_base.smn_mensajes.smn_mensajes_id = ${fld:id}
and
	smn_base.smn_mensajes.msj_usuario = '${def:user}'
