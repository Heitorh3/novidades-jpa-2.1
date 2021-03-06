package br.com.novidades.testes;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

import br.com.novidades.model.Usuario;

public class UsuarioPersistenceTest {

	private JIntegrity helper;
	
	private EntityManager manager;
	
	@Before
	public void init() {
		this.helper = new JIntegrity();
		this.helper.useMySQL();
		
		this.helper.cleanAndInsert();
		
		this.manager = JPAHelper.currentEntityManager();
	}
	
	@Test
	public void deveRetornarDataDeNascimentoDoUsuario() {
		Usuario usuario = this.manager.find(Usuario.class, 3L);
		
		assertEquals(LocalDate.of(1984, 10, 2), usuario.getDataNascimento());
	}
	
}