package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class RobotPlayer {

    static String treeText = "0 question 1 2\n" +
            "1 leaf move\n" +
            "2 leaf hello";

    static String archonText = "0 seq 1 2\n" +
            "1 leaf mapSize\n" +
            "2 question 3 4\n" +
            "3 leaf build_gardeners\n" +
            "4 question 5 6\n" +
            "5 leaf buy_points\n" +
            "6 leaf moveBounded";
    static String gardenerText = "0 seq 5 1 2\n" +
            "1 leaf findPlantingSpot\n" +
            "2 question 3 4\n"+
            "3 leaf plantTree\n" +
            "4 leaf waterTree\n" +
            "5 leaf buildUnit";
    static String lumberText = "0 question 1 2\n" +
            "1 seq 3 4\n" +
            "2 leaf moveBounded\n" +
            "3 leaf senseTrees\n" +
            "4 leaf chop";

    static String scoutText = "0 leaf move";

    static String soldierText = "0 seq 1 2\n" +
            "1 leaf shoot\n" +
            "2 leaf moveToBlood";

    static String tankText = "0 leaf move";

    static RobotController rc;

    public static void run(RobotController rc) throws GameActionException{
        BehaviourTree tree = new BehaviourTree(treeText);


        ArchonController archonController = null;
        TankController tankController = null;
        LumberjackController lumberController = null;
        GardenerController gardenerController = null;
        SoldierController soldierController = null;
        ScoutController scoutController = null;


        switch (rc.getType()){
            case ARCHON:{
                archonController = new ArchonController(rc,archonText);
            }break;
            case LUMBERJACK:{
                lumberController = new LumberjackController(rc,lumberText);
            }break;
            case GARDENER:{
                gardenerController = new GardenerController(rc,gardenerText);
            }break;
            case SOLDIER:{
                soldierController = new SoldierController(rc,soldierText);
            }break;
            case SCOUT:{
                scoutController = new ScoutController(rc,scoutText);
            }break;
            case TANK:{
                tankController = new TankController(rc,tankText);
            }break;
            default:{

            }break;
        }

        while (true){
            switch (rc.getType()){
                case ARCHON:{
                    archonController.run();
                }break;

                case TANK:{
                    tankController.run();
                }break;

                case SCOUT:{
                    scoutController.run();
                }break;

                case SOLDIER:{
                    soldierController.run();
                }break;

                case GARDENER:{
                    gardenerController.run();
                }break;

                case LUMBERJACK:{
                    lumberController.run();
                }break;
                default:{
                    System.out.println("Unknown Type");
                }
            }
        }
    }

}
