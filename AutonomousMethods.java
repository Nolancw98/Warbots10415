package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Anthony on 11/14/2015.
 */
public class AutonomousMethods extends RedTeamAuton {

    double leftPower;
    double rightPower;

    public AutonomousMethods()
    {
        leftPower = 0;
        rightPower = 0;
    }

    public void drive()
    {
        leftPower = .9;
        rightPower = .9;

        if(Math.abs(super.motorLeft.getCurrentPosition()) > Math.abs(super.motorRight.getCurrentPosition()))
        {
            leftPower-= .1;
            rightPower+= .1;
        }
        else
        {
            //Does the opposite of the if part.
            leftPower+=.1;
            rightPower-=.1;
        }

    }

    public void reset() throws InterruptedException
    {
        motorLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitOneFullHardwareCycle();
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {

        }

        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
    }

    public double leftPower()
    {
        return leftPower;
    }

    public double rightPower()
    {
        return rightPower;
    }

}
