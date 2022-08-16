
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
<<<<<<< Updated upstream
=======
    //PARTE DE JP
    
    private void parseAtribuicao()throws ParserException{
        parseVariavel();
        
        accept(Token.BECOMES);
        
        parseExpressao();
    }
    
    private void parseBooLit()throws ParserException {
        switch (currentToken.kind)
        {
            case Token.TRUE:
                acceptIt();
                break;
               
            case Token.FALSE:
                acceptIt();
                break;
        
    }
    }
    
    private void parseComando() throws ParserException{
        switch(currentToken.kind)
         {
            case Token.IDENTIFIER:
                parseAtribuicao();
                break;
            case Token.IF:
                paseCondicional();
                break;
            case Token.BEGIN:
                paseComandoComposto();
                break;
            case Token.WHILE:
                parseIterativo():
                break;
         }
    }
    
    private void parseComandoComposto() throws ParserException{
        accept(Token.BEGIN);
       
        parseListaDeComandos();
        
        accept(Token.END);
    }
    
    private void paseCondicional()throws ParserException{
        accept(Token.IF);
        
        parseExpressao();
        
        accept(Token.THEN);
        
        parseComando();
        switch(currentToken.kind)
        {
            case Token.ELSE:
                acceptIt();
                parseComando();
                break;
            case Token.EOT:
                acceptIt();
                break;
                
        }
    }
    
    private void parseCorpo()throws ParserException{
        parseDeclaracoes();
        parseComandoComposto();
    }
    
    private void parseDeclaracao()throws ParserException{
        parseDeclaracaoDeVariavel();
        
    }
    
    private void parseDeclaracaoDeVariavel()throws ParserException{
        accept(Token.VAR);
        parseListaDeIds();
        accept(Token.COLON);
        parseTipo();
        
    }
    
    private void parseDeclaracoes() throws ParserException{
        
        while (currentToken.kind == Token.VAR)
        {
           parseDeclaracao();
        }
    }
    
    private void parseExpressao() throws ParserException {
        if(currentToken.kind == Token.MENQI || 
            currentToken.kind == Token.MAIQ || 
            currentToken.kind == Token.MAIQI || 
            currentToken.kind == Token.MENQI || 
            currentToken.kind == Token.IGUA || 
            currentToken.kind == Token.DIF )
        {
            //acceptIt();
            parseOpad();
            parseTermo();
        }
    }
    
>>>>>>> Stashed changes
    
}
