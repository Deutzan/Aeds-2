import java.util.Scanner;

public class Troca {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        
        while(!str.equals("FIM")){
            //-- manda para inverter --//
            System.out.println(inverte(str));
            str = scan.nextLine();

        }

        scan.close();
    }
       
    public static String inverte(String str){
    return inverterAux(str, str.length() - 1);
}

private static String inverterAux(String str, int i){
    //-- condição de parada --//
    if(i < 0){
        return ""; 
    }
    //-- pega da primeira posição e vai somando ate o ultimo para retornar uma string invertida lá pro --//
    return str.charAt(i) + inverterAux(str, i - 1);
}
}
