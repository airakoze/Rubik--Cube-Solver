package model;

public class EdgeCubeMoves {
    private static long temp;
    private static final int numBits = 5;
    private static final long flip = 0b1L;
	
    private static final long corner0Mask = 0b11111L;
    private static final long corner1Mask = corner0Mask << numBits;
    private static final long corner2Mask = corner1Mask << numBits;
    private static final long corner3Mask = corner2Mask << numBits;
    private static final long corner4Mask = corner3Mask << numBits;
    private static final long corner5Mask = corner4Mask << numBits;
    private static final long corner6Mask = corner5Mask << numBits;
    private static final long corner7Mask = corner6Mask << numBits;
    private static final long corner8Mask = corner7Mask << numBits;
    private static final long corner9Mask = corner8Mask << numBits;
    private static final long corner10Mask = corner9Mask << numBits;
    private static final long corner11Mask = corner10Mask << numBits;

    private static final long clearWhite = corner4Mask | corner5Mask | corner6Mask |
	corner7Mask | corner8Mask | corner9Mask | corner10Mask | corner11Mask;
    private static final long clearBlue = corner0Mask | corner2Mask | corner3Mask |
	corner6Mask | corner7Mask | corner9Mask | corner10Mask | corner11Mask;
    private static final long clearOrange = corner0Mask | corner1Mask | corner2Mask |
	corner4Mask | corner7Mask | corner8Mask | corner10Mask | corner11Mask;
    private static final long clearGreen = corner0Mask | corner1Mask | corner3Mask |
	corner4Mask | corner5Mask | corner8Mask | corner9Mask | corner11Mask;
    private static final long clearRed = corner1Mask | corner2Mask | corner3Mask |
	corner5Mask | corner6Mask | corner8Mask | corner9Mask | corner10Mask;
    private static final long clearYellow = corner0Mask | corner1Mask | corner2Mask |
	corner3Mask | corner4Mask | corner5Mask | corner6Mask | corner7Mask;
	
