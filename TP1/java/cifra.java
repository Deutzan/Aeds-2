import java.util.Scanner;

public class cifra {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        String pal = scan.nextLine();

        while(!(pal.equals("FIM"))){
            String cifrado = Cif.cifrar(pal);
            System.out.println(cifrado);
            
            pal = scan.nextLine();
        }
        scan.close();
    }

    static class Cif {
        public static String cifrar(String pal){
            char[] cifra = pal.toCharArray();
            for(int i = 0; i < pal.length(); i++){
                char c = cifra[i];
                cifra[i] = (char) ((int) c + 3);
            }
            String cifrado = new String(cifra);
            return cifrado;
        }
    }

}

    
