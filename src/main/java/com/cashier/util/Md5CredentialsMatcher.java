package com.cashier.util;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName: Md5CredentialsMatcher
 * @description 
 * @author zhoujiaxin
 * @createDate 2018年11月20日
 */

public class Md5CredentialsMatcher extends HashedCredentialsMatcher  {

    public Md5CredentialsMatcher() {
        super();
        setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
    }
}
