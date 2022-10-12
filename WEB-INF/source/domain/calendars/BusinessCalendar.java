package domain.calendars;

import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import dinamica.Recordset;
import dinamica.StringUtil;
import dinamica.ValidatorUtil;
import dinamica.calendar.DefaultCalendar;

/***
	Supongamos que necesitamos deshabilitar la selección de sábados,
	domingos y días feriados (bancarios, semana santa, etc.). Para estos
	últimos usaremos una tabla de base de datos para tenerlos
	almacenados y configurarlos mediante un módulo de mantenimiento (un CRUD Simple por ejemplo).
	Una clase que cumpla con el propósito de proveer los días a un calendario debe extender a la
	clase base dinamica.calendar.DefaultCalendar
 ***/
public class BusinessCalendar extends DefaultCalendar 
{
	@Override
	public int service(Recordset inputs) throws Throwable
	{
		super.service(inputs);

		String yearsRange = getConfig().getConfigValue("range", null);
		
		//retrieve parameters
		String id = getRequest().getParameter("id");
		String d = getRequest().getParameter("date");
		String d2 = getRequest().getParameter("date.lbound");
		String d3 = getRequest().getParameter("date.ubound");

		if (d2==null)
			d2 = "";

		if (d3==null)
			d3 = "";

		//set date
		Date calDate = ValidatorUtil.testDate(d, "dd-MM-yy");
		if (calDate==null)
			calDate = new Date();

		//lower bound date
		Date minDate = ValidatorUtil.testDate(d2, "dd-MM-yy");

		//upper bound date
		Date maxDate = ValidatorUtil.testDate(d3, "dd-MM-yy");

		// save attached textbox id - used for callbacks
		getRequest().setAttribute("parent.ElementID", id);
		getRequest().setAttribute("date.lbound", d2);
		getRequest().setAttribute("date.ubound", d3);

		// calendar object
		Calendar c = Calendar.getInstance();
		c.setLenient(true);
        c.setTime(calDate);
        //patch 2009-04-13 gdottori
        c.set( Calendar.HOUR, 0 );
        c.set( Calendar.HOUR_OF_DAY, 0 );
        c.set( Calendar.MINUTE, 0 );
        c.set( Calendar.SECOND, 0 );
        c.set( Calendar.MILLISECOND, 0 );
        //end patch

		//recordset with selected year and month
		Recordset rsDate = new Recordset();
		rsDate.append("year", Types.INTEGER);
		rsDate.append("month", Types.INTEGER);
		rsDate.addNew();
		rsDate.setValue("year", new Integer(c.get(Calendar.YEAR)));
		rsDate.setValue("month", new Integer(c.get(Calendar.MONTH)+1));

		//recordset with years
		int y = 15;
		if (yearsRange!=null)
			y = Integer.parseInt(yearsRange);
		Recordset rsYears = new Recordset();
		rsYears.append("year", Types.INTEGER);
		for (int i=c.get(Calendar.YEAR)-y; i<c.get(Calendar.YEAR)+y;i++)
		{
			rsYears.addNew();
			rsYears.setValue("year", new Integer(i));
		}

		//selected day (if date==null -> day = today)
		int day = c.get(Calendar.DAY_OF_MONTH);

		//set first day of month
		c.set(Calendar.DATE,1);

		//get partial date mask - used as an href parameter
		String partialDate = StringUtil.formatDate(calDate, "MM-yyyy");

		// define recordset
		Recordset rs = createCustomCalendarRecordset();

		// fill empty slots until 1st day of month
		for (int i=Calendar.SUNDAY; i<c.get(Calendar.DAY_OF_WEEK); i++)
		{
			rs.addNew();
			rs.setValue("onclick", "" );
			rs.setValue("html", null);
			rs.setValue("class", "calEmptyCell");
		}

		// feed recordset with selected month
		for (int i=1; i<=c.getActualMaximum(Calendar.DAY_OF_MONTH);i++)
		{
			c.set(Calendar.DATE, i);
			//String date = StringUtil.formatNumber(i, "00")  + "-" + partialDate;
			String date = StringUtil.lprint(String.valueOf(i), "0", 2)  + "-" + partialDate;
			rs.addNew();
			rs.setValue("day", new Integer(i));
			rs.setValue("onclick", this.getCellOnClickValue(id, c, day, minDate, maxDate, date));
			rs.setValue("html",  this.getCellHTML(c, day));
			rs.setValue("class",  this.getCellStyle(c, day, minDate, maxDate));
			//SIMONE - Verify when is a day off or holiday or weekend or working day
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY )
			{
				rs.setValue("title", "No Laborable");
			}
			else
			{
				rs.setValue("title", "Laborable");
			}
			Recordset rsf = this.getRecordset("feriados.sql");
			rsf.top();
			while (rsf.next())
			{
				if (rsf.getDate("fecha").compareTo(c.getTime())==0)
				{
					rs.setValue("title", rsf.getString("descripcion"));
					break;
				}
			}
			//SIMONE
		}

