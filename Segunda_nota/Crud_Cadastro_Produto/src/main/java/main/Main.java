package main;

import javax.swing.JOptionPane;

import dao.ProdutoDAO;
import model.Produto;

public class Main {

	public static void main(String[] args) {
		ProdutoDAO produtoDAO = new ProdutoDAO();

		String controle = "-1";

		String opcao1 = "1 - Cadastrando Produtos \n";
		String opcao2 = "2 - Editando Produtos \n";
		String opcao3 = "3 - Deletar \n";
		String opcao4 = "4 - Consulta \n";
		String opcao5 = "5 - Listar \n";
		String opcao0 = "0 - Sair";

		while (!controle.equals("0")) {

			controle = JOptionPane.showInputDialog(opcao1.concat(opcao2).concat(opcao3).concat(opcao4).concat(opcao5).concat(opcao0));

			if (controle == null) {
				produtoDAO.fecharConexao();
				break;
			}

			switch (controle) {
			case "1":
				String Nome = JOptionPane.showInputDialog("Nome do produto: ");
				Double Preco = Double.parseDouble(JOptionPane.showInputDialog("Valor do produto: "));
			
				Produto produto = new Produto(null, Nome, Preco);
				produtoDAO.salvar(produto);
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			
				break;
			case "2":

				int editando_id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto: "));

				Produto editando_produto = produtoDAO.consultar(editando_id);

				String descricao = "";
				Double preco = 0.0;

				if (editando_produto != null) {
					descricao = JOptionPane.showInputDialog(null, editando_produto.toString() + "\nNome: ",
							editando_produto.getDescricao());
					preco = Double.parseDouble(
							JOptionPane.showInputDialog(null, editando_produto.toString() + "\n Nome: ", editando_produto.getPreco()));
				} else {
					JOptionPane.showMessageDialog(null, "Produto não localizado");
					break;
				}

				if (produtoDAO.editar(editando_id, descricao, preco)) {
					JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar editar o produto.");
				}

				break;
			case "3":

				int deletando_id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto: "));

				if (produtoDAO.deletar(deletando_id)) {
					JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Produto não identificado");
				}

				break;
			case "4":

				int produto_id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto: "));

				Produto prod = produtoDAO.consultar(produto_id);

				if (prod != null) {
					JOptionPane.showMessageDialog(null, prod.toString());
				} else {
					JOptionPane.showMessageDialog(null, "Produto não localizado");
				}

				break;
				
			case "5":
				
				String produto_lista = "";
				
				for (Produto p : produtoDAO.listarTodosProdutos()) {
					produto_lista += p.toString() + "\n";
				}
				
				
				JOptionPane.showMessageDialog(null, produto_lista);
				
				break;

			case "0":
				produtoDAO.fecharConexao();
				break;

			default:
				if (!controle.equals("0")) {
					JOptionPane.showMessageDialog(null, "Opção inválida!");
				}

				break;
			}
		}

	}
}
