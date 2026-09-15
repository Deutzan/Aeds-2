class Data{
    private int dia;
    private int mes;
    private int ano;

    //-- construtor --//
    public Data(){
        this.dia = 00;
        this.mes = 00;
        this.ano = 0000;
    }
    
    public Data(int dia,int mes,int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    //-- gets e sets --//
    public int getDia(){
        return dia;
    }
    public void setDia(int dia){
        this.dia = dia;
    }

    public int getMes(){
        return mes;
    }
    public void setMes(int mes){
        this.mes = mes;
    }

    public int getAno(){
        return ano;
    }
    public void setdia(int ano){
        this.ano = ano;
    }

    /* printagem da data */
    public void format(){
        System.out.print(dia + "/" + mes + "/" + ano);
    }

}