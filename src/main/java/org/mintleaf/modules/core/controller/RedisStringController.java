package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Redis相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/10 17:03
 * @Version 1.0
 */
@Api(tags="Redis相关控制器",description="描述")
@Controller
@RequestMapping("redis")
public class RedisStringController {

    @Autowired
    private StringRedisTemplate redisClient;

    /**
     * 字符串存入取出
     * @param para
     * @return
     * @throws Exception
     */
    @ApiOperation(value="字符串存入取出", notes="描述")
    @RequestMapping(value="setGet.json",method = {RequestMethod.GET})
    @ResponseBody
    public String setGet(String para) throws Exception{
        if(para==null){
            para="12345";
        }
        redisClient.opsForValue().set("test",para);
        String str = redisClient.opsForValue().get("test");
        System.out.print(str);
        return str;
    }

}
