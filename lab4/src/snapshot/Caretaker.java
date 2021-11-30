package snapshot;

import java.io.File;
import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> mementos;

    public Caretaker(){
        this.mementos = new ArrayList<>();
        this.readMementosFromFile();
    }

    private void readMementosFromFile(){
        File[] files = new File("Saves").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                this.mementos.add(new Memento(file.getName()));
            }
        }
    }

    public void addMemento(Memento memento){
        this.mementos.add(memento);
    }

    public Memento getMemento(int index){
        return mementos.get(index);
    }

    public String[] getListOfMementos(){
        String[] mementos = new String[this.mementos.size()];
        for(int i=0; i< this.mementos.size(); i++){
            mementos[i] = this.mementos.get(i).toString();
        }
        return mementos;
    }
}
