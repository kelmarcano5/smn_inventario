var almacen='${fld:almacen}';
var documento= '${fld:documento}';
var fecha='${fld:fecha}';

document.getElementById("smn_almacen_rf").value=almacen;
document.getElementById("smn_documento_id").value=documento;
document.getElementById("vcf_fecha_generacion").value=fecha;

document.getElementById("smn_almacen_rf").disabled=true;
document.getElementById("smn_documento_id").disabled=true;
document.getElementById("vcf_fecha_generacion").disabled=true;

document.getElementById("grabar").disabled=false;