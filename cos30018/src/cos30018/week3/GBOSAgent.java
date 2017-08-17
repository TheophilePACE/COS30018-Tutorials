package cos30018.week3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class GBOSAgent extends Agent {
	protected void setup() {
		System.out.println(getLocalName() + ": I have been created");
		addBehaviour(new MyMultiStepBehaviour());
		addBehaviour(new MyOneShotBehaviour());
		System.out.println(getLocalName() + ": I have added my behaviours");
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
			switch(step) {
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
			return super.onEnd();
		}
	}
}