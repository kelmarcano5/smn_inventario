package domain.convert;

import dinamica.GenericTransaction;
import dinamica.ImportExcel;
import dinamica.xml.Element;
import jxl.CellView;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CustomImportExcel extends ImportExcel
{	
	@Override
	public InputStream getInputStream(File file) throws Throwable 
	{

		InputStream f = new FileInputStream(file); 
		InputStream xls = new domain.convert.ExcelConvertHelper().convertFromXLSXtoXLS(f);
		
		return xls;
	}
	
	 @Override
     public String[] getParamsNames() throws Throwable 
	 {
        String[] params = new String[] {"efi_codigo",
        		                        "efi_nombre",
        		                        "efi_direccion_ref",
        		                        "efi_telefono_fijo",
        		                        "efi_telefono_movil",
        		                        "efi_codigo_aba",
        		                        "efi_codigo_swift",
        		                        "efi_req_banco_intermediario_ref",
        		                        "efi_banco_intermediario_ref",
        		                        "efi_routing",
        		                        "efi_contacto"};
        return params;
     }
	 


}
