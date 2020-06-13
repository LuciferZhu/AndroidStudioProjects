package com.example.lowlevel;
import com.example.ledcontrolplus.Student;

public class LedNativePlus {
    static {
        System.loadLibrary("ledPlus_jni");
    }

    public native int openDev();
    public native int closeDev();
    public native int devOn();
    public native int devOff();

    public native int addNum(int a, int b);
    public native String getStringFromJNI();
    public native void setStringToJNI(String str);
    public native Student getStudentFromJNI();
    public native void setStudentToJNI(Student stud);
}
