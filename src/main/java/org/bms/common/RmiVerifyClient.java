package org.bms.common;


import org.bms.verify.rmi.IVerifyService;

import java.util.Arrays;

/**
 * 对所有RMI调用进行第二级封装，提高接口可读性
 */
public class RmiVerifyClient {
    /**
     * 根据当前选择的机房和是否至尊版，返回对应RMI实例
     */
    private static IVerifyService getClient() throws Exception {
        return 验证机房地址.验证机房1号.getClient();
    }

    /**
     * 调用新RMI接口的抽象，出入参皆会附加时间戳再加密<br>
     * 失败时会重试所有服务器，最多3次<br>
     */
    private static String run(RmiVerifyInterface inf, Object... param) {
        long start = System.currentTimeMillis();
        try {
            String response = inf.run(getClient(), Arrays.toString(param));
            System.out.println("response = " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.println("【" + stackTraceElements[2].getMethodName() + "】调用接口耗时：" + (end - start) + "ms");
        }

        return "";
    }

    /**
     * 用于抽象调用RMI接口类的行为
     */
    @FunctionalInterface
    private interface RmiVerifyInterface {
        String run(IVerifyService iVerifyService, String param) throws Exception;
    }

    /**
     * 测试验证服务器有没有连接上
     * 【备注】此接口不会携带订单号
     *
     * @return "服务端请求正版验证中，请稍等..."
     */
    @Deprecated
    public static String 初始化登录() {
        return run(RmiVerifyInnerClient::初始化登录);
    }

    public static String 提交BUG反馈(String 反馈者, String 问题描述) {
        return run(RmiVerifyInnerClient::提交BUG反馈, 反馈者, 问题描述);
    }

    public static String 注册新订单号(int 注册次数) {
        return run(RmiVerifyInnerClient::注册新订单号, 注册次数);
    }

    public static void 重新绑定已有订单号(int 绑定次数) {
        run(RmiVerifyInnerClient::重新绑定已有订单号, 绑定次数);
    }

    public static boolean 检查序列号是否存在() {
        return Boolean.parseBoolean(run(RmiVerifyInnerClient::检查序列号是否存在));
    }

    public static String 查询更新日志() {
        return run(RmiVerifyInnerClient::查询更新日志);
    }

    public static String 查询补丁版本() {
        return run(RmiVerifyInnerClient::查询补丁版本);
    }

    public static String 查询序列号或自动注册() {
        return run(RmiVerifyInnerClient::查询序列号或自动注册);
    }

    public static String 查询绑定次数() {
        return run(RmiVerifyInnerClient::查询绑定次数);
    }

    public static int 查询上次注册后的天数() {
        return Integer.parseInt(run(RmiVerifyInnerClient::查询上次注册后的天数));
    }

    /**
     * 通过本机序列号查找订单<br>
     * 【备注】此接口不会携带订单号
     */
    public static String 查询订单号() {
        return run(RmiVerifyInnerClient::查询订单号);
    }

    public static String 查询购买的版本号() {
        return run(RmiVerifyInnerClient::查询购买的版本号);
    }

    public static String 查询补丁包下载地址() {
        return run(RmiVerifyInnerClient::查询补丁包下载地址);
    }

    public static String 查询新补丁包下载地址() {
        return run(RmiVerifyInnerClient::查询新补丁包下载地址, "ServerConstants.补丁版本号");
    }

    public static String 查询控制台密钥() {
        return run(RmiVerifyInnerClient::查询控制台密钥);
    }

    public static String 查询新脚本密钥() {
        return run(RmiVerifyInnerClient::查询新脚本密钥);
    }

    public static String 查询封包密钥() {
        return run(RmiVerifyInnerClient::查询封包密钥);
    }

    public static String 查询启动器通讯密钥() {
        return run(RmiVerifyInnerClient::查询启动器通讯密钥);
    }

    public static String 查询DES密钥() {
        return run(RmiVerifyInnerClient::查询DES密钥);
    }

    public static int 查询在线人数上限() {
        return 0;
    }

    public static String 检查MD5(String md5) {
        return run(RmiVerifyInnerClient::检查MD5, md5);
    }

    public static String 获取特定封包值(String 封包类型) {
        return run(RmiVerifyInnerClient::获取特定封包值, 封包类型);
    }

    public static String 获取封包头序列(String 封包方向) {
        return run(RmiVerifyInnerClient::获取封包头序列, 封包方向);
    }

    public static String 获取BUFF状态序列() {
        return run(RmiVerifyInnerClient::获取BUFF状态序列);
    }

    public static String 数学计算验证(int a, int b, int c, int d) {
        return run(RmiVerifyInnerClient::数学计算验证, a, b, c, d);
    }

    public static String 账号密码登录(String password) {
        return run(RmiVerifyInnerClient::账号密码登录, password);
    }

    public static String 查询商业版验证IP() { return run(RmiVerifyInnerClient::查询商业版验证IP); }

    public static String 查询商业版登陆器密钥() { return run(RmiVerifyInnerClient::查询商业版登陆器密钥); }

    public static String 查询商业版服务端名字() { return run(RmiVerifyInnerClient::查询商业版服务端名字); }

    @Deprecated
    public static String 查询脚本密钥() {
        return run(RmiVerifyInnerClient::查询脚本密钥);
    }

    @Deprecated
    public static String 查询商品版本是否启用(String s) {
        return run(RmiVerifyInnerClient::查询商品版本是否启用, s);
    }

    /*
    ======================================================
    以下是没有实际用上的空壳接口，用来增加反编译解读难度
    ======================================================
     */

    public static String a(String s) {
        return run(RmiVerifyInnerClient::a, s);
    }

    public static String d(String s) {
        return run(RmiVerifyInnerClient::d, s);
    }

    public static String i(String s) {
        return run(RmiVerifyInnerClient::i, s);
    }

    public static String r(String s) {
        return run(RmiVerifyInnerClient::r, s);
    }

    public static String w(String s) {
        return run(RmiVerifyInnerClient::w, s);
    }

    public static String x(String s) {
        return run(RmiVerifyInnerClient::x, s);
    }

    public static String y(String s) {
        return run(RmiVerifyInnerClient::y, s);
    }

    public static String z(String s) {
        return run(RmiVerifyInnerClient::z, s);
    }

    public static String ab(String s) {
        return run(RmiVerifyInnerClient::ab, s);
    }

    public static String ac(String s) {
        return run(RmiVerifyInnerClient::ac, s);
    }

    public static String ad(String s) {
        return run(RmiVerifyInnerClient::ad, s);
    }

    public static String af(String s) {
        return run(RmiVerifyInnerClient::af, s);
    }

    public static String ah(String s) {
        return run(RmiVerifyInnerClient::ah, s);
    }

    public static String ai(String s) {
        return run(RmiVerifyInnerClient::ai, s);
    }

    public static String am(String s) {
        return run(RmiVerifyInnerClient::am, s);
    }

    public static String an(String s) {
        return run(RmiVerifyInnerClient::an, s);
    }

    public static String ao(String s) {
        return run(RmiVerifyInnerClient::ao, s);
    }

    public static String aq(String s) {
        return run(RmiVerifyInnerClient::aq, s);
    }

    public static String ar(String s) {
        return run(RmiVerifyInnerClient::ar, s);
    }

    public static String at(String s) {
        return run(RmiVerifyInnerClient::at, s);
    }

    public static String au(String s) {
        return run(RmiVerifyInnerClient::au, s);
    }

    public static String av(String s) {
        return run(RmiVerifyInnerClient::av, s);
    }

    public static String az(String s) {
        return run(RmiVerifyInnerClient::az, s);
    }
}
