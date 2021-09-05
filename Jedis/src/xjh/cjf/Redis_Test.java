package xjh.cjf;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Redis_Test {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.23.131",6379);
        System.out.println("连接成功");
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
