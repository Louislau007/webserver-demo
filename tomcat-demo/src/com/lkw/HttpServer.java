package com.lkw;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author kwliu
 * @date 2016年6月3日 上午11:43:57
 * @version 1.0
 */
public class HttpServer {

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1,
					InetAddress.getByName("127.0.0.1")); // 监听到来自localhost:8080的套接字
			while (true) {
				try {
					Socket socket = null;
					InputStream input = null;
					OutputStream output = null;
					socket = serverSocket.accept(); // 接收该套接字
					input = socket.getInputStream();
					output = socket.getOutputStream();
					// 封装request请求
					Request request = new Request(input);
					request.parse();
					// 封装response对象
					Response response = new Response(output);
					response.setRequest(request);
					response.sendStaticResource(); // response内容输出
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
