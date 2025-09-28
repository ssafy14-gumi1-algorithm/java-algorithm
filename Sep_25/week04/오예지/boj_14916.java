package 오예지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_14916 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = Integer.parseInt(br.readLine());

        int coin5 = money/5;
        int coin2 = 0;
        int result = -1;

        while (coin5 >= 0){
            if ((money-coin5*5)%2 == 0){
                coin2 = (money-coin5*5)/2;
                result = coin5+coin2;
                break;
            }else{
                coin5--;
            }
        }

        System.out.println(result);

    }
}
