package cos30018.week3;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
/**
 * MyCounterAgent implements the methods exposed in the MyAgentInterface
 *
 */
public class MyCounterAgent extends Agent implements MyAgentInterface {
	private TickerBehaviour counter;
	/**
	 * Register the O2AInterface that must be accessible by an external program through the O2A interface
	 */
	public MyCounterAgent() {
		// Register the interface that must be accessible by an external program through the O2A interface
		registerO2AInterface(MyAgentInterface.class, this);
	}
	protected void setup() {
		System.out.println(getLocalName() + ": I have been created");
	}
	/**
	 * Activate counter. This method adds a ticker behavior that
	 * increases the counter every second and prints the value
	 */
	@Override
	public void activateCounter() {
		System.out.println(getLocalName() + ": I have been asked to start counting");
		counter = new TickerBehaviour(this, 1000) {
			private static final long serialVersionUID = 1L;
			public void onStart() {
				super.onStart();
				System.out.println(getLocalName()+ ": Start counting");
			}
			protected void onTick() {
				System.out.println(getLocalName() + ": Counter - " + getTickCount());
			}
			public int onEnd() {
				System.out.println(getLocalName() + ": Stop counting");
				return super.onEnd();
			}
		};
		addBehaviour(counter);
	}
	/**
	 * Deactivate counter. This is method removes the ticker behavior
	 */
	public void deactivateCounter() {
		System.out.println(getLocalName() + ": I have been asked to stop counting");
		counter.stop(); // stopping the ticker behaviour
	}
}