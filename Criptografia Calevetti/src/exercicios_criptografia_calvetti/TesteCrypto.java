package exercicios_criptografia_calvetti;

import java.io.*;

public class TesteCrypto 
{
    public static void main(String[] args) throws Exception 
    {
        BlocoDeNotas bdn = new BlocoDeNotas();
        bdn.lerTxt();
        bdn.geraTxt();
        
        String sMsgClara = bdn.getMensagemTxt();// exercicio 1
        String sMsgCifrada = null;
        String sMsgDecifrada = null;
        byte[] bMsgClara = null;
        byte[] bMsgCifrada = null;
        byte[] bMsgDecifrada = null;
             
        //exercicio 2
        PrintStream psCifrado = bdn.getPsTxtCifrado();
        PrintStream psDecifrado = bdn.getPsTxtDecifrado();        
        System.setOut(psCifrado);
        
        Impressora prn = new Impressora();
        System.out.println("----------------------------------------------------------------------------------");
        
        System.out.println(">>> Imprimindo mensagem original...\n");
        //converte o texto string dado no equivalente byte[]
        bMsgClara = sMsgClara.getBytes("ISO-8859-1");
        System.out.println("Mensagem Clara (Hexadecimanl):"); 
        System.out.println(prn.hexBytesToString(bMsgClara)+"\n");
        System.out.println("Mensagem Clara (String):\n");
        System.out.println(sMsgClara+"\n");
        
        System.out.println(">>> Cifrando com o algoritmo Dummy...\n");
        
        CryptoDummy cdummy = new CryptoDummy();
        // gera a chave dummy simetrica e nome do arquivo onde sera armazenada
        cdummy.geraChave(new File ("chave.dummy"));
        // gera a cifra dummy da mensagem dada, com a chave dummy simetrica dada
        cdummy.geraCifra(bMsgClara, new File ("chave.dummy"));
        bMsgCifrada = cdummy.getTextoCifrado();
        // converte o texto byte[] no equivalente String
        sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
                
        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgCifrada)+"\n");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada+"\n");
        
        System.setOut(psDecifrado);
                
        System.out.println(">>> Decifrando com o algoritmo Dummy...\n");
        
        cdummy.geraDecifra(bMsgCifrada, new File ("chave.dummy"));
        bMsgDecifrada = cdummy.getTextoDecifrado();
        sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
        
        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgDecifrada)+"\n");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada+"\n");
                        
        System.setOut(psCifrado);
        
        System.out.println(">>> Cifrando com o algoritmo AES...\n");
        
        CryptoAES caes= new CryptoAES();
        caes.geraChave(new File ("chave.simetrica"));
        caes.geraCifra(bMsgClara, new File ("chave.simetrica"));
        bMsgCifrada = caes.getTextoCifrado();
        
        sMsgCifrada = (new String (bMsgCifrada,"ISO-8859-1"));
        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgCifrada)+"\n");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada+"\n");
        
        System.setOut(psDecifrado);
        
        System.out.println(">>> Decifrando com o algoritmo AES...\n");
        caes.geraDecifra(bMsgCifrada, new File("chave.simetrica"));
        bMsgDecifrada = caes.getTextoDecifrado();
        sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
        
        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgDecifrada)+"\n");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada+"\n");
        
        System.setOut(psCifrado);
                
        System.out.println(">>> Cifrando com o algoritmo RSA...\n");
        CryptoRSA crsa = new CryptoRSA();
        crsa.geraParDeChaves(new File("chave.publica"), new File("chave.privada"));
        crsa.geraCifra(bMsgClara, new File("chave.publica"));
        bMsgCifrada = crsa.getTextoCifrado();
        sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
        
        System.out.println("Mensagem Cifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgCifrada)+"\n");
        System.out.println("Mensagem Cifrada (String):");
        System.out.println(sMsgCifrada+"\n");
        
        System.setOut(psDecifrado);
       
        System.out.println(">>> Decifrando com o algoritmo RSA...\n");
        crsa.geraDecifra(bMsgCifrada, new File("chave.privada"));
        bMsgDecifrada = crsa.getTextoDecifrado();
        sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
        
        System.out.println("Mensagem Decifrada (Hexadecimal):");
        System.out.println(prn.hexBytesToString(bMsgDecifrada)+"\n");
        System.out.println("Mensagem Decifrada (String):");
        System.out.println(sMsgDecifrada+"\n");        
    }
}
