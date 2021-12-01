package cadastro.aplicacao;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		
		String controle = "-1";
		
		while (!controle.equals("0")) {
			String opcoes = "1 - Cadastrar \n 2 - Editar \n 3 - Excluir \n 4 - consultar \n 0 - sair";
			controle = JOptionPane.showInputDialog(opcoes);
		
			
			switch (controle) {
			case "1":
				JOptionPane.showMessageDialog(null, "Cadastrando Produtos ...");
				break;
				
			case "2":
				JOptionPane.showMessageDialog(null, "Editando Produtos ...");
				break;
				
			case "3":
				JOptionPane.showMessageDialog(null, "Excluir Produtos ...");
				break;
			
			case "4":
				JOptionPane.showMessageDialog(null, "Consultando ...");
				break;
			
			case "0":
				JOptionPane.showMessageDialog(null, "Ate logo mais...");
				break;

			default:
				JOptionPane.showInternalMessageDialog(null, "Opção Invalida.");
			}
			
	
			}
		}
	}


