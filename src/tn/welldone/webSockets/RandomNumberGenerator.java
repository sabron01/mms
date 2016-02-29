package tn.welldone.webSockets;

public class RandomNumberGenerator {

	public static Long generate() {
		return DateUtil.getCurrentDateInIST().getTime();
	}
}
