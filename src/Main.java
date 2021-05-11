import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//comentario de prueba

		Scanner scan = new Scanner(System.in);
		
		String respuestaInicial ;
		String datosPersonales;
		
		String producto;
		int cantProducto;
		double valorUnitario;
		double valTotalProducto;

		String infoProductos = "\nCant. \t| Descripcion \t| Val. unitario \t| Val. total";
		//Cree las barras de separacion como variables para no tener que redactarlas cada vez
		String barraPrincipal = "\n*************************************************************************************";
		String barraProductos = "\n------------------------------------------------------------------------------------- \n";
		
		boolean repeat = true;
		
		double subtotal = 0;
		
		System.out.println("¿Desea realizar una compra? SI/NO");
		respuestaInicial= scan.nextLine();
			if ("SI".equals(respuestaInicial)) {
				datosPersonales = datosPersonales(scan, barraPrincipal);
				// Repeat e input: https://stackoverflow.com/questions/18975208/how-to-end-a-while-loop-via-user-input#comment28027654_18975269	
				//El while en public para poder calcular el subtotal de manera mas sencilla
				while (repeat) {
					producto = nombreProducto(scan);
					cantProducto = cantProducto(scan);
					valorUnitario = valorUnitario(scan);
					valTotalProducto = valorTotalProducto(valorUnitario, cantProducto);
					subtotal += valTotalProducto; 
				
					// Crea string con datos de productos porque no se armar arrays con objetos
					infoProductos += barraProductos + cantProducto + "\t | " + producto + "\t | " + valorUnitario + "\t\t | " + valTotalProducto + "\t | "  ;	
					
					repeat = agregarProducto(scan); 
				}
				cupon (datosPersonales, infoProductos, barraPrincipal, barraProductos, subtotal);	
			} else {
				System.out.println("Fin del programa");
			}
	}

	private static String datosPersonales (Scanner scan, String barraPrincipal) {
		String nombre; 
		String domicilio;
		String datosPersonales;

		System.out.println("Ingrese su nombre");
		nombre = scan.nextLine();
		
		System.out.println("Ingrese su domicilio");
		domicilio = scan.nextLine();
		
		datosPersonales = "\nNOMBRE: " + nombre + barraPrincipal + "\n DOMICILIO: " + domicilio ;

		return datosPersonales; 
	}
		
	private static String nombreProducto (Scanner scan) {
		String producto;
		
		System.out.println("Ingrese el nombre y descripción del producto");
		producto = scan.nextLine();
		
		return producto;
		
	}
	
	private static int cantProducto (Scanner scan) {

		int cantProducto;
		
		System.out.println("Ingrese la cantidad de unidades");
		cantProducto = scan.nextInt();
		
		return cantProducto;
	
	}
	
	private static double valorUnitario (Scanner scan) {
		double valorUnitario;
		
		System.out.println("Ingrese el valor unitario (sin IVA) del producto");
		valorUnitario = scan.nextDouble();
		scan.nextLine();
		
		return valorUnitario;
	}
	
	private static double valorTotalProducto (double valorUnitario, int cantProducto) {
		double valTotalProducto;
		
		valTotalProducto = valorUnitario * cantProducto;
		
		return valTotalProducto;
	}
	
	private static boolean agregarProducto (Scanner scan) {
		String input;
		boolean repeat = true;
			
		System.out.println("¿Desea agregar otro producto? SI/NO (en mayuscula)");
		input = scan.nextLine();

		switch (input) {
		case "SI": 
		case "si":
		case "Si":
		case "S":
				repeat = true;
		break;
		case "NO":
		case "no":
		case "No":
		case "N":
			System.out.println("Gracias por comprar con nosotros");
			repeat = false;
		break;
		default: 
			System.out.println("Por favor ingrese SI en caso de querer agregar otro producto, o NO si desea terminar la operacion");
			// Llama a la misma funcion para que se repita el proceso de seleccion de SI o NO
			agregarProducto(scan);
			}
		return repeat;
	}
	
	private static double calcularIVA (double subtotal) {
		final double IVA = 0.21; 
		double ivaProductos;
	
		ivaProductos = subtotal * IVA;
	
		return ivaProductos;
	
	}
	
	private static double calcularTotal (double subtotal, double ivaProductos) {
			double total;
			
			total = subtotal + ivaProductos;
			
			return total;
		}
	
	private static void cupon (String datosPersonales, String infoProductos, String barraPrincipal, String barraProductos,  double subtotal) {
			Date fecha = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");	
			String fechaActual = sdf.format(fecha);
			
			System.out.println(barraPrincipal);
			System.out.println("FECHA: " + fechaActual); 
			System.out.println(barraPrincipal + datosPersonales + barraPrincipal + infoProductos + barraPrincipal);
			System.out.println("SUBTOTAL: " + subtotal);
			System.out.println("IVA: " + calcularIVA(subtotal));
			System.out.println("TOTAL: " + calcularTotal(subtotal, calcularIVA(subtotal)));
			System.out.println(barraPrincipal);
		}
}
