package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.supcommerce.entity.Product;

@WebServlet(urlPatterns="/listProduct")
public class ListProductServlet extends HttpServlet {

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
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		
		List<Product> list;	
		try {
			Query query = em.createQuery("SELECT p FROM Product p");
			list = query.getResultList();
		} finally {
			em.close();
		}
		arg0.setAttribute("list", list);
		RequestDispatcher rd = arg0.getRequestDispatcher("/listProduct.jsp");
		rd.forward(arg0, arg1);
	}
}
