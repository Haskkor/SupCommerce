package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.supcommerce.entity.Product;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/auth/removeProduct")
public class RemoveProductServlet extends HttpServlet{

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			Query query = em.createQuery("DELETE p FROM Product p WHERE p.id = :id");
			query.setParameter("id", Long.valueOf(req.getParameter("id")));
			query.executeUpdate();
		} finally {
			em.close();
		}
		
		resp.sendRedirect(getServletContext().getContextPath() + "/listProduct");
	}
	
}
