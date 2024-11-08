package br.com.paulo.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.paulo.livraria.modelo.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				" select u from Usuario u " + " where u.email = :pEmail and u.senha=:pSenha", Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try{
			Usuario resultado = query.getSingleResult();
			
		}catch (NoResultException ex){
			return false;
		}

		
		em.close();

		return true;
	}

}
