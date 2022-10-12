INSERT INTO ${schema}s_session
(
	session_id,
	jsessionid,
	login_date,
	login_time,
	remote_addr,
	context_alias,
	userlogin
)
VALUES
(
	${seq:nextval@${schema}seq_session},
	'${def:session}',
	{d '${def:date}'},
	'${def:time}',
	'${def:remoteaddr}',
	'${def:alias}',
	${fld:userlogin}
)
