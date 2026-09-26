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

    Veiculos* veiculos = LerCSV("java_2/veiculosJ.csv",&n);

    if (veiculos == NULL) {
        return 1;
    }

    int id;
    scanf("%d", &id);

    while (id != -1) {

        for (int i = 0; i < n; i++) {

            if (veiculos[i].id == id) {
                char buffer[500];
                formatVeiculo(veiculos[i], buffer);
                printf("%s\n",buffer);
                i = n;
            }
        }
        scanf("%d", &id);
    }
    free(veiculos);
}