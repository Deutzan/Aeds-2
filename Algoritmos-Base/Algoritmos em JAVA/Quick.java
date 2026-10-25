import java.util.Scanner;

class Quick {
    public static void main(String [] args){
    Scanner scan = new Scanner(System.in);
    
    int n = scan.nextInt();
    int[] vetor = new int[n];
    
    // contrução
    for(int i = 0;i < n;i++){
        vetor[i] = scan.nextInt();
    }
    Mostrar(vetor,n);

    QuickSort(vetor,0,n - 1);

    Mostrar(vetor,n);

    scan.close();
    }
    
public static void Mostrar(int[] vetor,int n){
    System.out.print("[");
    for(int i = 0;i < n;i++){
        if(i < n-1){
            System.out.print(vetor[i] + ", ");}
        else{
            System.out.print(vetor[i]);}
    }
    System.out.println("]");
    }

public static void QuickSort(int[] vetor,int esq,int dir){
    int i = esq, j = dir, pivo = vetor[(esq+dir)/2];
    while (i <= j) {
        while (vetor[i] < pivo)
            i++;

        while (vetor[j] > pivo)
            j--;

        if (i <= j){ 
            int tmp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = tmp;
        i++; j--; }
    }

    if (esq < j)
    QuickSort(vetor,esq,j);
    if (i < dir)
    QuickSort(vetor,i,dir);

    }
}