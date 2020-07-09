
public class DesafioAritimetica {

	public static void main(String[] args) {
		
		Double a = (double) ((3+2)*6);
		Double res1 = (double) Math.pow(a, 3);
		double  resposta = res1 / (3*2) ;
		
		int a1 =  ((1-5)* (2-7))/2;
		Double resposta2 = Math.pow(a1 , 2);
		
		int total = (int) (resposta - resposta2) ;

		int cima = (int) Math.pow(total, 3);
		
		int baixo = (int) Math.pow(10, 3);
		
		System.out.println("RESULTADO = "+ cima/baixo);
		
		
	}
	
}
