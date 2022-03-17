package com.floresbarco.controller;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

import java.awt.*;
import java.io.*;
import java.util.Map;

public class DocumentController {
    private static DocumentController instance;

    public DocumentController() {
        instance = null;
    }

    // SINGLETON
    public static synchronized DocumentController getInstance() {
        if(instance == null) {
            instance = new DocumentController();
        }
        return instance;
    }

    public Map documentReding(String address) {
        Map map = null;
        try {
            String temp = "";
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(address));
            String line = "";
            while((line = br.readLine()) != null) {
                temp += (line + "\n");
            }
            map = gson.fromJson(temp, Map.class);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void documentWriting(String address, String content) {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(address);
            pw = new PrintWriter(fw);
            pw.write(content);
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public void openDocument(String address) {
        try
        {
            File file = new File(address);
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("ERROR AL ABRIR EL ARCHIVO.");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void execGraphviz(String address, String name, String type) {
        try {
            ProcessBuilder pb;
            pb = new ProcessBuilder("dot", "-T" + type, "-o",  name + "." + type, address);
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
