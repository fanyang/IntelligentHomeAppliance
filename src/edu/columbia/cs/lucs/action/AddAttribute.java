package edu.columbia.cs.lucs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.columbia.cs.lucs.service.ZigBeeService;
import edu.columbia.cs.lucs.service.ZigBeeServiceFactory;

/**
 * Servlet implementation class AddRoom
 */
@WebServlet("/AddAttribute")
public class AddAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAttribute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ZigBeeService zigBeeService = ZigBeeServiceFactory.getInstance();
		String roomName = request.getParameter("room");
		String deviceName = request.getParameter("device");
		String attributeName = request.getParameter("key");
		String attributeValue = request.getParameter("value");
		zigBeeService.addAttribute(attributeName, attributeValue, roomName, deviceName);
		
		request.getRequestDispatcher("GetAllDevices").forward(request, response);
	}

}
