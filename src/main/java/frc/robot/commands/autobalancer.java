package frc.robot.commands;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.Sensors.Pigeon;
import java.lang.Math;

public class autobalancer { 
    public SwerveDrive sds;

    public autobalancer(SwerveDrive m_sds) {
        sds = m_sds;
    }

    public void properheading() {
        double heading = sds.heading * (180/Math.PI);
        if (heading<0 && heading>-1*(Math.PI)/4) {        if (heading<0 && heading>-1*(Math.PI)/4) {}
        if (heading<-1*(Math.PI)/4 && heading>-1*(Math.PI)/2){}
        if (heading<-1*(Math.PI)/2 && heading> -3*(Math.PI)/4){}}
        if (heading<-1*(Math.PI)/4 && heading>-1*(Math.PI)/2){}
        if (heading<-1*(Math.PI)/2 && heading> -3*(Math.PI)/4){}
        if (heading>0 && heading<Math.PI/4) {
        }
        if (heading>Math.PI/4 && heading< Math.PI/2){}
        if (heading>Math.PI/2 && heading< Math.PI/4){}
    }
}