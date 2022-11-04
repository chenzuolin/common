package com.common.constant;

/**
 * @PackageName: com.common.constant
 * @ClassName: HttpStatus
 * @description:
 * @author: czl
 * @date: 2022/11/4 16:58
 */
public class HttpStatus {

    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;
}
