package com.lkw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author thomaslwq
 * @version 创建时间：Sep 5, 2012 9:59:53 PM 类说明 根据相应信息返回结果
 */
public class Response {

	
	Request request;
	OutputStream output;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void sendStaticResource() throws IOException {

		byte[] bytes = new byte[Constant.BUFFER_SIZE];
		FileInputStream fis = null;

		File file = new File(Constant.WEB_ROOT, request.getUri());// 从工程目录中寻找index.html文件
		if (file.exists()) {// 如果存在，读取内容
			try {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch); // 输出
					ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					fis.close();
				}
			}

		} else {
			// 找不到文件
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
					+ "Content-Type: text/html\r\n" + "Content-Length: 23\r\n"
					+ "\r\n" + "<h1>File Not Found</h1>";
			try {
				output.write(errorMessage.getBytes());
				output.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}

}
