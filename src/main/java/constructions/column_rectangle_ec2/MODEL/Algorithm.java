package constructions.column_rectangle_ec2.MODEL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import javafx.fxml.FXML;

public class Algorithm {

	ArrayList<Double> listAs = new ArrayList<Double>();
	ArrayList<Double> listAs1or2 = new ArrayList<Double>();

	public DecimalFormat df = new DecimalFormat("#0.0000000");
	public DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
	
	dfs.setDecimalSeparator('.');
	df.setDecimalFormatSymbols(dfs);

	private Data data = new Data();

	double Ac, Ic, ic, Afi, Asmin, Asmax, Asassum, Abig, Bbig, C = 0.7, l0, lambda, lambdaLim, ei, ee, e0, etot, Is, h,
			b, a, k1, n, k2, Kc, Ks, EI, Nb, eta, es2, es1, ksiefflim, As1, As2, ueff, ksieff;
	double As1prov = 0;
	double As2prov = 0;

	private double getAfi(double d) {
		double Afi = Math.PI * Math.pow(d, 2) / 4;
		Afi = Double.valueOf(df.format(Afi));
		return Afi;
	}

	private double getAc(double b, double h) {
		double Ac = b * h;
		Ac = Double.valueOf(df.format(Ac));
		return Ac;
	}

	private double getAsmin(double Ned, double fyd, double Ac) {
		double Asmin = Math.max(0.1 * Ned / (fyd * 1000), 0.002 * Ac);
		Asmin = Double.valueOf(df.format(Asmin));
		return Asmin;
	}

	private double getAsmax(double Ac) {
		double Asmax = 0.04 * Ac;
		Asmax = Double.valueOf(df.format(Asmax));
		return Asmax;
	}

	private double getIc(double b, double h) {
		double Ic = (b * h * h * h / 12);
		Ic = Double.valueOf(df.format(Ic));
		return Ic;
	}

	private double get_ic(double Ac, double Ic) {
		double ic = Math.sqrt(Ic / Ac);
		ic = Double.valueOf(df.format(ic));
		return ic;
	}

	private double get_Abig(double eff) {
		double Abig = 1 / (1 + (0.2 * eff));
		Abig = Double.valueOf(df.format(Abig));
		return Abig;
	}

	// Metoda zwraca obliczeniową wartość fyd w MPa
	private double getfyd() {
		double fyk = data.getValue_fyk();
		double ys = data.getValue_ys();

		double fyd = (fyk / ys);
		fyd = Double.valueOf(df.format(fyd));
		return fyd;
	}

	// Metoda zwraca obliczeniową wartość fyd w kN/m2
	private double getfydKNm() {
		double fyk = data.getValue_fyk();
		double ys = data.getValue_ys();

		double fyd = ((fyk / ys) * 1000);
		fyd = Double.valueOf(df.format(fyd));
		return fyd;
	}

	// Metoda zwraca obliczeniową wartość fcd w MPa
	private double getfcd() {
		double fck = data.getValue_fck();
		double yc = data.getValue_yc();

		double fcd = (fck / yc);
		fcd = Double.valueOf(df.format(fcd));
		return fcd;
	}

	// Metoda zwraca obliczeniową wartość fcd w kN/m2
	private double getfcdKNm() {
		double fck = data.getValue_fck();
		double yc = data.getValue_yc();

		double fcd = ((fck / yc) * 1000);
		fcd = Double.valueOf(df.format(fcd));
		return fcd;
	}

	private double get_Bbig() {
		double Bbig = Math.sqrt(1 + (2 * ((Asassum * getfydKNm())) / ((Ac * getfcdKNm()))));
		Bbig = Double.valueOf(df.format(Bbig));
		return Bbig;
	}

	private double get_l0(double Hd, double u) {
		double l0 = Hd * u;
		l0 = Double.valueOf(df.format(l0));
		return l0;
	}

