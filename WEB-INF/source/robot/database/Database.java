/**
 * 
 */
package robot.database;

import java.util.HashMap;

import robot.table.Table;

/**
 * @author SIMONE 21015, C.A.
 *
 */
public class Database 
{
	private DBMS dbms;
	private String driver;
	private String host;
	private int port;
	private String user; 
	private String password;
	private String databaseName; 
	private HashMap<String, Table> tables;
}
