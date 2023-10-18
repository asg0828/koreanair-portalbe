package com.bdp.ap.common.tableauapi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomStringBuilder {
	public static final String NUMBER = "0123456789";
    public static final String ALPHABET_LOWER_CASE = "abcdefghijkmnlopqrstuvwxyz";
    public static final String ALPHABET_UPPER_CASE = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";
    public static final String ALPHABET = ALPHABET_LOWER_CASE + ALPHABET_UPPER_CASE;
    public static final String SPECIAL = "~!@#$%^&*()_+{}|\\\"`;:'<>?,./=-[]";
     
     
    private HashSet<Character> mExcludedCharSet = new HashSet<Character>();
    private ArrayList<Character> mLimitCharList = new ArrayList<Character>();
             
    int mLength = 32;
     
    public String build() {
        return generateRandomString(mLength);
    }
     
    public RandomStringBuilder setLength(int length) {
        mLength = length;
        return this;
    }
     
    public int getLength() {
        return mLength;
    }
     
    public RandomStringBuilder putExcludedChar(char excluded) {
        mExcludedCharSet.add(excluded);
        return this;
    }
     
    public RandomStringBuilder putExcludedChar(char[] excludedList) {
        for(char excluded : excludedList) 
                putExcludedChar(excluded);
        return this;
    }
     
    public RandomStringBuilder putExcludedChar(String excluded) { 
                putExcludedChar(excluded.toCharArray());
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(char limited) {
        mLimitCharList.add(limited);
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(char[] limitedList) {
        for(char limited : limitedList) 
                putLimitedChar(limited);
        return this;
    }
     
    public RandomStringBuilder putLimitedChar(String limited) {
        putLimitedChar(limited.toCharArray());
        return this;
    }
     
    public boolean removeExcluded(char excluded) {
        return mExcludedCharSet.remove(excluded);
    }
     
    public boolean removeLimitedChar(char limited) {
        return mLimitCharList.remove((Character)limited);
    }
     
    public void clearExcluded() {
        mExcludedCharSet.clear();
    }
    public void clearLimited() {
        mLimitCharList.clear();
    }
     
     
    /**
     * 랜덤 문자열 생성.
     * @param length 문자열 길이
     * @return 랜덤 문자열
     */
    public String generateRandomString(int length) {
        boolean runExcludeChar = !isExcludedCharInLimitedChar();            
        StringBuffer strBuffer = new StringBuffer(length);
        Random rand = new Random(System.nanoTime());
        for(int i = 0; i < length; ++i ) {
            char randomChar = makeChar(rand);
            if(runExcludeChar)
                randomChar = excludeChar(rand, randomChar);
            strBuffer.append(randomChar);
        }
        return strBuffer.toString();
    }
     
    private boolean isExcludedCharInLimitedChar() {
        return mExcludedCharSet.containsAll(mLimitCharList);
    }
     
    private char excludeChar(Random rand, char arg) {
        while(mExcludedCharSet.contains(arg)) {
            arg = makeChar(rand);
        }
        return arg;
    }
    private char makeChar(Random rand) {
        if(mLimitCharList.isEmpty())
            return (char)(rand.nextInt(94) + 33);
        else
            return mLimitCharList.get(rand.nextInt(mLimitCharList.size()));
    }
    
    public static String getRamdomPassword(int len) { 
    	char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    					, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' 
    					, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    					}; 
    	
    	int idx = 0; StringBuffer sb = new StringBuffer(); 
    	
    	for (int i = 0; i < len; i++) { 
    		idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
    		sb.append(charSet[idx]); 
    	} 
    	return sb.toString(); 
    }
}