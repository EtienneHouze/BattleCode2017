package TestBot0;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.TreeInfo;

import java.util.ArrayList;

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

    public final static float smallMap = 50; // Under that, map is considered small.
    public final static float bigMap = 100; // Above that, map is considered big.

    // ARCHON=====================================================================================================

        // The following keep track of how many units of each type we have.
    int unitsBuilt;
    int gardenersCount;
    int soldiersCoutn;
    int lumberjacksCount;
    int scoutsCount;
    int tanksCount;
    int enemiesVisible;

    MapLocation whereToGo;

        // Instruction sent to gardeners to ask them to build specific units.
        // The following encoding is used:
        //  -1 : nothing
        //  2 : soldiers
        // 3 : tanks
        // 4 : scouts
        // 5 : lumberjacks
    int whatToBuild;

    float estimatedMapSize; // An estimation of the map size.

    // GARDENER====================================================================================================

    ArrayList<TreeInfo> treesToMonitor;
    boolean isInGoodSpot;

    // SCOUT=======================================================================================================

    // SOLDIER=====================================================================================================

    // LUMBERJACK==================================================================================================

    TreeInfo[] nearbyTrees;
    TreeInfo targetTree;

    // TANK========================================================================================================


    public void run(){
        tree.tickDown(rc,this);
    }

}
