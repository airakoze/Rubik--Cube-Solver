package model;
import java.util.Arrays;
import java.io.Serializable;

public class MyHashMap implements Serializable {
    
    private byte[] movesTable;
    private int capacity;
    private long[] cubeTable;
	
    public MyHashMap(int capacity) {
		this.capacity = capacity;
		movesTable = new byte[capacity];
		cubeTable = new long[capacity];

		Arrays.fill(movesTable, (byte)-1);
		Arrays.fill(cubeTable, (long)0);
    }
	
    public void put(long cube, byte moves) {
		int index = hash(cube);

		while (movesTable[index] != (byte)-1) {
			if (cube == cubeTable[index])
			break;
			index = (index + 1) % capacity;
		}

		movesTable[index] = moves;
		cubeTable[index] = cube;
    }
	
    public byte get(long cube) {
		int index = hash(cube);

		while (movesTable[index] != (byte)-1) {
			if (cube == cubeTable[index])
			break;
			index = (index + 1) % capacity;
		}
		return movesTable[index];
    }
	
    private int hash(long cube) {
		cube = cube & 0b1111111111111111111111111111111111111111L;	
		cube = (cube >> 2) | (cube << 38);
		cube = cube * 37; //17
		cube = cube % (long) capacity;
		int hash = (int) cube;
		return Math.abs(hash);
    }
}
