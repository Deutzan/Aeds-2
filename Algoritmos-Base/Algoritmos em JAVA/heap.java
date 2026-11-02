
public class heap {
    public static void main(String[] args) {
        int[] vetor = {5, 3, 8, 4, 2, 7, 1, 10, 6, 9,15,35,32,6778,213,4,1,34,3};

        heapsort(vetor);

        mostrar(vetor);
    }

    public static void heapsort(int[] vetor){
        construir(vetor.length - 1, vetor);
    
        int tam = vetor.length - 1;
        while(tam > 1){
            swap(1, tam, vetor);
            tam--;
            reconstruir(1, tam, vetor);
        }
    }

    public static void construir(int n, int[] vet){
        for(int i = n / 2; i >= 1; i--){
            reconstruir(i, n, vet);
        }
    }

    public static void reconstruir(int i, int tam, int[] vet){
        while(hasFilho(i, tam)){
            int filho = getMaiorFilho(i, tam, vet);

            if(vet[i] < vet[filho]){
                swap(i, filho, vet);
                i = filho;
            } else {
                break;
            }
        }
    }

    public static boolean hasFilho(int i, int tam){
        return 2*i <= tam;
    }

    public static int getMaiorFilho(int i, int tam, int[] vet){
        if(2*i == tam || vet[2*i] > vet[2*i + 1]){
            return 2*i;
        } else {
            return 2*i + 1;
        }
    }

    public static void swap(int i, int j, int[] vetor){
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

    public static void mostrar(int[] vetor){
        System.out.print("[");
        for(int i = 1; i < vetor.length; i++){
            if(i < vetor.length - 1){
                System.out.print(vetor[i] + ", ");
            } else {
                System.out.print(vetor[i]);
            }
        }
        System.out.println("]");
    }
}


