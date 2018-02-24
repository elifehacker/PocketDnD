package World;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

public class MessagePrinter {

	public static Queue<String> messages = new LinkedList<>();
	private static String tag = "PocketDnD";

	static public void print(String str) {
		messages.add(str);
		Log.d(tag, str);
	}

}
