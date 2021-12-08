import Converter.CoordinateConverter;
import Simulation.Simulation;
import Window.ProgramWindow;
import fireBrigade.SKKM;

public class Main {

    public static void main(String[] args) {
        ProgramWindow window = new ProgramWindow(new Simulation(new SKKM()));
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[0]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[0]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[0]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[0]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[1]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[1]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[1]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[1]);
        System.out.println(CoordinateConverter.randomPositionWGS84().getComponents()[1]);
    }


}
