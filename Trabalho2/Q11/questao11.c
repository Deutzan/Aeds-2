//a
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_VEICULOS 1000

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

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

typedef struct Celula {
    Veiculos elemento;
    struct Celula *prox;
} Celula;

typedef struct {
    Celula *primeiro;
    Celula *ultimo;
    int tamanho;
} Lista;



// PARSE DATA
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

// FORMAT DATA
void formatData(Data data) {
    printf("%02d/%02d/%04d",data.dia, data.mes,data.ano);
}

// PARSE VEICULO
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

// LER CSV
Veiculos *LerCSV(char *caminhoArquivo, int *n) {
    FILE *arquivo = fopen(caminhoArquivo, "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir arquivo.\n");
        exit(1);
    }
    Veiculos *veiculos =(Veiculos *) malloc(MAX_VEICULOS * sizeof(Veiculos));

    char linha[500];
    *n = 0;

    // Pula o cabeçalho
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

// BUSCAR POR ID
Veiculos *buscarPorId(
    Veiculos *veiculos,int n,int id) {

    for (int i = 0; i < n; i++) {
        if (veiculos[i].id == id) {
            return &veiculos[i];
        }
    }
    return NULL;
}

// FORMAT COMBUSTIVEL
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

// FORMATAR VEICULO
void formatVeiculo(Veiculos v) {
    printf( "[%d ## %s ## %s ## %d ## %s ## ",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria
    );
    formatCombustivel(v.combustivel);

    printf( " ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## ",
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

// INICIALIZAR LISTA
void inicializarLista(Lista *lista) {
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}

// CRIAR CELULA
Celula *criarCelula(Veiculos veiculo) {
    Celula *nova = (Celula *) malloc(sizeof(Celula));
    nova->elemento = veiculo;
    nova->prox = NULL;
    return nova;
}

// INSERIR NO INÍCIO
void inserirInicio(Lista *lista,Veiculos veiculo) {
    Celula *nova = criarCelula(veiculo);
    nova->prox = lista->primeiro;
    lista->primeiro = nova;

    if (lista->tamanho == 0) {
        lista->ultimo = nova;
    }
    lista->tamanho++;
}

// INSERIR NO FIM
void inserirFim(Lista *lista,Veiculos veiculo) {
    Celula *nova = criarCelula(veiculo);
    if (lista->tamanho == 0) {
        lista->primeiro = nova;
        lista->ultimo = nova;
    } 
    else {
        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }
    lista->tamanho++;
}

// INSERIR EM UMA POSIÇÃO
void inserir(Lista *lista,Veiculos veiculo,int posicao) {
    if (posicao < 0 || posicao > lista->tamanho) {
        return;
    }

    if (posicao == 0) {
        inserirInicio(lista, veiculo);
        return;
    }

    if (posicao == lista->tamanho) {
        inserirFim(lista, veiculo);
        return;
    }

    Celula *anterior = lista->primeiro;
    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }

    Celula *nova = criarCelula(veiculo);
    nova->prox = anterior->prox;
    anterior->prox = nova;
    lista->tamanho++;
}

// REMOVER DO INÍCIO
Veiculos removerInicio(Lista *lista) {
    Veiculos vazio = {0};

    if (lista->tamanho == 0) {
        return vazio;
    }

    Celula *removida = lista->primeiro;
    Veiculos veiculo = removida->elemento;

    lista->primeiro = removida->prox;
    lista->tamanho--;

    if (lista->tamanho == 0) {
        lista->ultimo = NULL;
    }

    free(removida);
    return veiculo;
}

// REMOVER DO FIM
Veiculos removerFim(Lista *lista) {
    Veiculos vazio = { 0 };
    if (lista->tamanho == 0) {
        return vazio;
    }

    // Só existe um elemento
    if (lista->tamanho == 1) {
        Veiculos veiculo = lista->primeiro->elemento;
        free(lista->primeiro);
        lista->primeiro = NULL;
        lista->ultimo = NULL;
        lista->tamanho = 0;
        return veiculo;
    }
    Celula *anterior = lista->primeiro;

    while (anterior->prox != lista->ultimo) {
        anterior = anterior->prox;
    }

    Veiculos veiculo = lista->ultimo->elemento;
    free(lista->ultimo);
    lista->ultimo = anterior;
    lista->ultimo->prox = NULL;
    lista->tamanho--;
    return veiculo;
}

// REMOVER DE UMA POSIÇÃO
Veiculos remover(Lista *lista,int posicao) {
    Veiculos vazio = {0};

    if (posicao < 0 || posicao >= lista->tamanho) {
        return vazio;
    }
    if (posicao == 0) {
        return removerInicio(lista);
    }
    if (posicao == lista->tamanho - 1) {
        return removerFim(lista);
    }
    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }
    Celula *removida = anterior->prox;

    Veiculos veiculo = removida->elemento;

    anterior->prox = removida->prox;
    free(removida);
    lista->tamanho--;
    return veiculo;
}

