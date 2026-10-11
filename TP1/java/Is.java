import java.util.Scanner;

public class Is {
    public static void main (String[] args){
    Scanner scan = new Scanner(System.in);
    
    //-- pede uma string --//
    String str = scan.nextLine();
    
    while(!(str.equals("FIM"))){

    //-- metodos --//
    X1X2(str);
    X3(str);
    X4(str);
    
    System.out.println("");
    str = scan.nextLine();

    }
        scan.close();
    }

    public static String lowkey(String str, int i){
    char[] chars = str.toCharArray();
    lowkeyRec(chars, i);
    return new String(chars);
}

    private static void lowkeyRec(char[] chars, int i){
    if(i == chars.length){
        return; // condição de parada
    }
    if(chars[i] >= 'A' && chars[i] <= 'Z'){
        chars[i] = (char)(chars[i] + ('a' - 'A'));
    }
    lowkeyRec(chars, i+1);
    }

    public static void ContaVoCO(String str, int i, int[] vogal, int[] consoante){
        if(i == str.length()){
            return;
        }
        if((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') && (str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u')){
            vogal[0]++;
        }
        else if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
            consoante[0]++;
        }
        ContaVoCO(str,i+1,vogal,consoante);
    }

    public static void X1X2(String str){
        int[] vogal = new int[1];
        int[] consoante = new int[1];

        lowkey(str,0);
        ContaVoCO(str,0,vogal,consoante);

        //-- verifica vogal e consoante --//
        if(vogal[0] > 0 && vogal[0] == str.length()){
            System.out.print("SIM ");
            }
        else{
            System.out.print("NAO ");
            }
        if(consoante[0] >  0 && consoante[0] == str.length()){
            System.out.print("SIM ");
            }
        else{
            System.out.print("NAO ");
            }
    }

    //-- verifica se é numero real --//
    public static int verificadorReal(String str, int i, int point){
        if(i == str.length()){
            return i;
        }
        char c = str.charAt(i);
        if(!(c >= '0' && c <= '9') && !(c == '.' || c == ',')){
            return i;
        }
        if(c == '.' || c == ','){
            point++;
        }
        if(point > 1){
            return i;
        }
    return verificadorReal(str, i + 1, point);
    }

    public static int verificadorInt(String str, int i){
        if(i == str.length()){
            return i;
        }
        char c = str.charAt(i);
        if(!(c >= '0' && c <= '9')){
            return i;
        }
        return verificadorInt(str, i + 1);
    }
    public static void X3(String str){
        int i = verificadorInt(str,0);
        if(i == str.length()){
            System.out.print("SIM ");
        }
        else{
            System.out.print("NAO ");
        }
    }

    public static void X4(String str){
        int point = 0;
        int i = verificadorReal(str, 0, point);
        if(i == str.length() && point <= 1){
            System.out.print("SIM");
        }
        else{
            System.out.print("NAO");
        }   
    }


}
