package cn.wangjie.mq.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-08-27 17:45
 **/
public interface OrderBinder {
    String OUTPUT = "order-out";
    @Output(OrderBinder.OUTPUT)
    MessageChannel output();
}
