package TestBot0;

import battlecode.common.*;

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

    public static ReturnType tryToDoSomeStuff(RobotController rc){
        Direction dir = randomDirection();
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
        Direction dir = randomDirection();
        if (rc.canBuildRobot(RobotType.LUMBERJACK,dir)){
            try{
                rc.buildRobot(RobotType.LUMBERJACK,dir);
                return  ReturnType.SUCCESS;
            }
            catch (GameActionException e){
                return ReturnType.FAIL;
            }
        }
        return ReturnType.FAIL;
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

    // Helpers========================================================================
    static Direction randomDirection() {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }



    public static ReturnType run(String funcName, RobotController rc,UnitController uc){
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

            default:{
                return ReturnType.FAIL;
            }
        }
    }


}
