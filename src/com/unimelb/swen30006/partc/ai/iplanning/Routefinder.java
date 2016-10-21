package com.unimelb.swen30006.partc.ai.iplanning;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import com.unimelb.swen30006.partc.core.World;
import com.unimelb.swen30006.partc.roads.Intersection;
import com.unimelb.swen30006.partc.roads.Intersection.Direction;
import com.unimelb.swen30006.partc.roads.Road;

public class Routefinder {
	World world;
	Road destinationroad;
	Road currentroad;
	IntersectionNode start;
	IntersectionNode goal;
	HashMap<Intersection,IntersectionNode> intersectionNodes;
	private PriorityQueue<AstarNode> fringe = new PriorityQueue<AstarNode>();

	public Routefinder(){
		intersectionNodes=new HashMap<Intersection,IntersectionNode>();
	}
	public void processworld(World world){
		this.world=world;
		Intersection[] intersections=world.getIntersections();
		for(Intersection inter : intersections){
			intersectionNodes.put(inter,new IntersectionNode(inter));
		}
		for(IntersectionNode inter:intersectionNodes.values()){
			for(Road road:inter.getstarnode().roads.values()){
				Intersection intersection1=world.intersectionAtPoint(road.getStartPos());
				Intersection intersection2=world.intersectionAtPoint(road.getEndPos());
				if(intersection1!=null&&intersection1!=inter.getstarnode()){
					inter.addNeighber(intersectionNodes.get(intersection1));
				}else if(intersection2!=null&&intersection2!=inter.getstarnode()){
					inter.addNeighber(intersectionNodes.get(intersection2));
				}
			}
		}
		System.out.println(intersectionNodes.size());
	}
	public IntersectionNode findIntersectionNode(Intersection inter){
		return intersectionNodes.get(inter);
	}
	public boolean findroute(Point2D.Double destination){
	
		this.goal=findIntersectionNode(world.intersectionAtPoint(destination));
		this.start=findIntersectionNode(world.intersectionAtPoint(new Point2D.Double(165,135)));
		start.estimatedcost = start.getLocation().distance(goal.getLocation());
		start.visited = true;
		AstarNode startTuple = new AstarNode(start, null, 0, start.estimatedcost);
		fringe.offer(startTuple);
		
		while (!fringe.isEmpty()) {
			System.out.println("fringe not empty");
			AstarNode current = fringe.poll();
			
			if (!current.startNode.visited) {
				current.startNode.visited = true;
				current.startNode.fromnode = current.pathFrom;
				current.startNode.costtohere = current.costSoFar;
			}
			
			if (current.startNode == goal) { System.out.println("found answer"); break; }
			
			for (IntersectionNode neighbour:current.startNode.neighber) {
				
				if (!neighbour.visited) {
					double costToNeighbour = current.costSoFar + current.startNode.getLocation().distance(neighbour.getLocation());
					double estimatedTotal = costToNeighbour + neighbour.getLocation().distance(goal.getLocation());
					fringe.offer(new AstarNode(neighbour, current.startNode, costToNeighbour, estimatedTotal));
				}
			}
		}
		//return goal;
		
	return false;
	}
	public Stack<Road> getroute(Point2D.Double start,Point2D.Double destination){
		Stack<Road> route=new Stack<Road>();
		Road endroad=world.roadAtPoint(destination);
		route.push(endroad);
		while(goal.fromnode!=null){
			Point2D.Double oneside=goal.getstarnode().pos;
			Point2D.Double otherside=goal.getfromnode().getstarnode().pos;
			Point2D.Double middle=new Point2D.Double((oneside.getX()+otherside.getX())/2,(oneside.getY()+otherside.getY())/2);
			Road road=world.roadAtPoint(middle);
			route.push(road);
			goal=goal.fromnode;
		}
		Road startroad=world.roadAtPoint(start);
		route.push(startroad);
		return route;
	}

	
}
