/**
 * 
 */
package robot.table.field;

/**
 * @author SIMONE 21015, C.A.
 *
 */
public class Field 
{
	private String prefix;
	private String column;
	private FieldType type;
	private int size;
	private boolean isNull;
	private boolean isPrimaryKey;
	private boolean isForeignKey;
	private boolean isSequence;
	private String label;
	private ReferenceField reference;
}
