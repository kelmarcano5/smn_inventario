insert into ${schema}s_loginlog  
(
	loginlog_id,
	login_date,
	login_time,
	remote_addr,
	context,
	user_id
)
values 
(
	${seq:nextval@${schema}seq_loginlog},
	{d '${def:date}'},
	'${def:time}',
	'${def:remoteaddr}',
	'${def:alias}',
	${fld:user_id}
)
