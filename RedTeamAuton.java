package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nolan on 11/11/2015.
 */
public class RedTeamAuton extends LinearOpMode {

    DcMotor motorRight;
    DcMotor motorLeft;
    Servo dunker;



    public void runOpMode() throws InterruptedException
    {
        AutonomousMethods am = new AutonomousMethods();
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");

        motorRight.setDirection(DcMotor.Direction.REVERSE);
        int ticks = 1440;
        double dist = 4.25 * Math.PI;

        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();
        for (int i = 0; i<1;i++)
        {

            while(Math.abs(motorLeft.getCurrentPosition()) <= 2 * 1440)
            {
                motorLeft.setPower(-.95);
                motorRight.setPower(-.95);

            }
            am.reset();

            while(Math.abs(motorLeft.getCurrentPosition()) <= 3000)
            {
                motorLeft.setPower(.95);
                motorRight.setPower(-.95);
            }
            am.reset();

            while(Math.abs(motorLeft.getCurrentPosition()) <= 7 * 1440)
            {
                motorLeft.setPower(-.95);
                motorRight.setPower(-.95);
            }
            am.reset();
        }


        Thread.sleep(500);

        //left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        //waitOneFullHardwareCycle();
        //left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        //right.setPower(0);
        //left.setPower(0);




    }

}