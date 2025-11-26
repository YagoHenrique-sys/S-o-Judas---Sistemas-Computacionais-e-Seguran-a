package AtividadeCriptografia;
// AtividadeCriptografia.java
// -------------------------------------------------------------
// Programa de exemplo didático que demonstra:
// 1) Criptografia Simétrica
// 2) Criptografia Assimétrica (conceito)
// 3) Função Hash (SHA-256)
// -------------------------------------------------------------

import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AtividadeCriptografia {

   
    // CRIPTOGRAFIA SIMÉTRICA
    public static String criptografarSimetrico(String texto, int chave) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            resultado.append((char) (c + chave)); // desloca o caractere
        }
        return resultado.toString();
    }

    public static String descriptografarSimetrico(String texto, int chave) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            resultado.append((char) (c - chave)); // desfaz o deslocamento
        }
        return resultado.toString();
    }

    // - CRIPTOGRAFIA ASSIMÉTRICA 
    static BigInteger chavePublica = BigInteger.valueOf(5);
    static BigInteger chavePrivada = BigInteger.valueOf(29);
    static BigInteger n = BigInteger.valueOf(91);

    public static BigInteger[] criptografarAssimetrico(String mensagem) {
        BigInteger[] resultado = new BigInteger[mensagem.length()];

        for (int i = 0; i < mensagem.length(); i++) {
            BigInteger m = BigInteger.valueOf((int) mensagem.charAt(i));
            resultado[i] = m.modPow(chavePublica, n); // c = m^e mod n
        }
        return resultado;
    }

    public static String descriptografarAssimetrico(BigInteger[] codigo) {
        StringBuilder texto = new StringBuilder();

        for (BigInteger c : codigo) {
            BigInteger m = c.modPow(chavePrivada, n); // m = c^d mod n
            texto.append((char) m.intValue());
        }
        return texto.toString();
    }

    // - FUNÇÃO HASH (SHA-256)
    public static String gerarHash(String mensagem) {
        try {
            // Cria o objeto MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(mensagem.getBytes());

            // Converte os bytes em formato hexadecimal
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            return "Erro ao gerar hash: " + e.getMessage();
        }
    }