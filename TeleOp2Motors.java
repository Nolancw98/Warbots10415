package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nolan on 11/14/2015.
 */
public class TeleOp2Motors extends OpMode {
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor lift;
    DcMotor feeder;
    DcMotor tilt;
    Servo dunker;
    Servo leftwing;
    Servo rightwing;
    Servo stopper;


    @Override
    public void init() {
        //Motors
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        lift = hardwareMap.dcMotor.get("lift");
        feeder = hardwareMap.dcMotor.get("feeder");
        tilt = hardwareMap.dcMotor.get("tilt");
        //lift.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //Servos
        dunker = hardwareMap.servo.get("dunker");
        leftwing = hardwareMap.servo.get("leftwing");
        rightwing = hardwareMap.servo.get("rightwing");
        dunker.setPosition(1);
        leftwing.setPosition(.5);
        rightwing.setPosition(.5);
        //stopper = hardwareMap.servo.get("Stop");
        //Setting Direction
        motorRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;

        // Alternate method for controlling the robot (using both sticks):
        // To use, uncomment code below and comment code above.
        // float left = -gamepad1.left_stick_y;
        // float right = -gamepad1.right_stick_y;

        right = Range.clip(right, -.95, .95);
        left = Range.clip(left, -.96, .96);


        //right = (float) scaleInput(right);
        //left = (float) scaleInput(left);
        if(gamepad2.left_bumper)
        {
            leftwing.setPosition(.3);
        }
        else
        {
            leftwing.setPosition(.7);
        }
        if(gamepad2.right_bumper)
        {
            rightwing.setPosition(.7);
        }
        else
        {
            rightwing.setPosition(.3);
        }

        if (gamepad1.a)
        {
            // greater = closer
            // smaller = farther
            dunker.setPosition(0.3);
            //dunker.setPosition(0.4);
        }
        else
        {
            dunker.setPosition(1);
        }
        if(gamepad2.dpad_down)
        {
            lift.setPower(-(.95 / 3));
        }
        if(gamepad2.dpad_up)
        {
            lift.setPower(.95 / 3);
        }
        if(gamepad2.a)
        {
            tilt.setPower(.1);
        }
        else if(gamepad2.y)
        {
            tilt.setPower(-.1);
        }
        else
        {
            tilt.setPower(0);
        }

        if (gamepad1.left_bumper && gamepad1.right_bumper && gamepad2.b){
            motorLeft.setPower(0);
            motorRight.setPower(0);
            stopper.setPosition(1);
        }
        else
        {

        }

        // write values to motor
        motorRight.setPower(right);
        motorLeft.setPower(left);

        lift.setPower(-gamepad2.left_stick_y);
        feeder.setPower(gamepad2.right_stick_y / 3.0);
    }


    @Override
    public void stop(){

    }

    /*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

}
