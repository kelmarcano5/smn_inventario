insert into ${schema}s_login_failed  
(
	login_failed_id,
	userlogin,
	login_date,
	login_time,
	remote_addr,
	context
)
values 
(
	${seq:nextval@${schema}seq_login_failed},
	${fld:userlogin},
	{d '${def:date}'},
	'${def:time}',
	'${def:remoteaddr}',
	'${def:alias}'
)
