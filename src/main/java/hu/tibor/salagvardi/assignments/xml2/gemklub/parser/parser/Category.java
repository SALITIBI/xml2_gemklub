package hu.tibor.salagvardi.assignments.xml2.gemklub.parser.parser;

public enum Category {
	ALL(0), KIDS(1441), GIFT(1448), STRATEGY(1450), FAMILY(1455), LOGIC_PUZZLE(1467), PARTY(1485);
	private int uriCode;
	
	private Category(int uriCode) {
		this.uriCode = uriCode;
	}

	public int getUriCode() {
		return uriCode;
	}
	
	
}
