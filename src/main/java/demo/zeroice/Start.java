package demo.zeroice;

import demo.zerocice.service.DemoI;

public class Start {
	public static void main(String[] args) {
		int status = 0;
		// Communicator实例，是ice run time的主句柄
		Ice.Communicator ic = null;
		try {
			// 调用Ice.Util.Initialize()初始化Ice run time
			ic = Ice.Util.initialize(args);

			// 创建一个对象适配器，传入适配器名字和在8756端口处接收来的请求
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("DemoAdapter", "default -p 9999");

			// 实例化一个PrinterI对象，为Printer接口创建一个servant
			Ice.Object object = new DemoI();

			// 调用适配器的add,告诉它有一个新的servant,传递的参数是刚才的servant,这里的“SimplePrinter”是Servant的名字
			adapter.add(object, Ice.Util.stringToIdentity("Demo"));

			// 调用适配器的activate()方法，激活适配器。被激活后，服务器开始处理来自客户的请求。
			adapter.activate();

			System.out.println("ICE服务端已经准备完毕");
			// 这个方法挂起发出调用的线程，直到服务器实现终止为止。或我们自己发出一个调用关闭。
			ic.waitForShutdown();
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if (ic != null) {
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
