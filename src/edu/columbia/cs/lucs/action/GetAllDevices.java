package edu.columbia.cs.lucs.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.columbia.cs.lucs.service.ZigBeeService;
import edu.columbia.cs.lucs.service.ZigBeeServiceFactory;

/**
 * Servlet implementation class GetAllDevices
 */
@WebServlet("/GetAllDevices")
public class GetAllDevices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllDevices() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ZigBeeService zigBeeService = ZigBeeServiceFactory.getInstance();
		
		response.getWriter().print(zigBeeService.getAllDevicesAsXML());
	}

}
