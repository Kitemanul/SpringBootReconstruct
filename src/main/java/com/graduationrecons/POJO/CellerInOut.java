package com.graduationrecons.POJO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class CellerInOut implements Serializable {

    private int id;
    private int period=-1;
    private int groupid;
    private int jarid;
    private Date time;
    private Date intime;
    private Date outtime;
    private Date Etime;
    private int rate;



}
