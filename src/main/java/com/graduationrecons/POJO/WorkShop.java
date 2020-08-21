package com.graduationrecons.POJO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class WorkShop implements Serializable {

    private int id;
    private Date time;
    private int shopid;
    private int groupid;
    private String jarid;

    public float t;
    private float rate;
    private float Temperature;
    private float Teperatrue1;
    private float Teperatrue2;
    private float Teperatrue3;
    private float Teperatrue4;
    private float Teperatrue5;
    private float Teperatrue6;
    private float Teperatrue7;
    private float Teperatrue8;
    private float Teperatrue9;
    private float Teperatrue10;
    private float Teperatrue11;
    private float Teperatrue12;
    private float Teperatrue13;
    private float Teperatrue14;
    private float Teperatrue15;
    private float Teperatrue16;
    private float Teperatrue17;
    private float Teperatrue18;
    private float Teperatrue19;
    private float Teperatrue20;
    private float Teperatrue21;
    private float Teperatrue22;
    private float Teperatrue23;
    private float Teperatrue24;
    private float Teperatrue25;
    private float Teperatrue26;
    private float Teperatrue27;
    private float Teperatrue28;
    private float Teperatrue29;
    private float Teperatrue30;

    public float getTemperature(int index)
    {
        switch (index)
        {
            case 1:return getTeperatrue1();
            case 2:return getTeperatrue2();
            case 3:return getTeperatrue3();
            case 4:return getTeperatrue4();
            case 5:return getTeperatrue5();
            case 6:return getTeperatrue6();
            case 7:return getTeperatrue7();
            case 8:return getTeperatrue8();
            case 9:return getTeperatrue9();
            case 10:return getTeperatrue10();
            case 11:return getTeperatrue11();
            case 12:return getTeperatrue12();
            case 13:return getTeperatrue13();
            case 14:return getTeperatrue14();
            case 15:return getTeperatrue15();
            case 16:return getTeperatrue16();
            case 17:return getTeperatrue17();
            case 18:return getTeperatrue18();
            case 19:return getTeperatrue19();
            case 20:return getTeperatrue20();
            case 21:return getTeperatrue21();
            case 22:return getTeperatrue22();
            case 23:return getTeperatrue23();
            case 24:return getTeperatrue24();
            case 25:return getTeperatrue25();
            case 26:return getTeperatrue26();
            case 27:return getTeperatrue27();
            case 28:return getTeperatrue28();
            case 29:return getTeperatrue29();
            case 30:return getTeperatrue30();

        }
        return 0;

    }





}
