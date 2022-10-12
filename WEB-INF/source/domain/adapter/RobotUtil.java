package domain.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

public class RobotUtil 
{
	//Robot syntax table
	private Hashtable<String,Boolean> syntaxTable = new Hashtable<String,Boolean>();

	//Database string connection
	private String DRIVER = "org.postgresql.Driver";
	private String URL    = "jdbc:postgresql://localhost:5432/simonedb";
	private String USER   = "postgres"; 
	private String PWD    = "postgres";

	private String DATABASE = "";
	private String SCHEMA   = "";
	@SuppressWarnings("unused")
	private String SECURITY = "";

	private String[] filterDirList = new String[]
			{"delete","detail","editor","excel","form","notfound","pdf","search","view"};
	private String[] panelDirList = new String[]
			{"hgrid","view"};
	private String[] viewrecordDirList = new String[]
			{"form","pdf"};
	private String[] importexcelDirList = new String[]
			{"error","excel","excelerror","excelerrorview","excelok","form","insert","pdf","upload"};
	private String[] pagedviewDirList = new String[]
			{"excel","form","notfound","pdf","search","view"};
	private String[] repgenDirList = new String[]
			{"cargar_config","detail","email","excel","fields","form","insert","notfound","pdf","search","txt","view","xml"};

	private String currentTable = null;
	private Vector<String> currentTableDetail = null;

	private Hashtable<String,Vector<RobotRepGenFields>> repgenList = null;
	private Hashtable<String,Vector<RobotMetadata>> metadataList = null;
	private Hashtable<String,Vector<RobotMetadata>> metadataFKList = null;

	public static int amountFields = 0;
	public static int amountFieldsFK = 0; 
	public static int amountRepGenFields = 0;

	private String prefixColumnTable = "";
	private String prefixColumnTableDetail = "";

	public final static Logger logger = Logger.getLogger(RobotUtil.class);

	public static final String LOGGER = "LOGGER";
	public static final String STDOUT = "STDOUT";

	private RobotActionProcess robotProcess = null;

	private boolean tableHasForeignKey = true;

	public RobotUtil()
	{
		//Base Syntax
		syntaxTable.put("@ADAPTER_START", false);
		syntaxTable.put("@ADAPTER_END", false);
		syntaxTable.put("@DIRECTORY_GENERATED_FILTER", false);
		syntaxTable.put("@DIRECTORY_GENERATED_ADDNEW", false);
		syntaxTable.put("@DIRECTORY_GENERATED_ADDNEWMD", false);
		syntaxTable.put("@DIRECTORY_GENERATED_ADDNEWMDMUL", false);
		syntaxTable.put("@DIRECTORY_GENERATED_VIEWCHART", false);
		syntaxTable.put("@DIRECTORY_GENERATED_DATECHART", false);
		syntaxTable.put("@DIRECTORY_GENERATED_VIEWRECORD", false);
		syntaxTable.put("@DIRECTORY_GENERATED_IMPORTEXCEL", false);
		syntaxTable.put("@DIRECTORY_GENERATED_PAGEDVIEW", false);
		syntaxTable.put("@DIRECTORY_GENERATED_FILTER", false);
		syntaxTable.put("@DIRECTORY_STANDARD_REPGEN", false);
		syntaxTable.put("@DIRECTORY_STANDARD_PANEL", false);
		syntaxTable.put("@DATABASE", false);
		syntaxTable.put("@DATABASE_DRIVER", false);
		syntaxTable.put("@DATABASE_URL", false);
		syntaxTable.put("@DATABASE_SCHEMA", false);
		syntaxTable.put("@DATABASE_SECURITY", false);
		syntaxTable.put("@VALIDATOR_FILE", false);
		syntaxTable.put("@EXECUTE_SCRIPT", false);

		//Filter Syntax
		syntaxTable.put("@FILTER", false);
		syntaxTable.put("@START_FILTER", false);
		syntaxTable.put("@END_FILTER", false);
		syntaxTable.put("@COPY_STANDARD_DIRECTORY", false);
		syntaxTable.put("@DISABLED_FIELD", false);

		robotProcess = new RobotActionProcess();

		metadataList = new Hashtable<String,Vector<RobotMetadata>>();
		metadataFKList = new Hashtable<String,Vector<RobotMetadata>>();
		repgenList = new Hashtable<String,Vector<RobotRepGenFields>>();
	}

