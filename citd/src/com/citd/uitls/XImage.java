/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.uitls;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/com/citd/Image/UitLogoNoText.png");
        return new ImageIcon(url).getImage();
    }
    
    public static void save(File src){
        File dst = new File("logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from , to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("logos",fileName);
        ImageIcon image = new ImageIcon(path.getPath());
        Image im = image.getImage();               
        //return new ImageIcon(path.getAbsolutePath());
        return new ImageIcon(im.getScaledInstance(180, 200, im.SCALE_SMOOTH));
    }
    public static ImageIcon read2(String fileName,int x,int y){
        File path = new File("logos",fileName);
        ImageIcon image = new ImageIcon(path.getPath());
        Image im = image.getImage();               
        //return new ImageIcon(path.getAbsolutePath());
        return new ImageIcon(im.getScaledInstance(x, y, im.SCALE_SMOOTH));
    }
}
