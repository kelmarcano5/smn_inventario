select	
	*
from ${schema}s_user
where userlogin = '${def:user}' and
	  is_ldap = 1