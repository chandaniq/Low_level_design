package design_quest_sept.elevator_system;

public class Request {
    private int srcFloor;
    private int destFloor;

    Request(int srcFloor, int destFloor) {
        this.srcFloor = srcFloor;
        this.destFloor = destFloor;
    }
    int getSrcFloor() {
        return srcFloor;
    }
    int getDestFloor() {
        return destFloor;
    }
}
