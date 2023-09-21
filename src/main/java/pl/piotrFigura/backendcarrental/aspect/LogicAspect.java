package pl.piotrFigura.backendcarrental.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class LogicAspect {

    //todoApp

    private final Timer carCreateGroupTimer;

    LogicAspect(final MeterRegistry meterRegistry) {
        carCreateGroupTimer = meterRegistry.timer("logic.product.car.update");
    }

    @Around("execution(* pl.piotrFigura.backendcarrental.service.CarServiceImpl.updateCar(..))")
    Object aroundCreateCar(ProceedingJoinPoint joinPoint) {
        return carCreateGroupTimer.record(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                }
                throw new RuntimeException(throwable);
            }
        });
    }

}
