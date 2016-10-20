package com.unimelb.swen30006.partc.HandleResponse;

import java.awt.geom.Point2D;

import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse;
import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.core.objects.WorldObject;
import com.unimelb.swen30006.partc.roads.Road;

public class HandleResponse {
	
	public Car car;
	public TrafficlightHandler trafficlighthandler;
	public ObstacleHandler obstaclehandler;
	public RegularHandler regularhandler;
	
	public WorldObject avoidObject;
	public String type = "straight";
	public int avoidTime = 0;
	
	
	public HandleResponse(Car car){
		this.car =car;
		this.trafficlighthandler = new TrafficlightHandler(car);
		this.obstaclehandler = new ObstacleHandler(car);
		this.regularhandler = new RegularHandler(car);
	}
	
	public void update(PerceptionResponse results[],float detla){
		if(results.length == 0){
			if(type != "straight"){
				int res = regularhandler.adjustDirection(type,avoidTime);
				if(res == 1){ 
					type = "straight";
				}else{ avoidTime --;}
			}
		}
			
	}
	
	/*public void RoadResponse(World world, Road road){
		Point2D.Double carPosition = car.getPosition();
		Road curRoad = world.roadAtPoint(carPosition);
		
		if(curRoad == road){ //the car not on the right lane
			if(car.getVelocity().angle() != 0){
				car.accelerate();
			}else{
				car.turn(45);
			}
		}
		Point2D.Double startpos = road.getStartPos();
		Point2D.Double endpos = road.getEndPos();
		Point2D.Double roadpos = (startpos.distance(carPosition) > endpos.distance(carPosition)) 
				? startpos : endpos;
		
		if(Math.abs(car.getPosition().x - startpos.x ) == 5 || 
				Math.abs(startpos.y - car.getPosition().y) == 5){
			car.accelerate();
		}else if(Math.abs(roadpos.x - carPosition.x) < 15 || 
				Math.abs(roadpos.y - carPosition.y) < 15 ){
			//car.turn(45);
			car.turn(90);
		}else if(Math.abs(roadpos.x - carPosition.x) > 15 || 
				Math.abs(roadpos.y - carPosition.y) > 15 ){
			//car.turn(-45);
			car.turn(-90);
		}
		
	}
	
	public void ObstacleResponse(){
		if(car.getVelocity().angle() != 0){
			car.accelerate();
		}else{
			car.turn(-45);
		}
	}
	
	public void RegularResponse(Point2D.Double des){
		if(!HasArrived(des)){
			if(car.getVelocity().angle() != 0){ 
				car.turn(-45);
			}else{
				car.accelerate();
			}
			
		}else{
			car.brake();
		}
	}
	
	private boolean HasArrived(Point2D.Double des){
		if(car.getPosition().x == des.x || car.getPosition().y == des.y){
			return true;
		}else{
			return false;
		}
	}*/
	
	
	
}
