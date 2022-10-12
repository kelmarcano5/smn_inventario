select
	user_id, 
	userlogin,
	enabled,
	0 as force_newpass,
	-1 as pwd_policy,
	locale,
	is_ldap,
	dn
	
from 
	${schema}s_user
	
where 
	userlogin = ${fld:userlogin}
and 
	is_ldap = 1