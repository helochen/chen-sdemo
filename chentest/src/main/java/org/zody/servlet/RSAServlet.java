package org.zody.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zody.RSAUtils;

/**
 * Servlet implementation class RSAServlet
 */
public class RSAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RSAServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Served at:" + request.getSession().getServletContext().getRealPath("/"));
		HashMap<String, Object> map = null;
		try {
			map = RSAUtils.getKeys();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");

		// 模
		String modulus = publicKey.getModulus().toString();
		// 公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		// 私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();

		// 使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
		RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);

		request.setAttribute("RSAPub", pubKey.getPublicExponent().toString(16));
		request.setAttribute("RSAPKey", pubKey.getModulus().toString(16));

		HttpSession session = request.getSession(true);
		session.setAttribute("priKey", priKey);
		
		System.out.println("公有Key:" + pubKey.getPublicExponent().toString(16));
		System.out.println("私有Key:" + priKey.getModulus().toString());
		// 明文

		request.getRequestDispatcher("index.jsp").forward(request, response);
		// response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do post");
		String miwen = request.getParameter("result");
		System.out.println(miwen);
		///解密
		String mingwen = null;
		Object priKey = request.getSession().getAttribute("priKey");
		try {
			mingwen = RSAUtils.decryptByPrivateKey(miwen, (RSAPrivateKey) priKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(mingwen);
		request.getSession().removeAttribute("priKey");
	}

}
