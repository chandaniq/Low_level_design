package design_quest_sept.elevator_system;
import java.util.*;
public class ElevatorController {
    private List<Elevator> elevators;
    private static ElevatorController controller;

    private ElevatorController(int numElavators, int requestLimit, int capacity) {
        this.elevators = new ArrayList<>();
        for(int i = 0; i < numElavators; i++) {
            Elevator elevator = new Elevator(i, capacity, requestLimit);
            elevators.add(elevator);
            new Thread(elevator).start();
        }
    }
    public static ElevatorController getControllerInstance(int numElavators, int requestLimit, int capacity) {
        if(controller == null)
        {
            controller = new ElevatorController(numElavators, requestLimit, capacity);
        }
        return controller;
    }
    void assignElevator(Request request) {
        Elevator elevator = getBestElevator(request);
        if (elevator != null) {
            elevator.addRequest(request);
        }
    }
    Elevator getBestElevator(Request request) {
        Elevator optimalElevator = null;
        int srcFloor = request.getSrcFloor();
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            int dist = Math.abs(srcFloor - currentFloor);
            if (dist < minDistance) {
                minDistance = dist;
                optimalElevator = elevator;

            }
        }
        return optimalElevator;
    }
    

}
