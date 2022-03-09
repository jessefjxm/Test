package org.bms.common;

import org.bms.verify.rmi.IManageService;
import org.bms.verify.rmi.IVerifyService;

import java.rmi.Naming;
import java.util.stream.Stream;

/**
 * 这里写死了所有当前的机房
 */
public enum 验证机房地址 {
    FTP机房("82.157.75.16", 10148, 20148),
    验证机房1号("82.157.75.16", 10148, 20148),
    验证机房2号("123.206.111.99", 18148, 28148),
    验证机房3号("103.96.74.132", 10148, 20148),

    本地测试("127.0.0.1", 10148, 20148),
    ;
    public final String ip;
    public final int port;
    @Deprecated public final int portVIP;

    验证机房地址(String ip, int port, int portVIP) {
        this.ip = ip;
        this.port = port;
        this.portVIP = portVIP;
    }

    public IVerifyService getClient() throws Exception {
        return (IVerifyService) Naming.lookup("rmi://" + ip + ":" + port + "/verifyService" + "VIP");
    }

    public IManageService getManageClient(boolean VIP) throws Exception {
        return (IManageService) Naming.lookup("rmi://" + ip + ":" + port + "/manageService" + (VIP ? "VIP" : ""));
    }

    public static Stream<验证机房地址> getAll远程机房() {
        return Stream.of(验证机房1号, 验证机房2号, 验证机房3号);
    }
}
