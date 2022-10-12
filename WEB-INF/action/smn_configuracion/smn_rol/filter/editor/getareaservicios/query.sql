  select 
smn_base.smn_areas_servicios.smn_areas_servicios_id as id,
smn_base.smn_areas_servicios.ase_codigo||'-'||smn_base.smn_areas_servicios.ase_descripcion as item
  from smn_base.smn_areas_servicios, smn_base.smn_sucursales
   where 		smn_base.smn_sucursales.smn_sucursales_id = smn_base.smn_areas_servicios.ase_sucursal
   and  smn_base.smn_areas_servicios.ase_empresa = ${fld:id2}
   and  smn_base.smn_areas_servicios.ase_sucursal = ${fld:id3}