import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.util.Scanner;


public class OwmJapisExample1 {

    public static void main(String[] args)
            throws APIException {
        Scanner s = new Scanner(System.in);
        OWM owm = new OWM("16fefbf1a9b67bfc7bd3cd80b7061b6d");
        System.out.println("Hi, how can Marebot help you today?");
        String city = "";
        CurrentWeather cwd = new CurrentWeather();
        while(s.hasNextLine()) {
            String response = s.nextLine();
            if(response.toLowerCase().contains("weather") || response.toLowerCase().contains("temperature")) {
                System.out.println("Okay, sure. Please input a city where you want to check the temperature.");
                city = s.nextLine();
                // getting current weather data for the "London" city
                cwd = owm.currentWeatherByCityName(city);
                //printing city name from the retrieved data
                System.out.println("City: " + cwd.getCityName());
                // printing the max./min. temperature
                System.out.println("In "+city +", it is currently " + Math.round(((cwd.getMainData().getTemp()-273.15)*(9/5)+32))+" degrees Fahrenheit.");
                System.out.println("Would you like me to help you with anything else? I can also tell you humidity, air pressure, snowfall, and more.");
            }
            if(response.toLowerCase().contains("humid") || response.toLowerCase().contains("humidity")) {
                System.out.println("Okay, do you want to check the humidity in the same city you previously entered, or a new city?");
                String in = s.nextLine().toLowerCase();
                if (in.contains("new") || in.contains("different")) {
                    System.out.println("Okay, sure. Please input a city where you want to check the humidity.");
                    city = s.nextLine();
                    cwd = owm.currentWeatherByCityName(city);
                    System.out.println("In " + city+", the humidity is currently " + Math.round(cwd.getMainData().getHumidity())+"%.");
                } else {
                    System.out.println("In "+city+", the humidity is currently "+ Math.round(cwd.getMainData().getHumidity())+"%.");
                }
                System.out.println("Would you like me to help you with anything else? I can also tell you temperature, air pressure, snowfall, and more.");
            }
            if(response.toLowerCase().contains("chat") || response.toLowerCase().contains("talk")) {
                System.out.println("Okay, what do you want to talk about?");
            }
            }
        }
    }
