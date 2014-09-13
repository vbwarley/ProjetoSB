package nucleo.model.persistencia.dao;

import java.util.List;

/**
 * Esta interface provê os métodos que uma classe que a contrate utilizarará.
 * 
 * @author Warley Vital
 *
 * @param <T> genérico que vai identificar o tipo do objeto
 * @param <type> genérico que vai identificar o tipo de chave a ser utilizada
 */
public interface DAO<T, type> {

	/**
	 * Método que define a criação do objeto.
	 * 
	 * @param objeto a ser persistido no banco
	 */
	void criar(T objeto);
	
	/**
	 * Método que retorna um objeto do banco que tenha o ID passado.
	 * 
	 * @param id a ser usado na consulta
	 * @return um instância do objeto se este existir, caso contrário null
	 */
	T consultar(type id);
	
	/**
	 * Método que altera o objeto no banco.
	 * 
	 * @param objeto a ser alterado
	 */
	void alterar(T objeto);
	
	/**
	 * Método que deleta o objeto do banco, se existir.
	 * 
	 * @param objeto a ser deletado
	 */
	void deletar(T objeto);
	
	/**
	 * Método que retorna todos os registros dos objetos desta classe no banco.
	 * 
	 * @return uma lista contendo os registros se existirem, caso contrário null
	 */
	List<T> getList();
}
