select 	l.*, u.userlogin
from 
	${schema}s_user u, ${schema}s_loginlog l
where
	u.user_id = l.user_id
and u.userlogin = '${def:user}'
order by 
	loginlog_id DESC
