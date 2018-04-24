package TestBot0;

import battlecode.common.RobotController;

import java.util.ArrayList;

public class GardenerController extends UnitController {

    public GardenerController(){}

    public GardenerController(RobotController r, String treeTxt){
        rc = r;
        tree = new BehaviourTree(treeTxt);

        treesToMonitor = new ArrayList<>();
        isInGoodSpot = false;
    }
}
