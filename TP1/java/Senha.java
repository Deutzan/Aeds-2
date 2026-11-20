import java.util.Scanner;

public class Senha {
    /*Valida¸c˜ao de Senha - Crie um m´etodo iterativo que recebe uma string como parˆametro
e retorna true se a string ´e uma senha v´alida, ou false caso contr´ario. Uma senha ´e considerada
v´alida se cont´em pelo menos 8 caracteres, incluindo pelo menos uma letra mai´uscula, uma letra
min´uscula, um n´umero e um caractere especial (por exemplo, !, @, #, etc.). Na sa´ıda padr˜ao,
para cada linha de entrada, escreva uma linha de sa´ıda com SIM/N ˜AO indicando se a senha ´e
v´alida. Por exemplo, se a entrada for “Senha123!”s, a sa´ıda deve ser SIM.
*/
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String senha = scan.nextLine();

        while(!(senha.charAt(0) == 'F' && senha.charAt(1) == 'I' && senha.charAt(2) == 'M')){
        boolean resultado;
        resultado = Sub.str(senha);

        if (resultado) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
        
        senha = scan.nextLine();
    }
        scan.close();
    }


static class Sub{
    public static boolean str(String senha){
       int passou = 0; 
        if(senha.length() >= 8){
            passou++;
        }
        if(senha.matches(".*[A-Z].*")){
            passou++;
        }
        if(senha.matches(".*[a-z].*")){
            passou++;
        }
        if(senha.matches(".*[0-9].*")){
            passou++;
        }
        if(senha.matches(".*[!@#$%^&*()-+].*")){
            passou++;
        }

        boolean resul;
        if(passou == 5){
            resul = true;
            }
        else{
            resul = false;
        }
            return resul;
        }
       
    }
    }


