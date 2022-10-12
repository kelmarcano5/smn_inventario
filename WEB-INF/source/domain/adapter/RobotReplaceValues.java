package domain.adapter;

public class RobotReplaceValues {
	
	private String parent = null;
	private String directory = null;
	private String actionValue = null;
	private String actionNewValue = null;
	private String actionType = null;
	public RobotReplaceValues(String parent, String directory, String actionValue, String actionNewValue) {
		super();
		this.parent = parent;
		this.directory = directory;		
		this.actionValue = actionValue;
		this.actionNewValue = actionNewValue;
	}
	public RobotReplaceValues(String parent, String directory, String actionValue, String actionNewValue, String actionType) {
		super();
		this.parent = parent;
		this.directory = directory;		
		this.actionValue = actionValue;
		this.actionNewValue = actionNewValue;
		this.actionType = actionType;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionValue() {
		return actionValue;
	}
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	public String getActionNewValue() {
		return actionNewValue;
	}
	public void setActionNewValue(String actionNewValue) {
		this.actionNewValue = actionNewValue;
	}	

}
