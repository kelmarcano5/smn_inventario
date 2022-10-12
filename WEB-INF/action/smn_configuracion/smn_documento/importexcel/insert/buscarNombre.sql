select
		smn_inventario.smn_documento.doc_nombre
from
		smn_inventario.smn_documento
where
		upper(smn_inventario.smn_documento.doc_nombre) = upper(${fld:doc_nombre})
