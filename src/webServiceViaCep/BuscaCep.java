/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServiceViaCep;

/**
 *
 * @author Adm
 */
public class BuscaCep {
    public static void main(String[] args) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep("30290620");
        if(webServiceCep.wasSuccessful())
        {
            System.out.println("Rua : "+webServiceCep.getLogradouro()); 
            System.out.println("Bairro : "+webServiceCep.getBairro());
            System.out.println("Cidade : "+webServiceCep.getCidade());
            System.out.println("Estado : "+webServiceCep.getUf());
        }
        else
        {
            System.out.println("Cep Incorreto");
        }
        
    }
}
