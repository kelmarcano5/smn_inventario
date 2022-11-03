//alertBox ('Su conteo fisico ha sido aprobado', '${lbl:b_continue_button}', null, null);
alertBox('Su valorizacion a sido aprobada! Desea generar el movimiento?', '${lbl:b_yes}', '${lbl:b_not}', 'generarOk();')
//alert("conteo: "+conteo+" almacen: "+almacen+" documento:"+documento+"&fecha: "+fecha);
// var almacen='${fld:almacen}';
// var documento= '${fld:documento}';
// var fecha='${fld:fecha}';
// var conteo='${fld:id}';
// //alert("conteo: "+conteo+" almacen: "+almacen+" documento:"+documento+"&fecha: "+fecha);

// setTimeout(function(){
//    generar(conteo, almacen, documento, fecha);
// }, 2000);

// function generar(conteo, almacen, documento, fecha){
// 	uri = "${def:context}${def:actionroot}/procesar?conteo="+ conteo+"&almacen="+almacen+"&documento="+documento+"&fecha="+fecha; 
// 	//alert(uri);
// 	openDialog("dialog", uri, 350, 100);
// }

