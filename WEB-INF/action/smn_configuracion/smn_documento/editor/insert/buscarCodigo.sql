select
		smn_inventario.smn_documento.doc_codigo
from
		smn_inventario.smn_documento
where
		upper(smn_inventario.smn_documento.doc_codigo) = upper(${fld:doc_codigo})
