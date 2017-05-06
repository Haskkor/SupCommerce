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
import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

@WebServlet(urlPatterns="/showProduct")
public class ShowProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			request.setAttribute("id", em.find(Product.class, id));
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/showProduct.jsp");
		rd.forward(request, response);

	}
}
