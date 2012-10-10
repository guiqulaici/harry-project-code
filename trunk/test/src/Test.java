import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		strList.add("a");
		strList.add("b");
		strList.add("c");
		
		List<String> list = new ArrayList<String>();
		
		for (String s : strList) {
			if (s.equals("b") || s.equals("c")) {
				list.add(s);
				strList.remove(s);
			}
		}
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		System.out.println(strList.size());
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}
}
