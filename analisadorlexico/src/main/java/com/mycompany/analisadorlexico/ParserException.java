/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analisadorlexico;

import java.io.FileNotFoundException;

/**
 *
 * @author aurel
 */
public class ParserException extends FileNotFoundException {
    public ParserException( Token currentToken, String expected,Scanner scan){
        super("SINTAX ERROR : "+ scan.linhas + ":" + scan.colunas +":" + " expected ' "+ expected +
                "' but received '" + Token.spellings[currentToken.kind] +" ' ");
    }
    
    
}
