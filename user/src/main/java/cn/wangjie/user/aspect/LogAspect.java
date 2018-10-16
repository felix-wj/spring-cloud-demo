package cn.wangjie.user.aspect;

import cn.wangjie.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: SpringCloudDemo
 * @description: 非侵入式，根据需要在切入点不同位置的切入内容
 * 使用@Before在切入点开始处切入内容
 * 使用@After在切入点结尾处切入内容
 * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 * @author: WangJie
 * @create: 2018-08-16 16:52
 **/
@Aspect
@Component
@Slf4j
@Order
public class LogAspect {

    private ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("execution(public * cn.wangjie.user.controller..*.*(..))")
    public void webLog() {
        //测试发现，切入点只是作为标记作用，方法体不执行
        log.info("方法 webLog()");
    }

    @Before("webLog()")
    @Order(1)
    public void doBefore(JoinPoint point) {

        long time = System.currentTimeMillis();
        log.info("@Before：时间为{}", time);
        beginTime.set(time);

        log.info("@Before：目标方法为：{}.{}" ,point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        log.info("@Before：参数为：{}", Arrays.toString(point.getArgs()));

        log.info("@Before：被织入的目标对象为：{}", point.getTarget());

    }

    /**
     *
     * @param point
     * @return 此处的返回数据可以替换切面所切方法的返回数据，而不仅仅是修改返回数据的内部数据
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        log.info("@Around方法执行之前");
        //获得参数
        Object[] args = point.getArgs();
        //改变参数
        args = Arrays.stream(args).map(arg -> {
            if (arg.getClass() == Integer.class) {
                return 100;
            }
            return arg;
        }).toArray();
        //执行，获取结果
        Object returnValue = point.proceed(args);
        log.info("@Around方法执行之后");
        log.info("@Around被织入的目标对象为：{}", point.getTarget());
        log.info("执行结果为：{}",returnValue);
        //修改结果
   /*     ((User) returnValue).setAge(12);
        User user = new User();
        user.setId(3);
        user.setName("张三-doAfter");*/
        return returnValue;
    }



    @After("webLog()")
    public void doAfter(JoinPoint point) {

        log.info("@After：模拟释放资源...");

        log.info("@After：目标方法为：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        log.info("@After：参数为：{}", Arrays.toString(point.getArgs()));

        log.info("@After：被织入的目标对象为：{}", point.getTarget());

        //测试发现，@After返回的数据无效，所以方法类型应该为void
       /* User user = new User();
        user.setId(3);
        user.setName("张三-doAfter");
        return user;*/
    }


    /**
     *此处只能修改返回数据的内部数据，无法替换返回数据
     * @param point
     * @param returnValue
     */
    @AfterReturning(pointcut = "webLog()" , returning = "returnValue")
    public void doAfterRuterning(JoinPoint point, Object returnValue) {

        long time = System.currentTimeMillis();
        log.info("@AfterReturning：方法执行完毕，执行时间为：{}ms",time-beginTime.get());

        log.info("@AfterReturning：目标方法为：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        log.info("@AfterReturning：参数为：{}", Arrays.toString(point.getArgs()));

        log.info("@AfterReturning：被织入的目标对象为：{}", point.getTarget());

        log.info("@AfterReturning：返回值为：" + returnValue);

        log.info("@AfterReturning：被织入的目标对象为：" + point.getTarget());
        //修改返回数据 有效
        //((User) returnValue).setAge(18);



        //测试发现，此方法只能对返回数据内的数据进行修改，无法改变返回数据的地址值，方法类型也应该是void
       /* User user = new User();
        user.setId(3);
        user.setName("张三-doAfter");
        return user;
*/

    }

}
