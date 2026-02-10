package br.edu.ifpb.padroes.atv2.cardapio.model;

public interface ItemCardapio {
    String getNome();
    double getPreco();
    void exibirInformacoes(String indentacao);
}