    public static void printEdgeCube(long cube) {
		System.out.print("E0: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E1: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E2: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E3: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E4: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E5: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;
		System.out.print("; E6: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;		
		System.out.print("; E7: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;		
		System.out.print("; E8: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;		
		System.out.print("; E9: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;		
		System.out.print("; E10: ");
		System.out.print(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
		cube = cube >> numBits;		
		System.out.print("; E11: ");
		System.out.println(((cube & 0b11110) >> 1) + ", " + (cube & 0b1));
    }
	
    public static long rotate(long cube, String rotationType) {
		if(rotationType.equals("orange90CW"))
			return rotateOrange90CW(cube);
		else if(rotationType.equals("orange90CCW"))
			return rotateOrange90CCW(cube);
		else if(rotationType.equals("orange180"))
			return rotateOrange180(cube);
		else if(rotationType.equals("red90CW"))
			return rotateRed90CW(cube);
		else if(rotationType.equals("red90CCW"))
			return rotateRed90CCW(cube);
		else if(rotationType.equals("red180"))
			return rotateRed180(cube);
		else if(rotationType.equals("green90CW"))
			return rotateGreen90CW(cube);
		else if(rotationType.equals("green90CCW"))
			return rotateGreen90CCW(cube);
		else if(rotationType.equals("green180"))
			return rotateGreen180(cube);
		else if(rotationType.equals("white90CW"))
			return rotateWhite90CW(cube);
		else if(rotationType.equals("white90CCW"))
			return rotateWhite90CCW(cube);
		else if(rotationType.equals("white180"))
			return rotateWhite180(cube);
		else if(rotationType.equals("blue90CW"))
			return rotateBlue90CW(cube);
		else if(rotationType.equals("blue90CCW"))
			return rotateBlue90CCW(cube);
		else if(rotationType.equals("blue180"))
			return rotateBlue180(cube);
		else if(rotationType.equals("yellow90CW"))
			return rotateYellow90CW(cube);
		else if(rotationType.equals("yellow90CCW"))
			return rotateYellow90CCW(cube);
		else if(rotationType.equals("yellow180"))
			return rotateYellow180(cube);
		else
			throw new IllegalArgumentException("Invalid rotation type");
    }
	
    public static long rotateWhite90CW(long cube) {
		temp = cube;
		cube = cube & clearWhite;
		cube = cube | ((corner1Mask & temp) >> numBits);  
		cube = cube | ((corner3Mask & temp) >> 2*numBits);
		cube = cube | ((corner0Mask & temp) << 2*numBits);
		cube = cube | ((corner2Mask & temp) << numBits);  
		return cube;
    }
	
    public static long rotateWhite90CCW(long cube) {
		temp = cube;
		cube = cube & clearWhite;
		cube = cube | ((corner2Mask & temp) >> 2*numBits);
		cube = cube | ((corner0Mask & temp) << numBits);
		cube = cube | ((corner3Mask & temp) >> numBits);  
		cube = cube | ((corner1Mask & temp) << 2*numBits);
		return cube;
		}
	
    public static long rotateWhite180(long cube) {
		return rotateWhite90CW(rotateWhite90CW(cube));
    }
	
    public static long rotateBlue90CW(long cube) {
		temp = cube;
		cube = cube & clearBlue;
		cube = cube | (((corner4Mask & temp) >> 4*numBits) ^ flip) << numBits; 
		cube = cube | ((corner8Mask & temp) >> 4*numBits);                     
		cube = cube | (((corner1Mask & temp) >> numBits) ^ flip) << 5*numBits; 
		cube = cube | ((corner5Mask & temp) << 3*numBits);                     
		return cube;
    }
	
    public static long rotateBlue90CCW(long cube) {
		temp = cube;
		cube = cube & clearBlue;
		cube = cube | (((corner5Mask & temp) >> 5*numBits) ^ flip) << numBits; 
		cube = cube | (((corner1Mask & temp) >> numBits) ^ flip) << 4*numBits; 
		cube = cube | ((corner8Mask & temp) >> 3*numBits); 
		cube = cube | ((corner4Mask & temp) << 4*numBits); 
		return cube;
    }
	
    public static long rotateBlue180(long cube) {
		return rotateBlue90CW(rotateBlue90CW(cube));
    }
    
    public static long rotateOrange90CW(long cube) {
		temp = cube;
		cube = cube & clearOrange;
		cube = cube | ((corner5Mask & temp) >> 2*numBits); 
		cube = cube | (((corner9Mask & temp) >> 9*numBits) ^ flip) << 5*numBits; 
		cube = cube | (((corner3Mask & temp) >> 3*numBits) ^ flip) << 6*numBits; 
		cube = cube | ((corner6Mask & temp) << 3*numBits); 
		return cube;
    }
	
    public static long rotateOrange90CCW(long cube) {
		temp = cube;
		cube = cube & clearOrange;
		cube = cube | (((corner6Mask & temp) >> 6*numBits) ^ flip) << 3*numBits; 
		cube = cube | ((corner3Mask & temp) << 2*numBits); 
		cube = cube | ((corner9Mask & temp) >> 3*numBits); 
		cube = cube | (((corner5Mask & temp) >> 5*numBits) ^ flip) << 9*numBits; 
		return cube;
    }
	
    public static long rotateOrange180(long cube) {
		return rotateOrange90CW(rotateOrange90CW(cube));
    }
	
    public static long rotateGreen90CW(long cube) {
		temp = cube;
		cube = cube & clearGreen;
		cube = cube | ((corner6Mask & temp) >> 4*numBits); 
		cube = cube | (((corner10Mask & temp) >> 10*numBits) ^ flip) << 6*numBits; 
		cube = cube | (((corner2Mask & temp) >> 2*numBits) ^ flip) << 7*numBits; 
		cube = cube | ((corner7Mask & temp) << 3*numBits); 
		return cube;
    }
	
    public static long rotateGreen90CCW(long cube) {
		temp = cube;
		cube = cube & clearGreen;
		cube = cube | (((corner7Mask & temp) >> 7*numBits) ^ flip) << 2*numBits; 
		cube = cube | ((corner2Mask & temp) << 4*numBits); 
		cube = cube | ((corner10Mask & temp) >> 3*numBits); 
		cube = cube | (((corner6Mask & temp) >> 6*numBits) ^ flip) << 10*numBits; 
		return cube;
    }
	
    public static long rotateGreen180(long cube) {
		return rotateGreen90CW(rotateGreen90CW(cube));
    }
	
    public static long rotateRed90CW(long cube) {
		temp = cube;
		cube = cube & clearRed;
		cube = cube | ((corner7Mask & temp) >> 7*numBits); 
		cube = cube | ((corner0Mask & temp) << 4*numBits); 
		cube = cube | (((corner11Mask & temp) >> 11*numBits) ^ flip) << 7*numBits;
		cube = cube | (((corner4Mask & temp) >> 4*numBits) ^ flip) << 11*numBits; 
		return cube;
    }
	
    public static long rotateRed90CCW(long cube) {
		temp = cube;
		cube = cube & clearRed;
		cube = cube | ((corner4Mask & temp) >> 4*numBits); 
		cube = cube | (((corner11Mask & temp) >> 11*numBits) ^ flip) << 4*numBits; 
		cube = cube | ((corner0Mask & temp) << 7*numBits); 
		cube = cube | (((corner7Mask & temp) >> 7*numBits) ^ flip) << 11*numBits; 
		return cube;
    }
	
    public static long rotateRed180(long cube) {
		return rotateRed90CW(rotateRed90CW(cube));
    }
   
    public static long rotateYellow90CW(long cube) {
		temp = cube;
		cube = cube & clearYellow;
		cube = cube | ((corner11Mask & temp) >> 3*numBits);
		cube = cube | ((corner8Mask & temp) << numBits);
		cube = cube | ((corner9Mask & temp) << numBits);
		cube = cube | ((corner10Mask & temp) << numBits);
		return cube;
    }
	
    public static long rotateYellow90CCW(long cube) {
		temp = cube;
		cube = cube & clearYellow;
		cube = cube | ((corner9Mask & temp) >> numBits); 
		cube = cube | ((corner10Mask & temp) >> numBits);
		cube = cube | ((corner11Mask & temp) >> numBits);
		cube = cube | ((corner8Mask & temp) << 3*numBits);
		return cube;
    }
	
    public static long rotateYellow180(long cube) {
		return rotateYellow90CW(rotateYellow90CW(cube));
    }
}
