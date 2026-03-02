package hk.users;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import hk.dao.UserDao;
import hk.models.Users;



/**
 * Servlet implementation class UploadPic
 */
@WebServlet("/UploadPic")
@MultipartConfig
public class UploadPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao dao;

	@Override
	public void init() throws ServletException {

		dao = new UserDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	public UploadPic() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hs = request.getSession(false);// create a session
		Users user = (Users) hs.getAttribute("userdetails");

		ServletContext sc = getServletContext();//interface and it gets created at the time of runnung project

		String project_path = sc.getRealPath("/");//absolute path

		System.out.println("old path " + project_path);

		String userid=user.getId();
		
		String newpath = project_path +userid;

		System.out.println("newpath " + newpath);

		File f = new File(newpath);
		
				
		if (!f.exists()) {
			f.mkdir();
			System.out.println("ditecory created");
		}

		Part p = request.getPart("fileupload");//control name
		String filename = p.getSubmittedFileName();//will give the name of the uploaded file

		System.out.println("uploaded file name is " + filename);
		  InputStream is=p.getInputStream();
		  		  
		  boolean status= dao.uploadDocs(is,f,filename);
		  
		  if(status==true)
		  {
			  
			  System.out.println("pic uploaded");
			  int updatestatus=	dao.updateProfilePic(filename, userid);
			  if(updatestatus>0)
			  {
				 user.setPicname(filename);
				hs.setAttribute("userdetails",user);
				  response.sendRedirect("/HouseKeeping/user/user_viewprofile.jsp"); }  
			  }
			  
		  }
		

	}


