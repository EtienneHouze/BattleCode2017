package TestBot0;


import battlecode.common.RobotController;

public class ArchonController extends UnitController{


    public ArchonController(RobotController r, String tree_generator){
        rc = r;
        tree = new BehaviourTree(tree_generator);
    }

    public void run(){
        tree.tickDown(rc,this);
    }
}
