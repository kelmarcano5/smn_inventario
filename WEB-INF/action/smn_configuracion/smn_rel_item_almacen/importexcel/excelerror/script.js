addNew();
lastPage=${fld:pagecount};
currentPage=${fld:currentpage};
recordsFound=${fld:recordcount};
alertBox ('El archivo Excel presenta errores', "Continuar", null, 'viewPage();');