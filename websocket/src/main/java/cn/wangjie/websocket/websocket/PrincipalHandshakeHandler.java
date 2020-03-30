package cn.wangjie.websocket.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * @program: parent
 * @description:
 * @author: WangJie
 * @create: 2020-03-16 11:15
 **/
@Component
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        /**
         * 这边可以按你的需求，如何获取唯一的值，既unicode
         * 得到的值，会在监听处理连接的属性中，既WebSocketSession.getPrincipal().getName()
         * 也可以自己实现Principal()
         */
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletServerHttpRequest.getServletRequest();
            /**
             * 这边就获取你最熟悉的陌生人,携带参数，你可以cookie，请求头，或者url携带，这边我采用url携带
             */
            String key = httpRequest.getParameter("token");

            return new Principal() {
                @Override
                public String getName() {
                    return key;
                }
            };
        }
        return null;
    }
}
