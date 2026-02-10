package stratePattern.messageSender;

public interface MessageSender {
    Channel getChannel();
    void send(SendRequest sendRequest);
}
