package pl.retsuz.shell.variations.Mv;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mv_Path extends CommandVariation {
    public Mv_Path(ICommandVariation next, ICommand parent) {
        super(next,parent,"[a-zA-Z0-9.l\\/_]*\\s[a-zA-Z0-9.l\\/_]*");
    }
    @Override
    public void make(String params) {
        String[] splitParam = params.trim().split(" ");
        Composite c= (Composite) (this.getParent().getContext().getCurrent());
        try {
            IComposite toMove = c.findElementByPath(splitParam[0]);
            IComposite des = c.findElementByPath(splitParam[1]);
            Composite.moveElement(toMove.getParent(), des, toMove);
        }catch(Exception e){
            System.out.println("Podane sciezki są nieprawidłowe.");
        }

    }
}
