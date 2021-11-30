import simulation.Simulation;
import window.Window;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int width, height, population;

        System.out.println("Width: ");
        width = Integer.parseInt(scanner.nextLine());

        System.out.println("Height: ");
        height = Integer.parseInt(scanner.nextLine());

        System.out.println("Population: ");
        population = Integer.parseInt(scanner.nextLine());

        Window window = new Window(Simulation.createSimulationWithResistance(width, height, population));
    }
}
