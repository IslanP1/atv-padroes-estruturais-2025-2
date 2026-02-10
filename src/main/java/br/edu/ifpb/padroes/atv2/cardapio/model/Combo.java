package br.edu.ifpb.padroes.atv2.cardapio.model;

import java.util.ArrayList;
import java.util.List;

public class Combo implements ItemCardapio{
    private String nome;
    private double desconto;
    List<ItemCardapio> itens;

    public Combo(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        double total = 0;
        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }
        return total * (1 - desconto);
    }

    @Override
    public void exibirInformacoes(String indentacao) {
        System.out.println(indentacao + "+ " + nome + " | R$ " + String.format("%.2f", getPreco()));
        for (ItemCardapio item : itens) {
            item.exibirInformacoes(indentacao + " ");
        }
    }
}
