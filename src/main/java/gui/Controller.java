package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final Model cModel;
    private final View cView;

    public Controller(Model model, View view){
        cModel = model;
        cView = view;

        cView.addInitListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {    // cand se apasa butonul de initializare
                cView.setOutputMsg(cModel.checkData(cView.getData()));      // seteaza mesajul de iesire
                if(cModel.isRightInput()) {
                    cView.setStatusRunning();           // seteaza statusul RUNNING
                    cModel.initSimulation(cView.getData());     // initializeaza simularea
                }
            }
        });
    }
}
