package stratePattern.messageSender;

public record SendRequest(Channel channel, String to, String title, String body) {
}
