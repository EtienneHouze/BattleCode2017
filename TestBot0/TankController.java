package TestBot0;

import battlecode.common.RobotController;

import java.awt.*;

public class TankController extends UnitController {

    public TankController(){}

    public TankController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);
    }
}
