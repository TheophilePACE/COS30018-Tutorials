package cos30018.week3;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class MyJADEController {
	public static void main(String[] args) throws StaleProxyException, InterruptedException {
		// Get a hold to the JADE runtime
		Runtime rt = Runtime.instance();
		// Launch the Main Container (with the administration GUI on top) listening on port 8888
		System.out.println(MyJADEController.class.getName() + ": Launching the platform Main Container...");
		Profile pMain = new ProfileImpl(null, 8888, null);
		pMain.setParameter(Profile.GUI, "true");
		ContainerController mainCtrl = rt.createMainContainer(pMain);
		// Wait for some time
		Thread.sleep(10000);
		// Create and start an agent of class CounterAgent
		System.out.println(MyJADEController.class.getName() + ": Starting up a CounterAgent...");
		AgentController agentCtrl = mainCtrl.createNewAgent("CounterAgent", MyCounterAgent.class.getName(), new Object[0]);
		agentCtrl.start();
		// Wait for some time
		Thread.sleep(20000);
		try {
			// Retrieve O2A interface CounterManager1 exposed by the agent to make it activate the counter
			System.out.println(MyJADEController.class.getName() + ": Activating counter");
			MyAgentInterface o2a = agentCtrl.getO2AInterface(MyAgentInterface.class);
			o2a.activateCounter();
			// Wait for some time
			Thread.sleep(20000);
			// Retrieve O2A interface CounterManager2 exposed by the agent to make it deactivate the counter
			System.out.println(MyJADEController.class.getName() + ": Deactivating counter");
			o2a.deactivateCounter();
		}
		catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
}