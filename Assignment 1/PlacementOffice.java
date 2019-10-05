/*
Lavanya Verma
2018155	
Ap 1st assignment 
completion date 11/08/19
*/


import java.util.Scanner ;
import java.util.ArrayList;
import java.util.Arrays;
class PlacementOffice{




	public static Student [] sData;
	public static ArrayList<Company> cData ;
	public static Student sDummy ;// dummy student for comparing whether a stuent's object at that particular index is nukk deleted
	public static Company cDummy;
	
	private static int nPlcd; // not placed students count
	


	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		int len = c.nextInt();
		sDummy = new Student(0,"NONE",0);
		sData = new Student[len];
		
		nPlcd = len;
		cDummy  = new Company(new String[2],0,"CLOSED","Dummy");
		cData = new ArrayList<Company>();
		//////////////////////////////////////////////////
		for (int x = 0 ; x<len;x++){
			float gpa = c.nextFloat();
			String crs = c.next();
			sData[x] = new Student(gpa,crs,x); 
		}
		//////////////////////////////////////////////
		while(nPlcd>0){
			int i = c.nextInt();
			switch(i){
				case 1:
					String name = c.next();
					System.out.print("Number of Eligible Courses = ");
					int clen = c.nextInt();
					String[]  criteria = new String[clen];
					for (int x = 0 ; x<clen;x++){
						criteria[x] = c.next();
					}
					System.out.print("Number of Required Student = ");
					int req = c.nextInt();
					Company temp = new Company(criteria,req,"OPEN",name); 
					cData.add(temp);
					show(name);
					int[] marks = new int[len];
					for (int x =0  ; x<len;x++){
						if (Arrays.asList(criteria).contains(sData[x].getCourse())){
							System.out.println("Enter score for Roll no."+(x+1));
							int score = c.nextInt();
							marks[x] =score;
							sData[x].addData(name + " "+ score);
							

						}else{
							marks[x]=-1;
						}
					}
					temp.setMarks(marks);
					
				break;
				case 2:
					System.out.println("Accounts removed for");
					for (int x =0 ; x<sData.length;x++){
						if  (sData[x].getPStatus()){
							System.out.println(x+1);
							sData[x] =  sDummy;
						}
					}
				break;
				case 3:
					System.out.println("Accounts removed for");
					for (int x = 0 ; x<cData.size();x++){
						if (cData.get(x).getStatus().equals("CLOSED")){
							System.out.println(cData.get(x).getName());
							cData.remove(x);
						}
					}
				break;
				case 4:
					if (nPlcd==1){
						System.out.println("1 Student left.");
					}else{
						System.out.println(nPlcd + " Students left.");
					}
				break;
				case 5:
					for (int x = 0 ; x<cData.size();x++){
						if (cData.get(x).getStatus().equals("OPEN")){
							System.out.println(cData.get(x).getName());
						}
					}
				break;
				case 6:
					String cm = c.next();
					who(cm);
			

				break;
				case 7:
					show(c.next());
				break;
				case 8:
					int rn = c.nextInt();
					if (sData[rn-1] != sDummy){
						sData[rn-1].disp();
					}else {
						System.out.println("No student with the given roll number has an account.");
					}
				break;
				case 9:
					int r = c.nextInt();
					if (sData[r-1] != sDummy){
						sData[r-1].dispCom();
					}else {
						System.out.println("No student with the given roll number has an account.");
						
					}

				break;

			}
		}

	}


	static void show(String name){
		int pr = 0;//0 - not displayed , 1- displayed
		for (int x = 0 ; x<cData.size();x++){
			if (cData.get(x).getName().equals(name)){
				cData.get(x).disp();
				pr =1 ;
			}
		}
		if (pr == 0){	
			System.out.println("No company with given name has an account");
		}
	}

	static void who(String name){// company select students
		Company Comp = cDummy;
		for (int x = 0 ; x<cData.size();x++){
			if (cData.get(x).getName().equals(name)){
				Comp = cData.get(x);
			}
		}
		if (Comp == cDummy){
			System.out.println("No company with given name has an account");
			return ;
		}
		if (Comp.selectedLen()==0){
			for (int x = 0 ; x<Comp.getReq();x++){
				int index =-1;
				float score = -1;
				for (int i = 0 ; i<Comp.getMarks().length;i++){
					if (!sData[i].getPStatus() && Comp.getMarks()[i]>0 && 
					(Comp.getMarks()[i]>score || Comp.getMarks()[i]==score && sData[x].getCgpa() > sData[index].getCgpa() )){
						index = i;
						score = Comp.getMarks()[i];
					}
				}
				if (index == -1){
					break;
				}
				else{
					sData[index].gotPlaced();
					nPlcd-=1;
					Comp.select(index+1);

				}
			}
		}
		Comp.setStatus("CLOSED");
		Comp.releaseList();



}
}
class Company{
	private String[] criteria ;
	private int req ;
	private String status;
	private final String name ;
	private ArrayList<Integer> selected;// list of selected candidates
	private int [] marks;


	public Company(String [] criteria,int req,String status,String name){
		this.criteria = criteria;
		this.req = req;
		this.status = status ;
		this.name = name;
		this.selected = new ArrayList<>();
	}



	public int selectedLen(){// return no. of selected candidates till now
		return selected.size();
	}
	public int [] getMarks(){ return this.marks;}
	public String getName(){  return this.name;}
	public String getStatus(){ return this.status; }
	public int getReq(){ return this.req;}
	


	void releaseList(){
		System.out.println("Roll No. of selected students");
		for(int x = 0;x<selected.size();x++){
			System.out.println(selected.get(x));
		}
	}


	void disp(){
		System.out.println(this.name);
		System.out.println("Course Criteria");
		for (int x = 0 ; x<criteria.length;x++){
			System.out.println(criteria[x]);
		}
		System.out.println("Number of Required Students = "+ req);
		System.out.println("Application Status = "+ status);
	}
	
	void setMarks(int [] marks){
		this.marks = marks;
	}
	void select(int rn){
		selected.add(rn);
	}
	void setStatus(String s){ this.status = s;};

}
class Student{
	private final float cgpa;
	private final String course;
	private final int rollNo;
	private boolean pStatus;// false - not placed
	private String details ;//company tech rounds data

	public Student(float cgpa , String course , int rollNo ){
		this.cgpa = cgpa;
		this.course = course;
		this.rollNo = rollNo;
		pStatus = false;
		details = "";

	}
	int getRollNo(){
		return this.rollNo;
	}
	String getCourse(){
		return this.course;

	}
	float getCgpa(){
		return this.cgpa;
	}
	void addData(String s){
		if (details.equals("")){
			details=s;
		}
		else{
			details+=" \n "+ s;
		}
	}
	void dispCom(){
		System.out.println(details);
	}
	boolean getPStatus(){
		return pStatus;
	}
	void gotPlaced(){
		this.pStatus = true;
	}

	void disp(){
		 System.out.println(rollNo+1);
		 System.out.println(cgpa);
		 System.out.println(course);
		 System.out.print("Placement Status: ");
		 if (pStatus == false){
		 	System.out.println( "Not placed");
		 }else {
			 System.out.println("Placed");
		 }

	}
}

