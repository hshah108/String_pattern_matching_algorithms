package demo.app;

public class StringPatternMatchingDemo {

	public static void main(String[] args) {
		String sourceString = "abcpqrxyz";
		String pattern = "bcpqrxyz";
		
		//boolean isPatternFound = bruteForceAlgorithm(sourceString, pattern);
		boolean isPatternFound = rabinKarpAlgorithm(sourceString, pattern);
		
		System.out.println("Pattern found : " + isPatternFound);
	}
	
	private static boolean bruteForceAlgorithm(String sourceString, String pattern) {
		
		int sourceLength = sourceString.length();
		int patternLength = pattern.length();
		
		for(int i = 0; i <= (sourceLength-patternLength) ; i++) {
			int patternCounter = 0;
			while(sourceString.charAt(patternCounter+i) == pattern.charAt(patternCounter)) {
				patternCounter++;
				if(patternCounter == patternLength) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean rabinKarpAlgorithm (String sourceString, String pattern) {
		
		int sourceLength = sourceString.length();
		int patternLength = pattern.length();
		double patternHash = 0;
		double sourceHash = 0;
		String substring = "";
		boolean matchFound = false;

		//calculate hash for pattern and also a block of characters matching pattern length of source string to avoid duplicate looping
		for( int i=0; i<patternLength; i++) {
			patternHash += pattern.charAt(i)*Math.pow(10, i);
			sourceHash += sourceString.charAt(i)*Math.pow(10, i);
		}
		
		if(patternHash == sourceHash) {
			matchFound = compareString(sourceString, pattern);
		}
		
		if(matchFound) {
			return true;
		}
		
		for(int i=0; i < (sourceLength-patternLength); i++) {
			sourceHash = recalculateHash(sourceHash, i, sourceString, patternLength);
			if(sourceHash == patternHash) {
				return compareString(substring, pattern);
			}
		}
		return false;
	}
	
	private static boolean compareString(String source, String pattern) {
	
		int length = source.length();
		
		for(int i=0; i<length; i++) {
			if(source.charAt(i) != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	private static double recalculateHash(double sourceHash, int startIndex, String s, int patternLength) {
		return (sourceHash - s.charAt(startIndex))/10 + s.charAt(startIndex+patternLength)*Math.pow(10,patternLength-1);
	}
	
	private static boolean kmpAlgorithm() {
		return false;
	}
}
