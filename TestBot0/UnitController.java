package TestBot0;

import battlecode.common.RobotController;

public abstract class UnitController {
    /**
     *
     * This abstract class is extended by one specific class for each robot type.
     * The aim is to be passed as argument to the functions, so that variables can be taken into account (for instance, a variable keeping track of the trees associated
     * with a gardener...
     *
     * Every variable of this class will not be used by all units, some are type-specific, so be careful !
     */

    // GENERAL VARIABLES
    RobotController rc;
    BehaviourTree tree;

    // ARCHON

    // GARDENER

    // SCOUT

    // SOLDIER

    // LUMBERJACK

    // TANK


    public void run(){
        tree.tickDown(rc,this);
    }

}
