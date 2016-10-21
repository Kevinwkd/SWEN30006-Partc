package com.unimelb.swen30006.partc.HandleResponse;

import java.awt.geom.Point2D;

import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse;
import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.roads.Road;

public class ObstacleHandler {
	
	private Car car;
	
	public ObstacleHandler(Car car){
		this.car = car;
	}
	
	public String obstacleResponse(PerceptionResponse result, World world){
		String type = TurnDirection(result,world);
		if(type == "left"){
			car.turn(45);
			return type;
		}else{
			car.turn(-45);
			return type;
		}
		
	}
	
	private String TurnDirection(PerceptionResponse result, World world){
		Road curroad = world.roadAtPoint(car.getPosition());
		Point2D.Double startpos = curroad.getStartPos();
		Point2D.Double endpos = curroad.getEndPos();
		float width = curroad.getWidth();
		
		if(startpos.x == endpos.x){
			if(Math.abs(result.position.x - (startpos.x + width/2)) > 
			Math.abs(result.position.x - (startpos.x - width/2))){
				return "left";
			}else{
				return "right";
			}
		}else if(startpos.y == endpos.y){
			if(Math.abs(result.position.y - (startpos.y + width/2)) > 
			Math.abs(result.position.y - (startpos.y - width/2))){
				return "left";
			}else{
				return "right";
			}
		}
		return null;
	}

}