		// fill empty slots from last day of month until next saturday
		if (c.get(Calendar.DAY_OF_WEEK)< Calendar.SATURDAY)
		{
			for (int i = c.get(Calendar.DAY_OF_WEEK); i < Calendar.SATURDAY; i++)
			{
				rs.addNew();
				rs.setValue("onclick", "" );
				rs.setValue("html", null);
				rs.setValue("class", "calEmptyCell");
			}
		}

		//publish recordset as a request attribute for HGrid output class
		getRequest().setAttribute("calendar", rs);

		//publish recordset with current month and year (used by combos)
		publish("calconfig", rsDate);
		publish("years", rsYears);

		return 0;

	}

	/**
	 * Creates the recordset that contains the basic data to print a Calendar.
	 * @return Recordset with this structure: day (int), onclick (varchar), html (varchar), class (varchar)
	 * @throws Throwable
	 */
	public Recordset createCustomCalendarRecordset() throws Throwable
	{
		Recordset rs = new Recordset();
		rs.append("day", Types.INTEGER);
		rs.append("onclick", Types.VARCHAR);
		rs.append("html", Types.VARCHAR);
		rs.append("class", Types.VARCHAR);
		rs.append("title", Types.VARCHAR); //Field added by SIMONE
		return rs;
	}
	
	//getCellStyle: permite indicar el estilo a aplicar, desactivado, activado, otro.
	@Override
	protected String getCellStyle(Calendar c, int currentDay, Date minDate, Date maxDate) throws Throwable 
	{
		String style = super.getCellStyle(c, currentDay, minDate, maxDate);
		//desactivar sabados y domingos
		if (c.get(Calendar.DAY_OF_WEEK)==1 || c.get(Calendar.DAY_OF_WEEK)==7) 
		{
			style = "calDisabled";
			return style;
		}
		//desactivar feriados
		Recordset rs = this.getRecordset("feriados.sql");
		rs.top();
		while (rs.next())
		{
			if (rs.getDate("fecha").compareTo(c.getTime())==0)
			{
				if (rs.getString("pais") != null)
				{
					style="calDisabledOnlyCountry";
					if (rs.getString("estado") != null)
					{
						style="calDisabledRegional";
					}
				}
				else
				{
					style = "calDisabledFixed";
				}
				break;
			}
		}
		return style;
	}

	//getCellOnClickValue: permite indicar la acción en caso de click (no hacer nada, retornar la fecha, etc.)
	@Override
	protected String getCellOnClickValue(String elementID, Calendar c, int currentDay, Date minDate, Date maxDate, String date) throws Throwable 
	{
		String value = super.getCellOnClickValue(elementID, c, currentDay, minDate, maxDate, date);

		//desactivar sabados y domingos
		if (c.get(Calendar.DAY_OF_WEEK)==1 || c.get(Calendar.DAY_OF_WEEK)==7) 
		{
			value = "";
			return value;
		}

		//desactivar feriados
		Recordset rs = this.getRecordset("feriados.sql");
		rs.top();
		while (rs.next())
		{			
			if (rs.getDate("fecha").compareTo(c.getTime())==0)
			{
				value="";
				break;
			}
		}
		return value;
	}
}