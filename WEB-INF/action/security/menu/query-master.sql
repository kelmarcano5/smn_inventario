select distinct
	m.title,
	m.menu_id,
	m.position
from
	${schema}s_menu m,
	${schema}s_menu_role mr,
	${schema}s_user u,
	${schema}s_user_role ur,
	${schema}s_application a,
	${schema}s_role r
where
	a.app_alias = '${req:dinamica.security.application}'
and
	u.userlogin = '${def:user}'
and
	r.app_id = a.app_id
and
	ur.role_id = r.role_id
and
	ur.user_id = u.user_id
and
	mr.role_id = ur.role_id
and
	m.menu_id = mr.menu_id
	
order by 
	m.position
