package exercicios_criptografia_calvetti;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BlocoDeNotas {
    
    private PrintStream psCifrado, psDecifrado;
    private String texto;           
           
    private void criaTxt(Path path) throws Exception{
        if(Files.notExists(path)){
            Files.createFile(path);
            Files.writeString(path, "caminho criado automaticamente");
        } 
    }
    
    public void geraTxt() throws Exception {     
        Path path = Path.of("C:\\texts_java\\texto_cifrado.txt");
        criaTxt(path);
        FileOutputStream fosCifrado = new FileOutputStream(path.toString());
        psCifrado = new PrintStream(fosCifrado);
      
        path = Path.of("C:\\texts_java\\texto_decifrado.txt");      
        criaTxt(path);  
        FileOutputStream fosDecifrado = new FileOutputStream(path.toString());
        psDecifrado = new PrintStream(fosDecifrado);        
    }
    
    public PrintStream getPsTxtCifrado(){     
        return psCifrado;
    }
    
    public PrintStream getPsTxtDecifrado(){       
        return psDecifrado;        
    }
        
    public void lerTxt() throws Exception{
        Path path = Path.of("C:\\texts_java\\msgClara.txt");
        criaTxt(path);
        byte[] bytes = Files.readAllBytes(path);
        texto = new String(bytes);       
    }
    
    public String getMensagemTxt(){
        return texto;       
    }
    
}
