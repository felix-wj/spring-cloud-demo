package cn.wangjie.mq.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-08-27 17:47
 **/
public interface OrderInBinder {
    String INPUT = "order-in";
    @Input(OrderInBinder.INPUT)
    SubscribableChannel input();
}
