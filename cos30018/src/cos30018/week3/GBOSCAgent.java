package cos30018.week3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;

//done() method of MyCyclicBehaviour agent always return false --> new cycle of execution each time (see PDF Week 3)
public class GBOSCAgent extends Agent {
	protected void setup() {
		System.out.println(getLocalName() + ": I have been created");
		// Add Cyclic Behaviour
		addBehaviour(new MyCyclicBehaviour());
		// Add OneShot Behaviour
		addBehaviour(new MyOneShotBehaviour());
		// Add Generic Behaviour
		addBehaviour(new MyMultiStepBehaviour());
		System.out.println(getLocalName() + ": I have added my behaviours");
	}
	protected void takeDown() {
		System.out.println(getLocalName() + ": Preparing to die");
		// do cleanup
	}
	
	
	private class MyCyclicBehaviour extends CyclicBehaviour {
		MyCyclicBehaviour() {
			System.out.println(getBehaviourName() + ": I have been created");
		}
		public void action() {
			System.out.println(getBehaviourName() + ": Cycling");
		}
	}
	private class MyOneShotBehaviour extends OneShotBehaviour {
		MyOneShotBehaviour() {
			System.out.println(getBehaviourName() + ": I have been created");
		}
		@Override
		public void action() {
			System.out.println(getBehaviourName() + ": I will be executed only once");
		}
	}
	
	
	private class MyMultiStepBehaviour extends Behaviour {
		private int step = 1;
		MyMultiStepBehaviour() {
			System.out.println(getBehaviourName() + ": I have been created");
		}
		@Override
		public void action() {
			switch (step) {
			case 1:
				System.out.println(getBehaviourName() + ": Operation 1");
				break;
			case 2:
				System.out.println(getBehaviourName() + ": Operation 2");
				break;
			case 3:
				System.out.println(getBehaviourName() + ": Operation 3");
				break;
			case 4:
				System.out.println(getBehaviourName() + ": Operation 4");
				break;
			}
			step++;
		}
		@Override
		public boolean done() {
			return step == 5;
		}
		public int onEnd() {
			System.out.println(this.getBehaviourName() + ": I have finished executing");
			System.out.println(this.getBehaviourName() + ": Terminating agent");
			//without this, cycling continue over and over
			myAgent.doDelete();
			return super.onEnd();
		}
	}
}