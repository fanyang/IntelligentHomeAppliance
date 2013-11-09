package edu.columbia.cs.lucs.service;


public class ZigBeeServiceFactory {
	
	private static ZigBeeService zigBeeService = 
			new ZigBeeServiceImpl("D:\\webapps\\intelligent_home_appliance\\data.xml");
	
	public static ZigBeeService getInstance() {
		return zigBeeService;
	}

}
