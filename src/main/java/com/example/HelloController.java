package com.example;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.Weather;

@RestController
@RequestMapping(path = "/rest")
public class HelloController {

	@RequestMapping("/greeting")
	public String index() {
		return "Greeting from Mandar";
	}
	@RequestMapping(value="/getWeather/{name}")
	public Weather getWeather(@PathVariable String name)
	{
		System.out.println("Name"+name +"Conditon"+"Mumbai".equalsIgnoreCase(name));
		
		Weather weather=new Weather();
		if("Mumbai".equals(name))
		{
			weather.setLat(100);
			weather.setLongi(300);
			weather.setUnit('c');
			weather.setTemp(20);
			
			
		}
		else
		{
			weather.setLat(10);
			weather.setLongi(30);
			weather.setUnit('f');
			weather.setTemp(20);
		}
		return weather;
		
	}
	


}
