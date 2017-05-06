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
import com.supinfo.supcommerce.entity.Category;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
	EntityManager em;
	
	@Override
	public void init() throws ServletException {
		em = emf.createEntityManager();
	}

	@Override
	public void destroy() {
		emf.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(getServletContext().getContextPath() + "/addCategory.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category cat1 = new Category();
		cat1.setName(req.getParameter("name"));
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(cat1);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
		resp.sendRedirect(getServletContext().getContextPath() + "/addCategory.jsp");
	}
	
}