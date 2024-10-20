package design_quest_sept.elevator_system;
import java.util.*;
public class ElevatorDemo {
    public static void main(String args[]) {
        //Create ElevatorController
        ElevatorController controller = new ElevatorController(5,10,5);

        //Cretae ButtonPanel
        ButtonPanel buttonPanel = new ButtonPanel(controller);

        buttonPanel.pressButton(5,10);
        buttonPanel.pressButton(3,7);
        buttonPanel.pressButton(8,2);
        buttonPanel.pressButton(1,9);

    }
}