	public boolean isTableHasForeignKey() {
		return tableHasForeignKey;
	}

	public void setTableHasForeignKey(boolean tableHasForeignKey) {
		this.tableHasForeignKey = tableHasForeignKey;
	}

	public RobotActionProcess getRobotActionProcess()
	{
		return robotProcess;
	}

	public void printInfo(String type)
	{
		if (type.compareTo(Robot.LOGGER) == 0)
		{
			RobotUtil.logger.info("\nSimoneitor - Electronic robot that allow to adapt any SIMONE web page applying a right standard.");
			RobotUtil.logger.info("\nRobotAdapter\t[-robotpath  <robot directory path>]");
			RobotUtil.logger.info("\nThis robot help to lead step by step until to adapt complete web page.\n");
		}
		else
		{
			System.out.println("\nSimoneitor - Electronic robot that allow to adapt any SIMONE web page applying a right standard.");
			System.out.println("\nRobotAdapter\t[-robotpath  <robot directory path>]");
			System.out.println("\nThis robot help to lead step by step until to adapt complete web page.\n");
		}
	}

	//función de utilidad que permite copiar los archivos de un directorio fuente a uno destino.
	public void copyFilesIntoDitectories(String pathSource, String pathTarget, String[] noCopyFiles) throws IOException 
	{
		logger.info("Coping file from " + pathSource + " to " + pathTarget);
		File templateDir = new File(pathSource);

		File[] files = templateDir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			String filePathSource = pathSource + "/" + files[i].getName(); 
			String filePathTarget = pathTarget + "/" + files[i].getName();
			boolean copyFile = true;
			if (noCopyFiles != null)
			{
				for(int j = 0; j < noCopyFiles.length; j++)
				{
					if (files[i].getName().compareTo(noCopyFiles[j]) == 0)
					{
						copyFile = false;
						break;
					}
				}
			}
			if (copyFile)
			{
				Path source = Paths.get(filePathSource);
				Path target = Paths.get(filePathTarget);
				File targetDirectory = new File(pathTarget);
				if (!targetDirectory.exists())
				{
					boolean directoryCreated = targetDirectory.mkdir();
					if (directoryCreated)
					{
						logger.info("Was created directory " + pathTarget);
					}
				}
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		logger.info("Standard directory " + pathSource + " already been copied");
	}

	public void checkRobotInstructions(String path)
	{
		String directory = path.substring(path.lastIndexOf("/") + 1, path.length());
		if (!directory.startsWith("smn_"))
		{
			logger.error("Please this directory " + directory + " must be changed to standard smn_"+ directory);
			System.exit(0);
		}
		if (directory.endsWith("robot"))
		{
			checkRobotSyntax(path + "/smn_instructions/" + directory + ".txt");
		}
		else 
		{
			File directoryList = new File(path);
			String[] list = directoryList.list();
			for(int i = 0; i < list.length; i++)
			{
				userInteraction(path + "/" + list[i],"filter");
				//userInteraction(path,"viewrecord");
				//userInteraction(path,"importexcel");
				//userInteraction(path,"pagedview");
				//userInteraction(path,"repgen");
				//userInteraction(path,"panel");
			}
		}
	}

	public void checkRobotSyntax(String path)
	{
		BufferedReader in = null;
		try 
		{
			boolean filterProcessing = false;
			in = new BufferedReader(new FileReader(path));
			Vector<String> instructionList = new Vector<String>();
			String directory = "";
			while(true)
			{
				String line = in.readLine();
				if (line == null)
				{
					break;
				}
				checkSyntax(line);
				if ((line.indexOf("@FILTER") != -1) || (line.indexOf("@START_FILTER") != -1))
				{
					if (line.indexOf("@FILTER") != -1)
					{
						StringTokenizer st = new StringTokenizer(line);
						st.nextToken();
						directory = st.nextToken().toLowerCase();
					}
					filterProcessing = true;
				} 
				else
				{
					if (filterProcessing)
					{
						if (line.indexOf("@END_FILTER") != -1)
						{
							robotProcess.filterProcess(directory, instructionList);
							filterProcessing = false;
						}
						else
						{
							instructionList.addElement(line.trim());
						}
					}
				}
			}
			Enumeration<String> enumeration = syntaxTable.keys();
			while (enumeration.hasMoreElements())
			{
				String key = enumeration.nextElement();			
				Boolean isValid = syntaxTable.get(key);
				if (!isValid)
				{
					logger.warn("Robot detected this syntax " + key + " is not used.");
				}				
			}
		} 
		catch (FileNotFoundException e) 
		{
			logger.error(e.toString());
		} catch (IOException e) 
		{
			logger.error(e.toString());
		} 
		finally
		{
			if (in != null)
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					logger.error(e.toString());
				}
			}
		}
	}

	public void checkSyntax(String line)
	{
		line = line.trim();
		if (line.compareTo("") == 0)
		{
			return;
		}		
		String instruction = line.substring(line.indexOf("@"),line.length());
		StringTokenizer st = new StringTokenizer(line," ");
		instruction = st.nextToken();
		if (syntaxTable.containsKey(instruction))
		{
			syntaxTable.put(instruction, true);
			String value = "";
			while(st.hasMoreTokens())
			{
				value += st.nextToken() + " ";
			}
			loadVariables(instruction, value.trim());
			if ((instruction.compareTo("@DIRECTORY_GENERATED_FILTER") == 0) ||
					(instruction.compareTo("@DIRECTORY_GENERATED_VIEWRECORD") == 0) ||
					(instruction.compareTo("@DIRECTORY_GENERATED_IMPORTEXCEL") == 0) ||
					(instruction.compareTo("@DIRECTORY_GENERATED_PAGEDVIEW") == 0) ||
					(instruction.compareTo("@DIRECTORY_STANDARD_REPGEN") == 0) ||
					(instruction.compareTo("@DIRECTORY_STANDARD_PANEL") == 0))
			{
				boolean defined = checkMandatoryInstructions(instruction);
				if (!defined)
				{
					logger.error("Mandatory instructions: " + instruction + ".");
					System.exit(0);
				}
			}
		}
		else
		{
			logger.error("Not valid instruction: " + instruction + ".");
			System.exit(0);
		}
	}

	public boolean checkDirectoriesGenerated(String path, String[] directories, String type)
	{
		File files = new File(path);
		String[] listFiles = files.list();
		if (listFiles == null)
		{
			logger.error("Robot detected \"" + type + "\" has not been generated.");
			return false;
		}
		for(int i = 0; i < directories.length; i++)
		{
			boolean found = false;
			for(int j = 0; j < listFiles.length; j++)
			{
				if (directories[i].compareTo(listFiles[j]) == 0)
				{
					found = true;
				}
			}
			if (!found)
			{
				logger.error("Robot detected \"" + type + "\" has not been generated correctly. This directory \"" + directories[i] + "\" is absence.");
				return false;	
			}
		}
		return true;
	}

	public boolean checkMandatoryInstructions(String instruction)
	{
		return syntaxTable.get(instruction);
	}

	public void loadVariables(String key, String value)
	{
		if (key.compareTo("@DATABASE") == 0)
		{
			StringTokenizer st = new StringTokenizer(value," ");
			DATABASE = st.nextToken();
			st.nextToken();
			USER = st.nextToken();
			st.nextToken();
			PWD = st.nextToken();
		}
		else if (key.compareTo("@DATABASE_DRIVER") == 0)
		{
			DRIVER = value;
		}
		else if (key.compareTo("@DATABASE_URL") == 0)
		{
			URL = value + "/" + DATABASE;
		}
		else if (key.compareTo("@DATABASE_SCHEMA") == 0)
		{
			SCHEMA = value;
		}
		else if (key.compareTo("@DATABASE_SECURITY") == 0)
		{
			SECURITY = value;
		}
	}

	public String getCurrentTable()
	{
		return currentTable;
	}

	public String getDatabaseSchema()
	{
		return SCHEMA;
	}

	public void connectSimoneDBForeignKey(String directory)
	{
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		Connection conFK = null;
		Statement stmFK = null;
		ResultSet rsFK = null;

		try 
		{
			//for(int i = 0; i < directories.length; i++)
			//{
			con = getConnection(DRIVER, URL, USER, PWD);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = null;

			String sql = "select " +
					"		R.TABLE_NAME " +
					"from " +
					"     	INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE u " +
					"	  	inner join INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS FK " +
					"	  	on U.CONSTRAINT_CATALOG = FK.UNIQUE_CONSTRAINT_CATALOG "+
					"	  	and U.CONSTRAINT_SCHEMA = FK.UNIQUE_CONSTRAINT_SCHEMA " + 
					"	  	and U.CONSTRAINT_NAME = FK.UNIQUE_CONSTRAINT_NAME " +
					"     	inner join INFORMATION_SCHEMA.KEY_COLUMN_USAGE R " +
					"	  	ON R.CONSTRAINT_CATALOG = FK.CONSTRAINT_CATALOG " +
					"	  	AND R.CONSTRAINT_SCHEMA = FK.CONSTRAINT_SCHEMA " + 
					"     	AND R.CONSTRAINT_NAME = FK.CONSTRAINT_NAME " +
					"where  " +
					"     	U.TABLE_SCHEMA = '" + SCHEMA + "'" +
					"     	AND " +
					"		U.TABLE_NAME = '" + directory + "'";
			rs = stm.executeQuery(sql);

			logger.info("Looking for foreign keys in \"" + directory + "\" from database \"" + DATABASE + "\".");
			if (getRowCount(rs) > 0)
			{				
				amountFieldsFK = 0;
				currentTableDetail = new Vector<String>();
				while(rs.next())
				{
					conFK = getConnection(DRIVER, URL, USER, PWD);
					stmFK = conFK.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rsFK = null;

					String tableDetail = rs.getString(1);
					logger.info("Table \"" + tableDetail + "\" Foreign key found from \"" + directory + "\".");

					String sqlFK = "select " +
							"		column_name, " + 
							"     	data_type,   " +
							"     	is_nullable, " +
							"     	numeric_precision, " +
							"     	character_maximum_length " +							 
							"from  " +
							"     	information_schema.columns " +
							"where " +
							"     	table_name = '" + tableDetail + "'";
					rsFK = stmFK.executeQuery(sqlFK);

					currentTableDetail.add(tableDetail);

					logger.info("Loading metadata FK information about \"" + tableDetail + "\" from database \"" + DATABASE + "\".");
					Vector <RobotMetadata> listFK = new Vector<RobotMetadata>();
					amountFieldsFK = getRowCount(rsFK) - 5;
					while (rsFK.next())
					{	
						RobotMetadata metadataFK = new RobotMetadata(tableDetail, rsFK.getString(1),rsFK.getString(2),rsFK.getString(3).toLowerCase(),rsFK.getInt(4), rsFK.getInt(5), true);
						logger.info("Column: " + metadataFK.getColumn() + " | " +
								"Type: " + metadataFK.getType() +  " | " +
								"Nullable? " + metadataFK.getNullable().toLowerCase() +  " | " +
								"Size: " + (metadataFK.getVarcharSize() != 0 ? metadataFK.getVarcharSize(): metadataFK.getNumericSize()));	
						listFK.addElement(metadataFK);

						String column = metadataFK.getColumn();
						if (column.indexOf("fecha_registro") != -1)
						{
							this.setPrefixColumnTableDetail(column.substring(0, column.indexOf("_")));
						}
					}
					logger.info("Amount of metadata FK found was: " + amountFieldsFK + ".");
					metadataFKList.put(directory, listFK);
					tableHasForeignKey = true;
				}
			}
			else
			{
				logger.info("Table foreign keys fom \"" + directory + "\" not found.");	
				tableHasForeignKey = false;
			}
			//}
		} 
		catch (Throwable e) 
		{
			logger.error(e.toString());
		} 
		finally
		{
			try {
				if (rs != null) rs.close();
				if (stm != null) stm.close();
				if (con != null) con.close();

				if (rsFK != null) rsFK.close();
				if (stmFK != null) stmFK.close();
				if (conFK != null) conFK.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.toString());
			}
		}
	}

	public Vector<String> getCurrentTableDetail() {
		return currentTableDetail;
	}

	public void setCurrentTableDetail(Vector<String> currentTableDetail) {
		this.currentTableDetail = currentTableDetail;
	}

	public String getPrefixColumnTable() {
		return prefixColumnTable;
	}

	public void setPrefixColumnTable(String prefixColumnTable) {
		this.prefixColumnTable = prefixColumnTable;
	}

	public String getPrefixColumnTableDetail() {
		return prefixColumnTableDetail;
	}

	public void setPrefixColumnTableDetail(String prefixColumnTableDetail) {
		this.prefixColumnTableDetail = prefixColumnTableDetail;
	}

	public Hashtable<String,Vector<RobotMetadata>> getMetadataFKList()
	{
		return metadataFKList;
	}

	private int getRowCount(ResultSet resultSet) {
		if (resultSet == null) {
			return 0;
		}
		try {
			resultSet.last();
			return resultSet.getRow();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return 0;
	}

	public boolean existsRepGenFields()
	{
		boolean exists = false;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try 
		{
			//for(int i = 0; i < directories.length; i++)
			//{
			con = getConnection(DRIVER, URL, USER, PWD);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = null;

			String sql = "select " + 
					"		*   " + 
					"from " +
					"     	smn_base.fields " +
					" where " +
					"     	smn_base.fields.colname like '%" + getDatabaseSchema() + "." + getCurrentTable() + ".%' and " +
					"       smn_base.fields.action_root like '%" + getCurrentTable() + "%'";
			rs = stm.executeQuery(sql);

			logger.info("Verifying weather exists metadata information about \"smn_base.fields\" from database \"" + DATABASE + "\".");
			if (rs.next())
			{	
				exists = true;
				logger.info("Information about \"smn_base.fields\" from database \"" + DATABASE + "\" exists.");
			}
			else
			{
				logger.info("Information about \"smn_base.fields\" from database \"" + DATABASE + "\" does not exists.");
			}

			//}
		} 
		catch (Throwable e) 
		{
			logger.error(e.toString());
		} 
		finally
		{
			try {
				rs.close();
				stm.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.toString());
			}
		}
		return exists;
	}

	public void connectInsertRepGenFields(Vector<RobotRepGenFields> repgenList)
	{
		Connection con = null;
		Statement stm = null;
		try 
		{
			con = getConnection(DRIVER, URL, USER, PWD);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int n = repgenList.size();
			String actionRoot = "";
			System.out.println("N = " + n);
			for(int i = 0; i < n; i++)
			{
				RobotRepGenFields field = repgenList.elementAt(i);
				
				String sql = "insert into smn_base.fields (" + 
						 "field_id, colname, alias, align, width_px, market, orden, format, type, action_root ) " +
						 "values (nextval('smn_base.seq_fields'), " + 
						 "'" + field.getColname() + "', " +
						 "'" + field.getAlias() + "', " +
						 "'" + field.getAlign() + "', " +
						 field.getWidthPixel() + ", " +
						 "'" + field.getMarket() + "', " +
						 field.getOrden() + ", " +
						 "'" + field.getFormat() + "', " +
						 "'" + field.getType() + "', " + 
						 "'" + field.getActionRoot() + "')";
				actionRoot = field.getActionRoot();
				
				stm.executeUpdate(sql);

				logger.info("Inserting metadata information into \"smn_base.fields\" from database \"" + DATABASE + "\" Colname = " + field.getColname() + ".");
			}
			String sql = "insert into smn_base.fields (" + 
					 "field_id, colname, alias, align, width_px, market, orden, format, type, action_root ) " +
					 "values (nextval('smn_base.seq_fields'), " + 
					 "'" + getDatabaseSchema() + "." + getCurrentTable() + "." + getPrefixColumnTable() + "_fecha_registro', " +
					 "'${lbl:b_store_date}', " +
					 "'center', " +
					 "150, " +
					 "'" + getPrefixColumnTable() + "_fecha_registro', " +
					 (n+1) + ", " +
					 "'@dd-MM-yyyy', " +
					 "'date', " + 
					 "'" + actionRoot + "')";
			
			stm.executeUpdate(sql);
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
			logger.error(e.toString());
		} 
		finally
		{
			try {
				stm.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.toString());
			}
		}
	}

	public void connectRepGenFields()
	{
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try 
		{
			amountRepGenFields = 0;
			//for(int i = 0; i < directories.length; i++)
			//{
			con = getConnection(DRIVER, URL, USER, PWD);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = null;

			String sql = "select " + 
					"		smn_base.fields.field_id,   " + 
					"		smn_base.fields.colname,   " +
					"		smn_base.fields.alias,   " +
					"		smn_base.fields.align,   " +
					"		smn_base.fields.width_px,   " +
					"     	smn_base.fields.market,     " +
					"		smn_base.fields.orden,   " +
					"		smn_base.fields.format,   " +
					"		smn_base.fields.type,   " +
					"     	smn_base.fields.action_root " +
					"from " +
					"     	smn_base.fields " +
					" where " +
					"     	smn_base.fields.colname like '%" + this.getCurrentTable() + "%'";
			rs = stm.executeQuery(sql);

			logger.info("Loading metadata information about \"smn_base.fields\" from database \"" + DATABASE + "\".");
			Vector <RobotRepGenFields> list = new Vector<RobotRepGenFields>();
			amountRepGenFields = getRowCount(rs);
			while (rs.next())
			{	
				RobotRepGenFields fields = new RobotRepGenFields(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8),  rs.getString(9), rs.getString(10));
				logger.info("FieldID: " + fields.getFieldID()+  " | " +
						"Colname: " + fields.getColname()+  " | " +
						"Alias: " + fields.getAlias()+  " | " +
						"Align: " + fields.getAlign()+  " | " +
						"WidthPixel: " + fields.getWidthPixel()+  " | " +
						"Market: " + fields.getMarket()+  " | " +
						"Order: " + fields.getOrden()+  " | " +
						"Format: " + fields.getFormat()+  " | " +
						"Type: " + fields.getType()+  " | " +
						"ActionRoot: " + fields.getActionRoot());	
				list.addElement(fields);
			}
			logger.info("Amount of repgen fields found was: " + amountRepGenFields + ".");
			repgenList.put("fields", list);
			//}
		} 
		catch (Throwable e) 
		{
			logger.error(e.toString());
		} 
		finally
		{
			try {
				rs.close();
				stm.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.toString());
			}
		}
	}

	public Hashtable<String,Vector<RobotRepGenFields>> getRepGenList()
	{
		return repgenList;
	}

	public String getRepGenActionRoot()
	{
		Vector <RobotRepGenFields> list = repgenList.get("fields");
		for(int i = 0; i < list.size(); i++)
		{
			RobotRepGenFields repGenField = list.elementAt(i);
			if (repGenField.getActionRoot().indexOf(this.getCurrentTable()) != -1)
			{
				return repGenField.getActionRoot();
			}
		}
		return "";
	}

	public void connectSimoneDB(String directories)
	{
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try 
		{
			amountFields = 0;
			//for(int i = 0; i < directories.length; i++)
			//{
			con = getConnection(DRIVER, URL, USER, PWD);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = null;

			String sql = "select " + 
					"		column_name, " + 
					"     	data_type,   " +
					"     	is_nullable, " +
					"     	numeric_precision, " + 
					"     	character_maximum_length " +
					"from " +
					"     	information_schema.columns " +
					" where " +
					"     	table_name = '" + directories + "'";
			rs = stm.executeQuery(sql);

			currentTable = directories;

			logger.info("Loading metadata information about \"" + directories + "\" from database \"" + DATABASE + "\".");
			Vector <RobotMetadata> list = new Vector<RobotMetadata>();
			amountFields = getRowCount(rs) - 5;
			while (rs.next())
			{	
				RobotMetadata metadata = new RobotMetadata(directories, rs.getString(1),rs.getString(2),rs.getString(3).toLowerCase(),rs.getInt(4),rs.getInt(5),false);
				logger.info("Column: " + metadata.getColumn() +  " | " +
						"Type: " + metadata.getType() +  " | " +
						"Nullable? " + metadata.getNullable().toLowerCase() +  " | " + 
						"Size: " + (metadata.getVarcharSize() != 0 ? metadata.getVarcharSize(): metadata.getNumericSize()));	
				list.addElement(metadata);

				String column = metadata.getColumn();
				if (column.indexOf("fecha_registro") != -1)
				{
					this.setPrefixColumnTable(column.substring(0, column.indexOf("_")));
				}
			}
			logger.info("Amount of metadata found was: " + amountFields + ".");
			metadataList.put(directories, list);
			//}
		} 
		catch (Throwable e) 
		{
			logger.error(e.toString());
		} 
		finally
		{
			try {
				rs.close();
				stm.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.toString());
			}
		}
	}

	public Hashtable<String,Vector<RobotMetadata>> getMetadataList()
	{
		return metadataList;
	}

	public Connection getConnection(String driver, String url, String user, String pwd) throws Throwable
	{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}

	public void userInteraction(String path, String action)
	{		
		boolean generated = false;
		if (action.compareTo("filter") == 0)
		{
			generated = checkDirectoriesGenerated(path + "/filter", filterDirList, action);
		}
		else if (action.compareTo("viewrecord") == 0)
		{
			generated = checkDirectoriesGenerated(path + "/detail", viewrecordDirList, "viewrecord");
		}
		else if (action.compareTo("importexcel") == 0)
		{
			generated = checkDirectoriesGenerated(path + "/importexcel", importexcelDirList, "importexcel");
		}
		else if (action.compareTo("pagedview") == 0)
		{
			generated = checkDirectoriesGenerated(path, pagedviewDirList, "pagedview");
		}
		else if (action.compareTo("repgen") == 0)
		{
			generated = checkDirectoriesGenerated(path + "/repgen", repgenDirList, "repgen");
		}
		else if (action.compareTo("panel") == 0)
		{
			generated = checkDirectoriesGenerated(path + "/panel", panelDirList, "panel");
		}
		if (generated)
		{
			return;
		}
		try {
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			generated = false;
			while(true)
			{
				logger.info("Please use 'Generar Filter' of Dinamica Framework.");
				logger.info("Ready? Please enter 'yes' when you finish: ");
				String line = br.readLine();
				if (line == null)
				{
					continue;
				}
				if (action.compareTo("filter") == 0)
				{
					generated = checkDirectoriesGenerated(path + "/filter", filterDirList, "filter");
					if ((generated) && (line.toLowerCase().compareTo("yes") == 0))
					{
						break;
					}
				}
			}
		} 
		catch (IOException e) 
		{
			logger.error(e.toString());
		}
	}

}
