package com.HaydiKodlayalim.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    @Before("execution(* com.HaydiKodlayalim.aop.service.*.*(..))")
    public void mesajVerMetodundanOnce(JoinPoint joinPoint) {
        System.out.println("[AOP @Before] mesajVer() metodundan önce yakalandı.");
        System.out.println("  Parametreler: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("  Hedef: " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @After("execution(* com.HaydiKodlayalim.aop.service.*.*(..))")
    public void mesajVerMetodundanSonra(JoinPoint joinPoint) {
        System.out.println("[AOP @After] mesajVer() metodu tamamlandıktan sonra çalıştı.");
    }

    @AfterReturning(pointcut = "execution(* com.HaydiKodlayalim.aop.service.MesajService.mesajVer(..))", returning = "sonuc")
    public void mesajVerSonrasiDonenDeger(JoinPoint joinPoint, Object sonuc) {
        System.out.println("[AOP @AfterReturning] Metot başarıyla döndü. Dönen Değer: " + sonuc);
    }

    @AfterThrowing(pointcut = "execution(* com.HaydiKodlayalim.aop.service.MesajService.mesajVer(..))", throwing = "istisna")
    public void mesajVerHataDurumu(JoinPoint joinPoint, Exception istisna) {
        System.err.println("[AOP @AfterThrowing] Metot hata fırlattı! Hata mesajı: " + istisna.getMessage());
    }

    @Around("execution(* com.HaydiKodlayalim.aop.service.IkinciMesajService.*(..))")
    public Object ikinciMesajAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long baslangic = System.currentTimeMillis();
        System.out.println("[AOP @Around] Metot çağrısı öncesi - " + joinPoint.getSignature().getName());

        Object sonuc = joinPoint.proceed();

        long sure = System.currentTimeMillis() - baslangic;
        System.out.println("[AOP @Around] Metot çağrısı bitti. Geçen süre: " + sure + " ms");
        return sonuc;
    }
}
