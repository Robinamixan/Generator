package Main;
import java.util.Locale;
import java.util.MissingResourceException;

public class Generator {
	public static void main(String[] args) {
		int lengthGenerate = Integer.parseInt(args[0]);
		String languageGenerate = args[1];
		double dataMisstakes = Double.parseDouble(args[2]);
		if (checkLocale(languageGenerate, "ru_RU"))
			languageGenerate = "ru";
		else if (checkLocale(languageGenerate, "en_US"))
			languageGenerate = "en";
		else if (checkLocale(languageGenerate, "be_BY"))
			languageGenerate = "by";
		else 
			languageGenerate = "---";
		System.out.println(lengthGenerate + " " + languageGenerate +" "+ dataMisstakes);
	}
	
	public static Boolean checkLocale(String locale,String need) {
		Locale lo = parseLocale(locale);
		Locale lo1 = parseLocale(need);
		if (isValid(lo)) {
			if (lo.equals(lo1))
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
}
