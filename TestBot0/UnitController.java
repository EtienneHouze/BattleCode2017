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

    // GENERAL VARIABLES==========================================================================================
    RobotController rc;
    BehaviourTree tree;
    public int unitTeamId;

    public final static float smallMap = 150; // Under that, map is considered small.
    public final static float bigMap = 400; // Above that, map is considered big.

    // ARCHON=====================================================================================================

        // The following keep track of how many units of each type we have.
    int unitsBuilt;
    int gardenersCount;
    int soldiersCoutn;
    int lumberjacksCount;
    int scoutsCount;
    int tanksCount;

        // Instruction sent to gardeners to ask them to build specific units.
        // The following encoding is used:
        //  -1 : nothing
        //  1 : lumberjacks
        //  2 : scouts
        //  3: soldiers
        //  4: tanks
    int whatToBuild;

    float estimatedMapSize; // An estimation of the map size.

    // GARDENER====================================================================================================

    // SCOUT=======================================================================================================

    // SOLDIER=====================================================================================================

    // LUMBERJACK==================================================================================================

    // TANK========================================================================================================


    public void run(){
        tree.tickDown(rc,this);
    }

}
