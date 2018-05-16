package com.hskj;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Created by hongHan_gao
 * Date: 2018/5/10
 */


public class MD5Test {

    @Test
    public void testMD5(){
        String password = "666";
        //加密：md5
        Md5Hash md5Hash = new Md5Hash(password);
        //fae0b27c451c728867a567e8c1bb4e53
        System.out.println(md5Hash);

        //加密：md5 + 盐
        md5Hash = new Md5Hash(password, "zhangsan");
        //2f1f526e25fdefa341c7a302b47dd9df
        System.out.println(md5Hash);

        //加密：md5 + 盐 + 散列次数
        md5Hash = new Md5Hash(password, "zhangsan" , 3);
        //cd757bae8bd31da92c6b14c235668091
        System.out.println(md5Hash);
    }
}
