import java.util.Scanner;

public class questao9 {

    public static void main(String[] args) {

        Veiculo[] veiculos = LerCSV.ler("java_2/veiculosJ.csv");

        Lista lista = new Lista(1000);

        Scanner scan = new Scanner(System.in);

        // =========================================
        // PRIMEIRA PARTE DA ENTRADA
        // =========================================

        int id = scan.nextInt();

        while (id != -1) {

            Veiculo veiculo = LerCSV.buscarPorId(veiculos, id);

            if (veiculo != null) {
                lista.inserirFim(veiculo);
            }

            id = scan.nextInt();
        }

        // =========================================
        // SEGUNDA PARTE DA ENTRADA
        // =========================================

        int quantidade = scan.nextInt();

        for (int i = 0; i < quantidade; i++) {

            String comando = scan.next();

            // =========================================
            // INSERIR NO INÍCIO
            // =========================================

            if (comando.equals("II")) {

                int idVeiculo = scan.nextInt();

                Veiculo veiculo =
                    LerCSV.buscarPorId(veiculos, idVeiculo);

                if (veiculo != null) {
                    lista.inserirInicio(veiculo);
                }

            // =========================================
            // INSERIR EM UMA POSIÇÃO
            // =========================================

            } else if (comando.equals("I*")) {

                int posicao = scan.nextInt();
                int idVeiculo = scan.nextInt();

                Veiculo veiculo =
                    LerCSV.buscarPorId(veiculos, idVeiculo);

                if (veiculo != null) {
                    lista.inserir(veiculo, posicao);
                }

            // =========================================
            // INSERIR NO FIM
            // =========================================

            } else if (comando.equals("IF")) {

                int idVeiculo = scan.nextInt();

                Veiculo veiculo =
                    LerCSV.buscarPorId(veiculos, idVeiculo);

                if (veiculo != null) {
                    lista.inserirFim(veiculo);
                }

            // =========================================
            // REMOVER DO INÍCIO
            // =========================================

            } else if (comando.equals("RI")) {

                Veiculo removido = lista.removerInicio();

                if (removido != null) {

                    System.out.println(
                        "(R)" +
                        removido.getMarca() +
                        " " +
                        removido.getModelo()
                    );
                }

            // =========================================
            // REMOVER DE UMA POSIÇÃO
            // =========================================

            } else if (comando.equals("R*")) {

                int posicao = scan.nextInt();

                Veiculo removido = lista.remover(posicao);

                if (removido != null) {

                    System.out.println(
                        "(R)" +
                        removido.getMarca() +
                        " " +
                        removido.getModelo()
                    );
                }

            // =========================================
            // REMOVER DO FIM
            // =========================================

            } else if (comando.equals("RF")) {

                Veiculo removido = lista.removerFim();

                if (removido != null) {

                    System.out.println(
                        "(R)" +
                        removido.getMarca() +
                        " " +
                        removido.getModelo()
                    );
                }
            }
        }

        // =========================================
        // MOSTRAR LISTA FINAL
        // =========================================

        lista.mostrar();

        scan.close();
    }
}


// =====================================================
// LISTA COM ALOCAÇÃO SEQUENCIAL
// =====================================================

class Lista {

    private Veiculo[] array;
    private int n;

    // =================================================
    // CONSTRUTOR
    // =================================================

    public Lista(int tamanho) {

        array = new Veiculo[tamanho];
        n = 0;
    }

    // =================================================
    // INSERIR NO INÍCIO
    // =================================================

    public void inserirInicio(Veiculo veiculo) {

        if (n >= array.length) {
            return;
        }

        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = veiculo;

        n++;
    }

    // =================================================
    // INSERIR EM UMA POSIÇÃO
    // =================================================

    public void inserir(Veiculo veiculo, int posicao) {

        if (n >= array.length ||
            posicao < 0 ||
            posicao > n) {

            return;
        }

        for (int i = n; i > posicao; i--) {
            array[i] = array[i - 1];
        }

        array[posicao] = veiculo;

        n++;
    }

    // =================================================
    // INSERIR NO FIM
    // =================================================

    public void inserirFim(Veiculo veiculo) {

        if (n >= array.length) {
            return;
        }

        array[n] = veiculo;

        n++;
    }

    // =================================================
    // REMOVER DO INÍCIO
    // =================================================

    public Veiculo removerInicio() {

        if (n == 0) {
            return null;
        }

        Veiculo removido = array[0];

        for (int i = 0; i < n - 1; i++) {
            array[i] = array[i + 1];
        }

        n--;

        array[n] = null;

        return removido;
    }

    // =================================================
    // REMOVER DE UMA POSIÇÃO
    // =================================================

    public Veiculo remover(int posicao) {

        if (posicao < 0 || posicao >= n) {
            return null;
        }

        Veiculo removido = array[posicao];

        for (int i = posicao; i < n - 1; i++) {
            array[i] = array[i + 1];
        }

        n--;

        array[n] = null;

        return removido;
    }

    // =================================================
    // REMOVER DO FIM
    // =================================================

    public Veiculo removerFim() {

        if (n == 0) {
            return null;
        }

        Veiculo removido = array[n - 1];

        n--;

        array[n] = null;

        return removido;
    }

    // =================================================
    // MOSTRAR
    // =================================================

    public void mostrar() {

        for (int i = 0; i < n; i++) {

            System.out.println(
                array[i].format()
            );
        }
    }
}


// =====================================================
// CLASSE DATA
// =====================================================

