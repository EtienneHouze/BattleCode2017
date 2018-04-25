package TestBot0;

import battlecode.common.*;
import scala.Unit;

import java.awt.*;
import java.time.temporal.Temporal;
import java.util.Map;

public class Functions {

    public enum ReturnType{
        /**
         * The three values functions can return in the tree
         */
        SUCCESS,
        FAIL,
        RUNNING
    }


    // Filler functions, used for tests=============================================
    public static ReturnType helloWorld(RobotController rc){
        System.out.println("Hello World");
        return ReturnType.SUCCESS;
    }


    public static  ReturnType move(RobotController rc){
        Direction dir = randomDirection();
        if (rc.canMove(dir)){
            try {
                rc.move(dir);
                return ReturnType.SUCCESS;
            }
            catch (GameActionException e){
                return ReturnType.FAIL;
            }
        }
        return ReturnType.FAIL;
    }



    public static ReturnType buildLumberjack(RobotController rc){
        double[] dirs = {0,Math.PI/3,2*Math.PI/3,Math.PI,4*Math.PI/3,5*Math.PI/3};
        for (double d:dirs){
            Direction dir = new Direction((float)d);
            if (rc.canBuildRobot(RobotType.LUMBERJACK,dir)){
                try{
                    rc.buildRobot(RobotType.LUMBERJACK,dir);
                    return  ReturnType.SUCCESS;
                }
                catch (GameActionException e){
                    return ReturnType.FAIL;
                }
            }
        }

        return ReturnType.SUCCESS;
    }


    // Archon stuff ==================================================================
    public static ReturnType evaluateMapSize(RobotController rc, UnitController uc){
        /**
         * If the map size has not yet been evaluated, estimates it.
         * Else, does nothing.
         */
        if (uc.estimatedMapSize == 0){
            MapLocation[] aTeamArchons = rc.getInitialArchonLocations(Team.A);
            MapLocation[] bTeamArchons = rc.getInitialArchonLocations(Team.B);
            uc.estimatedMapSize = aTeamArchons[0].distanceTo(bTeamArchons[0]);
        }
        return ReturnType.SUCCESS;
    }

    public static ReturnType readInfo(RobotController rc, UnitController uc){
        uc.lumberjacksCount = 0;        // In the following lines, reset of the unit counts.
        uc.tanksCount = 0;
        uc.soldiersCoutn = 0;
        uc.gardenersCount = 0;
        uc.scoutsCount = 0;
        for (int i = 0; i < 500; i ++){ // We iterate throught every possible unit channel and read what they say.
            try {
                int unitType = rc.readBroadcast(5000 + (10*i));
                switch (unitType){
                    case 1:{
                        uc.gardenersCount ++;
                    }break;
                    case 2:{
                        uc.soldiersCoutn ++;
                    }break;
                    case 3:{
                        uc.tanksCount ++;
                    }break;
                    case 4:{
                        uc.tanksCount ++;
                    }break;
                    case 5:{
                        uc.lumberjacksCount ++;
                    }break;
                    default:{

                    }break;
                }
            }
            catch(GameActionException e){
                return ReturnType.FAIL;
            }
        }
        return ReturnType.SUCCESS;
    }

    public static ReturnType broadcastAll(RobotController rc, UnitController uc){
        try{
            rc.broadcastInt(0,uc.whatToBuild);
        }
        catch(GameActionException e){

        }
        return ReturnType.SUCCESS;
    }

    public static ReturnType buildGardeners(RobotController rc, UnitController uc){
        Direction dir = randomDirection();
        if ((uc.gardenersCount < 20 && uc.estimatedMapSize > UnitController.bigMap)
                || (uc.gardenersCount < 10 && uc.estimatedMapSize > UnitController.smallMap)
                || uc.gardenersCount < 5){
            try{
                if(rc.canBuildRobot(RobotType.GARDENER,dir)){
                    rc.buildRobot(RobotType.GARDENER,dir);
                    uc.gardenersCount ++;
                    return ReturnType.SUCCESS;
                }
            }
            catch (GameActionException e){
                return ReturnType.FAIL;
            }
        }
        return ReturnType.FAIL;
    }

    public static ReturnType buyVP(RobotController rc, UnitController uc){
        if (rc.getTeamBullets() > 150){
            try{
                rc.donate(100);
                return ReturnType.SUCCESS;
            }
            catch (GameActionException e){
                return ReturnType.FAIL;
            }
        }
        return ReturnType.FAIL;
    }

    public static ReturnType whatToBuild(RobotController rc, UnitController uc){
        TreeInfo[] nearbyTrees = rc.senseNearbyTrees(20,Team.NEUTRAL);
        if (nearbyTrees.length > 5 && uc.lumberjacksCount < 2){
            uc.whatToBuild = 5;     // If there are more than 5 trees around and less than 2 lumberjacks, build lumberjackz
        }
        if (uc.enemyVisible || (uc.soldiersCoutn < 5 && rc.getRoundNum() < 100)){
            uc.whatToBuild = 2;     // If we have less than 5 soldiers or we see enemies, build soldiers.
            return ReturnType.SUCCESS;
        }
        uc.whatToBuild = -1;        // No building instructions here
        return ReturnType.SUCCESS;
    }

