addNew();
lastPage=${fld:pagecount};
currentPage=${fld:currentpage};
recordsFound=${fld:recordcount};
alertBox ('${lbl:b_error_excel_file}', '${lbl:b_continue_button}', null, 'viewPage();');