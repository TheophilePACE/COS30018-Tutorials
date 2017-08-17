package cos30018.week3;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.core.behaviours.TickerBehaviour;

public class TWAgent extends Agent {
	TickerBehaviour t;
	WakerBehaviour w;
	boolean continueTicking ;
	protected void setup() {
		//SOLUTION 1: boolean.
		//continueTicking = true; 
		System.out.println("Agent " + getLocalName() + " started.");
		// Behaviour that is called every second
		t = new TickerBehaviour(this, 1000) {
			protected void onTick() {
				//if(continueTicking == false)
				//	return;
				System.out.println("Agent " + myAgent.getLocalName() + ": tick=" +
						getTickCount());
			}
		};
		// Behaviour that is called after an elapsed timeout of 20 seconds
		w = new WakerBehaviour(this, 5000) {
			protected void handleElapsedTimeout() {
				System.out.println("Agent " + myAgent.getLocalName() + ": It's wakeup-time. Bye...");
				//Solution 2 : remove the behaviour
				removeBehaviour(t);
				//continueTicking = false;
			}
		};
		// Add the TickerBehaviour
		addBehaviour(t);
		// Add the WakerBehaviour
		addBehaviour(w);
	}
}