package model;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ImportHeuristics {
    private ArrayList<MyHashMap> namesList = new ArrayList<MyHashMap>();

    public ImportHeuristics() {
        try
        {
            FileInputStream fis = new FileInputStream("heuristicData");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            namesList = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
    
    public MyHashMap getHeuristic() {
	    return namesList.get(0);
    }
}
