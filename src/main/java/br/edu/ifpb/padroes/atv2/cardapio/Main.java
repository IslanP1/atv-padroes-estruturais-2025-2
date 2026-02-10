package br.edu.ifpb.padroes.atv2.cardapio;

import br.edu.ifpb.padroes.atv2.cardapio.model.Combo;
import br.edu.ifpb.padroes.atv2.cardapio.model.ItemCardapio;
import br.edu.ifpb.padroes.atv2.cardapio.model.ItemSimples;

public class Main {
    public static void main(String[] args) {
        // Criando itens simples para o cardápio
        ItemCardapio item1 = new ItemSimples("Hambúrguer", 20.00);
        ItemCardapio item2 = new ItemSimples("Batata Frita", 10.00);
        ItemCardapio item3 = new ItemSimples("Refrigerante", 7.50);
        ItemCardapio item4 = new ItemSimples("Sorvete", 10.00);

        // Criando um combo básico
        Combo comboBasico = new Combo("Combo Básico", 0.06); // 6% de desconto
        comboBasico.adicionarItem(item1);
        comboBasico.adicionarItem(item2);
        comboBasico.adicionarItem(item3);

        // Criando combo premiun
        Combo comboPremiun = new Combo("Combo Premiun", 0.12); // 12% de desconto
        comboPremiun.adicionarItem(comboBasico);
        comboPremiun.adicionarItem(new ItemSimples("Sorvete", 9.50));

        // Exibindo o cardápio
        System.out.println("\n======= Cardápio =======");
        item1.exibirInformacoes("");
        item2.exibirInformacoes("");
        item3.exibirInformacoes("");
        item4.exibirInformacoes("");
        System.out.println("\n====== Combos ======");
        comboBasico.exibirInformacoes("");
        comboPremiun.exibirInformacoes("");
    }
}
