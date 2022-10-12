update ${schema}s_user 
set 
	passwd=${fld:passwd},
	force_newpass=1 

where userlogin = ${fld:userlogin2}

