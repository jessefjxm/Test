package org.bms.verify.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * 仅用于本地控制台远程访问鉴权服务器进行控制
 */
public interface IManageService extends Remote {
    String 添加订单(String 订单号_管理控制台, String 订单号_用户, String 版本号) throws RemoteException;

    String 删除用户(String 订单号_管理控制台, String 订单号_用户) throws RemoteException;

    String 查询订单(String 订单号_管理控制台, String 订单号_用户) throws RemoteException;

    String 修改版本号(String 订单号_管理控制台, String 订单号_用户, String 版本号) throws RemoteException;

    String 修改注册时间(String 订单号_管理控制台, String 订单号_用户, String 注册时间) throws RemoteException;

    String 修改注册次数(String 订单号_管理控制台, String 订单号_用户, String 注册次数) throws RemoteException;

    String 修改注册码(String 订单号_管理控制台, String 订单号_用户, String 注册码) throws RemoteException;

    boolean 修改在线人数上限(String 订单号_管理控制台, String 订单号_用户, int 在线人数上限) throws RemoteException;

    String 更新密钥(String 订单号_管理控制台, String 游戏版本号, String 密钥类型, String 密钥值) throws RemoteException;

    boolean 更新MD5(String 订单号_管理控制台, String 游戏版本号, String 全部MD5) throws RemoteException;

    boolean 更新封包值(String 订单号_管理控制台, String 游戏版本号, String 封包类型, String 封包值) throws RemoteException;

    boolean 更新封包头序列(String 订单号_管理控制台, String 游戏版本号, String 封包方向, String 封包头序列) throws RemoteException;

    boolean 更新补丁信息(String 订单号_管理控制台, String 游戏版本号, String patchVersion, String downloadUrl, String updateLog, Map<String, Long> fileInfos, String region) throws RemoteException;

    long 查询最近补丁更新时间(String 订单号_管理控制台, String 游戏版本号) throws RemoteException;

    boolean 更新BUFF序列(String 订单号_管理控制台, String 游戏版本号, String BUFF序列) throws RemoteException;
}