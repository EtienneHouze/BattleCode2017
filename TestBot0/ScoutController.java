package TestBot0;

import battlecode.common.RobotController;

public class ScoutController extends UnitController {

    public ScoutController(){}

    public ScoutController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);
    }
}
