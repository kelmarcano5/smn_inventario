select * from ${schema}s_loginlog 
where user_id = ${fld:user_id}
order by login_date desc, login_time desc

