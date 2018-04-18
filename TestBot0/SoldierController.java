package TestBot0;

import battlecode.common.RobotController;

public class SoldierController extends UnitController {

    public SoldierController(){}

    public SoldierController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);
    }
}
