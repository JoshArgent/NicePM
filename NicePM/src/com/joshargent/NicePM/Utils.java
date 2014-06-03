package com.joshargent.NicePM;

public class Utils {
	
	public static String combineArgs(String[] args, int start, String seperator)
	{
		String t1 = "";
		int n = 0;
		for(String t2 : args)
		{
			if(n >= start)
			{
				if(n == args.length - 1)
				{
					t1 += t2;
				}
				else t1 += t2 + seperator;
			}
			n += 1;
		}
		return t1;
	}

}
