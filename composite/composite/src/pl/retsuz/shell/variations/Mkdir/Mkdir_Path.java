package pl.retsuz.shell.variations.Mkdir;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mkdir_Path extends CommandVariation {

    public Mkdir_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {
        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite catalog = new Composite();
            String[] splitParam = params.split("/");
            String catalogName = splitParam[splitParam.length-1];
            catalog.setName(catalogName);
            if(splitParam.length == 1){
                c.addElement(catalog);
            } else{
                IComposite elem = c.findElementByPath(params.replace("/"+catalogName, ""));
                if(elem instanceof Composite){
                    ((Composite) elem).addElement(catalog);
                } else{
                    System.out.println("Docelowy element nie jest katalogiem.");
                }
            }
        }catch(Exception e){
            System.out.println("Nieprawidłowa ścieżka do folderu, lub folder już istnieje.");
        }
    }
}
