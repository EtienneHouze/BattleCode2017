package TestBot0;


import battlecode.common.RobotController;

public class ArchonController extends UnitController{


    public ArchonController(RobotController r, String tree_generator){
        rc = r;
        tree = new BehaviourTree(tree_generator);
        lumberjacksCount = 0;
        gardenersCount = 0;
        soldiersCoutn = 0;
        tanksCount = 0;
        unitsBuilt = 0;

    }

    public void run(){
        tree.tickDown(rc,this);
    }
}
