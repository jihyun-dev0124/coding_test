package stratePattern.messageSender;

// @Component (스프링 빈으로 등록)
public class SmsSender implements MessageSender {
    @Override
    public Channel getChannel() {
        return Channel.SMS;
    }

    @Override
    public void send(SendRequest request) {
        System.out.println("SMS sender");
    }
}
