package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    public final JFrame mainFrame = new JFrame("Queue Simulator");      //Elementele ce vor aparea in interfata grafica

    private final JLabel l_nrClients = new JLabel("Number of clients: ", SwingConstants.CENTER);
    private final JLabel l_nrQueues = new JLabel("Number of queues: ", SwingConstants.CENTER);
    private final JLabel l_simTime = new JLabel("Maximum simulation time: ", SwingConstants.CENTER);
    private final JLabel l_minArriv = new JLabel("Minimum arrival time: ", SwingConstants.CENTER);
    private final JLabel l_maxArriv = new JLabel("Maximum arrival time: ", SwingConstants.CENTER);
    private final JLabel l_minServ = new JLabel("Minimum service time: ", SwingConstants.CENTER);
    private final JLabel l_maxServ = new JLabel("Maximum service time: ", SwingConstants.CENTER);
    private final JLabel l_statusAux = new JLabel("Status: ", SwingConstants.RIGHT);
    private final JLabel l_status = new JLabel("NOT RUNNING", SwingConstants.LEFT);
    private final JLabel l_output = new JLabel("Output Message:", SwingConstants.CENTER);

    private final JTextField tf_nrClients = new JTextField();
    private final JTextField tf_nrQueues = new JTextField();
    private final JTextField tf_simTime = new JTextField();
    private final JTextField tf_minArriv = new JTextField();
    private final JTextField tf_maxArriv = new JTextField();
    private final JTextField tf_minServ = new JTextField();
    private final JTextField tf_maxServ = new JTextField();
    private final JTextField tf_output = new JTextField();

    private final JButton b_initSim = new JButton("Initialize Simulation");

    GridBagLayout gl = new GridBagLayout();

    private final JPanel p_main = new JPanel(new GridBagLayout());
    private GridBagConstraints cn_main = new GridBagConstraints();

    public View(){
        tf_nrClients.setHorizontalAlignment(SwingConstants.CENTER);     //Setari cu scop pur estetic
        tf_nrQueues.setHorizontalAlignment(SwingConstants.CENTER);
        tf_simTime.setHorizontalAlignment(SwingConstants.CENTER);
        tf_minArriv.setHorizontalAlignment(SwingConstants.CENTER);
        tf_maxArriv.setHorizontalAlignment(SwingConstants.CENTER);
        tf_minServ.setHorizontalAlignment(SwingConstants.CENTER);
        tf_maxServ.setHorizontalAlignment(SwingConstants.CENTER);
        tf_output.setHorizontalAlignment(SwingConstants.CENTER);

        tf_output.setFont(tf_output.getFont().deriveFont(Font.BOLD));

        tf_output.setEditable(false);

        cn_main.insets = new Insets(0,3,0,3);

        p_main.add(l_nrClients,setConstraints(cn_main,0,0,1,1));
        p_main.add(tf_nrClients,setConstraints(cn_main,1,0,1,1));
        p_main.add(l_nrQueues,setConstraints(cn_main,0,1,1,1));
        p_main.add(tf_nrQueues,setConstraints(cn_main,1,1,1,1));
        p_main.add(l_simTime,setConstraints(cn_main,0,2,1,1));
        p_main.add(tf_simTime,setConstraints(cn_main,1,2,1,1));
        p_main.add(l_minArriv,setConstraints(cn_main,0,3,1,1));
        p_main.add(tf_minArriv,setConstraints(cn_main,1,3,1,1));
        p_main.add(l_maxArriv,setConstraints(cn_main,0,4,1,1));
        p_main.add(tf_maxArriv,setConstraints(cn_main,1,4,1,1));
        p_main.add(l_minServ,setConstraints(cn_main,0,5,1,1));
        p_main.add(tf_minServ,setConstraints(cn_main,1,5,1,1));
        p_main.add(l_maxServ,setConstraints(cn_main,0,6,1,1));
        p_main.add(tf_maxServ,setConstraints(cn_main,1,6,1,1));
        p_main.add(b_initSim,setConstraints(cn_main,0,7,2,1));
        p_main.add(l_statusAux,setConstraints(cn_main,2,2,1,1));
        p_main.add(l_status,setConstraints(cn_main,3,2,1,1));
        p_main.add(l_output,setConstraints(cn_main,2,3,2,1));
        p_main.add(tf_output,setConstraints(cn_main,2,4,2,1));

        mainFrame.setSize(600,500);
        mainFrame.setContentPane(p_main);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private GridBagConstraints setConstraints(GridBagConstraints gbc, int x, int y,int width, int height){  // functie pentru constrangeri gridBagLayout
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    protected void addInitListener(ActionListener e){
        b_initSim.addActionListener(e);
    }

    protected void setOutputMsg(String s) {
        tf_output.setText(s);
    }
    protected String[] getData(){   // ia toate datele de intrare
         String[] data = new String[7];
         data[0] = tf_nrClients.getText();
         data[1] = tf_nrQueues.getText();
         data[2] = tf_simTime.getText();
         data[3] = tf_minArriv.getText();
         data[4] = tf_maxArriv.getText();
         data[5] = tf_minServ.getText();
         data[6] = tf_maxServ.getText();

         return data;
    }

    protected void setStatusRunning(){
        l_status.setText("RUNNING");
        l_status.setForeground(new Color(0, 150, 0));
    }

    protected void setStatusNotRunning(){
        l_status.setText("NOT RUNNING");
        l_status.setForeground(Color.black);
    }
}
