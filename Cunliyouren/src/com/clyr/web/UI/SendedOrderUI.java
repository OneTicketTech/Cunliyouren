package com.clyr.web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.clyr.domain.Order;
import com.clyr.domain.U_AccessToken;
import com.clyr.domain.User;
import com.clyr.service.IOrderService;
import com.clyr.service.IUserService;
import com.clyr.service.impl.OrderService;
import com.clyr.service.impl.UserService;
import com.clyr.utils.WechatUtils;

public class SendedOrderUI extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendedOrderUI() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		U_AccessToken token=WechatUtils.getUAccessToken("SendedOrderUI");
		IUserService uservice=new UserService();
		IOrderService oservice=new OrderService();
		User u=uservice.searchByAccessToken(token.getAccess_token());
		if(u==null) 
		{
			JSONObject jt=JSONObject.fromObject(token);
			request.setAttribute("token", jt);
			request.getRequestDispatcher("/WEB-INF/pages/LoginUI").forward(request, response);
		}
		else{
			ArrayList<Order> a_o=oservice.SendedOrder(u.getuId());
			JSONArray ja=JSONArray.fromObject(a_o);
			request.setAttribute("sendedOrder", ja);
			request.getRequestDispatcher("/WEB-INF/pages/SendedOrder.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
