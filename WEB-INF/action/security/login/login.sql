select
	user_id, 
	userlogin,
	enabled,
	force_newpass,
	pwd_policy,
	locale,
	is_ldap
	
from 
	${schema}s_user
	
where 
	userlogin = ${fld:userlogin}
and 
	passwd = ${fld:passwd}

