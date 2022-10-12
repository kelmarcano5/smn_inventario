select distinct
	p.pan_service_description as description,
	substring('${def:actionroot}',1,7) || p.pan_service_path as path,
	substring(p.pan_service_path,2,length(p.pan_service_path)-6) as action_service,
	p.pan_icon_path as icon_path, 
	p.pan_orden as orden
from
	smn_base.smn_panel p,
	${schema}s_user u,
	${schema}s_user_role ur
where
	u.userlogin = '${def:user}'
and
	ur.role_id = p.pan_role_id
and
	ur.user_id = u.user_id
and
	p.pan_app_id = (select 
					${schema}s_application.app_id 
				from 
					${schema}s_application 
				where 
					${schema}s_application.app_alias = '${def:alias}')
and 
    (upper(p.pan_service_description) <> 'LISTAR' and
     upper(p.pan_service_description) <> 'COPIAR')
order by 
	p.pan_orden asc