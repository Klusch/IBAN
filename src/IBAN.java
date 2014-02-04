import java.math.BigInteger;


public class IBAN {

	private static String countryToNumber(String country) {
		String rep = "";
		
		country = country.toUpperCase();
        for (int i=0; i<country.length(); i++) {
        	char character = country.charAt(i);
        	int ascii = (int) character;
    		rep = rep + new Integer(ascii - 55).toString();
        }

		return rep + "00";
	}
	
	private static String createIBAN(String blz, String ktnr, String land) {
		String bban = blz + ktnr;
	    String concat = bban + countryToNumber(land);
	    BigInteger bg = new BigInteger(concat);
	    BigInteger proof = new BigInteger("98").subtract(bg.mod(new BigInteger("97")));
	    
		String hilf = proof.toString();
		if (hilf.length() == 1) {
			hilf = "0" + hilf;
		}
		
		return "DE " + hilf + " " + bban;
	}
	
	private static boolean proofIBAN() {
		
		BigInteger t = new BigInteger("700901001234567890131408");
		if (t.mod(new BigInteger("97")).equals(new BigInteger("1"))) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String blz = "27070024"; //"70090100";
		String ktnr = "0100580000"; //"1234567890";
		String land = "DE";
	    System.out.println(createIBAN(blz, ktnr, land));
	    
	    System.out.println(proofIBAN());
	}

}
