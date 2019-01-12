package com.lixiaozhuo._01_creating._01_singleton;

/**
 * 双重检查锁实现单例模式(因为JVM底层不兼容,不推荐使用)
 *
 */
public class Singleton3 {

  private static Singleton3 instance = null;

  public static Singleton3 getInstance() {
    if (instance == null) { 
      Singleton3 sc;
      synchronized (Singleton3.class) {
        sc = instance; 
        if (sc == null) { 
          synchronized (Singleton3.class) {
            if(sc == null) { 
              sc = new Singleton3();
            } 
          } 
          instance = sc; 
        } 
      } 
    } 
    return instance; 
  } 

  private Singleton3() {

  } 
    
}
