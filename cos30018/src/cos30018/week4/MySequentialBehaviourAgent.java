package cos30018.week4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;

public class MySequentialBehaviourAgent extends Agent {
	protected void setup() {
		System.out.println(getLocalName() + ": I have been created"); 
		SequentialBehaviour sb = new SequentialBehaviour();
		MyBehaviour a = new MyBehaviour(1, 3);
		MyBehaviour b = new MyBehaviour(2, 10);
		MyBehaviour c = new MyBehaviour(3, 2); sb.addSubBehaviour(a);
		sb.addSubBehaviour(b);
		sb.addSubBehaviour(c);
		this.addBehaviour(sb);
	}

	private class MyBehaviour extends Behaviour { 
		private int id;
		private int cycles; 
		private int currentCycle;
		
		MyBehaviour(int id, int cycles) {
			this.id = id;
			this.cycles = cycles; 
			this.currentCycle = 0;
		}
		@Override
		public void action() {
			System.out.println("Behaviour " + id + " executing cycle " + ++currentCycle);
		}
		public boolean done() {
			return (currentCycle==cycles);
		} 
	}


}
