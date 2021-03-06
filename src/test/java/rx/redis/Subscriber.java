package rx.redis;

import redis.clients.jedis.Jedis;
import rx.util.functions.Action1;
import rx.util.functions.Func1;

public class Subscriber {

    public static void main(String[] args) throws InterruptedException {
        Jedis j = new Jedis("localhost");
        j.connect();

        System.out.println("Subscribing...");
        RedisPubSub.observe(j, "channel")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.length();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer len) {
                        System.out.println(len);
                    }
                });

    }

}
