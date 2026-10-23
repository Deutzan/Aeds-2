#include <stdio.h>

typedef struct Celula{
    int elemento;
    Celula* prox;
}Celula;

Celula *novaCelula(int elemento) {
    Celula *nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}


int main() {





}