	// private void createListAs(){
	// double Afi = Double.valueOf(df.format(getAfi(data.getValue_fi())));
	// double Afipom = Afi;
	//
	// while(Afi <= Asmax){
	// if((Afi >= Asmin) && (Afi <= Asmax)){
	// listAs.add(Double.valueOf(df.format(Afi)));
	// Afi = Afi + Afipom;
	// }
	// else if(Afi < Asmin){
	// Afi = Afi + Afipom;
	// }
	// }
	// }

	private void createListAs() {
		// Warunki na minimalne zbrojenie w całym przekroju:
		// - fi x 4
		// - wzór Asmin

		double As = Double.valueOf(df.format(getAfi(data.getValue_fi()))); // pole
																			// 1
																			// pręta
																			// -
																			// zaokraglone
		double Afipom = As; // tu jest zapamiętana wartość pola 1 pręta
		As = 4 * Afipom; // przyjęcie pierwszego warunku

		if (As < Asmin) {
			As = Asmin;
			As = createAsprov(As);
		} else {
			// to do nothing
		}

		listAs.add(As); // dodajemy do listy na pierwszej pozycji wartość
						// minimalnego zbrojenia

		while (As <= Asmax) {
			As = As + Afipom; // powiększamy As o pole jednego preta
			listAs.add(Double.valueOf(df.format(As))); // dodajemy wartość
														// powiększonego pola As
														// do listy 'listAs'
		}
	}

	// private void createListAs1or2(){
	// double Afi = Double.valueOf(df.format(getAfi(data.getValue_fi())));
	// double Afipom = Afi;
	//
	// listAs1or2.add(2 * Double.valueOf(df.format(Afi)));
	//
	// while(Afi <= Asmax){
	// if((Afi >= Asmin) && (Afi <= Asmax)){
	// listAs.add(Double.valueOf(df.format(Afi)));
	// Afi = Afi + Afipom;
	// }
	// else if(Afi < Asmin){
	// Afi = Afi + Afipom;
	// }
	// }
	// }

	private void createListAs1or2() {
		// Metoda dodaje elementy do listy jako rozwiązania dla As1 i As2
		// Warunki
		// - fi x 2
		// - 0.5 x Asmin

		double As = Double.valueOf(df.format(getAfi(data.getValue_fi()))); // pole
																			// 1
																			// pręta
																			// -
																			// zaokraglone
		double Afipom = As; // tu jest zapamiętana wartość pola 1 pręta
		As = 2 * Afipom; // przyjęcie pierwszego warunku

		while (As < (0.5 * Asmin)) {
			System.out.println("As: " + As);
			System.out.println("0,5Asmin: " + (0.5 * Asmin));
			As = As + Afipom;
		}

		listAs1or2.add(As); // dodajemy do listy na pierwszej pozycji wartość
							// POŁOWY minimalnego zbrojenia

		while (As <= Asmax) {
			As = As + Afipom; // powiększamy As o pole jednego preta
			listAs1or2.add(Double.valueOf(df.format(As))); // dodajemy wartość
															// powiększonego
															// pola As do listy
															// 'listAs1or2'
		}
	}

	private double createAsprov(double As1or2) {
		int i = 0; // pomocnicza zmienna wskazująca na początek listy
		while (listAs1or2.get(i) < As1or2) {
			i++;
		}
		return listAs1or2.get(i);
	}

