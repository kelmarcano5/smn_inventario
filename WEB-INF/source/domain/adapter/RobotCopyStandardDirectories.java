package domain.adapter;

public class RobotCopyStandardDirectories 
{
	private String parent = null;
	private String directory = null;
	public RobotCopyStandardDirectories(String parent, String directory) {
		super();
		this.parent = parent;
		this.directory = directory;
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
	
}
