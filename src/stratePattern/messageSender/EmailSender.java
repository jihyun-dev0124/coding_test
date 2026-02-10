package stratePattern.messageSender;

//@Component
public class EmailSender implements MessageSender {
    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }

    @Override
    public void send(SendRequest sendRequest) {
        System.out.println("Email sender");
    }
}
