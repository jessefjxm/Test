package org.bms.verify.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 基于RMI的接口调用，端云侧必须包名、类名、方法签名完全一致<br>
 * V177起对这里的接口人工混淆，避免接口暴露过多信息<br>
 * 所有接口尽可能保证调用次数一致，即使是空函数<br>
 * <p>
 *
 * @入参 s 统一入参，用 , 来分隔原有的多个参数
 * @出参 统一返回值，数值型的我们自己转换
 * @抛出 RemoteException 必须抛出
 */
public interface IVerifyService extends Remote {
    String a(String s) throws RemoteException;

    /**
     * 查询普通版补丁日期
     *
     * @see RmiVerifyClient#查询补丁版本()
     */
    String b(String s) throws RemoteException;

    /**
     * 查询订单号或自动注册
     *
     * @see RmiVerifyClient#查询序列号或自动注册()
     */
    String c(String s) throws RemoteException;

    String d(String s) throws RemoteException;

    /**
     * 注册激活码
     *
     * @see RmiVerifyClient#注册新订单号(int)
     */
    String e(String s) throws RemoteException;

    /**
     * 查询注册次数
     *
     * @see RmiVerifyClient#查询绑定次数()
     */
    String f(String s) throws RemoteException;

    /**
     * 修改激活码
     *
     * @see RmiVerifyClient#重新绑定已有订单号(int)
     */
    String g(String s) throws RemoteException;

    /**
     * 查询上次注册后的天数
     *
     * @see RmiVerifyClient#查询上次注册后的天数()
     */
    String h(String s) throws RemoteException;

    String i(String s) throws RemoteException;

    /**
     * 通过本机序列号查找订单
     *
     * @see RmiVerifyClient#查询订单号()
     */
    String j(String s) throws RemoteException;

    /**
     * 查询控制台密钥
     *
     * @see RmiVerifyClient#查询控制台密钥()
     */
    String k(String s) throws RemoteException;

    /**
     * 查询脚本密钥
     *
     * @see RmiVerifyClient#查询脚本密钥()
     */
    String l(String s) throws RemoteException;

    /**
     * 查询封包密钥
     *
     * @see RmiVerifyClient#查询封包密钥()
     */
    String m(String s) throws RemoteException;

    /**
     * 查询在线人数上限
     *
     * @see RmiVerifyClient#查询在线人数上限()
     */
    String n(String s) throws RemoteException;

    /**
     * 查询启动器通讯密钥
     *
     * @see RmiVerifyClient#查询启动器通讯密钥()
     */
    String o(String s) throws RemoteException;

    /**
     * 数学计算验证
     *
     * @see RmiVerifyClient#数学计算验证(int, int, int, int)
     */
    String p(String s) throws RemoteException;

    /**
     * 查询新脚本密钥，大版本内密钥不变
     *
     * @see RmiVerifyClient#查询新脚本密钥()
     */
    String q(String s) throws RemoteException;

    String r(String s) throws RemoteException;

    /**
     * 提交BUG反馈
     *
     * @see RmiVerifyClient#提交BUG反馈(String, String)
     */
    String s(String s) throws RemoteException;

    String t(String s) throws RemoteException;

    String u(String s) throws RemoteException;

    /**
     * 检查注册码
     *
     * @see RmiVerifyClient#检查序列号是否存在()
     */
    String v(String s) throws RemoteException;

    /**
     * 查询DES密钥
     *
     * @see RmiVerifyClient#查询DES密钥()
     */
    String w(String s) throws RemoteException;

    /**
     * 查询ip
     *
     * @see RmiVerifyClient#查询ip验证()
     */
    String x(String s) throws RemoteException;

    /**
     * 查询登陆器密钥
     *
     * @see RmiVerifyClient#查询登陆器密钥()
     */
    String y(String s) throws RemoteException;

    /**
     * 查询服务端名字
     *
     * @see RmiVerifyClient#查询服务端名字()
     */
    String z(String s) throws RemoteException;

    /**
     * 初始化登录
     *
     * @see RmiVerifyClient#初始化登录()
     */
    String aa(String s) throws RemoteException;

    String ab(String s) throws RemoteException;

    String ac(String s) throws RemoteException;

    String ad(String s) throws RemoteException;

    /**
     * 查询更新日志
     *
     * @see RmiVerifyClient#查询更新日志()
     */
    String ae(String s) throws RemoteException;

    String af(String s) throws RemoteException;

    /**
     * 检查MD5
     *
     * @see RmiVerifyClient#检查MD5(String)
     */
    String ag(String s) throws RemoteException;

    String ah(String s) throws RemoteException;

    String ai(String s) throws RemoteException;

    /**
     * 查询补丁包下载地址
     *
     * @see RmiVerifyClient#查询补丁包下载地址()
     */
    String aj(String s) throws RemoteException;

    /**
     * 查询商品版本是否启用
     */
    @Deprecated
    String ak(String s) throws RemoteException;

    /**
     * 查询新补丁包下载地址，包含了压缩包+增量脚本
     *
     * @see RmiVerifyClient#查询新补丁包下载地址()
     */
    String al(String s) throws RemoteException;

    String am(String s) throws RemoteException;

    String an(String s) throws RemoteException;

    String ao(String s) throws RemoteException;

    /**
     * 查询购买的版本号
     *
     * @see RmiVerifyClient#查询购买的版本号()
     */
    String ap(String s) throws RemoteException;

    String aq(String s) throws RemoteException;

    String ar(String s) throws RemoteException;

    /**
     * 账号密码登录
     *
     * @see RmiVerifyClient#账号密码登录(String)
     */
    String as(String s) throws RemoteException;

    String at(String s) throws RemoteException;

    String au(String s) throws RemoteException;

    String av(String s) throws RemoteException;

    /**
     * 获取特定封包值
     *
     * @see RmiVerifyClient#获取特定封包值(String)
     */
    String aw(String s) throws RemoteException;

    /**
     * 获取封包头序列
     *
     * @see RmiVerifyClient#获取封包头序列(String)
     */
    String ax(String s) throws RemoteException;

    /**
     * 获取BUFF状态序列
     *
     * @see RmiVerifyClient#获取BUFF状态序列(String)
     */
    String ay(String s) throws RemoteException;

    String az(String s) throws RemoteException;
}
