package model;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException; 

public class CubeEntry {
    private char[] cube = new char[54];
    private long cornerCube;
    private long edgeCube;
	
    // mapping of cubie positions to array positions
    private int[] orangeFace = {12, 13, 14, 24, 25, 26, 36, 37, 38};
    private int[] greenFace  = {15, 16, 17, 27, 28, 29, 39, 40, 41};
    private int[] redFace    = {18, 19, 20, 30, 31, 32, 42, 43, 44};
    private int[] blueFace   = {9, 10, 11, 21, 22, 23, 33, 34, 35};
    private int[] whiteFace  = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private int[] yellowFace = {45, 46, 47, 48, 49, 50, 51, 52, 53};

    private int[] getCornerColors = {12, 11, 6, 14, 8, 15, 36, 45, 35, 38,
				     39, 47, 18, 17, 2, 20, 0, 9, 42, 53,
				     41, 44, 33, 51};
	

    private int[] getEdgeColors = {1, 19, 3, 10, 5, 16, 7, 13, 21, 32, 23,
				   24, 26, 27, 29, 30, 34, 48, 37, 46, 40,
				   50, 43, 52};

    public CubeEntry() {
    }
	
	private static List<String> getRecordsFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try(Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()){
				values.add(rowScanner.next());
			}
		}
		return values;
	}

    public void enterCubeState(String name) throws FileNotFoundException{
		List<List<String>> records = new ArrayList<>();
		try(Scanner scanner = new Scanner(new File(name));){
			while(scanner.hasNextLine()){
				records.add(getRecordsFromLine(scanner.nextLine()));
			}
		}
	
		for(int k = 0; k < records.size(); k++){
			for(int i = 0; i < records.get(k).size(); i ++){
				if(k == 0){
					cube[orangeFace[i]] = records.get(k).get(i).charAt(0);
				}
				if(k == 1){
					cube[greenFace[i]] = records.get(k).get(i).charAt(0);
				}
				if(k == 2){
					cube[redFace[i]] = records.get(k).get(i).charAt(0);
				}
				if(k == 3){
					cube[blueFace[i]] = records.get(k).get(i).charAt(0);
				}
				if(k == 4){
					cube[whiteFace[i]] = records.get(k).get(i).charAt(0);
				}
				if(k == 5){
					cube[yellowFace[i]] = records.get(k).get(i).charAt(0);
				}
			}
		}

		cube[25] = 'O';
		cube[28] = 'G';
		cube[31] = 'R';
		cube[22] = 'B';
		cube[4]  = 'W';
		cube[49] = 'Y';
    }
	
    /*
	private String cubeToString() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < cube.length; i++) {
			result.append(cube[i]);
		}
		return result.toString();
    }
	*/

    public boolean isCubeOK() {
		return true;
    }

    public boolean solveAnotherCube() {
		return false;
    }

    public void createCompactCube() {
		computeCornerCube();
		computeEdgeCube();
    }

    public long getCornerCube() {
		return this.cornerCube;
    }

    public long getEdgeCube() {
		return this.edgeCube;
    }
	
    private void computeCornerCube() {
		cornerCube = 0L;
		char[] colors = new char[3];
		for(int i = 0; i < 8; i++) {
			// get the three colors for the cubie in corner position i
			for(int j = 0; j < 3; j++)
			colors[j] = cube[getCornerColors[3*i + j]];
			cornerCube = cornerCube | (cornerIDandOrientation(colors) << 5*i);
		}
		// for reference:
		// 0: 12/11/6  (corner #: cubie faces)
		// 1: 14/8/15
		// 2: 36/45/35
		// 3: 38/49/47
		// 4: 18/17/2
		// 5: 20/0/9
		// 6: 42/53/41
		// 7: 44/33/51
    }
	

    private long cornerIDandOrientation(char[] colors) {
		if(colors.length != 3)
			throw new IllegalArgumentException("Invalid input array length (corner cube)");
		// The corner cubie encodings are:
		// 	Orange/Blue/White:   0
		//     	Orange/White/Green:  1
		//      Orange/Yellow/Blue:  2
		//      Orange/Green/Yellow: 3
		//      Red/Green/White:     4
		//      Red/White/Blue:      5
		//      Red/Yellow/Green:    6
		//      Red/Blue/Yellow:     7
		String c = new String(colors);
		if(c.equals("OBW")) { // corner cubie 0
			return 0b00000L;
		} else if(c.equals("WOB")) {
			return 0b00001L;
		} else if(c.equals("BWO")) {
			return 0b00010L;
		} else if(c.equals("OWG")) { // corner cubie 1
			return 0b00100L;
		} else if(c.equals("GOW")) {
			return 0b00101L;
		} else if(c.equals("WGO")) {
			return 0b00110L;
		} else if(c.equals("OYB")) { // corner cubie 2
			return 0b01000L;
		} else if(c.equals("BOY")) {
			return 0b01001L;
		} else if(c.equals("YBO")) {
			return 0b01010L;
		} else if(c.equals("OGY")) { // corner cubie 3
			return 0b01100L;
		} else if(c.equals("YOG")) {
			return 0b01101L;
		} else if(c.equals("GYO")) {
			return 0b01110L;
		} else if(c.equals("RGW")) { //corner cubie 4
			return 0b10000L;
		} else if(c.equals("WRG")) {
			return 0b10001L;
		} else if(c.equals("GWR")) {
			return 0b10010L;
		} else if(c.equals("RWB")) { // corner cubie 5
			return 0b10100L;
		} else if(c.equals("BRW")) {
			return 0b10101L;
		} else if(c.equals("WBR")) {
			return 0b10110L;
		} else if(c.equals("RYG")) { // corner cubie 6
			return 0b11000L;
		} else if(c.equals("GRY")) {
			return 0b11001L;
		} else if(c.equals("YGR")) {
			return 0b11010L;
		} else if(c.equals("RBY")) { // corner cubie 7
			return 0b11100L;
		} else if(c.equals("YRB")) {
			return 0b11101L;
		} else if(c.equals("BYR")) {
			return 0b11110L;
		} else {
			throw new IllegalArgumentException("[corner cubie] Invalid permutation or input detected!");
		}
    }
	

    private long edgeIDandOrientation(char[] colors) {
		if(colors.length != 2)
			throw new IllegalArgumentException("Invalid input array length (edge cube)");
		// Edge cubie identification encodings
		// (the first color is the MAJOR color):
		//  0:  White/Red
		//  1:  White/Blue
		//  2:  White/Green
		//  3:  White/Orange
		//  4:  Blue/Red
		//  5:  Blue/Orange
		//  6:  Orange/Green
		//  7:  Green/Red
		//  8:  Blue/Yellow
		//  9:  Orange/Yellow
		// 10:  Green/Yellow
		// 11:  Red/Yellow
		String c = new String(colors);
		// edge cubie 0
		if(c.equals("WR")) {
			return 0b00000L;
		} else if(c.equals("RW")) {
			return 0b00001L;
			// edge cubie 1
		} else if(c.equals("WB")) {
			return 0b00010L;
		} else if(c.equals("BW")) {
			return 0b00011L;
			// edge cubie 2
		} else if(c.equals("WG")) {
			return 0b00100L;
		} else if(c.equals("GW")) {
			return 0b00101L;
			// edge cubie 3
		} else if(c.equals("WO")) {
			return 0b00110L;
		} else if(c.equals("OW")) {
			return 0b00111L;
			// edge cubie 4
		} else if(c.equals("BR")) {
			return 0b01000L;
		} else if(c.equals("RB")) {
			return 0b01001L;
			// edge cubie 5
		} else if(c.equals("BO")) {
			return 0b01010L;
		} else if(c.equals("OB")) {
			return 0b01011L;
			// edge cubie 6
		} else if(c.equals("OG")) {
			return 0b01100L;
		} else if(c.equals("GO")) {
			return 0b01101L;
			// edge cubie 7
		} else if(c.equals("GR")) {
			return 0b01110L;
		} else if(c.equals("RG")) {
			return 0b01111L;
			// edge cubie 8
		} else if(c.equals("BY")) {
			return 0b10000L;
		} else if(c.equals("YB")) {
			return 0b10001L;
			// edge cubie 9
		} else if(c.equals("OY")) {
			return 0b10010L;
		} else if(c.equals("YO")) {
			return 0b10011L;
			// edge cubie 10
		} else if(c.equals("GY")) {
			return 0b10100L;
		} else if(c.equals("YG")) {
			return 0b10101L;
			// edge cubie 11
		} else if(c.equals("RY")) {
			return 0b10110L;
		} else if(c.equals("YR")) {
			return 0b10111L;
		} else {
			throw new IllegalArgumentException("[edge cubie] Invalid permutation or input detected!");
		}
    }
	
    private void computeEdgeCube() {
		edgeCube = 0L;
		char[] colors = new char[2];
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 2; j++)
			colors[j] = cube[getEdgeColors[2*i + j]];
			edgeCube = edgeCube | (edgeIDandOrientation(colors) << 5*i);
		}
		// for reference:
		// 0 : 1/19  (corner #: cubie faces)
		// 1 : 3/10
		// 2 : 5/16
		// 3 : 7/13
		// 4 : 21/32
		// 5 : 23/24
		// 6 : 26/27
		// 7 : 29/30
		// 8 : 34/48
		// 9 : 37/46
		// 10: 40/50
		// 11: 43/52
    }
}
