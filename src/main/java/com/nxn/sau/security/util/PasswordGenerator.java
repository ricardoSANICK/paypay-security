package com.nxn.sau.security.util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PasswordGenerator {
 
		public final static String[] numbers	= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		public final static String[] mayus 		= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		public final static String[] minus 		= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		public final static String[] mayusminus = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		//private static String[] specials = {"$","#","!","%","&","�"};
		
		public static String getRandomPassword(int length) {
					
			String[] symbols = symbols();
			
			long milis = new java.util.GregorianCalendar().getTimeInMillis();
			Random random = new Random(milis);
			StringBuilder sb = new StringBuilder();
			List<String> elements = new ArrayList<>();
													
			for (int i = 0; i < length; i++) {
			    int indexRandom = 0; 
			    
			    if(elements.size() == 1){
			    	indexRandom = random.nextInt( numbers.length );
			    	elements.add(numbers[indexRandom]);
			    } else if(elements.size() == 2){
			    	indexRandom = random.nextInt( mayus.length );
			    	elements.add(mayus[indexRandom]);
			    } else if(elements.size() == 3){
			    	indexRandom = random.nextInt( minus.length );
			    	elements.add(minus[indexRandom]);
			    } else {
			    	indexRandom = random.nextInt( symbols.length );
			    	elements.add(symbols[indexRandom]);
			    }
			}

			//Reordena los elementos de la lista
			Collections.shuffle(elements);
			
			
			for(String element : elements){
				sb.append(element);
			}
			
			return sb.toString();
		}
		
		private static String[] symbols(){
			
			int numberslength = numbers.length;
			int minuslength = minus.length;
			int mayuslength = mayus.length;
			
			int length = numberslength+minuslength+mayuslength;
			
			String[] _symbols = new String[length];
			
			for(int ix = 0; ix < length; ix++){
				if(numberslength > ix){
					_symbols[ix] = numbers[ix];
				}
				if(minuslength > ix){
					_symbols[numberslength+ix] = minus[new BigDecimal(ix-minuslength).abs().intValue()-1];
				}
				if(mayuslength > ix){
					_symbols[numberslength+minuslength+ix] =  mayus[new BigDecimal(ix-mayuslength).abs().intValue()-1];
				}
			}
			return _symbols;
		}
		
		public static void main(String[] args)
		{		
			DESEncryption des = new DESEncryption();
			
			if(args.length == 2)
			{
				if(args[0].equals("aleatorio")) // Generate and Encript
				{	
					String clave = getRandomPassword(Integer.parseInt(args[1]));
					System.out.println("Clave ["+ clave + "] generada y encriptada = " + des.encrypt(clave, des.getKey()));
					return;
				}
				else if(args[0].equals("encriptar")) // Encript
				{	
					System.out.println("Clave ["+ args[1] + "] encriptada = " + des.encrypt(args[1], des.getKey()));
					return;
				}
				else if(args[0].equals("desencriptar")) // Decript
				{
					System.out.println("Clave ["+ args[1] + "] desencriptada = "  + des.encrypt(args[1], des.getKey()));
					return;
				}
			}
			
			System.out.println("Funciones y sus parametros:\n ");
			
			System.out.println("- Generar password aleatoria encriptada con el numero de caracteres especificado:");
			System.out.println("  aleatorio [numero_caracteres]\n");
			
			System.out.println("- Encriptar password especificado:");
			System.out.println("  encriptar [password]\n");
			
			System.out.println("- Des-encriptar password especificado");
			System.out.println("  desencriptar [password_encriptado]\n");
			
			return;
		}
	}

