package TestBot0;

import battlecode.common.RobotController;

public class LumberjackController extends UnitController {

    public LumberjackController(){}

    public LumberjackController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);
    }
}
