import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class IMAGES
{
	public static int loaded=0;
	public static int nImages=32;
	
	public static Image imgMenu;
	
	public static Image imgSnuevo;
	public static Image imgScontinuar;
	public static Image imgSayuda;
	public static Image imgSAcercaDe;
	public static Image imgSsalir;
	
	public static Image imgNuevo;
	public static Image imgContinuar;
	public static Image imgAyuda;
	public static Image imgAcercaDe;
	public static Image imgSalir;
	public static Image imgAbout;
	public static Image imgRegresar;
	
	public static Image imgTAyuda;
	public static Image imgAyudaTitle;
	
	public static Image imgMultFacil;
	public static Image imgMenuTitle;
	public static Image imgAcercaDeTitle;
	public static Image num0;
	public static Image num1;
	public static Image num2;
	public static Image num3;
	public static Image num4;
	public static Image num5;
	public static Image num6;
	public static Image num7;
	public static Image num8;
	public static Image num9;
	
	public static Image op_por ;
	
	public static Image VLine;
	public static Image HLine ;
	
	public static Image vacio;
	
  InputStream is=null;
  BufferedInputStream bis=null;
  byte[] inBuffer=null;
	
  IMAGES()
  {
    try{
    System.gc();
    is = getClass().getResourceAsStream("data");
    bis = new BufferedInputStream(is);
    inBuffer = new byte[indice.VLine_End];
    bis.read(inBuffer,0,indice.VLine_End);
    imgMultFacil=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.multiplicacion_facil_Start,indice.multiplicacion_facil_End)).getImage();
    bis.close();
    is.close();
    System.gc();
    }catch(Exception e){}
    //imgMultFacil=new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/multiplicacion_facil.png"))).getImage();
  }
  
	void load()
	{
		switch(IMAGES.loaded)
		{
			case 0:
			//imgMenu = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/menu.png"));
				imgMenu=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.menu_Start,indice.menu_End)).getImage();
				//imgMenu=new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/menu.png"))).getImage();
				
				break;
			case 1:
			  imgSnuevo=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.Snuevo_Start,indice.Snuevo_End)).getImage();
				//imgSnuevo = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/Snuevo.png"))).getImage();
				break;
			case 2:
			  imgScontinuar=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.Scontinuar_Start,indice.Scontinuar_End)).getImage();
				//imgScontinuar = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/Scontinuar.png"))).getImage();
				break;
			case 3:
			  imgSayuda=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.Sayuda_Start,indice.Sayuda_End)).getImage();
				//imgSayuda = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/Sayuda.png"))).getImage();
				break;
			case 4:
			  imgSsalir=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.Ssalir_Start,indice.Ssalir_End)).getImage();
				//imgSsalir = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/Ssalir.png"))).getImage();
				break;
			case 5:
			  imgNuevo=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.nuevo_Start,indice.nuevo_End)).getImage();
				//imgNuevo = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/nuevo.png"))).getImage();
				break;
			case 6:
			  imgContinuar=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.continuar_Start,indice.continuar_End)).getImage();
				//imgContinuar = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/continuar.png"))).getImage();
				break;
			case 7:
			  imgAyuda=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.ayuda_Start,indice.ayuda_End)).getImage();
				//imgAyuda = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/ayuda.png"))).getImage();
				break;
			case 8:
			  imgSalir=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.salir_Start,indice.salir_End)).getImage();
				//imgSalir = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/salir.png"))).getImage();
				break;
			case 9:
				//imgMultFacil = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/multiplicacion_facil.png"));
				break;
			case 10:
			  imgMenuTitle=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.menuTitle_Start,indice.menuTitle_End)).getImage();
				//imgMenuTitle = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/menuTitle.png"))).getImage();
				break;
			case 11:
			  num0=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n0_Start,indice.n0_End)).getImage();
				//num0 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/0.png"))).getImage();
				break;
			case 12:
			  num1=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n1_Start,indice.n1_End)).getImage();
				//num1 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/1.png"))).getImage();
				break;
			case 13:
			  num2=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n2_Start,indice.n2_End)).getImage();
				//num2 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/2.png"))).getImage();
				break;
			case 14:
			  num3=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n3_Start,indice.n3_End)).getImage();
				//num3 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/3.png"))).getImage();
				break;
			case 15:
			  num4=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n4_Start,indice.n4_End)).getImage();
				//num4 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/4.png"))).getImage();
				break;
			case 16:
			  num5=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n5_Start,indice.n5_End)).getImage();
				//num5 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/5.png"))).getImage();
				break;
			case 17:
			  num6=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n6_Start,indice.n6_End)).getImage();
				//num6 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/6.png"))).getImage();
				break;
			case 18:
			  num7=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n7_Start,indice.n7_End)).getImage();
				//num7 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/7.png"))).getImage();
				break;
			case 19:
			  num8=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n8_Start,indice.n8_End)).getImage();
				//num8 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/8.png"))).getImage();
				break;
			case 20:
			  num9=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.n9_Start,indice.n9_End)).getImage();
				//num9 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/9.png"))).getImage();
				break;
			case 21:
			  op_por=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.op_por_Start,indice.op_por_End)).getImage();
				//op_por = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/op_por.png"))).getImage();
				break;
			case 22:
			  VLine=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.VLine_Start,indice.VLine_End)).getImage();
				//VLine = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/VLine.png"))).getImage();
				break;
			case 23:
			  HLine=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.HLine_Start,indice.HLine_End)).getImage();
				//HLine = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/HLine.png"))).getImage();
				break;
			case 24:
			  vacio=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.vacio_Start,indice.vacio_End)).getImage();
				//vacio = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/vacio.png"))).getImage();
				break;
			case 25:
			  imgAcercaDe=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.acercaDe_Start,indice.acercaDe_End)).getImage();
				//imgAcercaDe = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/acercaDe.png"))).getImage();
				break;
			case 26:
			  imgSAcercaDe=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.SacercaDe_Start,indice.SacercaDe_End)).getImage();
				//imgSAcercaDe = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/SacercaDe.png"))).getImage();
				break;
			case 27:
			  imgAcercaDeTitle=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.AcercaDeTitle_Start,indice.AcercaDeTitle_End)).getImage();
				//imgAcercaDeTitle = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/AcercaDeTitle.png"))).getImage();
				break;
			case 28:
			  imgAbout=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.About_Start,indice.About_End)).getImage();
				//imgAbout = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/About.png"))).getImage();
				break;
			case 29:
			  imgRegresar=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.Regresar_Start,indice.Regresar_End)).getImage();
				//imgRegresar = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/Regresar.png"))).getImage();
				break;
			case 30:
			  imgTAyuda=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.tAyuda_Start,indice.tAyuda_End)).getImage();
				//imgTAyuda = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/tAyuda.png"))).getImage();
				break;
			case 31:
			  imgAyudaTitle=new ImageIcon(Toolkit.getDefaultToolkit().createImage(inBuffer,indice.AyudaTitle_Start,indice.AyudaTitle_End)).getImage();
			  inBuffer=null;
			  System.gc();
				//imgAyudaTitle = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/AyudaTitle.png"))).getImage();
				break;
		}
		IMAGES.loaded++;
	}
}