class Data {

    private int dia;
    private int mes;
    private int ano;

    // =================================================
    // CONSTRUTOR
    // =================================================

    public Data() {

        dia = 0;
        mes = 0;
        ano = 0;
    }

    public Data(int dia, int mes, int ano) {

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // =================================================
    // GETTERS
    // =================================================

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    // =================================================
    // SETTERS
    // =================================================

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // =================================================
    // PARSE DA DATA
    // =================================================

    public static Data ParseDia(String data) {

        String[] partes = data.split("-");

        int ano = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);

        return new Data(dia, mes, ano);
    }

    // =================================================
    // FORMATAR DATA
    // =================================================

    public String format() {

        return String.format(
            "%02d/%02d/%04d",
            dia,
            mes,
            ano
        );
    }
}


// =====================================================
// CLASSE VEICULO
// =====================================================

class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String categoria;
    private String combustivel;
    private int cilindros;
    private double cilindrada;
    private String transmissao;
    private String tracao;
    private double consumoCidade;
    private double consumoEstrada;
    private double co2;
    private boolean turbo;
    private Data dataRegistro;

    // =================================================
    // CONSTRUTOR
    // =================================================

    public Veiculo(
        int id,
        String marca,
        String modelo,
        int ano,
        String categoria,
        String combustivel,
        int cilindros,
        double cilindrada,
        String transmissao,
        String tracao,
        double consumoCidade,
        double consumoEstrada,
        double co2,
        boolean turbo,
        Data dataRegistro
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
        this.consumoCidade = consumoCidade;
        this.consumoEstrada = consumoEstrada;
        this.co2 = co2;
        this.turbo = turbo;
        this.dataRegistro = dataRegistro;
    }

    // =================================================
    // GETTERS
    // =================================================

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

    public double getCilindrada() {
        return cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public String getTracao() {
        return tracao;
    }

    public double getConsumoCidade() {
        return consumoCidade;
    }

    public double getConsumoEstrada() {
        return consumoEstrada;
    }

    public double getCo2() {
        return co2;
    }

    public boolean getTurbo() {
        return turbo;
    }

    public Data getDataRegistro() {
        return dataRegistro;
    }

    // =================================================
    // SETTERS
    // =================================================

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

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public void setTracao(String tracao) {
        this.tracao = tracao;
    }

    public void setConsumoCidade(double consumoCidade) {
        this.consumoCidade = consumoCidade;
    }

    public void setConsumoEstrada(double consumoEstrada) {
        this.consumoEstrada = consumoEstrada;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public void setDataRegistro(Data dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    // =================================================
    // FORMATAR COMBUSTÍVEL
    // =================================================

    private String formatarCombustivel() {

        String resultado = "[";

        for (int i = 0; i < combustivel.length(); i++) {

            char c = combustivel.charAt(i);

            if (c == ';') {
                resultado += ",";
            } else {
                resultado += c;
            }
        }

        resultado += "]";

        return resultado;
    }

    // =================================================
    // FORMAT
    // =================================================

    public String format() {

        return String.format(
            "[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
            id,
            marca,
            modelo,
            ano,
            categoria,
            formatarCombustivel(),
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumoCidade,
            consumoEstrada,
            co2,
            turbo,
            dataRegistro.format()
        );
    }

    // =================================================
    // PARSE DO VEICULO
    // =================================================

    public static Veiculo ParseVeiculo(String linha) {

        String[] partes = linha.split(",");

        int id = Integer.parseInt(partes[0]);
        String marca = partes[1];
        String modelo = partes[2];
        int ano = Integer.parseInt(partes[3]);
        String categoria = partes[4];
        String combustivel = partes[5];
        int cilindros = Integer.parseInt(partes[6]);
        double cilindrada = Double.parseDouble(partes[7]);
        String transmissao = partes[8];
        String tracao = partes[9];
        double consumoCidade = Double.parseDouble(partes[10]);
        double consumoEstrada = Double.parseDouble(partes[11]);
        double co2 = Double.parseDouble(partes[12]);
        boolean turbo = Boolean.parseBoolean(partes[13]);
        Data dataRegistro = Data.ParseDia(partes[14]);

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
}


// =====================================================
// CLASSE LERCSV
// =====================================================

class LerCSV {

    // =================================================
    // LER CSV
    // =================================================

    public static Veiculo[] ler(String caminho) {

        Veiculo[] veiculos = new Veiculo[1000];

        int quantidade = 0;

        try {

            Scanner arquivo =
                new Scanner(new java.io.File(caminho));

            // Pular cabeçalho
            if (arquivo.hasNextLine()) {
                arquivo.nextLine();
            }

            while (arquivo.hasNextLine()) {

                String linha = arquivo.nextLine();

                if (!linha.isEmpty()) {

                    veiculos[quantidade] =
                        Veiculo.ParseVeiculo(linha);

                    quantidade++;
                }
            }

            arquivo.close();

        } catch (Exception e) {

            System.out.println(
                "Erro ao ler arquivo: " + e.getMessage()
            );
        }

        Veiculo[] resultado = new Veiculo[quantidade];

        for (int i = 0; i < quantidade; i++) {
            resultado[i] = veiculos[i];
        }

        return resultado;
    }

    // =================================================
    // BUSCAR POR ID
    // =================================================

    public static Veiculo buscarPorId(Veiculo[] veiculos,int id) {

    for (int i = 0; i < veiculos.length; i++) {

            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }
}
