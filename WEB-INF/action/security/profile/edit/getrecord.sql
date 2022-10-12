select	user_id, userlogin, 
		email, lname, fname, enabled,
		force_newpass, pwd_policy, locale
from ${schema}s_user
where userlogin = '${def:user}'
