package TestBot0;

import battlecode.common.*;

import java.awt.*;

public class Functions {

    public enum ReturnType{
        /**
         * The three values functions can return in the tree
         */
        SUCCESS,
        FAIL,
        RUNNING
    }

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

    public static ReturnType buildGardeners(RobotController rc){
        Direction dir = randomDirection();
        if (rc.getRobotCount() < 100 && rc.canBuildRobot(RobotType.GARDENER,dir)){
            try{
                rc.buildRobot(RobotType.GARDENER,dir);
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

    static Direction randomDirection() {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }



    public static ReturnType run(String funcName, RobotController rc){
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
                return buildGardeners(rc);
            }

            case "build_lumberjacks":{
                return buildLumberjack(rc);
            }

            default:{
                return ReturnType.FAIL;
            }
        }
    }


}
