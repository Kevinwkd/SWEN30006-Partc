package com.unimelb.swen30006.partc.ai.iplanning;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.unimelb.swen30006.partc.ai.interfaces.IPlanning;
import com.unimelb.swen30006.partc.ai.interfaces.PerceptionResponse;
import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.core.objects.Car;
import com.unimelb.swen30006.partc.roads.Road;
import com.badlogic.gdx.math.Vector2;
public class AIplanning implements IPlanning {
	public Routefinder routefinder;
	public World world;
	public Point2D.Double destination;
	public Car car;
	public boolean arrived;
	public Stack<Road> route;
	public AIplanning(Car car,World world) {
		routefinder=new Routefinder();
		this.car=car;
		this.arrived=false;
		this.world=world;
	}
	
	@Override
	public boolean planRoute(Point2D.Double destination) {
		
		// TODO Auto-generated method stub
		this.destination=destination;
		routefinder.processworld(world);
		routefinder.findroute(destination);
		route=routefinder.getroute(car.getPosition(), destination);
		
		return false;
	}

	@Override
	public void update(PerceptionResponse[] results, int visibility, float delta) {
		// TODO Auto-generated method stub
		
		final float ROTATION_RATE = 150f;

	        // Update the car
	        this.car.update(delta);
		
	}
	public boolean getturn(){
		Vector2 carposition=new Vector2((int)car.getPosition().x,(int)car.getPosition().y);
		Vector2 desposition=new Vector2((int)destination.x,(int)destination.y);
		Vector2 need=desposition.sub(carposition);
		double angle=need.angle()-car.getVelocity().angle();
		if(angle>180)return true;
		else return false;
	}
	public boolean turn(){
		System.out.println("ss");
		Vector2 carposition=new Vector2((int)car.getPosition().x,(int)car.getPosition().y);
		Vector2 desposition=new Vector2((int)destination.x,(int)destination.y);
		Vector2 need=desposition.sub(carposition);
		double angle=need.angle()-car.getVelocity().angle();
		if(angle<2)return false;
		else return true;
	}
	@Override
	public float eta() {
		// TODO Auto-generated method stub
		return 0;
	}

}
