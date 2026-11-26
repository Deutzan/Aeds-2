#include <stdio.h>
#define tan 50

int tamanho(char str[]) {
int i = 0;
	while (str[i] != '\0') {
        i++;
    }
	return i;
}

//-- deixa tudo minusculo --//
void lowkey(char str[]) {
	int i = 0;

	while (str[i] != '\0') {
        if (str[i] >= 'A' && str[i] <= 'Z') {
        	str[i] = str[i] + ('a' - 'A');
        }
        i++;
    }
}

//-- algoritimo de seleção --//
//--> melhor porque todo caso é n² e mais simples de implementar
void ordem(char str[]) {
int i, j;
char temp;
int tam = tamanho(str);

	for (i = 0; i < tam - 1; i++) {
        for (j = (i + 1); j < tam; j++) {
//-- faz um swap --//INSPIRED BY BIGHEADBH            
        	if (str[i] > str[j]) {
                	temp = str[i];
                	str[i] = str[j];
                	str[j] = temp;
            		}
        	}	
    	}
}

int main() {
//-- vetores --//
char str1[tan];
char str2[tan];
    
	scanf("%s", str1);

while(!(str1[0] == 'F' && str1[1] == 'I' && str1[2] == 'M')){
//-- pede as strings --//
	scanf("%s", str2);

tamanho(str1);
tamanho(str2);

//-- deixa minusculo --//
lowkey(str1);
lowkey(str2);

//-- ordena tudo --//
ordem(str1);
ordem(str2);

//-- compara as strings para ver se é um anagrama --//
int i = 0;

while (str1[i] != '\0' && str1[i] == str2[i]) {
	i++;
    }

	if (str1[i] == '\0') {
        printf("SIM\n");
	}
	else {
        printf("NAO\n");
    }
    //-- pede as strings --//
	scanf("%s",str1);
    }
}
