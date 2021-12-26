/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComandosCMD;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Loaderon - Alvaro SC
 */
public class Comando {

    /**
     * Se ejecuta un programa de windows utilizando cmd y lso respectivos comandos
     * @param ruta La ruta completa hacia el programa
     * @param nombrePrograma El nombre del programa sin incluír extensión
     * @param extensionPrograma Sólo la extensión del programa sin incluír el punto de separación
     */
    public static void ejecutarPrograma (String ruta, String nombrePrograma, String extensionPrograma){
        String comando = "cmd /c cd" + ruta + "&& start " + nombrePrograma + "." + extensionPrograma;
        ejecutarComando(comando);
    }
    
    /**
     * Recibe un String conteniendo el comando cmd a ejecutar ejemplo: String
     * comando; comando = "cmd /c cd C:\\users\\loaderon\\music\\listas de
     * reproducción && start ejercicios.wpl";
     *
     * @param comando
     */
    public static void ejecutarComando(String comando) {
        try {
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Error!", 0);
        }
    }

    /**
     * Recibe el nombre de un proceso e intenta terminar con el mismo devuelve
     * error si algo sale mal Funciona para windows y linux
     *
     * @param nombreProceso el nombre del proceso que quieres temrminar
     */
    private static void matarProceso(String nombreProceso) {
        String osName = System.getProperty("os.name");
        String cmd = "";
        if (osName.toUpperCase().contains("WIN")) {//S.O. Windows
            cmd += "tskill " + nombreProceso;
        } else {//Solo ha sido probado en win y linux
            cmd += "killall " + nombreProceso;
        }
        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec(cmd);
            hijo.waitFor();
            if (hijo.exitValue() == 0) {
                System.out.println(nombreProceso + " matado con exito");
            } else {
                System.out.println("Incapaz de matar " + nombreProceso + ". Exit code: " + hijo.exitValue() + "n");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Incapaz de matar " + nombreProceso + "." + e);
        }
    }
}
