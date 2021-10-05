package prolab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
    
	public class prolab211<T> {
		
		// PERMUTASYON ALGORITMASI
		
		 public Collection<List<T>> permute(Collection<T> input) {
		        Collection<List<T>> output = new ArrayList<List<T>>();
		        if (input.isEmpty()) {
		            output.add(new ArrayList<T>());
		            return output;
		        }
		        List<T> list = new ArrayList<T>(input);
		        T head = list.get(0);
		        List<T> rest = list.subList(1, list.size());
		        for (List<T> permutations : permute(rest)) {
		            List<List<T>> subLists = new ArrayList<List<T>>();
		            for (int i = 0; i <= permutations.size(); i++) {
		                List<T> subList = new ArrayList<T>();
		                subList.addAll(permutations);
		                subList.add(i, head);
		                subLists.add(subList);
		            }
		            output.addAll(subLists);
		        }
		        return output;
		    }
		 
	 final static int INF = 99999, V = 81; 

	 // 81*81 Matris yazdýrma kontrol kismi
	 
	 static void printSolution(int dist[][]) 
	 { 
	     System.out.println("The following matrix shows the shortest "+ 
	                      "distances between every pair of vertices"); 
	     for (int i=0; i<V; ++i) 
	     { 
	         for (int j=0; j<V; ++j) 
	         { 
	             if (dist[i][j]==INF)
	                 System.out.print("INF "); 
	             else
	                 System.out.print(dist[i][j]+"   "); 
	         } 
	         System.out.println(); 
	     } 
	 } 
	 

		   public static void main (String[] args) throws FileNotFoundException 
		    { 
			   
			 // Komsu olan þehirler arasi uzaklik matrisini alma
			   
			 File file = new File("komsuuzaklik.txt");
			 
			 FileReader fileReader = new FileReader(file);
			 String line;
			 String s;
			 BufferedReader br = new BufferedReader(fileReader);
			 
			 int uzaklik[][]=new int[100][100];
			 int koordinat[][]=new int[81][2];
			 int j=0;
			 int i=0;

			 try {
				while ((line = br.readLine()) != null) {
					 StringTokenizer st=new StringTokenizer(line, ",");
					 while (st.hasMoreElements()) {
						 
							s=(String) st.nextElement();
							
							 if(j==i) {
								uzaklik[i][j]=0;
								//  System.out.println(uzaklik[i][j]+" i:"+i+"  j:"+j);
							}
							else {
								uzaklik[i][j]= Integer.parseInt(s);
								//  System.out.println(uzaklik[i][j]+" i:"+i+"  j:"+j);
							}
							j++;			
						}
					 j=0;
					 i++;

				 }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 

			 // Haritanýn þehir merkezi koordinatlarini alma
			 
			 File file2 = new File("koordinat.txt");
			 i=0;
			 j=0;
			 FileReader fileReader2 = new FileReader(file2);
			 BufferedReader br2 = new BufferedReader(fileReader2);

			 try {
				while ((line = br2.readLine()) != null) {
					 StringTokenizer st=new StringTokenizer(line, ",");
					 while (st.hasMoreElements()) {
						 
							s=(String) st.nextElement();
							
						
								koordinat[i][j]= Integer.parseInt(s);
							
						//	System.out.println(koordinat[i][j]+" i:"+i+"  j:"+j);
							
							j++;			
						}
					 j=0;
					 i++;

				 }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				br2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 	// INF atama yeri
			 
			    int a,b;
			    for(a=0;a<81;a++)
			    {
			        for(b=0;b<81;b++)
			        {

			            if(uzaklik[a][b]==0 && a!=b)
			            {
			                uzaklik[a][b]= INF;
			            }
			            //System.out.println(" "+uzaklik[a][b]+" ");
			        }
			       //System.out.println();
			    }
			    //printSolution(uzaklik);
			    	 
			 // FLOYDWARSHAL
			    
			    int dist[][] = new int[V][V]; 
			     int deneme[][] = new int[V][V];
			     int k; 

			     for (i = 0; i < V; i++) {
			         for (j = 0; j < V; j++) { 
			        	 dist[i][j] = uzaklik[i][j];
			             if(i==j)
			             {
			                 deneme[i][j]=0;
			             }
			             else if(dist[i][j]!=INF)
			             {
			                 deneme[i][j]=i;
			             }
			             else
			             {
			                 deneme[i][j]=-1;
			             }
			             
			         }
			         }
			     for (k = 0; k < V; k++)
			     {
			         for (i = 0; i < V; i++)
			         {
			             for (j = 0; j < V; j++)
			             {
			            	 if (dist[i][k] + dist[k][j] < dist[i][j])
			                 {
			                   	
			                     dist[i][j] = dist[i][k] + dist[k][j];
			                     deneme[i][j]=deneme[k][j];
			                   
			                 }
			             }
			         }
			     }
			  
 
			     	int start=40;
			     
			        Scanner giris = new Scanner(System.in);
			        int secim;
			        System.out.println("Secim1:Gidilecek 3 sehir secin");
			        System.out.println("Secim2:Gidilecek 10 sehir secin");
			        System.out.println("Secim yapiniz (1 - 2) ?");
			        secim=giris.nextInt();

			    	 
			        switch (secim) {
			        case 1 :
			        	
			        	  Scanner scan = new Scanner(System.in);
					        System.out.println("Gitmek istediðiniz 3 þehrin plakasini girin: ");
					        int [] a1 = new int [4];
					        int [][] yol3 = new int[4][4];
					        a1[0]=40;
					        int toplam=99999;
					        for(int x=1;x<=3;x++) {
						        System.out.println(x+". sehir plakasi :");
						        a1[x]=scan.nextInt();
						        System.out.println(a1[x]);
						        }
					        for(i=0;i<4;i++)
					        {
					        	for(j=0;j<4;j++)
					        	{
					        		
					        			yol3[i][j]=dist[a1[i]][a1[j]];
					        			
					        		//System.out.println("Yol:"+yol3[i][j]);
					        		//System.out.println("Yol:"+rota[i][j]);
					        	}
					        }
					       
					        
					        prolab211<Integer> obj = new prolab211<Integer>();
					        Collection<Integer> input = new ArrayList<Integer>();
					        input.add(1);
					        input.add(2);
					        input.add(3);

					        Collection<List<Integer>> output = obj.permute(input);
					        int kf = 0;
					        Set<List<Integer>> pnr = null;
					        for ( i = 0; i < 1; i++) {
					            pnr = new HashSet<List<Integer>>();
					            for(List<Integer> integers : output){
					            pnr.add(integers.subList(i, integers.size()));
					            }
					            kf = input.size()- i;
					            /*System.out.println("P("+input.size()+","+kf+") :"+ 
					            "Count ("+pnr.size()+") :- "+pnr);*/
					        }
					        
					        int [][] rotas=new int [6][3];
					        
					        List<Integer> value = null;
					        System.out.println("Iterating over list:"); 
					        Iterator<List<Integer>> pne = pnr.iterator(); 
					        
					       for(i=0;i<6;i++) {
					        	value=pne.next();
					        	rotas[i][0]=value.get(0);
					        	rotas[i][1]=value.get(1);
					        	rotas[i][2]=value.get(2);
					    
					        //System.out.println("\n s"+(i+1)+".-->"+rotas[i][0]+"-"+rotas[i][1]+"-"+rotas[i][2]);
					   
					       }
					
					       
					       int tutucu=0,tutucu2=0; 
					       int yenitoplam;
					       int tutucuu[]=new int[5];
					       int toplamm[]=new int[5];
					       
					       for(i=0;i<5;i++) {
					    	   toplamm[i]=9999;
					    	   tutucuu[i]=9999;
					    	   
					       }
					       
					       for(i=0;i<6;i++)
					       {
					    	  			    	   
					    	   yenitoplam=yol3[0][rotas[i][0]]+yol3[rotas[i][0]][rotas[i][1]]+yol3[rotas[i][1]][rotas[i][2]]+yol3[rotas[i][2]][0];
			    			   if(yenitoplam==toplamm[0]||yenitoplam==toplamm[1]||yenitoplam==toplamm[2]||yenitoplam==toplamm[3]||yenitoplam==toplamm[4]) {
			    				   
			    				   
			    			   }
			    			   else if(yenitoplam<toplamm[0]) {
			    				   toplamm[4]=toplamm[3];
			    				   toplamm[3]=toplamm[2];
			    				   toplamm[2]=toplamm[1];
			    				   toplamm[1]=toplamm[0];
			    				   toplamm[0]=yenitoplam;
			    				   tutucuu[4]=tutucuu[3];
			    				   tutucuu[3]=tutucuu[2];
			    				   tutucuu[2]=tutucuu[1];
			    				   tutucuu[1]=tutucuu[0];
			    				   tutucuu[0]=i;
			    				   tutucu=i;
			    			   }
			    			   else if(yenitoplam<toplamm[1]&& yenitoplam!=toplamm[0]) {
			    				   toplamm[4]=toplamm[3];
			    				   toplamm[3]=toplamm[2];
			    				   toplamm[2]=toplamm[1];
			    				   toplamm[1]=yenitoplam;
			    				   tutucuu[4]=tutucuu[3];
			    				   tutucuu[3]=tutucuu[2];
			    				   tutucuu[2]=tutucuu[1];
			    				   tutucuu[1]=i;
			    				  
			    			   }
			    			   else if(yenitoplam<toplamm[2]) {
			    				   toplamm[4]=toplamm[3];
			    				   toplamm[3]=toplamm[2];
			    				   toplamm[2]=yenitoplam;
			    				   tutucuu[4]=tutucuu[3];
			    				   tutucuu[3]=tutucuu[2];
			    				   tutucuu[2]=i;
			    		
			    			   }
			    			   else if(yenitoplam<toplamm[3]) {
			    				   toplamm[4]=toplamm[3];
			    				   toplamm[3]=yenitoplam;
			    				   tutucuu[4]=tutucuu[3];
			    				   tutucuu[3]=i;
			    		
			    			   }
			    			   else if(yenitoplam<toplamm[4]) {
			    				   toplamm[4]=yenitoplam;
			    				   tutucuu[4]=i;
			    			   }
					       }
					       for(i=0;i<5;i++) {
					    	System.out.println("i:"+tutucuu[i]+"toplam:"+toplamm[i]);
					    	   
					       }
					       
					       // 3 þehir için resime çizgi çekme
					       
					       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
					        
					        Mat image = new Mat();
					        Mat image2 = new Mat();
					        Mat image3 = new Mat();
					        Mat image4 = new Mat();
					        
					        image=Highgui.imread("resmi\\siyasi.png");
					        image2=Highgui.imread("resmi\\siyasi.png");
					        image3=Highgui.imread("resmi\\siyasi.png");
					        image4=Highgui.imread("resmi\\siyasi.png");

					        
					        
					        
					       
					       int gecici,gidilecek;
					      // gecici=40;
					       
					       
					     for(j=0;j<3;j++) {
					    tutucu=tutucuu[j];
					    gecici=40;
						     for(i=0;i<3;i++) {
						    	 
						     gidilecek=a1[rotas[tutucu][i]];
						     
						//  System.out.println("--ARA-- Þehirden þehre yol");
						    if(j==0) {
						    	 while(gecici!=gidilecek) {
							         Core.line(image, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(44,22,22));
								        
								        Highgui.imwrite("1.Yol.png", image);
							    	 
							    	 System.out.print(gecici+"-");

							     gecici=deneme[gidilecek][gecici];
							      }
							     
							     System.out.print(gecici+"-"); 
							     
							     
							     
							     
						     }
						     if(j==1) {
						    	 while(gecici!=gidilecek) {
							         Core.line(image2, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(255,0,0));
								        
								        Highgui.imwrite("2.Yol.png", image2);
							    	 
							    	 System.out.print(gecici+"-");

							     gecici=deneme[gidilecek][gecici];
							      }
						    	 System.out.print(gecici+"-"); 
							
						     }
						     if(j==2) {
						    	 while(gecici!=gidilecek) {
						    	
						    		
							         Core.line(image3, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(0,0,255));
								        
								        Highgui.imwrite("3.Yol.png", image3);
							    	 
							    	 System.out.print(gecici+"-");

							     gecici=deneme[gidilecek][gecici];
							      
						    		
							      }
							     
						    	 System.out.print(gecici+"-"); 
						     }
						     
						    }
						     if(j==0) {
						    	 gidilecek=a1[0];
							     							  								     
								     while(gecici!=gidilecek) {
								         Core.line(image, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(44,22,22));
								         
									  Highgui.imwrite("1.Yol.png", image);
									  System.out.print(gecici+"-");
								     gecici=deneme[gidilecek][gecici];
								      }
								     System.out.print(gecici+"\n");
						    	 
						     }
						     if(j==1) {
						    	 gidilecek=a1[0];
							   							  								     
								     while(gecici!=gidilecek) {
								         Core.line(image2, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(255,0,0));
								         
									  Highgui.imwrite("2.Yol.png", image2);
									  System.out.print(gecici+"-");
								     gecici=deneme[gidilecek][gecici];
								      }
								     System.out.print(gecici+"\n");
						     }
						     if(j==2) {
						    	 if(tutucu==9999) {
					    			 
					    		 }
					    		 else {
						    	 
						    	 gidilecek=a1[0];
							   							  								     
								     while(gecici!=gidilecek) {
								         Core.line(image3, new Point(koordinat[gecici][0],koordinat[gecici][1]), new Point(koordinat[deneme[gidilecek][gecici]][0],koordinat[deneme[gidilecek][gecici]][1]), new Scalar(0,0,255));
								         
									  Highgui.imwrite("3.Yol.png", image3);
									  System.out.print(gecici+"-");
								     gecici=deneme[gidilecek][gecici];
								      }
					    		 }
								     System.out.print(gecici+"\n");
					    		 
						    	 
						     }
						     
							     }
			            break;

			        case 2 :
			        	  Scanner scan2 = new Scanner(System.in);
					        System.out.println("Gitmek istediðiniz 10 þehrin plakasini seçin: ");
					        int [] a2=new int[11];
					        int [][] yol10 = new int[11][11];
					        
					        a2[0]=40;
					        int toplam2=999999;
					        for(int x=1;x<=10;x++) {
						        System.out.println(x+". sehir plakasi :");
						        a2[x]=scan2.nextInt();
						        }
					        for(i=0;i<11;i++)
					        {
					        	for(j=0;j<11;j++)
					        	{
					        		
					        			yol10[i][j]=dist[a2[i]][a2[j]];
					        			
					        		//System.out.println("Yol:"+yol10[i][j]);
					        		
					        	}
					        }
					   
					        
					        prolab211<Integer> obj2 = new prolab211<Integer>();
					        Collection<Integer> input2 = new ArrayList<Integer>();
					        input2.add(1);
					        input2.add(2);
					        input2.add(3);
					        input2.add(4);
					        input2.add(5);
					        input2.add(6);
					        input2.add(7);
					        input2.add(8);
					        input2.add(9);
					        input2.add(10);

					        Collection<List<Integer>> output2 = obj2.permute(input2);
					        int kf2 = 0;
					        Set<List<Integer>> pnr2 = null;
					        for ( i = 0; i < 1; i++) {
					            pnr2 = new HashSet<List<Integer>>();
					            for(List<Integer> integers2 : output2){
					            pnr2.add(integers2.subList(i, integers2.size()));
					            }
					            kf2 = input2.size()- i;
					            /*System.out.println("P("+input2.size()+","+kf2+") :"+ 
					            "Count ("+pnr2.size()+") :- "+pnr2);*/
					        }
					        
					        int [][] rotas2=new int [3628800][10];
					        
					        List<Integer> value2 = null;
					        System.out.println("Iterating over list:"); 
					        Iterator<List<Integer>> pne2 = pnr2.iterator(); 
					        
					       for(i=0;i<3628800;i++) {
					        	value2=pne2.next();
					        	rotas2[i][0]=value2.get(0);
					        	rotas2[i][1]=value2.get(1);
					        	rotas2[i][2]=value2.get(2);
					        	rotas2[i][3]=value2.get(3);
					        	rotas2[i][4]=value2.get(4);
					        	rotas2[i][5]=value2.get(5);
					        	rotas2[i][6]=value2.get(6);
					        	rotas2[i][7]=value2.get(7);
					        	rotas2[i][8]=value2.get(8);
					        	rotas2[i][9]=value2.get(9);
					    
					       }
					        tutucu2=0;;
					        
					        tutucu2=0;;
						       
						       int yenitoplam2;
						       int tutucuu2[]=new int[5];
						       int toplamm2[]=new int[5];
						       
						       for(i=0;i<5;i++) {
						    	   toplamm2[i]=9999;
						    	   tutucuu2[i]=9999;
						    	   
						       }
					        
					          
					       for(i=0;i<3628800;i++)
					       {
					    	   yenitoplam=yol10[0][rotas2[i][0]]+yol10[rotas2[i][0]][rotas2[i][1]]+yol10[rotas2[i][1]][rotas2[i][2]]+yol10[rotas2[i][2]][rotas2[i][3]]
						    			+yol10[rotas2[i][3]][rotas2[i][4]]+yol10[rotas2[i][4]][rotas2[i][5]]+yol10[rotas2[i][5]][rotas2[i][6]]+yol10[rotas2[i][6]][rotas2[i][7]]
								    			+yol10[rotas2[i][7]][rotas2[i][8]]+yol10[rotas2[i][8]][rotas2[i][9]]+yol10[rotas2[i][9]][0];
				    		   
					    		  
			    			   if(yenitoplam==toplamm2[0]||yenitoplam==toplamm2[1]||yenitoplam==toplamm2[2]||yenitoplam==toplamm2[3]||yenitoplam==toplamm2[4]) {
			    				   
			    				   
			    			   }
			    			   else if(yenitoplam<toplamm2[0]) {
			    				   toplamm2[4]=toplamm2[3];
			    				   toplamm2[3]=toplamm2[2];
			    				   toplamm2[2]=toplamm2[1];
			    				   toplamm2[1]=toplamm2[0];
			    				   toplamm2[0]=yenitoplam;
			    				   tutucuu2[4]=tutucuu2[3];
			    				   tutucuu2[3]=tutucuu2[2];
			    				   tutucuu2[2]=tutucuu2[1];
			    				   tutucuu2[1]=tutucuu2[0];
			    				   tutucuu2[0]=i;
			    				   tutucu2=i;
			    			   }
			    			   else if(yenitoplam<toplamm2[1]&& yenitoplam!=toplamm2[0]) {
			    				   toplamm2[4]=toplamm2[3];
			    				   toplamm2[3]=toplamm2[2];
			    				   toplamm2[2]=toplamm2[1];
			    				   toplamm2[1]=yenitoplam;
			    				   tutucuu2[4]=tutucuu2[3];
			    				   tutucuu2[3]=tutucuu2[2];
			    				   tutucuu2[2]=tutucuu2[1];
			    				   tutucuu2[1]=i;
			    				   
			    				  
			    			   }
			    			   else if(yenitoplam<toplamm2[2]) {
			    				   toplamm2[4]=toplamm2[3];
			    				   toplamm2[3]=toplamm2[2];
			    				   toplamm2[2]=yenitoplam;
			    				   tutucuu2[4]=tutucuu2[3];
			    				   tutucuu2[3]=tutucuu2[2];
			    				   tutucuu2[2]=i;
			    		
			    			   }
			    			   else if(yenitoplam<toplamm2[3]) {
			    				   toplamm2[4]=toplamm2[3];
			    				   toplamm2[3]=yenitoplam;
			    				   tutucuu2[4]=tutucuu2[3];
			    				   tutucuu2[3]=i;
			    		
			    			   }
			    			   else if(yenitoplam<toplamm2[4]) {
			    				   toplamm2[4]=yenitoplam;
			    				   tutucuu2[4]=i;
			    			   }
					       }
					       for(i=0;i<5;i++) {
						    	System.out.println("i:"+tutucuu2[i]+"      toplam2:"+toplamm2[i]);
						    	   
						       }
					       
					       // 10 þehir için resime çizgi çekme

					       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
					        
					        Mat imagee = new Mat();
					        Mat image5 = new Mat();
					        Mat image6 = new Mat();
					        Mat image7 = new Mat();
					        Mat image8 = new Mat();
					        Mat image9 = new Mat();

					        image5=Highgui.imread("resmi\\siyasi.png");
					        image6=Highgui.imread("resmi\\siyasi.png");
					        image7=Highgui.imread("resmi\\siyasi.png");
					        image8=Highgui.imread("resmi\\siyasi.png");
					        image9=Highgui.imread("resmi\\siyasi.png");					      					        					        					        
					        
					        imagee=Highgui.imread("resmi\\siyasi.png");
					       
					       int gecici2,gidilecek2;
					       gecici2=40;
					       
					       for(j=0;j<5;j++) {
					    	   tutucu2=tutucuu2[j];
							   gecici=40;
					     
						     for(i=0;i<10;i++) {

						     gidilecek2=a2[rotas2[tutucu2][i]];
						    
						     
							  
							     
							  if(j==0) {
								  while(gecici2!=gidilecek2) {
							         Core.line(image5, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(44,22,22));
								        
								        Highgui.imwrite("1.Yol.png", image5);
							    	 
							    	 System.out.print(gecici2+"-");

							     gecici2=deneme[gidilecek2][gecici2];
							      }						     
						     System.out.print(gecici2+"-");							  							  
								  
							  }
							  if(j==1) {
								  while(gecici2!=gidilecek2) {
							         Core.line(image6, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(255,0,0));
								        
								        Highgui.imwrite("2.Yol.png", image6);
							    	 
							    	 System.out.print(gecici2+"-");

							     gecici2=deneme[gidilecek2][gecici2];
							      }						     
						     System.out.print(gecici2+"-");							  							  
								  
							  }
							  if(j==2) {
								  while(gecici2!=gidilecek2) {
							         Core.line(image7, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(0,0,255));
								        
								        Highgui.imwrite("3.Yol.png", image7);
							    	 
							    	 System.out.print(gecici2+"-");

							     gecici2=deneme[gidilecek2][gecici2];
							      }						     
						     System.out.print(gecici2+"-");							  							  
								  
							  }
							  if(j==3) {
								  while(gecici2!=gidilecek2) {
							         Core.line(image8, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(111,0,0));
								        
								        Highgui.imwrite("4.Yol.png", image8);
							    	 
							    	 System.out.print(gecici2+"-");

							     gecici2=deneme[gidilecek2][gecici2];
							      }						     
						     System.out.print(gecici2+"-");							  							  
								  
							  }
							  if(j==4) {
								  while(gecici2!=gidilecek2) {
							         Core.line(image9, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(0,0,111));
								        
								        Highgui.imwrite("5.Yol.png", image9);
							    	 
							    	 System.out.print(gecici2+"-");

							     gecici2=deneme[gidilecek2][gecici2];
							      }						     
						     System.out.print(gecici2+"-");							  							  
								  
							  }
 
						    	 }
						     if(j==0) {
						    	 gidilecek2=a2[0];
								 gecici2=40;	     
								 
								     while(gecici2!=gidilecek2) {
								    	 
								         Core.line(image5, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(44,22,22));
									        Highgui.imwrite("Asiyasi.png", image5);
									 System.out.print(gecici2+"-");	
								     gecici2=deneme[gidilecek2][gecici2];
								      }
								     System.out.print(gecici2+"\n");	
						     }
						     if(j==1) {
						    	 gidilecek2=a2[0];
								 gecici2=40;	     
								 
								     while(gecici2!=gidilecek2) {
								    	 
								         Core.line(image6, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(255,0,0));
									        Highgui.imwrite("Asiyasi.png", image6);
									 System.out.print(gecici2+"-");	
								     gecici2=deneme[gidilecek2][gecici2];
								      }
								     System.out.print(gecici2+"\n");	
						     }
						     if(j==2) {
						    	 gidilecek2=a2[0];
								 gecici2=40;	     
								 
								     while(gecici2!=gidilecek2) {
								    	 
								         Core.line(image7, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(0,0,255));
									        Highgui.imwrite("Asiyasi.png", image7);
									 System.out.print(gecici2+"-");	
								     gecici2=deneme[gidilecek2][gecici2];
								      }
								     System.out.print(gecici2+"\n");	
						     }
						     if(j==3) {
						    	 gidilecek2=a2[0];
								 gecici2=40;	     
								 
								     while(gecici2!=gidilecek2) {
								    	 
								         Core.line(image8, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(111,0,0));
									        Highgui.imwrite("Asiyasi.png", image8);
									        System.out.print(gecici2+"-");	
								     gecici2=deneme[gidilecek2][gecici2];
								      }
								     System.out.print(gecici2+"\n");	
						    	 
						    	 
						     }
						     if(j==4) {
						    	 gidilecek2=a2[0];
								 gecici2=40;	     
								 
								     while(gecici2!=gidilecek2) {
								    	 
								         Core.line(image9, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(0,0,111));
									        Highgui.imwrite("Asiyasi.png", image9);
									        System.out.print(gecici2+"-");	
								     gecici2=deneme[gidilecek2][gecici2];
								      }
								     System.out.print(gecici2+"\n");	
						    	 
						    	 
						     }
						     

						     }
						     gidilecek2=a2[0];
							 gecici2=40;
						     
							  System.out.println("--ARA--");
							     
							     while(gecici2!=gidilecek2) {
							         //Core.line(imagee, new Point(koordinat[gecici2][0],koordinat[gecici2][1]), new Point(koordinat[deneme[gidilecek2][gecici2]][0],koordinat[deneme[gidilecek2][gecici2]][1]), new Scalar(44,22,22));
							    	 
								       // Highgui.imwrite("Asiyasi.png", imagee);
							    	 System.out.println(gecici2);

							     gecici2=deneme[gidilecek2][gecici2];
							      }

			            break;
			        default :
			            System.out.println("Hatali secim! 1 ya da 2'ye basiniz.");
			            break;
			        }
			        
			    
			   //FLOYD BÝTÝS
     
        
			 	 
		}

	}