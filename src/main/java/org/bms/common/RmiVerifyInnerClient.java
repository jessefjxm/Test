package org.bms.common;

import org.bms.verify.rmi.IVerifyService;

import java.rmi.RemoteException;

/**
 * 对所有RMI调用进行第一级封装，使得难以突破反射混淆，向上追溯
 */
@SuppressWarnings("WeakerAccess")
public class RmiVerifyInnerClient {
    public static String 查询补丁版本(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.b(param);
    }

    public static String 查询序列号或自动注册(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.c(param);
    }

    public static String 注册新订单号(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.e(param);
    }

    public static String 查询绑定次数(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.f(param);
    }

    public static String 重新绑定已有订单号(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.g(param);
    }

    public static String 查询上次注册后的天数(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.h(param);
    }

    public static String 查询订单号(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.j(param);
    }

    public static String 查询控制台密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.k(param);
    }

    public static String 查询脚本密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.l(param);
    }

    public static String 查询封包密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.m(param);
    }

    public static String 查询在线人数上限(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.n(param);
    }

    public static String 查询启动器通讯密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.o(param);
    }

    public static String 数学计算验证(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.p(param);
    }

    public static String 查询新脚本密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.q(param);
    }

    public static String 提交BUG反馈(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.s(param);
    }

    public static String 检查序列号是否存在(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.v(param);
    }

    public static String 查询DES密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.w(param);
    }

    public static String 查询商业版验证IP(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.x(param);
    }

    public static String 查询商业版登陆器密钥(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.y(param);
    }

    public static String 查询商业版服务端名字(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.z(param);
    }

    public static String 初始化登录(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.aa(param);
    }

    public static String 查询更新日志(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ae(param);
    }

    public static String 检查MD5(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ag(param);
    }

    public static String 查询补丁包下载地址(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.aj(param);
    }

    @Deprecated
    public static String 查询商品版本是否启用(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ak(param);
    }

    public static String 查询新补丁包下载地址(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.al(param);
    }

    public static String 查询购买的版本号(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ap(param);
    }

    public static String 账号密码登录(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.as(param);
    }

    public static String 获取特定封包值(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.aw(param);
    }

    public static String 获取封包头序列(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ax(param);
    }

    public static String 获取BUFF状态序列(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ay(param);
    }

    /*
    ======================================================
    以下是没有实际用上的空壳接口，用来增加反编译解读难度
    ======================================================
     */

    public static String a(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.a(param);
    }

    public static String d(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.d(param);
    }

    public static String i(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.i(param);
    }

    public static String r(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.r(param);
    }

    public static String t(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.t(param);
    }

    public static String u(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.u(param);
    }

    public static String w(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.w(param);
    }

    public static String x(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.x(param);
    }

    public static String y(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.y(param);
    }

    public static String z(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.z(param);
    }

    public static String ab(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ab(param);
    }

    public static String ac(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ac(param);
    }

    public static String ad(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ad(param);
    }

    public static String af(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.af(param);
    }

    public static String ah(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ah(param);
    }

    public static String ai(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ai(param);
    }

    public static String am(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.am(param);
    }

    public static String an(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.an(param);
    }

    public static String ao(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ao(param);
    }

    public static String aq(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.aq(param);
    }

    public static String ar(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.ar(param);
    }

    public static String at(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.at(param);
    }

    public static String au(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.au(param);
    }

    public static String av(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.av(param);
    }

    public static String az(IVerifyService iVerifyService, String param) throws RemoteException {
        return iVerifyService.az(param);
    }
}
