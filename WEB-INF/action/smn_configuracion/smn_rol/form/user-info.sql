select
	*
from
	${schema}s_user
where
	${schema}s_user.userlogin = '${def:user}'
