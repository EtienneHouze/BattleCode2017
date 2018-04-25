package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.util.ArrayList;

public class GardenerController extends UnitController {

    public GardenerController(){}

    public GardenerController(RobotController r, String treeTxt){
        rc = r;
        tree = new BehaviourTree(treeTxt);

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
        treesToMonitor = new ArrayList<>();
        isInGoodSpot = false;
    }

    @Override
    public void run() {
        super.run();
        Functions.sendToArchon(this.rc,this);
    }
}
