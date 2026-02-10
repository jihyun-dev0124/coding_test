package stratePattern;


import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessateSenderPattern {
    public enum Channel{
        SMS, EMAIL, PUSH
    }
    public record SendRequest(Channel channel, String to, String title, String body){}

    interface MessageSender{
        Channel getChannel();
        void send(SendRequest request);
    }

    static class SmsSender implements MessageSender{
        @Override
        public Channel getChannel() {
            return Channel.SMS;
        }

        @Override
        public void send(SendRequest request) {
            System.out.println("Sending Sms message: " + request.body);
        }
    }

    static class EmailSender implements MessageSender{
        @Override
        public Channel getChannel() {
            return Channel.SMS;
        }

        @Override
        public void send(SendRequest request) {
            System.out.println("Sending Sms message: " + request.body);
        }
    }
    
    static class MessageSenderRegistry {
        private final Map<Channel, MessageSender> mapping;

        //스프링에서는 컴포넌트로 등록된 MessageSender구현체들을 찾아 list로 만들어 주입함.
        /*public MessageSenderRegistry(List<MessageSender> senders) {
            EnumMap<Channel, MessageSender> map = new EnumMap<>(Channel.class);
            for (MessageSender sender : senders) {
                map.put(sender.getChannel(), sender);
            }
            this.mapping = Map.copyOf(map);
        }*/

        public MessageSenderRegistry() {
            EnumMap<Channel, MessageSender> map = new EnumMap<>(Channel.class);
            map.put(Channel.SMS, new SmsSender());
            map.put(Channel.EMAIL, new SmsSender());
            this.mapping = Map.copyOf(map);
        }

        public MessageSender get(Channel channel){
            MessageSender messageSender = mapping.get(channel);
            if(messageSender == null){
                throw new IllegalArgumentException("Unknown channel: " + channel);
            }
            return messageSender;
        }
    }

    static class MessageService{
        private final MessageSenderRegistry registry;
        public MessageService(MessageSenderRegistry registry){
            this.registry = registry;
        }

        public void send(SendRequest request){
            registry.get(request.channel).send(request);
        }
    }

    public static void main(String[] args) {
        MessageService service = new MessageService(new MessageSenderRegistry());
        SendRequest request = new SendRequest(Channel.SMS, "0101234456", "SMS", "SMS발송~");
        service.send(request);
    }
}
