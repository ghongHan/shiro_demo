package com.hskj;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/5/10
 */


public class PermissionRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "PermissionRealm";
    }

    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //principals:用户认证信息
        //获得当前登录用户信息：用户凭证
        String username = (String) principals.getPrimaryPrincipal();
        //角色集合
        List<String> roles = new ArrayList<String>();
        roles.add("role1");
        //权限集合
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:list");
        //返回以上的角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证操作
     * @param authenticationToken 登录时包装的UsernamePasswordToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //通过用户名到数据库中查询用户信息，封装成一个AuthenticationInfo对象返回，方便认证器进行对比
        //获取token中的用户名
        String username = (String) authenticationToken.getPrincipal();
        //通过用户名查询数据库，将该用户对应的数据返回（帐号和密码）
        //此时假设查询到的数据为：zhangsan/666
        if(!"zhangsan".equals(username)){
            return null;
        }
        String password = "666";
        //info对象表示realm登录的比对信息：参数1：用户信息（真实登录对象user对象）；参数2：密码；参数3：当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }
}
