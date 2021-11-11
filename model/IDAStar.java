package model;
public class IDAStar {

    public static final int GODS_NUMBER = 20;

    private static final long cornerGoal =
	0b1110011000101001000001100010000010000000L;	

    private static final long edgeGoal =
	0b101101010010010100000111001100010100100000110001000001000000L;
	
    private static String[] rotationTypes =
    {"orange90CW", "orange90CCW", "orange180", "green90CW", "green90CCW",
     "green180", "red90CW", "red90CCW", "red180", "blue90CW", "blue90CCW",
     "blue180", "white90CW", "white90CCW", "white180", "yellow90CW",
     "yellow90CCW", "yellow180"};
	
    private static long numNodes;
	
    public static boolean findSolution(long cornerCube, long edgeCube, 
						MyHashMap heuristic, CubeTracer tracer) {
		int costLimit = (int)heuristic.get(cornerCube);
		for(int i = 0; i < GODS_NUMBER; i++) {
			System.out.println("Search depth: " + costLimit);
			numNodes = 0;
			costLimit =
			findSolutionHelper(cornerCube, edgeCube, costLimit, 0, tracer,
					heuristic);
			
			System.out.println("Number of nodes expanded: " + numNodes);
			if(costLimit < 0) {
			return true; 
			}
		}
		return false; 
    }
	
    private static int findSolutionHelper(long cornerCube, long edgeCube,
					  int costLimit, int startCost,
					  CubeTracer tracer,
					  MyHashMap heuristic) {
	
		numNodes++;
		if (cornerCube == cornerGoal && edgeCube == edgeGoal)
			return -1;
		
		int minCost = startCost + (int)heuristic.get(cornerCube);
		
		if (minCost > costLimit)
			return minCost;
		
		int nextCostLimit = GODS_NUMBER;
		
		for (String type : rotationTypes) {
		
		long nextCornerCube = CornerCubeMoves.rotate(cornerCube, type);
	
		long nextEdgeCube = EdgeCubeMoves.rotate(edgeCube, type);
	
		tracer.writeMove(type);
	
		int newCostLimit = findSolutionHelper(nextCornerCube, nextEdgeCube,
						costLimit, (startCost + 1),
						tracer, heuristic);
		
		if(newCostLimit < 0)
			return -1;
	
		nextCostLimit = Math.min(nextCostLimit, newCostLimit);
			tracer.eraseMove();
		}
		
		return nextCostLimit; 
    }
}
