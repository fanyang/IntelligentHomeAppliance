package edu.columbia.cs.lucs.service;



public interface ZigBeeService {

	String getAllDevicesAsXML();
	
	void addRoom(String roomName);
	
	void addDevice(String deviceName, String roomName);
	
	void addAttribute(String attributeName, String attributeValue, 
			String roomName, String deviceName);
	
	void updateAttribute(String attributeName, String attributeValue, 
			String roomName, String deviceName);
	
}