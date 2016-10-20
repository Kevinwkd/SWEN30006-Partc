package com.unimelb.swen30006.partc.HandleResponse;

import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.roads.Road;

public class RegularHandler {
	
	private Car car;
	public String type;
	public World world;
	public Road nextroad;
	
	public RegularHandler(Car car){
		this.car = car;
	}

	public int adjustDirection(String type, int avoidTime){
		if(world.intersectionAtPoint(car.getPosition()) == null){
			if(avoidTime == 0){
				if(type == "left"){
					car.turn(-45);
				}else if(type == "right"){
					car.turn(45);
				}
				return 1;
			}else if(avoidTime != 0){
				car.accelerate();
				return 2;
			}
		}else{
			if(isCarTurnintoRoad(nextroad)){
				if(type == "left"){
					car.turn(-45);
				}else if(type == "right"){
					car.turn(45);
				}
				return 1;
			}else{
				car.accelerate();
				return 3;
			}

		}

	}
	
	private boolean isCarTrunintoRoad(Road road){
		if(((road.getStartPos().x + (road.getWidth() / 2)) == car.getPosition().x)
				|| ((road.getStartPos().x + (road.getWidth() / 2)) == car.getPosition().x))
	}
}
