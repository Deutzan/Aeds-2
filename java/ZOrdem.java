public class ZOrdem {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int [] vetor = {5, 2, 9, 1, 5, 6, 8 ,1 , 1 ,0 ,1 ,3};
        
        selectionSort(vetor);
        insertionSort(vetor);
    }

    public static void trocar(int[] v1, int menor, int i) {
    int temp = v1[menor];
    v1[menor] = v1[i];
    v1[i] = temp;
    }  

    public static void selectionSort(int[] v1) {
        int n = v1.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
                for (int j = i + 1; j < n; j++) {
                    if(v1[menor] > v1[j]) {
                    menor = j;
                }
            }
            trocar(v1,menor,i);
        }
        System.out.println("seleção: ");
        for (int i = 0; i < v1.length; i++) {
            System.out.print(v1[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] v2) {
        for (int i = 1; i < v2.length; i++) {
            int temp = v2[i];
            int j = i - 1;
            while (j >= 0 && v2[j] > temp) {
                v2[j + 1] = v2[j];
                j--;
            }
            v2[j + 1] = temp;
        }
        System.out.println("inserção: ");
        for (int i = 0; i < v2.length; i++) {
            System.out.print(v2[i] + " ");
        }

    }

}

