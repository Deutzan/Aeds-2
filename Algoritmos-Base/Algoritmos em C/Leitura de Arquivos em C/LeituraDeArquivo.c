#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Coisas importantes para se lembrar:

1) Para abrir um arquivo em C, usamos fopen():

    FILE *arquivo = fopen("pub.in", "r");

    "Mostra qual arquivo será lido; o r significa leitura."

    Em Java, usamos:
        FileReader entrada = new FileReader("pub.in");
        BufferedReader ler = new BufferedReader(entrada);

    (Em Java, FileReader + BufferedReader fazem essa função)


2) Para verificar se o arquivo foi aberto corretamente:

    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    "Verifica se houve erro ao abrir o arquivo."

    Em Java, normalmente usamos try/catch para tratar erros de leitura.

    (Em Java, utiliza-se try/catch com IOException)


3) Para armazenar uma linha que está sendo lida:

    char linha[200];
    fgets(linha, sizeof(linha), arquivo);

    "O fgets() lê uma linha do arquivo e salva o conteúdo em linha."

    Em Java, usamos:
        String linha = ler.readLine();

    (Em Java, utiliza-se readLine())


4) Para fazer a leitura até EOF:

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {

        // Os sscanf

    }

    "Enquanto o fgets() conseguir ler uma linha, o while continua.
     Quando não conseguir ler outra linha, retorna NULL."

    Em Java, usamos:
        while ((linha = ler.readLine()) != null) {

            // Os parses

        }

    (Em Java, utiliza-se readLine() != null)


5) Para separar os dados e converter os tipos:

    Em C, usamos sscanf():

        int nivel;
        char nome[30];
        char classe[30];

        sscanf(linha, "%29s %29s %d", nome, classe, &nivel);

    "O sscanf() lê os campos da linha e converte os valores
     de acordo com os formatos informados."

    Em Java, usamos:
        String[] dados = linha.split(" ");
        int nivel = Integer.parseInt(dados[2]);

    (Em Java, utiliza-se split() + parseInt(), parseDouble() etc.)

    IMPORTANTE:
    O sscanf() separa e converte os campos em uma chamada.
    O split() separa os dados e os métodos parse convertem os tipos.


6) Os formatos mais importantes do sscanf():

    %d  = converte para int
    %f  = converte para float
    %lf = converte para double
    %c  = lê um caractere
    %s  = lê uma palavra

    Exemplo:
        sscanf(linha, "%d %f", &a, &b);

    Em Java, usamos:
        Integer.parseInt(dados[0]);
        Float.parseFloat(dados[1]);
        Double.parseDouble(dados[2]);
        Boolean.parseBoolean(dados[3]);

    (Em Java, utiliza-se Integer.parseInt(), Float.parseFloat(),
     Double.parseDouble() e Boolean.parseBoolean())

    Para String, não é necessário fazer parse:
        String nome = dados[0];


7) Para criar um vetor de jogadores:

    Em C:
        jogador *Herois = malloc(n * sizeof(jogador));

    "Aloca memória para armazenar n jogadores."

    Em Java:
        jogador[] Herois = new jogador[n];

    (Em Java, utiliza-se new jogador[n])

    OBSERVAÇÃO:
    Em Java, é necessário criar cada objeto individualmente:
        Herois[i] = new jogador();

    Em C, cada posição do vetor alocado pode armazenar uma estrutura.


8) Para acessar os atributos de um jogador:

    Em C:
        Herois[i].nivel = 25;

    "Acessa o campo nivel do jogador na posição i."

    Em Java:
        Herois[i].setNivel(25);

    (Em Java, pode-se utilizar o setter setNivel())

    OBSERVAÇÃO:
    Em C, utiliza-se ponto (.) quando a expressão representa
    diretamente uma estrutura. Para um ponteiro para estrutura,
    também existe o operador ->.


9) Para mostrar os dados na tela:

    Em C:
        printf("Nivel: %d\n", Herois[i].nivel);

    "Mostra o nível do jogador."

    Em Java:
        System.out.println("Nivel: " + Herois[i].getNivel());

    (Em Java, utiliza-se System.out.println())


10) Para fechar o arquivo:

    Em C:
        fclose(arquivo);

    "Fecha o arquivo depois que a leitura termina."

    Em Java, podemos utilizar try-with-resources para fechar
    o leitor automaticamente.

    (Em Java, utiliza-se try-with-resources)


11) Para liberar a memória:

    Em C:
        free(Herois);

    "Libera a memória alocada com malloc() quando ela não
     for mais necessária."

    Em Java, a memória dos objetos é gerenciada automaticamente.

    (Em Java, o Garbage Collector gerencia a memória)


12) Para ordenar os jogadores:

    Em C:
        SelectionSort(Herois, n);

    "Chama a função que ordena os jogadores por nível."

    Em Java:
        SelectionSort(Herois);

    (A lógica do Selection Sort é a mesma; a chamada depende
     dos parâmetros definidos na função)


=================================================================

Exemplo de pub.in:

3
Luis Mago 25 Cajado Tunica Elfo
Glauco Barbaro 30 Espada Armadura Fera
Gabriel Curandeiro 20 Catalizador Tunica Humano

A primeira linha informa a quantidade de jogadores.
As linhas seguintes contêm os dados de cada jogador.

*/
typedef struct jogador {
    char nome[30];
    char classe[30];
    int nivel;
    char arma[30];
    char armadura[30];
    char raca[30];
} jogador;

void mostrarJogador(jogador j) {
    printf("// -------- jogador %s --------//\n", j.nome);
    printf("Classe: %s\n", j.classe);
    printf("Nivel: %d\n", j.nivel);
    printf("Arma: %s\n", j.arma);
    printf("Armadura: %s\n", j.armadura);
    printf("Raca: %s\n", j.raca);
    printf("----------------------------------------\n");
}

void SelectionSort(jogador Herois[], int n) {
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;

        for (int j = i + 1; j < n; j++) {
            if (Herois[j].nivel < Herois[minIndex].nivel) {
                minIndex = j;
            }
        }

        jogador temp = Herois[minIndex];
        Herois[minIndex] = Herois[i];
        Herois[i] = temp;
    }
}

int main(void) {
    FILE *arquivo = fopen("pub.in", "r");

    if(arquivo == NULL) {printf("Erro ao abrir o arquivo.\n");return 1;}

    char linha[200];
    int n;

    // Aqui ele lê o n e salva o valor dele
    fgets(linha, sizeof(linha), arquivo);
    sscanf(linha, "%d", &n);

    jogador *Herois = malloc(n * sizeof(jogador));

    int i = 0;

    // Le cada jogador ate atingir a quantidade informada
    while (i < n && fgets(linha, sizeof(linha), arquivo) != NULL) {
        int camposLidos = sscanf(linha,"%29s %29s %d %29s %29s %29s",
            Herois[i].nome,
            Herois[i].classe,
            &Herois[i].nivel,
            Herois[i].arma,
            Herois[i].armadura,
            Herois[i].raca
        );

        if(camposLidos == 6){i++;}
    }

    fclose(arquivo);

    // Ordena os jogadores por nivel crescente
    SelectionSort(Herois, n);

    printf("Jogadores:\n");

    for (int j = 0; j < n; j++) {
        mostrarJogador(Herois[j]);
        printf("\n");
    }

    free(Herois);
}
