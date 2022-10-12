select distinct
	r.rolename
from
	${schema}s_service_role sr,
	${schema}s_user u,
	${schema}s_user_role ur,
	${schema}s_panel p,
	${schema}s_service s,
	${schema}s_role r
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
and
    r.role_id = sr.role_id
and
    r.app_id = p.app_id 
order by 
    r.rolename asc