Elevator :
- id : String
- currentFloor: int
- status : Status(moving, idle)
- doorStatus : DoorStatus
- direction : Direction
- requestQueue : Queue
- currentLoad : int
- capacity :int

+ moveUp() : void 
+ moveDown() : void
+ openDoor() : void
+ closeDoor() : void
+ addRequest(int floor) : void
+ handleRequest() : void
+ isOverLoaded() : boolean

Display :
+ showFloor() : void
+ showDirection : void

ButtonPanel :
+ pressButton(int srcFloor, int destFloor) : void


Request :
- srcFloor : int
- dstFloor : int
- direction : Direction 

Schedular :
+ getBestElevator(List<Elevator> elevators) : Eelevator

ElevatorController:
- elevators : List<Elevator> 
- schedular : Schedular
+ assignEelevator(Request request) : void
+ updateElevatorStatus(Elevator elevator) : void


