package TestBot0;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class RobotPlayer {

    static String treeText = "0 question 1 2\n" +
            "1 leaf move\n" +
            "2 leaf hello";

    static String archonText = "0 question 1 2\n" +
            "1 leaf build_gardeners\n" +
            "2 leaf move";
    static String gardenerText = "0 question 1 2\n" +
            "1 leaf build_lumberjacks\n" +
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
