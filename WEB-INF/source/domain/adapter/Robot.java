package domain.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;

public class Robot 
{

	private final String DIRECTIVE_ROBOT_PATH   = "-robotpath";
	private final String DIRECTIVE_ROBOT_BACK_BUTTON  = "-backbutton";
	private final String DIRECTIVE_ROBOT_BACK_BUTTON_LABEL  = "-backbuttonlabel";
	private final String DIRECTIVE_ROBOT_NEXT_BUTTON  = "-nextbutton";
	private final String DIRECTIVE_ROBOT_NEXT_BUTTON_LABEL  = "-nextbuttonlabel";
	private final String DIRECTIVE_ROBOT_PATH_LABEL  = "-pathlabel";	

	private final String DIRECTORY_ACTION       = "DIRECTORY_ACTION";
	private final String DIRECTORY_ROBOT        = "DIRECTORY_ROBOT";

	public static final String LOGGER = "LOGGER";
	public static final String STDOUT = "STDOUT";

	public static final String FORM_TYPE = "FORM_TYPE";
	public static final String JS_TYPE  = "JS_TYPE";
	public static final String PDF_TYPE  = "PDF_TYPE";
	public static final String EXCEL_TYPE  = "EXCEL_TYPE";
	public static final String TXT_TYPE  = "TXT_TYPE";
	public static final String XML_TYPE  = "XML_TYPE";
	public static final String EMAIL_TYPE  = "EMAIL_TYPE";
	public static final String SEARCH_TYPE  = "SEARCH_TYPE";
	public static final String EXCEL_DETAIL_TYPE  = "EXCEL_DETAIL_TYPE";
	public static final String EXCEL_TEMPLATE_TYPE = "EXCEL_TEMPLATE_TYPE";

	public static final String SEARCH_TYPE_DETAIL_REPGEN = "SEARCH_TYPE_DETAIL_REPGEN";
	public static final String SEARCH_TYPE_REPGEN  = "SEARCH_TYPE_REPGEN";
	public static final String SEARCH_TYPE_XML_REPGEN  = "SEARCH_TYPE_XML_REPGEN";
	public static final String EXCEL_TYPE_REPGEN  = "EXCEL_TYPE_REPGEN";
	public static final String EXCEL_DETAIL_TYPE_REPGEN  = "EXCEL_DETAIL_TYPE_REPGEN";
	public static final String PDF_TYPE_REPGEN  = "PDF_TYPE_REPGEN";
	public static final String PDF_DETAIL_TYPE_REPGEN  = "PDF_DETAIL_TYPE_REPGEN";
	public static final String TXT_TYPE_REPGEN  = "TXT_TYPE_REPGEN";
	public static final String TXT_DETAIL_TYPE_REPGEN  = "TXT_DETAIL_TYPE_REPGEN";
	public static final String XML_TYPE_REPGEN  = "XML_TYPE_REPGEN";
	public static final String XML_DETAIL_TYPE_REPGEN  = "XML_DETAIL_TYPE_REPGEN";

	public static final String NOTFOUND_TYPE = "NOTFOUND_TYPE";
	public static final String VIEW_TYPE = "VIEW_TYPE";
	public static final String INSERT_TYPE = "INSERT_TYPE";
	public static final String UPDATE_TYPE = "UPDATE_TYPE";

	public static final String[] COPY_FILES = null;

	private String actionDirectory = "";
	private String actionDirectoryDetail = "";

	private String recordsetInMemory = "";
	private String validatorRecordsetInMemory = "";

	private String fileLabelPath = "";
	private String actionLabel = ""; 
	private String customJavaPath = "";
	
	private String titleLabelTemplate = "";
	private String excelColumnTemplate = "";
	private boolean columTemplateAssigned = false;

	private Hashtable<String, String> parameters = new Hashtable<String, String>();
	private Hashtable<String, String> filterAndLabels = new Hashtable<String, String>();

	private Vector<RobotJoin> robotJoinList = new Vector<RobotJoin>();

	private RobotUtil util = null;

	private String placeholderHomePhone = "placeholder='${lbl:b_format_phone_local}'";
	private String placeholderHomeMobile = "placeholder='${lbl:b_format_phone_mobile}'";
	private String placeholderAbaSwift = "placeholder='${lbl:b_integer_range_aba_swift}'";
	private String placeholderEmail = "placeholder='${lbl:b_email_format}'"; 

	private RobotLabelsJAXP labels = null;

	private boolean sqlFormReaded = false;

	private String fieldJoin = "";
	private String fieldId = "";
	private String fieldItem = "";
	private String tableJoin = "";

	//robot constructor	
	public Robot()
	{
		util = new RobotUtil();
	}

	//method return robot util instance
	public RobotUtil getRobotUtil()
	{
		return util;
	}

	//method that print all robot instructions
	public void printInstructions(String type)
	{
		if (type.compareTo(Robot.LOGGER) == 0)
		{
			RobotUtil.logger.info("\n===== Welcome to Simoneitor your electronic robot adapter =====\n");
			RobotUtil.logger.info("You must consider the following steps for the successful execution of the robot: ");
			RobotUtil.logger.info("1. Read the robot syntax written at file 'smn_robot.txt' into /smn_robot/smn_instructions");
			RobotUtil.logger.info("2. Build a file with instructions specified in 'smn_robot.txt'");
			RobotUtil.logger.info("3. Generate Filter using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter");
			RobotUtil.logger.info("4. Generate ImportExcel using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/importexcel");
			RobotUtil.logger.info("5. Generate PagedView using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>");
			RobotUtil.logger.info("6. Generate ViewRecord using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter/detail");		
			RobotUtil.logger.info("7. Generate one of them: AddNew, AddNewMD, AddNewMDMultiple or ViewChart, using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter/editor");
			RobotUtil.logger.info("8. Generate PickList in case it be necessary");
			RobotUtil.logger.info("9. The robot will generate and adapt automatically REPGEN (Reports Generator) and PANEL (Iconographic Panel)");
			RobotUtil.logger.info("The electronic robot leads you in all process");
			RobotUtil.logger.info("Press any key to continue...");
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			try {
				br.readLine();
			} 
			catch (IOException e) 
			{
				RobotUtil.logger.error("Error in standard output", e.getCause());
			}
		}
		else
		{
			RobotUtil.logger.info("\n===== Welcome to Simoneitor your electronic robot adapter =====\n");
			RobotUtil.logger.info("You must consider the following steps for the successful execution of the robot: ");
			RobotUtil.logger.info("1. Read the robot syntax written at file 'smn_robot.txt' into /smn_robot/smn_instructions");
			RobotUtil.logger.info("2. Build a file with instructions specified in 'smn_robot.txt'");
			RobotUtil.logger.info("3. Generate Filter using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter");
			RobotUtil.logger.info("4. Generate ImportExcel using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/importexcel");
			RobotUtil.logger.info("5. Generate PagedView using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>");
			RobotUtil.logger.info("6. Generate ViewRecord using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter/detail");		
			RobotUtil.logger.info("7. Generate one of them: AddNew, AddNewMD, AddNewMDMultiple or ViewChart, using Dinamica Framework into the directoy /smn_robot/smn_actions/smn_<action>/filter/editor");
			RobotUtil.logger.info("8. Generate PickList in case it be necessary");
			RobotUtil.logger.info("9. The robot will generate and adapt automatically REPGEN (Reports Generator) and PANEL (Iconographic Panel)");
			RobotUtil.logger.info("The electronic robot leads you in all process");
			RobotUtil.logger.info("Press any key to continue...");
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			try {
				br.readLine();
			} 
			catch (IOException e) 
			{
				RobotUtil.logger.error(e.toString());
			}
		}
	}

