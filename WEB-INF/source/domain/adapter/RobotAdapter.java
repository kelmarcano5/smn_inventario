package domain.adapter;

import org.apache.log4j.PropertyConfigurator;

public class RobotAdapter
{

	@SuppressWarnings("unused")
	public static void main(String... args) throws Throwable 
	{
		args = new String[12];
		args[0] = "-robotpath";
		args[1] = "C:/simone/smn_robot";
		args[2] = "-backbutton";
		args[3] = "/action/...";
		args[4] = "-backbuttonlabel";
		args[5] = "";
		args[6] = "-nextbutton";
		args[7] = "/action/...";
		args[8] = "-nextbuttonlabel";
		args[9] = "";
		args[10] = "-pathlabel";
		args[11] = "C:/tomcat7/webapps/smn_base/web_inf";
		
		//robot instance
		Robot robot = new Robot();

		//Go over all directives
		boolean isLog4jConfigured = false;
		if (args != null)
		{
			for(int i = 0; i < args.length; i++)
			{
				if (args[i].compareTo("-robotpath") == 0)
				{
					if ((i + 1 < args.length) && (args[i + 1].lastIndexOf("smn_robot") != -1))
					{
						//configure log4j path
						String log4jConfPath = args[i + 1] + "/smn_properties/log4j.properties";
						PropertyConfigurator.configure(log4jConfPath);
						isLog4jConfigured = true;
					}
				}
			}
		} 
		else
		{
			robot.getRobotUtil().printInfo(Robot.STDOUT);
			System.exit(0);
		}

		//print to screen how it will work step by step
		if (isLog4jConfigured)
		{
			robot.printInstructions(Robot.LOGGER);
		}
		else
		{
			robot.getRobotUtil().printInfo(Robot.STDOUT);
			System.exit(0);
		}

		//robot start
		robot.start(args);

	}


}