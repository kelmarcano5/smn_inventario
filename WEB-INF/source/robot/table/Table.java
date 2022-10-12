/**
 * 
 */
package robot.table;

import java.util.ArrayList;
import java.util.HashMap;

import robot.table.field.Field;

/**
 * @author SIMONE 21015, C.A.
 *
 */
public class Table 
{	
	private String prefix;
	private String tableName;
	private ArrayList<Field> fields;
	private HashMap<String, Table> detail;
	private DinamicGeneratorType dinamicGeneratorType; //action
	private String label;
}

