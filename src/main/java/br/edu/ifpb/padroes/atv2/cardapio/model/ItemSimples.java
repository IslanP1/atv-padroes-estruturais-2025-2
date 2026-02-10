package br.edu.ifpb.padroes.atv2.cardapio.model;

public class ItemSimples implements ItemCardapio {
    private String nome;
    private double preco;

    public ItemSimples(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void exibirInformacoes(String indentacao) {
        System.out.println(indentacao + "- " + nome + ": R$ " + preco);
    }
}
