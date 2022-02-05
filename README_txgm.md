# Java训练营代码 - JDK17环境

## Maven 编译和运行

* 安装 JDK 17以上
* 安装 Apache Maven 3.6 以上
* 项目使用 `mvn compile` 编译
* 使用 `mvn exec:java -Dexec.mainClass="geektime.jdk.gm.Sm3Demo"` 执行测试
* `Dexec.args='XXX'` 提供运行参数

## 使用模块化构建支持国密算法的JRE

* 将国密算法支持封装成 jmod 模块 `jdk.txgm`
* 生成支持国密算法的 Java 运行时环境

### 说明和步骤

* 使用Tencent Kona中的国密模块  _（https://github.com/Tencent/TencentKona-8/releases/tag/Fiber-8.0.6-GA在此致谢！）_
* libTencentSM.so 为Linux动态库。其他操作系统需要使用对应的动态库文件。

```
$ mvn clean package
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.556 s
[INFO] Finished at: 2022-01-02T10:10:34+08:00
[INFO] ------------------------------------------------------------------------

$ cd target
target$ mkdir jmod
target$ jmod create --class-path classes/ --libs classes/libs/ jmod/txgm.jmod
target$ jmod list jmod/txgm.jmod 
classes/module-info.class
classes/com/tencent/crypto/provider/SM4GcmSecretMessage.class
classes/com/tencent/crypto/provider/SM2Cipher.class
......
lib/libTencentSM.so

target$ jlink --module-path ./jmod --add-modules=jdk.txgm --output jre
target$ jre/bin/java -cp classes/ geektime.jdk.gm.Sm3Demo
/usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib:/opt/play/java_geektime17/target
Provider Name :SunJCE
Provider Version :17
Provider Info:SunJCE Provider (implements RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC, ChaCha20)
44526eeba9235bae33f2bab8ff1f9ca8965b59d58be82af8111f336a00c1c432
cipher Cipher.SM2, mode: encryption, algorithm from: SMCSProvider encode size: 116

````

