package design_quest_sept.elevator_system;

public class ButtonPanel {
    private ElevatorController controller;
    ButtonPanel(ElevatorController controller) {
        this.controller = controller;
    }
    void pressButton(int srcFloor, int destFloor) {
        Request request = new Request(srcFloor, destFloor);
        controller.assignElevator(request);
    }
}
