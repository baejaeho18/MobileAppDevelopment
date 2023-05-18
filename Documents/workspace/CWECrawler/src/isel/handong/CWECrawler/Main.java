package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cwe.CWE;

public class Crawler {

	public static void main(String[] args) throws IOException {
		
		ArrayList<CWE> cwes = new ArrayList<CWE>();
		Element a;
		
		// connect to CWE homepage
		Document doc = Jsoup.connect("https://cwe.mitre.org/data/definitions/660.html").get();
		
		// get each cwe links
		Elements cweJavaList = doc.getElementsByAttributeValueContaining("href", "/data/definitions/");
		for(int i=0;i<cweJavaList.size();i++) {
			a = cweJavaList.get(i);
			// get cwe info by link
			cwes.add(GetCWE.getCWE(a.attr("href")));
		}
		
		// save cwe info as json format
		JSONArray cweJson = SaveCWE.saveCWEAsJson(cwes);
		
		// write json format to file
		try {
			FileWriter file = new FileWriter("./cwe.json");
			file.write(cweJson.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("File Error");
		}

	}

}
