package snapshot;
import Vector.IVector;
import Vector.Vector2D;
import person.Person;
import simulation.Simulation;
import states.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Memento {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final String source;

    public Memento(Simulation simulation){
        source = now().replace(" ", "-").replace(":", "-") + ".txt";
        File tempDirectory = new File("Saves");
        File file = new File("Saves/" + source);
        tempDirectory.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            simulation.saveToFile(new PrintWriter(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Memento(String source){
        this.source = source;
    }

    public Simulation getState(){
        File file = new File("Saves/" + source);
        ArrayList<Person> persons = new ArrayList<>();
        Simulation simulation = new Simulation();
        try {
            Scanner scanner = new Scanner(file);
            boolean firstLine = true;
            while (scanner.hasNextLine()){
                if(firstLine){
                    String[] data = scanner.nextLine().split(",");
                    simulation = new Simulation(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]));
                    firstLine = false;
                }
                String[] data = scanner.nextLine().split(",");
                IVector position = new Vector2D(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                IVector movement = new Vector2D(Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                persons.add(new Person(getStateFromString(data[0]) ,position, movement, Integer.parseInt(data[5]),
                        Integer.parseInt(data[6]), Integer.parseInt(data[7]) ));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        simulation.add(persons);
        return simulation;
    }

    private IState getStateFromString(String name){
        if(name.equals("Healthy")) return new Healthy();
        if(name.equals( "Resist")) return new Resist();
        if(name.equals( "SickWithoutSymptoms")) return new SickWithoutSymptoms();
        else return new SickWithSymptoms();
    }

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public String toString(){
        return source;
    }
}


