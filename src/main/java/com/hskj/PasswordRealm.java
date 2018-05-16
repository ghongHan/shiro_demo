package com.hskj;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by hongHan_gao
 * Date: 2018/5/10
 */


public class PasswordRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "PasswordRealm";
    }

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //通过用户名到数据库中查询用户信息，封装成一个AuthenticationInfo对象返回，方便认证器进行对比
        //获取token中的用户名
        String username = (String) token.getPrincipal();
        //通过用户名查询数据库，将该用户对应的数据返回（帐号和密码）
        //此时假设查询到的数据为：zhangsan/666
        if(!"zhangsan".equals(username)){
            return null;
        }
        //模拟数据库中保存加密之后密文：666 + 帐号（盐）+ 散列次数
        String password = "cd757bae8bd31da92c6b14c235668091";
        //info对象表示realm登录的比对信息：参数1：用户信息（真实登录对象user对象）；参数2：密码；参数3：指定盐；参数4：当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes("zhangsan"),getName());
        return info;
    }
}
