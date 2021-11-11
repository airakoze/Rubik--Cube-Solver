package model;

public class CubeTracer {
    private String[] sequence;
    private int counter;
    private int length;
	
    public CubeTracer(int length) {
		if(length < 1)
			throw new IllegalArgumentException("Tracer length must be greater than 0");
		this.length = length;
		sequence = new String[length];
		counter = 0;
		sequence[0] = "The cube is already solved!";
    }
	
    public void writeMove(String move) {
		sequence[counter] = move;
		counter = (counter + 1) % length;
    }

    public void eraseMove() {
		counter--;
		if(counter == -1)
			counter = length - 1;

		sequence[counter] = null;
    }

    public String[] getSequence() {
		return sequence;
    }

    public void reset() {
		counter = 0;
		for (int i = 0; i < length; i++) {
			sequence[i] = null;
		}
		sequence[0] = "The cube is already solved!";
    }
}
