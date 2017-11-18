
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class GetRes {
	static GetRes rl = new GetRes();
	
	public static final String PATH = "/res";
	
	public static void getFile() {
		File f = new File(rl.getClass().getResource("Female/455.jpg").toString());
		
		
		
		try(BufferedReader out = new BufferedReader(new FileReader(f))){
			out.close();
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
	}
	
	public static void load() throws FileNotFoundException {
		
		InputStream inp =  GetRes.class.getResourceAsStream("RU_name.txt");
		InputStreamReader inpst = new InputStreamReader(inp);
		
		try(BufferedReader out = new BufferedReader(inpst)){
			
			out.close();
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
	}
	
	public ArrayList<String> getNames(Locale loc, boolean sex) throws FileNotFoundException {
		String sb,filename = "", path;
		if (sex)
			path = "/Male";
		else
			path = "/Female";
		if (loc.equals(new Locale("ru", "RU")))
			filename = path + "/RU_name.txt";
		else if (loc.equals(new Locale("en", "US")))
			filename = path + "/US_name.txt";
		else if (loc.equals(new Locale("be", "BY")))
			filename = path + "/BY_name.txt";
		InputStream inp =  GetRes.class.getResourceAsStream(filename);
		InputStreamReader inpst = new InputStreamReader(inp);
		ArrayList<String> names = new ArrayList<String>();
		try(BufferedReader out = new BufferedReader(inpst)){
			while((sb = out.readLine())!=null) {
				names.add(sb);
			}
			out.close();
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
		return names;
	}
	
	public ArrayList<String> getLastNames(Locale loc, boolean sex) throws FileNotFoundException {
		String sb,filename = "",path;
		if (sex)
			path = "/Male";
		else
			path = "/Female";
		if (loc.equals(new Locale("ru", "RU")))
			filename = path + "/RU_last_name.txt";
		else if (loc.equals(new Locale("en", "US")))
			filename = path + "/US_last_name.txt";
		else if (loc.equals(new Locale("be", "BY")))
			filename = path + "/BY_last_name.txt";
		InputStream inp =  GetRes.class.getResourceAsStream(filename);
		InputStreamReader inpst = new InputStreamReader(inp);
		ArrayList<String> names = new ArrayList<String>();
		try(BufferedReader out = new BufferedReader(inpst)){
			while((sb = out.readLine())!=null) {
				names.add(sb);
			}
			out.close();
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
		return names;
	}
	
	public ArrayList<String> getMidleNames(Locale loc, boolean sex) throws FileNotFoundException {
		String sb,filename = "",path;
		if (sex)
			path = "/Male";
		else
			path = "/Female";
		if (loc.equals(new Locale("ru", "RU")))
			filename = path + "/RU_midle_name.txt";
		else if (loc.equals(new Locale("be", "BY")))
			filename = path + "/BY_midle_name.txt";
		else if (loc.equals(new Locale("en", "US")))
			filename = path + "/US_prefix.txt";
		InputStream inp =  GetRes.class.getResourceAsStream(filename);
		InputStreamReader inpst = new InputStreamReader(inp);
		ArrayList<String> names = new ArrayList<String>();
		try(BufferedReader out = new BufferedReader(inpst)){
			while((sb = out.readLine())!=null) {
				names.add(sb);
			}
			out.close();
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
		return names;
	}

}
