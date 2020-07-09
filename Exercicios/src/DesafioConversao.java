import java.util.*;
public class DesafioConversao {

	
	public static void main (String args[]) {
		Scanner entrada = new Scanner(System.in);
	
		System.out.println("Digite Seu primeiro salario");
		int valor1 = entrada.nextInt();

		System.out.println("Digite Seu segundo salario");
		int valor2 = entrada.nextInt();
	
		System.out.println("Digite Seu terceiro salario");
		String var = entrada.next();
		
 int res =  var.equals("+") ? valor1 + valor2 : (int) (var.equals("-") ? valor1 - valor2 : 
	 var.equals("*") ? valor1 * valor2 : valor1/valor2)  ;
 
 System.out.println("o resultado é "+ res);
		
		
	}
}
