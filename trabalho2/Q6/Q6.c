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
    char combustivel[25];
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

// PARSE DATA

Data ParseData(char *s) {
    Data data;
    sscanf(s, "%d-%d-%d",&data.ano,&data.mes,&data.dia);
    return data;
}

// PARSE VEICULO 
Veiculos ParseVeiculo(char *s) {
    Veiculos v;

    char campos[15][100];
    char campo[100];

    int quantidade = 0;
    int pos = 0;
    int dentroColchetes = 0;

    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] == '[') {
            dentroColchetes = 1;
        }

        if (s[i] == ']') {
            dentroColchetes = 0;
        }

        if (s[i] == ',' && dentroColchetes == 0) {
            campo[pos] = '\0';
            strcpy(campos[quantidade], campo);
            quantidade++;
            pos = 0;

        } else {
            campo[pos] = s[i];
            pos++;
        }
    }
    campo[pos] = '\0';

    strcpy(campos[quantidade], campo);
    quantidade++;
    v.id = atoi(campos[0]);
    strcpy(v.marca, campos[1]);
    strcpy(v.modelo, campos[2]);
    v.ano = atoi(campos[3]);
    strcpy(v.categoria, campos[4]);
    strcpy(v.combustivel, campos[5]);
    v.cilindros = atoi(campos[6]);
    v.cilindrada = atof(campos[7]);
    strcpy(v.transmissao, campos[8]);
    strcpy(v.tracao, campos[9]);
    v.consumo_cidade = atof(campos[10]);
    v.consumo_estrada = atof(campos[11]);
    v.co2 = atof(campos[12]);
    v.turbo = strcmp(campos[13], "true") == 0;
    v.data_registro = ParseData(campos[14]);

    return v;
}

// LER CSV 

Veiculos* LerCSV(char *caminhoArquivo, int *n) {
    FILE *arquivo = fopen(caminhoArquivo, "r");

    if (arquivo == NULL) {
        return NULL;
    }
    int capacidade = 100;

    Veiculos *veiculos = malloc(capacidade * sizeof(Veiculos));
    char linha[500];
    *n = 0;

    // Ignora o cabeçalho
    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        linha[strcspn(linha, "\n")] = '\0';
        veiculos[*n] = ParseVeiculo(linha);
        (*n)++;

        if (*n >= capacidade) {
            capacidade *= 2;
            veiculos = realloc(veiculos,capacidade * sizeof(Veiculos));
        }
    }
    fclose(arquivo);
    return veiculos;
}

// BUSCAR POR ID

Veiculos* buscarPorId(Veiculos *veiculos, int n, int id) {
    for (int i = 0; i < n; i++) {
        if (veiculos[i].id == id) {
            return &veiculos[i];
        }
    }
    return NULL;
}

// COUNTING SORT 

void countingSortAno(Veiculos *veiculos, int n, int exp) {
    Veiculos *ordenados = malloc(n * sizeof(Veiculos));
    int contagem[10] = {0};

    // Conta os dígitos
    for (int i = 0; i < n; i++) {
        int digito = (veiculos[i].ano / exp) % 10;
        contagem[digito]++;
    }

    // Soma acumulada
    for (int i = 1; i < 10; i++) {
        contagem[i] += contagem[i - 1];
    }

    // Ordena de trás para frente
    // para manter estabilidade
    for (int i = n - 1; i >= 0; i--) {
        int digito = (veiculos[i].ano / exp) % 10;
        ordenados[contagem[digito] - 1] = veiculos[i];
        contagem[digito]--;
    }
    // Copia de volta
    for (int i = 0; i < n; i++) {
        veiculos[i] = ordenados[i];
    }
    free(ordenados);
}

// RADIX SORT
void radixSortAno(Veiculos *veiculos, int n) {

    if (n <= 0) {
        return;
    }
    int maior = veiculos[0].ano;

    for (int i = 1; i < n; i++) {
        if (veiculos[i].ano > maior) {
            maior = veiculos[i].ano;
        }
    }

    for (int exp = 1;
         maior / exp > 0;
         exp *= 10) 
        {
        countingSortAno(veiculos,n,exp);
    }
}

// FORMAT DATA 
void formatData(Data *d, char *buffer) {
    sprintf(buffer,"%02d/%02d/%04d",d->dia,d->mes, d->ano);
}

// FORMAT VEICULO
void formatVeiculo(Veiculos *v, char *buffer) {
    char data[30];

    formatData(&v->data_registro,data);

    sprintf(buffer,"[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]",
        v->id,
        v->marca,
        v->modelo,
        v->ano,
        v->categoria,
        v->combustivel,
        v->cilindros,
        v->cilindrada,
        v->transmissao,
        v->tracao,
        v->consumo_cidade,
        v->consumo_estrada,
        v->co2,
        v->turbo ? "true" : "false",
        data
    );
}

// ==================== MAIN ====================

int main() {
    int n;

    Veiculos *veiculos = LerCSV("veiculosC.csv", &n);

    if (veiculos == NULL) {
        return 1;
    }
    // Vetor somente com os veículos
    // informados na entrada
    Veiculos selecionados[50];
    int quantidade = 0;
    int id;

    // Lê os IDs até encontrar -1
    while (scanf("%d", &id) == 1 && id != -1) {
        Veiculos *veiculo = buscarPorId(veiculos, n,id);

        if (veiculo != NULL) {
            selecionados[quantidade] = *veiculo;
            quantidade++;
        }
    }

    // Ordena somente os 50 veículos
    // selecionados pelo ano
    radixSortAno(selecionados,quantidade);

    // Imprime os veículos ordenados
    for (int i = 0; i < quantidade; i++) {
        char buffer[500];

        formatVeiculo(&selecionados[i],buffer);
        printf("%s\n", buffer);
    }
    free(veiculos);
}