
public class Maptouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Integer> map=new Map<String,Integer>();
		for(int i=0;i<20;i++) {
			map.insert("abc"+i, i);
		}
		for(int i=0;i<20;i++) {
			System.out.println("abc"+i+": "+map.getvalue("abc"+i));
		}

	}

}
