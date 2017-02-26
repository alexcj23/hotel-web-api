package ni.edu.ucem.webapi.core.qo;

import java.util.List;

public class Util {

	public static String convert(String a) {
		a = a.replace("[", "").replace("]", "");
		String res = "\"";
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == ':') {
				res += "\"" + ":" + "\"";
			} else if (a.charAt(i) == ',') {
				res += "\"" + ", " + "\"";
			} else {
				res += a.charAt(i);
			}
		}
		res += "\"" + "";
		return res;
	}

	public static boolean exist(List<String> params, String parameter) {
		boolean exist = false;
		for (String param : params) {
			if (param.split("=")[0].equalsIgnoreCase(parameter)) {
				return true;
			}
		}
		return exist;
	}

}
