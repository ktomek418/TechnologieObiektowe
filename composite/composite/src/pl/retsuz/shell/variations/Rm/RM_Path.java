package pl.retsuz.shell.variations.Rm;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class RM_Path extends CommandVariation {

    public RM_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }
    @Override
    public void make(String params) {
        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite file = c.findElementByPath(params);
            if(file instanceof TextFile){
                ((Composite) file.getParent()).removeElement(file);
                System.out.println("Plik zostal usuniety.");
            } else{
                System.out.println("Nie mozna usunac katalogu.");}

        }catch(Exception e){
            System.out.println("Nieprawidłowa ścieżka do pliku, lub podany plik nie istnieje");
        }
    }
}
