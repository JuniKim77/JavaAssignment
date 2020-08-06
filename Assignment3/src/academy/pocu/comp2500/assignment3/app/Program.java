package academy.pocu.comp2500.assignment3.app;

import academy.pocu.comp2500.assignment3.App;
import academy.pocu.comp2500.assignment3.SimulationManager;
import academy.pocu.comp2500.assignment3.IntVector2D;
import academy.pocu.comp2500.assignment3.Marine;
import academy.pocu.comp2500.assignment3.Mine;
import academy.pocu.comp2500.assignment3.SmartMine;
import academy.pocu.comp2500.assignment3.Tank;
import academy.pocu.comp2500.assignment3.Turret;
import academy.pocu.comp2500.assignment3.Unit;
import academy.pocu.comp2500.assignment3.Wraith;
import academy.pocu.comp2500.assignment3.registry.Registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
//        {
//            // K00
//            SimulationManager simulationManager = SimulationManager.getInstance();
//            ArrayList<Unit> units = new ArrayList<>();
//
//            Unit u0 = new Wraith(new IntVector2D(0xf, 0));
//            Unit u1 = new Marine(new IntVector2D(4, 3));
//            Unit u2 = new Marine(new IntVector2D(0xa, 5));
//            Unit u3 = new Turret(new IntVector2D(3, 4));
//            Unit u4 = new Wraith(new IntVector2D(0xe, 7));
//            Unit u5 = new Mine(new IntVector2D(0xe, 6), 3);
//            Unit u6 = new Mine(new IntVector2D(5, 0), 3);
//            Unit u7 = new SmartMine(new IntVector2D(8, 0), 2, 2);
//            Unit u8 = new Mine(new IntVector2D(4, 2), 3);
//            Unit u9 = new Tank(new IntVector2D(9, 6));
//            Unit uA = new Turret(new IntVector2D(3, 7));
//            Unit uB = new SmartMine(new IntVector2D(0xe, 3), 3, 1);
//            Unit uC = new Tank(new IntVector2D(8, 0));
//            Unit uD = new SmartMine(new IntVector2D(2, 4), 4, 1);
//            Unit uE = new Wraith(new IntVector2D(0xa, 0));
//            Unit uF = new Wraith(new IntVector2D(4, 5));
//            units.add(u0);
//            units.add(u1);
//            units.add(u2);
//            units.add(u3);
//            units.add(u4);
//            units.add(u5);
//            units.add(u6);
//            units.add(u7);
//            units.add(u8);
//            units.add(u9);
//            units.add(uA);
//            units.add(uB);
//            units.add(uC);
//            units.add(uD);
//            units.add(uE);
//            units.add(uF);
//
//            for (Unit unit : units) {
//                simulationManager.spawn(unit);
//            }
//
//            SimulationVisualizer visualizer = new SimulationVisualizer(units);
//            for (int i = 0; i < 10; ++i) {
//                clearConsole();
//                visualizer.visualize(i, simulationManager.getUnits());
//                simulationManager.update();
//                continueOnEnter();
//            }
//        }
//        {
//            // K01
//            SimulationManager simulationManager = SimulationManager.getInstance();
////            simulationManager.clear();
//            ArrayList<Unit> units = new ArrayList<>();
//
//            Unit u0 = new Tank(new IntVector2D(0x0, 0x2));
//            Unit u1 = new Tank(new IntVector2D(0x0, 0x6));
//            Unit u2 = new SmartMine(new IntVector2D(0x9, 0x7), 2, 1);
//            Unit u3 = new Mine(new IntVector2D(0x7, 0x5), 2);
//            Unit u4 = new SmartMine(new IntVector2D(0x1, 0x3), 2, 2);
//            Unit u5 = new Mine(new IntVector2D(0xa, 0x6), 3);
//            Unit u6 = new Mine(new IntVector2D(0xb, 0x7), 2);
//            Unit u7 = new SmartMine(new IntVector2D(0x0, 0x7), 2, 2);
//            Unit u8 = new Mine(new IntVector2D(0x6, 0x6), 4);
//            Unit u9 = new Mine(new IntVector2D(0x2, 0x0), 3);
//            Unit uA = new Mine(new IntVector2D(0x6, 0x4), 1);
//            Unit uB = new Mine(new IntVector2D(0xe, 0x3), 4);
//            Unit uC = new SmartMine(new IntVector2D(0xb, 0x0), 3, 2);
//            Unit uD = new SmartMine(new IntVector2D(0xf, 0x2), 3, 1);
//            Unit uE = new Mine(new IntVector2D(0xc, 0x2), 2);
//            Unit uF = new SmartMine(new IntVector2D(0xc, 0x6), 4, 1);
//            units.add(u0);
//            units.add(u1);
//            units.add(u2);
//            units.add(u3);
//            units.add(u4);
//            units.add(u5);
//            units.add(u6);
//            units.add(u7);
//            units.add(u8);
//            units.add(u9);
//            units.add(uA);
//            units.add(uB);
//            units.add(uC);
//            units.add(uD);
//            units.add(uE);
//            units.add(uF);

