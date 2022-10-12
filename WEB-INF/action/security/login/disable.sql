update ${schema}s_user set enabled = 0
where userlogin = '${req:userlogin}'
