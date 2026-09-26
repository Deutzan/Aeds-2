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
    char marca[22];
    char modelo[32];
    int ano;
    char categoria[30];
    char combustivel[9];
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


// ==================== PROTOTIPOS ====================

Veiculos* LerCSV(char* caminhoArquivo, int* n);

Veiculos* ParseVeiculo(char* s);

Data ParseData(char* s);

void formatVeiculo(Veiculos v, char* buffer);

void formatData(Data d, char* buffer);


// ==================== LER CSV ====================

Veiculos* LerCSV(char* caminhoArquivo, int* n) {

    FILE* arquivo = fopen(caminhoArquivo, "r");

    if (arquivo == NULL) {
        //printf("ERRO: nao foi possivel abrir o arquivo: %s\n", caminhoArquivo);
        return NULL;
    }
        //printf("Arquivo aberto com sucesso!\n");

        
    int capacidade = 100;

    Veiculos* veiculos = malloc(capacidade * sizeof(Veiculos));

    char linha[500];

    *n = 0;


    // Ignora o cabeçalho
    fgets(linha, sizeof(linha), arquivo);


    while (fgets(linha, sizeof(linha), arquivo) != NULL) {

        // Remove o \n
        linha[strcspn(linha, "\n")] = '\0';

        Veiculos* v = ParseVeiculo(linha);

        veiculos[*n] = *v;

        free(v);
        (*n)++;


        // Aumenta o vetor se necessário
        if (*n >= capacidade) {

            capacidade *= 2;

            veiculos = realloc(
                veiculos,
                capacidade * sizeof(Veiculos)
            );
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

    sscanf(s,"%d,%21[^,],%31[^,],%d,%29[^,],%8[^,],%d,%lf,%21[^,],%21[^,],%lf,%lf,%lf,%5[^,],%10[^\n]",

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

void countingSortAno(Veiculos* veiculos, int n, int exp) {
    Veiculos* ordenados = malloc(n * sizeof(Veiculos));
    int contagem[10] = {0};

    for (int i = 0; i < n; i++) {
        int digito = (veiculos[i].ano / exp) % 10;
        contagem[digito]++;
    }

    for (int i = 1; i < 10; i++) {
        contagem[i] += contagem[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
        int digito = (veiculos[i].ano / exp) % 10;
        ordenados[contagem[digito] - 1] = veiculos[i];
        contagem[digito]--;
    }

    for (int i = 0; i < n; i++) {
        veiculos[i] = ordenados[i];
    }

    free(ordenados);
}

void radixSortAno(Veiculos* veiculos, int n) {
    int maior = veiculos[0].ano;

    for (int i = 1; i < n; i++) {
        if (veiculos[i].ano > maior) {
            maior = veiculos[i].ano;
        }
    }

    for (int exp = 1; maior / exp > 0; exp *= 10) {
        countingSortAno(veiculos, n, exp);
    }
}


// ==================== PARSE DATA ============== //

Data ParseData(char* s) {
    Data data;

    sscanf(s,"%d-%d-%d",&data.ano,&data.mes,&data.dia);

    return data;
}


// ==================== FORMAT DATA ====================

void formatData(Data d, char* buffer) {
    sprintf(buffer, "%04d/%02d/%02d",d.ano,d.mes,d.dia );
}


// ==================== FORMAT VEICULO ====================

void formatVeiculo(Veiculos v, char* buffer) {

    char data[30];


    formatData(v.data_registro,data);


    sprintf(buffer,"[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria,
        v.combustivel,
        v.cilindros,
        v.cilindrada,
        v.transmissao,
        v.tracao,
        v.consumo_cidade,
        v.consumo_estrada,
        v.co2,
        v.turbo ? "true" : "false",
        data
    );
}


// ==================== MAIN ====================

int main() {
    int n;

    Veiculos* veiculos = LerCSV("veiculosC.csv", &n);

    if (veiculos == NULL) {
        return 1;
    }
    
    radixSortAno(veiculos, n);

    for (int i = 0; i < n; i++) {
    char buffer[500];

    formatVeiculo(veiculos[i], buffer);

    printf("%s\n", buffer);
}

free(veiculos);
}
