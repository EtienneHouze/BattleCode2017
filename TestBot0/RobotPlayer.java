package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class RobotPlayer {

    static String treeText = "0 question 1 2\n" +
            "1 leaf move\n" +
            "2 leaf hello";

    static String archonText = "0 seq 1 2 7\n" +
            "7 leaf readInfo\n" +
            "1 leaf mapSize\n" +
            "2 question 3 4\n" +
            "3 leaf build_gardeners\n" +
            "4 question 5 6\n" +
            "5 leaf buy_points\n" +
            "6 leaf hello";
    static String gardenerText = "0 question 1 2\n" +
            "1 leaf move\n" +
            "2 leaf move";
    static String lumberText = "0 leaf move";

    static String scoutText = "";

    static String soldierText = "";

    static String tankText = "";

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
