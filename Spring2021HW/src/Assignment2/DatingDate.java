package Assignment2;

import java.util.Scanner;

public class DatingDate {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        day begin = new day(input.next());

        day end = new day(input.next());

        int ans = 0;

        int year = begin.year;

        day nowDay = null;

        while (year <= end.year) {
            try {
                nowDay = new day(year);
                if (nowDay.compareTo(begin) >= 0 && nowDay.compareTo(end) <= 0){
                    System.out.println(nowDay);
                    ans ++;
                }
            }catch (RuntimeException e){
            }
            year ++;
        }
        System.out.println(ans);
    }

    private static class day{
        private int year;
        private int month;
        private int day;
        private int dayMax;

        private day(int year, int month, int day){
            this.year = year;
            if(month > 12 || month <= 0){
                throw new RuntimeException("Month is out of the bound.");
            }
            this.month = month;
            dayMax = getDayMax();
            if (day > dayMax || day <= 0){
                throw new RuntimeException("Day is out of the bound.");
            }
            this.day = day;
        }

        private day(String date){
            this(Integer.parseInt(date.substring(0, 4)) , Integer.parseInt(date.substring(4, 6)) , Integer.parseInt(date.substring(6, 8)));
        }

        private day(int year){
            this(year, year % 10 * 10 + year % 100 / 10, year / 1000 + year / 100 % 10 * 10);
        }

        private int getDayMax(){
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                return 31;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11){
                return 30;
            }
            if (year % 400 == 0){
                return 29;
            }
            if (year % 100 == 0){
                return 28;
            }
            if (year % 4 == 0){
                return 29;
            }
            return 28;
        }

        @Deprecated
        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() != day.class){
                return false;
            }
            day o = (DatingDate.day) obj;
            if (year != o.year){
                return false;
            }
            if (month != o.month){
                return false;
            }
            if (day != o.day){
                return false;
            }
            return true;
        }

        @Deprecated
        public boolean isEcho(){
            StringBuilder test = new StringBuilder();
            if (month < 10){
                test.append(0);
            }
            test.append(month);
            if (day<10){
                test.append(0);
            }
            test.append(day);
            String result = test.reverse().toString();
            return result.equals(String.valueOf(year));
        }

        /**
         *
         * @param obj the relative object to compare with.
         * @return 1: means this is later than obj.
         * 0: means they are same.
         * -1: means it is before obj.
         */
        public int compareTo(day obj){
            if (this.year < obj.year){
                return -1;
            }
            else if (this.year > obj.year){
                return 1;
            }
            if (this.month < obj.month){
                return -1;
            }
            else if (this.month > obj.month){
                return 1;
            }
            if (this.day < obj.day){
                return -1;
            }
            else if (this.day > obj.day){
                return 1;
            }
            return 0;
        }

        @Override
        public String toString(){
            return String.format("%04d-%02d-%02d", year, month, day);
        }
    }

}
