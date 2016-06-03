package com.lkw;

import java.io.File;

/**
 * @author kwliu
 * @date 2016年6月3日 下午3:15:27
 * @version 1.0
 */
public class Constant {
	// WEB_ROOT是服务器的根目录，即运行程序当前目录，如E:/workspace/tomcat-demo/webapps
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webapps";
	// 关闭的命令
	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	public static final int BUFFER_SIZE = 2048;
}
