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
import com.clyr.domain.Product;
import com.clyr.domain.U_AccessToken;
import com.clyr.domain.User;
import com.clyr.service.IOrderService;
import com.clyr.service.IProductService;
import com.clyr.service.IUserService;
import com.clyr.service.impl.OrderService;
import com.clyr.service.impl.ProductService;
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		IUserService uservice=new UserService();
		IOrderService oservice=new OrderService();
		IProductService pservice=new ProductService();
		String openId=request.getAttribute("openId").toString();
		User u=uservice.searchByOpenId(openId);
		ArrayList<Order> a_o=oservice.SendedOrder(u.getuId());
		ArrayList<Product> a_p=new ArrayList<Product>();
		ArrayList<User> a_u=new ArrayList<User>();
		for(Order o:a_o)
		{
			Product p_temp=new Product();
			User u_temp=new User();
			p_temp=pservice.searchByPId(o.getProductId());
			u_temp=uservice.searchByUId(o.getSellerId());
			a_p.add(p_temp);
			a_u.add(u_temp);
		}
		JSONArray jao=JSONArray.fromObject(a_o);
		JSONArray jau=JSONArray.fromObject(a_u);
		String str=joinJSONArray(jao,jau);
		JSONArray temp=JSONArray.fromObject(str);
		JSONArray jap=JSONArray.fromObject(a_p);
		str=joinJSONArray(temp,jap);
		JSONArray result=JSONArray.fromObject(str);
		request.setAttribute("sendedOrder", result);
		request.setAttribute("openId", openId);
		request.getRequestDispatcher("/WEB-INF/pages/SendedOrder.jsp").forward(request, response);
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

	/**
     * 返回两个JsonArray的合并后的字符串
     * @param mData
     * @param array
     * @return
     */
    public String joinJSONArray(JSONArray mData, JSONArray array) {
        StringBuffer buffer = new StringBuffer();
        try {
          int len = mData.size();
          for (int i = 0; i < len; i++) {
            JSONObject obj1 = (JSONObject) mData.get(i);
            JSONObject obj2 = (JSONObject) array.get(i);
            if (i == len - 1)
            {
            	String str=obj2.toString().substring(1);
            	buffer.append(obj1.toString()).deleteCharAt(buffer.length()-1).append(",").append(str);
            }
            else
            {
            	String str=obj2.toString().substring(1);
            	buffer.append(obj1.toString()).deleteCharAt(buffer.length()-1).append(",").append(str).append(",");
            }
          }
          buffer.insert(0, "[").append("]");
          return buffer.toString();
        } catch (Exception e) {
        }
        return null;
      }
}
