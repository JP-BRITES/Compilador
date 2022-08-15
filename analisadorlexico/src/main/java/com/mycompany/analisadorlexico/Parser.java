
package com.mycompany.analisadorlexico;
  
import java.io.IOException;

public class Parser {
    private Token currentToken;
    private Scanner  scan;
    
    public void parse(String args) throws IOException{
        
        scan = new Scanner();
        
        currentToken = scan.scan();
        
        if (currentToken != null)
        {
            parseProgram();
        }
    }
    private void accept(byte expectedToken) throws ParserException{
        if (currentToken.kind == expectedToken){
            acceptIt();
            
        }
        else{
            //sintaxError(expectedToken);
            System.out.println("ERROR");
        }
        
    }
    
    private void sintaxError(byte expectedToken) throws ParserException{
        throw new ParserException(currentToken, Token.spellings[expectedToken], scan);
    }
    private void acceptIt(){
        Token temp = scan.scan();
        while(temp == null){
            temp = scan.scan();
            
        }
        currentToken = temp;
        
    }
    
}
