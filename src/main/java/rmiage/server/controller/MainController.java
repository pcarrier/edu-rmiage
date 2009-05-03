package rmiage.server.controller;

import java.rmi.RemoteException;

import rmiage.server.connection.ConnectionManager;
import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;


public class MainController {

	protected SettingsController settingsController;
	protected ConnectionManager connection;

	@SuppressWarnings("unchecked")
	private SettingsController getSettingsController() {
		SettingsController res = null;
		try {
			String settingsControllerClassName = System.getProperty(
					"rmiage.settingsloader", "rmiage.framework");
			Class SettingsControllerClass = Class.forName(
					settingsControllerClassName).getClass();
			res = (SettingsController) SettingsControllerClass.newInstance();
		} catch (InstantiationException ex) {
			throw new SettingsException(
					"Cannot instantiate the settings controller");
		} catch (IllegalAccessException ex) {
			throw new SettingsException(
					"Illegal access around the settings controller");
		} catch (ClassNotFoundException ex) {
			throw new SettingsException(
					"Settings controller class cannot be found!");
		}
		
		return res;
	}

	/**
	 * Initialize the server with command line args.
	 * 
	 * @param cmdLineParams
	 *            params from command line.
	 * @throws RemoteException 
	 */
	public void init(String[] cmdLineParams) throws InterruptedException, RemoteException {
		
		this.settingsController = getSettingsController();
		int rmiport = this.settingsController.getRmiPort();

		if (this.settingsController != null) {
			
			this.connection = new ConnectionManager(rmiport);
			this.connection.bind("rmi://127.0.0.1/login", new StandardLoginController());
		}
	}


	public static void main(String[] args){
		MainController controller = new MainController();
		try {
			controller.init(args);
			
		} catch (InterruptedException e) {
			System.out.println("Received ^C");
			controller.connection.stop();
		} catch (SettingsException e) {
			System.out.println(e.getMessage());
			System.out.println("Server can't start.");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public void finalize(){
		System.out.println("Server stopped");
	}
	
}