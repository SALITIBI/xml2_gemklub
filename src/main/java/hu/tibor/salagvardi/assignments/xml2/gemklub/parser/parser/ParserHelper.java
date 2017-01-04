package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Interval;
import hu.tibor.salagvardi.assignments.xml2.gemklub.parser.model.Price;

class ParserHelper {
	static Price extractPrice(String data) throws IOException {
		Pattern pattern = Pattern.compile("([\\d\\s\\xA0]+)[\\s\\xA0](Ft)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		return new Price(extractPriceValue(matcher.group(1)), matcher.group(2));
	}
	static Double extractPriceValue(String data){
		Pattern nonBreakableSpace = Pattern.compile("\\xA0");
		Matcher matcher2 = nonBreakableSpace.matcher(data);
		String value = matcher2.replaceAll("");
		return new Double(Double.parseDouble(value));
	}
	static Double extractAverageRating(String data) throws IOException{
		Pattern pattern = Pattern.compile("\\(([\\d\\.]+?)\\)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			throw new IOException("Malformed document");
		}
		
		return Double.parseDouble( matcher.group(1));
	}
	static Integer extractArrivalDateInDays(String data){
		Pattern pattern = Pattern.compile(".*?(\\d+).*?");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			return null;
		}
		return Integer.parseInt(matcher.group(1));
	}
	static Interval extractInterval(String data) throws IOException{
		Pattern pattern = Pattern.compile("(\\d+)\\s*-\\s*(\\d+)");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {
			
			 return new Interval(Integer.parseInt(data), Integer.parseInt(data));
		}
		return new Interval(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
	}
}
