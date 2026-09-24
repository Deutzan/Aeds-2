import java.util.Scanner;

public class Sum {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        //-- pede uma string --//
        String numeros = scan.nextLine();

        while(!(numeros.equals(""))){
        int resultado = somaDigitos(numeros);
        System.out.println(resultado);

        //-- dava dando problema porque o nextline so funciona se tiver uma outra linha disponivel --//
        //-- mas se for EOF ele da erro --//
        if(scan.hasNextLine()){
        numeros = scan.nextLine();
        }
        else{
            break;
        }
    }
        scan.close();
    }

    //-- metodo interativo --//
    public static int somaDigitos(String num){
            
            if(num.length() == 0){
                return 0; //-- condição de parada funciona ate a string ter 0 posições --//
            }
            //-- pega o valor do primeiro e passa o string para a 1 posição --//
            //-- e então entra no return que pega a soma ate dar a condição de parada --//
            int primeiro = Character.getNumericValue(num.charAt(0));
            String resto = num.substring(1);
            return primeiro + somaDigitos(resto);
        }
    }

    