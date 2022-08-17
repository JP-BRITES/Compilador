/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analisadorlexico;

/**
 *
 * @author aurel
 */

 
public class Token {
    public final static byte IDENTIFIER= 0, INTLITERAL = 1,BEGIN = 2, DO = 3, END = 4, TRUE = 5, FALSE =6, 
            ELSE = 7, IF = 8, THEN = 9, VAR = 10, WHILE = 11, 
            BECOMES = 12, ADD= 13, SUB = 14, OR = 15, MUL = 16, DIV =17, 
            AND=18, MENQ = 19, MAIQ = 20,MENQI = 21, MAIQI = 22, IGUA = 23, 
            DIF= 24, COMEN = 25, RPAREN = 26,LPAREN = 27, COLON = 28,
            DOT=29, EOT = 30, ERROR = 31, SEMICOLON = 32, REAL = 33, PROGRAM = 34;
    
    public final static String[] spellings ={
        "<indentifier>","<integer-literal>","begin","do","end","true","false","else","if","then", "var", "while",":=","add","sub","or","mul",
        "div","and","menor que","maior que","menor igual","maior igual", "igual","diferente","comentario",")","(",":",".","<eot>","<error>",
        ";","real", "program"
    };
          
    public byte kind;
    public String spelling;
    
    public Token (byte kind, String spelling ){
        this.kind = kind; 
        this.spelling = spelling;
}
   
}
