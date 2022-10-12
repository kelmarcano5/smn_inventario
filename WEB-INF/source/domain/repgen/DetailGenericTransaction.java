package domain.repgen;

import dinamica.*;

public class DetailGenericTransaction extends GenericTransaction {

	@Override
	public String getSqlIN(Recordset rs, String colName) throws Throwable 
	{
		String sql = "";
		
		int type = rs.getField(colName).getType();
		StringBuilder b = new StringBuilder();
		rs.top();
		while (rs.next()) {
			switch (type) {
			case java.sql.Types.VARCHAR:
			case java.sql.Types.CHAR:				
				String v = StringUtil.replace(rs.getString(colName),"'","''");
				b.append("'" + v + "'" + ",");
				break;
			case java.sql.Types.INTEGER:
			case java.sql.Types.BIGINT:
				b.append(rs.getString(colName) + ",");
				break;
			}
		}
		b.deleteCharAt(b.length()-1);
		
		if (rs.getRecordCount()>0)
			sql = b.toString();
		return sql;
	}
	
}
