#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


// ==================== STRUCT DATA ====================

typedef struct Data {
    int dia;
    int mes;
    int ano;
} Data;


// ==================== STRUCT VEICULOS ====================

typedef struct Veiculos {
    int id;
    char marca[30];
    char modelo[40];
    int ano;
    char categoria[40];
    char combustivel[30];
    int cilindros;
    double cilindrada;
    char transmissao[22];
    char tracao[35];
    double consumo_cidade;
    double consumo_estrada;
    double co2;
    bool turbo;
    Data data_registro;
} Veiculos;


// ==================== PROTOTIPOS ====================

Veiculos* LerCSV(char* caminhoArquivo, int* n);

Veiculos* ParseVeiculo(char* s);

Data ParseData(char* s);

int* LerIds(int* qtdIds);

bool idEstaNaLista(int id, int* ids, int qtdIds);

void selectionSortModelo(Veiculos* veiculos, int n);

bool pesquisaBinaria(Veiculos* veiculos, int n, char* modelo);


// ==================== LER CSV ====================

Veiculos* LerCSV(char* caminhoArquivo, int* n) {

    FILE* arquivo = fopen(caminhoArquivo, "r");

    if (arquivo == NULL) {
        return NULL;
    }

    int capacidade = 100;

    Veiculos* veiculos = malloc(capacidade * sizeof(Veiculos));

    char linha[500];

    *n = 0;

    // Ignora o cabeçalho
    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {

        // Remove o \n
        strtok(linha, "\n");

        Veiculos* v = ParseVeiculo(linha);

        veiculos[*n] = *v;

        free(v);

        (*n)++;

        // Aumenta o vetor se necessário
        if (*n >= capacidade) {
            capacidade *= 2;
            veiculos = realloc(veiculos, capacidade * sizeof(Veiculos));
        }
    }

    fclose(arquivo);

    return veiculos;
}


// ==================== PARSE VEICULO ====================

Veiculos* ParseVeiculo(char* s) {

    Veiculos* v = malloc(sizeof(Veiculos));

    char turbo[6];
    char data[11];

    sscanf(s,
        "%d,%29[^,],%39[^,],%d,%39[^,],%29[^,],%d,%lf,%21[^,],%34[^,],%lf,%lf,%lf,%5[^,],%10[^\n]",

        &v->id,
        v->marca,
        v->modelo,
        &v->ano,
        v->categoria,
        v->combustivel,
        &v->cilindros,
        &v->cilindrada,
        v->transmissao,
        v->tracao,
        &v->consumo_cidade,
        &v->consumo_estrada,
        &v->co2,
        turbo,
        data
    );

    v->turbo = strcmp(turbo, "true") == 0;

    v->data_registro = ParseData(data);

    return v;
}


// ==================== PARSE DATA ====================

Data ParseData(char* s) {

    Data data;

    sscanf(s, "%d-%d-%d",
        &data.ano,
        &data.mes,
        &data.dia
    );

    return data;
}


// ==================== LER IDS ====================

int* LerIds(int* qtdIds) {

    int capacidade = 100;

    int* ids = malloc(capacidade * sizeof(int));

    *qtdIds = 0;

    int id;

    while (scanf("%d", &id) == 1 && id != -1) {

        ids[*qtdIds] = id;

        (*qtdIds)++;

        if (*qtdIds >= capacidade) {

            capacidade *= 2;

            ids = realloc(ids, capacidade * sizeof(int));
        }
    }

    return ids;
}


// ==================== VERIFICAR ID ====================

bool idEstaNaLista(int id, int* ids, int qtdIds) {

    for (int i = 0; i < qtdIds; i++) {

        if (id == ids[i]) {
            return true;
        }
    }

    return false;
}


// ==================== SELECTION SORT ====================

void selectionSortModelo(Veiculos* veiculos, int n) {

    for (int i = 0; i < n - 1; i++) {

        int menor = i;

        for (int j = i + 1; j < n; j++) {

            if (strcmp(veiculos[j].modelo,
                       veiculos[menor].modelo) < 0) {

                menor = j;
            }
        }

        if (menor != i) {

            Veiculos temp = veiculos[i];

            veiculos[i] = veiculos[menor];

            veiculos[menor] = temp;
        }
    }
}


// ==================== PESQUISA BINÁRIA ====================

bool pesquisaBinaria(Veiculos* veiculos, int n, char* modelo) {

    int inicio = 0;
    int fim = n - 1;

    while (inicio <= fim) {

        int meio = (inicio + fim) / 2;

        int comparacao = strcmp(modelo, veiculos[meio].modelo);

        if (comparacao == 0) {

            return true;

        } else if (comparacao < 0) {

            fim = meio - 1;

        } else {

            inicio = meio + 1;
        }
    }

    return false;
}


// ==================== MAIN ====================

int main() {

    int n;

    // Lê todos os veículos do CSV
    Veiculos* veiculos = LerCSV("veiculosC.csv", &n);

    if (veiculos == NULL) {
        return 1;
    }


    // ==================== PRIMEIRA PARTE DA ENTRADA ====================

    int qtdIds;

    int* ids = LerIds(&qtdIds);


    // ==================== SELECIONAR VEÍCULOS ====================

    Veiculos* selecionados =
        malloc(qtdIds * sizeof(Veiculos));

    int qtdSelecionados = 0;

    for (int i = 0; i < n; i++) {

        if (idEstaNaLista(
                veiculos[i].id,
                ids,
                qtdIds)) {

            selecionados[qtdSelecionados] =
                veiculos[i];

            qtdSelecionados++;
        }
    }


    // ==================== ORDENAR ====================

    selectionSortModelo(
        selecionados,
        qtdSelecionados
    );


    // ==================== SEGUNDA PARTE ====================

    char modelo[100];

    // Ignora o \n que ficou depois do -1
    getchar();

    while (fgets(modelo, sizeof(modelo), stdin) != NULL) {

        strtok(modelo, "\n");

        // Sentinela da segunda parte
        if (strcmp(modelo, "FIM") == 0) {
            break;
        }

        // ==================== PESQUISA BINÁRIA ====================

        if (pesquisaBinaria(
                selecionados,
                qtdSelecionados,
                modelo)) {

            printf("SIM\n");

        } else {

            printf("NAO\n");
        }
    }


    // ==================== LIBERA MEMÓRIA ====================

    free(veiculos);

    free(ids);

    free(selecionados);

    return 0;
}