package gui;

import control.SimulationManager;

public class Model {
    private boolean rightInput;

    protected String checkData(String[] inputData){ //verifica daca datele de intrare sunt corecte si returneaza
        int it = 0, nr = 0, aux = 0;                //un mesaj corespunzator
        for(String s : inputData){
            if(s.isBlank()) {rightInput = false; return "Empty Data Fields!";}
            try{
                nr = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                rightInput = false;
                return "Non-integer data type!";
            }
            if(nr<=0){
                rightInput = false;
                return "Negative values/zeroes not allowed!";
            }
            if(it == 3 || it == 5){
                aux = nr;
            } else if((it == 4 || it == 6)&& nr<aux) {
                rightInput = false;
                return "Wrong interval times!";
            }
            it++;
        }
        rightInput = true;
        return "Correct Input - SIMULATION STARTS";
    }

    protected void initSimulation(String[] inputData){
        if(isRightInput()){             //daca datele sunt corecte porneste thread-ul cu manager
            SimulationManager sm = new SimulationManager(inputData);
            Thread mainThread = new Thread(sm);
            mainThread.start();
        }
    }

    public boolean isRightInput(){
        return rightInput;
    }
}
