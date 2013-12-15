import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class canvas extends Canvas implements Runnable, KeyListener{
	public static final long serialVersionUID = 2L;
	
	Image bBuffer=null;
	Image bHoja=null;
  Graphics gBuffer=null;
	
	int _state=STATE.LOADING;
	int _in_class_state;
	int _width=800,_height=600;
	int selectedItem=STATE.NUEVO;
	StringBuffer number1 = new StringBuffer("");
	StringBuffer number2 = new StringBuffer("");
	int _y,_x;
	long timer,timerOld;
	boolean showRect;
	int OX;
	int OY;
	int x_, y_;
	boolean moveToVertical;
	int _active;
	boolean _multiplicar;
	boolean firstTime=true;
	int [][] _multiplicados;
	double [] _suma;
	int timesX;
	int timesY;
	int showTimeDelay;
	int times;
	boolean showDelay;
	int _steps;
	boolean showEfect=true;
	int efectX,efectX2;
	int orgX;
	int mostrados;
	IMAGES IMG=null;
	
	canvas()
	{
		_in_class_state=STATE.LOADING;
		timerOld=timer=(new Date()).getTime();
	}
	
	public void run(){
		while(_state!=STATE.SALIR_APP)
		{
			//Thread.yield();
			repaint();
			try{Thread.sleep(10);}catch(Exception e){}
		}
		System.exit(0);
	}
	
	//Thread thread=null;
	void hitEnter()
	{
		switch(_state)
		{
			case STATE.MENU:
			{
  			switch(menuSubState)
  		  {
  		    case STATE.MENU:
  		    {
    				switch(selectedItem)
    				{
    					case STATE.NUEVO:
    						startNew();
    						break;
    					case STATE.CONTINUAR:
    						changeState(STATE.IN_CLASS);
    						break;
    					case STATE.AYUDA:
    						menuSubState=STATE.MENU_AYUDA;
    						break;
    					case STATE.ACERCA_DE:
    						menuSubState=STATE.MENU_ACERCA_DE;
    						break;
    					case STATE.SALIR:
    						changeState(STATE.SALIR_APP);
    						break;
    				}
    			}
    			break;
    			case STATE.MENU_ACERCA_DE:
    			case STATE.MENU_AYUDA:
    			{
            menuSubState=STATE.MENU;
          }
          break;
    		}
  		}
  		break;
			case STATE.IN_CLASS:
				switch(_in_class_state)
				{
					case STATE.CLASS_INPUT1:
						if(number1.length()>0)
							changeState(STATE.CLASS_INPUT2);
						break;
					case STATE.CLASS_INPUT2:
						if(number2.length()>0)
							changeState(STATE.CLASS_FIRST_STEP);
						break;
					case STATE.CLASS_FIRST_STEP:
						if(moveToVertical==false && efectX==25)
						{
							moveToVertical=true;
						}
						else
						{
							if(_multiplicar==false && showEfect==false)
							{
								_multiplicar=true;
							}
							else if(_multiplicar == showDelay == true)
							{
								showDelay=false;
							}
							else if(moveToVertical==true && showEfect==true)
							{
								showEfect=false;
							}
						}
						if(_steps==2)
						{
							_in_class_state++;
						}
						break;
					case STATE.CLASS_SECOND_STEP:
						if(_steps==3)
						{
							_in_class_state++;
							showTimeDelay=40;
							showDelay=true;
						}
						break;
					case STATE.CLASS_THIRD_STEP:
						if(_steps==4)
						{
							_in_class_state++;
						}
						else
						{
							if(showDelay)
								showDelay=false;
						}
						break;
				}
				break;
		}
	}
	
	void changeState(int state)
	{
		switch(state)
		{
			case STATE.MENU_AYUDA:
				_state=STATE.MENU_AYUDA;
				break;
			case STATE.MENU_ACERCA_DE:
				_state=STATE.MENU_ACERCA_DE;
				break;
			case STATE.SALIR_APP:
				_state=STATE.SALIR_APP;
				break;
			case STATE.IN_CLASS:
				_state=STATE.IN_CLASS;
				break;
			case STATE.MENU:
				_state=STATE.MENU;
				break;
			case STATE.CLASS_INPUT1:
				_in_class_state=STATE.CLASS_INPUT1;
				number2.delete(0,number2.length());
				_y=105;
				break;
			case STATE.CLASS_INPUT2:
				_in_class_state=STATE.CLASS_INPUT2;
				_y=125;
				break;
			case STATE.CLASS_FIRST_STEP:
				_in_class_state=STATE.CLASS_FIRST_STEP;
				break;
		}
	}
	
	void moveUp()
	{
		switch(_state)
		{
			case STATE.MENU:
				if(selectedItem>STATE.NUEVO)
				{
					selectedItem--;
					if(selectedItem==STATE.CONTINUAR && firstTime==true)
						selectedItem--;
				}
				else
					selectedItem=STATE.SALIR;
				break;
			case STATE.IN_CLASS:
				switch(_in_class_state)
				{
					case STATE.CLASS_INPUT2:
						changeState(STATE.CLASS_INPUT1);
						break;
				}
		}
	}
	void moveDown()
	{
		switch(_state)
		{
			case STATE.MENU:
				if(selectedItem<STATE.SALIR)
				{
					selectedItem++;
					if(selectedItem==STATE.CONTINUAR && firstTime==true)
						selectedItem++;
				}
				else
					selectedItem=STATE.NUEVO;
				break;
			case STATE.IN_CLASS:
				switch(_in_class_state)
				{
					case STATE.CLASS_INPUT1:
						if(number1.length()>0)
							changeState(STATE.CLASS_INPUT2);
						break;
				}
		}
	}
	
	void remLastNumber()
	{
		switch(_in_class_state)
		{
			case STATE.CLASS_INPUT1:
				if(number1.length()>0)
					number1.deleteCharAt(number1.length() - 1);
				break;
			case STATE.CLASS_INPUT2:
				if(number2.length()>0)
					number2.deleteCharAt(number2.length() - 1);
				break;
		}
	}
	
	void addNumber(int n)
	{
		switch(_in_class_state)
		{
			case STATE.CLASS_INPUT1:
				if(number1.length()==9)
					return;
				break;
			case STATE.CLASS_INPUT2:
				if(number2.length()==number1.length())
					return;
				break;
			default:
				return;
				
		}
		if(_state==STATE.IN_CLASS)
			switch(n)
			{
				case 0:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("0");
					else
						number2.append("0");
					break;
				case 1:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("1");
					else
						number2.append("1");
					break;
				case 2:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("2");
					else
						number2.append("2");
					break;
				case 3:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("3");
					else
						number2.append("3");
					break;
				case 4:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("4");
					else
						number2.append("4");
					break;
				case 5:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("5");
					else
						number2.append("5");
					break;
				case 6:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("6");
					else
						number2.append("6");
					break;
				case 7:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("7");
					else
						number2.append("7");
					break;
				case 8:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("8");
					else
						number2.append("8");
					break;
				case 9:
					if(_in_class_state==STATE.CLASS_INPUT1)
						number1.append("9");
					else
						number2.append("9");
					break;
			}
	}
	Image getImgNum(char car)
	{
		switch(car)
		{
			case '0':
				return IMG.num0;
			case '1':
				return IMG.num1;
			case '2':
				return IMG.num2;
			case '3':
				return IMG.num3;
			case '4':
				return IMG.num4;
			case '5':
				return IMG.num5;
			case '6':
				return IMG.num6;
			case '7':
				return IMG.num7;
			case '8':
				return IMG.num8;
			case '9':
				return IMG.num9;
		}
		return null;
	}
	void drawString(StringBuffer str,Graphics g)
	{
		if(str.length()>0)
		{
			int y=_y-20;
			int x=_x-((str.length())*15);
			
			
			if(number2.equals(str) && y==105-20)
				y+=20;
				
			if(number1.equals(str) && y==125-20)
				y-=20;
			
			for(int i=0;i<str.length();i++)
			{
				char car = str.charAt(i);
				g.drawImage(getImgNum(car),x,y,null);
				x+=15;
			}
		}
	}
	void drawStrings(Graphics g)
	{
		OX=_x-((number1.length())*15);
		OY=_y-40;
		int mas=15;
		int x=OX;
		int y=OY;
		
		for(int i=0;i<number1.length();i++)
		{
			if(i!=0)
				x+=efectX;
			if(efectX<25)
				efectX++;
						
			char car=number1.charAt(i);
			g.drawImage(getImgNum(car),x,y,null);
			if((number1.length()-i) <= number2.length() && !moveToVertical)
			{
				int corX=number2.length()-(number1.length()-i);
				car=number2.charAt(corX);
				g.drawImage(getImgNum(car),x,y+20,null);
				if(corX==0 && x_==-1 && efectX==25)
				{
					x_=x;
					orgX=x_;
					y_=y+20;
				}
			}
			x+=mas;
		}
	}
	//Image imgCuadro=null;
	void drawRect(int x, int y, int w, int h, Graphics g)
	{
	  // if(imgCuadro==null)
	   {
	    // imgCuadro=createImage(w,h);
	    // Graphics gCuadro=imgCuadro.getGraphics();
	     //gCuadro.setColor(new Color(,0,0,10)); // 50% transparent BLACK
  		 g.drawImage(IMG.VLine,x,y,null);
  		 g.drawImage(IMG.VLine,x+w,y,null);
  		 g.drawImage(IMG.HLine,x,y,null);
  		 g.drawImage(IMG.HLine,x,y+h,null);
  	 }
  	// g.drawImage(imgCuadro,x,y,null);
	}
	void startNew()
	{
		orgX=0;
		_multiplicados=null;
		_suma=null;
		selectedItem=STATE.CONTINUAR;
		_in_class_state=STATE.CLASS_INPUT1;
		number1.delete(0,number1.length());
		number2.delete(0,number2.length());
		_y=105;
		_x=230;
		x_=-1;
		y_=0;
		moveToVertical=false;
		_active=0;
		showRect=true;
		_multiplicar=false;
		firstTime=false;
		timesX=0;
		timesY=-1;
		showTimeDelay=40;
		times=showTimeDelay;
		showDelay=true;
		_steps=1;
		efectX=0;
		efectX2=0;
		showEfect=true;
		mostrados=0;
		steP=0;
		casiTotal="";
		menos=0;
		actualY=0;
		actualX=0;
		actualYstep=0;
		actualXstep=0;
		lastTime=0;
		resultado="";
		pasoResultado=0;
		
    _state=STATE.IN_CLASS;
    
	}
	void hitEsc()
	{
		if(_state!=STATE.LOADING)
		{
			if(_state==STATE.MENU && menuSubState==STATE.MENU && firstTime==false)
				changeState(STATE.IN_CLASS);
			else
			{
			  if(_state!=STATE.MENU)
          _state=STATE.MENU;
        menuSubState=STATE.MENU;
      }
				
		}
	}
	int chava=0;
	void updateFirst(Graphics g)
	{
		int mas=40;
		int x=OX;
		int y=OY;
		
		if((_active==number2.length() || !showEfect) && !_multiplicar)
			_multiplicar=true;
		
		drawStrings(g);
		
		if(moveToVertical)
		{
			int originalY=OY+20;
			int originalX=OX-35;
			y+=40;
			
			x=orgX;
			
			for(int i=0;i<number2.length();i++)
			{
				char car=number2.charAt(i);
				
				if(i<_active || showEfect==false)
				{
					g.drawImage(getImgNum(car),originalX,y,null);
					y+=mas;
				}
				else if(_active==i)
				{
					g.drawImage(getImgNum(car),x_,y_,null);
					x=orgX+(i*mas);
				}
				else if(i>_active)
				{
					g.drawImage(getImgNum(car),x,originalY,null);
				}
				x+=mas;
			}
			if(_state==STATE.IN_CLASS)
			{
				if(y_==((originalY+20)+(_active*mas)) && x_==originalX && _active<number2.length())
				{
					_active++;
					x_=orgX+(_active*mas);
					y_=originalY;
				}
				else
				{
					if(x_>originalX)
						x_--;
					
					if(y_<((originalY+20)+(_active*mas)) )
						y_++;
				}
			}
		}
		if(_multiplicar)
		{
			x=OX;
			y=OY+40;
			
			if(_multiplicados==null)
			{
				_multiplicados= new int[number2.length()][number1.length()];
				for(int i=0;i<number1.length();i++)
				{
					for(int j=0;j<number2.length();j++)
					{
						_multiplicados[j][i]= Integer.parseInt(number1.charAt(i)+"")*Integer.parseInt(number2.charAt(j)+"");
					}
				}
			}
			for(int i=0;i<number1.length();i++)
			{
				for(int j=0;j<number2.length();j++)
				{
					boolean print=false;
					drawRect(x-12,y-10,40,40,g);
					if(timesX>=i && !(timesX==i && timesY<j))
					{
						print=true;
					}
					
					if(!showDelay)
					{
						print=true;
					}
					
					if(print)
					{
						if(_multiplicados[j][i]>9)
						{
							int n=_multiplicados[j][i]/10;
							g.drawImage(getImgNum(((char)(n+((int)'0')))),x-8,y,null);
							n=_multiplicados[j][i]-(n*10);
							g.drawImage(getImgNum(((char)(n+((int)'0')))),x+10,y,null);
						}
						else
							g.drawImage(getImgNum(((char)(_multiplicados[j][i]+((int)'0')))),x,y,null);
					}
					y+=mas;
				}
				y=OY+40;
				x+=mas;
			}
			if(_steps==1 && (timesX==_multiplicados[0].length || showDelay==false))
			{
				_steps++;
			}
		}
	}
	void updateSecond(Graphics g)
	{
		int mas=40;
		int x=OX;
		int y=OY+40;
		
		if(_state==STATE.IN_CLASS)
			if(efectX2<40)
				efectX2++;
		
		for(int i=0;i<number1.length();i++)
		{
			for(int j=0;j<number2.length();j++)
			{
				if(j!=0)
					x+=efectX2;
					
				if(efectX2==40 && i==0)
				{
					int tmp=x;
					x-=(j*40);
					while(x<tmp)
					{
						g.drawImage(IMG.vacio,x-12,y-10,null);
						drawRect(x-12,y-10,40,40,g);
						x+=40;
					}
				}
				
				drawRect(x-12,y-10,40,40,g);

				if(_multiplicados[j][i]>9)
				{
					int n=_multiplicados[j][i]/10;
					g.drawImage(getImgNum(((char)(n+((int)'0')))),x-8,y,null);
					n=_multiplicados[j][i]-(n*10);
					g.drawImage(getImgNum(((char)(n+((int)'0')))),x+10,y,null);
				}
				else
					g.drawImage(getImgNum(((char)(_multiplicados[j][i]+((int)'0')))),x,y,null);
				y+=mas;
			}
			y=OY+40;
			x=OX+((i+1)*40);
		}
		if(efectX2==40 && _steps==2)
			_steps++;
	}
	void updateThird(Graphics g)
	{
		int x=OX;
		int y=OY+(number2.length()*40)+40;
		g.setColor(Color.BLACK);
		g.fillRect(x-30,y,(((number1.length()*40)+(number2.length()*40))),2);
		
		y+=20;
		int n=number1.length()+number2.length()-1;
		if(_suma==null)
		{
			_suma = new double[n];
			for(int i=0; i<n; i++)
				_suma[i]=0;
				
			int tmpN=0;
			int i=0;
			int j=0;
			int intermedios=(_multiplicados[0].length-_multiplicados.length)-1;
			int inversos=1;
			boolean first=true;
			boolean secondFirst=false;
			
			while(tmpN<_suma.length)
			{
				if(i<_multiplicados.length && j<_multiplicados[0].length)
					_suma[tmpN]+=_multiplicados[i][j];
				
				i++;
				j--;
				
				if(tmpN<_multiplicados.length)
				{
					if(j<0)
					{
						tmpN++;
						if(tmpN<_multiplicados.length)
						{
							i=0;
							j=tmpN;
						}
					}
				}
				else if(intermedios>=0)
				{
					if(i>_multiplicados.length)
					{
						if(!first)
						{
							tmpN++;
							intermedios--;
						}
						if(intermedios>=0)
						{
							i=0;
							j=tmpN;
						}
					}
					first=false;
					secondFirst=true;
				}
				else
				{
					if(i>(_multiplicados.length-1))
					{
						i=inversos;
						j=_multiplicados[0].length-1;
						
						if(!first && !secondFirst)
						{
							tmpN++;
						}
						inversos++;
						secondFirst=false;
						first=false;
					}
				}
			}
		}
		if(!showDelay && mostrados<_suma.length)
			mostrados=_suma.length;
			
		for(int i=0; i<n;i++)
		{
			boolean paint=false;
			
			drawRect(x-12,y-10,40,40,g);
			
			if(i<mostrados)
				paint=true;

			if(paint)
			{
				if(_suma[i]>99)
				{
					int nn=((int)_suma[i])/100;
					g.drawImage(getImgNum(((char)(nn+((int)'0')))),x-10,y,null);
					nn=((((int)_suma[i])/10)-(nn*10));
					g.drawImage(getImgNum(((char)(nn+((int)'0')))),x+2,y,null);
					nn=((int)_suma[i])-((nn*10)+(((int)(_suma[i])/100)*100));
					g.drawImage(getImgNum(((char)(nn+((int)'0')))),x+15,y,null);
				}
				else if(_suma[i]>9)
				{
					int nn=((int)_suma[i])/10;
					g.drawImage(getImgNum(((char)(nn+((int)'0')))),x-5,y,null);
					nn=((int)_suma[i])-(nn*10);
					g.drawImage(getImgNum(((char)(nn+((int)'0')))),x+11,y,null);
				}
				else
					g.drawImage(getImgNum(((char)(((int)_suma[i])+((int)'0')))),x,y,null);
			}
			x+=40;
		}
		if(mostrados<_suma.length)
		{
			//if(!isAtMenu())
			{
				if(showTimeDelay==0)
					mostrados++;
				if(showTimeDelay<0)
					showTimeDelay=40;
				showTimeDelay--;
			}
		}
		else if(_steps==3)
			_steps=4;
	}
	int steP=0;
	int menos=0;
	String casiTotal="";
	int actualY=0;
	int actualX=0;
	int actualYstep=0;
	int actualXstep=0;
	double lastTime=0;
	double delay=25;
	String resultado="";
	int pasoResultado=0;
	
	Image imgFourthStep=null;
  void updateFourth(Graphics g)
	{
		int x=0;
		switch(steP)
		{
			case 0:
			{
				for(int i=0; i<_suma.length;i++)
				{
					casiTotal=casiTotal+""+Integer.toString((int)_suma[i]);
				}
				actualY=(OY+(number2.length()*40)+40)+20;
				actualX=OX;
				steP++;
			}
			case 1:
			{
				if(actualY>85)
					actualY--;
				if(actualX>55)
					actualX--;
				if(actualY==85 && actualX==55)
					steP++;
				x=actualX;
				for(int i=0; i<casiTotal.length();i++)
				{
					g.drawImage(getImgNum(casiTotal.charAt(i)),x,actualY,null);
					x+=15;
				}
				/*
				for(int i=0; i<_suma.length;i++)
					if(_suma[i]>99)
				 */
			break;
      }
			case 2:
			{
				x=actualX+((casiTotal.length()-1)*15);
				int y=actualY;
				int actualNum=_suma.length-1;
				int digits=Integer.toString((int)_suma[actualNum]).length();
				for(int i=casiTotal.length()-1; i>=0;i--)
				{
					g.drawImage(getImgNum(casiTotal.charAt(i)),x,y,null);
					digits--;
					if(digits==0)
					{
						int upLevelDigits=0;
						if(i!=0)
						{
							actualNum--;
							digits=Integer.toString((int)_suma[actualNum]).length();
							upLevelDigits=Integer.toString((int)_suma[actualNum+1]).length();
							x+=actualXstep*(upLevelDigits==3?2:upLevelDigits==2?1:0);
						}
						else
						{
							x+=actualXstep*(Integer.toString((int)_suma[0]).length()==3?2:Integer.toString((int)_suma[0]).length()==2?1:0);
						}
						if(actualXstep==15)
						{
							int cerosX=x;
							while((cerosX+15)<=(actualX+((casiTotal.length()-1)*15)))
							{
								cerosX+=15;
								g.drawImage(getImgNum('0'),cerosX,y,null);
							}
						}
						y+=actualYstep;
					}
					x-=15;
				}
				if(actualYstep<20)
				{
					if(System.currentTimeMillis()>lastTime+delay)
					{
						lastTime=System.currentTimeMillis();
						actualYstep++;
					}
				}
				else if(actualXstep<15)
				{
					if(System.currentTimeMillis()>lastTime+delay)
					{
						lastTime=System.currentTimeMillis();
						actualXstep++;
					}
				}
				//centrar
				else if(actualX>((_width-(casiTotal.length()*15)-x)/2))
				{
					if(System.currentTimeMillis()>lastTime+delay)
					{
						lastTime=System.currentTimeMillis();
						actualX--;
					}
				}
				else
				{
					if(resultado.equals(""))
					{
						int total[][] = new int[17][18];
						
						int xT=17;
						int yT=0;
						
						actualNum=_suma.length-1;
						digits=Integer.toString((int)_suma[actualNum]).length();
						for(int i=casiTotal.length()-1; i>=0;i--)
						{
							total[yT][xT]+=Integer.parseInt(casiTotal.charAt(i)+"");
							xT--;
							digits--;
							if(digits==0)
							{
								int upLevelDigits=0;
								if(i!=0)
								{
									actualNum--;
									digits=Integer.toString((int)_suma[actualNum]).length();
									upLevelDigits=Integer.toString((int)_suma[actualNum+1]).length();
									xT+=(upLevelDigits==3?2:upLevelDigits==2?1:0);
								}
								else
								{
									xT+=(Integer.toString((int)_suma[0]).length()==3?2:Integer.toString((int)_suma[0]).length()==2?1:0);
								}
								yT++;
							}
						}
						int sumaTotal[]=new int[18];
						for(int i=0; i<sumaTotal.length;i++)
							sumaTotal[i]=0;
						
						int sumaIt=0;
						for(int xTT=0;xTT<total[0].length;xTT++)
						{
							for(int yTT=0;yTT<total.length;yTT++)
								sumaTotal[sumaIt]+=total[yTT][xTT];
							sumaIt++;
						}
//						for(int i=0; i<sumaTotal.length;i++)
	//						System.out.println(sumaTotal[i]);
						
						for(int i=sumaTotal.length-1; i>0;i--)
						{
							if(sumaTotal[i]>9)
							{
								sumaTotal[i-1]+=(sumaTotal[i]/10);
								sumaTotal[i]-=((sumaTotal[i]/10)*10);
							}
						}
						int primerNumBueno=0;
						for(int i=0; i<sumaTotal.length;i++)
							if(sumaTotal[i]>0)
							{
								primerNumBueno=i;
								break;
							}
						for(int i=primerNumBueno; i<sumaTotal.length;i++)
								resultado=resultado+""+Integer.toString(sumaTotal[i]);
					}
					else
					{
						x=actualX+((casiTotal.length()-1)*15);
						g.setColor(Color.BLACK);
						
						g.drawLine(x-(resultado.length()*15),y+10, x+15, y+10);
						g.drawLine(x-(resultado.length()*15),y+11, x+15, y+11);
						g.drawLine(x-(resultado.length()*15),y+12, x+15, y+12);
						
						int yTotal=y+20;
						for(int i=0; i<pasoResultado;i++)
						{
							g.drawImage(getImgNum(resultado.charAt((resultado.length()-1)-i)),x,yTotal,null);
							x-=15;
						}
					}
					if(pasoResultado<resultado.length())
					{
  					if(System.currentTimeMillis()>lastTime+delay+20)
  					{
  						lastTime=System.currentTimeMillis();
  						pasoResultado++;
  					}
  				}
  				else
  				{
  				  imgFourthStep=createImage(_width,_height);
            Graphics gTmp=imgFourthStep.getGraphics();
            gTmp.drawImage(bBuffer,0,0,null);
            _in_class_state=STATE.CLASS_FIFTH_STEP;
          }
					
				}
			break;
			}
		}
	}
	
	long lastShowedTime=0;
	boolean showMessage=true;
	void updateFifth(Graphics g)
	{
    g.drawImage(imgFourthStep,0,0,null);
    
    if(System.currentTimeMillis()>lastShowedTime+300)
    {
      showMessage=!showMessage;
      lastShowedTime=System.currentTimeMillis();
    }
    
    if(showMessage)
    {
      String msg="Presionar ESC";
      gBuffer.setColor(Color.BLACK);
			gBuffer.setFont(new Font("ARIAL", Font.BOLD,  20));
      g.drawString(msg,(_width-(msg.length()*15))>>1,_height-50);
      
    }
    
  }

  void paintHojaBB()
	{
    gBuffer.setColor(Color.WHITE);
  	gBuffer.fillRect(0,0,_width,_height);
  	
  	gBuffer.setColor(new Color(0,200,255));
  		
  	for(int i=66; i<_height; i+=20)
  	{  	
  	 gBuffer.drawLine(0,i,_width,i);
  	 gBuffer.drawLine(0,i+1,_width,i+1);
  	}
  		
  	gBuffer.setColor(Color.RED);
  	gBuffer.drawLine(55,0,55,_height);
  	gBuffer.drawImage(IMG.imgMultFacil,47,22,null);
	}

	void paintHoja()
	{
    gBuffer.drawImage(bHoja,0,0,null);
  }
  
	boolean initialized=false;
  void init()
  {
    bBuffer=createImage(_width,_height);
    bHoja=createImage(_width,_height);
	  gBuffer=bHoja.getGraphics();
		IMG = new IMAGES();
		paintHojaBB();
		gBuffer=bBuffer.getGraphics();
		initialized=true;
  }
  
  void updateLoading()
  {
    if(!initialized)
      init();
    paintHoja();
    
    if(IMG.loaded<IMG.nImages)
		{
			IMG.load();
			//try{Thread.sleep(50);}catch(Exception e){}
			gBuffer.setColor(Color.BLACK);
			gBuffer.setFont(new Font("ARIAL", Font.BOLD,  20));
			int n=(int)(((double)IMG.loaded/(double)IMG.nImages)*100);
			gBuffer.drawString(n +"% Loaded",60,105);
		}
		else
		  _state=STATE.MENU;
  }
  
  Image imgMenu=null;
  int menuSubState=STATE.MENU;
  void updateMenu()
  {
    paintHoja();
    
	  if(imgMenu==null)
	  {
	    imgMenu = createImage(_width,_height);
	    Graphics gMenu=imgMenu.getGraphics();
	    gMenu.drawImage(bBuffer,0,0,null);
			gMenu.setColor(new Color(0,0,0,128)); // 50% transparent BLACK
			gMenu.fillRect(0,0,_width,_height);
			gMenu.drawImage(IMG.imgMenu,250,140,null);
			gMenu.drawImage(IMG.imgMultFacil,47,22,null);
		}
		
		gBuffer.drawImage(imgMenu,0,0,null);
		
		switch(menuSubState)
		{
      case STATE.MENU:
        {
          gBuffer.drawImage(IMG.imgMenuTitle,330,125,null);
          
          int x=250+61;
      		int y=140;
      		
      		y+=63-10;
      		if(selectedItem==STATE.NUEVO)
      			gBuffer.drawImage(IMG.imgSnuevo,x,y,null);
      		else
      			gBuffer.drawImage(IMG.imgNuevo,x,y,null);
      		
      		y+=40-10;
      		if(!firstTime)
      		{
      			y+=40-10;
      			if(selectedItem==STATE.CONTINUAR)
      				gBuffer.drawImage(IMG.imgScontinuar,x,y,null);
      			else
      				gBuffer.drawImage(IMG.imgContinuar,x,y,null);
      		}
      		if(firstTime)
      			y+=40-10;
      		else
      			y+=80-10;
      		if(selectedItem==STATE.AYUDA)
      			gBuffer.drawImage(IMG.imgSayuda,x,y,null);
      		else
      			gBuffer.drawImage(IMG.imgAyuda,x,y,null);
      		y+=80-10;
      		
      		if(selectedItem==STATE.ACERCA_DE)
      			gBuffer.drawImage(IMG.imgSAcercaDe,x,y,null);
      		else
      			gBuffer.drawImage(IMG.imgAcercaDe,x,y,null);
      			
      		y+=80-10;
      		
      		if(selectedItem==STATE.SALIR)
      			gBuffer.drawImage(IMG.imgSsalir,x,y,null);
      		else
      			gBuffer.drawImage(IMG.imgSalir,x,y,null);
        }
        break;
      case STATE.MENU_ACERCA_DE:
        {
          int x=250+61;
      		gBuffer.drawImage(IMG.imgAcercaDeTitle,260,120,null);
      		gBuffer.drawImage(IMG.imgAbout,x-25,199,null);
      		gBuffer.drawImage(IMG.imgRegresar,x,470,null);
        }
        break;
      case STATE.MENU_AYUDA:
        {
          int x=250+61;
      		gBuffer.drawImage(IMG.imgAyudaTitle,250,120,null);
      		gBuffer.drawImage(IMG.imgTAyuda,x-15,207,null);
      		gBuffer.drawImage(IMG.imgRegresar,x,470,null);
        }
        break; 
    }
  }
	
	void updateClass()
	{
	  paintHoja();
	  
	  if(_multiplicados!=null && _multiplicar==true && _steps==1)
			{
				if(timesX<_multiplicados[0].length)
				{
					if(times<0)
					{
						timesY++;
						times=showTimeDelay;
					}
					times--;
				}
				if(timesY==(_multiplicados.length-1))
				{
					timesY=-1;
					timesX++;
				}
			}
	  
		//System.out.println("_steps: "+_steps);
		if(System.currentTimeMillis()>timerOld+400)
		{
			showRect=!showRect;
			timerOld=System.currentTimeMillis();
		}
		
		switch(_in_class_state)
		{
			case STATE.CLASS_INPUT2:
				gBuffer.setColor(Color.BLACK);
				gBuffer.drawImage(IMG.op_por,((_x-((number1.length())*15))-15),105,null);
				gBuffer.fillRect((_x-((number1.length())*15)-15)-3,105+20,(_x-(_x-((number1.length())*15)-15))+18,2);
				//break;
			case STATE.CLASS_INPUT1:
				drawString(number2,gBuffer);
				drawString(number1,gBuffer);
				break;
			case STATE.CLASS_FIRST_STEP:
				updateFirst(gBuffer);
				break;
			case STATE.CLASS_THIRD_STEP:
				updateThird(gBuffer);
			case STATE.CLASS_SECOND_STEP:
				updateSecond(gBuffer);
				break;
			case STATE.CLASS_FOURTH_STEP:
				updateFourth(gBuffer);
				break;
			case STATE.CLASS_FIFTH_STEP:
			 updateFifth(gBuffer);
			 break;
		}
		
		if(showRect && (_in_class_state==STATE.CLASS_INPUT1 || _in_class_state==STATE.CLASS_INPUT2) && _state==STATE.IN_CLASS)
		{
			gBuffer.setColor(Color.RED);
			gBuffer.fillRect(_x,_y,15,2);
		}
	}
	
	public void update(Graphics g) {paint(g);}
	public void paint(Graphics g)
	{
		switch(_state)
		{
      case STATE.LOADING:
        updateLoading();
        break;
      case STATE.MENU:
        updateMenu();
        break;
      case STATE.IN_CLASS:
        updateClass();
    }
    g.drawImage(bBuffer,0,0,null);
	}

    public void keyPressed( KeyEvent key )
    {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            hitEsc();
        }
        else if(code == KeyEvent.VK_LEFT){
        }
        else if(code == KeyEvent.VK_RIGHT){
        }
        else if(code == KeyEvent.VK_UP){
            moveUp();
        }
        else if(code == KeyEvent.VK_DOWN){
            moveDown();
        }
        else if(code == KeyEvent.VK_SPACE){
        }
    		else if(code == KeyEvent.VK_ENTER){
    			hitEnter();
    		}
    		else if(code == KeyEvent.VK_BACK_SPACE)
    			remLastNumber();
    		else if(code>=KeyEvent.VK_0 && code <=KeyEvent.VK_9)
    			addNumber(code-48);
    		else if(code>=KeyEvent.VK_NUMPAD0 && code <=KeyEvent.VK_NUMPAD9)
    			addNumber(code-96);
    }
    
    public void keyReleased( KeyEvent event ){}
    public void keyTyped( KeyEvent event ){}
}