// MOSTRAR LISTA

void mostrar(Lista *lista) {
    Celula *atual = lista->primeiro;
    while (atual != NULL) {
        formatVeiculo(atual->elemento);
        atual = atual->prox;
    }
}

// LIBERAR LISTA
void liberarLista(Lista *lista) {
    Celula *atual = lista->primeiro;

    while (atual != NULL) {
        Celula *proxima = atual->prox;
        free(atual);
        atual = proxima;
    }
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}

int main() {
    int n;

    Veiculos *veiculos = LerCSV("veiculosJ.csv", &n);
    Lista lista;

    inicializarLista(&lista);

    // PRIMEIRA PARTE DA ENTRADA
    int id;
    scanf("%d", &id);

    while (id != -1) {
        Veiculos *veiculo = buscarPorId(veiculos,n,id);
        if (veiculo != NULL) {
            inserirFim(&lista,*veiculo);
        }
        scanf("%d", &id);
    }

    // SEGUNDA PARTE DA ENTRADA
    int quantidade;
    scanf("%d", &quantidade);

    for (int i = 0; i < quantidade; i++) {
        char comando[3];
        scanf("%s", comando);

        // INSERIR
        if (strcmp(comando, "II") == 0) {
            int idVeiculo;
            scanf("%d", &idVeiculo);
            Veiculos *veiculo = buscarPorId(veiculos,n,idVeiculo);

            if (veiculo != NULL) {
                inserirInicio(&lista,*veiculo);
            }
        }

        else if (strcmp(comando, "I*") == 0) {
            int posicao;
            int idVeiculo;

            scanf("%d %d",&posicao,&idVeiculo);
            Veiculos *veiculo = buscarPorId(veiculos,n,idVeiculo);

            if (veiculo != NULL) {
                inserir(&lista,*veiculo,posicao );
            }
        }

        else if (strcmp(comando, "IF") == 0) {
            int idVeiculo;
            scanf("%d", &idVeiculo);
            Veiculos *veiculo = buscarPorId(veiculos,n,idVeiculo);

            if (veiculo != NULL) {
                inserirFim(&lista,*veiculo);
            }
        }

        // REMOVE DO INÍCIO
        else if (strcmp(comando, "RI") == 0) {
            if (lista.tamanho > 0) {
                Veiculos removido =removerInicio(&lista);
                printf("(R)%s %s\n",removido.marca,removido.modelo);
            }
        }

        // REMOVE UMA POSIÇÃO
        else if (strcmp(comando, "R*") == 0) {
            int posicao;
            scanf("%d", &posicao);

            if (posicao >= 0 &&posicao < lista.tamanho) {
                Veiculos removido = remover( &lista, posicao);
                printf("(R)%s %s\n",removido.marca, removido.modelo);
            }
        }

        else if (strcmp(comando, "RF") == 0) {
            if (lista.tamanho > 0) {
                Veiculos removido =removerFim(&lista);
                printf("(R)%s %s\n",removido.marca,removido.modelo);
            }
        }
    }
    mostrar(&lista);
    liberarLista(&lista);
    free(veiculos);
}
