#include <stdio.h>
#define n 100

int main(){
//-- variaveis --//
int tamanho = 0;
char palavra[n];
char inverso[n];
int i;

//-- pedindo a palavra --//
fgets(palavra,n,stdin);

while(palavra[0] != 'F' && palavra[1] != 'I' && palavra[2] != 'M'){

//-- verificar o tamanho --//
i = 0;
while(palavra[i] != '\0'){
	i++;
	}	
tamanho = i;

//-- invertendo --//
for(i = 0;i < tamanho;i++){
	inverso[i] = palavra[tamanho-i-1];
	}	

//-- printando o inverso --//
for(i = 0;i < tamanho;i++){
	printf("%c",inverso[i]);
	}
	printf("\n");
	
fgets(palavra,n,stdin);
}
}
