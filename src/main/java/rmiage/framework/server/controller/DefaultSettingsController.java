/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.server.controller;

import java.rmi.registry.Registry;
import rmiage.server.storage.BackAssDescription;

/**
 *
 * @author gcarrier
 */
public class DefaultSettingsController implements ISettingsController {

    public void giveCommandLine(String[] cmdlineParams) {
        // unused
    }

    public int getRmiPort() {
        return Registry.REGISTRY_PORT;
    }

    public Class[] getModules() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BackAssDescription[] getBackendAssociationsDescriptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public String[] getModulesDescriptions() {
		// TODO Auto-generated method stub
		return null;
	}

}