package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mkdir extends Command {
    public Mkdir(IContext ctx, ICommand next) {
        super("mkdir", ctx, next, null, "Użycie mkdir <sciezka>");
    }
}
