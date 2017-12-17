package busSimulationService;

/**
 * @author Lancelot
 * As per given in class from slides
 */
public class RandomNumberGenerator {

	private static int seed = 1000;
	
	public static Double random(){
		Double x = - Math.log((seed + 1) / 65536.0);
		seed = (25173 * seed + 13849) % 65536;
		return x;
	}
}