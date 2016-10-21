package com.unimelb.swen30006.partc.HandleResponse;

import java.awt.geom.Point2D;

import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse;
import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.core.objects.WorldObject;
import com.unimelb.swen30006.partc.roads.Road;

public class RegularHandler {
		
	private Car car;
//	public String type;
	public Road road;
	public Point2D.Double startpoint;
	
	
	public RegularHandler(Car car,Road nextroad){
		this.car = car;
		startpoint = null;
		this.road = nextroad;
	}

	public int adjustDirection(String type, int avoidTime,World world){
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
		}

		if(isCarTurnintoRoad(type)){
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
	
	private boolean isCarTurnintoRoad(String type){
		Point2D.Double carpos = car.getPosition();
		Point2D.Double startpos = road.getStartPos();
		Point2D.Double endpos = road.getEndPos();
		
		double startdis = road.getStartPos().distance(startpoint);
		double enddis = road.getEndPos().distance(startpoint);
		Point2D.Double roadpos = (startdis > enddis)? startpos:endpos; 
		
		if((carpos.x > startpoint.x && carpos.x < roadpos.x)||
				(carpos.x > roadpos.x && carpos.x < startpoint.x)
				||(carpos.y > startpoint.y && carpos.x < roadpos.y)||
				(carpos.y > roadpos.y && carpos.x < startpoint.y)){
			if((carpos.x == roadpos.x + 7.5)||(carpos.x == roadpos.x - 7.5)
				||(carpos.y == roadpos.y + 7.5)||(carpos.y == roadpos.y - 7.5)){
				if(type == "left"){
					return true;
				}
			}
		}else if((carpos.x == roadpos.x + 7.5)||(carpos.x == roadpos.x - 7.5)
				||(carpos.y == roadpos.y + 7.5)||(carpos.y == roadpos.y - 7.5)){
				if(type == "right"){
					return true;
				}
		}

		return false;
	}


	public String isFinishAvoiding(PerceptionResponse avoidObject){
		if((Math.abs(car.getPosition().x - avoidObject.position.x) > (car.getLength()/2 + avoidObject.length/2))
			||(Math.abs(car.getPosition().y - avoidObject.position.y) > (car.getLength()/2 + avoidObject.length/2))){
			car.turn(45);
			return "right";
		}else{
			car.accelerate();
		}
		
		return "straight";
		
	}
		
	public String TurnNextRoad(World world){
		Point2D.Double carposition = car.getPosition();
		if(!whetherTurn(carposition,world)){
			return "straight";
		}
		
		this.startpoint = car.getPosition();
		
		Point2D.Double carpos = car.getPosition();
		Point2D.Double startpos = road.getStartPos();
		Point2D.Double endpos = road.getEndPos();
		
		double startdis = road.getStartPos().distance(startpoint);
		double enddis = road.getEndPos().distance(startpoint);
		Point2D.Double roadpos = (startdis > enddis)? startpos:endpos; 
		
		if(Math.abs(carpos.x - startpos.x ) == 5 || 
				Math.abs(startpos.y - carpos.y) == 5){
			car.accelerate();
			return "straight";
		}else if(Math.abs(roadpos.x - carpos.x) < 15 || 
				Math.abs(roadpos.y - carpos.y) < 15 ){
			car.turn(45);
			//car.turn(90);
			return "left";
		}else if(Math.abs(roadpos.x - carpos.x) > 15 || 
				Math.abs(roadpos.y - carpos.y) > 15 ){
			car.turn(-45);
			//car.turn(-90);
			return "right";
		}
		return "straight";
		
	}
	
	private boolean whetherTurn(Point2D.Double carposition, World world){
		if(world.intersectionAtPoint(carposition) != null){
			return true;
		}else{
			return false;
		}
	}
		
	
}
