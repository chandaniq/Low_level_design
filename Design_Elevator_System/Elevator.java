package design_quest_sept.elevator_system;


import java.util.*;

public class Elevator implements Runnable {
    private int id;
    private int currentFloor;
    private Status status;
    private DoorStatus doorStatus;
    private Direction direction;
    private int capacity;
    private int currentLoad;
    private Queue<Request> requestQueue;
    private int requestLimit;

    Elevator(int id, int capacity, int requestLimit) {
        this.id = id;
        this.currentFloor = 0;
        this.status = status.IDLE;
        this.doorStatus = DoorStatus.CLOSED;
        this.capacity = capacity;
        this.currentLoad = 0;
        requestQueue = new LinkedList();
        this.requestLimit = requestLimit;

    }
    Status getStatus() {
        return status;
    }
    int getCurrentFloor() 
    {
        return currentFloor;
    }
    void moveUp() {
        currentFloor++;
    }
    void moveDown() {
        currentFloor--;
    }
    void openDoor() {
        System.out.println("Elevator "+id+" openinng the Door");
        doorStatus = DoorStatus.OPEN;
    }
    void closeDoor() {
        System.out.println("Elevator "+id+" closing the door");
        doorStatus = DoorStatus.CLOSED;
    }
    synchronized void  addRequest(Request request) {
        if(requestQueue.size() < requestLimit) {
            requestQueue.add(request);
            System.out.println("Elevator"+" "+id+" added request for src floor: "+request.getSrcFloor()+" "+" dest floor: "+request.getDestFloor());
            notifyAll();
        }
    }
    void moveElevator(int destFloor) {
        if(destFloor > currentFloor) {
            direction = Direction.UP;
            System.out.println("moving up");
            for (int i = currentFloor; i < destFloor; i++) {
                moveUp();
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();;
                }
                System.out.println("Elevator "+id+" reached florr: "+currentFloor);

            }
        }
        else if(destFloor < currentFloor) {
            direction = direction.DOWN;
            System.out.println("Elevator "+id+" moving down");
            for (int i = currentFloor; i > destFloor; i--) {
                moveDown();
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();;
                }
                System.out.println("Elevator "+id+" reached florr: "+currentFloor);
            } 
        }
        System.out.println("Elevator "+id+" stopped at "+currentFloor);
        if (currentFloor == destFloor)
        {
            openDoor();
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();;
            }
            closeDoor();
        }
    }
    void processRequest(Request request) {
        int srcFloor = request.getSrcFloor();
        int destFloor = request.getDestFloor();
        status = Status.MOVING;
        moveElevator(srcFloor);
        moveElevator(destFloor);
        status = Status.IDLE;
    }
    synchronized Request getNextRequest() {
        while(requestQueue.isEmpty()) {
            try {
                wait();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return requestQueue.poll();
    }
    synchronized void processRequests() {
        while(true) {
            while(!requestQueue.isEmpty()) {
                Request request = getNextRequest();
                processRequest(request);
            }
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();;
            } 
        }
    }
    public void run() {
        processRequests();
    }
    boolean isOverLoaded() {
        if (currentLoad > capacity)
            return true;
        return false;
    }
}
