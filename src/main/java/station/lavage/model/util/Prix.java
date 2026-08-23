package station.lavage.model.util;

public class Prix {
	private String unite;
	private int montant;

	
	public Prix convertirPrix(String unite_sortie) {
		  Prix prixsortie = new Prix();
		  prixsortie.setUnite(unite_sortie);
		  prixsortie.setMontant(montant);
		  return prixsortie;
	}
	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

}
