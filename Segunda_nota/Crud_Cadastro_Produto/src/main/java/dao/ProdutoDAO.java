package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Produto;

public class ProdutoDAO {

	EntityManagerFactory factory;
	EntityManager em;

	public ProdutoDAO() {
		this.factory = Persistence.createEntityManagerFactory("projeto-jpa");
		this.em = factory.createEntityManager();
	}

	public void fecharConexao() {
		this.em.close();
		this.factory.close();
	}

	public void salvar(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}

	public boolean editar(int id, String descricao, Double preco) {
		boolean validacao = true;
		em.getTransaction().begin();
		Produto Editando = em.find(Produto.class, id);

		if (Editando == null) {
			validacao = false;
		} else {
			Editando.setDescricao(descricao);
			Editando.setPreco(preco);
			em.getTransaction().commit();
		}
		return validacao;
	}

	public boolean deletar(int id) {
		boolean validacao = true;
		em.getTransaction().begin();
		Produto deletando = em.find(Produto.class, id);
		if (deletando == null) {
			validacao = false;
		} else {
			em.remove(deletando);
			em.getTransaction().commit();
		}
		return validacao;

	}

	public Produto consultar(int id) {

		Produto produto = null;
		produto = em.find(Produto.class, id);
		return produto;

	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarTodosProdutos() {
		em.getTransaction().begin();

		String query = "select p from Produto p";
		Query consulta = em.createQuery(query);
		List<Produto> produtos = consulta.getResultList();

		em.getTransaction().commit();

		return produtos;

	}

}
