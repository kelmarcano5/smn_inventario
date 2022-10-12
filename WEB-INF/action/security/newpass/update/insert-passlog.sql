insert into ${schema}s_passlog  
(
	passlog_id,
	last_change,
	hash,
	user_id
)
values 
(
	${seq:nextval@${schema}seq_passlog},
	{d '${def:date}'},
	${fld:passwd},
	${fld:userid}
)