	//method that start process the following: verification, validation and execution of robot meta-languague  
	public void start(String args[]) throws Throwable
	{ 
		long tStart = System.currentTimeMillis();

		RobotUtil.logger.info("Electronic robot starting...");

		//actions to execute and its arguments (if it have any)
		boolean directive = false;
		int i = 0;
		int k = args.length;
		while(i < k)
		{
			if (args[i].trim().indexOf("-") != -1)
			{
				if (args[i].compareTo(DIRECTIVE_ROBOT_PATH) == 0)
				{
					parameters.put(DIRECTORY_ROBOT, "");
					parameters.put(DIRECTORY_ACTION, "");

					parameters.put(DIRECTIVE_ROBOT_BACK_BUTTON, "");
					parameters.put(DIRECTIVE_ROBOT_BACK_BUTTON_LABEL, "");
					parameters.put(DIRECTIVE_ROBOT_NEXT_BUTTON, "");
					parameters.put(DIRECTIVE_ROBOT_NEXT_BUTTON_LABEL, "");
				}
				if ((i + 1 < k) && (args[i + 1].indexOf("-") == -1))
				{
					if (args[i].compareTo(DIRECTIVE_ROBOT_PATH) == 0)
					{
						parameters.put(DIRECTORY_ROBOT, args[i+1]);
						parameters.put(DIRECTORY_ACTION, args[i+1] + "/smn_actions");	
						directive = true;					
					}
					else if (args[i].compareTo(DIRECTIVE_ROBOT_BACK_BUTTON) == 0)
					{
						parameters.put(DIRECTIVE_ROBOT_BACK_BUTTON, args[i+1]);	
						directive = true;					
					}
					else if (args[i].compareTo(DIRECTIVE_ROBOT_BACK_BUTTON_LABEL) == 0)
					{
						parameters.put(DIRECTIVE_ROBOT_BACK_BUTTON_LABEL, args[i+1]);	
						directive = true;					
					}
					else if (args[i].compareTo(DIRECTIVE_ROBOT_NEXT_BUTTON) == 0)
					{
						parameters.put(DIRECTIVE_ROBOT_NEXT_BUTTON, args[i+1]);	
						directive = true;					
					}
					else if (args[i].compareTo(DIRECTIVE_ROBOT_NEXT_BUTTON_LABEL) == 0)
					{
						parameters.put(DIRECTIVE_ROBOT_NEXT_BUTTON_LABEL, args[i+1]);	
						directive = true;					
					}
					else if (args[i].compareTo(DIRECTIVE_ROBOT_PATH_LABEL) == 0)
					{
						parameters.put(DIRECTIVE_ROBOT_PATH_LABEL, args[i+1]);	
						directive = true;					
					}
					i += 2;
				}
				else
				{
					i++;
				}
			}
		}
		if (directive)
		{
			RobotUtil.logger.info("Robot read all directives. Status: OK.");
		}
		else
		{
			RobotUtil.logger.error("Robot failed reading all directives. Status: FAILED.");
			RobotUtil.logger.error("Please read the following instructions:");
			util.printInfo(RobotUtil.LOGGER);
			System.exit(1);
		}

		fileLabelPath = parameters.get(DIRECTIVE_ROBOT_PATH_LABEL);
		labels = new RobotLabelsJAXP(fileLabelPath);

		//set directives correponding to each action
		boolean directivesFailed = false;
		Enumeration<String> enumeration = parameters.keys();
		while (enumeration.hasMoreElements())
		{
			String key = enumeration.nextElement();
			if ((key.trim().compareTo(DIRECTORY_ACTION) == 0) ||
					(key.trim().compareTo(DIRECTORY_ROBOT) == 0))
			{
				String path = parameters.get(key);
				if (path.compareTo("") == 0)
				{
					RobotUtil.logger.error("The parameter " + key + " must to have a path assigned. Status: FAILED.");
					directivesFailed = true;
				}
				else
				{
					util.checkRobotInstructions(parameters.get(key));
				}
			}
		}
		if (directivesFailed)
		{
			util.printInfo(RobotUtil.LOGGER);
			System.exit(0);
		}
		else
		{
			RobotUtil.logger.info("Electronic robot checked all directives. Status: OK.");
		}

		File directories = new File(parameters.get(DIRECTORY_ACTION)); 
		String[] directoryList = directories.list();

		//walk recursive through directories tree in order to adapting
		for(int j = 0; j < directoryList.length; j++)
		{
			String actionPath =  parameters.get(DIRECTORY_ACTION) + "/" + directoryList[j]; 
			actionDirectory = directoryList[j];

			File adaptedFileExists = new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory +"/" + actionDirectory + "_adapted.txt");
			System.out.println("FILE ADAPTED --> " + parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory +"/" + actionDirectory + "_adapted.txt");
			if (adaptedFileExists.exists())
			{
				RobotUtil.logger.info("");
				RobotUtil.logger.info("==========================================================================================");
				RobotUtil.logger.info("=== Robot detected " + actionDirectory + " already was adapted ===");
				RobotUtil.logger.info("==========================================================================================");
				System.exit(1);
			}

			util.connectSimoneDB(actionDirectory);
			util.connectSimoneDBForeignKey(actionDirectory);
			util.connectRepGenFields();

			//copy report generator actions
			copyFolder(new File(parameters.get(DIRECTORY_ROBOT) + "/smn_standard/repgen"), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/repgen"));

			File[] fileList = new File(actionPath).listFiles();
			try 
			{
				//build base directories
				File deleteRootDirectory = new File(actionPath + "/delete");
				if (!deleteRootDirectory.exists())
				{
					boolean directoryCreated = deleteRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created DELETE directory " + deleteRootDirectory.getPath());
					}
				}
				File detailRootDirectory = new File(actionPath + "/detail");
				if (!detailRootDirectory.exists())
				{
					boolean directoryCreated = detailRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created DETAIL directory " + detailRootDirectory.getPath());
					}
				}
				File editorRootDirectory = new File(actionPath + "/editor");
				if (!editorRootDirectory.exists())
				{
					boolean directoryCreated = editorRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created EDITOR directory " + editorRootDirectory.getPath());
					}
				}
				File emailRootDirectory = new File(actionPath + "/email");
				if (!emailRootDirectory.exists())
				{
					boolean directoryCreated = emailRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created E-MAIL directory " + emailRootDirectory.getPath());
					}
				}
				File xmlRootDirectory = new File(actionPath + "/xml");
				if (!xmlRootDirectory.exists())
				{
					boolean directoryCreated = xmlRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created XML directory " + xmlRootDirectory.getPath());
					}
				}
				File txtRootDirectory = new File(actionPath + "/txt");
				if (!txtRootDirectory.exists())
				{
					boolean directoryCreated = txtRootDirectory.mkdir();
					if (directoryCreated)
					{
						RobotUtil.logger.info("Was created TXT directory " + txtRootDirectory.getPath());
					}
				}
				//complete process				
				actionAdapterProcess(actionDirectory, fileList);

				actionAdapterProcessRunSecond(actionDirectory, fileList);

				actionAdapterProcessRunThird(actionDirectory, fileList);

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/form"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/form"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/excel"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/excel"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/pdf"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/pdf"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/txt/view"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/txt/view"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/xml/view"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/xml/view"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/txt/view"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/txt/view"));

				copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/xml/view"),
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/xml/view"));
				
				//copy action panel
				copyFolder(new File(parameters.get(DIRECTORY_ROBOT) + "/smn_standard/panel"), 
						new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/panel"));
			} 
			catch (IOException e) 
			{
				RobotUtil.logger.error(e.toString());
			}
		}

		String stuckFile = actionDirectory.replaceAll("_", "");
		File adaptQuery = new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/search/query-"+stuckFile+".sql");
		BufferedReader in = new BufferedReader(new FileReader(adaptQuery));
		String sql = "";
		while(true)
		{
			String readLine = in.readLine();
			if (readLine == null)
			{
				break;
			}
			sql += readLine + "\n";
		}
		if (in != null)
		{
			in.close();
		}

		adaptQuery = new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/form/query.sql");
		in = new BufferedReader(new FileReader(adaptQuery));
		String sqlDetail = "";
		while(true)
		{
			String readLine = in.readLine();
			if (readLine == null)
			{
				break;
			}
			sqlDetail += readLine + "\n";
		}
		if (in != null)
		{
			in.close();
		}

		//Adapt Main TXT
		FileWriter queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/txt/search/query.sql"));
		queryFile.write(sql);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Main XML
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/xml/search/query.sql"));
		queryFile.write(sql);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Main Detail TXT
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/txt/search/query.sql"));
		queryFile.write(sqlDetail);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Main Detail XML
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/xml/search/query.sql"));
		queryFile.write(sqlDetail);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Filter TXT
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/txt/search/query.sql"));
		queryFile.write(sql);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Filter XML
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/xml/search/query.sql"));
		queryFile.write(sql);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Filter Detail TXT
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/txt/search/query.sql"));
		queryFile.write(sqlDetail);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//Adapt Filter Detail XML
		queryFile = new FileWriter(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/xml/search/query.sql"));	
		queryFile.write(sqlDetail);
		if (queryFile != null)
		{
			queryFile.close();
		}

		//patch to figure out incomplete cases
		deleteDirectory(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail"));
		deleteDirectory(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor"));
		
		copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail"), 
				new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail"));
		copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/editor"), 
				new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor"));

		//Adapt REPGEN Java Files
		String repgenJavaSourcePath = parameters.get(DIRECTORY_ROBOT) + "/smn_standard/repgen/convert";		
		String repgenJavaTargetPath = "C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/" + customJavaPath.replaceAll("\\\\", "/");

		File javaFiles = new File( repgenJavaSourcePath );
		String[] javaList = javaFiles.list(); 
		for(int j = 0; j < javaList.length; j++)
		{
			FileWriter fw = null;
			BufferedReader brJava = null;		
			try
			{
				String javaFile = "";
				brJava = new BufferedReader(new FileReader( repgenJavaSourcePath + "/" + javaList[j] ));
				while(true)
				{
					String line = brJava.readLine();
					if (line == null)
					{
						break;
					}
					if (line.indexOf("package") != -1)
					{
						line = line.substring(0, line.indexOf(" "));
						line += " " + customJavaPath.replaceAll("\\\\", ".") + ";"; 
					}
					if (line.indexOf("${smn:labels_xml_custom}") != -1)
					{
						String labCustom = "\t\tString nameCustom  = \"" + actionLabel + "\";\n" +
								"\t\tString labelCustom = \"${lbl:" + actionLabel + "}\";\n\n";
						line = line.replaceFirst("\\$\\{smn\\:labels_xml_custom\\}", Matcher.quoteReplacement(labCustom));
						javaFile += labCustom;
						continue;
					}
					if (line.indexOf("${smn:labels_txt_custom}") != -1)
					{
						String labCustom = "\t\tString nameCustom  = \"" + actionLabel + "\";\n" +
								"\t\tString labelCustom = \"${lbl:" + actionLabel + "}\";\n\n";
						line = line.replaceFirst("\\$\\{smn\\:labels_txt_custom\\}", Matcher.quoteReplacement(labCustom));
						javaFile += labCustom;
						continue;
					}
					if (line.indexOf("${smn:labels_excel_custom}") != -1)
					{
						String labCustom = "\t\tString nameCustom  = \"" + actionLabel + "\";\n" +
								"\t\tString labelCustom = \"${lbl:" + actionLabel + "}\";\n\n";
						line = line.replaceFirst("\\$\\{smn\\:labels_excel_custom\\}", Matcher.quoteReplacement(labCustom));
						javaFile += labCustom;
						continue;
					}
					if (line.indexOf("${smn:filters_pdf_custom}") != -1)
					{
						String filterCustom = "";
						Enumeration<String> enumKeys = filterAndLabels.keys();
						while (enumKeys.hasMoreElements())
						{
							String field = enumKeys.nextElement();
							String label = filterAndLabels.get(field);
							String fCust = 	"\t\td = rs.getString(\""+ field +"\");\n" +
									"\t\t\tif (d != null)\n" +
									"\t\t\t{\n" +
									"\t\t\tname  = \""+ label +"\";\n" +
									"\t\t\tlabel = \"${lbl:"+ label +"}\";\n" +
									"\t\t\tstr_filter = StringUtil.replace(label, label, _labels.getLabel(name, _language));\n\n" +
									"\t\t\t//filter field\n" +
									"\t\t\tc = new PdfPCell(new Phrase(str_filter, tblHeaderFont));\n" +
									"\t\t\tc.setGrayFill(0.95f);\n"+
									"\t\t\tc.setHorizontalAlignment(Element.ALIGN_LEFT);\n" +
									"\t\t\tdatatable.addCell(c);\n" +
									"\t\t\td = d.replaceAll(\"%\", \"\");\n" +
									"\t\t\tc = new PdfPCell( new Phrase( d , tblBodyFont ) );\n" +
									"\t\t\tc.setHorizontalAlignment(Element.ALIGN_LEFT);\n" +
									"\t\t\tdatatable.addCell(c);\n" +	
									"\t\t}\n\n";
							filterCustom += fCust;
						}
						line = line.replaceFirst("\\$\\{smn\\:filters_pdf_custom\\}", Matcher.quoteReplacement(filterCustom));
					}
					javaFile += line + "\n";
				}
				//System.out.println("Writing to --> " + repgenJavaTargetPath + "/" + javaList[j]);
				fw = new FileWriter( repgenJavaTargetPath + "/" + javaList[j] );
				fw.write(javaFile);
				RobotUtil.logger.info("Java file REPGEN adapted --> " + repgenJavaTargetPath + "/" + javaList[j]);
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
				if (brJava != null)
				{
					brJava.close();
				}
				if (fw != null)
				{
					fw.close();
				}
			}
		}

		if (!util.existsRepGenFields())
		{
			String repgenValidatorFile = parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/editor/insert/validator.xml";	

			Vector<RobotRepGenFields> repgenList = new Vector<RobotRepGenFields>();
			BufferedReader brVal = null;		
			try
			{
				String valActionRoot = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/action", util.getCurrentTable());
				System.out.println("valActionRoot 1 - " + valActionRoot);
				valActionRoot = valActionRoot.replaceAll("\\\\", "/");
				System.out.println("valActionRoot 2 - " + valActionRoot);
				valActionRoot = valActionRoot.substring(valActionRoot.indexOf("/action"));
				System.out.println("valActionRoot 3 - " + valActionRoot);
				valActionRoot += "/repgen";
				System.out.println("valActionRoot 4 - " + valActionRoot);

				Integer orden = 1;
				brVal = new BufferedReader(new FileReader( repgenValidatorFile ));
				while(true)
				{
					String line = brVal.readLine();
					System.out.println("Reading line - " + line);
					if (line == null)
					{
						break;
					}
					if (line.indexOf("parameter") != -1)
					{						
						if (line.indexOf("\"") != -1)
						{
							line = line.replaceAll("\"", "'");
						}
						String valId = line.substring(line.indexOf("id")+4);
						valId = valId.substring(0, valId.indexOf("'"));
						System.out.print("ValId - " + valId);
						
						String valColname = util.getDatabaseSchema() + "." + util.getCurrentTable() + "." + valId;
						System.out.print(" | valColname - " + valColname);
						
						String valType = line.substring(line.indexOf("type")+6);
						valType = valType.substring(0, valType.indexOf("'"));
						System.out.print(" | valType - " + valType);
						
						System.out.print("Line -- " + line);
						String valLabel = line.substring(line.indexOf("label")+7);
						valLabel = valLabel.substring(0, valLabel.indexOf("'"));
						System.out.println(" | valLabel - " + valLabel);

						String valAlign = "left";
						/*
						if (valType.compareTo("varchar") == 0)
						{
							valAlign = "left";
						}
						else if (valType.compareTo("double") == 0)
						{
							valAlign = "right";
						}
						else
						{
							valAlign = "center";
						}
						*/

						Integer valWidthPx = 0;

						if (valId.indexOf("codigo") != -1)
						{
							valWidthPx = 50;
						}
						else if ((valId.indexOf("nombre") != -1) || (valId.indexOf("descripcion") != -1))
						{
							valWidthPx = 150;
						}
						else
						{
							valWidthPx = 80;
						}

						String valFormat = "";
						if (valType.compareTo("date") == 0)
						{
							valFormat = "@dd-MM-yyyy";
						}

						RobotRepGenFields valField = new RobotRepGenFields(-1,valColname,valLabel,valAlign,valWidthPx,valId,orden,valFormat,valType,valActionRoot);

						RobotUtil.logger.info(valField);

						repgenList.addElement(valField);

						orden++;
					}
				}
				util.connectInsertRepGenFields(repgenList);
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
				if (brVal != null)
				{
					brVal.close();
				}
			}
		}
		
		//copy sql files lack in repgen
		File filesLack = new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/filter/editor/insert");
		String[] lackList = filesLack.list();
		for(int j = 0; j < lackList.length; j++)
		{
			if (lackList[j].startsWith("buscar"))
			{
				copySimpleFile(new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/filter/editor/insert/" + lackList[j]),
					   	   	   new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/repgen/search/" + lackList[j]));
			}
		}
		
		//Delete extra folders 
		deleteDirectory(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter_full"));
		
		deleteDirectory(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/repgen/convert"));
		
		deleteDirectory(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/modelo"));

		//Create file that indicates it action was adapted
		File adaptedFile = new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory +"/" + actionDirectory + "_adapted.txt");
		adaptedFile.createNewFile();

		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;

		RobotUtil.logger.info("Elapsed time: " + elapsedSeconds + " seconds");

		util = null;

		System.gc();

		util = new RobotUtil();
	}

	/**
	 * Force deletion of directory
	 * @param path
	 * @return
	 */
	public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	//recursive function that make the adapting process
	public void actionAdapterProcess(String parentDirectory, File[] files) throws Throwable
	{
		//walk per each file
		for (File file : files)
		{
			//walk per each directory
			if (file.isDirectory()) 
			{
				//System.out.println("--> DIRECTORY: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
				if (file.getName().endsWith("search"))
				{
					if (file.getParent().endsWith(actionDirectory))
					{
						String sqlFile = "";
						File queryFile = new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/search");
						File[] listFiles = queryFile.listFiles();
						for(int k = 0; k < listFiles.length; k++)
						{
							if (listFiles[k].getName().startsWith("query-") && listFiles[k].getName().endsWith(".sql"))
							{
								sqlFile = listFiles[k].getName();
								break;
							}
						}
						String[] adaptFile = new String[]{sqlFile};
						adaptFileToStandard(file, "/search", actionDirectory + "\\search", adaptFile, Robot.SEARCH_TYPE);
					}
					else if (file.getParent().endsWith("repgen"))
					{						
						File queryFile = new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/filter/search");
						File[] listFiles = queryFile.listFiles();
						for(int k = 0; k < listFiles.length; k++)
						{
							if (listFiles[k].getName().startsWith("clause-") && 
									listFiles[k].getName().endsWith(".sql") && 
									listFiles[k].getName().indexOf("fecha_registro") == -1)
							{
								copySimpleFile(new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/filter/search/" + listFiles[k].getName()),
										new File(parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/repgen/search/" + listFiles[k].getName()));

								//System.out.println("--> SEARCH REPGEN FROM FILTER...");
								String[] adaptFile = new String[]{listFiles[k].getName()};
								adaptFileToStandard(file, "/repgen/search", "repgen\\search", adaptFile, Robot.SEARCH_TYPE_REPGEN);
							}
						}

						//System.out.println("--> SEARCH REPGEN");
						String[] adaptFile = new String[]{"query-base.sql","query-field.sql","query-field2.sql","total_width.sql","clause-fdesde.sql","clause-fhasta.sql"};
						adaptFileToStandard(file, "/repgen/search", "repgen\\search", adaptFile, Robot.SEARCH_TYPE_REPGEN);

						adaptFile = new String[]{"config.xml","validator.xml"};
						adaptFileToStandard(file, "/repgen/search", "repgen\\search", adaptFile, Robot.SEARCH_TYPE_XML_REPGEN);

						//XML manage
						adaptFile = new String[]{"query-base.sql"};
						adaptFileToStandard(file, "/repgen/xml",  "\\repgen\\xml", adaptFile, Robot.SEARCH_TYPE_REPGEN);

						adaptFile = new String[]{"config.xml","template.txt"};
						adaptFileToStandard(file, "/repgen/xml",  "\\repgen\\xml", adaptFile, Robot.XML_TYPE_REPGEN);

						adaptFile = new String[]{"query-base.sql"};
						adaptFileToStandard(file, "/repgen/detail/xml", "\\repgen\\detail\\xml", adaptFile, Robot.SEARCH_TYPE_REPGEN);

						adaptFile = new String[]{"config.xml","template.txt"};
						adaptFileToStandard(file, "/repgen/detail/xml",  "\\repgen\\detail\\xml", adaptFile, Robot.XML_TYPE_REPGEN);

						//TXT manage
						adaptFile = new String[]{"query-base.sql"};
						adaptFileToStandard(file, "/repgen/txt",  "\\repgen\\txt", adaptFile, Robot.SEARCH_TYPE_REPGEN);

						adaptFile = new String[]{"config.xml"};
						adaptFileToStandard(file, "/repgen/txt",  "\\repgen\\txt", adaptFile, Robot.TXT_TYPE_REPGEN);

						adaptFile = new String[]{"query-base.sql"};
						adaptFileToStandard(file, "/repgen/detail/txt", "\\repgen\\detail\\txt", adaptFile, Robot.SEARCH_TYPE_REPGEN);

						adaptFile = new String[]{"config.xml"};
						adaptFileToStandard(file, "/repgen/detail/txt",  "\\repgen\\detail\\txt", adaptFile, Robot.TXT_TYPE_REPGEN);
					}
				}
				else if (file.getName().endsWith("delete"))
				{
					if (file.getParent().endsWith("filter"))
					{
						//copy root parent directory
						copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/delete"));
					}
					else if (file.getParent().substring(file.getParent().lastIndexOf("\\")+1).startsWith("detail"))
					{
						if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
						{
							if (file.getParent().indexOf("email")==-1)
							{
								actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
								//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

								//copy root parent directory
								copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail + "/delete"));
							}
						}
					}
				}
				else if (file.getName().endsWith("insert"))
				{
					if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1) &&
							(file.getParent().indexOf("email")==-1))
					{
						String[] noCopyFiles = new String[]{"validator.xml"};
						copyStandardFiles(file, "/filter/editor/insert", "editor\\insert", noCopyFiles);

						String[] adaptFile = new String[]{"validator.xml"};
						adaptFileToStandard(file, "/filter/editor/insert", "editor\\insert", adaptFile, Robot.INSERT_TYPE);

						//copy root parent directory
						copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/insert"));
					}
					else if (file.getParent().indexOf("importexcel")!=-1)
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] noCopyFiles = new String[]{"excel.xml"};
							copyStandardFiles(file, "/importexcel/insert", "importexcel\\insert", noCopyFiles);

							String[] adaptFile = new String[]{"excel.xml"};
							adaptFileToStandard(file, "/importexcel/insert", "importexcel\\insert", adaptFile, Robot.INSERT_TYPE);
						}
					}
					else if (file.getParent().substring(file.getParent().lastIndexOf("\\")+1).startsWith("detail"))
					{
						if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
						{
							if (file.getParent().indexOf("email")==-1)
							{
								actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
								//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

								//copy root parent directory
								copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail + "/insert"));
							}
						}
					}
				}
				else if (file.getName().endsWith("update"))
				{
					if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1) &&
							(file.getParent().indexOf("email")==-1))
					{
						String[] noCopyFiles = new String[]{"validator.xml"};
						copyStandardFiles(file, "/filter/editor/update", "editor\\update", noCopyFiles);

						String[] adaptFile = new String[]{"validator.xml"};
						adaptFileToStandard(file, "/filter/editor/update", "editor\\update", adaptFile, Robot.UPDATE_TYPE);

						//copy root parent directory
						copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/update"));
					}
					else if (file.getParent().substring(file.getParent().lastIndexOf("\\")+1).startsWith("detail"))
					{
						if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
						{
							if (file.getParent().indexOf("email")==-1)
							{
								actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
								//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

								//copy root parent directory
								copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail + "/update"));
							}
						}
					}
				}
				else if (file.getName().endsWith("notfound"))
				{
					if (file.getParent().endsWith("filter"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
							String[] adaptFile = new String[]{"message.htm"};
							adaptFileToStandard(file, "/filter/notfound", "filter\\notfound", adaptFile, Robot.NOTFOUND_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/notfound"));
						}
					}
				}
				else if (file.getPath().endsWith("filter")) 
				{
					String path = file.getPath() + "/email";
					File newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						//String[] adaptFile = new String[]{"message.htm"};
						//adaptFileToStandard(file, "/filter/email", "filter\\email", adaptFile, Robot.EMAIL_TYPE);

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/filter/email";
						String pathTarget = file.getParent()+"\\filter\\email";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						//copy root parent directory
						copyFolder(new File(pathSource), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/email"));
					}
					path = file.getPath() + "\\xml";
					//System.out.println("Path --> "+path);
					newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/filter/xml";
						String pathTarget = file.getParent()+"\\filter\\xml";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						String[] adaptFile = new String[]{"query.sql"};
						adaptFileToStandard(file, "/filter/xml/search", actionDirectory + "\\filter\\xml\\search", adaptFile, Robot.XML_TYPE);

						adaptFile = new String[]{"template.xml"};
						adaptFileToStandard(file, "/filter/xml/view",  actionDirectory + "\\filter\\xml\\view", adaptFile, Robot.XML_TYPE);
						adaptFileToStandard(file, "/filter/xml/notfound",  actionDirectory + "\\filter\\xml\\notfound", adaptFile, Robot.XML_TYPE);

						//copy root parent directory
						copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/xml"), 
								new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/xml"));

						//detail
						pathSource = robotDirectory + "/smn_standard/filter/detail/xml";
						pathTarget = file.getParent()+"\\filter\\detail\\xml";

						//System.out.println("--> pathSource detail: "+pathSource);
						//System.out.println("--> pathTarget detail: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						adaptFile = new String[]{"query.sql"};
						adaptFileToStandard(file, "/filter/detail/xml/search", actionDirectory + "\\filter\\detail\\xml\\search", adaptFile, Robot.XML_TYPE);

						adaptFile = new String[]{"template.xml"};
						adaptFileToStandard(file, "/filter/detail/xml/view",  actionDirectory + "\\filter\\detail\\xml\\view", adaptFile, Robot.XML_TYPE);
						adaptFileToStandard(file, "/filter/detail/xml/notfound",  actionDirectory + "\\filter\\detail\\xml\\notfound", adaptFile, Robot.XML_TYPE);

						copyFolder(new File(pathTarget), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/xml"));
					}
					path = file.getPath() + "/txt";
					//System.out.println("Path --> "+path);
					newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/filter/txt";
						String pathTarget = file.getParent()+"\\filter\\txt";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						String[] adaptFile = new String[]{"query.sql"};
						adaptFileToStandard(file, "/filter/txt/search",  actionDirectory + "\\filter\\txt\\search", adaptFile, Robot.TXT_TYPE);

						adaptFile = new String[]{"template.txt"};
						adaptFileToStandard(file, "/filter/txt/view",  actionDirectory + "\\filter\\txt\\view", adaptFile, Robot.TXT_TYPE);

						//copy root parent directory
						copyFolder(new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/txt"), 
								new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/txt"));

						//detail

						pathSource = robotDirectory + "/smn_standard/filter/detail/txt";
						pathTarget = file.getParent()+"\\filter\\detail\\txt";

						//System.out.println("--> pathSource detail: "+pathSource);
						//System.out.println("--> pathTarget detail: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						adaptFile = new String[]{"query.sql"};
						adaptFileToStandard(file, "/filter/detail/txt/search",  actionDirectory + "\\filter\\detail\\txt\\search", adaptFile, Robot.TXT_TYPE);

						adaptFile = new String[]{"template.txt"};
						adaptFileToStandard(file, "/filter/detail/txt/view",  actionDirectory + "\\filter\\detail\\txt\\view", adaptFile, Robot.TXT_TYPE);

						copyFolder(new File(pathTarget), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/txt"));
					}
				}
				else if (file.getPath().endsWith("importexcel")) 
				{
					String path = file.getPath() + "/email";
					File newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						//String[] adaptFile = new String[]{"message.htm"};
						//adaptFileToStandard(file, "/filter/email", "filter\\email", adaptFile, Robot.EMAIL_TYPE);

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/filter/email";
						String pathTarget = file.getParent()+"\\importexcel\\email";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						//copy root parent directory
						copyFolder(new File(pathSource), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/email"));
					}
					path = file.getPath() + "\\xml";
					//System.out.println("Path --> "+path);
					newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/importexcel/xml";
						String pathTarget = file.getParent()+"\\importexcel\\xml";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						String[] adaptFile = new String[]{"template.xml"};
						adaptFileToStandard(file, "/importexcel/xml/search", actionDirectory + "\\importexcel\\xml\\search", adaptFile, Robot.XML_TYPE);

						adaptFileToStandard(file, "/importexcel/xml/notfound",  actionDirectory + "\\importexcel\\xml\\notfound", adaptFile, Robot.XML_TYPE);
					}
					path = file.getPath() + "/txt";
					//System.out.println("Path --> "+path);
					newPath = new File(path);
					if (!newPath.exists())
					{
						//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

						String robotDirectory = parameters.get(DIRECTORY_ROBOT);
						String pathSource = robotDirectory + "/smn_standard/importexcel/txt";
						String pathTarget = file.getParent()+"\\importexcel\\txt";

						//System.out.println("--> pathSource: "+pathSource);
						//System.out.println("--> pathTarget: "+pathTarget);

						copyFolder(new File(pathSource), new File(pathTarget));

						String[] adaptFile = new String[]{"template.txt"};
						adaptFileToStandard(file, "/importexcel/txt/search",  actionDirectory + "\\importexcel\\txt\\search", adaptFile, Robot.TXT_TYPE);
					}
				}
				else if (file.getName().endsWith("form"))
				{
					if (file.getParent().endsWith("detail") && (file.getParent().indexOf("filter")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/filter/detail/form", "detail\\form", adaptFile, Robot.FORM_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/form"));
						}
					}
					else if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/filter/editor/form", "editor\\form", adaptFile, Robot.FORM_TYPE);	

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/form"));
						}
					}
					else if (file.getParent().endsWith("filter"))
					{

						///System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/filter/form", "filter\\form", adaptFile, Robot.FORM_TYPE);
						}
					}
					else if (file.getParent().endsWith(actionDirectory))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] noCopyFiles = new String[]{"template.htm"};
							copyStandardFiles(file, "/form", actionDirectory + "\\form", noCopyFiles);

							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/form", actionDirectory + "\\form", adaptFile, Robot.FORM_TYPE);
						}
					}
					else if (file.getParent().endsWith("importexcel"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/importexcel/form", "importexcel\\form", adaptFile, Robot.FORM_TYPE);
						}
					}
					else if (file.getParent().endsWith("repgen"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/repgen/form", "repgen\\form", adaptFile, Robot.FORM_TYPE);
						}
					}
					if (file.getParent().endsWith("detail") && (file.getParent().indexOf("repgen")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/repgen/detail/form", "detail\\form", adaptFile, Robot.FORM_TYPE);

							adaptFile = new String[]{"query2.sql","field2.sql"};
							adaptFileToStandard(file, "/repgen/detail/form", "detail\\form", adaptFile, Robot.SEARCH_TYPE_DETAIL_REPGEN);
						}
					}
				}
				else if (file.getName().endsWith("view"))
				{
					if (file.getParent().endsWith("filter"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/filter/view", "filter\\view", adaptFile, Robot.VIEW_TYPE);
						}
					}
					else if (file.getParent().endsWith(actionDirectory))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/view", actionDirectory + "\\view", adaptFile, Robot.VIEW_TYPE);	
						}
					}
					else if (file.getParent().endsWith("importexcel"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/importexcel/excelerrorview", "importexcel\\excelerrorview", adaptFile, Robot.VIEW_TYPE);
						}
					}
					else if (file.getParent().endsWith("repgen"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
							String[] adaptFile = new String[]{"template.htm"};
							adaptFileToStandard(file, "/repgen/view", "repgen\\view", adaptFile, Robot.VIEW_TYPE);
						}
					}
					else
					{
						if ((file.getParent().indexOf("editor")!=-1) && (file.getParent().indexOf("filter")!=-1))
						{
							if (file.getParent().indexOf("email")==-1)
							{
								actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
								//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

								String[] adaptFile = new String[]{"template.htm"};
								adaptFileToStandard(file, "/filter/editor/detail/view", actionDirectoryDetail + "\\view", adaptFile, Robot.VIEW_TYPE);

								//copy root parent directory
								copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail + "/view"));
							}
						}
					}
				}
				else if (file.getName().endsWith("pdf"))
				{
					if (file.getParent().endsWith("detail") && (file.getParent().indexOf("filter")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/filter/detail/pdf", "detail\\pdf", adaptFile, Robot.PDF_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/pdf"));
						}
					}
					else if (file.getParent().endsWith("detail") && (file.getParent().indexOf("repgen")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/repgen/detail/pdf", "detail\\pdf", adaptFile, Robot.PDF_DETAIL_TYPE_REPGEN);
						}
					}
					else if (file.getParent().endsWith(actionDirectory))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/pdf", actionDirectory + "\\pdf", adaptFile, Robot.PDF_TYPE);	
						}						
					}
					else if (file.getParent().endsWith("filter"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							///System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/filter/pdf", "filter\\pdf", adaptFile, Robot.PDF_TYPE);

							//copy root parent directory
							//copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/pdf"));
						}
					}
					else if (file.getParent().endsWith("importexcel"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/importexcel/pdf", "importexcel\\pdf", adaptFile, Robot.PDF_TYPE);
						}
					}
					else if (file.getParent().endsWith("repgen"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("-----> Processing File REPGEN PDF: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/repgen/pdf", "repgen\\pdf", adaptFile, Robot.PDF_TYPE_REPGEN);
						}
					}
				}
				else if (file.getName().endsWith("excel"))
				{
					if (file.getParent().endsWith("filter"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/filter/excel", "filter\\excel", adaptFile, Robot.EXCEL_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/detail/excel"));
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/detail/excel"));

							adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, null, "filter\\detail\\excel", adaptFile, Robot.EXCEL_DETAIL_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/excel"));
						}
					}
					else if (file.getParent().endsWith("detail") && (file.getParent().indexOf("repgen")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/repgen/detail/excel", "detail\\excel", adaptFile, Robot.EXCEL_DETAIL_TYPE_REPGEN);
						}
					}
					else if (file.getParent().endsWith("importexcel"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/importexcel/excel", "importexcel\\excel", adaptFile, Robot.EXCEL_TYPE);

							copyFolder(new File(parameters.get(DIRECTORY_ROBOT) + "/smn_standard/importexcel/excel/template"), 
									new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/importexcel/excel/template"));

							adaptFileToStandard(file, "/importexcel/excel/template", "importexcel\\excel\\template", adaptFile, Robot.EXCEL_TEMPLATE_TYPE);
						}
					}
					else if (file.getParent().endsWith("repgen"))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							String[] adaptFile = new String[]{"config.xml"};
							adaptFileToStandard(file, "/repgen/excel", "repgen\\excel", adaptFile, Robot.EXCEL_TYPE_REPGEN);
						}
					}
				}
				else if (file.getName().endsWith("edit"))
				{
					if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1) && (file.getName().indexOf("detail")==-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//System.out.println("Processing File: " + file.getName() + " - Parent Directory: " + parentDirectory + " - Full Path: " + file.getParent());

							String[] adaptFile = new String[]{"script.js"};
							//System.out.println("JS MD NotExists --> " + file.getAbsoluteFile().getAbsolutePath());
							adaptFileToStandard(file, "/filter/editor/edit", "editor\\edit", adaptFile, Robot.JS_TYPE);							

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/edit"));
						}
					}
					else
					{
						if ((file.getParent().indexOf("editor")!=-1) && (file.getParent().indexOf("filter")!=-1) && (file.getParent().indexOf("email")==-1))
						{
							actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
							//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

							String[] adaptFile = new String[]{"script.js"};
							adaptFileToStandard(file, "/filter/editor/detail/edit", actionDirectoryDetail + "\\edit", adaptFile, Robot.JS_TYPE);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail + "/edit"));
						}						
					}
				}
				else if (file.getName().endsWith("addnew"))
				{
					if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/addnew"));
						}
					}
				}
				else if (file.getParent().substring(file.getParent().lastIndexOf("\\")+1).startsWith("detail"))
				{
					if (file.getParent().endsWith("editor") && (file.getParent().indexOf("filter")!=-1))
					{
						if (file.getParent().indexOf("email")==-1)
						{
							actionDirectoryDetail = file.getParent().substring(file.getParent().lastIndexOf("\\")+1);
							//System.out.println("Tabla Detalle --> " + actionDirectoryDetail);

							//copy root parent directory
							copyFolder(new File(file.getPath()), new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/editor/" + actionDirectoryDetail));
						}
					}
				}
				else if (file.getName().endsWith("error") && !file.getName().endsWith("excelerror"))
				{
					if (file.getParent().indexOf("importexcel")!=-1)
					{
						copyStandardFiles(file, "/importexcel/error", "importexcel\\error", COPY_FILES);
					}
				}
				else if (file.getName().endsWith("excelerror"))
				{
					if (file.getParent().indexOf("importexcel")!=-1)
					{
						copyStandardFiles(file, "/importexcel/excelerror", "importexcel\\excelerror", COPY_FILES);
					}
				}
				else if (file.getName().endsWith("excelok"))
				{
					if (file.getParent().indexOf("importexcel")!=-1)
					{
						copyStandardFiles(file, "/importexcel/excelok", "importexcel\\excelok", COPY_FILES);
					}
				}
				actionAdapterProcess(file.getName(), file.listFiles()); // Calls same method again.
			} 
		}
	}

	public void adaptFileToStandard(File file, String source, String target, String[] adaptFiles, String type) throws Throwable 
	{
		String robotDirectory = parameters.get(DIRECTORY_ROBOT);
		String pathSource = robotDirectory + "/smn_standard" + source;

		String parentDir = file.getPath().substring(0,file.getPath().lastIndexOf("\\"));
		parentDir = parentDir.substring(0,parentDir.lastIndexOf("\\"));

		String pathTarget = parentDir + "\\" + target;

		//System.out.println("Path Source: "+pathSource);
		//System.out.println("Path Target: "+pathTarget);

		String disabledColumn = "";

		//System.out.println("Type: " + type);

		int n = adaptFiles.length;
		for(int i = 0; i < n; i++)
		{
			BufferedReader inSrc = null;
			BufferedReader inTrg = null;
			FileWriter out = null;
			try 
			{				
				if ((type.compareTo(Robot.SEARCH_TYPE_XML_REPGEN) == 0) && (adaptFiles[i].indexOf("validator") != -1))
				{
					pathTarget = pathTarget.substring(0, pathTarget.indexOf("repgen"));
					pathTarget += "filter\\editor\\insert";
					//System.out.println("INSERT PATH SEARCH_TYPE_XML_REPGEN --> "+pathTarget);
				}

				/*File tmp = new File( pathTarget );
				if (!tmp.exists())
				{
					tmp.mkdirs();
				}*/
				inTrg = new BufferedReader(new FileReader(pathTarget+"\\"+adaptFiles[i]));

				//template.htm in /filter/detail/form
				String label = "";
				String labelDetail = "";
				String htmlTable = "";
				String pdfColumn = "";
				String jsColumn = "";
				String recordsetName = "";
				String excelColumn = "";
				String pdfFilterColumn = "";
				String filterSearch = "";
				String widthColumns = "";
				String headerColumns = "";
				String dataColumns = "";
				String parametersColumn = "";
				String xmlValidators = "";
				String xmlTransformers = "";
				String xmlExcelDetail = "";
				String prefix = "";
				String keypressDate = "";
				String pdfRecordsets = "";
				String pdfHeaders = "";
				String numericfields = "";
				String keypressDateDetail = "";
				String htmlTableDetail = "";
				String registerLoaded = "";
				String sqlSearch = "";
				String recordset = "<?xml version='1.0' encoding='ISO-8859-1'?>\n" +
						"<recordset>\n";
				String refFields = "";
				String refValidators = "";
				String sheetRef = "";
				
				int calculateCellPosx = 0;
				int calculateColspan = 0;

				boolean bannerFound = false;
				boolean htmlTableFound = false;
				boolean fieldsDisabled = false;
				boolean hasFilter = false;
				boolean htmlTableMainFound = false;
				boolean pdfHeaderFound = false;
				boolean hasDetail = false;
				boolean htmlTableDetailFound = false;
				boolean toolbarDetail = false;
				boolean readJSMD = false;
				boolean hasHidden = false;
				boolean readyRecordset = false;
				boolean addnewEdit = false;
				while(true)
				{
					String line = inTrg.readLine();
					if (line == null)
					{
						break;
					}
					if (type.compareTo(Robot.FORM_TYPE) == 0)
					{
						//String prefixTable = util.getPrefixColumnTable();
						//System.out.println("PREFIX TABLE --> query-"+prefixTable+"_");
						if ((!sqlFormReaded) && (pathTarget.indexOf("editor") != -1))
						{
							System.out.println("PATH FORM --> "+pathTarget);
							File files = new File(pathTarget);
							File[] fileList = files.listFiles();
							for(int k = 0; k < fileList.length; k++)
							{
								String filename = fileList[k].getName();
								if (!filename.startsWith("user-info") && !filename.startsWith("query-smn_")) // && filename.startsWith("query-"+prefixTable+"_"))
								{
									if (filename.endsWith(".sql"))
									{
										System.out.println("SQL File --> "+fileList[k].getPath());

										BufferedReader br = new BufferedReader(new FileReader(fileList[k].getPath()));
										while(true)
										{
											String sqlLine = br.readLine();
											System.out.println("SQL READLINE --> "+sqlLine);
											if (sqlLine == null)
											{
												break;
											}
											String fieldname = sqlLine.substring(sqlLine.indexOf(","), sqlLine.indexOf("from"));
											fieldname = fieldname.replaceAll(",", "");
											fieldname = fieldname.replaceAll("'", "");
											fieldname = fieldname.replaceAll("-", "");
											fieldname = fieldname.replaceAll("as item", "");
											System.out.println("SQL READLINE TO PROCESS--> "+fieldname);

											fieldJoin = filename;
											fieldJoin = fieldJoin.replaceAll("query-", "");
											fieldJoin = fieldJoin.replaceAll(".sql", "");
											fieldJoin = fieldJoin.trim();
											System.out.println("FIELD JOIN --> "+fieldJoin);

											if (fieldname.indexOf("||") != -1)
											{
												fieldId = fieldname.substring(0, fieldname.indexOf("||")).trim();
												fieldItem = fieldname.substring( fieldname.lastIndexOf("||")+2, fieldname.length()).trim();
											}
											else
											{
												String newFieldId = sqlLine.substring(sqlLine.indexOf(" "), sqlLine.indexOf("as id")-1);
												fieldId = newFieldId.substring(newFieldId.lastIndexOf(".")+1,  newFieldId.length()).trim();
												fieldItem = fieldname.substring( fieldname.lastIndexOf(".")+1, fieldname.length()).trim();
											}

											System.out.println("FIELD ID --> "+fieldId);
											System.out.println("FIELD ITEM --> "+fieldItem);

											String tablaname = sqlLine.substring(sqlLine.indexOf("from"));
											tablaname = tablaname.replaceAll("from", "");

											java.util.StringTokenizer st = new java.util.StringTokenizer(tablaname," ");

											tableJoin = st.nextToken();

											System.out.println("TABLE JOIN --> "+tableJoin);
											RobotJoin robotJoin = null;	
											boolean found = false;
											for(int j = 0; j < robotJoinList.size(); j++)
											{
												RobotJoin rJoin = robotJoinList.elementAt(j);
												if (rJoin.getFieldItem().compareTo(fieldItem) == 0) 
												{
													found = true;
													break;
												}
											}
											if (found)
											{
												fieldItem += " as " +  fieldItem.substring(fieldItem.lastIndexOf(".")+1) + "2,";
											}
											//verify if the field reference is NULLABLE
											Hashtable<String,Vector<RobotMetadata>> tableMetadataList = util.getMetadataList();
											Vector<RobotMetadata> metadataList = tableMetadataList.get(actionDirectory);
											boolean fieldJoinIsNullable = false;
											for(int t = 0; t < metadataList.size(); t++)
											{
												RobotMetadata metadata = metadataList.elementAt(t);
												String column = metadata.getColumn();
												if (column.compareTo(fieldJoin) == 0)
												{
													String isNullable = metadata.getNullable();
													if ((isNullable.compareTo("yes") == 0) || (isNullable.compareTo("si") == 0))
													{
														fieldJoinIsNullable = true;
														break;
													}
												}
											}
											//patch - solve postal zone without description
											if (fieldItem.compareTo("") == 0)
											{
												fieldItem = fieldId;
											}
											robotJoin = new RobotJoin(fieldJoin, fieldId, fieldItem, tableJoin, fieldJoinIsNullable);
											robotJoinList.add(robotJoin);
										}
										if (br != null) br.close();
									}

								};
							}
							sqlFormReaded = true;
						}
						if (line.indexOf("addnew/edit") != -1)
						{
							addnewEdit = true;
						}
						if ((line.indexOf("input") != -1) && (addnewEdit))
						{							
							if (line.indexOf("telefono_fijo") != -1)
							{
								String lb = labels.getLabel("b_format_phone_local", "es");
								if (lb == null)
								{
									RobotUtil.logger.warn("Robot detected you must to define this label ${lbl:b_format_phone_local} in "+fileLabelPath);
								}
								line = line.substring(0, line.indexOf(">"));
								line += " " + placeholderHomePhone + " >\n";
							}
							else if (line.indexOf("telefono_movil") != -1)
							{
								String lb = labels.getLabel("b_format_phone_mobile", "es");
								if (lb == null)
								{
									RobotUtil.logger.warn("Robot detected you must to define this label ${lbl:b_format_phone_mobile} in "+fileLabelPath);
								}
								line = line.substring(0, line.indexOf(">"));
								line += " " + placeholderHomeMobile + " >\n";								
							}
							else if (line.indexOf("email") != -1)
							{
								String lb = labels.getLabel("b_email_format", "es");
								if (lb == null)
								{
									RobotUtil.logger.warn("Robot detected you must to define this label ${lbl:b_email_format} in "+fileLabelPath);
								}
								line = line.substring(0, line.indexOf(">"));
								line += " " + placeholderEmail + " >\n";								
							}
							else if ((line.indexOf("codigo_aba") != -1) || (line.indexOf("codigo_swift") != -1))
							{
								String lb = labels.getLabel("b_integer_range_aba_swift", "es");
								if (lb == null)
								{
									RobotUtil.logger.warn("Robot detected you must to define this label ${lbl:b_integer_range_aba_swift} in "+fileLabelPath);
								}
								line = line.substring(0, line.indexOf(">"));
								line += " " + placeholderAbaSwift + " >\n";								
							}
						}
						if (line.indexOf("form2") != -1)
						{
							hasDetail = true;
						}
						if (hasDetail)
						{
							//maxlength="2" --> integer length
							if ((line.indexOf("maxlength=\"10\"") != -1) && (line.indexOf("class=\"numeric\"") != -1))
							{
								String field = line.substring(line.indexOf("=")+2, line.indexOf("name")-2);
								field = "var txt_" + field + " = new NumericInput(document.form2." + field + ");";
								numericfields += field + "\n";
							}
							if (line.indexOf("class=\"date\"") != -1)
							{
								String field = line.substring(line.indexOf("=")+2, line.indexOf("name")-2);
								field = "document.form2." + field + ".onkeypress = keypressDate;\n";
								keypressDateDetail += field + "\n";
							}
							if (line.indexOf("h1") != -1)
							{
								labelDetail = line.substring(line.indexOf("h1")+2, line.indexOf("</h1>"));
							}
							if (htmlTableDetailFound)
							{
								htmlTableDetail += line + "\n";
								if (line.indexOf("</table>") != -1)
								{
									htmlTableDetailFound = false;
								}
							}
							if ((line.indexOf("<table") != -1) && (!htmlTableDetailFound))
							{
								htmlTableDetail += line + "\n";
								htmlTableDetailFound = true;
							}
							if ((line.indexOf("input") != -1) && (!readyRecordset))
							{		
								String id = "";
								if (line.indexOf("hidden") != -1)
								{					
									hasHidden = true;
									continue;
								}
								if (line.indexOf("<!--")!=-1)
								{
									continue;
								}
								if (line.indexOf("input")!=-1)
								{
									//System.out.println("--> " + line);
									id = line.substring(line.indexOf("\"")+1, line.indexOf("2")+1);
									//System.out.println("ID = " + id);
									if ((hasHidden) && (line.indexOf("saveDetail2") == -1))
									{
										Vector<RobotMetadata> metadataListFK = util.getMetadataFKList().get(actionDirectory);
										if (metadataListFK == null)
										{
											RobotUtil.logger.info("");
											RobotUtil.logger.info("===============================================================================================================");
											RobotUtil.logger.info("=== Robot was stopped. Please, check database schema configured in to robot: " + util.getDatabaseSchema() + " ===");
											RobotUtil.logger.info("===============================================================================================================");
											if (inSrc  != null)
											{
												inSrc.close();
											}
											if (inTrg  != null)
											{
												inTrg.close();
											}
											if (out  != null)
											{
												out.close();
											}
											System.exit(0);
										}
										for(int j = 0; j < metadataListFK.size(); j++)
										{
											RobotMetadata elem = metadataListFK.elementAt(j);
											//System.out.print("Column MD FK --> "+elem.getColumn()+"  ID: "+id.substring(0, id.length()-1));
											if (elem.getColumn().compareTo(id.substring(0, id.length()-1)) == 0)
											{
												String requiredData = (elem.getNullable().compareTo("si") == 0 || 
														elem.getNullable().compareTo("yes") == 0
														? "true"
																: "false");
												recordset += "\t<parameter id='" + id + "' ";
												recordset += "type='integer' ";
												recordset += "required='" + requiredData + "' ";
												recordset += "label='' />\n";	

												recordset += "\t<parameter id='" + id +  "_name' ";
												recordset += "type='varchar' ";
												recordset += "required='" + requiredData + "' ";
												recordset += "label='' />\n";	
												break;
											}
										}
									}
									else
									{
										if (line.indexOf("text") != -1)
										{
											Vector<RobotMetadata> metadataListFK = util.getMetadataFKList().get(actionDirectory);
											if (metadataListFK == null)
											{
												RobotUtil.logger.info("");
												RobotUtil.logger.info("===============================================================================================================");
												RobotUtil.logger.info("=== Robot was stopped. Please, check database schema configured in to robot: " + util.getDatabaseSchema() + " ===");
												RobotUtil.logger.info("===============================================================================================================");
												if (inSrc  != null)
												{
													inSrc.close();
												}
												if (inTrg  != null)
												{
													inTrg.close();
												}
												if (out  != null)
												{
													out.close();
												}
												System.exit(0);
											}
											for(int j = 0; j < metadataListFK.size(); j++)
											{
												RobotMetadata elem = metadataListFK.elementAt(j);
												//System.out.println("Column MD FK --> "+elem.getColumn()+"  ID: "+id.substring(0, id.length()-1));
												if (elem.getColumn().compareTo(id.substring(0, id.length()-1)) == 0)
												{
													String requiredData = (elem.getNullable().compareTo("si") == 0 || 
															elem.getNullable().compareTo("yes") == 0
															? "true"
																	: "false");
													if (line.indexOf("date") != -1)
													{
														recordset += "\t<parameter id='" + id +  "' ";
														recordset += "type='date' ";
														recordset += "required='" + requiredData + "' ";
														recordset += "label='' />\n";
													}
													else
													{
														recordset += "\t<parameter id='" + id +  "' ";
														recordset += "type='varchar' ";
														recordset += "required='" + requiredData + "' ";
														recordset += "label='' />\n";
													}
													break;
												}
											}
										}
									}
									if (line.indexOf("saveDetail2") != -1)
									{
										recordset += "</recordset>\n";
										readyRecordset = true;

										recordsetInMemory = recordset;
										validatorRecordsetInMemory = "\t<custom-validator \n" +
												"\t\tclassname='dinamica.validators.CreateRecordset' \n" + 
												"\t\ton-error-label='' \n" +
												"\t\tdefinition='recordset.xml' \n"+ 
												"\t\tsessionID='detail-" + util.getCurrentTableDetail().elementAt(0) + ".sql'/> \n";

										System.out.println(recordset);
										System.out.println();
										System.out.println(validatorRecordsetInMemory);
									}
								}
							}
						}
						if (bannerFound)
						{
							label = line.trim();
							label = label.substring(label.indexOf(":")+1, label.length()-1);
							bannerFound = false;
						}					
						if (((line.indexOf("bannerDialog") != -1) || (line.indexOf("banner") != -1)) && (!bannerFound))
						{
							bannerFound = true;
						}
						if (htmlTableFound)
						{
							htmlTable += line + "\n";
							if (line.indexOf("</table>") != -1)
							{
								htmlTableFound = false;
							}
						}
						if ((line.indexOf("calendarOpen") != -1) && (pathSource.indexOf("repgen") == -1))
						{
							prefix = line.substring(line.indexOf("'")+1, line.indexOf("_"));
							keypressDate = 	"document.form1." + prefix + "_fecha_registro_ini.onkeypress = keypressDate;\n" +
									"\tdocument.form1." + prefix + "_fecha_registro_fin.onkeypress = keypressDate;\n";
						}
						if ((line.indexOf("<table") != -1) && (!htmlTableFound) && (!hasDetail))
						{
							htmlTable += line + "\n";
							htmlTableFound = true;
						}
						if ((line.indexOf("<table>") != -1) && (!htmlTableMainFound) && (!hasDetail))
						{
							htmlTableMainFound = true;
						}
						if (htmlTableMainFound)
						{
							filterSearch += line + "\n";
							if (line.indexOf("</table>") != -1)
							{
								htmlTableMainFound = false;
							}
						}
					}
					else if (type.compareTo(Robot.VIEW_TYPE) == 0)
					{
						if (line.indexOf("toolbar") != -1)
						{
							toolbarDetail = true;
							continue;
						}
						if (line.indexOf("<col") != -1)
						{
							widthColumns += line + "\n";
							calculateColspan++;
						}
						if (line.indexOf("<th class") != -1)
						{
							headerColumns += line + "\n";
						}
						if (line.indexOf("<th style") != -1)
						{
							headerColumns += line + "\n";
						}
						else
						{
							toolbarDetail = false;
						}
						if ((line.indexOf("<td") != -1) && (line.indexOf("<img src=") == -1))
						{
							dataColumns += line + "\n";
						}
						if (line.indexOf("Registros cargados en") != -1)
						{
							registerLoaded = line.substring(line.indexOf("$"), line.indexOf("}")+1);
						}
					}
					else if (type.compareTo(Robot.PDF_TYPE) == 0)
					{
						//System.out.println("PDF Line --> "+line);
						if (line.indexOf("</pdf-report>") != -1)
						{	
							break;
						}
						if (line.indexOf("pdf-title") != -1)
						{	
							line = line.trim();			
							if (line.indexOf("Errores") != -1)
							{
								label = line.substring(line.indexOf("Errores"), line.lastIndexOf("</pdf-title>")).trim();
							}
							else
							{
								label = line.substring(line.indexOf(":")+1, line.lastIndexOf("}")).trim();
								actionLabel = label;
								titleLabelTemplate = line.substring(line.indexOf("$"), line.indexOf("}")+1);
							}							
						}
						if (line.indexOf("<recordset") != -1)
						{
							pdfRecordsets += line + "\n";
							continue;
						}
						if (line.indexOf("filter.params") != -1)
						{
							hasFilter = true;
							continue;
						}
						if (pdfHeaderFound)
						{
							pdfHeaders += line + "\n";
							if (line.indexOf("</record>") != -1)
							{
								htmlTableFound = false;
							}
						}
						if ((line.indexOf("<record") != -1) && (!pdfHeaderFound) && (!hasFilter))
						{
							pdfHeaders += line + "\n";
							pdfHeaderFound = true;
							continue;
						}
						if ((line.indexOf("<col") != -1) && (!hasFilter))
						{
							if (line.indexOf("Fila") != -1)
							{
								line = line.replaceFirst("Fila", "\\$\\{lbl\\:b_row\\}");
							}
							if (line.indexOf("Columna") != -1)
							{
								line = line.replaceFirst("Columna", "\\$\\{lbl\\:b_column\\}");
							}
							if (line.indexOf("Descripción del error") != -1)
							{
								line = line.replaceFirst("Descripción del error", "\\$\\{lbl\\:b_error_description\\}");
							}
							String colname = line.substring(line.indexOf("'")+1);
							colname = colname.substring(0, colname.indexOf("'"));

							String labelRef = line.substring(line.indexOf("$"));
							labelRef = labelRef.substring(0, labelRef.indexOf("}")+1);
							
							if (!columTemplateAssigned)
							{
								excelColumnTemplate += "\t\t<col id='" + colname + "' label='" + labelRef + "' />\n";
								//If arrived to the end set true
								if (line.indexOf("fecha_registro") != -1)
								{
									columTemplateAssigned = true;
								}
							}
							
							System.out.println("REF Colname: " + colname + " | Label: " + labelRef);

							int k = robotJoinList.size();
							for(int j = 0; j < k; j++)
							{
								RobotJoin rj = robotJoinList.elementAt(j);
								String refField = rj.getFieldJoin();
								if (refField.compareTo(colname) == 0)
								{
									System.out.println("REF Colname Join --> " + colname);
									rj.setLabel(labelRef);
									robotJoinList.set(j, rj);
									break;
								}
							}
														
							pdfColumn += line + "\n";
						}
						else if ((line.indexOf("<col") != -1) && (hasFilter))
						{
							pdfFilterColumn += line + "\n";
						}
						else
						{
							hasFilter = false;	
						}
						//it is case when you use parent pdf
						if (line.indexOf("<table") != -1)
						{				
							line = line.trim();
							recordsetName = line.substring(line.indexOf("-")+1, line.indexOf("."));
							//System.out.println("recordsetName --> "+recordsetName);
						}						
					} 
					else if (type.compareTo(Robot.JS_TYPE) == 0)
					{
						if ((line.indexOf("document.form1") != -1) || (line.indexOf("document.form2") != -1))
						{
							jsColumn += line + "\n";
							Vector<Object> listDisabled = util.getRobotActionProcess().getInstructionsToRun();
							for(int j = 0; j < listDisabled.size(); j++)
							{
								Object object= listDisabled.elementAt(j);
								if (object instanceof RobotDisabledFields)
								{
									RobotDisabledFields disabledField= (RobotDisabledFields)object;
									for(int k = 0; (k < disabledField.size()) && (!fieldsDisabled); k++)
									{
										String field = disabledField.elementAt(k);
										if (line.indexOf(field) != -1)
										{
											String fieldForm = line.substring(0, line.lastIndexOf("."));
											fieldForm += ".disabled=true;";
											disabledColumn += fieldForm + "\n";
											fieldsDisabled = true;
										}
									}
								}
							}
						}
						else
						{
							//patch - getting combos
							if (line.indexOf("Modificar") != -1)
							{
								line = line.replaceFirst("Modificar", "\\$\\{lbl\\:b_modify_button\\}");
								jsColumn += line + "\n";
							}
							else if (line.indexOf("Editar registro") != -1)
							{
								line = line.replaceFirst("Editar registro", "\\$\\{lbl\\:b_edit_record\\}");
								jsColumn += line + "\n";
							}
							else
							{
								jsColumn += line + "\n";
							}
						}
						if (line.indexOf("detailView") != -1)
						{
							readJSMD = true;
						}
					}
					else if ((type.compareTo(Robot.EXCEL_TYPE) == 0) || (type.compareTo(Robot.EXCEL_TEMPLATE_TYPE) == 0))
					{
						if (line.indexOf("<excel") != -1)
						{	
							if (line.indexOf(":") != -1)
							{
								line = line.trim();
								label = line.substring(line.indexOf(":")+1, line.indexOf("}"));
							}
							if (line.indexOf("-") != -1)
							{
								recordsetName = line.substring(line.indexOf("-")+1, line.indexOf("."));
							}
						}
						else if (line.indexOf("<col") != -1)
						{
							if (line.indexOf("Fila") != -1)
							{
								line = line.replaceFirst("Fila", "\\$\\{lbl\\:b_row\\}");
							}
							if (line.indexOf("Columna") != -1)
							{
								line = line.replaceFirst("Columna", "\\$\\{lbl\\:b_column\\}");
							}
							if (line.indexOf("Descripción del error") != -1)
							{
								line = line.replaceFirst("Descripción del error", "\\$\\{lbl\\:b_error_description\\}");
							}
							
							excelColumn += line + "\n";
							++calculateCellPosx;
						}
					}
					else if (type.compareTo(Robot.EXCEL_DETAIL_TYPE) == 0)
					{
						if (line.indexOf("query-smn") != -1)
						{
							line = line.replaceFirst(line.substring(line.indexOf("-"), line.indexOf(".")), "");
						}
						xmlExcelDetail += line + "\n";
					}
					else if (type.compareTo(Robot.SEARCH_TYPE) == 0)
					{
						if (line.indexOf("select") != -1)
						{
							sqlSearch += line + "\n";
							line = "\t\t" + line;
							sqlSearch += util.getDatabaseSchema() + "." + util.getCurrentTable() + "." + util.getCurrentTable() + "_id,\n";
							continue;
						}
						sqlSearch += line + "\n";						
					}
					else if (type.compareTo(Robot.SEARCH_TYPE_XML_REPGEN) == 0)
					{						
						//System.out.println("SOURCE SEARCH_TYPE_XML_REPGEN -> " + pathTarget+"\\"+adaptFiles[i]);
						if (line.indexOf("<parameter") != -1)
						{
							if (line.indexOf("true") != -1)
							{
								line = line.replaceAll("true", "false");	
							}
							parametersColumn += line + "\n";

							String sql = "";
							String validators = "";
							String transformers = "";
							String classname="dinamica.validators.DuplicatedKeyValidator";
							String onErrorLabel="${lbl:b_record_not_duplicated}.";

							//System.out.println("SEARCH_TYPE_XML_REPGEN --> "+line);
							String column = line.substring(line.indexOf("'")+1, line.indexOf("type")-1);
							column = column.substring(0, column.indexOf("'")).trim();
							if (column.indexOf("codigo") != -1)
							{
								sql="buscarCodigo.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";								
								xmlValidators += validators;
								
								//This process i going to do with a copy from filter/editor/insert/
								//createValidatorFile(pathTarget, column, sql);
								
								transformers += "\t<custom-validator\n" + 
												"\t\tclassname='dinamica.validators.SQLPatternTransformer'\n" + 
												"\t\tparameter='" + column + "'\n" + 
												"\t\trule='contains' />\n\n";
								xmlTransformers += transformers;
							}
							else if (column.indexOf("nombre") != -1)
							{
								sql="buscarNombre.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";
								xmlValidators += validators;
								
								//createValidatorFile(pathTarget, column, sql);
								
								transformers += "\t<custom-validator\n" + 
										"\t\tclassname='dinamica.validators.SQLPatternTransformer'\n" + 
										"\t\tparameter='" + column + "'\n" + 
										"\t\trule='contains' />\n\n";
								xmlTransformers += transformers;
							}
							else if (column.indexOf("descripcion") != -1)
							{
								sql="buscarDescripcion.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";
								xmlValidators += validators;
								
								//createValidatorFile(pathTarget, column, sql);
								
								transformers += "\t<custom-validator\n" + 
										"\t\tclassname='dinamica.validators.SQLPatternTransformer'\n" + 
										"parameter='" + column + "'\n" + 
										"\t\trule='contains' />\n\n";
								xmlTransformers += transformers;
							}
							else if (line.indexOf("varchar") != -1)
							{
								transformers += "\t<custom-validator\n" + 
										"\t\tclassname='dinamica.validators.SQLPatternTransformer'\n" + 
										"\t\tparameter='" + column + "'\n" + 
										"\t\trule='contains' />\n\n";
								xmlTransformers += transformers;								
							}
						}
					}
					else if (type.compareTo(Robot.INSERT_TYPE) == 0)
					{
						if (adaptFiles[i].compareTo("excel.xml") == 0)
						{
							if (line.indexOf("\"") != -1)
							{
								line = line.replaceAll("\"", "'");
							}
							if (line.indexOf("<parameter") != -1)
							{
								//System.out.print("INSERT - Import Excel References --> " + line);
								String id = line.substring(line.indexOf("id")+4);
								//System.out.print(" | ID Parcial: --> " + id);
								id = id.substring(0, id.indexOf("'")).trim();
								//System.out.println(" | ID Final: --> " + id);
								for(int k = 0; k < robotJoinList.size(); k++)
								{
									RobotJoin fieldJoin = robotJoinList.elementAt(k);
									String field = fieldJoin.getFieldJoin();
									//System.out.println("INSERT - Import Excel References (Id: " + id + ", FieldJoin: " + field + ")");
									if (field.compareTo( id ) == 0)
									{
										line = line.replaceFirst(id, id + "_ref");
										refFields += line + "\n";
										refFields = refFields.replaceFirst("true", "false");

										refValidators += "\t<custom-validator \n" +
												"\t\tclassname='dinamica.validators.GetColValueValidator' \n" + 
												"\t\ton-error-label='${lbl:b_field_does_not_registered}: " + id + "' \n" + 
												"\t\tparameter='" + id + "_ref' \n" + 
												"\t\tsql='" + id + ".sql' \n" +
												"\t\tid='" + id + "_ref'/>\n\n";

										String tJoin = fieldJoin.getTableJoin();
										String fieldId = tJoin + "." + tJoin.substring(tJoin.indexOf(".")+1) + "_id as " + id + "_ref";
										String condition = "upper(" + fieldJoin.getFieldItem() + ") = upper(${fld:" + id + "_desc})";
										String refSQL = "select \n" + 
												"\t" + fieldId + " \n" +
												"from   \n" + 
												"\t" + tJoin + " \n" +
												"where  \n" + 
												"\t" + condition + "\n";
										FileWriter fw = new FileWriter( pathTarget + "\\" + id + ".sql");
										fw.write(refSQL);
										if (fw != null)
										{
											fw.close();
										}

										line = line.replaceFirst(id, id + "_desc");
										line = line.replaceFirst("integer", "varchar");

										break;
									} 
									else
									{
										if ((line.indexOf("_req_") != -1) || (line.indexOf("inactivo") != -1) || (line.indexOf("maneja") != -1))
										{
											line = line.replaceFirst(id, id + "_ref");
											refFields += line + "\n";
											refFields = refFields.replaceFirst("true", "false");

											refValidators += "\t<custom-validator \n" +
													"\t\tclassname='dinamica.validators.GetColValueValidator' \n" + 
													"\t\ton-error-label='${lbl:b_field_does_not_registered}: " + id + "' \n" + 
													"\t\tparameter='" + id + "_ref' \n" + 
													"\t\tsql='" + id + ".sql' \n" +
													"\t\tid='" + id + "_ref'/>\n\n";

											String tJoin = fieldJoin.getTableJoin();
											String fieldId = tJoin + "." + tJoin.substring(tJoin.indexOf(".")+1) + "_id as " + id + "_ref";
											String condition = "upper(" + fieldJoin.getFieldItem() + ") = upper(${fld:" + id + "_desc})";
											String refSQL = "select \n" + 
													"\t" + fieldId + " \n" +
													"from   \n" + 
													"\t" + tJoin + " \n" +
													"where  \n" + 
													"\t" + condition + "\n";
											FileWriter fw = new FileWriter( pathTarget + "\\" + id + ".sql");
											fw.write(refSQL);
											if (fw != null)
											{
												fw.close();
											}

											line = line.replaceFirst(id, id + "_desc");
											line = line.replaceFirst("integer", "varchar");	
											line = line.replaceFirst("maxlen='1'", "maxlen='2'");

											break;
										}
									}

								}
							}
						}
						if (line.indexOf("<parameter") != -1)
						{
							parametersColumn += line + "\n";

							String sql = "";
							String validators = "";
							String classname="dinamica.validators.DuplicatedKeyValidator";
							String onErrorLabel="${lbl:b_record_not_duplicated}.";

							String column = line.substring(line.indexOf("'")+1, line.indexOf("type")-1);
							column = column.substring(0, column.indexOf("'")).trim();
							if (column.indexOf("codigo") != -1)
							{
								sql="buscarCodigo.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";
								createValidatorFile(pathTarget, column, sql);
								xmlValidators += validators;
							}
							else if (column.indexOf("nombre") != -1)
							{
								sql="buscarNombre.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";
								createValidatorFile(pathTarget, column, sql);
								xmlValidators += validators;
							}
							else if (column.indexOf("descripcion") != -1)
							{
								sql="buscarDescripcion.sql";
								validators += "\t<custom-validator\n" + 
										"\t\tclassname='" + classname + "'\n" +
										"\t\t\ton-error-label='" + onErrorLabel + "'\n" +
										"\t\t\tsql='" + sql + "'\n" +
										"\t\t\tid='"  + column + "'/>\n\n";
								createValidatorFile(pathTarget, column, sql);
								xmlValidators += validators;
							}
						}
					}
					else if (type.compareTo(Robot.UPDATE_TYPE) == 0)
					{
						if (line.indexOf("<parameter") != -1)
						{
							parametersColumn += line + "\n";
						}
					}
				}
				calculateCellPosx /= 2;

				String htmlNew = "";
				String xmlNew  = "";
				String txtNew  = "";
				String jsNew  = "";	
				if (source != null)
				{
					if (type.compareTo(Robot.SEARCH_TYPE) == 0)
					{
						inSrc = new BufferedReader(new FileReader(pathSource+"/query-base.sql"));
					}
					else if ((type.compareTo(Robot.SEARCH_TYPE_REPGEN) == 0) || 
							(type.compareTo(Robot.SEARCH_TYPE_XML_REPGEN) == 0) ||
							(type.compareTo(Robot.SEARCH_TYPE_DETAIL_REPGEN) == 0))
					{
						if ((adaptFiles[i].indexOf("clause-") != -1) && (adaptFiles[i].indexOf(util.getPrefixColumnTable()) != -1))
						{
							continue;
						}
						//System.out.println("Process REPGEN XML TXT PATH --> "+pathSource+"/" + adaptFiles[i]);
						inSrc = new BufferedReader(new FileReader(pathSource+"/" + adaptFiles[i]));
					}
					else if (type.compareTo(Robot.FORM_TYPE) == 0)
					{
						if (hasDetail)
						{
							String filemd = adaptFiles[i].substring(0, adaptFiles[i].indexOf(".")) + "md.htm"; 
							//System.out.println("File HTM Form MD --> "+filemd);
							inSrc = new BufferedReader(new FileReader(pathSource+"/"+filemd));
						}
						else
						{
							inSrc = new BufferedReader(new FileReader(pathSource+"/"+adaptFiles[i]));
						}						
					}
					//else if ((type.compareTo(Robot.JS_TYPE) == 0) && (readJSMD))
					else if (type.compareTo(Robot.JS_TYPE) == 0)
					{
						if (readJSMD)
						{
							String filemd = adaptFiles[i].substring(0, adaptFiles[i].indexOf(".")) + "md.js";
							//System.out.println("File JS Form MD --> "+filemd);
							inSrc = new BufferedReader(new FileReader(pathSource+"/"+filemd));
						}
						else
						{
							inSrc = new BufferedReader(new FileReader(pathSource+"/"+adaptFiles[i]));
						}
					}
					else
					{
						inSrc = new BufferedReader(new FileReader(pathSource+"/"+adaptFiles[i]));
					}
					//System.out.println("Path Adapting from Standard --> " +pathSource+"/"+adaptFiles[i]);
					while(true)
					{
						String line = inSrc.readLine();
						if (line == null)
						{
							break;
						}
						if (type.compareTo(Robot.EXCEL_DETAIL_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								System.out.println("--> DatabaseSchema: " + util.getDatabaseSchema() + " | getCurrentTable: " + util.getCurrentTable());
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								System.out.println("--> Package: " + pkg);
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".ExcelReportDetail";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);

								this.pkgResult = "";
							}
							xmlNew += line + "\n";
						}
						if (type.compareTo(Robot.EXCEL_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));

								customJavaPath = pkg;
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".ExcelReport";								

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);

								this.pkgResult = "";
							}
							xmlNew += line + "\n";
						} 
						if (type.compareTo(Robot.EXCEL_TYPE) == 0)
						{
							if (line.indexOf("${smn:calculate_cellposx}") != -1)
							{							
								String cellPosX = Integer.toString(calculateCellPosx);							
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceFirst("calculate_cellposx\\}", cellPosX);	
							}
						}
						if ((line.indexOf("${smn:title_label}") != -1) && (!label.isEmpty()))
						{
							line = line.replaceAll("smn", "lbl");
							line = line.replaceAll("title_label", Matcher.quoteReplacement(label));	
						}
						if ((line.indexOf("${smn:title_label_detail}") != -1) && (!labelDetail.isEmpty()))
						{
							labelDetail =  labelDetail.substring(labelDetail.indexOf(":")+1, labelDetail.indexOf("}"));
							line = line.replaceAll("smn", "lbl");
							line = line.replaceAll("title_label_detail", Matcher.quoteReplacement(labelDetail));	
						}
						if (type.compareTo(Robot.FORM_TYPE) == 0)
						{
							//Patch - repgen detail (template.htm)
							if (line.indexOf("title_label") != -1)
							{
								line = line.replaceFirst("title_label", Matcher.quoteReplacement(actionLabel));
							}
							if (hasDetail) 
							{
								if (line.indexOf("${smn:numericfields}") != -1)
								{
									line = line.replaceFirst("\\$\\{smn\\:numericfields\\}", numericfields);
								}
								if (line.indexOf("${smn:keypressdate}") != -1)
								{
									line = line.replaceFirst("\\$\\{smn\\:keypressdate\\}", keypressDateDetail);
								}
								if (line.indexOf("${smn:title_label_detail}") != -1)
								{
									line = line.replaceFirst("\\$\\{smn\\:title_label_detail\\}", labelDetail);
								}
								if (htmlTableDetail.indexOf("Añadir") != -1)
								{
									htmlTableDetail = htmlTableDetail.replaceFirst("Añadir", "\\$\\{lbl\\:b_add_button\\}");
								}
								if (htmlTableDetail.indexOf("Desplegar calendario") != -1)
								{
									htmlTableDetail = htmlTableDetail.replaceFirst("Desplegar calendario", "\\$\\{lbl\\:b_deploy_calendar\\}");
								}
								if (htmlTableDetail.indexOf("Seleccione") != -1)
								{
									htmlTableDetail = htmlTableDetail.replaceFirst("Seleccione", "\\$\\{lbl\\:b_choose\\}");
								}
								if (line.indexOf("${smn:html_table_detail}") != -1)
								{
									line = line.replaceFirst("\\$\\{smn\\:html_table_detail\\}", Matcher.quoteReplacement(htmlTableDetail));
								}								
							}
							if ((line.indexOf("alert") != -1) && (line.indexOf("alertBox") == -1))
							{
								line = "//" + line;
							}
							if (line.indexOf("/*") != -1)
							{
								line = line.replaceFirst("/*", "");
							}
							if (line.indexOf("*/") != -1)
							{
								line = line.replaceFirst("*/", "");
							}
							if (line.indexOf("${smn:html_table}") != -1)
							{
								htmlNew += htmlTable + "\n";
								continue;
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceAll("\\$\\{smn\\:table\\}", util.getCurrentTable());
							}
							if (line.indexOf("${smn:table_detail}") != -1)
							{
								line = line.replaceAll("\\$\\{smn\\:table_detail\\}", util.getCurrentTableDetail().elementAt(0));
							}
							if (line.indexOf("${smn:add_record}") != -1)
							{
								line = line.replaceAll("smn", "lbl");
								line = line.replaceAll("add_record", "b_add_record");
							}
							if (line.indexOf("${smn:filter_search}") != -1)
							{
								line = line.replaceAll("smn", "lbl");
								line = line.replaceAll("filter_search", "b_filter_find");
							}
							if (line.indexOf("${smn:html_filter_search}") != -1)
							{
								if (filterSearch.indexOf("Archivo Excel") != -1)
								{
									filterSearch = filterSearch.replaceFirst("Archivo Excel", "\\$\\{lbl\\:b_excel_file\\}");
								}
								htmlNew += filterSearch + "\n";
								continue;
							}
							if (line.indexOf("${smn:picklists}") != -1)
							{
								//SIMONE - PENDIENTE
								line = line.replaceFirst("\\$\\{smn\\:picklists\\}", "");
							}
							if (line.indexOf("${smn:keypress_date}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:keypress_date\\}", keypressDate);
							}
							if (line.indexOf("${smn:path_back}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:path_back\\}", parameters.get(DIRECTIVE_ROBOT_BACK_BUTTON));
							}
							if (line.indexOf("${smn:path_back_label}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:path_back_label\\}", parameters.get(DIRECTIVE_ROBOT_BACK_BUTTON_LABEL));
							}
							if (line.indexOf("${smn:path_next}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:path_next\\}", parameters.get(DIRECTIVE_ROBOT_NEXT_BUTTON));
							}
							if (line.indexOf("${smn:path_next_label}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:path_next_label\\}", parameters.get(DIRECTIVE_ROBOT_NEXT_BUTTON_LABEL));
							}
							int heigth = 60;
							if (line.indexOf("${smn:form_width}") != -1)
							{
								if (validatorRecordsetInMemory.isEmpty())
								{
									line = line.replaceFirst("\\$\\{smn\\:form_width\\}", "650");
								}
								else
								{
									line = line.replaceFirst("\\$\\{smn\\:form_width\\}", "950");
								}
							}
							heigth += (((RobotUtil.amountFields + RobotUtil.amountFieldsFK) * 60) / 2) + 180;
							if (heigth > 600)
							{
								heigth = Math.min(heigth, 600);
							}
							else if (heigth < 180)
							{
								heigth = 180;
							}
							if (line.indexOf("${smn:form_heigth}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn\\:form_heigth\\}", Integer.toString(heigth));
							}
							if (line.indexOf("${smn:disabled_false_fields_by_id}") != -1)
							{
								String conditions = "";
								Hashtable<String,Vector<RobotRepGenFields>> repGenTable = util.getRepGenList();
								Vector<RobotRepGenFields> repGenList = repGenTable.get("fields");
								for(int j = 0; j < repGenList.size(); j++)
								{
									RobotRepGenFields repGenField = repGenList.elementAt(j);
									if (repGenField.getColname().indexOf("fecha_registro") == -1)
									{
										String colnameDB = repGenField.getColname();
										String colname = colnameDB.substring(colnameDB.indexOf(".")+1);
										colname = colname.substring(colname.indexOf(".")+1);
										conditions += "\t\t\t\tif (field_id == " + repGenField.getFieldID() + ")\n";
										conditions += "\t\t\t\t{\n";
										conditions += "\t\t\t\t\tdocument.getElementById('" + colname + "').disabled=false;\n";
										conditions += "\t\t\t\t}\n";
									}
								}
								line = line.replaceFirst("\\$\\{smn\\:disabled_false_fields_by_id\\}", Matcher.quoteReplacement(conditions));
							}
							if (line.indexOf("${smn:disabled_true_fields_by_id}") != -1)
							{
								String conditions = "";
								Hashtable<String,Vector<RobotRepGenFields>> repGenTable = util.getRepGenList();
								Vector<RobotRepGenFields> repGenList = repGenTable.get("fields");
								for(int j = 0; j < repGenList.size(); j++)
								{
									RobotRepGenFields repGenField = repGenList.elementAt(j);
									if (repGenField.getColname().indexOf("fecha_registro") == -1)
									{
										String colnameDB = repGenField.getColname();
										String colname = colnameDB.substring(colnameDB.indexOf(".")+1);
										colname = colname.substring(colname.indexOf(".")+1);
										conditions += "\t\t\t\tif (field_id == " + repGenField.getFieldID() + ")\n";
										conditions += "\t\t\t\t{\n";
										conditions += "\t\t\t\t\tdocument.getElementById('" + colname + "').disabled=true;\n";
										conditions += "\t\t\t\t}\n";
									}
								}
								line = line.replaceFirst("\\$\\{smn\\:disabled_true_fields_by_id\\}", Matcher.quoteReplacement(conditions));
							}
							if (line.indexOf("${smn:disabled_false_fields}") != -1)
							{
								String conditions = "";
								Hashtable<String,Vector<RobotRepGenFields>> repGenTable = util.getRepGenList();
								Vector<RobotRepGenFields> repGenList = repGenTable.get("fields");
								for(int j = 0; j < repGenList.size(); j++)
								{
									RobotRepGenFields repGenField = repGenList.elementAt(j);
									if (repGenField.getColname().indexOf("fecha_registro") == -1)
									{
										String colnameDB = repGenField.getColname();
										String colname = colnameDB.substring(colnameDB.indexOf(".")+1);
										colname = colname.substring(colname.indexOf(".")+1);
										conditions += "\t\t\t\tif (field[i].value == " + repGenField.getFieldID() + ")\n";
										conditions += "\t\t\t\t{\n";
										conditions += "\t\t\t\t\tdocument.getElementById('" + colname + "').disabled=false;\n";
										conditions += "\t\t\t\t}\n";
									}
								}
								line = line.replaceFirst("\\$\\{smn\\:disabled_false_fields\\}", Matcher.quoteReplacement(conditions));
							}
							if (line.indexOf("${smn:disabled_true_fields}") != -1)
							{
								String conditions = "";
								Hashtable<String,Vector<RobotRepGenFields>> repGenTable = util.getRepGenList();
								Vector<RobotRepGenFields> repGenList = repGenTable.get("fields");
								for(int j = 0; j < repGenList.size(); j++)
								{
									RobotRepGenFields repGenField = repGenList.elementAt(j);
									if (repGenField.getColname().indexOf("fecha_registro") == -1)
									{
										String colnameDB = repGenField.getColname();
										String colname = colnameDB.substring(colnameDB.indexOf(".")+1);
										colname = colname.substring(colname.indexOf(".")+1);
										conditions += "\t\t\t\tif (field[i].value == " + repGenField.getFieldID() + ")\n";
										conditions += "\t\t\t\t{\n";
										conditions += "\t\t\t\t\tdocument.getElementById('" + colname + "').disabled=true;\n";
										conditions += "\t\t\t\t}\n";
									}
								}
								line = line.replaceFirst("\\$\\{smn\\:disabled_true_fields\\}", Matcher.quoteReplacement(conditions));
							}
							if (line.indexOf("${smn:store_date_position}") != -1)
							{
								int datePos = 0;
								Hashtable<String,Vector<RobotRepGenFields>> repGenTable = util.getRepGenList();
								Vector<RobotRepGenFields> repGenList = repGenTable.get("fields");
								for(int j = 0; j < repGenList.size(); j++)
								{
									RobotRepGenFields repGenField = repGenList.elementAt(j);
									String colname = repGenField.getColname();
									if (colname.indexOf("fecha_registro") != -1)
									{
										datePos = repGenField.getFieldID();
										break;
									}
								}
								line = line.replaceFirst("\\$\\{smn\\:store_date_position\\}", Integer.toString(datePos));
							}
							if (line.indexOf("${smn:html_filter_search_repgen}") != -1)
							{
								String labFilter = "";
								String filtersStr = "";
								boolean filtersFound = false;
								BufferedReader inHtm = new BufferedReader(new FileReader( parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/filter/form/template.htm" ));
								while(true)
								{
									String lineStr = inHtm.readLine();
									if (lineStr == null)
									{
										break;
									}
									if (lineStr.indexOf("<!--campos-->") != -1)
									{
										filtersFound = true;
										continue;
									}
									if (lineStr.indexOf("<!--botones-->") != -1)
									{
										filtersStr = filtersStr.substring(0, filtersStr.lastIndexOf("</table>")-1);
										break;
									} 
									if (filtersFound)
									{

										if (lineStr.indexOf("${lbl:") != -1)
										{
											labFilter = lineStr.substring(lineStr.indexOf(":")+1, lineStr.lastIndexOf("}"));
										}
										if (((lineStr.indexOf("<input") != -1) && (lineStr.indexOf("button") == -1)) ||
											(lineStr.indexOf("<select") != -1))
										{
											lineStr = lineStr.substring(0, lineStr.indexOf(">")) + " disabled >";
											String labelStr = "";
											if (lineStr.indexOf("\"") != -1)
											{
												String lnTmp = lineStr.substring(lineStr.indexOf("\"")+1);
												labelStr = lnTmp.substring(0, lnTmp.indexOf("\""));
											}
											else if (lineStr.indexOf("'") != -1)
											{
												String lnTmp = lineStr.substring(lineStr.indexOf("'")+1);
												labelStr = lnTmp.substring(0, lnTmp.indexOf("'"));
											}
											filterAndLabels.put(labelStr, Matcher.quoteReplacement(labFilter));
										}
										filtersStr += lineStr + "\n";
									}
									if (lineStr.indexOf("b_store_date_grater_than") != -1)
									{										
										filtersStr = filtersStr.substring(0, filtersStr.lastIndexOf("<tr>"));
										break;
									}
								}
								if (inHtm != null)
								{
									inHtm.close();
								}
								//System.out.println("Filter REPGEN --> " + filtersStr);
								line = line.replaceFirst("\\$\\{smn\\:html_filter_search_repgen\\}", Matcher.quoteReplacement(filtersStr));
							}
							htmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.VIEW_TYPE) == 0)
						{
							if (line.indexOf("${smn:width_columns}") != -1)
							{
								htmlNew += widthColumns + "\n";
								continue;
							}
							if (line.indexOf("${smn:calculate_colspan}") != -1)
							{
								if (toolbarDetail)
								{
									calculateColspan--;
								}
								String colspan = Integer.toString(calculateColspan);
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("calculate_colspan\\}", colspan);
							}
							if (line.indexOf("${smn:calculate_colspan_main}") != -1)
							{
								String colspan = Integer.toString(calculateColspan+3);
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("calculate_colspan_main\\}", colspan);
							}
							if (line.indexOf("${smn:html_columns_header}") != -1)
							{
								if (headerColumns.indexOf("Fila") != -1)
								{
									headerColumns = headerColumns.replaceFirst("Fila", "\\$\\{lbl\\:b_row\\}");
								}
								if (headerColumns.indexOf("Columna") != -1)
								{
									headerColumns = headerColumns.replaceFirst("Columna", "\\$\\{lbl\\:b_column\\}");
								}
								if (headerColumns.indexOf("Descripción del error") != -1)
								{
									headerColumns = headerColumns.replaceFirst("Descripción del error", "\\$\\{lbl\\:b_error_description\\}");
								}
								htmlNew += headerColumns + "\n";
								continue;
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceAll("\\$\\{smn\\:table\\}", util.getCurrentTable());
							}

							if (line.indexOf("${smn:table_detail}") != -1)
							{
								line = line.replaceFirst("detail\\$\\{smn:", "");
								line = line.replaceAll("table_detail\\}", actionDirectoryDetail);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:data_columns}") != -1)
							{
								htmlNew += dataColumns + "\n";
								continue;
							}
							if (line.indexOf("${smn:register_loaded}") != -1)
							{
								htmlNew += registerLoaded + "\n";
								continue;
							}
							htmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.PDF_DETAIL_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".PDFlReportDetail";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);

								this.pkgResult = "";
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.PDF_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".PDFReport";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);
								//System.out.println("Line modified REPGEN: "+line);

								this.pkgResult = "";
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.PDF_TYPE) == 0)
						{
							if (line.indexOf("align='right'") != -1)
							{
								line = line.replaceFirst("align='right'", "align='left'");
							}
							if (line.indexOf("align='center'") != -1)
							{
								line = line.replaceFirst("align='center'", "align='left'");
							}
							if ((line.indexOf("<table") != -1) || (line.indexOf("</table") != -1))
							{
								line = line.replaceFirst("table", "record"); 
							}
							if (line.indexOf("${smn:pdf_recordsets}") != -1)
							{
								xmlNew += pdfRecordsets + "\n";
								continue;
							}
							if (line.indexOf("${smn:pdf_headers}") != -1)
							{
								xmlNew += pdfHeaders + "\n";
								System.out.println("pdfHeaders --> "+pdfHeaders);
								continue;
							}
							if (line.indexOf("${smn:pdf_columns}") != -1)
							{
								xmlNew += pdfColumn + "\n";
								System.out.println("pdfColumn --> "+pdfColumn);
								continue;
							}
							if (line.indexOf("${smn:pdf_filter_columns}") != -1)
							{
								xmlNew += pdfFilterColumn + "\n";
								System.out.println("pdfFilterColumn --> "+pdfFilterColumn);
								continue;
							}
							if (line.indexOf("${smn:table_stuck}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("table_stuck\\}", recordsetName);
							}
							xmlNew += line + "\n";
						}	
						else if (type.compareTo(Robot.TXT_DETAIL_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".TextReport";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);
							}
							txtNew += line + "\n";
						}
						else if (type.compareTo(Robot.TXT_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".TextReport";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);
							}
							txtNew += line + "\n";
						}
						else if (type.compareTo(Robot.TXT_TYPE) == 0)
						{
							//System.out.println("Adapting SEARCH from Standard SQL --> "+line);
							if (line.indexOf("${smn:schema}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("schema\\}", util.getDatabaseSchema());							
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:sql_columns}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								//System.out.println("n = "+metadataList.size());
								for(int k = 0; k < metadataList.size(); k++)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = util.getDatabaseSchema() + "." + actionDirectory + "." + metadata.getColumn();
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1))
									{
										column = "\t" + column + ",";
										queryColumns += column + "\n";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								if (queryColumns.indexOf(",") != -1)
								{
									line = line.replaceAll("sql_columns\\}", queryColumns.substring(0, queryColumns.lastIndexOf(",")));
								}
								else
								{
									line = line.replaceAll("sql_columns\\}", queryColumns);
								}
							}
							if (line.indexOf("${smn:pairs_parameter_value}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								for(int k = metadataList.size()-1; k >= 0; k--)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = metadata.getColumn() + "=\"\\$\\{fld:" + metadata.getColumn() + "\\}\"";
									column += "  ";
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1))
									{
										queryColumns += column + "  ";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("pairs_parameter_value\\}", queryColumns);
							}
							txtNew += line + "\n";		
						}
						else if (type.compareTo(Robot.SEARCH_TYPE) == 0)
						{
							System.out.println("Adapting SEARCH from Standard SQL --> "+line);				
							if (line.indexOf("${smn:schema}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("schema\\}", util.getDatabaseSchema());							
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:sql_columns}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								for(int k = 0; k < metadataList.size(); k++)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = util.getDatabaseSchema() + "." + actionDirectory + "." + metadata.getColumn();
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1))
									{
										column = "\t" + column + ",";
										queryColumns += column + "\n";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								if (queryColumns.lastIndexOf(",") != -1)
								{
									line = line.replaceAll("sql_columns\\}", queryColumns.substring(0, queryColumns.lastIndexOf(",")));
								}
								else
								{
									line = line.replaceAll("sql_columns\\}", queryColumns);
								}
							}
							sqlSearch += line + "\n";
						}
						else if ((type.compareTo(Robot.SEARCH_TYPE_REPGEN) == 0) ||
								(type.compareTo(Robot.SEARCH_TYPE_DETAIL_REPGEN) == 0))
						{
							if (line.indexOf("${smn:schema}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("schema\\}", util.getDatabaseSchema());							
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:sql_columns}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								for(int k = 0; k < metadataList.size(); k++)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = util.getDatabaseSchema() + "." + actionDirectory + "." + metadata.getColumn();
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1))
									{
										column = "\t" + column + ",";
										queryColumns += column + "\n";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								if (queryColumns.lastIndexOf(",") != -1)
								{
									line = line.replaceAll("sql_columns\\}", queryColumns.substring(0, queryColumns.lastIndexOf(",")));
								}
								else
								{
									line = line.replaceAll("sql_columns\\}", queryColumns);
								}
							}
							if (line.indexOf("${smn:repgen_path}") != -1)
							{
								String repgenPath = util.getRepGenActionRoot();

								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("repgen_path\\}", repgenPath);					
							}
							if (line.indexOf("acronym}") != -1)
							{
								line = line.replaceAll("acronym\\}", util.getPrefixColumnTable());							
							}
							sqlSearch += line + "\n";
						}
						else if (type.compareTo(Robot.SEARCH_TYPE_XML_REPGEN) == 0)
						{
							if (line.indexOf("${smn:filter_fields}") != -1)
							{
								String searchPath = parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/search";
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("filter_fields\\}", getFilterColumns(searchPath));					
							}
							if (line.indexOf("${smn:transformers}") != -1)
							{
								xmlNew += xmlTransformers + "\n";
								continue;
							}
							if (line.indexOf("${smn:parameters}") != -1)
							{
								xmlNew += parametersColumn + "\n";
								continue;
							}
							if (line.indexOf("${smn:validators}") != -1)
							{	
								xmlNew += xmlValidators + "\n";		
								if (util.isTableHasForeignKey())
								{
									xmlNew += validatorRecordsetInMemory + "\n";
								}
								continue;				
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.XML_DETAIL_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".XmlReport";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.XML_TYPE_REPGEN) == 0)
						{
							if (line.indexOf("${smn:package_path}") != -1)
							{
								String pkg = getPackage("C:/tomcat7/webapps/" + util.getDatabaseSchema() + "/WEB-INF/source/domain/repgen", 
										util.getCurrentTable());
								pkg = pkg.substring(pkg.indexOf("domain"));
								pkg = pkg.replaceAll("\\\\", ".");

								pkg += ".XmlReport";

								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("package_path\\}", pkg);
							}
							if (line.indexOf("${smn:schema}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("schema\\}", util.getDatabaseSchema());							
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.XML_TYPE) == 0)
						{
							if (line.indexOf("${smn:schema}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("schema\\}", util.getDatabaseSchema());							
							}
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:sql_columns}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								for(int k = 0; k < metadataList.size(); k++)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = util.getDatabaseSchema() + "." + actionDirectory + "." + metadata.getColumn();
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1))
									{
										column = "\t" + column + ",";
										queryColumns += column + "\n";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								if (queryColumns.indexOf(",") != -1)
								{
									line = line.replaceAll("sql_columns\\}", queryColumns.substring(0, queryColumns.lastIndexOf(",")));
								}
								else
								{
									line = line.replaceAll("sql_columns\\}", queryColumns);
								}
							}
							if (line.indexOf("${smn:pairs_parameter_value}") != -1)
							{
								String queryColumns = "";
								Vector<RobotMetadata> metadataList = util.getMetadataList().get(actionDirectory);
								for(int k = metadataList.size()-1; k >= 0; k--)
								{
									RobotMetadata metadata = metadataList.elementAt(k);
									String column = metadata.getColumn() + "=\"\\$\\{fld:" + metadata.getColumn() + "\\}\"";
									column += "  ";
									if ((column.indexOf("usuario") == -1) && 
											(column.indexOf("idioma") == -1) && 
											(column.indexOf("hora") == -1) &&
											(column.indexOf(actionDirectory+"_id") == -1)) 
									{
										queryColumns += column + "  ";
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("pairs_parameter_value\\}", queryColumns);
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.JS_TYPE) == 0)
						{
							if (line.indexOf("${smn:table}") != -1)
							{
								line = line.replaceFirst("\\$\\{smn:", "");
								line = line.replaceAll("table\\}", actionDirectory);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:table_detail}") != -1)
							{
								line = line.replaceFirst("detail\\$\\{smn:", "");
								line = line.replaceAll("table_detail\\}", actionDirectoryDetail);
								line = line.replaceFirst("\\$\\{smn:", "");
							}
							if (line.indexOf("${smn:fields_form}") != -1)
							{
								jsNew += jsColumn;
								continue;
							} 
							if (line.indexOf("${smn:fields_form_disabled}") != -1)
							{
								jsNew += disabledColumn;
								continue;
							}
							jsNew += line + "\n";
						}
						else if (type.compareTo(Robot.EXCEL_TYPE) == 0)
						{
							if (line.indexOf("${smn:title_label}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("title_label\\}", Matcher.quoteReplacement(titleLabelTemplate));
							}
							if (line.indexOf("${smn:table_stuck}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("table_stuck\\}", recordsetName);
							}
							if (line.indexOf("${smn:excel_columns}") != -1)
							{
								xmlNew += excelColumn + "\n";
								continue;
							}
							xmlNew += line + "\n";
						}
						else if (type.compareTo(Robot.EXCEL_TEMPLATE_TYPE) == 0)
						{
							if (line.indexOf("${smn:title_label}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("title_label\\}", Matcher.quoteReplacement(titleLabelTemplate));
							}
							if (line.indexOf("${smn:table_stuck}") != -1)
							{
								String stuck = util.getCurrentTable().substring(util.getCurrentTable().indexOf(".")+1);
								stuck = stuck.replaceAll("_", "");
								//System.out.println("STUCK EXCEL_TEMPLATE_TYPE --> " + xmlNew);
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("table_stuck\\}", stuck);
							}
							if (line.indexOf("${smn:recordset_ref}") != -1)
							{
								String recordsetRef = "";
								for(int k = 0; k < robotJoinList.size(); k++)
								{
									RobotJoin fieldJoin = robotJoinList.elementAt(k);
									String recordsetStuck = fieldJoin.getTableJoin().substring(fieldJoin.getTableJoin().indexOf(".")+1).replaceAll("_", "");
									recordsetRef += "<recordset id='query-" + recordsetStuck + ".sql' source='sql' scope='transaction' />\n\t";

									String item = fieldJoin.getFieldItem();
									item = item.substring(item.indexOf(".")+1);
									item = item.substring(item.indexOf(".")+1);
									
									String sheetLabel = "";
									if (item.indexOf("nombre") != -1)
									{
										sheetLabel = "b_name";
									}
									else if (item.indexOf("descripcion") != -1)
									{
										sheetLabel = "b_description";
									}
									else if (item.indexOf("codigo") != -1)
									{
										sheetLabel = "b_code";
									}
									
									sheetRef += "\t<sheet recordset='query-" + recordsetStuck + ".sql' sheetname='" + fieldJoin.getLabel() + "'>\n" +
											"\t\t<col id='" + item + "' label='${lbl:" + sheetLabel + "}' />\n" +
											"\t</sheet>\n\n";
									
									String recordsetSQL = "select  \n" +
									                      "      *   " +
									                      "from    \n" +
									                      "      " + fieldJoin.getTableJoin();
									
									FileWriter rsWriter = new FileWriter( pathTarget + "\\query-" + recordsetStuck + ".sql" );
									rsWriter.write(recordsetSQL);
									if (rsWriter != null)
									{
										rsWriter.close();
									}
								}
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("recordset_ref\\}", recordsetRef);								
							}
							if (line.indexOf("${smn:excel_columns}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("excel_columns\\}", Matcher.quoteReplacement(excelColumnTemplate));
							}
							if (line.indexOf("${smn:excel_columns_ref}") != -1)
							{
								line = line.replaceAll("\\$\\{smn:", "");
								line = line.replaceAll("excel_columns_ref\\}", Matcher.quoteReplacement(sheetRef));
							}
							xmlNew += line + "\n";
							//System.out.println("EXCEL_TEMPLATE_TYPE --> " + xmlNew);
						}
						else if (type.compareTo(Robot.NOTFOUND_TYPE) == 0)
						{
							if (line.indexOf("${smn:notfound}") != -1)
							{
								htmlNew += "${lbl:b_not_found}.\n";
								continue;
							}
							htmlNew += line + "\n";
						}
						else if ((type.compareTo(Robot.INSERT_TYPE) == 0) || (type.compareTo(Robot.UPDATE_TYPE) == 0))
						{
							if (line.indexOf("${smn:parameters}") != -1)
							{
								xmlNew += parametersColumn + "\n";
								continue;
							}
							if (line.indexOf("${smn:parameters_ref}") != -1)
							{
								System.out.println("INSERT - Import Excel Original References --> " + refFields);
								xmlNew += refFields + "\n";
								continue;
							}
							if (line.indexOf("${smn:validators}") != -1)
							{	
								xmlNew += xmlValidators + "\n";		
								if (util.isTableHasForeignKey())
								{
									xmlNew += validatorRecordsetInMemory + "\n";
								}
								continue;				
							}
							if (line.indexOf("${smn:validators_ref}") != -1)
							{	
								xmlNew += refValidators;	
								continue;				
							}
							xmlNew += line + "\n";
						}
					}
				}

				if (hasDetail)
				{
					File adaptedFile = new File(parameters.get(DIRECTORY_ACTION) + "/" + actionDirectory + "/filter/editor/addnew/smn_adapted_copied.txt");
					adaptedFile.createNewFile();
				}
				if (type.compareTo(Robot.SEARCH_TYPE) == 0)
				{
					out = new FileWriter( parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/search/"+adaptFiles[i]);
					//verifico si ya el query fue adaptado antes
					int lastIndexSelect = sqlSearch.lastIndexOf("select");
					if (lastIndexSelect > 0)
					{
						sqlSearch = sqlSearch.substring(0, lastIndexSelect - 1);
					}
					System.out.println("Storing SEARCH from Standard --> " +sqlSearch);
					out.write(sqlSearch);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_sql.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.SEARCH_TYPE_REPGEN) == 0)
				{
					//System.out.println("Storing SEARCH REPGEN REPGEN XML TXT PATH TARGET--> " +pathTarget);
					out = new FileWriter( pathTarget+"/"+adaptFiles[i]);
					//verifico si ya el query fue adaptado antes
					int lastIndexSelect = sqlSearch.lastIndexOf("select");
					if (lastIndexSelect > 0)
					{
						sqlSearch = sqlSearch.substring(0, lastIndexSelect - 1);
					}
					//System.out.println("Storing SEARCH REPGEN from Standard --> " +sqlSearch);
					out.write(sqlSearch);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_xml_txt_repgen.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.SEARCH_TYPE_DETAIL_REPGEN) == 0)
				{
					//System.out.println("Storing SEARCH REPGEN FORM PATH TARGET--> " +pathTarget);
					out = new FileWriter( pathTarget+"/"+adaptFiles[i]);
					//verifico si ya el query fue adaptado antes
					int lastIndexSelect = sqlSearch.lastIndexOf("select");
					if (lastIndexSelect > 0)
					{
						sqlSearch = sqlSearch.substring(0, lastIndexSelect - 1);
					}
					//System.out.println("Storing SEARCH REPGEN from Standard --> " +sqlSearch);
					out.write(sqlSearch);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_sql_detail_repgen.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.SEARCH_TYPE_XML_REPGEN) == 0)
				{
					out = new FileWriter( parameters.get(DIRECTORY_ROBOT) + "/smn_actions/" + actionDirectory + "/repgen/search/"+adaptFiles[i]);
					//verifico si ya el query fue adaptado antes
					//System.out.println("Storing SEARCH CONFIG REPGEN from Standard --> " +xmlNew);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_sql_repgen.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.FORM_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(htmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_form.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.VIEW_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(htmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_view.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.NOTFOUND_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(htmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_notfound.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.PDF_TYPE_REPGEN) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					//System.out.println("PDF REPGEN Adapted --> "+xmlNew+" | Path: "+pathTarget+"/"+adaptFiles[i]);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_pdf_repgen.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.PDF_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					//System.out.println("PDF Adapted --> "+xmlNew);
					xmlNew = xmlNew.replaceAll("center", "left");
					xmlNew = xmlNew.replaceAll("right", "left");
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_pdf.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.PDF_DETAIL_TYPE_REPGEN) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					//System.out.println("PDF REPGEN Detail Adapted --> "+xmlNew+" | Path: "+pathTarget+"/"+adaptFiles[i]);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_pdf_detail_repgen.txt");
					adaptedFile.createNewFile();
				}
				else if ((type.compareTo(Robot.XML_TYPE) == 0) || 
						 (type.compareTo(Robot.XML_TYPE_REPGEN) == 0) ||
						 (type.compareTo(Robot.XML_DETAIL_TYPE_REPGEN) == 0))
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					//System.out.println("Storing TEXT from Standard --> " +xmlNew);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_xml.txt");
					adaptedFile.createNewFile();
				}

				else if ((type.compareTo(Robot.TXT_TYPE) == 0) || 
						 (type.compareTo(Robot.TXT_TYPE_REPGEN) == 0) ||
						 (type.compareTo(Robot.TXT_DETAIL_TYPE_REPGEN) == 0))
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);					
					//System.out.println("Storing TEXT from Standard --> " +txtNew);
					out.write(txtNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_text.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.JS_TYPE) == 0)
				{					
					if (hasDetail)//(readJSMD)
					{
						out = new FileWriter(pathTarget+"/script.js");
					}
					else
					{
						out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					}
					//System.out.println("Storing JS from Standard --> " +jsNew);
					out.write(jsNew);
					if (out  != null)
					{
						out.close();
					}
					if (hasDetail)//(readJSMD)
					{
						File adaptedFile = new File(pathTarget+"/smn_adapted_jsmd.txt");
						adaptedFile.createNewFile();
					}
					else
					{
						File adaptedFile = new File(pathTarget+"/smn_adapted_js.txt");
						adaptedFile.createNewFile();
					}
				}
				else if ((type.compareTo(Robot.EXCEL_TYPE) == 0) || 
						 (type.compareTo(Robot.EXCEL_TYPE_REPGEN) == 0) ||
						 (type.compareTo(Robot.EXCEL_TEMPLATE_TYPE) == 0))
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_excel.txt");
					adaptedFile.createNewFile();
				}
				else if ((type.compareTo(Robot.EXCEL_DETAIL_TYPE) == 0) || 
						 (type.compareTo(Robot.EXCEL_DETAIL_TYPE_REPGEN) == 0))
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(xmlExcelDetail);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_excel_detail.txt");
					adaptedFile.createNewFile();
				}
				else if (type.compareTo(Robot.INSERT_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_insert.txt");
					adaptedFile.createNewFile();
					if (util.isTableHasForeignKey())
					{
						out = new FileWriter(pathTarget+"/recordset.xml");
						out.write(recordsetInMemory);
						if (out  != null)
						{
							out.close();
						}
					}
				}
				else if (type.compareTo(Robot.UPDATE_TYPE) == 0)
				{
					out = new FileWriter(pathTarget+"/"+adaptFiles[i]);
					out.write(xmlNew);
					if (out  != null)
					{
						out.close();
					}
					File adaptedFile = new File(pathTarget+"/smn_adapted_update.txt");
					adaptedFile.createNewFile();
					if (util.isTableHasForeignKey())
					{
						out = new FileWriter(pathTarget+"/recordset.xml");
						out.write(recordsetInMemory);
						if (out  != null)
						{
							out.close();
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
					if (inSrc  != null)
					{
						inSrc.close();
					}
					if (inTrg  != null)
					{
						inTrg.close();
					}
					if (out  != null)
					{
						out.close();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

			RobotUtil.logger.info("Directory " + pathTarget + " adapted to standard SIMONE");
		}
	}

	//recursive function that make the adapting process
	public void actionAdapterProcessRunSecond(String parentDirectory, File[] files) throws Throwable
	{
		//walk per each file
		for (File file : files)
		{
			//walk per each directory
			if (file.isDirectory()) 
			{
				adaptFileToStandardRunSecond(file);

				actionAdapterProcessRunSecond(file.getName(), file.listFiles()); // Calls same method again.
			} 
		}
	}

	public void adaptFileToStandardRunSecond(File file) throws Throwable 
	{
		//String robotDirectory = parameters.get(DIRECTORY_ROBOT);
		//String pathSource = robotDirectory + "/smn_standard" + source;

		String pathTarget = file.getPath();

		//System.out.println("Path Source: "+pathSource);
		System.out.println("Path Target Second --> "+pathTarget);

		if (pathTarget.indexOf("email") != -1)
		{
			return;
		}

		if (pathTarget.indexOf("importexcel") != -1)
		{
			return;
		}

		if (pathTarget.indexOf("edit") != -1)
		{
			return;
		}

		//System.out.println("Type: " + type);

		String[] fileList = file.list();

		int n = fileList.length;
		for(int i = 0; i < n; i++)
		{
			if (fileList[i].indexOf(".sql") == -1)
			{
				continue;
			}
			BufferedReader inTrg = null;
			FileWriter out = null;
			try 
			{		
				String select = "";
				String from = "";
				String join = "";
				String orderby = "";

				boolean joinHasNullable = false;
				if ((fileList[i].startsWith("clause-")) ||
					(fileList[i].startsWith("query-smnconfiguracion")) ||
					((pathTarget.indexOf("fields") != -1) && (fileList[i].compareTo("query.sql") != -1)))
				{
					continue;
				}
				if ((fileList[i].compareTo("query.sql") == 0) ||
					(fileList[i].compareTo("query-base.sql") == 0) ||
					(fileList[i].startsWith("query-smn")))
				{	
					System.out.println("<-- QUERY -->");	
					System.out.println("PATH FORM --> "+pathTarget+"\\"+fileList[i]);
					//System.out.println("robotJoinList.size() --> "+robotJoinList.size());					
					for(int k = 0;  k < robotJoinList.size(); k++)
					{
						RobotJoin rJoin = robotJoinList.elementAt(k);	
						if (rJoin.isNullable())
						{
							joinHasNullable = true;
							break;
						}
					}
					for(int k = 0;  k < robotJoinList.size(); k++)
					{
						RobotJoin rJoin = robotJoinList.elementAt(k);			
						System.out.println("FIELD JOIN --> "+rJoin.getFieldJoin());																	
						System.out.println("FIELD ID --> "+rJoin.getFieldId());
						System.out.println("FIELD ITEM --> "+rJoin.getFieldItem());
						System.out.println("TABLE JOIN --> "+rJoin.getTableJoin());
						select += "\t" + rJoin.getFieldItem() + ",\n";

						//manage auto-table itself
						if (rJoin.getTableJoin().compareTo(util.getDatabaseSchema() + "." + actionDirectory) == 0)
						{
							if (k == 0)
							{
								from += util.getDatabaseSchema() + "." + actionDirectory + "\n";
							}
							continue;
						}

						String tJoin = "";
						if (!joinHasNullable)
						{
							if (from.indexOf(rJoin.getTableJoin()+",") == -1)
							{
								from += "\t" + rJoin.getTableJoin() + ",\n";
							}
							tJoin = rJoin.getTableJoin().substring(rJoin.getTableJoin().indexOf(".")+1);
							join += "\t" + rJoin.getTableJoin() + "." + tJoin + "_id = " + util.getDatabaseSchema() + "." + actionDirectory + "." + rJoin.getFieldJoin() + " and\n";
						}
						else
						{
							//table base that makes join with all
							if (k == 0)
							{
								from += util.getDatabaseSchema() + "." + actionDirectory + "\n";
							}
							if (rJoin.isNullable())
							{
								tJoin = rJoin.getTableJoin().substring(rJoin.getTableJoin().indexOf(".")+1);
								from += "\tleft outer join " + rJoin.getTableJoin() + 
										" on " + 
										rJoin.getTableJoin() + "." + tJoin + "_id = " + util.getDatabaseSchema() + "." + actionDirectory + "." + rJoin.getFieldJoin() + "\n";
							}
							else
							{
								tJoin = rJoin.getTableJoin().substring(rJoin.getTableJoin().indexOf(".")+1);
								from += "\tinner join " + rJoin.getTableJoin() + 
										" on " + 
										rJoin.getTableJoin() + "." + tJoin + "_id = " + util.getDatabaseSchema() + "." + actionDirectory + "." + rJoin.getFieldJoin() + "\n";								
							}
						}
					}
					/*if (!select.endsWith(","))
					{
						select = select.substring(0, select.lastIndexOf(","));
					}
					//from = from.substring(0, from.indexOf(","));
					if (select.indexOf("and") != -1)
					{
						join = join.substring(0, join.lastIndexOf("and"));
					}*/
					select = select.trim();
					from = from.trim();
					join = join.trim();

					System.out.println("SQL SELECT --> "+select);
					System.out.println("SQL FROM --> "+from);
					System.out.println("SQL WHERE --> "+join);
				}
				System.out.println("Path Target Full Second --> "+pathTarget+"\\"+fileList[i]);

				String sqlOriginal = "";
				String sql = "";

				BufferedReader inSrc = new BufferedReader(new FileReader(pathTarget+"\\"+fileList[i]));
				boolean inSelect = false;
				boolean inFrom = false;
				boolean inWhere = false;
				boolean inOrderBy = false;
				boolean joinSelectLaid = false;
				boolean joinFromLaid = false;
				boolean hasWhere = false;
				while(true)
				{
					String srcLine = inSrc.readLine();
					sqlOriginal += srcLine + "\n"; 
					if (srcLine == null)
					{
						break;
					}					
					srcLine = srcLine.trim();
					if (inOrderBy)
					{
						orderby = "\t" + srcLine;
					}
					//System.out.println("Query analysis --> " + srcLine);
					if (srcLine.indexOf(util.getDatabaseSchema() + "." + actionDirectory + "." + actionDirectory + "_id") != -1)
					{
						srcLine = "\t" + srcLine; 
					}
					if (srcLine.indexOf("select") != -1)
					{
						inSelect = true;
						sql += "select\n";
						//System.out.println("SQL --> " + sql);
						continue;
					}
					if (srcLine.indexOf("from") != -1)
					{
						inSelect = false;
						inFrom = true;
						sql += "from\n";
						//System.out.println("SQL --> " + sql);
						continue;
					}
					if (srcLine.indexOf("where") != -1)
					{
						inFrom = false;
						inWhere = true;
						hasWhere = true;
						sql += "where\n";
						continue;
					}
					if (srcLine.indexOf("order by") != -1)
					{
						inFrom = false;
						inWhere = false;
						inOrderBy = true;
						continue;
					}					
					if (inSelect)
					{
						//System.out.println("Query analysis Select --> " + select + " | joinSelectLaid = "+joinSelectLaid);
						if (select.isEmpty())
						{
							sql += "\t" + srcLine + "\n";
						}
						else
						{
							if (!joinSelectLaid)
							{
								sql += "\t" + select + "\n";
								sql += "\t" + srcLine + "\n";
								//System.out.println("Query analysis Select FIELD --> " + sql);
								joinSelectLaid = true;
							}
							else
							{
								sql += "\t" + srcLine + "\n";
							}							
						}
						//System.out.println("SELECT --> " + sql);
					}
					if (inFrom)
					{
						if (!joinHasNullable)
						{
							sql += "\t" + srcLine + "\n";
							//System.out.println("SQL --> " + sql + " | joinHasNullable --> " + joinHasNullable);
						}
						//System.out.println("Query analysis From FIELD --> " + sql);
						//System.out.println("FROM --> " + sql);
					}
					if (inWhere)
					{
						if (from.isEmpty())
						{
							sql += "\t" + srcLine + "\n";
							//System.out.println("SQL --> " + sql + " | From is EMPTY");
						}
						else
						{
							if (!joinFromLaid)
							{
								if (sql.indexOf(from) == -1)
								{
									if (!from.isEmpty())
									{
										sql = sql.replaceFirst("from", "from\n\t"+from);
										//System.out.println("SQL --> " + sql + " | NOT joinFromLaid");
									}
								}
								if (!join.isEmpty())
								{
									sql += "\t" + join + "\n";
									sql += "\t" + srcLine + "\n";
									//System.out.println("SQL --> " + sql + " | NOT join is empty");
								}
								else
								{
									sql += "\t" + srcLine + "\n";
									//System.out.println("SQL --> " + sql + " | NOT join empty");
								}
								joinFromLaid = true;
							}
							else
							{
								sql += "\t" + srcLine + "\n";
								//System.out.println("SQL --> " + sql + " | joinFromLaid");
							}
							//System.out.println("WHERE --> " + sql);
						}
						//System.out.println("Query analysis Where FIELD --> " + sql);
					}					
				}
				if (!hasWhere)
				{
					//System.out.println("SQL --> " + sql + " | NOT hasWhere");
					if (!from.isEmpty())
					{
						if (joinHasNullable)
						{
							sql += "\t" + from.trim() + "\n";
						}
						else
						{
							String sqlFrom = sql.substring(sql.indexOf("from")+5);
							sql = sql.substring(0, sql.indexOf("from")+5);
							sql += "\n\t" + from.trim() + "\n";
							sql += "\t" + sqlFrom.trim() + "\n";
						}
					}
					if (!join.isEmpty())
					{	
						int p = join.lastIndexOf("and");
						if (p != -1)
						{
							join = join.substring(0, p-1);
						}
						sql += "where\n";
						sql += "\t" + join + "\n";
					}
				}
				if (inOrderBy)
				{
					if (!orderby.isEmpty())
					{
						sql += "order by\n\t" + orderby + "\n";
						//System.out.println("SQL --> " + sql + " | ORDER BY");
					}
				}
				System.out.println("Query Original --> " + sqlOriginal);
				System.out.println("Query to replace --> " + sql);
				if (inSrc  != null) inSrc.close();
				out = new FileWriter(pathTarget+"\\"+fileList[i]);
				out.write(sql);
				if (out != null) out.close();
				break;

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
					if (inTrg  != null)
					{
						inTrg.close();
					}
					if (out  != null)
					{
						out.close();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	//recursive function that make the adapting process
	public void actionAdapterProcessRunThird(String parentDirectory, File[] files) throws Throwable
	{
		//walk per each file
		for (File file : files)
		{
			//walk per each directory
			if (file.isDirectory()) 
			{
				//System.out.println("THIRD ROOT----> "+file.getPath());
				adaptFileToStandardRunThird(file);

				actionAdapterProcessRunThird(file.getName(), file.listFiles()); // Calls same method again.
			} 
		}

	}

	public void adaptFileToStandardRunThird(File file) throws Throwable 
	{
		//String robotDirectory = parameters.get(DIRECTORY_ROBOT);
		//String pathSource = robotDirectory + "/smn_standard" + source;

		String pathTarget = file.getPath();

		//System.out.println("THIRD ----> "+pathTarget);

		if (pathTarget.indexOf("email") != -1 ||
				pathTarget.indexOf("importexcel") != -1 ||
				pathTarget.indexOf("edit") != -1 ||
				pathTarget.indexOf("search") != -1 ||
				pathTarget.indexOf("notfound") != -1)
		{
			return;
		}

		/*	if (pathTarget.indexOf("view") != -1)
		{
			System.out.println("VIEW ----> "+pathTarget);
			if (file.getParent().endsWith(actionDirectory))
			{
				return;
			}
			if (!file.getParent().endsWith("xml") && !file.getParent().endsWith("txt"))
			{
				return;
			}
		}*/
		/*
		if (pathTarget.endsWith("pdf") || pathTarget.endsWith("excel"))
		{
			if (file.getParent().endsWith(actionDirectory))
			{
				return;
			}
		}*/

		//System.out.println("Type: " + type);

		String[] fileList = file.list();

		int n = fileList.length;
		//System.out.println("fileList ---> " + n);
		for(int i = 0; i < n; i++)
		{
			String fileToRead = "";
			if (pathTarget.endsWith("pdf") || pathTarget.endsWith("excel"))
			{
				fileToRead = "config.xml";
			}
			else 
			{
				if (pathTarget.endsWith("view") && file.getParent().endsWith("xml"))
				{
					fileToRead = "template.xml";
				}
				else if (pathTarget.endsWith("view") && file.getParent().endsWith("txt"))
				{
					fileToRead = "template.txt";
				}
				else if (pathTarget.endsWith("view") || pathTarget.endsWith("form"))
				{
					fileToRead = "template.htm";
				}
				else
				{
					continue;
				}
			}
			BufferedReader inTrg = null;
			FileWriter out = null;
			try 
			{					
				System.out.println("Path Target Third --> "+pathTarget+"\\"+fileToRead);
				inTrg = new BufferedReader(new FileReader(pathTarget+"\\"+fileToRead));

				String pdf = "";
				String excel = "";
				String txt = "";
				String xml = "";
				String htm = "";
				boolean pdfWithFilter = false;
				while(true)
				{
					String line = inTrg.readLine();
					if (line == null)
					{
						break;
					}
					if (line.indexOf("Desplegar calendario") != -1)
					{
						line = line.replaceFirst("Desplegar calendario", "\\$\\{lbl\\:b_deploy_calendar\\}");
					}
					if (line.indexOf("Seleccione") != -1)
					{
						line = line.replaceFirst("Seleccione", "\\$\\{lbl\\:b_choose\\}");
					}
					String item = "";
					String field = "";
					//System.out.println("Thrid Line --> "+line);
					for(int k = 0;  k < robotJoinList.size(); k++)
					{
						RobotJoin rJoin = robotJoinList.elementAt(k);
						String newField = "";
						String join = rJoin.getFieldJoin();
						item = rJoin.getFieldItem();
						field = rJoin.getFieldId();
						if (item.indexOf(",") != -1)
						{
							item = item.substring(0, item.lastIndexOf(","));
						}
						if (line.indexOf("filter.params") != -1)
						{
							//System.out.println("FILTER FOUND --> "+line);
							pdfWithFilter = true;
						}
						if (line.indexOf(join) != -1)
						{
							if ((item.indexOf("as") != -1) && (item.indexOf("2") != -1))
							{
								//System.out.println("item --> "+item);
								newField = item.substring(item.lastIndexOf("as")+2);
							}
							else
							{
								newField = item.substring(item.lastIndexOf(".")+1);
							}
							//System.out.println("Replacing ITEM --> "+item);
							if (pathTarget.endsWith("form"))
							{	
								if (line.indexOf(join+"_rows") == -1)
								{
									line = line.replaceAll(join, newField.trim());
								}
							}
							else
							{
								if (pathTarget.endsWith("pdf"))
								{
									if (pdfWithFilter)
									{
										pdfWithFilter = false;
									}
									else
									{
										line = line.replaceAll(join, newField.trim());
									}
								}
								else
								{
									line = line.replaceAll(join, newField.trim());
								}
							}
						}
						else
						{
							String fieldID = field.substring(field.lastIndexOf(".")+1)+"_";
							if (line.indexOf(fieldID) != -1)
							{
								if (line.indexOf("sortBy") == -1 && line.indexOf("${fld:") == -1)
								{
									String newFieldId = line.substring(line.indexOf(fieldID));
									System.out.println("newFieldId --> " + newFieldId);
									if (newFieldId.indexOf("\"") != -1)
									{
										newFieldId = newFieldId.replaceAll("\"", "'");
									}
									if (newFieldId.indexOf("'") != -1)
									{
										newFieldId = newFieldId.substring(0, newFieldId.indexOf("'"));
									}
									else
									{
										newFieldId = newFieldId.substring(0, newFieldId.indexOf(" ")-1);
									}
									//System.out.println("ITEM --> " + item);
									item = item.substring(item.lastIndexOf("as")+2);
									//System.out.println("ITEM --> " + item);
									item = item.substring(1);
									//System.out.println("ITEM --> " + item + "   REPLACE --> "+newFieldId);
									line = line.replaceAll(newFieldId, item.trim());
								}
								else
								{
									String newFieldId = line.substring(line.indexOf(fieldID));
									if (line.indexOf("sortBy") != -1)
									{
										newFieldId = newFieldId.substring(0, newFieldId.indexOf(",")-1);
									}
									else
									{
										newFieldId = newFieldId.substring(0, newFieldId.indexOf("}"));
									}
									//System.out.println("ITEM --> " + item);
									item = item.substring(item.lastIndexOf("as")+2);
									//System.out.println("ITEM --> " + item);
									item = item.substring(1);
									//System.out.println("ITEM --> " + item + "   REPLACE --> "+newFieldId);
									//line = line.replaceAll(newFieldId, item.trim());															
								}
							}
						}
						if (pathTarget.endsWith("pdf"))
						{
							line = line.replaceAll("format='######'","");
						}
					}
					if (pathTarget.endsWith("pdf"))
					{
						line = line.replaceAll("center", "left");
						line = line.replaceAll("right", "left");
						pdf += line + "\n";
					}
					else if (pathTarget.endsWith("excel"))
					{
						excel += line + "\n";
					}
					else if (file.getParent().endsWith("txt"))
					{
						txt += line + "\n";		
					}
					else if (file.getParent().endsWith("xml"))
					{
						xml += line + "\n";			
					}
					else if (pathTarget.endsWith("view") || pathTarget.endsWith("form"))
					{
						htm += line + "\n";		
					}
				}
				if (inTrg != null) inTrg.close();
				if (!pdf.isEmpty())
				{
					//System.out.println("Storing in path --> "+pathTarget+"\\"+fileToRead);
					out = new FileWriter(pathTarget+"\\"+fileToRead);
					//System.out.println("PDF to replace --> " + pdf);
					out.write(pdf);
					if (out  != null) out.close();
					pdf = ""; 
				}
				else if (!excel.isEmpty())
				{
					//System.out.println("Storing in path --> "+pathTarget+"\\"+fileToRead);
					out = new FileWriter(pathTarget+"\\"+fileToRead);
					//System.out.println("EXCEL to replace --> " + excel);
					out.write(excel);
					if (out  != null) out.close();
					excel = ""; 
				}
				else if (!txt.isEmpty())
				{
					//System.out.println("Storing in path --> "+pathTarget+"\\"+fileToRead);
					out = new FileWriter(pathTarget+"\\"+fileToRead);
					//System.out.println("TXT to replace --> " + txt);
					out.write(txt);
					if (out  != null) out.close();
					txt = "";
				}
				else if (!xml.isEmpty())
				{
					//System.out.println("Storing in path --> "+pathTarget+"\\"+fileToRead);
					out = new FileWriter(pathTarget+"\\"+fileToRead);
					//System.out.println("XML to replace --> " + xml);
					out.write(xml);
					if (out  != null) out.close();
					xml = "";
				}
				else if (!htm.isEmpty())
				{
					//System.out.println("Storing in path --> "+pathTarget+"\\"+fileToRead);
					out = new FileWriter(pathTarget+"\\"+fileToRead);
					//System.out.println("HTM to replace --> " + htm);
					out.write(htm);
					if (out  != null) out.close();
					htm = "";
				}
				break;
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
					if (inTrg  != null)
					{
						inTrg.close();
					}
					if (out  != null)
					{
						out.close();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void createValidatorFile(String pathTarget, String column, String sql)
	{
		System.out.println("Created file (createValidatorFile) --> " + sql + " in " + pathTarget);
		FileWriter validatorFile = null;
		try {
			validatorFile = new FileWriter(pathTarget+"\\"+sql);
			validatorFile.write("select\n");
			validatorFile.write("\t\t" + util.getDatabaseSchema() + 
					"." + util.getCurrentTable()   + 
					"." + column + "\n");
			validatorFile.write("from\n");
			validatorFile.write("\t\t" + util.getDatabaseSchema() + 
					"." + util.getCurrentTable() + "\n");	
			validatorFile.write("where\n");
			validatorFile.write("\t\tupper(" + util.getDatabaseSchema() + 
					"." + util.getCurrentTable()   + 
					"." + column + ") = upper(${fld:" + column + "})\n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (validatorFile != null)
				{
					validatorFile.close();
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
	}

	public String fastReplace( String str, String target, String replacement ) {
		int targetLength = target.length();
		if( targetLength == 0 ) {
			return str;
		}
		int idx2 = str.indexOf( target );
		if( idx2 < 0 ) {
			return str;
		}
		StringBuilder buffer = new StringBuilder( targetLength > replacement.length() ? str.length() : str.length() * 2 );
		int idx1 = 0;
		do {
			buffer.append( str, idx1, idx2 );
			buffer.append( replacement );
			idx1 = idx2 + targetLength;
			idx2 = str.indexOf( target, idx1 );
		} while( idx2 > 0 );
		buffer.append( str, idx1, str.length() );
		return buffer.toString();
	}

	public void copyStandardFiles(File file, String source, String target, String[] noCopyFiles) throws IOException {
		String robotDirectory = parameters.get(DIRECTORY_ROBOT);

		File directoryCopied = null;
		directoryCopied = new File(file.getPath() + "\\smn_copied.txt");
		util.copyFilesIntoDitectories(robotDirectory + "/smn_standard" + source, file.getPath(), noCopyFiles);
		directoryCopied.createNewFile(); 

		String parentDir = file.getPath().substring(0,file.getPath().lastIndexOf("\\"));
		parentDir = parentDir.substring(0,parentDir.lastIndexOf("\\"));
		directoryCopied = new File(parentDir+"\\" + target + "\\smn_copied.txt");
		util.copyFilesIntoDitectories(robotDirectory + "/smn_standard" + source, parentDir+"\\" + target, noCopyFiles);
		directoryCopied.createNewFile();
	}

	public static void copyFolder(File source, File destination)
	{
		if (source.isDirectory())
		{
			if (!destination.exists())
			{
				destination.mkdirs();
			}

			String files[] = source.list();

			for (String file : files)
			{
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);

				copyFolder(srcFile, destFile);
			}
		}
		else
		{
			InputStream in = null;
			OutputStream out = null;

			try
			{
				in = new FileInputStream(source);
				out = new FileOutputStream(destination);

				byte[] buffer = new byte[1024];

				int length;
				while ((length = in.read(buffer)) > 0)
				{
					out.write(buffer, 0, length);
				}
			}
			catch (Exception e)
			{
				try
				{
					if (in != null)
					{
						in.close();
					}
					if (out != null)
					{
						out.close();
					}
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	private static void copySimpleFile(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	private String pkgResult = "";
	public String getPackage( String path, String directory ) throws IOException 
	{
		//System.out.println( "GetPackage - PATH --> " + path + " | DIRECTORY: " + directory);
		File root = new File( path );
		File[] list = root.listFiles();

		if (list == null) return "";

		for ( File f : list ) 
		{
			if ( f.isDirectory() && f.getName().startsWith("smn_")) 
			{
				String dir = f.getName();
				//System.out.println( "GetPackage - Directory --> " + f.getAbsolutePath() + " | Name: " + dir + " | Directory: " + directory);
				if (dir.compareTo( directory ) == 0)
				{
					pkgResult = f.getAbsolutePath();
					//System.out.println( "GetPackage - Encontrado --> " + pkgResult );
					break;
				}
				getPackage ( f.getAbsolutePath(), directory );
			}
		}
		return pkgResult;
	}

	public String getFilterColumns(String directory)
	{
		File root = new File( directory );
		File[] list = root.listFiles();

		if (list == null) return "";

		String fields = "";
		for ( File f : list ) 
		{
			String clauseFile = f.getName();
			if ((clauseFile.indexOf("fecha_registro") == -1) &&
					(clauseFile.indexOf("clause-") != -1) &&
					(clauseFile.indexOf("fdesde") == -1) &&
					(clauseFile.indexOf("fhasta") == -1))
			{
				clauseFile = clauseFile.substring(clauseFile.indexOf("-")+1,clauseFile.indexOf("."));
				fields += clauseFile + ";";
			}
		}
		return fields;
	}

}