	private void methodOfNominalStiffness() {

		// Double.valueOf(df.format( ));

		if (lambda > lambdaLim) {

			Is = Double.valueOf(df.format(Asassum * Math.pow((0.5 * h - a), 2)));
			System.out.println("Is: " + Is);

			k1 = Double.valueOf(df.format(Math.sqrt(data.getValue_fck() / 20)));
			System.out.println("k1: " + k1);

			n = Double.valueOf(df.format(data.getValue_Ned() / (Ac * getfcdKNm())));
			System.out.println("n: " + n);

			k2 = Double.valueOf(df.format(n * lambda / 170));
			System.out.println("k2: " + k2);

			Kc = Double.valueOf(df.format((k1 * k2) / (1 + data.getValue_eff())));
			System.out.println("Kc: " + Kc);

			Ks = 1.0;
			System.out.println("Ks: " + Ks);

			EI = Double.valueOf(df.format(
					Kc * data.getValue_Ec() * 1000000 * Ic + Ks * data.getValue_Es() * 1000000 * Math.pow(Is, 2)));
			System.out.println("EI: " + EI);

			Nb = Double.valueOf(df.format((Math.pow(Math.PI, 2) * EI) / Math.pow(l0, 2)));
			System.out.println("Nb: " + Nb);

			eta = Double.valueOf(df.format(1 / (1 - (data.getValue_Ned() / Nb))));
			System.out.println("eta: " + eta);

			etot = Double.valueOf(df.format(eta * e0));
			System.out.println("etot: " + etot);

		} else {

			etot = e0;
			System.out.println("etot: " + etot);
		}
	}

