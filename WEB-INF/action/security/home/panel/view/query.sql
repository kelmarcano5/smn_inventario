select distinct
	s.service_id,
	s.description,
	s.path,
	p.icon_path,
	p.orden
from
	${schema}s_service_role sr,
	${schema}s_user u,
	${schema}s_user_role ur,
	${schema}s_panel p,
	${schema}s_service s
where
	u.userlogin = '${def:user}'
and
	sr.service_id = s.service_id
and
	ur.role_id = sr.role_id
and
	ur.user_id = u.user_id
and
	s.service_id = p.service_id
and
	p.app_id = (select app_id from ${schema}s_application where app_alias = '${def:alias}')
order by p.orden asc