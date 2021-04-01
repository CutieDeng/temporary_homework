package Assignment2;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Deprecated
public class DatingData_2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate beginDay = LocalDate.parse(input.next(), formatter);
        LocalDate endDay = LocalDate.parse(input.next(), formatter);
        LocalDate testDay;
        int ans = 0;
        int month;
        int day;
        for (int year = beginDay.getYear(); year <= endDay.getYear(); year++){
            month = year % 10 * 10 + year % 100 / 10;
            day = year / 10000 + year / 1000 % 10 * 10;
            try {
                testDay = LocalDate.of(year, month, day);
                if (testDay.isAfter(beginDay) && testDay.isBefore(endDay))
                    ans ++;
                System.out.println(testDay);
            }
            catch (DateTimeException e){
            }
        }
        System.out.println(ans);
    }
}
