listboxClear("smn_almacen_rf");

var optionChoose = document.createElement("option");
optionChoose.text = '[${lbl:b_choose}]';
optionChoose.value = "";
document.form1.smn_almacen_rf.add(optionChoose);

<smn_almacen_rf_rows>
	var option = document.createElement("option");
	option.text = "${fld:item@js}"; 
	option.value = "${fld:id}";
	document.form1.smn_almacen_rf.add(option);
</smn_almacen_rf_rows>

