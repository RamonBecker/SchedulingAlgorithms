package Principal;

public class Processo implements Comparable<Processo> {

	private Integer id;
	private Integer at;
	private Integer bt;
	private int pr;
	private int ct;
	private int tat;
	private int wt;
	private int et;
	private int qat;

	public Processo(int id, int pr, int at, int bt) {
		this.id = id;
		this.pr = pr;
		this.at = at;
		this.bt = bt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Integer getBt() {
		return bt;
	}

	public void setBt(Integer bt) {
		this.bt = bt;
	}

	public int getPr() {
		return pr;
	}

	public void setPr(int pr) {
		this.pr = pr;
	}

	public int getCt() {
		return ct;
	}

	public void setCt(int ct) {
		this.ct = ct;
	}

	public int getTat() {
		return tat;
	}

	public void setTat(int tat) {
		this.tat = tat;
	}

	public int getWt() {
		return wt;
	}

	public void setWt() {
		this.wt = tat - bt;
	}

	public int getEt() {
		return et;
	}

	public void setEt() {
		this.et++;
	}

	public boolean completeBT() {
		if (this.et == this.bt) {
			return true;
		}
		return false;
	}

	public int getQat() {
		return qat;
	}

	public void setQat(int qat) {
		this.qat = qat;
	}

	public void dadosProcessoCompleto() {
	    tat = ct - at;
		wt = tat = bt;
	}

	@Override
	public String toString() {
		return "[id="+id+", pr="+pr+", at="+at+", bt="+bt+", ct="+ct+", tat="+tat+", wt="+wt+"]";
	}

	@Override
	public int compareTo(Processo outroProcesso) {
		return this.at.compareTo(outroProcesso.getAt());
	}
}
