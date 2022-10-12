package domain.adapter;

import java.util.StringTokenizer;
import java.util.Vector;

public class RobotActionProcess 
{
	private Vector<Object> instructionList = null; 

	public RobotActionProcess()
	{
		instructionList = new Vector<Object>();
	}

	public Vector<Object> getInstructionsToRun()
	{
		return instructionList;
	}

	private Vector<String> instructions = null;
	public void filterProcess(String directory, Vector<String> list)
	{
		this.instructions = list;
		for(int i = 0; i < instructions.size(); i++)
		{
			String instruccion = instructions.elementAt(i);
			if (directory.compareTo("delete") == 0)
			{
				if (instruccion.startsWith("@COPY_STANDARD_DIRECTORIES"))
				{
					instructionList.add(new RobotCopyStandardDirectories("filter", "delete"));
				}
			}
			else if (directory.compareTo("detail") == 0)
			{
				if (instruccion.startsWith("@COPY_STANDARD_DIRECTORIES"))
				{
					instructionList.add(new RobotCopyStandardDirectories("detail", "editor/form"));
				}
				else if (instruccion.startsWith("@DISABLED_FIELD"))
				{
					instruccion = instruccion.substring(instruccion.indexOf(" "), instruccion.length());
					RobotDisabledFields disabledFields = new RobotDisabledFields();
					StringTokenizer st = new StringTokenizer(instruccion," ");
					while(st.hasMoreTokens())
					{						
						disabledFields.addElement(st.nextToken());
					}
					instructionList.add(disabledFields);
				}
			}
		}
	}

	public Vector<Object> getRobotAction()
	{
		return instructionList;
	}
}

