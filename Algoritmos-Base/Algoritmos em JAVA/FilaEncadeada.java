
class FilaEncadeada{
    public static void main(String[] args) throws Exception {
    
    Fila fila = new Fila();
    
    fila.Inserir(2);
    fila.Inserir(5);
    fila.Inserir(9);
    fila.Inserir(1);
    System.out.println(fila.RemoverUltimo());
    fila.Mostrar();


    }                   

}

class Celula{
    public int elemento;
    public Celula prox;

    public Celula() {
        this.elemento = 0;
        this.prox = null;
    }
    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class Fila{            
    public Celula primeiro,ultimo;

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void Inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void InserirInicio(int x){
        Celula tmp = new Celula();
        tmp.prox = primeiro;
        primeiro.elemento = x;
        primeiro = tmp;
        tmp = null;
    }

    public int RemoverInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        tmp.prox = tmp = null;
        return primeiro.elemento;
    }

    public int RemoverUltimo() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
           }
        Celula tmp = primeiro;
            while (tmp.prox != ultimo) {
                tmp = tmp.prox;
            }
        int resp = ultimo.elemento;
        ultimo = tmp;
        tmp.prox = tmp = null;
        return resp;
    }

    public void Mostrar(){
        Celula tmp = primeiro.prox;
        System.out.print("Fila: ");
        while(tmp != null){
            if(tmp.prox == null){
                System.out.print("[" + tmp.elemento + "]--> NULL");     
            }else{
                System.out.print("[" + tmp.elemento + "]--> ");
            }
            tmp = tmp.prox; 
        }
        System.out.println();
    }
}