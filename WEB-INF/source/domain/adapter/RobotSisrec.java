package domain.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;

public class RobotSisrec 
{

	public void readFile( String pathFile ) throws IOException 
	{
		BufferedReader br = new BufferedReader(new FileReader( pathFile + "/security.sql"));
		String newLine = "";
		int n = 1;
		while(true)
		{
			String line = br.readLine();
			if (line == null)
			{
				break;
			}
			String q1 = line.substring(0, line.lastIndexOf("(")+1);
			q1 += n + ", ";
			//System.out.println(q1);
			String q2 = line.substring(line.indexOf("VALUES"));
			q2 = q2.substring(q2.indexOf(",")+1);
			//System.out.println(q2);

			Integer v1 = Integer.parseInt(q2.substring(0, q2.indexOf(",")).trim());
			Integer v2 = Integer.parseInt(q2.substring(q2.indexOf(",")+1, q2.indexOf(")")).trim());

			Integer v22 = 0;

			//Roles
			if (v1 != 1)
			{
				if (v2 == 500) v22 = 511;
				if (v2 == 501) v22 = 514;
				if (v2 == 502) v22 = 513;
				if (v2 == 503) v22 = 512;
				if (v2 == 504) v22 = 515;
				if (v2 == 505) v22 = 510;
				if (v2 == 1) v22 = 1;
			}
			else
			{
				v22 = v2;
			}

			newLine += q1 + v1 + ", " + v22 + ");\n"; 
			System.out.println("New line --> " + newLine);

			n++;
		}
		br.close();
		FileWriter fw = new FileWriter( pathFile  + "/securityNew.sql" );
		fw.write(newLine);
		fw.close();
		System.out.println( "File Adapted --> " + pathFile  + "/securityNew.sql" );
	}

	String pkg = "";
	public String getPackage( String path, String directory ) throws IOException 
	{
		File root = new File( path );
		File[] list = root.listFiles();

		if (list == null) return "";

		for ( File f : list ) 
		{
			if ( f.isDirectory() ) 
			{
				String dir = f.getName();
				System.out.println( "Directory --> " + f.getAbsolutePath() + " | Name: " + dir + " | Directory: " + directory);
				if (dir.compareTo( directory ) == 0)
				{
					pkg = f.getAbsolutePath();
					System.out.println( "Encontrado --> " + pkg );
				}
				getPackage ( f.getAbsolutePath(), directory );
			}
		}
		return pkg;
	}

	public void walk( String path ) throws IOException 
	{

		File root = new File( path );
		File[] list = root.listFiles();

		if (list == null) return;

		for ( File f : list ) 
		{
			if ( f.isDirectory() ) 
			{
				walk( f.getAbsolutePath() );
				System.out.println( "Directory -- > " + f.getAbsoluteFile() );
			}
			else 
			{
				System.out.println( "File --> " + f.getAbsoluteFile() );
				String file = f.getName();
				if (file.endsWith(".sql") || file.endsWith(".xml"))
				{
					BufferedReader br = new BufferedReader(new FileReader( f.getAbsoluteFile() ));
					String newLine = "";
					while(true)
					{
						String line = br.readLine();
						if (line == null)
						{
							break;
						}
						if (line.indexOf("public.") != -1)
						{
							line = line.replaceAll("public.", "smn_sisrec.");
						}
						newLine += line + "\n";
					}
					br.close();
					FileWriter fw = new FileWriter(f.getAbsoluteFile());
					fw.write(newLine);
					fw.close();
					System.out.println( "File Adapted --> " + f.getAbsoluteFile() );
				}
			}
		}
	}

	Hashtable<String,String> table = new Hashtable<String,String>();
	public void changeSisrecMultilanguague(String labelPath, String actionRoot)
	{		
		BufferedReader in = null;
		try 
		{
			in = new BufferedReader( new FileReader( labelPath ) );

			String id = "", es = "";
			while(true)
			{
				String line = in.readLine();
				if (line == null)
				{
					break;
				}
				line = line.replaceAll("\"", "'");
				if ((line.indexOf("label") != -1) && (line.indexOf("id=") != -1))
				{
					id = line.substring(line.indexOf("'")+1);
					id = id.substring(0, id.indexOf("'"));
					continue;
				}
				if (line.indexOf("language='es'") != -1)
				{
					es = line.substring(line.indexOf(">")+1, line.lastIndexOf("<"));
					if (!es.isEmpty() && !id.isEmpty())
					{
						String marker = "${lbl:" + id + "}";
						System.out.println("ES = " + es + " |  Marker = " + marker);
						table.put(es, marker);
					}
				}
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (in != null)
				{
					in.close();	
				}

			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		walk2( actionRoot );
	}

	public void walk2( String path ) 
	{
		File root = new File( path );
		File[] list = root.listFiles();

		if (list == null) return;

		for ( File f : list ) 
		{
			if ( f.isDirectory() ) 
			{
				walk2( f.getAbsolutePath() );
				//System.out.println( "Directory -- > " + f.getAbsoluteFile() );
			}
			else 
			{
				//System.out.println( "File --> " + f.getAbsoluteFile() );
				BufferedReader br = null;
				FileWriter fw = null;
				try {
					br = new BufferedReader(new FileReader( f.getAbsoluteFile() ));

					String newLine = "";
					while(true)
					{
						String line = br.readLine();
						if (line == null)
						{
							break;
						}

						Enumeration<String> enumeration = table.keys();
						while (enumeration.hasMoreElements())
						{
							String key = enumeration.nextElement();

							if (line.indexOf( key ) != -1)
							{
								line = line.replaceAll(key, Matcher.quoteReplacement(table.get(key)));
							}						
						}
						newLine += line + "\n";
					}
					fw = new FileWriter(f.getAbsoluteFile());
					fw.write(newLine);

					System.out.println( "File Adapted --> " + f.getAbsoluteFile() );

				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				finally
				{
					try {
						if (br != null)
						{
							br.close();
						}
						if (fw != null)
						{
							fw.close();
						}
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException 
	{
		RobotSisrec fw = new RobotSisrec();
		fw.changeSisrecMultilanguague("C:/tomcat7/webapps/smn_base/WEB-INF/labels.xml", "C:/Simone/smn_robot/smn_test");

		//fw.readFile( "C:/Users/ANA ROCIO/Desktop/SIMONE" ); //walk( "C:/Users/ANA ROCIO/Desktop/SIMONE/action" );
		//String pkg = fw.getPackage("C:/tomcat7/webapps/smn_base/WEB-INF/source/domain/repgen", "smn_usuarios");
		//pkg = pkg.substring(pkg.indexOf("repgen"));
		//pkg = pkg.replaceAll("\\\\", ".");

		//System.out.println("Package --> " + pkg);
	}

}
