
public Doidona(){
    this.raiz = null;
}


    public void inserirVeiculo(Veiculo v){
        char letra = v.placa.charAt(0);
        No i = raiz;

        while(i != null && i.letra != letra){
            i = (letra < i.letra) ? i.esq : i.dir;
        }

        int pos = i.t1.hash1(v.placa,v);
        if(i.t1.tabela[pos] == null){
            i.t1.tabela[pos] = v;
        } else {
            pos = i.t1.rehash1(v.placa,v);
            if(i.t1.tabela[pos] == null){
                i.t1.tabela[pos] = v;
            } else {
                pos = i.t1.t2.hasht2(v.chassi,v);
                Lista l = i.t1.t2.tabela[pos];
                l.fim.prox = new CelulaLista(v);
                l.fim = l.fim.prox;
            }
        



    }

}

class Veiculo{
    String placa,modelo,chassi;
    String tipo;

    public Veiculo(String placa,String modelo,String chassi,String tipo){
    this.placa = placa;
    this.modelo = modelo;
    this.chassi = chassi;
    this.tipo = tipo;
    }
}

class No {
    char letra;
    No esq,dir;
    T1 t1;

public No(char letra) {
        this.letra = letra;
        this.esq = null;
        this.dir = null;
        
    }
}

class Lista{
    CelulaLista inicio,fim;
}

class T1{
    Veiculo[] tabela;
    T2 t2;

    tabela = new Veiculo[7];

    public int hash1(String placa, Veiculo v){
        int paia = 0;
        for(int i = 0; i < tabela.length; i++){
            paia += (int)placa.charAt(i);
        }
        return paia % 7;
    } 


    public int rehash1(String placa, Veiculo v){
        int paia = 1;
        for(int i = 0; i < tabela.length; i++){
            paia += (int)placa.charAt(i);
        }
        return paia % 7;
    } 
}

class T2{
    Lista tabela;
}

class CelulaLista{
    Veiculo veiculo;
    CelulaLista prox;
}
