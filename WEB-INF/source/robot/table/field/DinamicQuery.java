/**
 * 
 */
package robot.table.field;

import java.util.ArrayList;

/**
 * @author SIMONE 21015, C.A.
 *
 */
public class DinamicQuery  extends ReferenceField
{
	private String query;
	private ArrayList<String> select;
	private ArrayList<String> from;
	private ArrayList<String> where;
	private ArrayList<String> orderby;
	private String id;
	private String item;
}
