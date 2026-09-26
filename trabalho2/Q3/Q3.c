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

void formatVeiculo(Veiculos v, char* buffer);

void formatData(Data d, char* buffer);

void formatCombustivel(const char* combustivel, char* buffer);

int* LerIds(int* qtdIds);

bool idEstaNaLista(int id, int* ids, int qtdIds);


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

        // Remove o \n (usando strtok, que é permitido)
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

    sscanf(s,"%d,%29[^,],%39[^,],%d,%39[^,],%29[^,],%d,%lf,%21[^,],%34[^,],%lf,%lf,%lf,%5[^,],%10[^\n]",

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


// ==================== COMPARAR IGNORANDO MAIUSCULA/MINUSCULA ====================

// Implementação manual (sem depender de strcasecmp, que não é uma função padrão
// permitida): compara duas strings caractere a caractere, tratando 'A'-'Z' como
// equivalentes a 'a'-'z'. Retorna <0, 0 ou >0, igual ao strcmp.
int compararModeloIgnorandoCaixa(const char* a, const char* b) {

    int i = 0;

    while (a[i] != '\0' && b[i] != '\0') {

        char ca = a[i];
        char cb = b[i];

        if (ca >= 'A' && ca <= 'Z') {
            ca = ca + ('a' - 'A');
        }
        if (cb >= 'A' && cb <= 'Z') {
            cb = cb + ('a' - 'A');
        }

        if (ca != cb) {
            return ca - cb;
        }

        i++;
    }

    return a[i] - b[i];
}


// ==================== SELECTION SORT (por modelo) ====================

void selectionSortModelo(Veiculos* veiculos, int n) {

    for (int i = 0; i < n - 1; i++) {

        int menor = i;

        for (int j = i + 1; j < n; j++) {

            if (compararModeloIgnorandoCaixa(veiculos[j].modelo, veiculos[menor].modelo) < 0) {
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


// ==================== PARSE DATA ============== //

Data ParseData(char* s) {
    Data data;

    sscanf(s,"%d-%d-%d",&data.ano,&data.mes,&data.dia);

    return data;
}


// ==================== FORMAT DATA ====================

void formatData(Data d, char* buffer) {
    // dia/mes/ano
    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}


// ==================== FORMAT COMBUSTIVEL ====================

// Transforma "Gasoline;Electricity" em "[Gasoline,Electricity]"
// e "Gasoline" em "[Gasoline]"
void formatCombustivel(const char* combustivel, char* buffer) {

    char temp[30];
    sprintf(temp, "%s", combustivel);

    // Troca ';' por ','
    for (int i = 0; temp[i] != '\0'; i++) {
        if (temp[i] == ';') {
            temp[i] = ',';
        }
    }

    sprintf(buffer, "[%s]", temp);
}


// ==================== FORMAT VEICULO ====================

void formatVeiculo(Veiculos v, char* buffer) {

    char data[30];
    char combustivelFormatado[35];

    formatData(v.data_registro, data);
    formatCombustivel(v.combustivel, combustivelFormatado);

    sprintf(buffer,"[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria,
        combustivelFormatado,
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


// ==================== LER IDS DIGITADOS ====================

// Lê os ids digitados pelo usuário até encontrar o sentinela -1
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


// Verifica se um id está na lista de ids digitados
bool idEstaNaLista(int id, int* ids, int qtdIds) {

    for (int i = 0; i < qtdIds; i++) {
        if (ids[i] == id) {
            return true;
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

    // Lê os ids digitados (termina em -1)
    int qtdIds;
    int* ids = LerIds(&qtdIds);

    // Filtra somente os veículos cujo id foi digitado
    Veiculos* selecionados = malloc(qtdIds * sizeof(Veiculos));
    int qtdSelecionados = 0;

    for (int i = 0; i < n; i++) {
        if (idEstaNaLista(veiculos[i].id, ids, qtdIds)) {
            selecionados[qtdSelecionados] = veiculos[i];
            qtdSelecionados++;
        }
    }

    // Ordena apenas os selecionados
    selectionSortModelo(selecionados, qtdSelecionados);

    // Imprime os selecionados já ordenados
    for (int i = 0; i < qtdSelecionados; i++) {
        char buffer[300];
        formatVeiculo(selecionados[i], buffer);
        printf("%s\n", buffer);
    }

    free(veiculos);
    free(ids);
    free(selecionados);
    return 0;
}