
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
    
    private void parseLiteral() {
        switch(currentToken.kind) {
            case Token.BOOLEAN:
                parseBoolLit();
                break;
            case Token.INTLITERAL:
                parseIntLit();
                break;
            case Token.REAL:
                parseFloatLit(); // dúvida aqui nesses Tokens
                break;
            default:
                System.out.println("Erro");
        }
    }
    
    private void parseopAd() {
        switch (currentToken.kind) {
            case Token.ADD:
                acceptIt();
                break;
            case Token.SUB:
                acceptIt();
                break;
            case Token.OR:
                acceptIt();
                break;
            default:
                System.out.println("Erro");
        }
    }
    
    private void parseopMul() {
        switch (currentToken.kind) {
            case Token.MUL:
                acceptIt();
                break;
            case Token.DIV:
                acceptIt();
                break;
            case Token.AND:
                acceptIt();
                break;
            default:
                System.out.println("Erro");
        }
    }

    private void parseopRel() {
        switch (currentToken.kind) {
            case Token.MENQ:
                acceptIt();
                break;
            case Token.MAIQ:
                acceptIt();
                break;
            case Token.MENQI:
                acceptIt();
                break;
            case Token.MAIQI:
                acceptIt();
                break;
            case Token.IGUA:
                acceptIt();
                break;
            case Token.DIF:
                acceptIt();
                break;
            default:
                System.out.println("Erro");
        }
    }
    
    private void parsetipo() {
        parseTipoSimples();
    }
    
    private void parseTermo () {
        parseFator();
        while(currentToken.kind == Token.MUL || currentToken.kind == Token.DIV || currentToken.kind == Token.AND) {
            parseopMul();
            parseFator();
        }
    }
    
    private void parseTipoSimples() {
        switch(currentToken.kind) {
            case Token.INTLITERAL:
                acceptIt();
                break;
            case Token.REAL:
                acceptIt();
                break;
            case Token.BOOLEAN:
                acceptIt();
                break;
            default:
                System.out.println("Erro");
        }
    }
    
    private void parseVariavel() {
        parseId();
    }
    
    private void parseProgram() {
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.DOT);
    }
    
}
