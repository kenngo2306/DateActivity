package test;
import main.WeatherDB;
import org.junit.* ;

import static org.junit.Assert.* ;
public class WeatherDBTest
{
	@Test
	public void test_getWeather()
	{
		System.out.println("Test if getWeather returns true condition...");
		WeatherDB weatherDB = new WeatherDB();
		assertTrue(weatherDB.getWeather(1).equals("sunny"));
		assertTrue(weatherDB.getWeather(2).equals("rainy"));
		assertTrue(weatherDB.getWeather(3).equals("cloudy"));
		assertTrue(weatherDB.getWeather(4).equals("stormy"));
		assertTrue(weatherDB.getWeather(5).equals("normal"));
	}

}
