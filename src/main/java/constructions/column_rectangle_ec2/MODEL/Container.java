package constructions.column_rectangle_ec2.MODEL;

//Class Singleton
public class Container {

	private Data data;

	private static Container container = new Container();

	private Container() {
		data = new Data();
	}

	public Data getData() {
		return data;
	}

	public static Container getInstance() {
		return container;
	}
}
