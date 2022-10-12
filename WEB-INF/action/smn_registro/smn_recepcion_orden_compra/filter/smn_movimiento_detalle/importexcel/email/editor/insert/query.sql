select
	smn_base.smn_contactos.smn_contactos_id,
	smn_base.smn_contactos.cnt_nombres,
	smn_base.smn_contactos.cnt_apellidos,
	smn_base.smn_contactos.cnt_email 
from
	smn_base.smn_contactos
where
	smn_base.smn_contactos.smn_contactos_id in ${lst:smn_contactos_id@query-smn_contactos.sql}
and
	smn_base.smn_contactos.cnt__usuario = '${def:user}'

