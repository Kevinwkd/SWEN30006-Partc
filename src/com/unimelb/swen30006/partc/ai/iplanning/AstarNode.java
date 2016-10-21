package com.unimelb.swen30006.partc.ai.iplanning;

public class AstarNode implements Comparable<AstarNode>{
	
		public IntersectionNode startNode = null;
		public IntersectionNode pathFrom = null;
		public double costSoFar = 0;
		public double totalCost = 0;
		
		public AstarNode(IntersectionNode start, IntersectionNode from, double costSoFar, double totalCost) {
			this.startNode = start;
			this.pathFrom = from;
			this.costSoFar = costSoFar;
			this.totalCost = totalCost;
		}

		public int compareTo(AstarNode o) {
			if (this.totalCost < o.totalCost) { return -1; }
			else if (this.totalCost > o.totalCost) { return 1; }
			else { return 0; }
		}

	
}
