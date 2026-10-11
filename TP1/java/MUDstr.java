import java.util.Scanner;
import java.util.Random;

public class MUDstr {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine(); 
        
        while(!(str.equals("FIM"))){
            char a  = aleatorio();
            char b  = aleatorio();

            char[] vetor = str.toCharArray();

            for(int i = 0; i <str.length();i++){
                char c = str.charAt(i);
                if(c == a){
                    vetor[i] = b;
                }
            }
            
        
            str = new String(vetor);
            System.out.println(str);
        
        str = scan.nextLine(); 
        }
        scan.close();
    }
    
    static Random random = new Random(4);

    public static char aleatorio(){
        return (char) ('a' + (Math.abs(random.nextInt())%26));
    }
}
