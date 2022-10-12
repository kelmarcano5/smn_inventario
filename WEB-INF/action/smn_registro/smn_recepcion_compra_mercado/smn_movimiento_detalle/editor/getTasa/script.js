listboxClear("smn_tasa_rf");

var optionChoose = document.createElement("option");
optionChoose.text = '[${lbl:b_choose}]';
optionChoose.value = "";
document.form1.smn_tasa_rf.add(optionChoose, 0);

<item_rows>
	var option = document.createElement("option");
	var valor_tasa = "${fld:item}";
	option.text = "${fld:fecha_item}"+" - "+valor_tasa; 
	option.value = "${fld:id}";
	document.form1.smn_tasa_rf.add(option, 0);
	document.form1.value_tasa.value=valor_tasa;
</item_rows>