	@FXML
	private void calculateAlgorithm() {

		As1 = 0;
		As2 = 0;
		As1prov = 0;
		As2prov = 0;

		b = Double.valueOf(df.format(data.getValue_b()));
		System.out.println("b: " + b);

		h = Double.valueOf(df.format(data.getValue_h()));
		System.out.println("h: " + h);

		a = Double.valueOf(df.format(data.getValue_a()));
		System.out.println("a: " + a);

		Ac = getAc(data.getValue_b(), data.getValue_h());
		System.out.println("Pole betonu: " + Ac);

		Ic = getIc(data.getValue_b(), data.getValue_h());
		System.out.println("Ic: " + Ic);

		ic = get_ic(Ac, Ic);
		System.out.println("ic: " + ic);

		Afi = getAfi(data.getValue_fi());
		System.out.println("Afi: " + Afi);

		Asmin = getAsmin(data.getValue_Ned(), data.getValue_fyk(), Ac);
		System.out.println("Asmin: " + Asmin);

		Asmax = getAsmax(Ac);
		System.out.println("Asmax: " + Asmax);

		createListAs();
		createListAs1or2();

		System.out.println("Lista poz. 0 " + listAs.get(0));
		System.out.println("Lista poz. 1 " + listAs.get(1));
		System.out.println("Lista poz. 2 " + listAs.get(2));
		System.out.println("Lista poz. 3 " + listAs.get(3));
		System.out.println("Lista poz. 4 " + listAs.get(4));

		System.out.println("Lista poz. 10 " + listAs1or2.get(0));
		System.out.println("Lista poz. 11 " + listAs1or2.get(1));
		System.out.println("Lista poz. 12 " + listAs1or2.get(2));
		System.out.println("Lista poz. 13 " + listAs1or2.get(3));
		System.out.println("Lista poz. 14 " + listAs1or2.get(4));

		Abig = get_Abig(data.getValue_eff());
		System.out.println("A: " + Abig);

		l0 = get_l0(data.getValue_Hd(), data.getValue_u());
		System.out.println("l0: " + l0);

		ksiefflim = Double
				.valueOf(df.format(0.8 * (0.0035 / (0.0035 + (getfydKNm() / (data.getValue_Es() * 1000000))))));
		System.out.println("ksi: " + ksiefflim);

		int i = 0;
		Asassum = listAs.get(i);
		while (Asassum != (As1prov + As2prov)) {
			Asassum = listAs.get(i);
			System.out.println("Asassum: " + Asassum);
			System.out.println("Asmax: " + Asmax);

			Bbig = get_Bbig();
			System.out.println("B: " + Bbig);

			lambda = Double.valueOf(df.format(l0 / ic));
			System.out.println("Lambda: " + lambda);

			lambdaLim = Double
					.valueOf(df.format((20 * Abig * Bbig * C) / (Math.sqrt(data.getValue_Ned() / (Ac * getfcdKNm())))));
			System.out.println("LambdaLim: " + lambdaLim);

			ei = Double.valueOf(df.format(l0 / 400));
			System.out.println("ei: " + ei);

			ee = Double.valueOf(df.format(Math.abs((data.getValue_Med() / data.getValue_Ned()))));
			System.out.println("ee: " + ee);

			double e0pom1 = ee + ei;
			double e0pom2 = Math.max(data.getValue_h() / 30, 0.02);
			e0 = Double.valueOf(Math.max(e0pom1, e0pom2));
			System.out.println("e0: " + e0);

			methodOfNominalStiffness(); // tu mamy wiliczone etot

			es2 = Double.valueOf(df.format(0.5 * h - etot - a));
			System.out.println("es2: " + es2);

			es1 = Double.valueOf(df.format(0.5 * h + etot - a));
			System.out.println("es1: " + es1);

			if (data.getValue_ifSymetric() == true) {

				// Double.valueOf(df.format( ));

				System.out.println("Policz zbrojenie symetryczne");

			} else {

				System.out.println("Zacznij od Dużego mimośrodu");

				System.out.println("Asassum: " + Asassum);

				As2 = Double.valueOf(df.format((data.getValue_Ned() * es1
						- ksiefflim * (1 - 0.5 * ksiefflim) * getfcdKNm() * b * Math.pow(h - a, 2))
						/ (getfydKNm() * (h - 2 * a))));
				System.out.println("As2: " + As2);

				As2prov = Double.valueOf(df.format(createAsprov(As2)));
				System.out.println("As2prov: " + As2prov);

				if (As2 <= 0) {

					As2 = Double.valueOf(df.format(Asmin));
					As2prov = Double.valueOf(df.format(createAsprov(As2)));
					System.out.println("As2prov: " + As2prov);

					ueff = Double.valueOf(df.format((data.getValue_Ned() * es1 - getfydKNm() * As2prov * (h - 2 * a))
							/ (getfcdKNm() * b * Math.pow(h - 2 * a, 2))));
					System.out.println("ueff: " + ueff);

					ksieff = Double.valueOf(df.format(1 - Math.sqrt(1 - 2 * ueff)));
					System.out.println("ksieff: " + ksieff);

					if (ksieff > ((2 * a) / (h - a))) {

						As1 = Double.valueOf(df.format(
								(ksieff * b * (h - a) * getfcdKNm() + As2prov * getfydKNm() - data.getValue_Ned())
										/ (getfydKNm())));
						System.out.println("As1: " + As1);

					} else {
						As1 = Double.valueOf(df.format((-1 * data.getValue_Ned() * es2) / (getfydKNm() * (h - 2 * a))));
						System.out.println("As1: " + As1);
					}
				} else if (As2 <= 0.5 * Asmin) {

					As1 = Double.valueOf(df.format(Asmin));

				} else {

					As1 = Double.valueOf(df.format(
							(ksiefflim * b * (h - a) * getfcdKNm() + As2prov * getfydKNm() - data.getValue_Ned())
									/ getfydKNm()));

				}

				if ((As1 >= 0) && (As1 <= 0.5 * Asmin)) {

					As1prov = 0.5 * Double.valueOf(df.format(createAsprov(As1)));
					System.out.println("As1prov: " + As1prov);

				} else if (As1 > 0.5 * Asmin) {

					As1prov = Double.valueOf(df.format(createAsprov(As1)));
					System.out.println("As1prov: " + As1prov);
					System.out.println("Błąd 0");

				} else {
					System.out.println("Mały mimośród");
				}
				System.out.println("Błąd 1");
			}
			System.out.println("Błąd 2");

			i++;
			System.out.println("Błąd 3");
		}
		System.out.println("Błąd 4");
		System.out.println("Symetryczny? " + data.getValue_ifSymetric());
		System.out.println("Błąd 5");
		System.out.println("Wyniki:");
		System.out.println("Asassum: " + Asassum);
		System.out.println("As1prov: " + As1prov);
		System.out.println("As2prov: " + As2prov);
	}
	
}
