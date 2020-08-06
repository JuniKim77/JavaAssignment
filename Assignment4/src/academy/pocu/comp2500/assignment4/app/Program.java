package academy.pocu.comp2500.assignment4.app;

import academy.pocu.comp2500.assignment4.App;
import academy.pocu.comp2500.assignment4.Canvas;
import academy.pocu.comp2500.assignment4.ClearCommand;
import academy.pocu.comp2500.assignment4.Command;
import academy.pocu.comp2500.assignment4.CommandHistoryManager;
import academy.pocu.comp2500.assignment4.DecreasePixelCommand;
import academy.pocu.comp2500.assignment4.DrawPixelCommand;
import academy.pocu.comp2500.assignment4.FillHorizontalLineCommand;
import academy.pocu.comp2500.assignment4.IncreasePixelCommand;
import academy.pocu.comp2500.assignment4.OverdrawAnalyzer;
import academy.pocu.comp2500.assignment4.ToLowerCaseCommand;
import academy.pocu.comp2500.assignment4.ToUpperCaseCommand;
import academy.pocu.comp2500.assignment4.registry.Registry;

public class Program {

    public static void main(String[] args) {
        // write your code here
        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        {
            Canvas canvas = new Canvas(30, 25);
            Command command1 = new FillHorizontalLineCommand(10, 't');
            Command command2 = new FillHorizontalLineCommand(2, 'K');
            Command command3 = new DrawPixelCommand(8, 6, '0');
            Command command4 = new ClearCommand();
            Command command5 = new ClearCommand();
            Command command6 = new DrawPixelCommand(22, 18, 'K');
            Command command7 = new ClearCommand();

            CommandHistoryManager manager = new CommandHistoryManager(canvas);
            manager.execute(command1);
            manager.execute(command2);
            manager.execute(command3);
//            System.out.println(canvas.getDrawing());
            manager.execute(command4);
//            System.out.println(canvas.getDrawing());
            manager.undo();
//            System.out.println(canvas.getDrawing());
            manager.undo();
//            System.out.println(canvas.getDrawing());
            manager.execute(command5);
            manager.execute(command6);
            manager.execute(command7);
//            System.out.println(canvas.getDrawing());
            manager.undo();
            System.out.println(canvas.getDrawing());
            Command command8 = new FillHorizontalLineCommand(12, 'B');
            manager.execute(command8);
            Command command9 = new DecreasePixelCommand(27, 7);
            manager.execute(command9);
            manager.undo();
            Command command10 = new DecreasePixelCommand(26, 12);
            manager.execute(command10);
            Command command11 = new ToUpperCaseCommand(14, 14);
            manager.execute(command11);
            Command command12 = new ToUpperCaseCommand(3, 18);
            System.out.println(canvas.getDrawing());
            manager.execute(command12);
            System.out.println(canvas.getDrawing());
            manager.redo();
            System.out.println(canvas.getDrawing());
        }
    }
}
