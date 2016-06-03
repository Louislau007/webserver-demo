package com.lkw;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author thomaslwqlkw
 * @version 创建时间：Sep 5, 2012 9:59:32 PM 类说明 将浏览器发来的请求信息转化成字符和截取url
 */
public class Request {

	// 输入流
	private InputStream input;
	// uri
	private String uri;

	public Request(InputStream inputStream) {
		this.input = inputStream;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	// 从套接字中读取字符信息
	public void parse() {
		StringBuffer request = new StringBuffer(Constant.BUFFER_SIZE);
		int i = 0;
		byte[] buffer = new byte[Constant.BUFFER_SIZE];

		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		for (int j = 0; j < i; j++) {
			request.append((char) (buffer[j]));
		}
		System.out.println(request.toString());
		uri = parseUri(request.toString()); // uri=/index.html
	}

	// 截取请求的url,如http://localhost:8080/index.html ，截取部分为 /index.html
	private String parseUri(String requestString) {
		int index1 = 0;
		int index2 = 0;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}

}
