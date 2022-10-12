select 
	userlogin 
from 
	${schema}s_user 
where 
	userlogin = ${fld:userlogin} and 
	direccion_ip is not null and 
	direccion_ip <> '${def:remoteaddr}'
