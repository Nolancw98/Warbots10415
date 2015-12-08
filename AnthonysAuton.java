package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nolan on 11/11/2015.
 */
public class AnthonysAuton extends LinearOpMode {

    DcMotor right;
    DcMotor left;
    Servo dunker;



    public void runOpMode() throws InterruptedException
    {
        right = hardwareMap.dcMotor.get("motorRight");
        left = hardwareMap.dcMotor.get("motorLeft");

        right.setDirection(DcMotor.Direction.REVERSE);
        int ticks = 1440;

        left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();
        for (int i = 0; i<1;i++)
        {
            left.setTargetPosition(3 * ticks);
            right.setTargetPosition(3 * ticks);

            left.setPower(.95);
            right.setPower(.95);

            Thread.sleep(1000);

            left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

            left.setTargetPosition(3000);
            right.setTargetPosition(3000);

            left.setPower(.95);
            right.setPower(-.95);

            Thread.sleep(500);

            left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

            left.setTargetPosition(12 * ticks);
            right.setTargetPosition(12 * ticks);

            left.setPower(.95);
            right.setPower(.95);
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