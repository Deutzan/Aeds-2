#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_VEICULOS 1000
#define TAM_FILA 5

// =====================================================
// STRUCT DATA
// =====================================================

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;


// =====================================================
// STRUCT VEICULOS
// =====================================================

typedef struct {
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


// =====================================================
// STRUCT FILA
// =====================================================

typedef struct {
    Veiculos array[TAM_FILA];

    int primeiro;
    int ultimo;
    int tamanho;

} Fila;


// =====================================================
// PARSE DATA
// =====================================================

Data ParseData(char *s) {

    Data data;

    char *token;

    token = strtok(s, "-");
    data.ano = atoi(token);

    token = strtok(NULL, "-");
    data.mes = atoi(token);

    token = strtok(NULL, "-");
    data.dia = atoi(token);

    return data;
}


// =====================================================
// FORMAT DATA
// =====================================================

void formatData(Data data) {

    printf("%02d/%02d/%04d",
           data.dia,
           data.mes,
           data.ano);
}


// =====================================================
// PARSE VEICULO
// =====================================================

Veiculos ParseVeiculo(char *s) {

    Veiculos v;
    char *token;

    token = strtok(s, ",");
    v.id = atoi(token);

    token = strtok(NULL, ",");
    strcpy(v.marca, token);

    token = strtok(NULL, ",");
    strcpy(v.modelo, token);

    token = strtok(NULL, ",");
    v.ano = atoi(token);

    token = strtok(NULL, ",");
    strcpy(v.categoria, token);

    token = strtok(NULL, ",");
    strcpy(v.combustivel, token);

    token = strtok(NULL, ",");
    v.cilindros = atoi(token);

    token = strtok(NULL, ",");
    v.cilindrada = atof(token);

    token = strtok(NULL, ",");
    strcpy(v.transmissao, token);

    token = strtok(NULL, ",");
    strcpy(v.tracao, token);

    token = strtok(NULL, ",");
    v.consumo_cidade = atof(token);

    token = strtok(NULL, ",");
    v.consumo_estrada = atof(token);

    token = strtok(NULL, ",");
    v.co2 = atof(token);

    token = strtok(NULL, ",");
    v.turbo = (strcmp(token, "true") == 0);

    token = strtok(NULL, "\n");
    v.data_registro = ParseData(token);

    return v;
}


// =====================================================
// LER CSV
// =====================================================

Veiculos *LerCSV(char *caminhoArquivo, int *n) {

    FILE *arquivo = fopen(caminhoArquivo, "r");

    if (arquivo == NULL) {
        printf("Erro ao abrir arquivo.\n");
        exit(1);
    }

    Veiculos *veiculos = (Veiculos *) malloc(MAX_VEICULOS * sizeof(Veiculos));

    char linha[500];

    *n = 0;

    // Pular cabeçalho
    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {

        linha[strcspn(linha, "\n")] = '\0';

        if (strlen(linha) > 0) {

            veiculos[*n] = ParseVeiculo(linha);

            (*n)++;
        }
    }

    fclose(arquivo);
    return veiculos;
}


// =====================================================
// BUSCAR VEICULO POR ID
// =====================================================

Veiculos *buscarPorId(
    Veiculos *veiculos,int n,int id) {
    for (int i = 0; i < n; i++) {

        if (veiculos[i].id == id) {
            return &veiculos[i];
        }
    }
    return NULL;
}


// =====================================================
// FORMATAR COMBUSTIVEL
// =====================================================

void formatCombustivel(char *combustivel) {

    printf("[");

    for (int i = 0; combustivel[i] != '\0'; i++) {

        if (combustivel[i] == ';') {
            printf(",");
        } else {
            printf("%c", combustivel[i]);
        }
    }
    printf("]");
}


// =====================================================
// FORMATAR VEICULO
// =====================================================

void formatVeiculo(Veiculos v) {

    printf(
        "[%d ## %s ## %s ## %d ## %s ## ",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria
    );

    formatCombustivel(v.combustivel);

    printf(
        " ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## ",
        v.cilindros,
        v.cilindrada,
        v.transmissao,
        v.tracao,
        v.consumo_cidade,
        v.consumo_estrada,
        v.co2,
        v.turbo ? "true" : "false"
    );

    formatData(v.data_registro);

    printf("]\n");
}

// =====================================================
// INICIALIZAR FILA
// =====================================================

void inicializarFila(Fila *fila) {

    fila->primeiro = 0;
    fila->ultimo = 0;
    fila->tamanho = 0;
}


// =====================================================
// FILA CHEIA
// =====================================================

bool filaCheia(Fila *fila) {

    return fila->tamanho == TAM_FILA;
}

// =====================================================
// FILA VAZIA
// =====================================================

bool filaVazia(Fila *fila) {

    return fila->tamanho == 0;
}

// =====================================================
// REMOVER DA FILA
// =====================================================

Veiculos remover(Fila *fila) {

    Veiculos removido = fila->array[fila->primeiro];

    fila->primeiro = (fila->primeiro + 1) % TAM_FILA;

    fila->tamanho--;

    return removido;
}


// =====================================================
// INSERIR NA FILA
// =====================================================

void inserir(Fila *fila, Veiculos veiculo) {

    // Se estiver cheia, remove antes de inserir
    if (filaCheia(fila)) {

        Veiculos removido = remover(fila);

        printf("(R)%s %s\n",removido.marca,removido.modelo);
    }

    fila->array[fila->ultimo] = veiculo;

    fila->ultimo = (fila->ultimo + 1) % TAM_FILA;

    fila->tamanho++;
}


// =====================================================
// MOSTRAR FILA
// =====================================================

void mostrarFila(Fila *fila) {

    for (int i = 0; i < fila->tamanho; i++) {

        int posicao =
            (fila->primeiro + i) % TAM_FILA;

        formatVeiculo(fila->array[posicao]);
    }
}


// MAIN

int main() {
    int n;

    Veiculos *veiculos = LerCSV("veiculosJ.csv", &n);
    Fila fila;
    inicializarFila(&fila);

    // PRIMEIRA PARTE DA ENTRADA

    int id;

    scanf("%d", &id);

    while (id != -1) {
        Veiculos *veiculo = buscarPorId(veiculos, n, id);
        if (veiculo != NULL) {
            inserir(&fila, *veiculo);
        }
        scanf("%d", &id);
    }

    // SEGUNDA PARTE DA ENTRADA

    int quantidade;
    scanf("%d", &quantidade);

    for (int i = 0; i < quantidade; i++) {
        char comando;
        scanf(" %c", &comando);

        // INSERIR
        if (comando == 'I') {

            int idVeiculo;

            scanf("%d", &idVeiculo);

            Veiculos *veiculo = buscarPorId(veiculos,n,idVeiculo);

            if (veiculo != NULL) {

                inserir(&fila, *veiculo);
            }
        }
        // REMOVER
        else if (comando == 'R') {
            if (!filaVazia(&fila)) {
                Veiculos removido = remover(&fila);
                printf("(R)%s %s\n",removido.marca,removido.modelo);
            }
        }
    }

    mostrarFila(&fila);
    free(veiculos);
}