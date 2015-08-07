package main;

public class WeatherDB
{
	public static String getWeather(int code)
	{
		String weather = "";
		switch (code)
		{
			case 1: weather = "sunny";
				break;
			case 2: weather = "rainy";
				break;
			case 3: weather = "cloudy";
				break;
			case 4: weather = "stormy";
				break;
			default: weather = "normal";
		}
		
		return weather;
	}
}
