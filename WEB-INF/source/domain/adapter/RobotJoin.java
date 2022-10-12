package domain.adapter;

public class RobotJoin {
	
	private String fieldJoin;
	private String fieldId;
	private String fieldItem;
	private String tableJoin;
	private boolean isNullable;
	private String label;

	public RobotJoin(String fieldJoin, String fieldId, String fieldItem, String tableJoin, boolean isNullable) {
		super();
		this.fieldJoin = fieldJoin;
		this.fieldId = fieldId;
		this.fieldItem = fieldItem;
		this.tableJoin = tableJoin;
		this.isNullable = isNullable;
		this.label= "";
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getFieldJoin() {
		return fieldJoin;
	}

	public void setFieldJoin(String fieldJoin) {
		this.fieldJoin = fieldJoin;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldItem() {
		return fieldItem;
	}

	public void setFieldItem(String fieldItem) {
		this.fieldItem = fieldItem;
	}

	public String getTableJoin() {
		return tableJoin;
	}

	public void setTableJoin(String tableJoin) {
		this.tableJoin = tableJoin;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		result = prime * result + ((fieldItem == null) ? 0 : fieldItem.hashCode());
		result = prime * result + ((fieldJoin == null) ? 0 : fieldJoin.hashCode());
		result = prime * result + (isNullable ? 1231 : 1237);
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((tableJoin == null) ? 0 : tableJoin.hashCode());
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
		RobotJoin other = (RobotJoin) obj;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		if (fieldItem == null) {
			if (other.fieldItem != null)
				return false;
		} else if (!fieldItem.equals(other.fieldItem))
			return false;
		if (fieldJoin == null) {
			if (other.fieldJoin != null)
				return false;
		} else if (!fieldJoin.equals(other.fieldJoin))
			return false;
		if (isNullable != other.isNullable)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (tableJoin == null) {
			if (other.tableJoin != null)
				return false;
		} else if (!tableJoin.equals(other.tableJoin))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RobotJoin [fieldJoin=" + fieldJoin + ", fieldId=" + fieldId + ", fieldItem=" + fieldItem
				+ ", tableJoin=" + tableJoin + ", isNullable=" + isNullable + ", label=" + label + "]";
	}

}
