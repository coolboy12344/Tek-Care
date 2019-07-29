import java .io.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class datauploader extends Database {
public static ArrayList<Database> readFromCsvFile(){
	Reader rdr=null;
	BufferedReader bfrdr=null;
	ArrayList<Database> health=new ArrayList<Database>();
	try{
		rdr=new FileReader("C:\\Users\\nEW u\\Desktop\\Mental_health_data.csv");
		bfrdr=new BufferedReader(rdr);
		String nextline;
     	bfrdr.readLine();
	    int c=1;
	    while((nextline=bfrdr.readLine())!=null) 
		{ 
			Database ob =new Database();
			String [] line=nextline.split(",");
			for(int i=0;i<line.length;i++)
			{
				switch(i)
				{
				case 0:
					ob.setEmpid(Integer.parseInt(line[0]));
				break;
				case 1: ob.setAge((line[1]));
				break;
				case 2: ob.setGender(line[2]);
				break;
				case 3: ob.setFamily_history(line[3]);
				break;
				case 4: ob.setTreatment(line[4]);
				break;
				case 5:ob.setWork_interfere(line[5]);
				break;
	
				case 6:
					ob.setRemote_work(line[6]);
				break;
				case 7:
				ob.setBenfits(line[7]);
					
		        break;
				case 8:
					ob.setWellness_program(line[8]);
				break;
				case 9: 
					ob.setSeek_help(line[9]);
						
				break;
				case 10: 
					ob.setAnonymity(line[10]);
					break;
				case 11:
					ob.setMental_health_consequence(line[11]);
					break;
				case 12:
					ob.setPhysical_health_consequence(line[12]);
					break;
					
				case 13:
					ob.setCoworkers(line[13]);
					break;
					
				case 14:
					ob.setSupervisor(line[14]);
					break;
					
				case 15:
					ob.setMental_health_interview(line[15]);
					break;
					
				case 16:
					ob.setPhysical_health_interview(line[16]);
					break;
				case 17:
					ob.setMental_vs_physical(line[17]);
					break;
				case 18:
					ob.setPredict_leave(line[18]);
					break;
				
					}
				}
			health.add(ob);	
			}
	System.out.println(c);
	     } catch (FileNotFoundException e) {

		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return health;
		}


public static void loadDataToDb(ArrayList<Database> health){
	Connection dbCon = null;
	PreparedStatement pstmt = null;
	
	String url= "jdbc:mysql://localhost:3306/teksystems";
	String username = "root";
	String password = "kunal";
	
	String paramQuery = "insert into dataset(Emp_ID, Age, Gender, Family_History,"
			+ " Past_Treatment, Health_Obstruction_in_work, remote_work, benefits, past_experience_of_wellness_program, "
			+ "Help_seeked, anonymity, mental_health_consequence, physical_heath_consequence, coworkers, "
			+ "supervisor, mental_health_interview, physical_health_interview, mental_vs_physical_health,leaves)"
			+ "values( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?,"
			+ "? , ? , ? , ? , ? , ? , ? )";
    try {
		Class.forName("com.mysql.jdbc.Driver");
	 dbCon = DriverManager.getConnection(url,username,password);
	 dbCon.setAutoCommit(false);
	pstmt = dbCon.prepareStatement(paramQuery);
	int c=0,batchsize=1000;
	for(int i=0;i<health.size();i++)
	{c++;


Database obj=new Database();
obj=health.get(i);
pstmt.setInt(1,obj.getEmpid());
pstmt.setString(2,obj.getAge());
pstmt.setString(3,obj.getGender());
pstmt.setString(4,obj.getFamily_history());
pstmt.setString(5,obj.getTreatment());
pstmt.setString(6,obj.getWork_interfere());
pstmt.setString(7, obj.getRemote_work());
pstmt.setString(8,obj.getBenfits());
pstmt.setString(9,obj.getWellness_program());
pstmt.setString(10,obj.getSeek_help());
pstmt.setString(11,obj.getAnonymity());
pstmt.setString(12, obj.getMental_health_consequence());
pstmt.setString(13, obj.getPhysical_health_consequence());
pstmt.setString(14,obj.getCoworkers());
pstmt.setString(15,obj.getSupervisor());
pstmt.setString(16,obj.getMental_health_interview());
pstmt.setString(17,obj.getPhysical_health_interview());
pstmt.setString(18,obj.getMental_vs_physical());
pstmt.setString(19,obj.getPredict_leave());

pstmt.addBatch();
if((i+1)%batchsize==0) {pstmt.executeBatch();
dbCon.commit();
pstmt.clearBatch();
}
}

    }

    catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
	

public static void main(String[] args) {
ArrayList<Database> health = new ArrayList<Database>(); 
health=readFromCsvFile();
loadDataToDb(health);
}


}