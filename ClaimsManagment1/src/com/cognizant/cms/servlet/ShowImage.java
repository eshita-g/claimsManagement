package com.cognizant.cms.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.cms.dao.ConnectionHandler;


@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowImage() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String memId="1";
		try {
	   		   Connection con=ConnectionHandler.getConnection();
	   		   System.out.println();
	   			PreparedStatement stmt=con.prepareStatement("select * from member where mem_id='"+memId+"'");
				ResultSet rs=stmt.executeQuery(); 
				Blob blob=null;
	   			while(rs.next()){
	   				blob=rs.getBlob("file");
	   	   		 InputStream inputStream = blob.getBinaryStream();
	   	   	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 byte[] buffer = new byte[4096];
                 int bytesRead = -1;
                 
                 while ((bytesRead = inputStream.read(buffer)) != -1) {
                     outputStream.write(buffer, 0, bytesRead);                  
                 }
                  
                 byte[] imageBytes = outputStream.toByteArray();
                 String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 inputStream.close();
                 outputStream.close();
                 request.setAttribute("imm", base64Image);
                 RequestDispatcher requestDispatcher = request.getRequestDispatcher("file_upload.jsp");
                 requestDispatcher.forward(request, response);
	   			}
	   		
	   			} catch (SQLException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
