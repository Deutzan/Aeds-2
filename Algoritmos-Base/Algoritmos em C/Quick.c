#include <stdio.h>
#include <stdlib.h>

void Mostrar(int vetor[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        if (i < n - 1) {
            printf("%d, ", vetor[i]);
        } else {
            printf("%d", vetor[i]);
        }
    }
    printf("]\n");
}

void QuickSort(int vetor[], int esq, int dir) {
    int i = esq, j = dir;
    int pivo = vetor[(esq + dir) / 2];

    while (i <= j) {
        while (vetor[i] < pivo) i++;
        while (vetor[j] > pivo) j--;

        if (i <= j) {
            int tmp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = tmp;
            i++;
            j--;
        }
    }

    if (esq < j) QuickSort(vetor, esq, j);
    if (i < dir) QuickSort(vetor, i, dir);
}

int main() {
    int n;
    scanf("%d", &n);

    int *vetor = malloc(n * sizeof(int));

    for (int i = 0; i < n; i++) {
        scanf("%d", &vetor[i]);
    }

    Mostrar(vetor, n);

    QuickSort(vetor, 0, n - 1);

    Mostrar(vetor, n);

    free(vetor);
}
