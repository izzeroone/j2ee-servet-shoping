package com.example.controller;

import com.example.dao.HistoryDAOImpl;
import com.example.dao.ProductDAOImpl;
import com.example.dao.UserDAOImpl;
import com.example.model.Cart;
import com.example.model.History;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Servlet implementation class ConfirmServlet
 */
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
    private ProductDAOImpl productDAO = new ProductDAOImpl();  
    private HistoryDAOImpl historyDAO = new HistoryDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User u = userDAO.getUser(username);
		//lấy time lưu csdl
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp tdate = new java.sql.Timestamp(calendar.getTime().getTime());
		
		double total = 0;
		ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);

		   if(cart!=null){
			   for(Cart c : cart){
				   total = total + (c.getQuantity() * productDAO.getProduct(c.getP().getMa_san_pham()).getGia_ban());
				   History h = new History(0, u.getUser_id(), c.getP().getMa_san_pham(), tdate, c.getQuantity(), (c.getQuantity() * productDAO.getProduct(c.getP().getMa_san_pham()).getGia_ban()));
				   historyDAO.addHistory(h);
			   }
		   }
		   cart.clear();
		   request.getSession().setAttribute("cart", cart);
		   response.sendRedirect("/shop/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
