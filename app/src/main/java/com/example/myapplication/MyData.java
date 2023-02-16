package com.example.myapplication;

import org.w3c.dom.Node;

public class MyData {
    private int dis1,dis2,dis3,didWhat;
    private MyData next;
    private MyData(int dis1,int dis2,int dis3,int didWhat)
    {
        this.setDis1(dis1);
        this.setDis2(dis2);
        this.setDis3(dis3);
        this.setDidWhat(didWhat);
    }

    public int getDis1(){return this.dis1;}
    public void setDis1(int dis1){this.dis1=dis1;}

    public int getDis2(){return this.dis1;}
    public void setDis2(int dis2){this.dis2=dis2;}

    public int getDis3(){return this.dis3;}
    public void setDis3(int dis3){this.dis3=dis3;}

    public int getDidWhat(){return this.dis1;}
    public void setDidWhat(int didWhat){this.didWhat=didWhat;}

    public MyData getNext(){return this.next;}
    //public MyData setNext(MyData next,int dis1,int dis2,int dis3,int didWhat){
      //  return this.next=next;
   // }
}
