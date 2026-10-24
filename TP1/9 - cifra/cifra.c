#include <stdio.h>
//-- tamanho do vetor --//
#define N 300
//-- tamanho do pulo da cifra --//
#define pulo 3

char cifra(char letra){
	return letra + pulo;
}

int main() {
//-- variaveis --//
	char texto[N];

//-- pedindo o texto --//
	fgets(texto, N, stdin);

	while(texto[0] != 'F' && texto[1] != 'I' && texto[2] != 'M'){
//--olhando cada  posição no vetor --//
	for (int i = 0; texto[i] != '\0'; i++) {
        	texto[i] = cifra(texto[i]);
    }
	printf("%s\n", texto);
	fgets(texto, N, stdin);
   }
}
