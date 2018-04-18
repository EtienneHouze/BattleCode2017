package TestBot0;

import battlecode.common.RobotController;

public class GardenerController extends UnitController {

    public GardenerController(){}

    public GardenerController(RobotController r, String treeTxt){
        rc = r;
        tree = new BehaviourTree(treeTxt);
    }
}
