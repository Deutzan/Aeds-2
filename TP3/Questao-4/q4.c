#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct Data {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct Veiculos {
    int id;
    char marca[22];
    char modelo[32];
    int ano;
    char categoria[30];
    char combustivel[30];
    int cilindros;
    double cilindrada;
    char transmissao[22];
    char tracao[22];
    double consumo_cidade;
    double consumo_estrada;
    double co2;
    bool turbo;
    Data data_registro;
} Veiculos;

// LISTA FLEXIVEL

typedef struct Celula {
    Veiculos elemento;
    struct Celula* prox;
} Celula;

typedef struct Lista {
    Celula* primeiro;   // celula cabeca
    Celula* ultimo;
    int tamanho;
} Lista;

// PROTOTIPOS

Veiculos* LerCSV(char* caminhoArquivo, int* n);
Veiculos* ParseVeiculo(char* s);
Data ParseData(char* s);
void formatVeiculo(Veiculos v, char* buffer);
void formatData(Data d, char* buffer);

void iniciarLista(Lista* l);
void inserirFim(Lista* l, Veiculos v);
void liberarLista(Lista* l);
void mostrarLista(Lista* l);
int compararIgnorandoCaso(char* a, char* b);
int menor(Veiculos* a, Veiculos* b);
void selecao(Lista* l);

// LISTA

void iniciarLista(Lista* l) {
    Celula* cabeca = malloc(sizeof(Celula));
    cabeca->prox = NULL;
    l->primeiro = cabeca;
    l->ultimo = cabeca;
    l->tamanho = 0;
}

void inserirFim(Lista* l, Veiculos v) {
    Celula* nova = malloc(sizeof(Celula));
    nova->elemento = v;
    nova->prox = NULL;
    l->ultimo->prox = nova;
    l->ultimo = nova;
    l->tamanho++;
}

void liberarLista(Lista* l) {
    Celula* atual = l->primeiro;
    while (atual != NULL) {
        Celula* prox = atual->prox;
        free(atual);
        atual = prox;
    }
    l->primeiro = l->ultimo = NULL;
    l->tamanho = 0;
}

void mostrarLista(Lista* l) {
    char buffer[500];
    for (Celula* i = l->primeiro->prox; i != NULL; i = i->prox) {
        formatVeiculo(i->elemento, buffer);
        printf("%s\n", buffer);
    }
}

// ORDENACAO

// compara strings ignorando maiusculas/minusculas
int compararIgnorandoCaso(char* a, char* b) {
    while (*a && *b) {
        char ca = (*a >= 'A' && *a <= 'Z') ? *a + 32 : *a;
        char cb = (*b >= 'A' && *b <= 'Z') ? *b + 32 : *b;
        if (ca != cb) return (unsigned char)ca - (unsigned char)cb;
        a++;
        b++;
    }
    return (unsigned char)*a - (unsigned char)*b;
}

// criterio: modelo ignorando caso (desempate por id)
int menor(Veiculos* a, Veiculos* b) {
    int c = compararIgnorandoCaso(a->modelo, b->modelo);
    if (c != 0) return c < 0;
    return a->id < b->id;
}

// Selecao em lista encadeada: troca os elementos (conteudo) das celulas
void selecao(Lista* l) {
    for (Celula* i = l->primeiro->prox; i != NULL && i->prox != NULL; i = i->prox) {
        Celula* min = i;
        for (Celula* j = i->prox; j != NULL; j = j->prox) {
            if (menor(&j->elemento, &min->elemento)) {
                min = j;
            }
        }
        if (min != i) {
            Veiculos tmp = i->elemento;
            i->elemento = min->elemento;
            min->elemento = tmp;
        }
    }
}

// LER CSV

Veiculos* LerCSV(char* caminhoArquivo, int* n) {
    FILE* arquivo = fopen(caminhoArquivo, "r");
    if (arquivo == NULL) {
        return NULL;
    }

    int capacidade = 100;
    Veiculos* veiculos = malloc(capacidade * sizeof(Veiculos));
    char linha[500];
    *n = 0;

    // Ignora o cabecalho
    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        linha[strcspn(linha, "\r\n")] = '\0';

        Veiculos* v = ParseVeiculo(linha);
        veiculos[*n] = *v;
        free(v);
        (*n)++;

        if (*n >= capacidade) {
            capacidade *= 2;
            veiculos = realloc(veiculos, capacidade * sizeof(Veiculos));
        }
    }

    fclose(arquivo);
    return veiculos;
}

