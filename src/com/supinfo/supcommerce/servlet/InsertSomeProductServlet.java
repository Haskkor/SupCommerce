package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.supinfo.supcommerce.entity.Product;

@WebServlet(urlPatterns="/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
	
	private EntityManagerFactory emf; 
	
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
	}

	@Override
	public void destroy() {
		emf.close();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
		Product supProd = new Product();
		EntityTransaction t = em.getTransaction();
		supProd.setName("Boite de boosters Théros");
		supProd.setContent("36 boosters Théros");
		supProd.setPrice(90.0f);
		try {
			t.begin();
			em.persist(supProd);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}
}