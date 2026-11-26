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

typedef struct Fila{
    Celula* primeiro;
    Celula* ultimo;
}Fila;

void Mostrar(Fila f){
        Celula* tmp = f.primeiro->prox;
        printf("Fila: ");
        while(tmp != NULL){
            if(tmp->prox == NULL){
                printf("[%d]--> NULL", tmp->elemento);     
            }else{
                printf("[%d]--> ", tmp->elemento);
            }
            tmp = tmp->prox; 
        }
        printf("\n");
}


void Inserir(Fila* f, int elemento){
    Celula* nova = novaCelula(elemento);
    if(f->primeiro == NULL){
        f->primeiro = nova;
        f->ultimo = nova;
    }else{
        f->ultimo->prox = nova;
        f->ultimo = nova;
    }
}
void RemoverInicio(Fila* f){
    if(f->primeiro == NULL){
        printf("Fila vazia!\n");
        return;
    }
    Celula* tmp = f->primeiro;
    f->primeiro = f->primeiro->prox;
    free(tmp);
}

void RemoverUltimo(Fila* f){
    if(f->primeiro == NULL){
        printf("Fila vazia!\n");
        return;
    }
    if(f->primeiro == f->ultimo){
        free(f->primeiro);
        f->primeiro = NULL;
        f->ultimo = NULL;
        return;
    }
    Celula* tmp = f->primeiro;
    while(tmp->prox != f->ultimo){
        tmp = tmp->prox;
    }
    free(f->ultimo);
    f->ultimo = tmp;
    f->ultimo->prox = NULL;
}

int main() {

    Fila* fila = (Fila*) malloc(sizeof(Fila));
    
    fila->Inserir(2);
    fila->Inserir(5);
    fila->Inserir(9);
    fila->Inserir(1);
    fila->RemoverInicio();
    fila->RemoverUltimo();
    fila->RemoverPos(2);
    fila->Mostrar();


}
