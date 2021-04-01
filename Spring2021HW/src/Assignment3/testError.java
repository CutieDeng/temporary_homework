package Assignment3;

        import java.util.Scanner;

public class testError {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long f1 = System.currentTimeMillis();
        for(int i = 1; i <= n; ++ i)
            for(int i1 = 1; i1 <= n; ++ i1)
                for(int i2= 1; i2 <= n; ++ i2)
                    for(int i3 = 1; i3 <= n; ++ i3);
        System.out.println(System.currentTimeMillis() - f1);
    }

}
