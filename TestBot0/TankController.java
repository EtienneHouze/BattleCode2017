package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.awt.*;

public class TankController extends UnitController {

    public TankController(){}

    public TankController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);


        // Next block finds the iD.
        unitTeamId = 0;
        try{
            int test = rc.readBroadcast(5000);
            while (test != 0 && unitTeamId < 500){
                unitTeamId ++;
                test = rc.readBroadcast(5000 + (unitTeamId * 10));
            }
        }
        catch (GameActionException e){

        }
    }
}