//            for (Unit unit : units) {
//                simulationManager.spawn(unit);
//            }
//
//            SimulationVisualizer visualizer = new SimulationVisualizer(units);
//            for (int i = 0; i < 10; ++i) {
//                clearConsole();
//                visualizer.visualize(i, simulationManager.getUnits());
//                simulationManager.update();
//                continueOnEnter();
//            }
//        }
        {
            // K02
            SimulationManager simulationManager = SimulationManager.getInstance();
//            simulationManager.clear();
            ArrayList<Unit> units = new ArrayList<>();

            Unit u0 = new Turret(new IntVector2D(0x6, 0x0));
            Unit u1 = new Wraith(new IntVector2D(0x5, 0x2));
            Unit u2 = new Wraith(new IntVector2D(0x0, 0x0));
            Unit u3 = new Marine(new IntVector2D(0x3, 0x3));
            Unit u4 = new Tank(new IntVector2D(0x6, 0x0));
            Unit u5 = new SmartMine(new IntVector2D(0x5, 0x0), 4, 1);
            Unit u6 = new Tank(new IntVector2D(0x1, 0x0));
            Unit u7 = new Marine(new IntVector2D(0x1, 0x2));
            Unit u8 = new Marine(new IntVector2D(0x4, 0x3));
            Unit u9 = new SmartMine(new IntVector2D(0x5, 0x0), 1, 3);
            Unit uA = new Tank(new IntVector2D(0x1, 0x1));
            Unit uB = new Marine(new IntVector2D(0x3, 0x0));
            Unit uC = new Mine(new IntVector2D(0x3, 0x3), 3);
            Unit uD = new Wraith(new IntVector2D(0x3, 0x0));
            Unit uE = new Wraith(new IntVector2D(0x1, 0x0));
            Unit uF = new SmartMine(new IntVector2D(0x0, 0x2), 2, 2);
            units.add(u0);
            units.add(u1);
            units.add(u2);
            units.add(u3);
            units.add(u4);
            units.add(u5);
            units.add(u6);
            units.add(u7);
            units.add(u8);
            units.add(u9);
            units.add(uA);
            units.add(uB);
            units.add(uC);
            units.add(uD);
            units.add(uE);
            units.add(uF);

            for (Unit unit : units) {
                simulationManager.spawn(unit);
            }

            SimulationVisualizer visualizer = new SimulationVisualizer(units);
            for (int i = 0; i < 10; ++i) {
                clearConsole();
                visualizer.visualize(i, simulationManager.getUnits());
                simulationManager.update();
                continueOnEnter();
            }
        }
        {
            // K03
            SimulationManager simulationManager = SimulationManager.getInstance();
            simulationManager.clear();
            ArrayList<Unit> units = new ArrayList<>();

            Unit u0 = new Turret(new IntVector2D(0x6, 0x0));
            Unit u1 = new Wraith(new IntVector2D(0x5, 0x2));
            Unit u2 = new Wraith(new IntVector2D(0x0, 0x0));
            Unit u3 = new Marine(new IntVector2D(0x3, 0x3));
            Unit u4 = new Tank(new IntVector2D(0x6, 0x0));
            Unit u5 = new SmartMine(new IntVector2D(0x5, 0x0), 4, 1);
            Unit u6 = new Tank(new IntVector2D(0x1, 0x0));
            Unit u7 = new Marine(new IntVector2D(0x1, 0x2));
            Unit u8 = new Marine(new IntVector2D(0x4, 0x3));
            Unit u9 = new SmartMine(new IntVector2D(0x5, 0x0), 1, 3);
            Unit uA = new Tank(new IntVector2D(0x1, 0x1));
            Unit uB = new Marine(new IntVector2D(0x3, 0x0));
            Unit uC = new Mine(new IntVector2D(0x3, 0x3), 3);
            Unit uD = new Wraith(new IntVector2D(0x3, 0x0));
            Unit uE = new Wraith(new IntVector2D(0x1, 0x0));
            Unit uF = new SmartMine(new IntVector2D(0x0, 0x2), 2, 2);
            units.add(u0);
            units.add(u1);
            units.add(u2);
            units.add(u3);
            units.add(u4);
            units.add(u5);
            units.add(u6);
            units.add(u7);
            units.add(u8);
            units.add(u9);
            units.add(uA);
            units.add(uB);
            units.add(uC);
            units.add(uD);
            units.add(uE);
            units.add(uF);

            for (Unit unit : units) {
                simulationManager.spawn(unit);
            }

            SimulationVisualizer visualizer = new SimulationVisualizer(units);
            for (int i = 0; i < 10; ++i) {
                clearConsole();
                visualizer.visualize(i, simulationManager.getUnits());
                simulationManager.update();
                continueOnEnter();
            }
        }
        {
            SimulationManager simulationManager = SimulationManager.getInstance();
            simulationManager.clear();

            Unit u0 = new Mine(new IntVector2D(12, 1), 2);
            Unit u1 = new Marine(new IntVector2D(0, 5));
            Unit u2 = new Turret(new IntVector2D(5, 6));
            Unit u3 = new Tank(new IntVector2D(2, 4));
            Unit u4 = new Marine(new IntVector2D(2, 4));
            Unit u5 = new Wraith(new IntVector2D(2, 7));

            ArrayList<Unit> units = new ArrayList<>();

            units.add(u0);
            units.add(u1);
            units.add(u2);
            units.add(u3);
            units.add(u4);
            units.add(u5);

            for (Unit unit : units) {
                simulationManager.spawn(unit);
            }

            SimulationVisualizer visualizer = new SimulationVisualizer(units);
            for (int i = 0; i < 10; ++i) {
                clearConsole();
                visualizer.visualize(i, simulationManager.getUnits());
                simulationManager.update();
                continueOnEnter();
            }
        }
    }

    public static void continueOnEnter() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Press enter to continue");
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}