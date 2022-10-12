select 
	userlogin 
from 
	${schema}s_session
where 
	userlogin = ${fld:userlogin} and 
	context_alias = '${def:alias}'