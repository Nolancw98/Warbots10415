package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nolan on 12/6/2015.
 */
public class NolansAuton extends LinearOpMode{
    DcMotor motorRight;
    DcMotor motorLeft;

    public void runOpMode() throws InterruptedException
    {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        final int ticks = 1440; //To get 1 full rotation of the big gear multiply by 3

        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        ElapsedTime eTime = new ElapsedTime();
        AutonomousMethods am = new AutonomousMethods();
        double time = 0;

        //Autonomus Code
        waitForStart();
        eTime.startTime();

        motorLeft.setTargetPosition(ticks * 3);
        motorRight.setTargetPosition(ticks * 3);

        //Moves forward 1 rotation
        while (eTime.time() < 1)
        {
            motorLeft.setPower(am.leftPower());
            motorRight.setPower(am.rightPower());
            am.drive();
        }
        am.reset();
        //Mock code for turning right
        while (eTime.time() < .5)
        {
            motorLeft.setPower(-.95);
            motorRight.setPower(.95);
        }
        am.reset();
        //Moves forward 9 rotations
        while (eTime.time() < 5)
        {
            motorLeft.setPower(am.leftPower());
            motorRight.setPower(am.rightPower());
            am.drive();
        }
        //Rest of the code

        //Stops everything
        motorLeft.setPower(0);
        motorRight.setPower(0);

    }


}
