package domain;

public class Marcaje {

	public static String getTransactionMarkType(String tipo_marcaje) 
	{
		StringBuilder builder = new StringBuilder();
		String[] listMark = tipo_marcaje.split(",");
		String[] listMarkLack = new String[listMark.length];

		//case when there is once mark
		if (listMark.length == 1)
		{
			if (listMark[0].compareTo("ENT") == 0)
			{
				builder.append("ENT");
				builder.append(",");
				builder.append("SAL_LACK");
			}
			else
			{
				builder.append("ENT_LACK");
				builder.append(",");
				builder.append("SAL");				
			}

			String result = builder.toString();
			//System.out.println("Builder: " + result);
			return result;
		}

		//initialize array
		for(int i = 0; i < listMarkLack.length; i++)
		{
			listMarkLack[i] = new String("0");
		}
		
		// 0   1   2   3   4   5   6   7   8   9
		//ENT,SAL,ENT,SAL,SAL,SAL,SAL,SAL,ENT,SAL
		//computing which are mark lack
		int i = 0;
		while(i < listMark.length - 1)
		{
			if (listMark[i].compareTo(listMark[i+1]) == 0)
			{
				if (listMark[i].compareTo("ENT") == 0)
				{
					listMarkLack[i] = "SAL_LACK";
					listMarkLack[i+1] = "SAL_LACK";
				}
				else
				{
					listMarkLack[i] = "ENT_LACK";
					listMarkLack[i+1] = "ENT_LACK";
				}
			}
			i+=2;
		}
		
		for(int k = 0; k < listMark.length; k++)
		{
			System.out.print(listMark[k] + ",");
		}
		System.out.println();

		
		for(int k = 0; k < listMarkLack.length; k++)
		{
			System.out.print(listMarkLack[k] + ",");
		}
		System.out.println();

		//complete mark lack
		int j = 0;
		while(j < listMark.length - 1) 
		{
			if (listMark[j].compareTo(listMark[j+1]) == 0)
			{
				if (listMark[j].compareTo("ENT") == 0)
				{
					builder.append(listMark[j]);
					builder.append(",");
					builder.append(listMarkLack[j]);
					builder.append(",");
					builder.append(listMark[j+1]);
					builder.append(",");
					builder.append(listMarkLack[j+1]);
				}
				else
				{
					builder.append(listMarkLack[j]);
					builder.append(",");
					builder.append(listMark[j]);
					builder.append(",");
					builder.append(listMarkLack[j+1]);
					builder.append(",");
					builder.append(listMark[j+1]);
				}
			}
			else
			{
				builder.append(listMark[j]);
				builder.append(",");
				builder.append(listMark[j+1]);
			}
			j+=2;
			builder.append(",");
		}

		//add last element
		if (listMark.length % 2 != 0)
		{
			if (listMark[listMark.length - 1].compareTo("ENT") == 0)
			{
				builder.append(listMark[listMark.length - 1]);
				builder.append(",");
				builder.append("SAL_LACK");				
			}
			else
			{
				builder.append("ENT_LACK");
				builder.append(",");
				builder.append(listMark[listMark.length - 1]);
			}
			builder.append(",");
		}

		String result = builder.toString();
		//System.out.println("Builder: " + result);

		//remove last comma
		return result.substring(0, result.length() - 1);
	}
	
