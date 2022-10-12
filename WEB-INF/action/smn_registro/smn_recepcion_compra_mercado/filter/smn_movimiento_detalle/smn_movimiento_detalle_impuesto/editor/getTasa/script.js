listboxClear("smn_tasa");

var optionChoose = document.createElement("option");
optionChoose.text = '[${lbl:b_choose}]';
optionChoose.value = "";
document.form1.smn_tasa.add(optionChoose);

<rows>
	var option = document.createElement("option");
	var valor_tasa = "${fld:item}";
	option.text = "${fld:fecha_item}"+" - "+valor_tasa; 
	option.value = "${fld:id}";
	document.form1.smn_tasa.add(option);
	document.form1.value_tasa.value=valor_tasa;
</rows>