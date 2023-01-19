package crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cwe.CWE;

public class GetCWE {

	public static CWE getCWE(String cweLink) {
		// connect to each cwe info page
		Document doc = null;
		try {
			doc = Jsoup.connect("https://cwe.mitre.org" + cweLink).get();
		} catch (IOException e1) {
			System.out.println("cwe link error");
			e1.printStackTrace();
		}
		
		// get Info
		// Elements el = doc.getElementsByAttributeValueContaining("class", "weakness");
		// System.out.println(el);"oc_"+id+"_Demonstrative_Examples"
		
		// id, name
		int id = 0;
		String name = "";
		String idName = doc.getElementsByAttributeValue("style", "overflow:auto;").text().split("-")[1];
		id = Integer.parseInt(idName.substring(0, idName.indexOf(":")));
		name = idName.substring(idName.indexOf(":")+2);
//		System.out.println(id + " " + name);
		
		// description
		String descript = "";
		descript = doc.getElementsByAttributeValue("name", "oc_" + id + "_Description").text();
		//extend description
		try {
			descript += " " + doc.getElementsByAttributeValue("name", "oc_"+id+"_Extended_Description").text();
//			System.out.println(descript);
		} catch (Exception e) {
			System.out.println(id+" : No extend_descript");
		}
		// relationship (ids for childOf, parentOf, memberOf)
		ArrayList<Integer> childOf = new ArrayList<Integer>();
		ArrayList<Integer> parentOf = new ArrayList<Integer>();
		ArrayList<Integer> memberOf = new ArrayList<Integer>();
		try {
			Elements relations = doc.getElementsByAttributeValue("id", "relevant_table");
			relations = doc.getElementsByAttributeValueContaining("class", "reltable");
			for(int i=0;i<relations.size();i++) {
				Element e = relations.get(i);
				Elements es = e.getElementsByAttributeValue("valign", "top");
				switch(es.get(4).text()) {
					case "ChildOf":
						childOf.add(Integer.parseInt(es.get(6).text()));
						break;
					case "ParentOf":
						parentOf.add(Integer.parseInt(es.get(6).text()));
						break;
					case "MemberOf":
						memberOf.add(Integer.parseInt(es.get(6).text()));
						break;
					default:
						System.out.println("Unavailable relation");
						break;
				}
			}
		} catch(Exception e) {
			System.out.println(id+" : No releationship");
		}
//		System.out.println(childOf);
//		System.out.println(parentOf);
//		System.out.println(memberOf);
		
		// applicable platform language
		
		// examples
		ArrayList<String> exampleCode = new ArrayList<String>();
		String description = "";
		try {
			Element example = doc.getElementById("oc_"+id+"_Demonstrative_Examples");
			// description
			Elements ps = example.select("p");
			for(Element p : ps) {
				description += p.text() + "\n";
			}
			// bad code
			Elements badCodes = example.getElementsByAttributeValue("class", "indent Bad");
			for(Element bc : badCodes) {
				exampleCode.add(CodeParser.parser(bc.html()));
			}
		} catch(Exception e) {
			System.out.println(id+" : No example");
		}
//		System.out.println(description);
		
		return new CWE(id, name, descript, childOf, parentOf, memberOf, exampleCode, description);
	}
}
