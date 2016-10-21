package com.unimelb.swen30006.partc.HandleResponse;

import java.awt.geom.Point2D;

import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse;
import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse.Classification;
import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.core.objects.WorldObject;
import com.unimelb.swen30006.partc.roads.Road;

public class HandleResponse {
	
	public Car car;
	public TrafficlightHandler trafficlighthandler;
	public ObstacleHandler obstaclehandler;
	public RegularHandler regularhandler;
	
	public PerceptionResponse avoidObject;
	public String type = "straight";
	public int avoidTime = 0;
	public Road nextroad;
	
	
	public HandleResponse(Car car){
		this.car =car;
		this.trafficlighthandler = new TrafficlightHandler(car);
		this.obstaclehandler = new ObstacleHandler(car);
		this.regularhandler = new RegularHandler(car,nextroad);
	}
	
	public void update(PerceptionResponse results[],float detla, World world){
		if(results.length == 0){
			if(type != "straight"){
				int res = regularhandler.adjustDirection(type,avoidTime,world);
				if(res == 1){ 
					type = "straight";
				}else if(res == 2){ avoidTime --;}
			}
			if(avoidObject != null){
				String temp;
				if((temp = regularhandler.isFinishAvoiding(avoidObject) )!= "straight"){
					type = temp;
					avoidObject = null;
					avoidTime = 5;
				}
			}
		
			if((type = regularhandler.TurnNextRoad(world)) != "straight"){
				avoidTime = 5;
			}
		}else if(results[0].objectType == Classification.TrafficLight){
			trafficlighthandler.trafficlightResponse();
		}else{
			avoidObject = results[0];
			type = obstaclehandler.obstacleResponse(results[0], world);
			avoidTime = 5;
		}
		
		
			
	}
	

	
	
	
}
