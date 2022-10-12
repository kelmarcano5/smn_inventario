//dinamica.js - patch 2015-03-06

//codigo de estatus de ajax
var S_READY = 4;

//separadores de decimales y miles usados por el servidor
var _server_dsep = "${def:dsep}";
var _server_tsep = "${def:tsep}";

/**
* Crear objeto XmlHttpRequest de manera
* portable entre browsers IE y FF. Retorna
* una referencia al objeto listo para usarse
* en comunicacion Ajax
*/
function getHttpObject()
{
	var obj = null;
	if (window.XMLHttpRequest) {
		obj = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		obj = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return obj;
}


/**
* Invocar servicio Ajax
* httpMethod: String GET o POST
* uri: Path del servicio, no incluir el contexto. Ejemplo: /action/xxxx/yyyy
* divResponse: ID del DIV donde se muestra la respuesta en caso de que sea HTML
* divProgress: ID del DIV a mostrar mientras se ejecuta la llamada
* formName: Nombre del formulario en caso que httpMethod sea igual a POST
* 			2014-06-23: Permite añadir mas de un formulario separado por punto y coma (;) Ejemplo: formName="form3;form5;form6"
* afterResponseFn: Apuntador a funcion a ejecutar despues de mostrar una respuesta HTML
* onErrorFn: Apuntador a funcion a ejecutar en caso de error http 500, 403, 404, 401, etc.
*            Puede utilizarse para restaurar los DIVs que se escondieron antes de invocar
*            esta funcion.
*/
function ajaxCall(httpMethod, uri, divResponse, divProgress, formName, afterResponseFn, onErrorFn)
{

		//mostrar indicador de progreso
		var progress = document.getElementById(divProgress);
		if (progress!=null)
			progress.style.display='';

		//ensamblar el URL
		var server = "${def:httpserver}";
		var url = server + "${def:context}" + uri;
		if (url.indexOf("?")>0)
			url = url + "&random=" + Math.random();
		else
			url = url + "?random=" + Math.random();
		
		//ensamblar formulario
		var data = null;
		if (formName!=null) {
			//mas de un formulario? 2014-06-23
			if(formName.indexOf(";")!=-1) {
				var fn = formName.split(";");
				for (var i = 0; i < fn.length; i++) {
					data = data + "&" + getFormValues(fn[i]);
				}	
			} else	
				data = getFormValues(formName);
		}
		
		//send request
		var http = getHttpObject();
		
		//manejador de respuesta AJAX
		http.onreadystatechange = function ()
		{
			if (http.readyState == S_READY) 
			{
			
				var contentType = http.getResponseHeader("Content-type");
				var text = http.responseText;
		
				//verificacion de sesion de seguridad activa (redirect a login)
				if (text.indexOf("_ajax_VE8374yz_")>0) 
				{
						window.location="${def:context}/index.htm";
				} 
				else 
				{
		
					//apagar indicador de progreso
					if (progress!=null)
						progress.style.display='none';
					
					//verificacion de error y seguridad
					if (http.status == 500) 
						alert("Error en el sistema.");
					else if (http.status == 403)
						alert("Acceso denegado.");
					else if (http.status == 404)
						alert("El servicio solicitado no existe.");
					else if (http.status == 401)
						alert("El servicio solicitado requiere comunicación vía SSL.");
					else 
					{

						//tratar caso cuando no hay comunicacion con el servidor
						if (contentType==null || (http.status !=200 && http.status!=400)) {
							alert("El servidor no responde.");
							//invocar callback en caso de error
							if (onErrorFn!=null)
								onErrorFn();
							return false;
						}

						//ok - responder de acuerdo al tipo de contenido
						if( contentType.indexOf("text/javascript")>=0 ) 
						{
							eval(text);
						} 
						else 
						{
							if (divResponse!=null) {
								var div = document.getElementById(divResponse);
								div.innerHTML = text;
								div.style.display='';
							}
							if (http.status == 200 && afterResponseFn!=null)
								afterResponseFn();
						}
					}
					
					//invocar callback en caso de error
					if (http.status != 200) {
						if (onErrorFn!=null)
							onErrorFn();
					}
					
				}
		   	}
		}; 
		
    	http.open(httpMethod, url, true);
    	http.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
    	http.send(data);

		return false;

}

/**
* Limpiar los controles de un formulario,
* los comboBox se ubican en el 1er elemento, el
* atributo value del resto de los controles se
* pone en "" vacio. El focus se pone en el 1er
* control activo.
* formName: nombre del formulario
*/
function clearForm(formName)
{
	var f = document.forms[formName];
	for (var i=0;i<f.elements.length;i++)
	{

		var e = f.elements[i];
		if (e.type=="text" || e.type=="hidden" || e.tagName=="TEXTAREA" || e.type=="date")
		{
			e.value = "";
		}
		if (e.tagName=="SELECT")
		{
			e.options.selectedIndex = 0;
		}
		if (e.type=="checkbox")
		{
			e.checked = false;
		}
		if (e.type=="radio")
		{
			e.checked = false;
		}		
	}
	
	clearErrorMessages();
	
}

/**
* Limpiar mensajes de error en los campos
*/
function clearErrorMessages() {
	var divs = getElementsByClass("errormsg");
	for (var i=0;i<divs.length;i++) {
		divs[i].style.display="none";
		divs[i].innerHTML = "";
	}
}

/**
* Poner el focus en el 1er control
* activo de un formulario.
* formName: Nombre del formulario
*/
function setFocusOnForm(formName)
{
	var f = document.forms[formName];
	var n = f.elements.length;
	for (var i=0;i<n;i++) {
		var e = f.elements[i];
		if (e.readOnly == false && e.type!="hidden") 
		{
			e.focus();
			return;
		}
	}
}

/**
* Posicionar un DIV justo debajo de un 
* control de formulario, alineado a la
* izquierda.
* element: objeto que representa al control
* box: objeto que representa al DIV
*/
function moveBox(element, box) 
{

	/* 
	NOTE:
	Original author: JTricks.com :: http://www.jtricks.com/ 
	*/

	var offset = 3;
    var cleft = 0;
	var ctop = 0;
	var obj = element;
	  while (obj.offsetParent) {
	    cleft += obj.offsetLeft;
	    ctop += obj.offsetTop;
	    obj = obj.offsetParent;
	  }
	  box.style.left = cleft + 'px';
	  ctop += element.offsetHeight + offset;
	  if (document.body.currentStyle &&
	    document.body.currentStyle['marginTop']) {
	    ctop += parseInt(
	      document.body.currentStyle['marginTop']);
	  }
	  box.style.top = ctop + 'px';

}

/**
 * Evento que se dispara al cambiar las dimensiones
 * de la ventana del browser.
 */
window.onresize = function()
{
	reloadBox();
};

/**
 * Ajusta la ubicacion de caja de dialogo como
 * por ejemplo un picklist cuando se redimensiona
 * la ventana del browser, tambien ajusta la altura
 * del lightbox. 
 */
function reloadBox()
{
	//picklist estandar
	if(pickControl!=null) {
		//si esta centrado
		if(isCenter!=null) {
			var boxdiv = document.getElementById(pickControl + "_popup");
			var height = boxdiv.style.height;
			var width = boxdiv.style.width;
			boxdiv.style.top = (pageHeight() - parseInt(height)) / 2 + 'px';
			boxdiv.style.left = (pageWidth() - parseInt(width)) / 2 + 'px';
		} else {
			//si no esta centrado
			var obj = document.getElementById(pickControl);
			var boxdiv = document.getElementById(pickControl + "_popup");
			moveBox(obj, boxdiv);
		}
	}
	
	//caja de dialogo centrada
	if(dialogID!=null) {
		var boxdiv = document.getElementById(dialogID + "_popup");
		var height = boxdiv.style.height;
		var width = boxdiv.style.width;
		boxdiv.style.top = (pageHeight() - parseInt(height)) / 2 + 'px';
		boxdiv.style.left = (pageWidth() - parseInt(width)) / 2 + 'px';
	}
	
	//lightbox para ambos casos
	var overlay = document.getElementById('_overlay');
	if (overlay!=null) {
		 if (document.body.scrollHeight > pageHeight())
			  overlay.style.height = document.body.scrollHeight + 'px';
		  else
			  overlay.style.height = pageHeight() + 'px';
	}
		
}

/**
* Crear un DIV que contenga un IFRAME
* y posicionar el DIV con su contenido
* abajo y alineado a la izquierda del
* elemento cuyo ID es el valor del parámetro objID
* objID: ID del DIV que sera creado o inyectado el IFRAME
* width: Ancho en pixeles del DIV
* height: Altura en pixeles del DIV
* centerPage: Variable String "true|false" que determina si el DIV sera colocado
* debajo de un elemento del HTML o centrado en la pagina HTML,
* si es nulo (por defecto) quiere decir que sera colocado debajo del un elemento,
* si es true sera centrado en la pagina
* scrollActive: Variable String "true|false" que determina si se activan scrollbars en el iframe, por defecto es false
*/		
function showBox(objID, width, height, url, centerPage, scrollActive, html) 
{

	  //patch 2013-02-27
	  if (scrollActive==null)
		  scrollActive=false;
	  if (centerPage==null)
		  centerPage=false;
	
	  /* soporte para lightbox */
	  var overlay = document.getElementById('_overlay');
	  if (overlay==null) {
	  	overlay = document.createElement("div");
	  	overlay.className = "overlay";
	  	
	  	if (document.body.scrollHeight > pageHeight()) 
	  		overlay.style.height = document.body.scrollHeight + 'px';
	  	else
	  		overlay.style.height = pageHeight() + 'px';
	  	
	  	overlay.setAttribute("id", "_overlay");
	  	document.body.appendChild(overlay);
	  } else {
		  if (document.body.scrollHeight > pageHeight())
			  overlay.style.height = document.body.scrollHeight + 'px';
		  else
			  overlay.style.height = pageHeight() + 'px';
	  }
	  /* ---- */

	/* 
	NOTE:
	Original author: JTricks.com :: http://www.jtricks.com/ 
	*/

	var obj = document.getElementById(objID);
	var newID = objID + "_popup";
	//var borderStyle = "#e1e1e1 1px solid";
	var boxdiv = document.getElementById(newID);
	
	  if (boxdiv != null) {
	  
		  /* soporte para lightbox */
		  overlay.style.display='block';
		  boxdiv.style.zIndex = 1002;
		  /* ---- */
	  
	    if (boxdiv.style.display=='none') {
	    	if (url!=null) 
	    	{
	    		var f = window.frames[objID + "_iframe"];
	    		f.document.open();
    			f.document.write("<center>");
    			f.document.write("<br><br>");
    			f.document.write("<img src='${def:context}/images/progress.gif'>");
	    		f.document.write("<span style='padding-left:10px;font-family:Arial;font-size:9pt;font-weight:normal'>Cargando...</span>");
    			f.document.write("</center>");
	    		f.document.close();
    			f.location = url;
	      	}
	    	
	  	  //patch 2013-02-27
	  	  if (html!=null) {
	  		  var f = window.frames[objID + "_iframe"];
	  		  f.document.open();
	  		  f.document.write(html);
	  		  f.document.close();	    		
	  	  }	    	
	    	
	    	if (centerPage==false)
	    		moveBox(obj, boxdiv);
	    	
	    	boxdiv.style.display='block';
	    	
	    	//parche 20111029
	    	if (document.body.scrollWidth > pageWidth()) {
	    		overlay.style.width = document.body.scrollWidth + 'px';
	    	}
  
	    	if (document.body.scrollHeight > pageHeight()) {
	    		overlay.style.height = document.body.scrollHeight + 'px';
	    	}
	    	
	    } else
	    	boxdiv.style.display='none';
	    return false;
	  }
	
	  boxdiv = document.createElement('div');
	  boxdiv.className = "dialogbox";
	  
	  boxdiv.setAttribute('id', newID);
	  boxdiv.style.display = 'none';
	  boxdiv.style.position = 'absolute';
	  boxdiv.style.width = width + 'px';
	  boxdiv.style.height = height + 'px';
	  //boxdiv.style.border = borderStyle;
	  //boxdiv.style.backgroundColor = '#F7F7F7';
	  boxdiv.style.backgroundColor = 'white';
	  boxdiv.style.padding = "0px";
	  
	  //si se desea que el DIV este alineado en el centro de la pagina
	  if (centerPage==true) {
	   boxdiv.style.top =65 +'px';
		 // boxdiv.style.top = (pageHeight() - height) / 2 + 'px';
		  boxdiv.style.left = (pageWidth() - width) / 2 + 'px';
	  }
	  
	  contents = document.createElement('iframe');
	  if (scrollActive==true)
		  contents.scrolling = 'yes';
	  else
		  contents.scrolling = 'no';
	  
	  contents.style.overflowX = 'auto';
	  contents.style.overflowY = 'auto';
	  
	  contents.frameBorder = '0';
	  contents.style.width = width + 'px';
	  contents.style.height = height + 'px';
	  contents.marginHeight = 0;
	  contents.marginWidth = 0;
	  contents.setAttribute('id', objID + "_iframe");
	  contents.setAttribute('name', objID + "_iframe");
	  
	  boxdiv.appendChild(contents);
	  document.body.appendChild(boxdiv);

	  if (url!=null) {
	  		var f = window.frames[objID + "_iframe"];
			f.document.open();
			f.document.write("<center>");
			f.document.write("<br><br>");
			f.document.write("<img src='${def:context}/images/progress.gif'>");
			f.document.write("<span style='padding-left:10px;font-family:Arial;font-size:9pt;font-weight:bold'>Loading...</span>");
			f.document.write("</center>");
			f.document.close();
			f.location = url;
	  }

	  //patch 2013-02-27
	  if (html!=null) {
		  var f = window.frames[objID + "_iframe"];
		  f.document.open();
		  f.document.write(html);
		  f.document.close();	    		
	  }
	  
	  /* soporte para lightbox */
	  overlay.style.display='block';
	  boxdiv.style.zIndex = 1002;
	  /* ---- */
	  
	  if (centerPage==false)
		  moveBox(obj, boxdiv);
	  
	  boxdiv.style.display = 'block';
	  
	  //parche 20111029
	  if (document.body.scrollWidth > pageWidth()) {
		  overlay.style.width = document.body.scrollWidth + 'px';
	  }
	  
	  if (document.body.scrollHeight > pageHeight()) {
		  overlay.style.height = document.body.scrollHeight + 'px';
	  }
	  
	  return false;
	  
}	

/**
* Esconde un DIV + IFRAME abierto con la
* función showBox()
* objID: ID del DIV que sera ocultado
*/
function closeBox(objID)
{
	/*var obj = document.getElementById(objID);
	if (!obj.readonly)
		obj.focus();*/
		
	document.getElementById(objID + "_popup").style.display = "none";
	
	/* soporte para lightbox */
	var overlay = document.getElementById('_overlay');
	if (overlay!=null)
		overlay.style.display='none';
	
}

/**
* Retorna TRUE si el navegador
* es Internet Explorer
*/
function isIE() {
	if (navigator.appName.indexOf("Explorer")>0)
		return true;
	else
		return false;
}

/**
* Retorna TRUE si el valor representa una fecha
* de lo contrario retorna FALSE. Si el año viene con 2 dígitos
* y en menor que 30, se asume el 20XX, de lo contrario 19XX.
* value: String que representa una fecha con formato dd-mm-yyyy o dd/mm/yyyy
*/
function isValidDate(value)
{

	var d = convertDate(value);
	if (d==null)
		return false;
	else
		return true;
}

/**
* Retorna un objeto DATE si el valor representa una fecha
* de lo contrario retorna NULL. Si el año viene con 2 dígitos
* y es menor que 30, se asume el 20XX, de lo contrario 19XX.
* value: String que representa una fecha con formato dd-mm-yyyy o dd/mm/yyyy
*/
function convertDate(value)
{

	value = value.replace(new RegExp("/", "g") ,"-");
	var values = value.split("-");
	
	if (values.length!=3)
		return null;
	
	var day = values[0];
	var month = values[1];
	var year = values[2];

	if (year.length!=2 && year.length!=4)
		return null;

	if (year.length==2 && parseInt(year)<30)
		year = "20" + year;
	else if (year.length==2 && parseInt(year)>=30)
		year = "19" + year;

	month = month-1;

	var dteDate = new Date(year,month,day);
	
	var r = ((day==dteDate.getDate()) && (month==dteDate.getMonth()) && (year==dteDate.getFullYear()));
		
	if (r)
	 return dteDate;
	else
		return null;

}

/**
* Mostrar un Calendario debajo y alineado
* a la izquierda del elemento designado
* por el parametro objID. El elemento debe
* ser un textbox.
* objID: ID del elemento en donde sera colocada la fecha seleccionada
* lboundID: ID del elemento que contiene una fecha menor que sera usada para
* no permitir seleccionar fechas menores en el calendario a la fecha en el elemento objID,
* permite nulos cuando no se requiere.
* uboundID: ID del elemento que contiene una fecha mayor que sera usada para
* no permitir seleccionar fechas mayores en el calendario a la fecha en el elemento objID,
* permite nulos cuando no se requiere.
* isMovil: true para mostrar centrado en la pantalla del dispositivo 
* calUrl: Ruta del action por si se quiere usar un calendario alternativo al default. Ejem: /action/mycal
*/
function calendarOpen(objID, lboundID, uboundID, isMovil, calUrl)
{
	
	pickControl = objID;
	
	var d = document.getElementById(objID).value;
	var url = "${def:context}/action/calendar?id=" + objID + "&date=" + d;
	
	if (calUrl!=null)
		url = "${def:context}" + calUrl + "?id=" + objID + "&date=" + d;
	
	if (lboundID != null)
		url = url + "&date.lbound=" + document.getElementById(lboundID).value;
	if (uboundID != null)
		url = url + "&date.ubound=" + document.getElementById(uboundID).value;
	
	if ( isMovil!=null )
		showBox(objID, 240,154, url, true);
	else
		showBox(objID, 240 ,154, url, false, false);
}

/**
* Retorna valor seleccionado en el calendario
* poniendolo dentro del textbox asociado y 
* cierra el calendario
* objID: ID del textbox asociado al calendario
* sdate: String que representa una fecha en formato dd-mm-yyyy
*/
function calendarReturnValue(objID, sdate)
{
	document.getElementById(objID + "_popup").style.display = "none";
	document.getElementById(objID).value = sdate;
	
	/* soporte para lightbox */
	var overlay = document.getElementById('_overlay');
	if (overlay!=null)
		overlay.style.display='none';	
	
	return false;
}

/**
* Filtrar caracteres no permitidos en campos fecha, se usa
* de esta manera: document.forms1.text1.onkeypress = keypressDate
* en el evento onload del body.
*/
function keypressDate(e) 
{ 
	var mask = "0123456789-";
	var keyCode = e ? e.which : window.event.keyCode;
	var key = String.fromCharCode(keyCode);
	if (mask.indexOf(key)==-1 && keyCode != 8 && keyCode!=0 && keyCode!=13)
		return false;
}

/**
* Filtrar caracteres no permitidos en campos enteros, se usa
* de esta manera: document.forms1.text1.onkeypress = keypressInteger
* en el evento onload del body.
*/
function keypressInteger(e) 
{ 
	var mask = "0123456789";
	var keyCode = e ? e.which : window.event.keyCode;
	var key = String.fromCharCode(keyCode);
	if (mask.indexOf(key)==-1 && keyCode != 8 && keyCode!=0 && keyCode!=13)
		return false;
} 

/**
* Filtrar caracteres no permitidos en campos con decimales, se usa
* de esta manera: document.forms1.text1.onkeypress = keypressDouble
* en el evento onload del body. Solo acepta el punto "." como separador
* de decimales.
*/
function keypressDouble(e) 
{ 
	var mask = "0123456789.";
	var keyCode = e ? e.which : window.event.keyCode;
	var elem = e ? e.target : window.event.srcElement;
	var key = String.fromCharCode(keyCode);
	if (mask.indexOf(key)==-1 && keyCode != 8 && keyCode!=0 && keyCode!=13)
		return false;
		
	if (key=="." && elem.value.indexOf(".") > -1)
		return false;	
} 

/**
* Retorna la trama requerida para hacer un POST del
* formulario indicado usando Ajax
* formName: Nombre del formulario 
*/
function getFormValues(formName)
{

	returnString ="";
	
	if( formName == "" ) return returnString;
	
	formElements=document.forms[formName].elements;
	
	for ( var i=formElements.length-1; i>=0; --i ) {
		if (formElements[i].name!=""){ 
			//añade elementos radio y checkbox
			if (formElements[i].type=="checkbox" && !formElements[i].checked){
			//este checkbox si no ha sido seleccionado es ignorado
			}else{
				if (formElements[i].type=="radio" && !formElements[i].checked){
				//este radio si no ha sido seleccionado es ignorado
				}
				else {
					var value = formElements[i].value;
					
					//NumericInput control? quitar formato de mascara antes de postear
					if (formElements[i].alt!=null && formElements[i].alt=="numeric") {
						value = value.replace(/\./g ,"");
						value = value.replace(/,/g ,".");
					}
					
					//chrome y safari dispositivo movil 
					if (formElements[i].type!=null && formElements[i].type=="date" && value!=null && value!="") {
						var s = value.split("-");
						var d = s[2]+'-'+s[1]+'-'+s[0];
						value = d;
					}
					
					value = encodeURI(value);
					value = value.replace(new RegExp("&", "g") ,"%26");
					
					//patch 2013-01-24
					value = value.replace(new RegExp("\\+", "g") ,"%2B");
					
					returnString = returnString + formElements[i].name + "=" + value + "&";
				}
			}
		}
	}

	returnString = returnString.substring(0, returnString.length - 1);
	return returnString;

}

/**
* Retorna checkboxes o radiobutton con la propiedad
* checked = true
* radioName: Nombre del checkbox o radiobutton
* radioValue: Valor del checkbox o radiobutton
* formName: Nombre del formulario 
*/
function setCheckboxValue(radioName,radioValue,formName)
{
	if( formName == "") { 
		//si forName es nulo no hace nada
		return;
	}else{
		formElements=document.forms[formName].elements;
	}
	for ( var i=formElements.length-1; i>=0; --i ) {
		if (formElements[i].name == radioName && (formElements[i].type=="radio" || formElements[i].type=="checkbox") ){ 
			//añade elementos radio y checkbox
			if (formElements[i].value==radioValue){
			formElements[i].checked = true;
			return;
			}
		}
	}
}

/**
* Retorna combos con la propiedad
* select = true
* comboName: Nombre del combo
* comboValue: Valor del combo
* formName: Nombre del formulario 
*/
function setComboValue(comboName,comboValue,formName)
{	   
   var combo = document.forms[formName].elements[comboName];
   var cantidad = combo.length;
   for (var i = 0; i < cantidad; i++) {
      if (combo[i].value == comboValue) {
         combo[i].selected = true;
      }
    }   
}

/**
 * Remplazar el valor "," por "." en un elemento que contiene
 * un valor de tipo decimal. 
 * elem: Elemento que contiene el valor a ser remplazado
 */
function replaceDecimal(elem)
{
	var value = elem.value;
	elem.value = value.replace(new RegExp(",", "g") ,".");
}

/**
* Funciones de soporte para los grid
* contiene funciones para la vista pagina, 
* panel de control, regresar a la pagina indicada,
* y ordenamiento por columnas.
*/
	
//variables de control
var lastPage=0;
var currentPage=0;
var recordsFound=0;

/**
 * Ir a la primera pagina 
 */
function pageFirst()
{
	currentPage = 1;
	viewPage();
}

/**
 * Ir a la ultima pagina
 */
function pageLast()
{
	currentPage = lastPage;
	viewPage();
}

/**
 * Ir a la pagina anterior
 */
function pagePrev()
{
	if (currentPage>1) {
		currentPage=currentPage - 1;
		viewPage();
	}
}

/**
 * Ir a la pagina siguiente
 */
function pageNext()
{
	if (currentPage<lastPage) {
		currentPage=currentPage + 1;
		viewPage();
	}
}
	
/**
 * Ir a la pagina indicada por la variable currentPage
 * url: URI donde se mostrara la vista pagina 
 */
function gotoPage(url)
{
	//llamada Ajax...
	ajaxCall(httpMethod="GET", 
					uri= url + "?pagenumber=" + currentPage, 
					divResponse="response", 
					divProgress="grid-progress", 
					formName=null, 
					afterResponseFn=showCurrentPage, 
					onErrorFn=null);    	
}
	
/**
 * Mostrar en la pagina de navegacion la pagina actual
 */
function showCurrentPage()
{
	document.getElementById('pagecount').innerHTML=currentPage + " de " + lastPage;
	document.getElementById('recordcount').innerHTML=recordsFound  + " filas";
}
	
/**
 * Ordenar el grid por la columna indicada
 * recordsetId: ID del recordset almacenado en sesion en el servidor
 * colName: columna del recordset por la cual se hara el sort
 */
function sortBy(colName, recordsetId)
{
	if (recordsetId == null)
		recordsetId = "query.sql";
		
	//llamada Ajax...
	ajaxCall(httpMethod="GET", 
					uri="/action/sort?rs=" + recordsetId + "&colname=" + colName, 
					divResponse="response", 
					divProgress="grid-progress", 
					formName=null, 
					afterResponseFn=showCurrentPage, 
					onErrorFn=null);    	
}	

/** 
 * Funciones de soporte para picklist 
 */

//variables de control
var pickControl = null;
var idControl = null;
var isCenter = null;
/**
 * Abre un picklist DIV + IFRAME
 * id: ID del elemento donde sera posicionado el picklist, y sera llenado 
 * el valor de tipo PK (comunmente) seleccionado en el picklist  
 * idCtl: ID del elemento en donde sera llenado el texto seleccionado en el picklist
 * url: URL en donde se encuentra el action que sera mostrado como picklist 
 * ancho: Ancho del picklist 
 * altura: Altura del piklist
 * isMovil: true para mostrar centrado en la pantalla del dispositivo
 */
function pickOpen(id, idCtl, url, ancho, altura, isMovil)
{	
	if (pickControl!=null)
			pickClose();
			
	//verifica si existe el textbox
	var obj = document.getElementById(id);
	if (obj==null) {
		alert("ERROR (pickOpen): no existe un elemento con ID: " + id);
		return;			
	}

	pickControl = id;
	idControl = idCtl;
	
	if (isMovil != null) {
		isCenter = 'true';
		//mostrar popup dhtml
		showBox(pickControl, ancho, altura, url, true, true);
	} else
		//mostrar popup dhtml
		showBox(pickControl, ancho, altura, url);
}			

/**
 * Rellena los elementos de la vista HTML padre con
 * los valores seleccionados en el picklist 
 * @param id Valor comunmente de tipo PK seleccionado en el picklist
 * @param description Valor comunmente de tipo texto seleccionado en el picklist
 * @return
 */
function pickSelect(id, description)
{
	document.getElementById(idControl).value = id;
	document.getElementById(pickControl).value = description;
	if (document.getElementById(pickControl).onchange)
		document.getElementById(pickControl).onchange();
}

/**
 * Oculta o cierra el picklist 
 */
function pickClose()
{
	closeBox(pickControl);
	idControl = null;
	pickControl = null;
}	

/**
 * Obtener el nodo a partir de el nombre del control
 * node: Nodo
 * name: Nombre del control
 */
function getNodeByName(node, name) {
	for (var i=0;i<node.childNodes.length;i++) {
		if (node.childNodes[i].name==name)
			return node.childNodes[i];
	}
}

/** 
 * soporte para listboxes 
 */

/**
 * Añadir un item a un listbox
 * elementId: ID del control que representa al ListBox
 * itemId: ID del item
 * itemTitle: Texto del item
 * Notas: no permite añadir items con ID duplicado
*/ 
function listboxAddItem(elementId, itemId, itemTitle) {
	
	//obtener el control listbox
	var lbox = document.getElementById(elementId);
	if (lbox==null) {
		alert("ERROR (listboxAddItem): no existe un elemento con ID: " + elementId);
		return;
	}
		
	//crear el item
	var option = document.createElement('option');
	option.value = itemId;
	option.text = itemTitle;
	
	//este item ya esta en el listbox?
	for (var i = 0; i < lbox.length; i++) {
      if (lbox[i].value == itemId) {
         alert("Este item ya fue seleccionado.");
         return;
      }
    } 	
	
	//añadir item
	if (isIE())
		lbox.add(option);
	else
		lbox.add(option, null);
}

/**
 * Remover un item a un listbox
 * elementId: ID del control que representa al ListBox
 * Nota: solo removera el item si esta seleccionado
*/ 
function listboxRemoveItem(elementId) {
	var lbox = document.getElementById(elementId);
	if (lbox==null) {
		alert("ERROR (listboxRemoveItem): no existe un elemento con ID: " + elementId);
		return;
	}	
	if (lbox.selectedIndex>=0)
		lbox.remove(lbox.selectedIndex);
}

/**
 * Eliminar todos los items de un listbox
 * elementId: ID del control que representa al ListBox
*/
function listboxClear(elementId) {

	var lbox = document.getElementById(elementId);
	if (lbox==null) {
		alert("ERROR (listboxClear): no existe un elemento con ID: " + elementId);
		return;
	}
	
	while (lbox.length>0) {
		lbox.remove(0);
	}
	
}

/**
 * Obtener los IDs de los items de un listbox concatenados como un String
 * y separados por un caracter, por defecto es ";" si no se especifica
 * elementId: ID del control que representa al ListBox
 * separator: caracter a utilizar para separar los items
 * Nota: si el control esta vacio retorna un string vacio
*/ 
function listboxGetItemValues(elementId, separator) {

	if (separator==null)
		separator=";";

	var lbox = document.getElementById(elementId);
	if (lbox==null) {
		alert("ERROR (listboxGetItemValues): no existe un elemento con ID: " + elementId);
		return;
	}	
	var ids = "";
	for (var i=0;i<lbox.length;i++) {
		var item = lbox.options[i];
		ids = ids + item.value + separator;
	}
	//quitar el ultimo ;
	if(ids!="")
		ids = ids.substr(0, ids.length-1);
	return ids;
	
}

/**
 * Obtener el texto de los items de un listbox 
 * elementId: ID del control que representa al ListBox
 * separator: caracter a utilizar para separar los items
 * Nota: si el control esta vacio retorna un string vacio
*/ 
function listboxGetTextValues(elementId, separator) {

	if (separator==null)
		separator=";";

	var lbox = document.getElementById(elementId);
	if (lbox==null) {
		alert("ERROR (listboxGetTextValues): no existe un elemento con ID: " + elementId);
		return;
	}	
	var ids = "";
	for (var i=0;i<lbox.length;i++) {
		var item = lbox.options[i];
		ids = ids + item.text + separator;
	}
	//quitar el ultimo ;
	if(ids!="")
		ids = ids.substr(0, ids.length-1);
	return ids;
	
}

/**
 * Llenar el contenido de un objeto, como un DIV por ejemplo.
 * Forma parte del soporte para la validacion de formularios
 * posteados via Ajax.
 * elementId: Elemento donde sera llenado el contenido de un objeto
 * text: Texto
*/
function setInnerHtml(elementId, text) {

	var obj = document.getElementById(elementId);
	if (obj==null) {
		return;
	}
	
	//patch 2013-10-01
	var c = obj.innerHTML;
	if (c!="") {
		text = c + "<br>" + text;
	}
	
	obj.innerHTML = text;
	obj.style.display = "";
}

/**
 * Verifica si existe el DIV que corresponde a un control
 * de un formulario para mostrar un mensaje de error, si no existe
 * lo crea y luego muestra el mensaje usando setInnerHtml.
 * Es utilizada por el servicio generico de validacion Ajax:
 * /action/error/validation/ajax
 * formElementId: Elemento donde sera mostrado el mensaje de error
 * text: Texto de error
*/
function setFormErrorMsg(formElementId,text) {
	var id = formElementId + "_error";
	var obj = document.getElementById(id);
	if (obj==null) {
		try {
			var elem = document.getElementById(formElementId);
			var div = document.createElement( "div" );
			div.id = id;
			div.className = "errormsg";
			elem.parentNode.appendChild(div);
		} catch (err) {
			alert("Error en setFormErrorMsg -> Element ID: " + formElementId);
		}
	}	
	setInnerHtml(id, text);	
}

/**
 * By Dustin Diaz - http://www.dustindiaz.com/top-ten-javascript/
 * Retorna un array de elementos que tengan una clase dada,
 * los parametros node y tag pueden ser null
 * searchClass: Clase
 * node: Nodo
 * tag: TagName
*/
function getElementsByClass(searchClass,node,tag) {
	var classElements = new Array();
	if ( node == null )
		node = document;
	if ( tag == null )
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp('(^|\\\\s)'+searchClass+'(\\\\s|$)');
	for (i = 0, j = 0; i < elsLen; i++) {
		if ( pattern.test(els[i].className) ) {
			classElements[j] = els[i];
			j++;
		}
	}
	return classElements;
}

/**
 * Código tomado de:
 * http://www.codingforums.com/showthread.php?p=178077#post178077
 * Permite dar formato a un decimal y mostrar un cierto numero de decimales
 * n: Numero de decimales a mostrar
 */
Number.prototype.toDecimals=function(n){
    n=(isNaN(n))?
        2:
        n;
    var
        nT=Math.pow(10,n);
    function pad(s){
            s=s||'.';
            return (s.length>n)?
                s:
                pad(s+'0');
    }
    return (isNaN(this))?
        this:
        (new String(
            Math.round(this*nT)/nT
        )).replace(/(\.\d*)?$/,pad);
};

/** 
 * Código tomado de:
 * http://www.webdeveloper.com/forum/showthread.php?t=68675
 * Permite obtener tanto el alto como ancho de la pagina HTML que se esta usando
 */
function pageWidth() {return window.innerWidth != null? window.innerWidth: document.body != null? document.body.clientWidth:null;}
function pageHeight() {return window.innerHeight != null? window.innerHeight: document.body != null? document.body.clientHeight:null;}

/**
 * Soporte para DialogBox centrado en una pagina HTML
 */

//variable de control
var dialogID = null;

/**
* Muestra un DIV + IFRAME en el centro de una pagina
* dialogID: ID del dialog que sera centrado
* url: URL del action que contiene la informacion que sera impresa dentro del DIV + IFRAME
* ancho: Ancho del DIV + IFRAME
* altura: Altura del DIV + IFRAME
*/
function openDialog(dialogID, url, ancho, altura)
{	
	this.dialogID = dialogID;
	//mostrar popup dhtml
	showBox(dialogID, ancho, altura, url, true, true);
}	

/**
* Oculta un DIV + IFRAME que se encuentra en el centro
* de una pagina 
*/
function closeDialog()
{
	document.getElementById(dialogID + "_popup").style.display = "none";

	/* soporte para lightbox */
	var overlay = document.getElementById('_overlay');
	if (overlay!=null)
		overlay.style.display='none';
}

/**
 * Mostrar caja de dialogo centrada con un mensaje y opciones Aceptar|Cancelar, al darle clic en Aceptar
 * se cierra el dialogo y se invoca la funcion con el script provisto. Debe existir en la pagina un DIV con id=response, este DIV no sera afectado.
 * message: Texto a mostrar en el DialogBox
 * btnOk: etiqueta para el boton Aceptar
 * btnCancel: opcional - etiqueta para el boton Cancelar, si es null el boton se omite
 * onOkFn: script para invocar la funcion en caso de clic en Aceptar, si es nulo solo se cierra el dialogo
 */
function confirmBox(message, btnOk, btnCancel, onOkFn) {
		var padTop = "10px";
		if (message.length <= 40)
			padTop = "25px";
	 	var fn = "parent.closeBox(\"response\")";
	 	if (onOkFn!=null)
	 		fn = fn + ";" + onOkFn;
		var html = "<!doctype html><html><head><style>html, body {height:100%; margin:0px; overflow-y:hidden} div {margin:0px;padding:0px;}</style></head>" + 
		"<body><div style='font-family:Arial;font-weight:normal;font-size:10pt;text-align:center;height:60%;width:100%;'><div style='padding-top:" + padTop +";'>" + message + "</div></div>";
		html = html + "<div style='text-align:center;height:40%;width:100%;background-color:#e9e9e9;padding-top:10px;'><button id='btnOK' onclick='" + fn + "'>" + btnOk + "</button>";
		if (btnCancel!=null)
			html  = html + "&nbsp;<button onclick='parent.closeBox(\"response\")'>" + btnCancel + "</button>";
		html = html + "</div></body></html>";
		showBox("response", 300,120, null, true, false, html);
		var f = window.frames["response_iframe"];
		if (btnCancel==null)
			f.document.getElementById("btnOK").focus();
}

/**
* Code by Patrick Desjardins http://stackoverflow.com/questions/149055/how-can-i-format-numbers-as-money-in-javascript
*/
Number.prototype.format = function(c, d, t){
	var n = this, c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
	return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};

/*
Widget para convertir un input text en un control con mascara
para ingreso de números con separadores de miles y decimales. La función
getFormValues() le quita el formato para enviar el valor en formato americano  
como lo espera Dinámica. Soporta 0, 2 o 4 decimales.
Se usa asi; onload = function() { var text1 = new MaskedTextDouble(document.form1.text1); }
*/
function NumericInput(elem, decimalPlaces, allowNegative) {
	
	var _decimals = ((decimalPlaces != null) ? decimalPlaces : 2);
	var _dSep = ((_server_dsep != null) ? _server_dsep : ","); 
	var _tSep = ((_server_tsep != null) ? _server_tsep : "."); 
	var _allowNegative = ((allowNegative != null) ? allowNegative : false);
	var _divisor = 100;
	
	if (_decimals==0)
		_divisor = 0;
	else if (_decimals==4)
		_divisor = 10000;
	
	elem.alt = "numeric";
	
	elem.onkeyup = function(e) {
		var keyCode = e ? e.which : window.event.keyCode;
		var elem = e ? e.target : window.event.srcElement;
		if (keyCode==8 || keyCode==46 && elem.value!="") {
			var value = elem.value;
			value = value.replace(/,/g ,"");	
			value = value.replace(/\./g ,"");
			var num = Number(value);
			if (num>0) {
				if (_divisor>0)
					num = num / _divisor;
				elem.value = num.format(_decimals, _dSep ,_tSep);
			}
		}
	};
	
	elem.onkeydown = function(e) {
		var keyCode = e ? e.which : window.event.keyCode;
		if (keyCode>=35 && keyCode <=40)
			return false;
	};
	
	elem.onpaste = function(e) {
		return false;
	};	
	
	elem.onkeypress = function(e) {
		var mask = "0123456789";
		if (_allowNegative)
			mask = mask + "-";
		var keyCode = e ? e.which : window.event.keyCode;
		if (keyCode==null || keyCode==0)
			keyCode = e.keyCode;
		var elem = e ? e.target : window.event.srcElement;
		var key = String.fromCharCode(keyCode);
		
		if (mask.indexOf(key)==-1 && keyCode!=8 && keyCode!=13 && keyCode!=9)
			return false;
		
		if (keyCode==8 || keyCode==13 || keyCode==9)
			return true;
		
		var value = elem.value;

		if (key=="-") {
			if (value.indexOf("-")==0)
				elem.value = value.substring(1);
			else 
				elem.value = "-" + elem.value;				
			return false;
		}
		
		value = value.replace(/,/g ,"");	
		value = value.replace(/\./g ,"");
		value = value + key;
		var num = Number(value);
		if (_divisor>0)
			num = num / _divisor;
		elem.value = num.format(_decimals, _dSep ,_tSep); 	
		return false;			
	};
	
} //end widget NumericInput


// funciones añadidas desde el 2013-04-03

//Mostrar DialogBox con efecto lightbox para alert o confirm
//message: mensaje a mostrar, puede contener html
//buttonOK: etiqueta del botón OK
//buttonCancel: etiqueta del botón Cancel, si es nulo no se muestra este botón y el focus se pone el botón OK
//OkFn: script con la función a invocar en el onclick del botón OK, si es nulo solo se cierra el dialogo
function alertBox(message, buttonOK, buttonCancel, OkFn) {
	
	var fn = "closeAlertBox()";
	if (OkFn!=null)
 		fn = fn + ";" + OkFn;
 		
	var div = document.getElementById("alertbox");
	if (div!=null) {
		div.parentNode.removeChild(div);
	}
		
	var ab = "<div id='alertbox' class='alertbox' style='display:none'>" + 
	"<div id='alertbox-msg'>" + message + "</div>" +
	"<button id='alertbox-ok' onclick='" + fn + "'>" + buttonOK + "</button>";
		
	if (buttonCancel!=null)
		ab += "<button style='margin-left:3px;' onclick='closeAlertBox()'>" + buttonCancel + "</button>";
		
	ab += "</div>";
	
	document.body.insertAdjacentHTML("beforeEnd", ab);
	div = document.getElementById("alertbox");
	
	lightBoxOn();

	var height;
	var width;
	if (!window.getComputedStyle) {
		//IE8 hack
		height = parseInt(div.currentStyle["height"]);
		width = parseInt(div.currentStyle["width"]);
	} else {
		var height = parseInt(window.getComputedStyle(div, null).getPropertyValue("height"));			
		var width = parseInt(window.getComputedStyle(div, null).getPropertyValue("width"));
	}
	
	div.style.position = 'absolute';
	div.style.zIndex = 1002;
	div.style.top = (pageHeight() - height) / 2 + 'px';
	div.style.left = (pageWidth() - width) / 2 + 'px';
	div.style.display = "block";
	
	if (buttonCancel==null) {
		var btn = document.getElementById("alertbox-ok");
		btn.focus();
	}
	
}

//Cierra un DialogBox desplegado con la función alertBox()
function closeAlertBox() {
	var id ="alertbox";
	if (document.getElementById(id)!=null)
		document.getElementById(id).style.display = "none";
	lightBoxOff();
}

//Activa efecto lightbox desactivando el fondo
function lightBoxOn() {
	var overlay = document.getElementById('_overlay');
	if (overlay==null) {
		overlay = document.createElement("div");
	  	overlay.className = "overlay";
	  	overlay.setAttribute("id", "_overlay");
	  	document.body.appendChild(overlay);
	}
	  	
	if (document.body.scrollHeight > pageHeight()) 
	  	overlay.style.height = document.body.scrollHeight + 'px';
	else
		overlay.style.height = pageHeight() + 'px';
	  
	if (document.body.scrollWidth > pageWidth()) 
    	overlay.style.width = document.body.scrollWidth + 'px';
	else
		overlay.style.width = "100%"; 

	overlay.style.display='block';
}

//Desactiva el lightbox
function lightBoxOff() {
	var overlay = document.getElementById('_overlay');
	if (overlay!=null)
		overlay.style.display='none';			
}


//SIMONE CODE
function getFormValuesToCopy(formName)
{
	returnString ="";
	
	if( formName == "" ) 
	{
		return returnString;
	}
	
	formElements=document.forms[formName].elements;
	//copyFormElements=document.forms[formName].elements;
	
	if (typeof(Storage) == "undefined" || !window.localStorage) 
	{
 		alert('SIMONE - Sorry, no web storage support.');
	} 
	
	for ( var i=formElements.length-1; i>=0; --i )
	{
		if (formElements[i].name!="")
		{ 
			//aÃ±ade elementos radio y checkbox
			if (formElements[i].type=="checkbox" && !formElements[i].checked)
			{
				//este checkbox si no ha sido seleccionado es ignorado
				localStorage.setItem(formElements[i].name, "true");
			}
			else
			{
				if (formElements[i].type=="radio" && !formElements[i].checked)
				{
					//este radio si no ha sido seleccionado es ignorado
					localStorage.setItem(formElements[i].name, "true");
				}
				else 
				{
					var key   = formElements[i].name;
					var value = formElements[i].value;
					
					if (value == null)
					{
						value = "";	
					}
					
					if (key != 'id')
					{
						localStorage.setItem(key, value);
						//alert(localStorage.getItem(formElements[i].name));

						returnString = returnString + formElements[i].name + "=" + value + "&";
					}
				}
			}
		}
	}

	returnString = returnString.substring(0, returnString.length - 1);
	//alert(returnString);
	return returnString;

}

function getFormValuesToPaste(formName)
{

	returnString ="";
	
	//alert('formName = ' + formName);
	
	if( formName == "") 
	{
		return returnString;
	}
		
	formElements=document.forms[formName].elements;
	
	//alert(formElements.length);
	
	if (typeof(Storage) == "undefined" || !window.localStorage) 
	{
		alert('SIMONE - Sorry, no web storage support.');
	}
	
	for ( var i=formElements.length-1; i>=0; --i ) 
	{
		if (formElements[i].name!="")
		{ 
			//aÃ±ade elementos radio y checkbox
			if (formElements[i].type=="checkbox" && !formElements[i].checked)
			{
				//este checkbox si no ha sido seleccionado es ignorado
				formElements[i].checked = localStorage.getItem(formElements[i].name);
			}
			else
			{
				if (formElements[i].type=="radio" && !formElements[i].checked)
				{
					//este radio si no ha sido seleccionado es ignorado
					formElements[i].checked = localStorage.getItem(formElements[i].name);
				}
				else 
				{
					var key   = formElements[i].name;
					var value = formElements[i].value;
					
					if (value == null)
					{
						value = "";	
					}
					
					if (key !== "id")
					{
						//alert(localStorage.getItem(key));
						formElements[i].value = localStorage.getItem(key);
										
						returnString = returnString + formElements[i].name + "=" + value + "&";
					}
				}
			}
		}
	}
	
	//localStorage.clear();
	
	returnString = returnString.substring(0, returnString.length - 1);
	return returnString;
	
}

function getFormValuesToStore(formName)
{
	returnString ="";
	
	if( formName == "" ) 
	{
		return returnString;
	}
	
	formElements=document.forms[formName].elements;
	
	for ( var i=formElements.length-1; i>=0; --i )
	{
		if (formElements[i].name!="")
		{ 
			//aÃ±ade elementos radio y checkbox
			if (formElements[i].type=="checkbox" && !formElements[i].checked)
			{
				//este checkbox si no ha sido seleccionado es ignorado
			}
			else
			{
				if (formElements[i].type=="radio" && !formElements[i].checked)
				{
					//este radio si no ha sido seleccionado es ignorado
				}
				else 
				{
					var key   = formElements[i].name;
					var value = formElements[i].value;
					
					if (value == null)
					{
						value = "";	
					}
					
					if (key != 'id')
					{
						if (formElements[i].name.slice(-4) != 'name') 
						{
							returnString = returnString + formElements[i].name + "=" + value + "|";
							
							returnString = returnString + formElements[i].name + "_disabled=" + formElements[i].disabled + "|";
							
						}						
					}
				}
			}
		}
	}

	returnString = returnString.substring(0, returnString.length - 1);
	//alert(returnString);
	return returnString;

}

var IDLE_TIMEOUT = ${req:idle_timeout}; // lo coloca en el request una clase transaction
var _idleSecondsCounter = 0;
document.onclick = function() {
	_idleSecondsCounter = 0;
};
document.onkeypress = function() {
	_idleSecondsCounter = 0;
};
window.setInterval(CheckIdleTime, 1000);

function CheckIdleTime() {
	_idleSecondsCounter++;
	if (_idleSecondsCounter >= IDLE_TIMEOUT) {
		document.location.href = "${def:httpserver}${def:context}/action/security/exit";
	}
}



