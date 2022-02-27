package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// configuration에 등록하거나 or @Component. 보통 aop는 config에 등록해서 사용
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // targeting hellospring 밑의 패키지에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            return joinPoint.proceed();
            // 아니면 중간에 intercepting해서 분기할 수도 있다.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString() + " "+ timeMs + "ms");
        }

    }
}