// PARSE

Veiculos* ParseVeiculo(char* s) {
    Veiculos* v = malloc(sizeof(Veiculos));
    char* campos[30];
    int nc = 0;
    char* p = s;

    // separa os campos respeitando aspas
    while (nc < 30) {
        if (*p == '"') {
            p++;
            campos[nc++] = p;
            while (*p && *p != '"') p++;
            if (*p == '"') {
                *p = '\0';
                p++;
            }
            if (*p == ',') p++;
            else break;
        } else {
            campos[nc++] = p;
            while (*p && *p != ',') p++;
            if (*p == ',') {
                *p = '\0';
                p++;
            } else {
                break;
            }
        }
    }

    // se o combustivel tinha virgula sem aspas, junta de novo
    int extra = nc - 15;
    if (extra < 0) extra = 0;
    for (int k = 5; k < 5 + extra; k++) {
        campos[k][strlen(campos[k])] = ',';
    }

    #define CAMPO(i) campos[(i) <= 5 ? (i) : (i) + extra]

    v->id = atoi(CAMPO(0));
    strncpy(v->marca, CAMPO(1), sizeof(v->marca) - 1);
    v->marca[sizeof(v->marca) - 1] = '\0';
    strncpy(v->modelo, CAMPO(2), sizeof(v->modelo) - 1);
    v->modelo[sizeof(v->modelo) - 1] = '\0';
    v->ano = atoi(CAMPO(3));
    strncpy(v->categoria, CAMPO(4), sizeof(v->categoria) - 1);
    v->categoria[sizeof(v->categoria) - 1] = '\0';
    strncpy(v->combustivel, CAMPO(5), sizeof(v->combustivel) - 1);
    v->combustivel[sizeof(v->combustivel) - 1] = '\0';
    v->cilindros = atoi(CAMPO(6));
    v->cilindrada = atof(CAMPO(7));
    strncpy(v->transmissao, CAMPO(8), sizeof(v->transmissao) - 1);
    v->transmissao[sizeof(v->transmissao) - 1] = '\0';
    strncpy(v->tracao, CAMPO(9), sizeof(v->tracao) - 1);
    v->tracao[sizeof(v->tracao) - 1] = '\0';
    v->consumo_cidade = atof(CAMPO(10));
    v->consumo_estrada = atof(CAMPO(11));
    v->co2 = atof(CAMPO(12));
    v->turbo = strcmp(CAMPO(13), "true") == 0;
    v->data_registro = ParseData(CAMPO(14));

    #undef CAMPO
    return v;
}

Data ParseData(char* s) {
    Data data;
    sscanf(s, "%d-%d-%d", &data.ano, &data.mes, &data.dia);
    return data;
}

// FORMAT

void formatData(Data d, char* buffer) {
    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

void formatVeiculo(Veiculos v, char* buffer) {
    char data[30];
    formatData(v.data_registro, data);

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]",
            v.id, v.marca, v.modelo, v.ano, v.categoria, v.combustivel,
            v.cilindros, v.cilindrada, v.transmissao, v.tracao,
            v.consumo_cidade, v.consumo_estrada, v.co2,
            v.turbo ? "true" : "false", data);
}

// MAIN

int main() {
    int n;
    Veiculos* veiculos = LerCSV("/tmp/veiculos.csv", &n);
    if (veiculos == NULL) {
        return 1;
    }

    Lista lista;
    iniciarLista(&lista);

    int id;
    scanf("%d", &id);

    while (id != -1) {
        for (int i = 0; i < n; i++) {
            if (veiculos[i].id == id) {
                inserirFim(&lista, veiculos[i]);
                i = n;
            }
        }
        scanf("%d", &id);
    }

    selecao(&lista);
    mostrarLista(&lista);

    liberarLista(&lista);
    free(veiculos);
}
