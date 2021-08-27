package principal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
public class Buscamina {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Marco_Tablero mimarco = new Marco_Tablero();
	        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        mimarco.setVisible(true);
	}

}
//----------------------------------CLASES-------------------------------------------------------------
class Marco_Tablero extends JFrame  {
	public Marco_Tablero(){
		setBounds(380,25,605,670);
		setTitle("Buscaminas");
		setResizable (false);
		setIconImage(new ImageIcon(getClass().getResource("/img/icono.png")).getImage());
		Lamina miLamina = new Lamina();
		add(miLamina);	
}
	
	
class Lamina extends JPanel implements ActionListener, Runnable{
	public Lamina(){	
		
			UIManager.put("OptionPane.background",Color.BLACK);
			UIManager.put("OptionPane.Foreground", Color.red);
			UIManager.put("Panel.background",Color.white);
			UIManager.put("Button.background",Color.white);
		
		setLayout (new BorderLayout());
		sur=new JPanel(); 
		sur.setLayout(new FlowLayout());
		sur.setBackground(Color.black);
		boton=new JButton();
		for(int i=0;i<7;i++){
          ico_icono[i] = new ImageIcon(getClass().getResource(icono[i]));
      }
		 boton.setIcon(ico_icono[2]);
		 boton.setBackground(new Color (96,96,96));
		 boton.addActionListener(this);
		 sur.add(boton);
		
		 
	norte=new JPanel();
	norte.setLayout(new FlowLayout());
	norte.setBackground(Color.black);
		tiempo = new JLabel("00:00" );
	    tiempo.setFont( new Font( Font.SERIF, Font.BOLD, 25) );
	    tiempo.setHorizontalAlignment( JLabel.CENTER );
	    tiempo.setForeground(new Color (250,250,250));
	    tiempo.setOpaque( false );
	    iniciar_cronometro();
	    reloj=new JLabel();
		reloj.setIcon(ico_icono[3]);
		   	puntos=new JLabel();
		   	puntos.setIcon(ico_icono[4]);
			 etiqueta = new JLabel();
			 etiqueta.setText(String.valueOf(contador));
			 etiqueta.setFont( new Font( Font.SERIF, Font.BOLD, 25) );
		     etiqueta.setHorizontalAlignment( JLabel.CENTER );
		     etiqueta.setForeground(new Color (250,250,250));
		     etiqueta.setOpaque(false);
			 
		     bandera=new JLabel();
			 bandera.setIcon(ico_icono[6]);
		     etiqueta1 = new JLabel();
		     etiqueta1.setText(String.valueOf(banderita));
		     etiqueta1.setFont( new Font( Font.SERIF, Font.BOLD, 25) );
		     etiqueta1.setHorizontalAlignment( JLabel.CENTER );
		     etiqueta1.setForeground(new Color (250,250,250));
		     etiqueta1.setOpaque(false);
		        
		     mouse=new JLabel();
			 mouse.setIcon(ico_icono[5]);
			 mousejl = new JLabel();
			 mousejl.setText(String.valueOf(clic));
			 mousejl.setForeground(new Color (250,250,250));
			 mousejl.setFont( new Font( Font.SERIF, Font.BOLD, 25) );
			 mousejl.setHorizontalAlignment( JLabel.CENTER ); 
	    
			 vacio = new JLabel();
			 vacio.setText("                             ");
			 vacio.setForeground(new Color (250,250,250));
			 vacio.setFont( new Font( Font.SERIF, Font.BOLD, 25) );
			 vacio.setHorizontalAlignment( JLabel.CENTER );  
		     
			norte.add(puntos);
			norte.add(etiqueta);
			
			norte.add(bandera);
			norte.add(etiqueta1);
		
	        norte.add(mouse);	
	        norte.add(mousejl);
	        norte.add(vacio);
	        norte.add(reloj);	
	        norte.add(tiempo);
	       
	    tabla=new JPanel();
		tabla.setLayout(new FlowLayout());
		tabla.setBackground(Color.BLACK);
		juego_nuevo();
		
		add(norte,BorderLayout.NORTH);
		add(sur,BorderLayout.SOUTH);
		add(tabla,BorderLayout.CENTER);		

}	
//-----------------------------------METODOS ------------------------------------------------------------
	public void cargar_cuadros(){
	for(int i=0;i<12;i++){
	            ico_num[i] = new ImageIcon(getClass().getResource(numeros[i]));
	        }  
     for( int i = 0 ; i < FILAS; i++ ){
        for( int j = 0 ; j < COLUMNAS; j++ ){
              Botones[i][j] = new JButton();
             Botones[i][j].setBackground(new Color(96,96,96));
              Botones[i][j].setPreferredSize(new Dimension(55,55));   
              tabla.add(Botones[i][j]);
        }
 }         	
}		

