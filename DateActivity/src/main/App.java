package main;
/*
 * Write an application to tell the user about a day in the past 
 * (their birthday, the Battle of Normandy, the date of their first drink etc.). 
 * It should keep asking until the user enters an empty date. 
 * You can randomly generate the text about the weather and temperature that day, 
 * but you should know enough by now to accurately identify the day of the week 
 * and the number of days between that day and the current date.
 * 
 * Bonus (Die with the Lie) Have the application give consistent information about the weather and temperature. 
 * If the application is asked about a date that it has already been asked about that run, 
 * it should give the same answer.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


public class App
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		String dateStr = getDate(keyboard);
		
		// create an arraylist to store history
		ArrayList<WeatherCondition> weatherConditions = new ArrayList<WeatherCondition>();
		
		
		//as long as user input sth, continue the loop
		while (!dateStr.equals(""))
		{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			//try to parse the date
			try
			{
				Date date = sdf.parse(dateStr);
				
				Date now = new Date();
				
				String[] randomInfo = getRandomInfo();
				
				//set up single weatherCOndition object
				WeatherCondition weatherCondition = new WeatherCondition();
				
				weatherCondition.setCondition(randomInfo[0]);
				weatherCondition.setTemp(randomInfo[1]);
				weatherCondition.setDate(date.toString());
				
				boolean found = false;
				
				//find if the input date has already existed
				for(int i = 0; i < weatherConditions.size(); i++)
				{
					SimpleDateFormat sdf2 =new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					Date temp = sdf2.parse(weatherConditions.get(i).getDate());
					String tempStr = sdf.format(temp);
					
					//if existed, print the same weather condition, exit the loop
					if(tempStr.equalsIgnoreCase(dateStr))
					{
						weatherCondition = weatherConditions.get(i);
						System.out.println("That was a " + getFormattedDate("EEEE",date) +
								" It was a "+ weatherCondition.getCondition()+" day and the temperature averaged "+ weatherCondition.getTemp() +" degrees.\n It was " + DateDiff(date,now) +
								" days ago.");
						found = true;
						break;
					}					
				}
				
				//if not found, get a random weathercondition then add weathercondition to the arraylist
				if(!found)
				{
					System.out.println("That was a " + getFormattedDate("EEEE",date) +
							" It was a "+ randomInfo[0]+" day and the temperature averaged "+ randomInfo[1] +" degrees.\n It was " + DateDiff(date,now) +
							" days ago.");
					weatherConditions.add(weatherCondition);
				}
				
				
				
			}
			//print parse error
			catch(ParseException e)
			{
				System.out.println("Invalid format, try again, format: mm/dd/yyyy");
			}
			catch(NullPointerException e)
			{
				
				System.out.println("Invalid format, try again, format: mm/dd/yyyy");
				
			}
			catch(ClassCastException e)
			{
				System.out.println("Invalid format, try again, format: mm/dd/yyyy");
			}
			finally
			{
				System.out.println("finally block");
			}
			
			dateStr = getDate(keyboard);
		}
		
	}
	
	//getDate method to get input from user
	private static String getDate(Scanner keyboard)
	{
		System.out.println("What is the date that you are asking about? format: mm/dd/yyyy");
		
		String dateStr = keyboard.nextLine();
		
		//use regular expression to check the format of the date
		while(!dateStr.matches("[0-1][0-9]/[0-3][0-9]/\\d\\d\\d\\d"))
		{
			if(dateStr.equals(""))
			{
				break;
			}
			System.out.println("Invalid, please input again, format: mm/dd/yyyy");
			dateStr = keyboard.nextLine();
		}
		return dateStr;
	}
	
	// get formatteddate string with a specific pattern
	private static String getFormattedDate(String pattern, Date date)
	{
		String dateStr = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		
		dateStr = sdf.format(date);
		
		return dateStr;
	}
	
	//calculate the diff
	private static long DateDiff(Date date1, Date date2)
	{
		long diff = date2.getTime() - date1.getTime();
		long daydiff = diff/(24*60*60*1000);
		return daydiff;
	}
	
	//get random weather condition
	private static String[] getRandomInfo()
	{
		Random rd = new Random();
		String[] randomInfo = new String[2];
		randomInfo[0] = WeatherDB.getWeather(1 + rd.nextInt(4));
		randomInfo[1] = Integer.toString(32 + rd.nextInt(78));
		return randomInfo;
	}
}
