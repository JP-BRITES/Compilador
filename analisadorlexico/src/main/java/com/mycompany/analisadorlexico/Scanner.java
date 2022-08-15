/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analisadorlexico;
import static com.mycompany.analisadorlexico.Token.BEGIN;
import static com.mycompany.analisadorlexico.Token.WHILE;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner{
 
    private char currentChar = leitura(); //receber primeiro caracter do arquivo
    private byte currentKind; 
    private StringBuffer currentSpelling;
    private int cont = 0;
    private int i;
    private int aux =0;
    public int colunas = 0;
    public int linhas = 1;
    private int tam;
    private int k;
    private int v=0;   
    private int isreal = 0;
    
    private void take (char expectedChar){
        if ((int)currentChar == 10){
            currentChar = '\n';
        }
        
        if (currentChar == expectedChar){
            
            currentSpelling.append(currentChar);
            cont++;
            currentChar = proximo();
            colunas++;
        } else {
            System.out.println("ERRO LEXICO"); // escrever no arquivo de saida erro
        }
         
  
    }
      
     private void takeIt(){
     if (currentChar == '\n'){
         v++;
         linhas++;
         if (v==2){
             linhas--;
             v=0;
         }
         
     }
     colunas++;
     currentSpelling.append(currentChar);
     cont++;
     currentChar = proximo();     
}
       
       private boolean isDigit(char c){
           
           return (c >= '0' && c <= '9');
       }
       private boolean isLetter (char c){
           return (c >= 'a' && c <= 'z') || (c>='A' && c <='Z');
       }
       private boolean isGraphic (char c){
           int i = c;
           return (i>32);
           
           
       }
       
       private void scanSeparator() {
           switch (currentChar) {
               case '!': {
                   takeIt(); 
                   while ( isGraphic(currentChar)){
                       takeIt();
                   }
                   take('\n');
               } break;
               case ' ': takeIt();break;
               case '\n' : {takeIt();colunas=0;break;}
               
           }
       }
       
       private byte scanToken () {
     switch(currentChar){
       case 'a' : case 'b' : case 'c' : case 'd': case 'e': case 'f':
        case 'g': case 'h' : case 'i': case 'j' : case 'k' : case 'l' : case 'm': case 'n' : case 'o':
       case 'p' : case 'q':  case'r' :case 's' : case 't' : case 'u': case 'v': case 'w': case 'y': case 'z': case 'x':
         
           case 'A': case 'B' : case 'C' : case 'D': case 'E':case 'F':
            case'G' :case 'H' :case 'I': case 'J': case'K': case'L': case'M': case'N': case'O':
            case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U':case 'V':case 'W': case 'Y': case 'Z': case 'X':   
                
           takeIt();
         
         while(isLetter (currentChar)||isDigit(currentChar)) takeIt();
           
         tam = currentSpelling.length();
         for (k = BEGIN; k<= WHILE; k++){
            
             
             if(Token.spellings[k].equals(currentSpelling.toString())){
                
                System.out.println("Atual tipo: "+k);
                System.out.println("Atual Conteudo: "+currentSpelling);
                System.out.println("Coluna: "+ (colunas - (tam-1))+", Linhas: "+linhas+'\n');
                aux=0;
                break;
               
                
             }
             else{
                 
                 aux++;
             }
         
         }
        
         
         if (aux == 10){

            
             System.out.println("Atual tipo: "+Token.IDENTIFIER);
             System.out.println("Atual Conteudo: "+currentSpelling);
             System.out.println("Coluna: "+ (colunas - (tam-1))+", Linhas: "+linhas+'\n');
             aux=0;
         }

         
         return Token.IDENTIFIER;
     
      case'0': case'1': case'2': case'3': case'4':
      case'5': case'6': case'7': case'8': case'9':
          takeIt();
          while(isDigit(currentChar) == true){
             takeIt();
             if(currentChar == '.'){
                 takeIt();
                 isreal = 1;
             }
          }
          if (isreal == 1){
              System.out.println("Atual tipo: "+Token.REAL);
              System.out.println("Atual Conteudo: "+currentSpelling);
              tam = currentSpelling.length();
              System.out.println("Coluna: "+ (colunas - (tam-1))+", Linhas: "+linhas+'\n');
              return Token.REAL;
          }
          else{
              
          System.out.println("Atual tipo: "+Token.INTLITERAL);
          System.out.println("Atual Conteudo: "+currentSpelling);
          tam = currentSpelling.length();
          System.out.println("Coluna: "+ (colunas - (tam-1))+", Linhas: "+linhas+'\n');
          return Token.INTLITERAL;
          }
          
      case '+' :  takeIt(); System.out.println("Atual tipo: "+Token.ADD);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.ADD; 
      case '-' : takeIt(); System.out.println("Atual tipo: "+Token.SUB);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.SUB; 
      case '|': takeIt(); System.out.println("Atual tipo: "+Token.OR);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.OR;  
      case '*' : takeIt(); System.out.println("Atual tipo: "+Token.MUL);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.MUL;
      case '/' : takeIt(); System.out.println("Atual tipo: "+Token.DIV);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.DIV;
      case '&' :takeIt(); System.out.println("Atual tipo: "+Token.AND);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.AND;
      case '<' : takeIt();if (currentChar == '='){takeIt(); System.out.println("Atual tipo: "+Token.MENQI);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.MENQI;} else { if (currentChar == '>'){takeIt(); System.out.println("Atual tipo: "+Token.DIF);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n'); return Token.DIF;}System.out.println("Atual tipo: "+Token.MENQ);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.MENQ;} 
      case '>' : takeIt();if (currentChar == '='){takeIt(); System.out.println("Atual tipo: "+Token.MAIQI);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.MAIQI;} System.out.println("Atual tipo: "+Token.MAIQ);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n'); return Token.MAIQ;
      case '=' : takeIt(); System.out.println("Atual tipo: "+Token.IGUA);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.IGUA; 
      case '.' : takeIt(); System.out.println("Atual tipo: "+Token.DOT);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.DOT;
      case ';' : takeIt(); System.out.println("Atual tipo: "+Token.SEMICOLON);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.SEMICOLON;
      case '!' : takeIt(); System.out.println("Atual tipo: "+Token.COMEN);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.COMEN;
      case ')' : takeIt(); System.out.println("Atual tipo: "+Token.RPAREN);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.RPAREN;
      case '(' : takeIt(); System.out.println("Atual tipo: "+Token.LPAREN);System.out.println("Atual Conteudo: "+currentSpelling); System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.LPAREN;
      case ':' : takeIt(); if(currentChar == '='){takeIt();System.out.println("Atual tipo: "+Token.BECOMES);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.BECOMES;}
      else {System.out.println("Atual tipo: "+Token.COLON);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.COLON;}
      case '\000' : takeIt(); System.out.println("Atual tipo: "+Token.EOT);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.EOT;
      
      default : System.out.println("Atual tipo: "+Token.ERROR);System.out.println("Atual Conteudo: "+currentSpelling);System.out.println("Coluna: "+colunas+", Linhas: "+linhas+'\n');return Token.ERROR;
      }
           
}
       
       public  Token scan(){
               
           while (currentChar == '!'|| currentChar == ' ' ||currentChar == '\n'){
               scanSeparator();
               }
           currentSpelling= new StringBuffer ("");
           currentKind = scanToken();
           
           return new Token (currentKind,currentSpelling.toString());
           
           
       }
       
       public char leitura() {
           
           BufferedReader reader = null;
                    try {
                            reader = new BufferedReader(new InputStreamReader(new FileInputStream("teste.txt")));
                        } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        int c;char character=0;
                        try {
                                c = reader.read();
                                if(c!=-1){
                                    character = (char) c;
                                 return (character);
                                }
                                else
                                {
                                    return ('\000');
                                }

                                } catch (IOException ex) {
                                        Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);

                                                                 }

       return (character);
   }
       
       public char proximo() {
           
           
           BufferedReader reader = null;
                    try {
                            reader = new BufferedReader(new InputStreamReader(new FileInputStream("teste.txt")));
                        } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        int c = 0;char character=0;
                        try {
                                for(i=0;i<cont+1;i++){
                                   c = reader.read(); 
                                   if ((int)c == 13){
                                       c = '\n';
                                   }
                                   
                                }
                                
                                if(c!=-1){
                                    character = (char) c;
                                 return (character);
                                }
                                else
                                {
                                    return ('\000');
                                }

                                } catch (IOException ex) {
                                        Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);

                                                                 }

       return (character);
   }
           
           
           
           
       
       
      
}

  
