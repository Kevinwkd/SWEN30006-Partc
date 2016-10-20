package com.unimelb.swen30006.partc.HandleResponse;

import com.unimelb.swen30006.partc.core.objects.Car;

public class TrafficlightHandler {
	
	private Car car;
	
	public TrafficlightHandler(Car car){
		this.car = car;
	}
	
	public void trafficlightResponse(){
		car.brake();
	}

}
