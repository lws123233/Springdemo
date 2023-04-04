package com.example.springdemo.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFutureTestTwo();
    }

    static void CompletableFutureTestTwo(){
        MyThreadPool myThreadPool=new MyThreadPool(new ThreadGroup("MyThreadGroup"));

        //异步不带返回值
        CompletableFuture<Void> 执行了异步不带返回值1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("执行了异步不带返回值1");
            }
        });

        CompletableFuture<Void> 执行了异步不带返回值2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                log.info("执行了异步不带返回值2");
            }
        });
        try {
            log.info(执行了异步不带返回值1.get().toString());
            log.info(执行了异步不带返回值2.get().toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                log.info("进入了异步带返回值1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "执行了异步带返回值1";
            }
        });

        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                log.info("进入了异步带返回值2");
                return "执行了异步带返回值2";
            }
        });

        try {
            log.info(stringCompletableFuture1.get());
            log.info(stringCompletableFuture2.get());

            /**
             * 结果处理
             */
            //可以指定完成异步任务（无论正常完成还是抛出异常）后做某些动作，该动作支持异步，支持传入线程池
           stringCompletableFuture1.whenComplete(new BiConsumer<String, Throwable>() {
                @Override
                public void accept(String s, Throwable throwable) {
                    //正常返回时throwable为空，异常时s为空
                    String massage="";
                    massage= s==null ? massage+throwable.getMessage() : massage+s;
                    s=s+"改变";
                    log.info("进入了 whenComplete 方法 " + massage);
                }
            });


            /**
             * 结果转换，返回同一个CompletableFuture
             */
            System.out.println(CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    int res = 100;
                    log.info("第一次运算" + res);
                    return 100;
                }
            }).thenApplyAsync(res -> {
                res = res + 100;
                log.info("第二次运算" + res);
                return res;
            }).get());
            /**
             * 结果转换，返回一个新的CompletableFuture
             */
            System.out.println(CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    int res = 100;
                    log.info("第一次运算" + res);
                    return res;
                }
            }).thenComposeAsync(new Function<Integer, CompletionStage<Integer>>() {
                @Override
                public CompletionStage<Integer> apply(Integer integer) {
                    return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                        @Override
                        public Integer get() {
                            log.info("第二次运算" + (integer + 100));
                            return integer + 100;
                        }
                    });
                }
            }).get());


            CompletableFuture<String> stringCompletableFuture$0 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("执行第一个任务");
                return "执行第一个任务";
            }, myThreadPool.executorService);

            CompletableFuture<String> stringCompletableFuture$1 = CompletableFuture.supplyAsync(() -> {
                log.info("执行第二个任务");
                return "执行第二个任务";
            },myThreadPool.executorService);

            CompletableFuture<String> stringCompletableFuture$2 = CompletableFuture.supplyAsync(() -> {
                log.info("执行了第三个任务");
                return "执行了第三个任务";
            }, myThreadPool.executorService);


            CompletableFuture.anyOf(stringCompletableFuture$0,stringCompletableFuture$1,stringCompletableFuture$2)
                    .whenCompleteAsync((s,th)->{
                        if(null==th){
                            log.info("任务完成"+" "+s);
                        }
                    });


//            List<String> collect = Stream.of(stringCompletableFuture$0, stringCompletableFuture$1, stringCompletableFuture$2)
//                    .map(CompletableFuture::join).collect(Collectors.toList());

           // log.info(collect.toString());

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


   public static class MyThreadPool{
        private ExecutorService executorService;
        private ThreadGroup threadGroup;
        MyThreadPool(ThreadGroup threadGroup){
            this.threadGroup=threadGroup;
            this.executorService=Executors.newCachedThreadPool(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(threadGroup,r,"myThreadPool");
                }
            });
        }

   }

}
