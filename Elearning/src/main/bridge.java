package main;

import engine.AdaptiveLPSEngine;

public class bridge {

    /**
     * @param args the command line arguments
     */
    public static CentralSystem system = new CentralSystem();
    public static AdaptiveLPSEngine engine = new AdaptiveLPSEngine();
    
    public static final String JAVABRIDGE_PORT = "8087";
    static final php.java.bridge.JavaBridgeRunner runner = php.java.bridge.JavaBridgeRunner.getInstance(JAVABRIDGE_PORT);   
     
    public static void main(String[] args) throws InterruptedException
    {
    	system.run();
    	if (runner != null) {
    		runner.waitFor();
    	} else {
    		System.out.println("\nCould not get JavaBridge Instance");
    	}
        System.exit(0);
    }
    
}