    // Gardener stuff ==================================================================

    
    public static ReturnType findPlantingSpot(RobotController rc, UnitController uc){
        if (uc.isInGoodSpot){
            return ReturnType.SUCCESS;
        }
        boolean tooClose = false;
        for (MapLocation archonLocation : rc.getInitialArchonLocations(rc.getTeam())){
            if (rc.getLocation().distanceTo(archonLocation) < 10){
                tooClose = true;
            }
        }
        if (rc.senseNearbyTrees((float) 2.0, rc.getTeam()).length == 0){
            try{
                double[] directions = new double[]{0, Math.PI / 3, 2 * Math.PI / 3, 4 * Math.PI / 3, 5 * Math.PI / 3};
                for (int i = 1; i < 5; i ++){
                    Direction dir = new Direction((float) directions[i]);
                    if ((tooClose || !(rc.canPlantTree(dir)))){
    //                    rc.move(dir);
                            Direction randDir = randomDirection();
                            if (rc.canMove(randDir)){
                                rc.move(randDir);
                            }
                        return ReturnType.RUNNING; // In this case, the agent is still trying to find a good spot
                        }
                    }
               }
                catch (GameActionException e){
                    return ReturnType.FAIL;
                }
            }
        uc.isInGoodSpot = true;
        return ReturnType.SUCCESS; // In this case, it is in a good spot
     }
    
    public static ReturnType plantTree(RobotController rc){
        try{
            double[] directions = new double[]{0, Math.PI / 3, 2 * Math.PI / 3, 4 * Math.PI / 3, 5 * Math.PI / 3};
            for (int i = 1; i < 5; i ++){
                Direction dir = new Direction((float) directions[i]);
                if (rc.canPlantTree(dir)){
                    rc.plantTree(dir);

                    return ReturnType.SUCCESS;
                }
            }
           }
            catch (GameActionException e){
                return ReturnType.FAIL;
            }
        return ReturnType.FAIL;
     }
    
    public static ReturnType waterTree(RobotController rc, UnitController uc){
        try{
            int lowestHealth = 50;
            int lowestID = -1;
            TreeInfo[] tis = rc.senseNearbyTrees((float) 2.0);
            for (TreeInfo ti : tis) {
                if (rc.canWater(ti.getID())) {
                    if (ti.getHealth() < lowestHealth) {
                        lowestID = ti.getID();
                    }
                }
            }
            if (lowestID != 1){
                rc.water(lowestID);
                return ReturnType.SUCCESS;
            }
        }
        catch (GameActionException e){
            return ReturnType.FAIL;
        }
        return ReturnType.FAIL;
    }

    public static ReturnType sendToArchon(RobotController rc,UnitController uc){
        switch (rc.getType()){
            case GARDENER:{
                int channel = 5000 + (uc.unitTeamId*10);
                try{
                    rc.broadcastInt(channel,1);     // broadcasting unit type
                    rc.broadcastFloat(channel+1,rc.getLocation().x);
                    rc.broadcastFloat(channel+2,rc.getLocation().y);
                    Team otherTeam;
                    if (rc.getTeam() == Team.A)
                        otherTeam = Team.B;
                    else
                        otherTeam = Team.A;
                    RobotInfo[] enemies = rc.senseNearbyRobots(20f,otherTeam);
                    rc.broadcastInt(channel+3,enemies.length);
                }
                catch (GameActionException e){

                }
            }break;
        }
        return ReturnType.SUCCESS;
    }


    // Helpers========================================================================
    static Direction randomDirection() {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }



    public static ReturnType run(String funcName, RobotController rc, UnitController uc){
        /**
         *
         * Runs all function depending on their name.
         * If you want to add your own function, add their entry here and their code in this class.
         */
        switch (funcName){
            case "hello":{
                return helloWorld(rc);
            }
            case "move":{
                return move(rc);
            }

            case "build_gardeners":{
                return buildGardeners(rc,uc);
            }

            case "build_lumberjacks":{
                return buildLumberjack(rc);
            }

            case "mapSize":{
                return evaluateMapSize(rc, uc);
            }

            case "readInfo":{
                return readInfo(rc,uc);
            }

            case "buy_points":{
                return buyVP(rc,uc);
            }

            case "findPlantingSpot":{
                return findPlantingSpot(rc,uc);
            }

            case "plantTree":{
                return plantTree(rc);
            }

            case "waterTree":{
                return waterTree(rc,uc);
            }

            case "whatToBuild":{
                return whatToBuild(rc,uc);
            }

           default:{
                return ReturnType.FAIL;
            }
        }
    }


}
