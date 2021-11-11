package controller;
import model.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RubikCubeController {
    private final MainPanel mainPanel;

    public RubikCubeController(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    public void onStart(){
        SidePanel rubikView = null;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        try {
            rubikView = new SidePanel(mainPanel.getTitle() + " " + strDate+".csv");
            mainPanel.closeWindow();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        SidePanel finalRubikView = rubikView;
        rubikView.nextButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalRubikView.onNext();
            }
        });
        SidePanel finalRubikView1 = rubikView;
        rubikView.saveAndRunButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalRubikView1.onSaveAndRun();
            	ImportHeuristics h = new ImportHeuristics();
                MyHashMap heuristic = h.getHeuristic();
                CubeEntry c = new CubeEntry();
                CubeTracer tracer = new CubeTracer(IDAStar.GODS_NUMBER);
                do {
                    do {
                        try{
                            c.enterCubeState("current.csv");
                        }catch (IOException l) {
                            System.out.println(l.getMessage());
                        }
                    }
                    while(!c.isCubeOK());
                    c.createCompactCube();
                    long cornerCube = c.getCornerCube();
                    long edgeCube = c.getEdgeCube();
                    boolean foundSolution =
                        IDAStar.findSolution(cornerCube, edgeCube, heuristic, tracer);
                    if (foundSolution) {
                        String[] solution = tracer.getSequence();
                        ResultPanel resultView = new ResultPanel(solution);
                    } else
                        System.out.println("No solution found.");
                    tracer.reset();
                } while(c.solveAnotherCube());
            }
        });
    }

    public void onLoad(){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        
        ImportHeuristics h = new ImportHeuristics();
        MyHashMap heuristic = h.getHeuristic();
        CubeEntry c = new CubeEntry();
        CubeTracer tracer = new CubeTracer(IDAStar.GODS_NUMBER);
        do {
            do {
                try{
                    c.enterCubeState(file);
                }catch (IOException l) {
                    System.out.println(l.getMessage());
                }
            } 
            while(!c.isCubeOK());
            c.createCompactCube();
            long cornerCube = c.getCornerCube();
            long edgeCube = c.getEdgeCube();
            boolean foundSolution =
            IDAStar.findSolution(cornerCube, edgeCube, heuristic, tracer);
            if (foundSolution) {
                String[] solution = tracer.getSequence();
                ResultPanel resultView = new ResultPanel(solution);
            } else
                System.out.println("No solution found.");
            tracer.reset();
        } 
        while(c.solveAnotherCube());
    }
}
