package br.com.novidades.testes;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

import br.com.novidades.dto.AlertasPorUsuario;

public class ConstructorResultTest {

	private JIntegrity helper;
	
	private EntityManager manager;
	
	@Before
	public void init() {
		this.helper = new JIntegrity();
		this.helper.useMySQL();
		
		this.helper.cleanAndInsert("Usuario", "Alert");
		
		this.manager = JPAHelper.currentEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarAlertasPorUsuario() {
		List<AlertasPorUsuario> lista = this.manager.createNamedQuery("alertasPorUsuario").getResultList();
		
		lista.forEach(System.out::println);
	}
	
	/*Outra forma de fazer */
	@SuppressWarnings("unchecked")
	@Test
	public void alertasPorUsuario() {
		List<AlertasPorUsuario> lista = this.manager.createNativeQuery("select u.nome as nome, count(a.codigo) as totalAlertas from usuario u, alert a "
																														+ "where u.codigo = a.codigo_usuario "
																														+ "group by u.nome", "alertasPorUsuarioMapping").getResultList();
		
		lista.forEach(System.out::println);
	}

}
