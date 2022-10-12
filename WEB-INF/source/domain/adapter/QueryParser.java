package domain.adapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QueryParser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader( "C:/Users/ANA ROCIO/Desktop/SIMONE/users.sql" ));
		String newLine = "";
		while(true)
		{
			String line = br.readLine();
			if (line == null)
			{
				break;
			}
			newLine = line.substring(0, line.lastIndexOf("(")+1);
			line = newLine + line.substring(line.indexOf("'")-1) + "\n";
			System.out.println(line);
		}
		br.close();

	}

}
