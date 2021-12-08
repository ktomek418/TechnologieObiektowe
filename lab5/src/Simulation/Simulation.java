package Simulation;

import Converter.CoordinateConverter;
import Vector.IVector;
import Vector.Vector2D;
import emergencyNotification.ReportedAlarm;
import emergencyNotification.emergencyStrategy.EmergencyStrategy;
import emergencyNotification.emergencyStrategy.FalseAlarmStrategy;
import emergencyNotification.emergencyStrategy.TrueAlarmStrategy;
import fireBrigade.SKKM;
import fireBrigade.fireTruck.IFireTruck;
import fireBrigade.firefightingUnit.FirefightingUnit;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Simulation extends JPanel {

    public static final int FPS = 25;
    public static final int PADDING = 50;

    private final SKKM skkm;
    private final int simulationWidth;
    private final int simulationHeight;

    private Timer nextMoveTimer;
    private Timer emergencyTimer;
    private final Random random = new Random();

    public Simulation(SKKM skkm){

        this.skkm = skkm;
        double[] dimension = CoordinateConverter.getDimensionInPixels().getComponents();
        this.simulationWidth = (int) dimension[0];
        this.simulationHeight = (int) dimension[1];
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        this.setPreferredSize(new Dimension(this.simulationWidth + PADDING * 2, this.simulationHeight +
                PADDING * 2));
        setTimers();
    }

    private void setTimers(){
        nextMoveTimer = new Timer(1000/FPS, e -> {skkm.nextMove();
        repaint();});
        emergencyTimer = new Timer(1000, e->skkm.addEmergency(createEmergency()));
        nextMoveTimer.start();
        emergencyTimer.start();
    }

    private ReportedAlarm createEmergency(){
        double[] dim = CoordinateConverter.getDimensionInPixels().getComponents();
        IVector position = new Vector2D(random.nextInt((int) dim[0]), random.nextInt((int) dim[1]));
        int cars;
        EmergencyStrategy strategy;
        Color color;
        if(random.nextDouble() <= 0.7){
            cars = 2;
            color = Color.magenta;
        } else{
            cars = 3;
            color = Color.DARK_GRAY;
        }
        if(random.nextDouble() <= 0.05){
            strategy = new FalseAlarmStrategy();
            color = Color.black;
        } else strategy = new TrueAlarmStrategy();

        return new ReportedAlarm(position, strategy, cars, color);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillRect(PADDING, PADDING,simulationWidth, simulationHeight);
        for (FirefightingUnit unit: skkm.getFirefightingUnits()) {
            g2d.setPaint(Color.GREEN);
            double[] position = unit.getPosition().getComponents();
            g2d.fillRect((int) position[0]+ PADDING, (int) position[1] + PADDING, 15, 15);
            for(IFireTruck truck: unit.getFireTrucks()){
                double[] truckPosition = truck.getPosition().getComponents();
                g2d.setPaint(truck.getColor());
                g2d.fillOval((int) truckPosition[0]+ PADDING, (int) truckPosition[1] + PADDING, 8, 8);
            }
        }
        for(ReportedAlarm emergency: skkm.getEmergencyNotifications()){
            g2d.setPaint(emergency.getColor());
            double[] position = emergency.getPosition().getComponents();
            g2d.fillOval((int) position[0]+ PADDING, (int) position[1] + PADDING, 15, 15);
        }
    }
}
