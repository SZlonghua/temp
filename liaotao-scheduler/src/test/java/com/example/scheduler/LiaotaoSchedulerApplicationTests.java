package com.example.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//@SpringBootTest
public class LiaotaoSchedulerApplicationTests {

    @Test
    void contextLoads() {

        Integer[] data = {1,2,3,4,5,6,7};
        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
            return new ArrayList<Integer>(Arrays.asList(data));
        });


        future.whenComplete((r,e)->{
            new Di(r).pt();

        });





    }

    private class Di {
        List<Integer> list;
        Integer readIndex=0;

        public Di(List<Integer> list) {
            this.list = list;
        }
        public void pt() {
            if(readIndex<list.size()){
                pri(list.get(readIndex)).whenComplete((t,err)->{
                    if(err==null){
                        readIndex = readIndex +1;
                        pt();
                    }else {
                        System.out.println("cuowu");
                    }

                });
            }else System.out.println("wancehng");

        }
    }



    public CompletableFuture<Void> pri(Integer i){
        return CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread()+":"+i);
            System.out.println(i);
        });
    }

}
