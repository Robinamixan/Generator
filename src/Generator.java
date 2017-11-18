

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Random;

public class Generator {
	
	public static void main(String[] args) throws FileNotFoundException  {
		int lengthGenerate = Integer.parseInt(args[0]);
		String languageGenerate = args[1];
		double dataMisstakes = Double.parseDouble(args[2]);
		if (checkLocale(languageGenerate, "ru_RU"))
			genName(lengthGenerate, new Locale("ru", "RU"), dataMisstakes);
		else if (checkLocale(languageGenerate, "en_US"))
			genName(lengthGenerate, new Locale("en", "US"), dataMisstakes);
		else if (checkLocale(languageGenerate, "be_BY"))
			genName(lengthGenerate, new Locale("be", "BY"), dataMisstakes);
		else 
			languageGenerate = "---";
	}
	
	public static void genName(int amount, Locale loc, double mistakes) throws FileNotFoundException {
		GetRes gen = new GetRes();
		Random rand = new Random(System.nanoTime());
		ArrayList<String> namesM = gen.getNames(loc,true);
		ArrayList<String> lastnamesM = gen.getLastNames(loc,true);
		ArrayList<String> midlenamesM = gen.getMidleNames(loc,true);
		ArrayList<String> namesF = gen.getNames(loc,false);
		ArrayList<String> lastnamesF = gen.getLastNames(loc,false);
		ArrayList<String> midlenamesF = gen.getMidleNames(loc,false);
		StringBuilder sb = new StringBuilder();
		String temp,temp1;
		for (int i = 0; i < amount; i++) {
			
			if (loc.equals(new Locale("ru", "RU")) || loc.equals(new Locale("be", "BY")))
			{
				if (rand.nextBoolean()) {
					sb.append(lastnamesM.get(rand.nextInt(lastnamesM.size())) + " ");
					sb.append(namesM.get(rand.nextInt(namesM.size())) + " ");
					sb.append(midlenamesM.get(rand.nextInt(midlenamesM.size()))+ ", ");
				}
				else {
					sb.append(lastnamesF.get(rand.nextInt(lastnamesF.size())) + " ");
					sb.append(namesF.get(rand.nextInt(namesF.size())) + " ");
					sb.append(midlenamesF.get(rand.nextInt(midlenamesF.size()))+ ", ");
				}
			}
			else
			{
				if (rand.nextBoolean()) {
					temp1 = midlenamesM.get(rand.nextInt(midlenamesM.size())) + " ";
					if (temp1.equals(" "))
						temp1 = "";
					sb.append(temp1);
					sb.append(lastnamesM.get(rand.nextInt(lastnamesM.size())) + " ");
					sb.append(namesM.get(rand.nextInt(namesM.size())) + ", ");
				}
				else {
					temp1 = midlenamesF.get(rand.nextInt(midlenamesF.size())) + " ";
					if (temp1.equals("  "))
						temp1 = "";
					sb.append(temp1);
					sb.append(lastnamesF.get(rand.nextInt(lastnamesF.size())) + " ");
					sb.append(namesF.get(rand.nextInt(namesF.size())) + ", ");
				}
			}
			
			
			sb.append(getPhoneNumber(loc));
			
			temp = Makemisstakes(new String(sb), mistakes, loc);
			System.out.println(temp);
			sb.delete(0,sb.length());
		}
		
	}
	
	public static String getPhoneNumber(Locale loc) {
		Random rand = new Random(System.nanoTime());
		StringBuilder sb = new StringBuilder();
		if (loc.equals(new Locale("ru", "RU"))) {
			sb.append("+7(9"+rand.nextInt(99)+")");
			sb.append(rand.nextInt(999)+"-");
			sb.append(rand.nextInt(99)+"-");
			sb.append(rand.nextInt(99));
		}
		else if (loc.equals(new Locale("en", "US"))) {
			sb.append("+1("+rand.nextInt(999)+")");
			sb.append(rand.nextInt(999)+"-");
			sb.append(rand.nextInt(99)+"-");
			sb.append(rand.nextInt(99));
		}
		else if (loc.equals(new Locale("be", "BY"))) {
			sb.append("+375(0"+rand.nextInt(99)+")");
			sb.append(rand.nextInt(999)+"-");
			sb.append(rand.nextInt(99)+"-");
			sb.append(rand.nextInt(99));
		}
		return new String(sb);
	}
	
	public static Boolean checkLocale(String locale,String need) {
		Locale loc = parseLocale(locale);
		Locale loc1 = parseLocale(need);
		if (isValid(loc)) {
			if (loc.equals(loc1))
				return true;
			else 
				return false;
		} else {
			return false;
		}
	}

	private static Locale parseLocale(String locale) {
		String[] parts = locale.split("_");
		switch (parts.length) {
			case 3: return new Locale(parts[0], parts[1], parts[2]);
		    case 2: return new Locale(parts[0], parts[1]);
		    case 1: return new Locale(parts[0]);
		    default: throw new IllegalArgumentException("Invalid locale: " + locale);
		}
	}	

	private static boolean isValid(Locale locale) {
		try {
			return locale.getISO3Language() != null && locale.getISO3Country() != null;
		} catch (MissingResourceException e) {
		    return false;
		}
	}
		
	public static String Makemisstakes(String d, double perc, Locale loc) {
		Random rand = new Random(System.nanoTime());
		StringBuffer sb = new StringBuffer(d);
		int posit,mist, sec, chr;
		double v = 3.3, tmp;
		if (perc < 1 && perc > 0)
		{
			tmp = rand.nextDouble();
			if (perc <= tmp)
				perc = 1;
		}
		for (int i = 0; i < perc; i++) {
			posit = rand.nextInt(sb.length()-1);
			mist = rand.nextInt(3);
			mist = getMist(v);
			switch (mist){
			case 0:{
				if (loc.equals(new Locale("ru", "RU")) || loc.equals(new Locale("be", "BY")))
					chr = rand.nextInt(31)+'а';
				else
					chr = rand.nextInt(26)+'a';
				char l = ((char) chr );
				sb.insert(posit, l);
				v-=0.17;
				break;
			}
			case 1:{
				sb.deleteCharAt(posit);
				v+=0.17;
				break;
			}
			case 2:{
				if (posit == sb.length()-1)
					sec = posit-1;
				else 
					sec = posit+1;
				char temp = sb.charAt(posit);
				sb.setCharAt(posit, sb.charAt(sec));
				sb.setCharAt(sec, temp);
				break;
			}
			}
		}
		return new String(sb);
	}
	
	private static int getMist(double v) {
		Random rand = new Random(System.nanoTime());
		double perc = 10*rand.nextDouble();
		if (perc<v)
			return 0;
		else if (perc >= v && perc < 6)
			return 1;
		else if (perc >= 6 && perc < 10)
			return 2;
		return 2;
	}
}
