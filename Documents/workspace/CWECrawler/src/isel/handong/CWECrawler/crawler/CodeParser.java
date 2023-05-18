package crawler;

public class CodeParser {
	public static String parser(String html) {
		
		String badCode = "";
		
//		System.out.println(html);
		badCode = html.replace("<br>", "\n");
		badCode = badCode.replaceAll("<[^<>]*>", "");
		badCode = badCode.replace("&lt;","<");
		badCode = badCode.replace("&gt;",">");
		badCode = badCode.replace("&nbsp;","");
		
		// badCode = badCode.replace("\n\n", "\n");
		badCode = badCode.split(":")[1];
//		System.out.println(badCode);
		
		return badCode;
	}
}
