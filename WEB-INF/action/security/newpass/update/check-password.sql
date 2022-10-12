select hash from ${schema}s_passlog p, ${schema}s_user u
where hash = ${fld:passwd}
and p.user_id = u.user_id
and u.userlogin = '${def:user}'
