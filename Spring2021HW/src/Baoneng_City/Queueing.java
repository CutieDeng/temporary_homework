package Baoneng_City;

import java.util.Scanner;

public class Queueing {
    private int hour;
    private int minute;
    private int s;

    private Queueing(int hour, int minute, int s) {
        this.hour = hour;
        this.minute = minute;
        this.s = s;
    }

    private Queueing(){

    }

    private Queueing compareTo(Queueing specialPoint){
        Queueing delta = new Queueing();
        delta.hour = this.hour - specialPoint.hour;
        delta.minute = this.minute - specialPoint.minute;
        delta.s = this.s - specialPoint.s;
        while (delta.s < 0){
            delta.s += 60;
            delta.minute --;
        }
        while (delta.minute < 0){
            delta.minute += 60;
            delta.hour --;
        }
        return delta;
    }

    @Override
    public String toString(){
        if (hour == 0 && minute == 0){
            return String.format("%ds",s);
        }
        if (s == 0){
            return String.format("%dm",60*hour + minute);
        }
        return String.format("%dm%ds", 60*hour + minute, s);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queueing time1 = new Queueing(input.nextInt(), input.nextInt(), input.nextInt());
        Queueing time2 = new Queueing(input.nextInt(), input.nextInt(), input.nextInt());
        System.out.println(time2.compareTo(time1));
    }
}
