package assign2.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateTime {

	static public String getCurTime() {
		Date dt=new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowTime = df.format(dt);
		return nowTime;
	}

}
