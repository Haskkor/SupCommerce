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

@WebServlet(urlPatterns="/auth/addProduct")
public class AddProductServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		Float price = Float.valueOf(req.getParameter("price"));
		long id = (long) req.getAttribute("id");
		Product supProd = new Product();
		supProd.setName(name);
		supProd.setContent(content);
		supProd.setPrice(price);
		EntityTransaction t = em.getTransaction();
		try {
			supProd.setCat(em.find(Category.class, id));
			t.begin();
			em.persist(supProd);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
		resp.sendRedirect((req.getServletContext().getContextPath() + "/showProduct?id=" + supProd.getId()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Category> list;	
		try {
			Query query = em.createQuery("SELECT c FROM Category c");
			list = query.getResultList();
		} finally {
			em.close();
		}
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher("/listProduct.jsp");
		rd.forward(req, resp);

		resp.sendRedirect((req.getServletContext().getContextPath() + "/auth/addProduct.jsp"));
	}
	
	
}
