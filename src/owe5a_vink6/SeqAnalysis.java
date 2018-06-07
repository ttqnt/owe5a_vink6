package owe5a_vink6;

/**
 *
 * @author karin
 */

public class SeqAnalysis extends Exception {
    static final char[] PL = {'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'K', 'S', 'T', 'Y'};
    static final char[] NP = {'A', 'F', 'I', 'L', 'M', 'P', 'W', 'V'};
    private String protSeq;

    SeqAnalysis(String input) throws InvalidSeqException{
	checkSequence(input);
    }
    //controleert of de invoer een geldige eiwitsequentie is en gooit anders een exception op
    private void checkSequence (String input) throws InvalidSeqException{
	String seq = input.toUpperCase();	
	if(seq.matches("[ACDEFGHIKLMNPQRSTVWY]+")){
            protSeq = seq;
	} else {
            throw new InvalidSeqException();
        }
    }
    //retourneert aantal eiwitten in de sequentie
    public int getTotal(){
	return protSeq.length();
    }
    
    //geeft het percentage polaire eiwitten
    public int  getPolar(){
	int  polar = 0;
	String temp = "";
		
	for(int a = 0; a < protSeq.length(); a++){
            for(int i = 0; i < PL.length; i++){
                if (PL[i] == (protSeq.charAt(a) )) {
                    temp += PL[i];
                }
            }
        }
	polar = ((temp.length()*100)/(protSeq.length()));
	return polar;
    }
	
    //retourneerd het percentage nonpolaire eiwitten
    public int getNonpolar(){
	int npolar;
	String temp = "";
		
	for(int a = 0; a < protSeq.length(); a++){
            for(int i = 0; i < NP.length; i++){
		if (NP[i] == (protSeq.charAt(a) )) {
                    temp += NP[i];
                }
            }
        }
        npolar = (temp.length()*100/(protSeq.length()));
	return npolar;
    }
	
}

