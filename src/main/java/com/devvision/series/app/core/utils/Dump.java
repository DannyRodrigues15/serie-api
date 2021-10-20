package com.devvision.series.app.core.utils;

public class Dump {
	public static void dd(Object obj, String text) {
		System.out.println("============= " + text + " =============");
		System.out.println(obj);
		System.out.println("======================================================================\n");
	}
	
	public static void dd(Object obj, String text, Object currentClass, int line) {
		System.out.println("============= " + text + " | Class: " + currentClass + " - Line: " + line + " =============");
		System.out.println(obj);
		System.out.println("=======================================================================================\n");
	}
	
	public static int getLineNumber() {
	    return StackWalker.getInstance(StackWalker.Option.SHOW_HIDDEN_FRAMES).walk((s) -> s.skip(1).findFirst()).get().getLineNumber();
	}
}