package rmiage.server.controller;

import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MainController {

	protected SettingsController settingsController;
	protected Registry registry;

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
	 */
	public void init(String[] cmdLineParams) throws InterruptedException {
		this.settingsController = getSettingsController();
		int rmiport = this.settingsController.getRmiPort();

		if (this.settingsController != null) {
			this.registry = this.createRegistry(rmiport);
		}

	}

	/**
	 * Create the rmiregistry
	 * 
	 * @param port
	 *            the port to use.
	 * @return the rmiregistry.
	 */
	private Registry createRegistry(int port) {
		Registry reg = null;
		try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return reg;
	}

	/**
	 * Launch the rmiregistry
	 */
	private void launchRegistry() {
		if (this.registry != null) {
			// this.registry.bind(name, obj);
		}
	}

	protected void start() throws InterruptedException {
		this.launchRegistry();
		// Something else todo?
		System.out.println("Server Started");
	}

	/**
	 * Stop the server properly
	 */
	private void stop() {
		if (this.registry != null) {
			try {
				//Unbind each bounded uri.
				for (String bound : this.registry.list()) {
						this.registry.unbind(bound);
				}
			} catch (Exception e) {
				System.out.println("An error occured when stopping.");
				e.printStackTrace();
			}
		}
		System.out.println("Server Stoped");
	}

	public static void main(String[] args) {
		MainController ctrl = new MainController(); 
		try {
			ctrl.init(args);
			ctrl.start();
		} catch (InterruptedException e) {
			System.out.println("Received ^C");
			ctrl.stop();
		} catch (SettingsException e) {
			System.out.println(e.getMessage());
			System.out.println("Server can't start.");
		}
	}
}