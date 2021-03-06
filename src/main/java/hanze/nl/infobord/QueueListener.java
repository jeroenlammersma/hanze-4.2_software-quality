package hanze.nl.infobord;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {
	private String consumerName;

	public QueueListener(String consumerName) {
		this.consumerName = consumerName;
		InfoBoard infoBoard = InfoBoard.getInfoBoard();
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				InfoBoard infoboard = InfoBoard.getInfoBoard();
				infoboard.processedMessage(text);
				infoboard.setLines();
			} else {
				System.out.println("Consumer(" + consumerName + "): Received: " + message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
