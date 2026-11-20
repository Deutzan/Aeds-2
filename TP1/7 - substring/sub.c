#include <stdio.h>
#define TAM 100

int tamanho(char str[]) {
int i = 0;
	while (str[i] != '\0') {
    	i++;
    }
	return i;
}

int maiorSubstring(char str[]) {

int n = tamanho(str);
int maior = 0;
//-- define onde a substring começa --//
	for(int inicio = 0; inicio < n; inicio++) {
	int tamanhoAtual = 0;

//-- responsavel por aumentar a substring --//
        for(int fim = inicio; fim < n; fim++) {
        int repetido = 0;

//-- Verifica se str[fim] ja apareceu na substring atual --//    
	for (int i = inicio; i < fim; i++) {
            if(str[i] == str[fim]) {
                    repetido = 1;
                    break;
                }
            }
	if(repetido) {
        break;
            }
        tamanhoAtual++;
        }
	if(tamanhoAtual > maior) {
        maior = tamanhoAtual;
        }
    }
    return maior;
}

int main() {
//-- variaveis --//
char str[TAM];

fgets(str, TAM, stdin);

while(!(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')){
	int i = 0;

// Remove o '\n' lido pelo fgets
	while(str[i] != '\0') {
	if (str[i] == '\n') {
        	str[i] = '\0';
                break;
            }
	i++;
        }
	printf("%d\n", maiorSubstring(str));
	fgets(str, TAM, stdin);
    }
}

