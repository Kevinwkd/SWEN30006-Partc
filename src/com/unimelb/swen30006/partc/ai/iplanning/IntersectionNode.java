package com.unimelb.swen30006.partc.ai.iplanning;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.unimelb.swen30006.partc.roads.Intersection;

public class IntersectionNode {
	Intersection startnode;
	IntersectionNode fromnode;
	boolean goal;
	boolean visited;
	double costtohere;
	double estimatedcost;
	ArrayList<IntersectionNode> neighber;
	public IntersectionNode(Intersection intersection){
		this.startnode=intersection;
		this.visited=false;
		neighber=new ArrayList<IntersectionNode>();
		this.goal=false;
	}
	public Intersection getstarnode(){
		return startnode;
	}
	public IntersectionNode getfromnode(){
		return fromnode;
	}
	public void setFromNode(IntersectionNode from){
		this.fromnode=from;
	}
	public void addNeighber(IntersectionNode inter){
		neighber.add(inter);
	}
	public Point2D.Double getLocation(){
		return startnode.pos;
	}
	public void setgoal(){
		this.goal=true;
	}
}
