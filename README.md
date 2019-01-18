# HumanDrowsinessDetectionSystem
An IOT cum Android based project where we ensure the safety of car drivers
by detecting drowsiness, detecting accident notifying all the nearby hospitals and
gps tracking where owners can track their car even after being stolen through
their app. Using Raspberry PI3 and a normal PIcamera module (compatible with
RPI) , through python opencv we detect whether a driver is feeling sleepy or
not by tracking his eyes. Using gps modem and gsm modem and accelerometer
sensor we can detect whenever a car is involved in accident, For android app, we
have included normal signup login page along with Firebase-auth sign-in page.
Whenever accident is detected by our rpi3 , it will notify our app through firebase
and then our app will send messages with the gps locations to the hospitals.
• Hardware Requirement:
– Raspberry pi 3
– Raspberry Pi Camera module
– Gps/Gprs module (PAM-7Q)
– Accelerometer sensor (GY-521)
– Node mcu
• Software Requirements:
– Android device running on 5.0 and Onwards
