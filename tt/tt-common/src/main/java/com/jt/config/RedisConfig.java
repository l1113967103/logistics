package com.jt.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	@Value("${redis.nodes}")
	private String redisNodes;
	//4.实现redis集群
	@Bean
	public JedisCluster getCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		String[] node = redisNodes.split(",");
		for (String nodeInfo : node) {
			String[] split = nodeInfo.split(":");
			String host = split[0];
			int port = Integer.parseInt(split[1]);
			nodes.add(new HostAndPort(host, port));
		}
		return new JedisCluster(nodes);
	}
	/*
	 * @Value("${redis.masterName}") private String masterName;
	 * 
	 * @Value("${redis.sentinelNodes}") private String sentinelNodes;
	 * //3.实现redis整合哨兵
	 * 
	 * @Bean public JedisSentinelPool getPool() { String[] nodes =
	 * sentinelNodes.split(","); Set<String> sentinels = new HashSet<>(); for
	 * (String sNode : nodes) { sentinels.add(sNode); } return new
	 * JedisSentinelPool(masterName, sentinels); }
	 */
	/*
	 * //2.redis分片机制的测试
	 * 
	 * @Value("${redis.nodes}") private String nodes;//ip:端口,ip:端口
	 * 
	 * @Bean public ShardedJedis getShards() { List<JedisShardInfo> shards = new
	 * ArrayList<>(); //将nodes中的数据进行分组 String[] node = nodes.split(","); for (String
	 * nodeArgs : node) { String[] args = nodeArgs.split(":"); String nodeIp =
	 * args[0]; int port = Integer.parseInt(args[1]); JedisShardInfo info = new
	 * JedisShardInfo(nodeIp, port); shards.add(info); } return new
	 * ShardedJedis(shards); }
	 */
	/*
	 * //1.redis单台测试
	 * 
	 * @Value("${redis.host}") private String host;
	 * 
	 * @Value("${redis.port}") private int port;
	 * 
	 * @Bean //执行方法获取实例化对象 public Jedis getJedis() { return new Jedis(host, port); }
	 */
}
