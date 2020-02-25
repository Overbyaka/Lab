import java.util.Scanner;

public class First {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] mas = new int [4];
        int x;
        boolean check = true;
        System.out.println("Введите кол-во купюр номиналом 1000, 500, 100, 30 рублей: ");
        for(int i = 0; i < 4; i++){
            mas[i] = in.nextInt();
        }
        System.out.println("Введете сумму, которую нужно выдать этими купюрами: ");
        x = in.nextInt();
        for(int i = 0; i <= mas[3] && check && i * 30 <= x; i++){
            for(int j = 0; j <= mas[2] && check && j * 100 <=x; j++){
                for(int k = 0; k <= mas[1] && check && k * 500 <= x; k++){
                    for(int l = 0; l <= mas[0] && check && l*1000<=x; l++){
                        if(l*1000 + k*500+j*100+i*30 == x){
                            System.out.println("Чтоб получить сумму " + x + ", необходимо: " + l + " купюр номиналом 1000, " + k + " купюр номиналом 500, " + j + " купюр номиналом 100 и " + i + " купюр номиналом 30.");
                            check = false;
                        }
                    }
                }
            }
        }
        if(check){
            System.out.println("Данную сумму нельзя получить имеющимися купюрами");
        }
    }
}
