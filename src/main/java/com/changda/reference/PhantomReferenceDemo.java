package com.changda.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @program: JucAndJvm
 * @classname: PhantomReferenceDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-31 20:29
 **/
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//        ReferenceQueue<Object> referenceQueue2 = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>(new String("I am here"), referenceQueue);
//        SoftReference<String> softReference = new SoftReference<>("I am here1", referenceQueue2);

        System.out.println("phantomReference.get() = " + phantomReference.get());
        System.gc();

        Reference<?> reference;
        while ((reference = referenceQueue.poll()) != null) {
            if (reference == phantomReference){
                System.out.println("被回收了");
            }
        }

//        while ((reference = referenceQueue2.poll()) != null) {
//            if (reference == softReference){
//                System.out.println("被回收了");
//            }
//        }
    }
}
