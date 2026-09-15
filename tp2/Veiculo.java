class Veiculo {
//-- atributos --//
private int id;
private String marca;
private String modelo;
private int ano;
private String categoria;
private String combustivel;
private int cilindros;
private float cilindrada;
private String transmissao;
private String tracao;
private double consumo_cidade;
private double consumo_estrada;
private double co2;
private boolean turbo;
private Data data_registro;

//-- construtor --//
public Veiculo(int id, String marca, String modelo, int ano, String categoria, String combustivel, int cilindros, float cilindrada, String transmissao, String tracao, float consumo_cidade, float consumo_estrada, float co2, boolean turbo, Data data_registro) {
    this.id = id;
    this.marca = marca;
    this.modelo = modelo;
    this.ano = ano;
    this.categoria = categoria;
    this.combustivel = combustivel;
    this.cilindros = cilindros;
    this.cilindrada = cilindrada;
    this.transmissao = transmissao;
    this.tracao = tracao;
    this.consumo_cidade = consumo_cidade;
    this.consumo_estrada = consumo_estrada;
    this.co2 = co2;
    this.turbo = turbo;
    this.data_registro = data_registro;
}

//-- getters e setters --//
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCombustivel() {
        return combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public int getCilindros() {
        return cilindros;
    }
    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public float getCilindrada() {
        return cilindrada;
    }
    public void setCilindrada(float cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }
    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getTracao() {
        return tracao;
    }
    public void setTracao(String tracao) {
        this.tracao = tracao;
    }

    public double getConsumo_cidade() {
        return consumo_cidade;
    }
    public void setConsumo_cidade(double consumo_cidade) {
        this.consumo_cidade = consumo_cidade;
    }

    public double getConsumo_estrada() {
        return consumo_estrada;
    }
    public void setConsumo_estrada(double consumo_estrada) {
        this.consumo_estrada = consumo_estrada;
    }

    public double getCo2() {
        return co2;
    }
    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public boolean isTurbo() {
        return turbo;
    }
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public Data getData_registro() {
        return data_registro;
    }
    public void setData_registro(Data data_registro) {
        this.data_registro = data_registro;
    }

   public void printCarro() {
    System.out.print("[");
    System.out.print(id + " ## ");
    System.out.print(marca + " ## ");
    System.out.print(modelo + " ## ");
    System.out.print(ano + " ## ");
    System.out.print(categoria + " ## ");
    System.out.print(combustivel + " ## ");
    System.out.print(cilindros + " ## ");
    System.out.print(cilindrada + " ## ");
    System.out.print(transmissao + " ## ");
    System.out.print(tracao + " ## ");
    System.out.print(consumo_cidade + " ## ");
    System.out.print(consumo_estrada + " ## ");
    System.out.print(co2 + " ## ");
    System.out.print(turbo + " ## ");
    data_registro.format();
    System.out.print("]");
    
}
}