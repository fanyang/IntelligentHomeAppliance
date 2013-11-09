package edu.columbia.cs.lucs.service;

import java.io.*;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

public class ZigBeeServiceImpl implements ZigBeeService {
	
	private String fileName;
	private Model model;
    private static final String NS = "lucs://www.cs.columbia.edu/";
	
    
	public ZigBeeServiceImpl(String fileName) {
		this.fileName = fileName;
		loadModel();
	}
	
	
	private void loadModel() {
		// Create an empty model
		model = ModelFactory.createDefaultModel();
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(fileName);
		
		// Read the RDF/XML file
		model.read(in, null);
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    private void writeModel() {
    	try(FileOutputStream fos = new FileOutputStream(fileName);) {
    		model.write(fos, "RDF/XML-ABBREV");
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
    

	@Override
	public String getAllDevicesAsXML() {
		byte[] buffer = new byte[8000];
		int length = 0;
		try(
				FileInputStream fis = new FileInputStream(fileName);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			) {
			
			while ((length = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			return baos.toString("UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	
	@Override
	public void addRoom(String roomName) {

		Resource zigbee = model.createResource(NS + "zigbee");
        Resource room = model.createResource(NS + roomName);
        Property area = model.createProperty(NS + "area" );
        model.add(zigbee, area, room);
        
        writeModel();
	}
	

	@Override
	public void addDevice(String deviceName, String roomName) {
		Resource zigbee = model.createResource(NS + "zigbee");
        Resource room = model.createResource(NS + roomName);
        Property area = model.createProperty(NS + "area");
        model.add(zigbee, area, room);
        Property resource = model.createProperty(NS + "resource");
        Resource device = model.createResource(NS + deviceName);
        model.add(room, resource, device);
        
        writeModel();

	}
	

	@Override
	public void addAttribute(String attributeName, String attributeValue,
			String roomName, String deviceName) {

		Resource zigbee = model.createResource(NS + "zigbee");
        Resource room = model.createResource(NS + roomName);
        Property area = model.createProperty(NS + "area");
        model.add(zigbee, area, room);
        Property resource = model.createProperty(NS + "resource");
        Resource device = model.createResource(NS + deviceName);
        model.add(room, resource, device);
        Property attrib = model.createProperty(NS + attributeName);
        device.addProperty(attrib, attributeValue);
        
        writeModel();
	}

	@Override
	public void updateAttribute(String attributeName, String attributeValue,
			String roomName, String deviceName) {

        Resource zigbee = model.createResource(NS + "zigbee");
        Resource room = model.createResource(NS + roomName);
        Property area = model.createProperty(NS + "area");        
        Property resource = model.createProperty(NS + "resource");
        Resource device = model.createResource(NS + deviceName);
        Property attrib = model.createProperty(NS + attributeName);
        model.removeAll(device, attrib, null);
        
        model.add(zigbee, area, room);
        model.add(room, resource, device);   
        device.addProperty(attrib, attributeValue);
        
        writeModel();
	}

	

}
