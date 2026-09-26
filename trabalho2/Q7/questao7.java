
package Q7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class questao7 {

    public static void main(String[] args) {
        Veiculo[] todos = LerCSV.ler("veiculosJ.csv");
        Veiculo[] selecionados = new Veiculo[50];
        int quantidade = 0;

        Scanner entrada = new Scanner(System.in);
        int id;

        while ((id = entrada.nextInt()) != -1) {
            Veiculo veiculo = LerCSV.buscarPorId(todos, id);

            if (veiculo != null) {
                selecionados[quantidade] = veiculo;
                quantidade++;
            }
        }

        Veiculo[] usados = new Veiculo[quantidade];
        for (int i = 0; i < quantidade; i++) {
            usados[i] = selecionados[i];
        }

        Veiculo.Bucketsort(usados);
        LerCSV.printCarro(usados);

        entrada.close();
    }
}


// LER CSV 

class LerCSV {
    public static Veiculo[] ler(String caminho) {
        Veiculo[] veiculos = new Veiculo[501];
        int quantidade = 0;
        try {

            BufferedReader arquivo = new BufferedReader(new FileReader(caminho));
            arquivo.readLine();
            String linha;

            while ((linha = arquivo.readLine()) != null) {
                Veiculo veiculo = Veiculo.ParseVeiculo(linha);
                veiculos[quantidade] = veiculo;
                quantidade++;
            }
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return veiculos;
    }


    public static Veiculo buscarPorId(
        Veiculo[] veiculos,int id) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null &&
                veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }
        return null;
    }


    public static void printCarro(Veiculo[] veiculos) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                System.out.println(veiculos[i].format()
                );
            }
        }
    }
}


// DATA 

class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data() {
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public static Data ParseDia(String dataRegistro) {
        String[] data = dataRegistro.split("-");
        int anoData = Integer.parseInt(data[0]);
        int mesData = Integer.parseInt(data[1]);
        int diaData = Integer.parseInt(data[2]);
        return new Data(diaData,mesData,anoData);
    }


    public String format() {
        return String.format("%02d/%02d/%04d",dia, mes,ano);
    }
}


// VEICULO

class Veiculo {
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

    public Veiculo(
        int id,
        String marca,
        String modelo,
        int ano,
        String categoria,
        String combustivel,
        int cilindros,
        float cilindrada,
        String transmissao,
        String tracao,
        float consumo_cidade,
        float consumo_estrada,
        float co2,
        boolean turbo,
        Data data_registro
    ) {

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

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }


    public String getCategoria() {
        return categoria;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public int getCilindros() {
        return cilindros;
    }

    public float getCilindrada() {
        return cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public String getTracao() {
        return tracao;
    }

    public double getConsumo_cidade() {
        return consumo_cidade;
    }

    public double getConsumo_estrada() {
        return consumo_estrada;
    }

    public double getCo2() {
        return co2;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public Data getData_registro() {
        return data_registro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public void setCilindrada(float cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public void setTracao(String tracao) {
        this.tracao = tracao;
    }

    public void setConsumo_cidade(double consumo_cidade) {
        this.consumo_cidade = consumo_cidade;
    }

    public void setConsumo_estrada(double consumo_estrada) {
        this.consumo_estrada = consumo_estrada;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public void setData_registro(Data data_registro) {
        this.data_registro = data_registro;
    }

    public static String[] separarCampos(String linha) {
        String[] campos = new String[15];
        int quantidade = 0;
        int inicio = 0;
        boolean dentroColchetes = false;

        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '[') {
                dentroColchetes = true;
            } 
            else if (linha.charAt(i) == ']') {
                dentroColchetes = false;
            } 
            else if (linha.charAt(i) == ',' && !dentroColchetes) {
                campos[quantidade] = linha.substring(inicio, i);
                quantidade++;
                inicio = i + 1;
            }
        }
        campos[quantidade] = linha.substring(inicio);
        return campos;
    }

    // PARSE VEICULO
    public static Veiculo ParseVeiculo(String linha) {
        String[] dados = separarCampos(linha);
        int id = Integer.parseInt(dados[0]);
        String marca = dados[1];
        String modelo = dados[2];
        int ano = Integer.parseInt(dados[3]);
        String categoria = dados[4];
        String combustivel = dados[5];
        int cilindros = Integer.parseInt(dados[6]);
        float cilindrada = Float.parseFloat(dados[7]);
        String transmissao = dados[8];
        String tracao = dados[9];
        float consumoCidade = Float.parseFloat(dados[10]);
        float consumoEstrada = Float.parseFloat(dados[11]);
        float co2 = Float.parseFloat(dados[12]);
        boolean turbo = Boolean.parseBoolean(dados[13]);
        Data dataRegistro = Data.ParseDia(dados[14]);

        return new Veiculo(
            id,
            marca,
            modelo,
            ano,
            categoria,
            combustivel,
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumoCidade,
            consumoEstrada,
            co2,
            turbo,
            dataRegistro
        );
    }


    // FORMAT 
    public String format() {
        return String.format("[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
            id,
            marca,
            modelo,
            ano,
            categoria,
            combustivel,
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumo_cidade,
            consumo_estrada,
            co2,
            turbo,
            data_registro.format()
        );
    }


    // BUCKET SORT
    public static void Bucketsort(Veiculo[] veiculos) {
        if (veiculos.length == 0) {
            return;
        }
        // 10 buckets
        Veiculo[][] buckets = new Veiculo[10][veiculos.length];
        int[] quantidade = new int[10];

        // DISTRIBUIÇÃO
        for (int i = 0; i < veiculos.length; i++) {
            double cilindrada = veiculos[i].getCilindrada();
            int indiceBucket = (int)(cilindrada / 0.8);

            // Caso seja exatamente 8.0
            if (indiceBucket >= 10) {
                indiceBucket = 9;
            }
            buckets[indiceBucket][quantidade[indiceBucket]]= veiculos[i];
            quantidade[indiceBucket]++;
        }
        // INSERTION SORT
        for (int b = 0; b < 10; b++) {
            for (int i = 1;i < quantidade[b];i++) {
                Veiculo atual = buckets[b][i];
                int j = i - 1;
                while ( j >= 0 && buckets[b][j].getCilindrada() > atual.getCilindrada()) {
                    buckets[b][j + 1] = buckets[b][j];
                    j--;
                }
                buckets[b][j + 1] = atual;
            }
        }


        // DEVOLVE AO VETOR
        int posicao = 0;
        for (int b = 0; b < 10; b++) {
            for (int j = 0;j < quantidade[b];j++) {
                veiculos[posicao] = buckets[b][j];
                posicao++;
            }
        }
    }
}