#include <stdio.h>

int SOMA(int numero){
//-- condição de parada --//
	if(numero == 0){
	return 0;}

//-- recursão --//
	return(numero%10) + SOMA(numero/10);
}


int main(){
//-- variaveis --//	
int num;

//while da entrada -> saida
	while(scanf("%d",&num)== 1){
	       printf("%d\n", SOMA(num));	
	}
}

