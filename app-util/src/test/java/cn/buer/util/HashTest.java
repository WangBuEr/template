package cn.buer.util;

import com.mongodb.util.Hash;

public class HashTest {
	static int additiveHash(String key, int prime) {
		int hash, i;
		for (hash = key.length(), i = 0; i < key.length(); i++)
			hash += key.charAt(i);
		return (hash % prime);
	}

	public static void main(String[] args) {
		int hash = HashTest.additiveHash("wangjhg5411111112312132ee", 100);
		System.out.println(hash);
	}

}
