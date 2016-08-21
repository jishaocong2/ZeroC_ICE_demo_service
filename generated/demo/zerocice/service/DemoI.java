package demo.zerocice.service;

import Ice.Current;

public class DemoI extends _DemoDisp{

	@Override
	public void print(String s, Current __current) {
		System.out.println(s);
		
	}

}
