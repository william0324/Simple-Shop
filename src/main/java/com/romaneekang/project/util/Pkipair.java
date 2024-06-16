package com.romaneekang.project.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class Pkipair {
    /**
     * 创建签名，进行商户私钥证书加签形成签名
     *
     * @param signMsg 参数1={参数1}&参数2={参数2}&……&参数n={参数n}
     * @return 签名字符串
     */
    public String signMsg(String signMsg) {
        String base64 = "";
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            // 读取商户私钥证书
            String file = Pkipair.class.getResource("10012140356.pfx").getPath().replaceAll("%20", " ");
            FileInputStream ksfis = new FileInputStream(file);
            BufferedInputStream ksbufin = new BufferedInputStream(ksfis);
            char[] keyPwd = "123456".toCharArray();
            //char[] keyPwd = "YaoJiaNiLOVE999Year".toCharArray();
            ks.load(ksbufin, keyPwd);
            PrivateKey priK = (PrivateKey) ks.getKey("test-alias", keyPwd);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priK);
            signature.update(signMsg.getBytes(StandardCharsets.UTF_8));
/*            BASE64Encoder encoder = new BASE64Encoder();
            base64 = encoder.encode(signature.sign());*/
            Base64.Encoder encoder = Base64.getEncoder();
            base64 = encoder.encodeToString(signature.sign());
        } catch (FileNotFoundException e) {
            System.out.println("123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("test = " + base64);
        return base64;
    }

    /**
     * 验证签名
     *
     * @param val
     * @param msg
     * @return
     */
    public boolean enCodeByCer(String val, String msg) {
        boolean flag = false;
        try {
            String file = Pkipair.class.getResource("CFCA_sandbox.cer").toURI().getPath();//99bill[1].cert.rsa.20140803.cer
            System.out.println(file);                       //  99bill.cert.rsa.20140803.cer
            FileInputStream inStream = new FileInputStream(file);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
            PublicKey pk = cert.getPublicKey();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(pk);
            signature.update(val.getBytes());
/*            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            flag = signature.verify(decoder.decodeBuffer(msg));*/
            Base64.Decoder decoder = Base64.getDecoder();
            flag = signature.verify(decoder.decode(msg));
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no");
        }
        return flag;
    }
}

