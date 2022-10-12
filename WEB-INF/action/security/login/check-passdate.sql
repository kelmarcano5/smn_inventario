select last_change from 
	${schema}s_passlog p, 
	${schema}s_user u
where p.user_id = u.user_id
and u.userlogin = ${fld:userlogin}
order by last_change DESC
