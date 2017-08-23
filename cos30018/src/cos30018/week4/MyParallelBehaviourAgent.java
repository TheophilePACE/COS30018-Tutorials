package cos30018.week4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;

public class MyParallelBehaviourAgent extends Agent{
	protected void setup() {
		//WHEN_ANY ==> When any of the subBehaviour is over, the agent is over
		//WHEN_ALL ==> When all the subBehaviours are over , the agent is over
		ParallelBehaviour pb = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
		
		MyBehaviour a = new MyBehaviour(1, 3);
		MyBehaviour b = new MyBehaviour(2, 10);
		MyBehaviour c = new MyBehaviour(3, 2);
		MyBehaviour d = new MyBehaviour(4, 6);

		pb.addSubBehaviour(a);
		pb.addSubBehaviour(b); 
		pb.addSubBehaviour(c); 
		pb.addSubBehaviour(d);
		
		addBehaviour(pb);
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
