package domain.adapter;

public class RobotRepGenFields 
{
	private Integer fieldID;
	private String colname;
	private String alias;
	private String align;
	private Integer widthPixel;
	private String market;
	private Integer orden;
	private String format;
	private String type;
	private String actionRoot;
	
	public RobotRepGenFields() {
		super();
		this.fieldID = -1;
		this.colname = "";
		this.alias = "";
		this.align = "";
		this.widthPixel = -1;
		this.market = "";
		this.orden = -1;
		this.format = "";
		this.type = "";
		this.actionRoot = "";		
	}

	public RobotRepGenFields(Integer fieldID, String colname, String alias, String align, Integer widthPixel,
			String market, Integer orden, String format, String type, String actionRoot) {
		super();
		this.fieldID = fieldID;
		this.colname = colname;
		this.alias = alias;
		this.align = align;
		this.widthPixel = widthPixel;
		this.market = market;
		this.orden = orden;
		this.format = format;
		this.type = type;
		this.actionRoot = actionRoot;
	}

	public Integer getFieldID() {
		return fieldID;
	}

	public void setFieldID(Integer fieldID) {
		this.fieldID = fieldID;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getActionRoot() {
		return actionRoot;
	}

	public void setActionRoot(String actionRoot) {
		this.actionRoot = actionRoot;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Integer getWidthPixel() {
		return widthPixel;
	}

	public void setWidthPixel(Integer widthPixel) {
		this.widthPixel = widthPixel;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RobotRepGenFields [fieldID=" + fieldID + ", colname=" + colname + ", alias=" + alias + ", align="
				+ align + ", widthPixel=" + widthPixel + ", market=" + market + ", orden=" + orden + ", format="
				+ format + ", type=" + type + ", actionRoot=" + actionRoot + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionRoot == null) ? 0 : actionRoot.hashCode());
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((align == null) ? 0 : align.hashCode());
		result = prime * result + ((colname == null) ? 0 : colname.hashCode());
		result = prime * result + ((fieldID == null) ? 0 : fieldID.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((orden == null) ? 0 : orden.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((widthPixel == null) ? 0 : widthPixel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RobotRepGenFields other = (RobotRepGenFields) obj;
		if (actionRoot == null) {
			if (other.actionRoot != null)
				return false;
		} else if (!actionRoot.equals(other.actionRoot))
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (align == null) {
			if (other.align != null)
				return false;
		} else if (!align.equals(other.align))
			return false;
		if (colname == null) {
			if (other.colname != null)
				return false;
		} else if (!colname.equals(other.colname))
			return false;
		if (fieldID == null) {
			if (other.fieldID != null)
				return false;
		} else if (!fieldID.equals(other.fieldID))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (orden == null) {
			if (other.orden != null)
				return false;
		} else if (!orden.equals(other.orden))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (widthPixel == null) {
			if (other.widthPixel != null)
				return false;
		} else if (!widthPixel.equals(other.widthPixel))
			return false;
		return true;
	}


}
