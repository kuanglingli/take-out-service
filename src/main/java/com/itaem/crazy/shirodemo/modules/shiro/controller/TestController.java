package com.itaem.crazy.shirodemo.modules.shiro.controller;


import com.itaem.crazy.shirodemo.modules.shiro.entity.SysToken;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author 大誌
 * @Date 2019/4/7 15:20
 * @Version 1.0
 */
@Api(tags = "测试")
@RestController
public class TestController {

    private final ShiroService shiroService;

    public TestController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    //    @RequiresPermissions({"save"}) //没有的话 AuthorizationException
    @PostMapping("/save")
    public Map<String, Object> save(@RequestHeader("token")String token) {
        System.out.println("save");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有save的权力");
        SysToken token1 = shiroService.findByToken(token);
        System.out.println(token1.getUserId());
        return map;
    }//f603cd4348b8f1d41226e3f555d392bd

//    @RequiresPermissions({"delete"}) //没有的话 AuthorizationException
    @DeleteMapping("/delete")
    public Map<String, Object> delete(@RequestHeader("token")String token) {
        System.out.println("delete");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有delete的权力");
        return map;
    }

//    @RequiresPermissions({"update"}) //没有的话 AuthorizationException
    @PutMapping("update")
    public Map<String, Object> update(@RequestHeader("token")String token) {
        System.out.println("update");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有update的权力");
        return map;
    }

//    @RequiresPermissions({"select"}) //没有的话 AuthorizationException
    @GetMapping("select")
    public Map<String, Object> select(@RequestHeader("token")String token) {
        System.out.println("select");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有select的权力");
        return map;
    }

//    @RequiresRoles({"vip"}) //没有的话 AuthorizationException
    @GetMapping("/vip")
    public Map<String, Object> vip(@RequestHeader("token")String token) {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有VIP角色");
        return map;
    }
//    @RequiresRoles({"svip"}) //没有的话 AuthorizationException
    @GetMapping("/svip")
    public Map<String, Object> svip(@RequestHeader("token")String token) {
        System.out.println("svip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有SVIP角色");
        return map;
    }
//    @RequiresRoles({"p"}) //没有的话 AuthorizationException
    @GetMapping("/p")
    public Map<String, Object> p(@RequestHeader("token")String token) {
        System.out.println("p");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有P角色");
        return map;
    }
}