	public void cargar_minas(){
      for(int i=0;i<FILAS;i++){
      for(int j=0;j<COLUMNAS;j++){
          posicion[i][j]=0;
          
      }
 }
         
      int fil, col;   
      for ( int i=0;i<10;i++){
          do{
              fil=(int)Math.floor(Math.random() * FILAS);
              col=(int) Math.floor(Math.random() * COLUMNAS);
                           
          }while(posicion[fil][col]!=0);
          
          posicion[fil][col]=9;
          System.out.print("mina "+i+" pos "+fil+ " ");
          System.out.print(col+ " ");
          System.out.println();
      }
  }	

	public void pista_mina(){
      for(int i=0;i<FILAS;i++){
          for(int j=0;j<COLUMNAS;j++){
              if(posicion[i][j]==9){
                  for(int i2=i-1;i2<=i+1;i2++){
                      for(int j2=j-1;j2<=j+1;j2++){
                          if(i2>=0 && i2<FILAS && j2>=0 && j2<COLUMNAS && posicion[i2][j2]!=9)
                              posicion[i2][j2]++;
                      }
                    }
                 }
              }
          }
      }
	
	public void eventos_boton(){
      for(int i=0;i<FILAS;i++){
      for(int j=0;j<COLUMNAS;j++){
      	 this.Botones[i][j].addMouseListener(mouseListener); 
          this.Botones[i][j].addActionListener(
          new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  for(int i=0;i<FILAS;i++){
                  for(int j=0;j<COLUMNAS;j++){
                      if(e.getSource()==Botones[i][j] && Botones[i][j].getIcon() == null)
                          pulsar_boton(i,j);
                      
                  			}
                  	}
              	}
              }
          		);
      	}
      }
  }
	
	public void pulsar_boton(int i, int j) {                       
		 resultados(i,j);
		 ver_cuadros();
 }
		
	public void ver_cuadros(){
     for(int i=0;i<FILAS;i++){
     for(int j=0;j<COLUMNAS;j++){
      if(visible_cuadro[i][j]==false){
      	
      }else if(visible_cuadro[i][j]==true && Botones[i][j].getIcon() == null){
        if(posicion[i][j]==0){
     	   Botones[i][j].setIcon(ico_num[0]);
     	
        }else 
     	   if(posicion[i][j]==1){
        Botones[i][j].setIcon(ico_num[1]);
        }else 
     	   if(posicion[i][j]==2){
        Botones[i][j].setIcon(ico_num[2]);
        }else 
     	   if(posicion[i][j]==3){
        Botones[i][j].setIcon(ico_num[3]);
        }else 
     	   if(posicion[i][j]==4){
        Botones[i][j].setIcon(ico_num[4]);
        }else 
     	   if(posicion[i][j]==5){
        Botones[i][j].setIcon(ico_num[5]);
        }else 
     	   if(posicion[i][j]==6){
        Botones[i][j].setIcon(ico_num[6]);
        }else 
     	   if(posicion[i][j]==7){
        Botones[i][j].setIcon(ico_num[7]);
        }else 
     	   if(posicion[i][j]==8){
        Botones[i][j].setIcon(ico_num[8]);
        }else 
     	   if(posicion[i][j]==9) {  
     	Botones[i][j].setIcon(ico_num[9]); 
     	
     	}	   
     		
     	}
      }		  
   }       			  
}

	public void ver_minas(boolean var){
      for(int i=0;i<FILAS;i++){
      for(int j=0;j<COLUMNAS;j++){
          visible_cuadro[i][j]=var;
         
      }
     }
	}
	
	public void resultados(int i, int j){
		
		if(i>=0 && i<FILAS && j>=0 && j<COLUMNAS && visible_cuadro[i][j]==false){
          if(visible_cuadro[i][j]==false){
          	visible_cuadro[i][j]=true;
              if(posicion[i][j]==9){
              parar_cronometro();	
              mousejl.setText(String.valueOf(clic+1));
              ver_minas(true);
              bloquear=true;
              pintar_cuadro();
               Toolkit.getDefaultToolkit().beep();
               boton.setIcon(ico_icono[1]); 
               Botones[i][j].setIcon(ico_num[11]);   
               JOptionPane.showMessageDialog(null,"Puntos: "+contador+nl+"Tiempo: "+minutos+" : "+segundos+nl+"Pulsaciones: "+(clic+1),"PERDISTE", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Proyectos_eclipse\\minesweeper-java\\src\\img\\icono2.png"));              
             
              }
          else if(visible_cuadro[i][j]==true){
              contador++;
              if (contador == 71){
              	 parar_cronometro();
              	 ver_minas(true);
              	 bloquear=true;
                   pintar_cuadro();
                   boton.setIcon(ico_icono[0]);
                   etiqueta.setText(String.valueOf(contador));
                   mousejl.setText(String.valueOf(clic+1));
                   Toolkit.getDefaultToolkit().beep();
                   JOptionPane.showMessageDialog(null,"Puntos: "+contador+nl+"Tiempo: "+minutos+" : "+segundos+nl+"Pulsaciones: "+(clic+1),"GANASTE",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Proyectos_eclipse\\minesweeper-java\\src\\img\\icono2.png"));              
               
              }
              etiqueta.setText(String.valueOf(contador));
             
          }
          }
          if(posicion[i][j]==0){
              resultados(i, j-1);
              resultados(i, j+1);
              resultados(i-1, j);
              resultados(i+1, j);
          }
      }
  }
		
	public void juego_nuevo(){      
		cargar_cuadros();	
      contador = 0;
      iniciar_cronometro();   
      ver_minas(false);
      cargar_minas();
      pista_mina();
      ver_cuadros();
      eventos_boton();
    
	}
	

	 MouseListener mouseListener= new MouseListener() {

		public void mouseClicked(MouseEvent e) {
	terminar_bandera();
	jugar_clic_derecho(e);
		
		if (bloquear==false) {
			clic++;
			mousejl.setText(String.valueOf(clic));
		}
		
			
		}
		public void mouseEntered(MouseEvent e) {
			 for(int i=0;i<FILAS;i++){
			        for(int j=0;j<COLUMNAS;j++){
			        	if (bloquear==false) {
			        	if (e.getSource() == Botones[i][j]){
			        		  Botones[i][j].setBackground(new Color(30,156,45));
			        		   
			        		  }	
			        	
			        	}
			        }
			 }
		}
		public void mouseExited(MouseEvent e) {
			 for(int i=0;i<FILAS;i++){
			        for(int j=0;j<COLUMNAS;j++){
			        	
			        	if (e.getSource() == Botones[i][j]){
			        		   Botones[i][j].setBackground(new Color(96,96,96));
			        		  }	
			        	
			        	
			        }
			 }
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		
	 };
	 

	public void pintar_cuadro() {
		
		 for(int i=0;i<FILAS;i++){
		        for(int j=0;j<COLUMNAS;j++){
		        
		        	if(Botones[i][j].getIcon() ==ico_num[10]) {
		        	
		        		  if(posicion[i][j]==9) {  
		        		       	Botones[i][j].setBackground(Color.blue); 
		        		       	}	  
		        		
		        	}
		        	
		        }
		 }
		
	}
	
	public void jugar_clic_derecho(MouseEvent e) {
		 
		if (e.getButton()==3){
			 for(int i=0;i<FILAS;i++){
			        for(int j=0;j<COLUMNAS;j++){
			        	
			        	if(Botones[i][j] == e.getSource()){  		
			        			if(Botones[i][j].getIcon() == null ) {
			        				
			        					if(banderita>0) {
			        					Botones[i][j].setIcon(ico_num[10]); 
			        					banderita--;
			        					valor_bandera++;
			        						etiqueta1.setText(String.valueOf(banderita+" "));
			        			
			        				  				if(posicion[i][j]==9) { 
					        		                 bandera_fin++;
					      
			        				  				}	  
			        					}
			        				
			        	}else {
			        			if(Botones[i][j].getIcon() ==ico_num[10]  ) {
			    		
			        					if(bloquear!=true) {
			        					Botones[i][j].setIcon(null); 
			        					banderita++;
			        					valor_bandera--;
			        					etiqueta1.setText(String.valueOf(banderita+" "));
			    			       
			        						if(posicion[i][j]==9) {  
			    			    	
			        						bandera_fin--;
			    			    
				        		       	}	  
			    			
			    		   }
			    		
			    	   }
			    	   
			       }
			        			
			        }
			   }			
			
		}
		}
	}  

	public void terminar_bandera() {
		  if(valor_bandera==9) {
   	   parar_cronometro();
   	  ver_minas(true);
     	   bloquear=true;
          pintar_cuadro();
          mousejl.setText(String.valueOf(clic+1));
          etiqueta.setText(String.valueOf(contador));
          Toolkit.getDefaultToolkit().beep();
       
          if(bandera_fin==9) {
          	  boton.setIcon(ico_icono[0]);    
          	JOptionPane.showMessageDialog(null,"Puntos: "+contador+nl+"Tiempo: "+minutos+" : "+segundos+nl+"Pulsaciones: "+(clic),"GANASTE",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Users\\Fernando\\Documents\\Java_Practicas\\Final_Buscamina\\src\\icono2.png"));                              
          }else {
        JOptionPane.showMessageDialog(null,"Puntos: "+contador+nl+"Tiempo: "+minutos+" : "+segundos+nl+"Pulsaciones: "+(clic+1),"Perdiste",JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\Fernando\\Documents\\Java_Practicas\\Final_Buscamina\\src\\icono2.png"));              
        boton.setIcon(ico_icono[1]); 
          }
      }
	}

	 
	public void iniciar_cronometro() {
	    cronometroActivo = true;
	    hilo = new Thread( this );
	    hilo.start();
	}
	
	public void run(){
	    
	    try
	    {
 
	            while( cronometroActivo ){
		            Thread.sleep(4);
		            milesimas += 4;
		            if( milesimas == 1000 ){
		                milesimas = 0;
		                segundos += 1;
		            if( segundos == 60 ){
		                 segundos = 0;
		                 minutos++;
		                }
		            }
	          
	            if( minutos < 10 ) min = "0" + minutos;
	            else min = minutos.toString();
	            if( segundos < 10 ) seg = "0" + segundos;
	            else seg = segundos.toString();
	         
	            tiempo.setText(String.valueOf(min+":"+seg));   
	          
	        }
	    }catch(Exception e){}
	}
	
	public void parar_cronometro(){

     cronometroActivo = false;
 }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==boton){
			boton.setIcon(ico_icono[2]);
			dispose();

		Buscamina.main(null);
			
		}	
	}

	
	Integer minutos = 0 , segundos = 0, milesimas = 0;
  String min="", seg="", mil=""; 
	JLabel tiempo,reloj,puntos,etiqueta,etiqueta1,etiqueta2,mouse,mousejl,vacio,bandera;
	Thread hilo;
	boolean cronometroActivo;
	JPanel sur,tabla,norte;
  int FILAS=9,COLUMNAS=9,contador=0,clic,banderita=10,bandera_fin=0,valor_bandera;
		JButton Botones[][] = new JButton[FILAS][COLUMNAS],boton;
		int posicion[][]=  new int[FILAS][COLUMNAS];
		boolean visible_cuadro[][] = new boolean[FILAS][COLUMNAS];
		String nl=System.getProperty("line.separator");
		String[] icono = {"/img/gano.png", "/img/perdio.png", "/img/nuevo.png","/img/reloj.png","/img/puntos.png","/img/mouse.png","/img/flag.png"};
		ImageIcon[] ico_icono = new ImageIcon[8];
		String numeros[] = {"/img/0.PNG", "/img/1.PNG", "/img/2.PNG", "/img/3.PNG", "/img/4.PNG", "/img/5.PNG", "/img/6.PNG", "/img/7.PNG", "/img/8.PNG", "/img/9.gif", "/img/bandera.png", "/img/rojo.gif"};
		ImageIcon[] ico_num = new ImageIcon[12];
		boolean bloquear=false;
		boolean var;
		}
}
