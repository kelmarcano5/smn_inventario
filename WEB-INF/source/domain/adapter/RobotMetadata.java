package domain.adapter;

public class RobotMetadata 
{
	private String table;
	private String column;
	private String type;
	private String nullable;
	private int numericSize;
	private int varcharSize;
	private boolean isForeignKey;
	
	public RobotMetadata()
	{
		this.table = "";
		this.column   = "";
		this.type     = "";
		this.nullable = "";
		this.numericSize = 0;
		this.varcharSize = 0;
		this.isForeignKey = false;
	}
	
	public RobotMetadata(String table, String column, String type, String nullable, int numericSize, int varcharSize, boolean isForeignKey) 
	{
		this.table = table;
		this.column = column;
		this.type = type;
		this.nullable = nullable;
		this.numericSize = numericSize;
		this.varcharSize = varcharSize;
		this.isForeignKey = isForeignKey;
	}
	
	public boolean isForeignKey() {
		return isForeignKey;
	}

	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	public String getTable()
	{
		return table;
	}
	
	public int getVarcharSize() {
		return varcharSize;
	}

	public void setVarcharSize(int varcharSize) {
		this.varcharSize = varcharSize;
	}

	public void setTable(String table) 
	{
		this.table = table;
	}
	
	public String getColumn() 
	{
		return column;
	}
	
	public void setColumn(String column) 
	{
		this.column = column;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getNullable() 
	{
		return nullable;
	}
	
	public void setNullable(String nullable) 
	{
		this.nullable = nullable;
	}
	
	public int getNumericSize() 
	{
		return numericSize;
	}
	
	public void setNumericSize(int numericSize) 
	{
		this.numericSize = numericSize;
	}
	
	@Override
	public String toString() 
	{
		return "RobotMetadata [column=" + column + ", type=" + type + ", nullable=" + nullable + ", numericSize=" + numericSize + ", varcharSize=" + varcharSize + ", isForeignKey=" + isForeignKey + "]";
	}

}
