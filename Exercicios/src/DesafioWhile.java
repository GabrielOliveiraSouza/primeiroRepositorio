import javax.swing.JOptionPane;

public class DesafioWhile {

	public static void main(String[] args) {
		
		
		ArthurLixo();
		
	}0
	
	public static void ArthurLixo() {
		Integer n1 = 0 ;
		Integer total = 0;
		int m = 0;
		do {
			n1 = Integer.parseInt(JOptionPane.showInputDialog("Digite uma nota valida"));

			if (n1 > 0 && n1 <= 10) {
				m++;
				total += n1;
			}
		} while (n1 != -1);

		total = total / m;
		System.out.println("média " + total);
	}
}
