package cos30018.week4;

import java.util.Iterator;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SenderAgent extends Agent {
	protected void setup()
	{
		// First set-up message receiving behaviour
		CyclicBehaviour messageListeningBehaviour = new CyclicBehaviour(this)
		{
			public void action() {
				ACLMessage msg= receive();
				if (msg!=null) {
					System.out.println(getLocalName()+ ": Received response " +
							msg.getContent() + " from " + msg.getSender().getLocalName());
				}
				block();
			}
		};
		addBehaviour(messageListeningBehaviour);
		// Send messages to two agents whose names are "a1" and "a2" (hard-coded)
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent( "Ping" );
		for (int i = 1; i<=2; i++) {
			msg.addReceiver(new AID("a" + i, AID.ISLOCALNAME) );
		}

		// Send Message (only once)
		System.out.println(getLocalName()+ ": Sending message " + msg.getContent() + " to ");
		Iterator receivers = msg.getAllIntendedReceiver();
		while(receivers.hasNext()) {
			System.out.println(((AID)receivers.next()).getLocalName());
		}
		send(msg);
	}

}
