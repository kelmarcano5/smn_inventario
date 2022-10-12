listboxClear("smn_item_rf");

var optionChoose = document.createElement("option");
optionChoose.text = '[${lbl:b_choose}]';
optionChoose.value = "";
document.form1.smn_item_rf.add(optionChoose, 0);

<smn_item_rf_rows>
	var option = document.createElement("option");
	option.text = "${fld:item@js}"; 
	option.value = "${fld:id}";
	document.form1.smn_item_rf.add(option);
</smn_item_rf_rows>