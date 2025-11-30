package tn.esprit.tpcafeziedsnoussi.config.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AspectClass {

    private static final Logger log = LoggerFactory.getLogger(AspectClass.class);

    // -------- Pointcut definitions (reusable) --------
    // all public methods in controllers package
    @Pointcut("execution(public * tn.esprit.tpcafeziedsnoussi.controllers..*(..))")
    public void controllerMethods() {}

    // all public methods in services package
    @Pointcut("execution(public * tn.esprit.tpcafeziedsnoussi.services..*(..))")
    public void serviceMethods() {}

    // all public methods in repositories package
    @Pointcut("execution(public * tn.esprit.tpcafeziedsnoussi.repositories..*(..))")
    public void repositoryMethods() {}

    // a composite pointcut for the application layer (controllers + services)
    @Pointcut("controllerMethods() || serviceMethods()")
    public void appLayer() {}

    // -------- Example Advices --------

    // Before: log method entry and arguments for controller methods
    @Before("controllerMethods()")
    public void logControllerEntry(JoinPoint joinPoint) {
        try {
            String signature = joinPoint.getSignature().toShortString();
            Object[] args = joinPoint.getArgs();
            log.info("[AOP][Before][Controller] Entering {} with args={}", signature, Arrays.toString(args));
        } catch (Exception e) {
            // Advice should never throw — swallow and log at debug level
            log.debug("[AOP][Before] Exception while logging controller entry", e);
        }
    }

    // AfterReturning: log results for service methods
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logServiceExit(JoinPoint joinPoint, Object result) {
        try {
            String signature = joinPoint.getSignature().toShortString();
            log.info("[AOP][AfterReturning][Service] {} returned: {}", signature, result);
        } catch (Exception e) {
            log.debug("[AOP][AfterReturning] Exception while logging service exit", e);
        }
    }

    // AfterThrowing: log exceptions thrown by repository methods
    @AfterThrowing(pointcut = "repositoryMethods()", throwing = "ex")
    public void logRepositoryException(JoinPoint joinPoint, Throwable ex) {
        try {
            String signature = joinPoint.getSignature().toShortString();
            log.error("[AOP][AfterThrowing][Repository] Exception in {}: {}", signature, ex.toString());
        } catch (Exception e) {
            log.debug("[AOP][AfterThrowing] Exception while logging repository exception", e);
        }
    }

    // Around: measure execution time for service methods and proceed
    @Around("serviceMethods()")
    public Object measureServiceExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long duration = System.currentTimeMillis() - start;
            try {
                String signature = pjp.getSignature().toShortString();
                // log at debug for durations to avoid noisy logs in production; adjust as needed
                log.debug("[AOP][Around][Service] {} executed in {} ms", signature, duration);
            } catch (Exception e) {
                log.debug("[AOP][Around] Exception while logging execution time", e);
            }
            return result;
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            try {
                String signature = pjp.getSignature().toShortString();
                log.error("[AOP][Around][Service] {} failed after {} ms with exception: {}", signature, duration, t.toString());
            } catch (Exception e) {
                log.debug("[AOP][Around] Exception while logging failure", e);
            }
            throw t;
        }
    }

    // Example of matching methods annotated with a specific annotation (e.g., @Deprecated)
    @Before("@annotation(java.lang.Deprecated)")
    public void beforeDeprecated(JoinPoint jp) {
        try {
            log.warn("[AOP] Calling deprecated method: {}", jp.getSignature().toShortString());
        } catch (Exception e) {
            log.debug("[AOP] Exception in beforeDeprecated advice", e);
        }
    }

}
