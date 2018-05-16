package com.hskj;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by hongHan_gao
 * Date: 2018/5/9
 * 测试shiro认证
 */


public class ShiroTest {

    /**
     * 权限realm
     */
    @Test
    public void testHasRole(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_permission_realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //主体登录
        subject.login(token);
        //授权的前提：用户必须先的登录
        //判断当前用户是否拥有某个角色
        System.out.println(subject.hasRole("role1"));
        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("user:delete"));
    }


    /**
     * 自定义认证realm,密码加密
     */
    @Test
    public void testLoginByPasswordRealm(){
        //创建SecurityManager工厂对象：加载配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_cryptography.ini");
        //创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager绑定到当前运行环境，让系统随时随地都可以访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);
        //创建当前登录的主体，注意：此时主题尚未认证
        Subject subject = SecurityUtils.getSubject();
        //收集主体登录的身份/凭证,即帐号密码
        //参数1：将要登录的用户名，参数2:登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //主体登录
        subject.login(token);
        //判断登录是否成功
        System.out.println("是否登录：" + subject.isAuthenticated());
        //登出（注销）
        subject.logout();
        System.out.println("是否登录：" + subject.isAuthenticated());
    }

    /**
     * 自定义认证realm，密码未加密
     */
    @Test
    public void testLoginByRealm(){
        //创建SecurityManager工厂对象：加载配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_my_realm.ini");
        //创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager绑定到当前运行环境，让系统随时随地都可以访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);
        //创建当前登录的主体，注意：此时主题尚未认证
        Subject subject = SecurityUtils.getSubject();
        //收集主体登录的身份/凭证,即帐号密码
        //参数1：将要登录的用户名，参数2:登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //主体登录
        subject.login(token);
        //判断登录是否成功
        System.out.println("是否登录：" + subject.isAuthenticated());
        //登出（注销）
        subject.logout();
        System.out.println("是否登录：" + subject.isAuthenticated());
    }

    /**
     * shiro默认的认证realm
     */
    @Test
    public void testLogin(){
        //创建SecurityManager工厂对象：加载配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager绑定到当前运行环境，让系统随时随地都可以访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);
        //创建当前登录的主体，注意：此时主题尚未认证
        Subject subject = SecurityUtils.getSubject();
        //收集主体登录的身份/凭证,即帐号密码
        //参数1：将要登录的用户名，参数2:登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //主体登录
        subject.login(token);
        //判断登录是否成功
        System.out.println("是否登录：" + subject.isAuthenticated());
        //登出（注销）
        subject.logout();
        System.out.println("是否登录：" + subject.isAuthenticated());
    }
}