	public static String getTransactionMarkHour(String tipo_marcaje, String hora_trans, int tolerancia) 
	{
		String[] listMark = tipo_marcaje.split(",");

		String[] listHour = hora_trans.split(",");
		String[] listHourLack = new String[listMark.length];

		if (listMark.length == 1)
		{
			StringBuffer builder = new StringBuffer();
			int tolerance = 0;
			if (listMark[0].compareTo("ENT") == 0)
			{
				tolerance = tolerancia; // if mark type is an input then you must plus tolerance value 
				String newHour = getNewHour(listHour[0],tolerance);
				
				builder.append(newHour + "_LACK");
				builder.append(",");
				builder.append(listHour[0]);
			}
			else
			{
				tolerance = -tolerancia; // if mark type is an output then you must subtract tolerance value
				String newHour = getNewHour(listHour[0],tolerance);
				
				builder.append(listHour[0]);
				builder.append(",");
				builder.append(newHour + "_LACK");
			}
			
			return builder.toString();
		}
		
		//initialize array
		for(int i = 0; i < listHourLack.length; i++)
		{
			listHourLack[i] = new String("");
		}

		int i = 0;
		while(i < listMark.length - 1)
		{
			if (listMark[i].compareTo(listMark[i+1]) == 0)
			{
				int tolerance = 0;
				if (listMark[i].compareTo("ENT") == 0)
				{
					tolerance = tolerancia; // if mark type is an input then you must plus tolerance value 
				}
				else
				{
					tolerance = -tolerancia; // if mark type is an output then you must subtract tolerance value 
				}
				String newHour = getNewHour(listHour[i],tolerance);
				listHourLack[i] = newHour + "_LACK";
				listHourLack[i+1] = newHour + "_LACK";
			}
			i+=2;
		}
		
		for(int k = 0; k < listHour.length; k++)
		{
			System.out.print(listHour[k] + ",");
		}
		System.out.println();

		
		for(int k = 0; k < listHourLack.length; k++)
		{
			System.out.print(listHourLack[k] + ",");
		}
		System.out.println();

		StringBuilder builder = new StringBuilder();
		int j = 0;
		while(j < listHourLack.length) 
		{
			String hourLack = listHourLack[j];
			if (hourLack.compareTo("") != 0)
			{
				if (hourLack.indexOf("ENT") != -1)
				{
					builder.append(hourLack);
					builder.append(",");
					builder.append(listHour[j].substring(0, listHour[j].indexOf("_")));
				}
				else
				{
					builder.append(listHour[j].substring(0, listHour[j].indexOf("_")));
					builder.append(",");
					builder.append(hourLack);
				}
			}
			else
			{
				builder.append(listHour[j].substring(0, listHour[j].indexOf("_")));
			}
			builder.append(",");
			j++;
		}
		
		//add last element
		if (listHour.length % 2 != 0)
		{
			if (listHour[listHour.length - 1].compareTo("ENT") == 0)
			{
				builder.append(listHour[listHour.length - 1]);
				builder.append(",");
				builder.append(listHourLack[listHourLack.length - 1]);			
			}
			else
			{
				builder.append(listHourLack[listHourLack.length - 1]);
				builder.append(",");
				builder.append(listHour[listHour.length - 1]);
			}
			builder.append(",");
		}
		
		String result = builder.toString();		
		
		return result.substring(0, result.length() - 1);
	}
	
	public static String getNewHour(String hora_trans, int tolerancia) 
	{
		boolean pad = false; 

		int minutes = 0;
		if (hora_trans.charAt(3) == '0')
		{
			minutes = hora_trans.charAt(4) - '0';
			pad = true;
		}
		else
		{
			minutes = Integer.parseInt(hora_trans.charAt(3) + "" + hora_trans.charAt(4));	
		}
		minutes += tolerancia;

		StringBuffer sb = new StringBuffer(hora_trans.substring(0, hora_trans.indexOf("_")));

		if (!pad)
		{
			sb.replace(3, 5, Integer.toString(minutes));
		}
		else
		{
			sb.replace(3, 5, "0" + Integer.toString(minutes));
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(getTransactionMarkType("ENT,SAL,ENT,SAL,SAL,SAL,SAL,SAL,ENT,SAL"));
		System.out.println();
		/*System.out.println(getTransactionMarkType("SAL,SAL"));
		System.out.println();
		System.out.println(getTransactionMarkType("ENT,ENT"));
		System.out.println();
		System.out.println(getTransactionMarkType("SAL,ENT,SAL"));
		System.out.println();
		System.out.println(getTransactionMarkType("SAL,SAL,SAL"));
		System.out.println();
		System.out.println(getTransactionMarkType("ENT,ENT,ENT"));
		System.out.println();
		System.out.println(getTransactionMarkType("SAL,ENT,ENT,SAL,ENT,ENT,SAL"));*/
		
		System.out.println(getTransactionMarkHour("ENT,SAL,ENT,SAL,SAL,SAL,SAL,SAL,ENT,SAL", 
				                                  "08:30_3048277,08:54_3048278,09:09_3048279,09:19_3048280,09:57_3048281,10:17_3048282,10:38_3048283,12:49_3048284,13:13_3048285,13:19_3048286", 0));

	}

}
