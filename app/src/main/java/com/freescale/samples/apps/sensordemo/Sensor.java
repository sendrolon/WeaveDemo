package com.freescale.samples.apps.sensordemo;

/**
 * Created by B50027 on 3/25/2016.
 */

enum SensorType {
    NONE,
    DISTANCE,
    GAS,
    ALTITUDE,
    TEMPERATURE,
    GYROSCOPE,
    ACCELEROMETER,
    MAGNETOMETER,
    HALL
};
public class Sensor {
    private String mReport;
    public Sensor(String report) {
        mReport = report;
    }
    private Object mValue;
    public Sensor(String type, Object value) {
        mSensorType = Sensor.findSensorType(type);
        mValue = value;
        mReport = type + " = " + value;
    }
    private SensorType mSensorType;


    public static SensorType findSensorType(String type) {
        switch (type) {
            case "distance":
                return SensorType.DISTANCE;
            case "gas":
                return SensorType.GAS;
            case "altitude":
                return SensorType.ALTITUDE;
            case "temperature":
                return SensorType.TEMPERATURE;
            case "gyroscope":
                return SensorType.GYROSCOPE;
            case "magnetometer":
                return SensorType.MAGNETOMETER;
            case "hall":
                return SensorType.HALL;
            default:
                return SensorType.NONE;
        }
    }
    public String getReport() {
        return mReport;
    }
    public SensorType getSensorType() {
        return mSensorType;
    }
    public Object getValue() {
        return mValue;
    }
}
