package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class LumberjackController extends UnitController {

    public LumberjackController(){}

    public LumberjackController(RobotController r, String t){
        rc = r;
        tree = new BehaviourTree(t);

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
