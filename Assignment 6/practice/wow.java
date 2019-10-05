import static java.lang.System.out;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class wow implements Serializable{
	g gx;
	wow(g gx){this.gx = gx;}
	public static void main (String[] args) throws Exception{
		/*wow w = new wow(new g(1,new c(8,9)));
		FileOutputStream fos = new FileOutputStream("student.gcl");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(w);
		oos.close();
		fos.close();

		// String c = new String(System.getProperty("user.dir"));
		System.out.println("--------------");
		System.out.println(System.getProperty("user.dir"));
		System.out.println("--------------");
		File  l = new File(System.getProperty("user.dir"));
		File [] ls = l.listFiles();
		Pattern pat = Pattern.compile("[a-zA-Z0-9]+.gcl");
		ArrayList<String> ss = new ArrayList<String>(); 
		for (File f : ls){
			// System.out.println(f);
			String g = f.toString();
			if (g.endsWith(".gcl")){
				ss.add(g);
				Matcher m = pat.matcher(g);
				m.find();
				System.out.println(m.group(0));


				System.out.println(g);
			}

		}
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(ss.get(0)));
		wow c = (wow) in.readObject();
		out.println(c.gx.x);
		out.println(c.gx.cc.y);
		out.println(c.gx.cc.z);
		System.out.println("---------------------------------------------==============b");

		HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        for(String key: hm.keySet()){
            System.out.println(key);
        }
	*/
		int v = 2; 
	int b = 5-3;
	System.out.println(v==b);


	}

}


class g implements Serializable{
	int x ;
	c cc;
	g(int x,c cc){ this.x = x;
		this.cc = cc;}

}
class c implements Serializable{
	int y , z ;
	c(int y , int z ){ this.y = y;
		this.z = z;}
}