package ArvoreBinaria;

class Tree{
    public static void main(String[] args){
        
    }


    
}

class No{
    int elemento;
    No esq = null;
    No dir = null;

    public No(int elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore{
    No raiz = null;

    public void Inserir(int x){
        raiz = inserir(x,raiz);
    }

    public No inserir(int x,No i){
        if(i == null){
            i = new No(x);
        }
        if(x < i.elemento){
            i.esq = inserir(x,i.esq);
        }
        else if(x > i.elemento){
            i.dir = inserir(x,i.dir);
        }
        return i;
    }

    public Boolean Pesquisa(No A,int x){
        if(A == null){
            return false;
        }
        else if (x == A.elemento){
            return true;
        }
        else if (x < A.elemento){
            return Pesquisa(A.esq,x);
        }else{
            return Pesquisa(A.dir,x);
        }
    }

    public void PreOrder(No A){
        if(A != null){
            System.out.print(A.elemento + " ");
            PreOrder(A.esq);
            PreOrder(A.dir);
        }
    }

    public void MeioOrder(No A){
        if(A != null){
            MeioOrder(A.esq);
            System.out.print(A.elemento + " ");
            MeioOrder(A.dir);
        }
    }

    public void PosOrder(No A){
        if(A != null){
            PosOrder(A.esq);
            PosOrder(A.dir);
            System.out.print(A.elemento + " ");
        }
    }

    public void Remover(int x){
        raiz = remover(raiz,x);
    }
    public No remover(No i,int x){
    if(i == null){
        return null;
    }
    if(x < i.elemento){
        i.esq = remover(i.esq,x);
    }
    else if(x > i.elemento){
        i.dir = remover(i.dir,x);
    }
    else{
        if(i.esq == null){
        return i.dir; 
        }
        if(i.dir == null){
        return i.esq;
        }

        No sucessor = i.dir;
        while(sucessor.esq != null){
            sucessor = sucessor.esq;
        }
        i.elemento = sucessor.elemento; 
        i.dir = remover(i.dir, sucessor.elemento);
    }
    return i;
    }
    public int contagem(No A){
        if(A == null){
        return 0;
        }
        else{
            return 1 + contagem(A.esq) + contagem(A.dir);
        }
    }

}