#include <stdio.h>
#define TAM 200
/*
 * X1 - recebe uma String(ve se ela só tem vogais) se sim,retorna verdadeiro
 * X2 - recebe uma String(ve se ela só tem consoantes) se sim,retorna verdadeiro
 * X3 - recebe uma String(ve se ela é um numero inteiro) se sim,retorna verdadeiro
 * X4 - recebe uma String(ve se ela á um numero real) se sim,retorna verdadeiro
*/

//X1(true) - so pode estar contido em (a e i o u)[e maiusculos]
//X2(true) - deve estar entre (A e Z),mas diferentes de (a e i o u)[e maiusculos]
//X3(true) - so pode ser numero
//X4(true) - so numero e no maximo um '.' ou uma ',';

int tamanho(char str[]){
//-- variaveis --//
int i = 0;
	while(str[i] != '\0'){
	i++;
	}
	return i;
}

void lowkey(char str[]){
//-- variaveis --//
int i = 0;

	while(str[i] != '\0'){
		if(str[i] >= 'A' && str[i] <= 'Z'){
		str[i] = str[i] + ('a' - 'A');
		}
    i++;
	}
}

void X1X2(char str[],int tamanho){
//-- variaveis --//
int vogal = 0;
int consoante = 0;
int i = 0;

lowkey(str);

	while(str[i] != '\0'){
	if((str[i] >= 'a' && str[i] <= 'z') && (str[i] == 'a'||str[i] == 'e'||str[i] == 'i'||str[i] == 'o'||str[i] == 'u')){
	vogal++;
		}
	else{
	if(str[i] >= 'a' && str[i] <= 'z'){
    consoante++;
    }

	}
		i++;
	}


//-- if vogal e consoante --//
if(vogal > 0 && vogal == tamanho){
	printf("SIM ");
	}
else{
	printf("NAO ");
	}
if(consoante >  0 && consoante == tamanho){
	printf("SIM ");
	}
else{
	printf("NAO ");
}
}


void X3(char str[], int tamanho){
//-- variaveis --//
int i = 0;

	while((str[i] != '\0') && (str[i] >= '0' && str[i] <= '9')){
	i++;
	}
	if(i == tamanho){
		printf("SIM ");
	}
	else{
		printf("NAO ");
	}

}
void X4(char str[],int tamanho){
//-- variaveis --//
int i = 0;
int point = 0;

	while((str[i] != '\0') && (str[i] >= '0' && str[i] <= '9'||str[i] == '.'||str[i] == ',')){
	if(str[i] == '.' || str[i] == ','){
	point++;
	}
    i++;
	}
	if(i == tamanho && point <= 1){
		printf("SIM ");
	}
	else{
		printf("NAO ");
	}	
}

int main(){
//-- variaveis --//
char str[TAM];
int tam = 0;

//-- pede a string --//
fgets(str,TAM,stdin);

	while(!(str[0] == 'F' && str[1] == 'I' && str[2] == 'M')){
tam = tamanho(str);

if(str[tam - 1] == '\n'){
    str[tam - 1] = '\0';
    tam--;
}

//-- metodos --//
X1X2(str,tam);
X3(str,tam);
X4(str,tam);
printf("\n");
	fgets(str,TAM,stdin);
	}
}
